package com.suzl.controller;

import com.suzl.common.ApplicationContextHelper;
import com.suzl.common.JsonData;
import com.suzl.dao.SysAclModuleMapper;
import com.suzl.dao.SysUserMapper;
import com.suzl.exception.ParamException;
import com.suzl.exception.PermissionException;
import com.suzl.model.SysAclModule;
import com.suzl.model.SysUser;
import com.suzl.param.TestVo;
import com.suzl.util.BeanValidator;
import com.suzl.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.15 16:59
 */

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        throw new PermissionException("test exception");
        // return JsonData.success("hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate() throws ParamException {
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        return JsonData.success(JsonMapper.obj2String(module), "test validate");
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SysUserMapper sysUserMapper = (SysUserMapper) ac.getBean("sysUserMapper");
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(1);
        System.out.println(JsonMapper.obj2String(sysUser));
    }
}
