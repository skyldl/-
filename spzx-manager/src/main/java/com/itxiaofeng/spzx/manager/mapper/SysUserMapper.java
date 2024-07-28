package com.itxiaofeng.spzx.manager.mapper;

import com.itxiaofeng.spzx.model.dto.system.SysUserDto;
import com.itxiaofeng.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {


    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    public abstract SysUser selectByUserName(String userName) ;

    List<SysUser> findByPage(SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    SysUser findByUserName(String userName);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
