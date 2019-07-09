package cn.cy.dao;

import cn.cy.model.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 30721 on 2019/7/6.
 */
@Mapper
public interface SysUserDaoInter {

    @Insert("insert into boot_user(id, name, age, psw) values(#{id}, #{name}, #{age}, #{psw})")
    int addSysUser(SysUser sysUser);
}
