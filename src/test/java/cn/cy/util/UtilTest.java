package cn.cy.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by 30721 on 2019/7/12.
 */
public class UtilTest {

    public static void main(String[] args) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ssZZ";
        System.out.println(DateFormatUtils.format(new Date(), pattern));
    }
}
