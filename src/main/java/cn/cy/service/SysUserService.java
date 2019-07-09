package cn.cy.service;

import cn.cy.dao.SysUserDaoInter;
import cn.cy.model.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by 30721 on 2019/7/6.
 */
@Service
public class SysUserService {

    @Resource
    private SysUserDaoInter sysUserDaoInter;

    public boolean addSysUser(SysUser sysUser) {
        return sysUserDaoInter.addSysUser(sysUser)>0?true:false;
    }

    @Transactional(rollbackFor= Exception.class)
    public boolean addTwoSysUser(SysUser user1, SysUser user2) {
        boolean bool1 = sysUserDaoInter.addSysUser(user1)>0?true:false;
        if (true) {
            throw new RuntimeException("逻辑异常了！");
        }
        boolean bool2 = sysUserDaoInter.addSysUser(user2)>0?true:false;
        return bool1&&bool2;
    }
}
