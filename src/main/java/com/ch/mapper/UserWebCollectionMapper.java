package com.ch.mapper;

import com.ch.pojo.UserWebCollection;
import com.ch.pojo.UserWebCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWebCollectionMapper {
    int countByExample(UserWebCollectionExample example);

    int deleteByExample(UserWebCollectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserWebCollection record);

    int insertSelective(UserWebCollection record);

    List<UserWebCollection> selectByExample(UserWebCollectionExample example);

    UserWebCollection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserWebCollection record, @Param("example") UserWebCollectionExample example);

    int updateByExample(@Param("record") UserWebCollection record, @Param("example") UserWebCollectionExample example);

    int updateByPrimaryKeySelective(UserWebCollection record);

    int updateByPrimaryKey(UserWebCollection record);
}