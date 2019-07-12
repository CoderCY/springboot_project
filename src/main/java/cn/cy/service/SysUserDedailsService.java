package cn.cy.service;

import cn.cy.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 30721 on 2019/7/11.
 */
@Service
public class SysUserDedailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户是否存在，并授予角色
     * 这边不做密码校验，密码校验交给springboot。
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getSysUser(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //这里简单的做一下，权限赋予，业务需求需要根据实际情况。
        if (username.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            authorities.add(new SimpleGrantedAuthority("USER"));
        } else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        return new User(sysUser.getName(), sysUser.getPsw(), authorities);
    }
}
