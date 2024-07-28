package com.itxiaofeng.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itxiaofeng.spzx.common.exception.GuiguException;
import com.itxiaofeng.spzx.manager.mapper.SysRoleUserMapper;
import com.itxiaofeng.spzx.manager.mapper.SysUserMapper;
import com.itxiaofeng.spzx.manager.service.SysUserService;
import com.itxiaofeng.spzx.model.dto.system.AssginRoleDto;
import com.itxiaofeng.spzx.model.dto.system.LoginDto;
import com.itxiaofeng.spzx.model.dto.system.SysUserDto;
import com.itxiaofeng.spzx.model.entity.system.SysUser;
import com.itxiaofeng.spzx.model.vo.common.ResultCodeEnum;
import com.itxiaofeng.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper ;

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper ;
    @Override
    public LoginVo login(LoginDto loginDto) {

        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();

        // 从redis中获取验证码

        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode" + codeKey);
        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)){
            throw  new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        redisTemplate.delete("user:login:validatecode" + codeKey);


        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if(sysUser == null) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
//            throw new RuntimeException("用户名或者密码错误") ;
        }

        // 验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if(!md5InputPassword.equals(sysUser.getPassword())) {
//            throw new RuntimeException("用户名或者密码错误") ;
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login" + token , JSON.toJSONString(sysUser) , 30 , TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        // 二次优化，我们一开始在没有设置拦截器的时候，我们只能是到redis里面去查询用户信息，在设置了拦截器以后，当请求userinfo
        // 接口的时候，我们的拦截器已经进行了拦截，并且呢从redis里面进行查询并且把用户信息保存到了该线程里面，当线程执行到这里的时候
        // 我们已经对redis里面进行查询了一次，如果我们再从redis里面取也不是不可以，但是我们就相当于重复查询了两次redis，我们可以直接
        // 从线程变量里面去取用户信息返回。


        // 登录成功后，我们以token为键，用户信息为值保存了用户的信息到了redis里面，也就是我们根据token可以在redis里面把用户信息取出来。
        // 这样我们直接把用户信息返回去给用户即可
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        return JSON.parseObject(userJson, SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token) ;
    }

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto) ;
        PageInfo pageInfo = new PageInfo(sysUserList) ;
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        // 根据输入的用户名查询用户
        SysUser dbSysUser = sysUserMapper.findByUserName(sysUser.getUserName()) ;
        if(dbSysUser != null) {
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS) ;
        }

        // 对密码进行加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(0);
        sysUserMapper.saveSysUser(sysUser) ;
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser) ;
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId) ;
    }

    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        // 删除之前的所有的用户所对应的角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId()) ;

        // 分配新的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        roleIdList.forEach(roleId->{
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(),roleId);
        });
    }
}