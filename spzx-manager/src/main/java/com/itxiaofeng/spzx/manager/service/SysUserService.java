package com.itxiaofeng.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.itxiaofeng.spzx.model.dto.system.AssginRoleDto;
import com.itxiaofeng.spzx.model.dto.system.LoginDto;
import com.itxiaofeng.spzx.model.dto.system.SysUserDto;
import com.itxiaofeng.spzx.model.entity.system.SysUser;
import com.itxiaofeng.spzx.model.vo.system.LoginVo;

public interface SysUserService {

    /**
     * 根据用户名查询用户数据
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto) ;

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);

    void doAssign(AssginRoleDto assginRoleDto);
}