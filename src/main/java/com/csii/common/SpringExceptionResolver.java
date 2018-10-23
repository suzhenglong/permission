package com.csii.common;

import com.csii.exception.ParamException;
import com.csii.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.16 14:43
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        String url = request.getRequestURI().toString();
        ModelAndView view;
        String defaultMsg = "System Error";
        JsonData result;

        // 要求项目中的所有请求json数据,都使用.json
        if (url.endsWith(".json")) {
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                result = JsonData.fail(ex.getMessage());
                view = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("unknown json exception,url:" + url, ex);
                result = JsonData.fail(defaultMsg);
                view = new ModelAndView("jsonView", result.toMap());
            }
            //要求项目中的所有请求page页面,都使用.page
        } else if (url.endsWith(".page")) {
            log.error("unknown page exception,url:" + url, ex);
            result = JsonData.fail(defaultMsg);
            view = new ModelAndView("exception", result.toMap());
        } else {
            log.error("unknown exception,url:" + url, ex);
            result = JsonData.fail(defaultMsg);
            view = new ModelAndView("jsonView", result.toMap());
        }
        return view;
    }
}
