package com.itxiaofeng.spzx.user.service;

import com.itxiaofeng.spzx.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    UserAddress getById(Long id);
}
