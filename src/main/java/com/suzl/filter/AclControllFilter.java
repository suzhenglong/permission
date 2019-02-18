package com.suzl.filter;

import com.suzl.common.ApplicationContextHelper;
import com.suzl.common.JsonData;
import com.suzl.common.RequestHolder;
import com.suzl.model.SysUser;
import com.suzl.service.SysCoreService;
import com.suzl.util.JsonMapper;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.31 14:51
 */
@Slf4j
public class AclControllFilter implements Filter {

    private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();

    public static final String NOAUTHURL = "/sys/user/noAuth.page";

    /**
     * 获取过滤白名单
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String exclusionUrls = filterConfig.getInitParameter("exclusionUrls");
        List<String> exclusionUrlList = Splitter.on(",").trimResults().trimResults().omitEmptyStrings().splitToList(exclusionUrls);
        exclusionUrlSet = Sets.newConcurrentHashSet(exclusionUrlList);
        exclusionUrlSet.add(NOAUTHURL);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        Map parameterMap = request.getParameterMap();
        // 白名单处理
        if (exclusionUrlSet.contains(servletPath)) {
            filterChain.doFilter(request, response);
            return;
        }
        SysUser user = RequestHolder.getCurrentUser();

        if (user == null) {
            log.info("someone visit {} ,but no login ,parameter: {}", servletPath, JsonMapper.obj2String(parameterMap));
            noAuth(request, response);
            return;
        }
        SysCoreService sysCoreService = ApplicationContextHelper.popBean(SysCoreService.class);
        if (!sysCoreService.hasUrlAcl(servletPath)) {
            log.info("{} visit {} ,but no login ,parameter: {}", JsonMapper.obj2String(user), servletPath, JsonMapper.obj2String(parameterMap));
            noAuth(request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
        return;

    }

    private void noAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        if (servletPath.endsWith(".json")) {
            JsonData fail = JsonData.fail("没有访问权限,如需访问,请联系管理员");
            response.setHeader("Content-Type", "application/json");
            response.getWriter().print(JsonMapper.obj2String(fail));
        } else {
            clientRedirect(NOAUTHURL, response);
            return;
        }
    }

    private void clientRedirect(String url, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/html");
        response.getWriter().print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "<title>跳转中...</title>\n" + "</head>\n" + "<body>\n" + "跳转中，请稍候...\n" + "<script type=\"text/javascript\">//<![CDATA[\n"
                + "window.location.href='" + url + "?ret='+encodeURIComponent(window.location.href);\n" + "//]]></script>\n" + "</body>\n" + "</html>\n");
    }

    @Override
    public void destroy() {

    }
}
