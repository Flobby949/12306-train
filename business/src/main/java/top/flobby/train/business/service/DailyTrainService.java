package top.flobby.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.flobby.train.business.domain.DailyTrain;
import top.flobby.train.business.domain.DailyTrainExample;
import top.flobby.train.business.domain.Train;
import top.flobby.train.business.mapper.DailyTrainMapper;
import top.flobby.train.business.req.DailyTrainQueryReq;
import top.flobby.train.business.req.DailyTrainSaveReq;
import top.flobby.train.business.resp.DailyTrainQueryResp;
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
public class DailyTrainService {

    private static final Logger LOG = LoggerFactory.getLogger(DailyTrainService.class);

    @Resource
    private DailyTrainMapper dailyTrainMapper;
    @Resource
    private TrainService trainService;
    @Resource
    private DailyTrainStationService dailyTrainStationService;
    @Resource
    private DailyTrainCarriageService dailyTrainCarriageService;
    @Resource
    private DailyTrainSeatService dailyTrainSeatService;
    @Resource
    private DailyTrainTicketService dailyTrainTicketService;
    @Resource
    private SkTokenService skTokenService;

    public void save(DailyTrainSaveReq req) {
        DateTime now = DateTime.now();
        DailyTrain dailyTrain = BeanUtil.copyProperties(req, DailyTrain.class);
        if (ObjectUtil.isNull(dailyTrain.getId())) {
            dailyTrain.setId(SnowUtil.getSnowflakeNextId());
            dailyTrain.setCreateTime(now);
            dailyTrain.setUpdateTime(now);
            dailyTrainMapper.insert(dailyTrain);
        } else {
            dailyTrain.setUpdateTime(now);
            dailyTrainMapper.updateByPrimaryKey(dailyTrain);
        }
    }

    public PageResp<DailyTrainQueryResp> queryList(DailyTrainQueryReq req) {
        DailyTrainExample dailyTrainExample = new DailyTrainExample();
        dailyTrainExample.setOrderByClause("date desc, code asc");
        DailyTrainExample.Criteria criteria = dailyTrainExample.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getCode())) {
            criteria.andCodeEqualTo(req.getCode());
        }
        if (ObjectUtil.isNotEmpty(req.getDate())) {
            criteria.andDateEqualTo(req.getDate());
        }
        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<DailyTrain> dailyTrainList = dailyTrainMapper.selectByExample(dailyTrainExample);

        PageInfo<DailyTrain> pageInfo = new PageInfo<>(dailyTrainList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<DailyTrainQueryResp> list = BeanUtil.copyToList(dailyTrainList, DailyTrainQueryResp.class);

        PageResp<DailyTrainQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        dailyTrainMapper.deleteByPrimaryKey(id);
    }


    public void genDaily(Date date) {
        List<Train> trainList = trainService.selectAll();
        if (CollUtil.isEmpty(trainList)) {
            LOG.error("没有车次信息");
            throw new BusinessException(BusinessExceptionEnum.TRAIN_CODE_NOT_EXIST);
        }
        for (Train train : trainList) {
            genTrainDaily(train, date);
        }
    }

    public void genTrainDaily(Train train, Date date) {
        // 删除当天的车次信息
        DailyTrainExample dailyTrainExample = new DailyTrainExample();
        dailyTrainExample.createCriteria()
                .andCodeEqualTo(train.getCode())
                .andDateEqualTo(date);
        dailyTrainMapper.deleteByExample(dailyTrainExample);
        // 生成当天的车次信息
        DateTime now = DateTime.now();
        DailyTrain dailyTrain = BeanUtil.copyProperties(train, DailyTrain.class);
        dailyTrain.setId(SnowUtil.getSnowflakeNextId());
        dailyTrain.setDate(date);
        dailyTrain.setCreateTime(now);
        dailyTrain.setUpdateTime(now);
        dailyTrainMapper.insert(dailyTrain);

        // 生成当天的车站信息
        dailyTrainStationService.genDaily(date, train.getCode());
        // 生成当天的车厢信息
        dailyTrainCarriageService.genDailyCarriage(train.getCode(), date);
        // 生成当天的座位信息
        dailyTrainSeatService.genDailySeat(date, train.getCode());
        // 生成余票信息
        dailyTrainTicketService.genDailyTicket(date, train.getCode(), dailyTrain);
        // 生成当天的令牌信息
        skTokenService.genDaily(date, train.getCode());
        LOG.info("车次{}生成当天信息结束", train.getCode());
    }
}