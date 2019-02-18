package com.suzl.common;

import com.suzl.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.17 10:43
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    public static final String START_TIMR = "requestStartTime";

    /**
     * 请求之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map map = request.getParameterMap();
        log.info("request start url:{},params:{}", url, JsonMapper.obj2String(map));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIMR, start);
        return true;
    }

    /**
     * 请求正常结束之后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // String url = request.getRequestURI().toString();
        // Map map = request.getParameterMap();
        // long start = (long) request.getAttribute(START_TIMR);
        // long end = System.currentTimeMillis();
        // log.info("request finished url:{},params:{},cost:{}", url, JsonMapper.obj2String(map), end - start);
        removeThreadLocalInfo();
    }

    /**
     * 请求结束之后 包括异常情况
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        long start = (long) request.getAttribute(START_TIMR);
        long end = System.currentTimeMillis();
        log.info("request finished url:{},cost:{}", url, end - start);
        removeThreadLocalInfo();
    }

    public void removeThreadLocalInfo() {
        com.suzl.common.RequestHolder.remove();
    }
}
