package cn.cy.controller;

import cn.cy.model.SysUser;
import cn.cy.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by 30721 on 2019/7/7.
 */
@RestController
@RequestMapping("/sysUsers")
@Api(value = "用户接口")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * ApiOperation和ApiImplicitParam 并不是Spring的注释，而是Swagger2中的
     * @ApiOperation(value = "接口说明", httpMethod = "请求方式", response = "接口返回参数类型", notes = "接口发布说明")
     * @ApiImplicitParam(value = "备注输入参数名称（中文）", name = "备注输入参数名称（英文）", required = 该入参是否必填(Boolean), dataType = "该入参的数据类型", paramType = "前台接口调用时url 参数形式")
     * @param user
     * @return
     */
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user")
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String postUser(@RequestBody SysUser user) {
        if (sysUserService.addSysUser(user)) {
            return "成功";
        }
        return "失败";
    }
}
