package top.flobby.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.flobby.train.common.utils.SnowUtil;
import top.flobby.train.member.domain.Passenger;
import top.flobby.train.member.mapper.PassengerMapper;
import top.flobby.train.member.req.PassengerSaveReq;

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
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
