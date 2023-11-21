package top.flobby.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.flobby.train.common.context.LoginMemberContext;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.common.utils.SnowUtil;
import top.flobby.train.member.domain.Passenger;
import top.flobby.train.member.domain.PassengerExample;
import top.flobby.train.member.mapper.PassengerMapper;
import top.flobby.train.member.req.PassengerQueryReq;
import top.flobby.train.member.req.PassengerSaveReq;
import top.flobby.train.member.resp.PassengerQueryResp;

import java.util.List;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-21 14:16
 **/

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req){
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setMemberId(LoginMemberContext.getMemberId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    public PageResp<PassengerQueryResp> query(PassengerQueryReq req) {
        PassengerExample example = new PassengerExample();
        PassengerExample.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Passenger> passengers = passengerMapper.selectByExample(example);
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengers);
        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(BeanUtil.copyToList(passengers, PassengerQueryResp.class));
        return pageResp;
    }
}
