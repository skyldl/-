package com.itxiaofeng.spzx.user.mapper;

import com.itxiaofeng.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {

    void save(UserInfo userInfo);

    UserInfo getByUsername(@Param("username") String username);

}
