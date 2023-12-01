package top.flobby.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import top.flobby.train.business.domain.DailyTrain;
import top.flobby.train.business.domain.DailyTrainTicket;
import top.flobby.train.business.domain.DailyTrainTicketExample;
import top.flobby.train.business.domain.TrainStation;
import top.flobby.train.business.enums.SeatTypeEnum;
import top.flobby.train.business.enums.TrainTypeEnum;
import top.flobby.train.business.mapper.DailyTrainTicketMapper;
import top.flobby.train.business.req.DailyTrainTicketQueryReq;
import top.flobby.train.business.req.DailyTrainTicketSaveReq;
import top.flobby.train.business.resp.DailyTrainTicketQueryResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.common.utils.SnowUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author Flobby
 */

@Service
public class DailyTrainTicketService {

    private static final Logger LOG = LoggerFactory.getLogger(DailyTrainTicketService.class);

    @Resource
    private DailyTrainTicketMapper dailyTrainTicketMapper;
    @Resource
    private TrainStationService trainStationService;
    @Resource
    private DailyTrainSeatService dailyTrainSeatService;

    public void save(DailyTrainTicketSaveReq req) {
        DateTime now = DateTime.now();
        DailyTrainTicket dailyTrainTicket = BeanUtil.copyProperties(req, DailyTrainTicket.class);
        if (ObjectUtil.isNull(dailyTrainTicket.getId())) {
            dailyTrainTicket.setId(SnowUtil.getSnowflakeNextId());
            dailyTrainTicket.setCreateTime(now);
            dailyTrainTicket.setUpdateTime(now);
            dailyTrainTicketMapper.insert(dailyTrainTicket);
        } else {
            dailyTrainTicket.setUpdateTime(now);
            dailyTrainTicketMapper.updateByPrimaryKey(dailyTrainTicket);
        }
    }

    @CachePut(value = "DailyTrainTicketService.queryList")
    public PageResp<DailyTrainTicketQueryResp> queryList2(DailyTrainTicketQueryReq req) {
        return queryList(req);
    }

    // @Cacheable(value = "DailyTrainTicketService.queryList3")
    public PageResp<DailyTrainTicketQueryResp> queryList3(DailyTrainTicketQueryReq req) {
        LOG.info("测试缓存击穿");
        return null;
    }

