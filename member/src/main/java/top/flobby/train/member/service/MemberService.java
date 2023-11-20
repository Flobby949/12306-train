package top.flobby.train.member.service;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.flobby.train.member.domain.Member;
import top.flobby.train.member.domain.MemberExample;
import top.flobby.train.member.mapper.MemberMapper;

import java.util.List;

/**
 * @author : Flobby
 * @program : train
 * @description : service
 * @create : 2023-11-20 15:59
 **/

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            throw new RuntimeException("手机号已被注册");
        }

        Member member = new Member();
        member.setMobile(mobile);
        member.setId(System.currentTimeMillis());
        memberMapper.insert(member);
        return member.getId();
    }
}
