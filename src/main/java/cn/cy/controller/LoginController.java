package cn.cy.controller;

import cn.cy.model.ResultBean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 30721 on 2019/7/11.
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public ResultBean login() {
        ResultBean resultBean = new ResultBean();
        resultBean.setState(0);
        resultBean.setMsg("This is the login page.");
        return resultBean;
    }

    @RequestMapping("/login-error")
    public ResultBean loginError(Model model) {
        ResultBean resultBean = new ResultBean();
        resultBean.setState(1);
        resultBean.setMsg("request failure.");
        return resultBean;
    }

}
