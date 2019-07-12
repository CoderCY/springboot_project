package cn.cy.core.filter;

import cn.cy.model.ResultBean;
import cn.cy.util.JWTUtil;
import cn.cy.util.ResponseUtil;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * Created by 30721 on 2019/7/10.
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 解析用户并判断其合法性。
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
//            自定义的页面--获取user信息
//            SysUser user = GsonUtil.getInstance().fromJson(request.getReader(),SysUser.class);
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            user.getName(),
//                            user.getPsw(),
//                            new ArrayList<>()));
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getParameter("username"),
                            request.getParameter("password"),
                            new ArrayList<>()));
        } catch (JsonSyntaxException | JsonIOException |  NullPointerException e) {
            e.printStackTrace();
            ResultBean result = new ResultBean<>();
            result.setState(1);
            result.setMsg("参数格式错误");
            ResponseUtil.responseJson(response, HttpStatus.BAD_REQUEST.value(), result);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("name",userDetails.getUsername());
        List<String> list = new ArrayList<>();
        for (GrantedAuthority grantedAuthority: userDetails.getAuthorities()) {
            list.add(grantedAuthority.getAuthority());
        }
        map.put("auth", list);
        //生成token
        String tokenStr = JWTUtil.createJWT(map,0L);
        //header中添加 token
        response.addHeader("Authorization", "Bearer " + tokenStr);
        ResultBean<String> result = new ResultBean<>();
        result.setState(0);
        result.setMsg("认证成功");
        result.setResultBean(tokenStr);
        ResponseUtil.responseJson(response, HttpStatus.OK.value(), result);
    }

    /**
     * y
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String msg;
        if (failed instanceof BadCredentialsException) {
            msg = "密码错误";
        } else {
            msg = failed.getMessage();
        }
        ResultBean result = new ResultBean<>();
        result.setState(1);
        result.setMsg(msg);
        ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), result);
    }
}
