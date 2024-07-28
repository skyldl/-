package com.itxiaofeng.spzx.manager.controller;

import com.itxiaofeng.spzx.manager.service.SysMenuService;
import com.itxiaofeng.spzx.manager.service.SysUserService;
import com.itxiaofeng.spzx.manager.service.ValidateCodeService;
import com.itxiaofeng.spzx.model.dto.system.LoginDto;
import com.itxiaofeng.spzx.model.entity.system.SysUser;
import com.itxiaofeng.spzx.model.vo.common.Result;
import com.itxiaofeng.spzx.model.vo.common.ResultCodeEnum;
import com.itxiaofeng.spzx.model.vo.system.LoginVo;
import com.itxiaofeng.spzx.model.vo.system.SysMenuVo;
import com.itxiaofeng.spzx.model.vo.system.ValidateCodeVo;
import com.itxiaofeng.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "系统管理")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private SysMenuService sysMenuService;
    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.build(loginVo , ResultCodeEnum.SUCCESS) ;
    }
    @Operation(summary = "获取用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo() {
//        SysUser sysUser = sysUserService.getUserInfo(token) ;
        return Result.build(AuthContextUtil.get(),ResultCodeEnum.SUCCESS);
//        return Result.build(sysUser , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "获取验证码")
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "退出登录")
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        sysUserService.logout(token) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList =  sysMenuService.findUserMenuList() ;
        return Result.build(sysMenuVoList , ResultCodeEnum.SUCCESS) ;
    }
}