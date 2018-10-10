package com.ch.mapper;

import com.ch.pojo.IpProxy;
import com.ch.pojo.IpProxyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IpProxyMapper {
    int countByExample(IpProxyExample example);

    int deleteByExample(IpProxyExample example);

    int deleteByPrimaryKey(String ip);

    int insert(IpProxy record);

    int insertSelective(IpProxy record);

    List<IpProxy> selectByExample(IpProxyExample example);

    IpProxy selectByPrimaryKey(String ip);

    int updateByExampleSelective(@Param("record") IpProxy record, @Param("example") IpProxyExample example);

    int updateByExample(@Param("record") IpProxy record, @Param("example") IpProxyExample example);

    int updateByPrimaryKeySelective(IpProxy record);

    int updateByPrimaryKey(IpProxy record);
}