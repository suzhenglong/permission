package com.csii.test;

import com.csii.util.PasswordUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.17 16:24
 */
public class PermissionTset {

    @Test
    public void testMap() {
        System.out.println("suzl");
    }

    @Test
    public void dateTime() {
        /* System.out.println(new Date().getTime());*/
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testRandomPassword() throws Exception {
        System.out.println(PasswordUtil.randomPassword());
        Thread.sleep(100);
        System.out.println(PasswordUtil.randomPassword());
        Thread.sleep(100);
        System.out.println(PasswordUtil.randomPassword());
        Thread.sleep(100);
        System.out.println(PasswordUtil.randomPassword());
    }

}
