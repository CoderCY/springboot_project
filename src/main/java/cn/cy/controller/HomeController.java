package cn.cy.controller;

import cn.cy.model.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 30721 on 2019/7/11.
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    public ResultBean home() {
        ResultBean resultBean = new ResultBean();
        resultBean.setState(0);
        resultBean.setMsg("This is the home page.");
        return resultBean;
    }
}
