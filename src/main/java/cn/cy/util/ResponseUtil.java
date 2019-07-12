package cn.cy.util;

import cn.cy.model.ResultBean;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 30721 on 2019/7/10.
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

    public static void responseJson(HttpServletResponse response,int httpStatus, ResultBean result) {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(httpStatus);
            PrintWriter out=response.getWriter();
            out.println(JSONObject.valueToString(result));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void main(String[] args) {
        ResultBean result = new ResultBean<>();
        result.setState(1);
        result.setMsg("参数格式错误");
        try {
            System.out.println(JSONObject.valueToString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
