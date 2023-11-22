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
import top.flobby.train.business.domain.TrainCarriage;
import top.flobby.train.business.domain.TrainCarriageExample;
import top.flobby.train.business.enums.SeatColEnum;
import top.flobby.train.business.mapper.TrainCarriageMapper;
import top.flobby.train.business.req.TrainCarriageQueryReq;
import top.flobby.train.business.req.TrainCarriageSaveReq;
import top.flobby.train.business.resp.TrainCarriageQueryResp;
import top.flobby.train.common.exception.BusinessException;
import top.flobby.train.common.exception.BusinessExceptionEnum;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.common.utils.SnowUtil;

import java.util.List;

/**
 * @author Flobby
 */

@Service
public class TrainCarriageService {

    private static final Logger LOG = LoggerFactory.getLogger(TrainCarriageService.class);

    @Resource
    private TrainCarriageMapper trainCarriageMapper;

    public void save(TrainCarriageSaveReq req) {
        DateTime now = DateTime.now();
        TrainCarriage trainCarriage = BeanUtil.copyProperties(req, TrainCarriage.class);
        // 自动计算列数和总座位数
        int colCount = SeatColEnum.getColsByType(req.getSeatType()).size();
        trainCarriage.setColCount(colCount);
        trainCarriage.setSeatCount(colCount * req.getRowCount());
        if (ObjectUtil.isNull(trainCarriage.getId())) {
            // 厢号唯一性校验
            if (selectIndexByUnique(trainCarriage.getTrainCode(), trainCarriage.getIndex())) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR);
            }
            trainCarriage.setId(SnowUtil.getSnowflakeNextId());
            trainCarriage.setCreateTime(now);
            trainCarriage.setUpdateTime(now);
            trainCarriageMapper.insert(trainCarriage);
        } else {
            trainCarriage.setUpdateTime(now);
            trainCarriageMapper.updateByPrimaryKey(trainCarriage);
        }
    }

    public PageResp<TrainCarriageQueryResp> queryList(TrainCarriageQueryReq req) {
        TrainCarriageExample trainCarriageExample = new TrainCarriageExample();
        trainCarriageExample.setOrderByClause("train_code asc, `index` asc");
        TrainCarriageExample.Criteria criteria = trainCarriageExample.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
            criteria.andTrainCodeEqualTo(req.getTrainCode());
        }
        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<TrainCarriage> trainCarriageList = trainCarriageMapper.selectByExample(trainCarriageExample);

        PageInfo<TrainCarriage> pageInfo = new PageInfo<>(trainCarriageList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<TrainCarriageQueryResp> list = BeanUtil.copyToList(trainCarriageList, TrainCarriageQueryResp.class);

        PageResp<TrainCarriageQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        trainCarriageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 按车次查询
     *
     * @param trainCode 列车代码
     * @return {@link List}<{@link TrainCarriage}>
     */
    public List<TrainCarriage> selectByTrainCode(String trainCode) {
        TrainCarriageExample trainCarriageExample = new TrainCarriageExample();
        trainCarriageExample.setOrderByClause("`index` asc");
        TrainCarriageExample.Criteria criteria = trainCarriageExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode);
        return trainCarriageMapper.selectByExample(trainCarriageExample);
    }

    /**
     * 唯一性校验
     *
     * @param trainCode 列车代码
     * @param index     车厢号
     * @return boolean
     */
    private boolean selectIndexByUnique(String trainCode, Integer index) {
        TrainCarriageExample trainCarriageExample = new TrainCarriageExample();
        TrainCarriageExample.Criteria criteria = trainCarriageExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode).andIndexEqualTo(index);
        List<TrainCarriage> trainCarriageList = trainCarriageMapper.selectByExample(trainCarriageExample);
        return CollUtil.isNotEmpty(trainCarriageList);
    }
}