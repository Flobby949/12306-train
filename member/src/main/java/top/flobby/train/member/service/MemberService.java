package top.flobby.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.flobby.train.common.exception.BusinessException;
import top.flobby.train.common.exception.BusinessExceptionEnum;
import top.flobby.train.common.utils.JwtUtil;
import top.flobby.train.common.utils.SnowUtil;
import top.flobby.train.member.domain.Member;
import top.flobby.train.member.domain.MemberExample;
import top.flobby.train.member.mapper.MemberMapper;
import top.flobby.train.member.req.MemberLoginReq;
import top.flobby.train.member.req.MemberRegisterReq;
import top.flobby.train.member.req.MemberSendCodeReq;
import top.flobby.train.common.resp.MemberLoginResp;

import java.util.List;

/**
 * @author : Flobby
 * @program : train
 * @description : service
 * @create : 2023-11-20 15:59
 **/

@Slf4j
@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    /**
     * 注册
     *
     * @param req req
     * @return long
     */
    public long register(MemberRegisterReq req) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(req.getMobile());
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_PHONE_EXIST);
        }

        Member member = new Member();
        member.setMobile(req.getMobile());
        member.setId(SnowUtil.getSnowflakeNextId());
        memberMapper.insert(member);
        return member.getId();
    }

    /**
     * 发送验证码
     *
     * @param req req
     */
    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        // 如果手机号不存在，插入一条记录
        if (CollUtil.isEmpty(list)) {
            log.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setMobile(mobile);
            member.setId(SnowUtil.getSnowflakeNextId());
            memberMapper.insert(member);
        } else {
            log.info("手机号存在，不插入记录");
        }

        // 生成验证码，发送短信
        String code = "8888";
        log.info("生成短信验证码：{}", code);
        log.info("保存短信记录表");
        log.info("对接短信发送接口");
    }

    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);
        // 如果手机号不存，加入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_PHONE_NOT_EXIST);
        }
        // 校验验证码
        if (!ObjectUtil.equal(code, "8888")) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_CODE_ERROR);
        }
        // 生成token
        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
