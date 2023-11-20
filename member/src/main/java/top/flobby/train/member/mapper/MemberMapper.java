package top.flobby.train.member.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-20 15:43
 **/

@Mapper
public interface MemberMapper {

    int countMember();
}
