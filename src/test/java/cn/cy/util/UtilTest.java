package cn.cy.util;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by 30721 on 2019/7/12.
 */
public class UtilTest {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("op","my name");
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("user");
        map.put("auth", list);
        String token = JWTUtil.createJWT(map,0L);
        System.out.println(token);
        List<String> p = (List)JWTUtil.verifyJwt(token).get("auth");
        System.out.println(p);

    }
}
