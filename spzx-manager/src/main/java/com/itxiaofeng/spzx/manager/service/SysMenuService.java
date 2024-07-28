package com.itxiaofeng.spzx.manager.service;

import com.itxiaofeng.spzx.model.entity.system.SysMenu;
import com.itxiaofeng.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);

    List<SysMenuVo> findUserMenuList();
}