    // @Cacheable(value = "DailyTrainTicketService.queryList")
    public PageResp<DailyTrainTicketQueryResp> queryList(DailyTrainTicketQueryReq req) {
        DailyTrainTicketExample dailyTrainTicketExample = new DailyTrainTicketExample();
        dailyTrainTicketExample.setOrderByClause("id desc");
        DailyTrainTicketExample.Criteria criteria = dailyTrainTicketExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getDate())) {
            criteria.andDateEqualTo(req.getDate());
        }
        if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
            criteria.andTrainCodeEqualTo(req.getTrainCode());
        }
        if (ObjectUtil.isNotEmpty(req.getStart())) {
            criteria.andStartEqualTo(req.getStart());
        }
        if (ObjectUtil.isNotEmpty(req.getEnd())) {
            criteria.andEndEqualTo(req.getEnd());
        }
        // 查询日期是今天开始15天内的车票
        DateTime now = DateTime.now();
        DateTime end = DateUtil.offsetDay(now, 15);
        criteria.andDateBetween(now.toJdkDate(), end.toJdkDate());
        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<DailyTrainTicket> dailyTrainTicketList = dailyTrainTicketMapper.selectByExample(dailyTrainTicketExample);

        PageInfo<DailyTrainTicket> pageInfo = new PageInfo<>(dailyTrainTicketList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<DailyTrainTicketQueryResp> list = BeanUtil.copyToList(dailyTrainTicketList, DailyTrainTicketQueryResp.class);

        PageResp<DailyTrainTicketQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        dailyTrainTicketMapper.deleteByPrimaryKey(id);
    }


    public void genDailyTicket(Date date, String trainCode, DailyTrain dailyTrain) {
        // 删除原有数据
        DailyTrainTicketExample dailyTrainTicketExample = new DailyTrainTicketExample();
        dailyTrainTicketExample.createCriteria().andTrainCodeEqualTo(trainCode).andDateEqualTo(date);
        dailyTrainTicketMapper.deleteByExample(dailyTrainTicketExample);

        // 查询车站信息
        List<TrainStation> stationList = trainStationService.selectStationByTrainCode(trainCode);
        if (CollUtil.isEmpty(stationList)) {
            LOG.info("车次{}没有车站信息，生成结束", trainCode);
            return;
        }

        DateTime now = DateTime.now();
        for (int i = 0; i < stationList.size(); i++) {
            TrainStation startStation = stationList.get(i);
            BigDecimal sumKM = BigDecimal.ZERO;

            for (int j = i + 1; j < stationList.size(); j++) {
                TrainStation endStation = stationList.get(j);

                sumKM = sumKM.add(endStation.getKm());

                DailyTrainTicket dailyTrainTicket = new DailyTrainTicket();
                dailyTrainTicket.setId(SnowUtil.getSnowflakeNextId());
                dailyTrainTicket.setDate(date);
                dailyTrainTicket.setTrainCode(trainCode);
                dailyTrainTicket.setStart(startStation.getName());
                dailyTrainTicket.setStartPinyin(startStation.getNamePinyin());
                dailyTrainTicket.setStartTime(startStation.getOutTime());
                dailyTrainTicket.setStartIndex(startStation.getIndex());
                dailyTrainTicket.setEnd(endStation.getName());
                dailyTrainTicket.setEndPinyin(endStation.getNamePinyin());
                dailyTrainTicket.setEndTime(endStation.getInTime());
                dailyTrainTicket.setEndIndex(endStation.getIndex());

                int ydz = dailyTrainSeatService.countSeat(date, trainCode, SeatTypeEnum.YDZ.getCode());
                int edz = dailyTrainSeatService.countSeat(date, trainCode, SeatTypeEnum.EDZ.getCode());
                int rw = dailyTrainSeatService.countSeat(date, trainCode, SeatTypeEnum.RW.getCode());
                int yw = dailyTrainSeatService.countSeat(date, trainCode, SeatTypeEnum.YW.getCode());

                // 票价 = 距离 * 座位类型基础票价 * 座位系数
                String trainType = dailyTrain.getType();
                BigDecimal priceRate = EnumUtil.getFieldBy(TrainTypeEnum::getPriceRate, TrainTypeEnum::getCode, trainType);
                BigDecimal ydzPrice = sumKM.multiply(SeatTypeEnum.YDZ.getPrice())
                        .multiply(priceRate)
                        .setScale(2, RoundingMode.HALF_UP);
                BigDecimal edzPrice = sumKM.multiply(SeatTypeEnum.EDZ.getPrice())
                        .multiply(priceRate)
                        .setScale(2, RoundingMode.HALF_UP);
                BigDecimal rwPrice = sumKM.multiply(SeatTypeEnum.RW.getPrice())
                        .multiply(priceRate)
                        .setScale(2, RoundingMode.HALF_UP);
                BigDecimal ywPrice = sumKM.multiply(SeatTypeEnum.YW.getPrice())
                        .multiply(priceRate)
                        .setScale(2, RoundingMode.HALF_UP);


                dailyTrainTicket.setYdz(ydz);
                dailyTrainTicket.setYdzPrice(ydzPrice);
                dailyTrainTicket.setEdz(edz);
                dailyTrainTicket.setEdzPrice(edzPrice);
                dailyTrainTicket.setRw(rw);
                dailyTrainTicket.setRwPrice(rwPrice);
                dailyTrainTicket.setYw(yw);
                dailyTrainTicket.setYwPrice(ywPrice);
                dailyTrainTicket.setCreateTime(now);
                dailyTrainTicket.setUpdateTime(now);
                dailyTrainTicketMapper.insert(dailyTrainTicket);
            }
        }
    }

    public DailyTrainTicket selectByUnique(Date date, String trainCode, String start, String end) {
        DailyTrainTicketExample dailyTrainTicketExample = new DailyTrainTicketExample();
        dailyTrainTicketExample.createCriteria()
                .andDateEqualTo(date)
                .andTrainCodeEqualTo(trainCode)
                .andStartEqualTo(start)
                .andEndEqualTo(end);
        List<DailyTrainTicket> dailyTrainTicketList = dailyTrainTicketMapper.selectByExample(dailyTrainTicketExample);
        if (CollUtil.isEmpty(dailyTrainTicketList)) {
            return null;
        }
        return dailyTrainTicketList.get(0);
    }
}