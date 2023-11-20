package top.flobby.train.member.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.flobby.train.member.mapper.MemberMapper;

/**
 * @author : Flobby
 * @program : train
 * @description : service
 * @create : 2023-11-20 15:49
 **/

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int countMember(){
        return memberMapper.countMember();
    }
}
