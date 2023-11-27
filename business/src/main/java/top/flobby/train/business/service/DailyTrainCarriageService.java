package top.flobby.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.flobby.train.business.domain.DailyTrainCarriage;
import top.flobby.train.business.domain.DailyTrainCarriageExample;
import top.flobby.train.business.domain.TrainCarriage;
import top.flobby.train.business.enums.SeatColEnum;
import top.flobby.train.business.mapper.DailyTrainCarriageMapper;
import top.flobby.train.business.req.DailyTrainCarriageQueryReq;
import top.flobby.train.business.req.DailyTrainCarriageSaveReq;
import top.flobby.train.business.resp.DailyTrainCarriageQueryResp;
import top.flobby.train.common.exception.BusinessException;
import top.flobby.train.common.exception.BusinessExceptionEnum;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.common.utils.SnowUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Flobby
 */

@Service
public class DailyTrainCarriageService {

    private static final Logger LOG = LoggerFactory.getLogger(DailyTrainCarriageService.class);

    @Resource
    private DailyTrainCarriageMapper dailyTrainCarriageMapper;
    @Resource
    private TrainCarriageService trainCarriageService;

    public void save(DailyTrainCarriageSaveReq req) {
        DateTime now = DateTime.now();
        DailyTrainCarriage dailyTrainCarriage = BeanUtil.copyProperties(req, DailyTrainCarriage.class);
        // 自动计算列数和总座位数
        int colCount = SeatColEnum.getColsByType(req.getSeatType()).size();
        dailyTrainCarriage.setColCount(colCount);
        dailyTrainCarriage.setSeatCount(colCount * req.getRowCount());
        if (ObjectUtil.isNull(dailyTrainCarriage.getId())) {
            // 厢号唯一性校验
            if (selectIndexByUnique(dailyTrainCarriage.getTrainCode(), dailyTrainCarriage.getIndex())) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR);
            }
            dailyTrainCarriage.setId(SnowUtil.getSnowflakeNextId());
            dailyTrainCarriage.setCreateTime(now);
            dailyTrainCarriage.setUpdateTime(now);
            dailyTrainCarriageMapper.insert(dailyTrainCarriage);
        } else {
            dailyTrainCarriage.setUpdateTime(now);
            dailyTrainCarriageMapper.updateByPrimaryKey(dailyTrainCarriage);
        }
    }

    public PageResp<DailyTrainCarriageQueryResp> queryList(DailyTrainCarriageQueryReq req) {
        DailyTrainCarriageExample dailyTrainCarriageExample = new DailyTrainCarriageExample();
        dailyTrainCarriageExample.setOrderByClause("date desc, train_code asc");
        DailyTrainCarriageExample.Criteria criteria = dailyTrainCarriageExample.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
            criteria.andTrainCodeEqualTo(req.getTrainCode());
        }
        if (ObjectUtil.isNotEmpty(req.getDate())) {
            criteria.andDateEqualTo(req.getDate());
        }
        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<DailyTrainCarriage> dailyTrainCarriageList = dailyTrainCarriageMapper.selectByExample(dailyTrainCarriageExample);

        PageInfo<DailyTrainCarriage> pageInfo = new PageInfo<>(dailyTrainCarriageList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<DailyTrainCarriageQueryResp> list = BeanUtil.copyToList(dailyTrainCarriageList, DailyTrainCarriageQueryResp.class);

        PageResp<DailyTrainCarriageQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        dailyTrainCarriageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 唯一性校验
     *
     * @param trainCode 列车代码
     * @param index     车厢号
     * @return boolean
     */
    private boolean selectIndexByUnique(String trainCode, Integer index) {
        DailyTrainCarriageExample trainCarriageExample = new DailyTrainCarriageExample();
        DailyTrainCarriageExample.Criteria criteria = trainCarriageExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode).andIndexEqualTo(index);
        List<DailyTrainCarriage> list = dailyTrainCarriageMapper.selectByExample(trainCarriageExample);
        return CollUtil.isNotEmpty(list);
    }

    public void genDailyCarriage(String trainCode, Date date) {
        // 删除原有数据
        DailyTrainCarriageExample dailyTrainCarriageExample = new DailyTrainCarriageExample();
        dailyTrainCarriageExample.createCriteria().andDateEqualTo(date).andTrainCodeEqualTo(trainCode);
        dailyTrainCarriageMapper.deleteByExample(dailyTrainCarriageExample);
        // 生成新数据
        List<TrainCarriage> carriageList = trainCarriageService.selectByTrainCode(trainCode);
        if (CollUtil.isEmpty(carriageList)) {
            LOG.error("没有车厢信息");
            throw new BusinessException(BusinessExceptionEnum.TRAIN_CARRIAGE_IS_EMPTY);
        }
        for (TrainCarriage trainCarriage : carriageList) {
            DateTime now = DateTime.now();
            DailyTrainCarriage dailyTrainCarriage = BeanUtil.copyProperties(trainCarriage, DailyTrainCarriage.class);
            dailyTrainCarriage.setId(SnowUtil.getSnowflakeNextId());
            dailyTrainCarriage.setDate(date);
            dailyTrainCarriage.setCreateTime(now);
            dailyTrainCarriage.setUpdateTime(now);
            dailyTrainCarriageMapper.insert(dailyTrainCarriage);
        }
        LOG.info("生成 [{}] 车次 [{}] 的车厢信息结束", DateUtil.formatDate(date),trainCode);
    }

    public List<DailyTrainCarriage> selectBySeatType (Date date, String trainCode, String seatType) {
        DailyTrainCarriageExample example = new DailyTrainCarriageExample();
        example.createCriteria()
                .andDateEqualTo(date)
                .andTrainCodeEqualTo(trainCode)
                .andSeatTypeEqualTo(seatType);
        return dailyTrainCarriageMapper.selectByExample(example);
    }
}