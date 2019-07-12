package cn.cy;

import cn.cy.model.SysUser;
import cn.cy.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootProjectApplicationTests {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private PasswordEncoder passwordEncoder;

	@Test
	public void add() {
		SysUser sysUser = new SysUser();
		sysUser.setAge(12);
		sysUser.setId(1);
		sysUser.setName("zhangsan");
		sysUser.setPsw("qwer");
		System.out.println(sysUserService.addSysUser(sysUser));
	}

	@Test
	public void addTwo() {
		SysUser sysUser1 = new SysUser();
		sysUser1.setAge(3);
		sysUser1.setId(3);
		sysUser1.setName("小米");
		sysUser1.setPsw("qwer");
		SysUser sysUser2 = new SysUser();
		sysUser2.setAge(4);
		sysUser2.setId(4);
		sysUser2.setName("大米");
		sysUser2.setPsw("qwer");
		System.out.println(sysUserService.addTwoSysUser(sysUser1, sysUser2));
	}

	@Test
	public void updatePsw() {
		System.out.println(passwordEncoder.encode("admin"));
	}
}
