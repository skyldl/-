package com.itxiaofeng.spzx.user.service;

import com.itxiaofeng.spzx.model.dto.h5.UserLoginDto;
import com.itxiaofeng.spzx.model.dto.h5.UserRegisterDto;
import com.itxiaofeng.spzx.model.vo.h5.UserInfoVo;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    Object login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
