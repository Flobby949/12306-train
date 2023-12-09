package top.flobby.train.business.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.flobby.train.business.domain.AppManage;
import top.flobby.train.business.domain.AppManageExample;

public interface AppManageMapper {
    long countByExample(AppManageExample example);

    int deleteByExample(AppManageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppManage row);

    int insertSelective(AppManage row);

    List<AppManage> selectByExample(AppManageExample example);

    AppManage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") AppManage row, @Param("example") AppManageExample example);

    int updateByExample(@Param("row") AppManage row, @Param("example") AppManageExample example);

    int updateByPrimaryKeySelective(AppManage row);

    int updateByPrimaryKey(AppManage row);
}