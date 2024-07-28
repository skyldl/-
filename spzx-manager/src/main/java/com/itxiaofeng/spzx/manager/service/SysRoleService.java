package com.itxiaofeng.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.itxiaofeng.spzx.model.dto.system.SysRoleDto;
import com.itxiaofeng.spzx.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> findAllRoles(Long userId);
}
