package com.aripd.util.filter;

import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.view.LoginBean;
import com.aripd.util.helper.CookieHelper;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * To prevent user from going back to Login page if the user already logged in
 *
 * @author cem
 */
public class LoginPageFilter implements Filter {

    @Inject
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String navigateString = "/player/index.jsf";

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getUserPrincipal() != null) { //If user is already authenticated
            response.sendRedirect(request.getContextPath() + navigateString);
        } else {
            String uuid = CookieHelper.getCookieValue(request, LoginBean.COOKIE_NAME);

            if (uuid != null) {
                UserEntity user = userService.findOneByUuid(uuid);

                if (user != null) {
                    request.login(user.getUsername(), user.getPassword());
                    CookieHelper.addCookie(response, LoginBean.COOKIE_NAME, uuid, LoginBean.COOKIE_AGE); // Extends age.
                    response.sendRedirect(request.getContextPath() + navigateString);
                } else {
                    CookieHelper.removeCookie(response, LoginBean.COOKIE_NAME);
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
