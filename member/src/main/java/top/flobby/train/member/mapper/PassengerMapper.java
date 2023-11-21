package top.flobby.train.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.flobby.train.member.domain.Passenger;
import top.flobby.train.member.domain.PassengerExample;

import java.util.List;

/**
 * @author Flobby
 */
@Mapper
public interface PassengerMapper {
    long countByExample(PassengerExample example);

    int deleteByExample(PassengerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Passenger row);

    int insertSelective(Passenger row);

    List<Passenger> selectByExample(PassengerExample example);

    Passenger selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Passenger row, @Param("example") PassengerExample example);

    int updateByExample(@Param("row") Passenger row, @Param("example") PassengerExample example);

    int updateByPrimaryKeySelective(Passenger row);

    int updateByPrimaryKey(Passenger row);
}