package cn.cy.dao;

import cn.cy.model.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by 30721 on 2019/7/6.
 */
@Mapper
public interface SysUserDaoInter {

    @Insert("insert into boot_user(id, name, age, psw) values(#{id}, #{name}, #{age}, #{psw})")
    int addSysUser(SysUser sysUser);

    @Select("select * from boot_user where name=#{name}")
    SysUser getSysUser(String name);

    @Update("update boot_user set psw=#{psw} where name=name=#{name}")
    int updatePsw(SysUser sysUser);
}
