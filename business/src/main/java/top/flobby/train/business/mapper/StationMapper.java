package top.flobby.train.business.mapper;

import org.apache.ibatis.annotations.Param;
import top.flobby.train.business.domain.Station;
import top.flobby.train.business.domain.StationExample;

import java.util.List;

public interface StationMapper {
    long countByExample(StationExample example);

    int deleteByExample(StationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Station row);

    int insertSelective(Station row);

    List<Station> selectByExample(StationExample example);

    Station selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Station row, @Param("example") StationExample example);

    int updateByExample(@Param("row") Station row, @Param("example") StationExample example);

    int updateByPrimaryKeySelective(Station row);

    int updateByPrimaryKey(Station row);
}