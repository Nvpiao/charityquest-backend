package com.forever17.project.charityquest.aop;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.exceptions.UserNotLogInException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * check login status Aop
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 29 Feb 2020
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class LoginCheckAop {

    private final HttpSession httpSession;

    @Autowired
    public LoginCheckAop(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    /**
     * cut point aim at LoginCheck Annotation
     */
    @Pointcut(value = "@annotation(com.forever17.project.charityquest.aop.annotation.LoginCheck)")
    public void loginStatus() {
    }

    /**
     * get request header and check if user in session
     */
    @Before(value = "loginStatus()")
    public void beforeDoing() throws UserNotLogInException {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(
                        RequestContextHolder.getRequestAttributes())).getRequest();
        // from header to get token
        String requestToken = request.getHeader(CharityConstants.HEADER_REQUEST_TOKEN);
        // from session to get token
        String sessionToken = (String) httpSession.getAttribute(CharityConstants.HEADER_REQUEST_TOKEN);

        // check whether session expired or not
        if (Objects.isNull(sessionToken)) {
            log.error(CharityConstants.RETURN_USER_NOT_LOGIN);
            throw new UserNotLogInException(CharityConstants.RETURN_USER_NOT_LOGIN);
        }

        // check if it is a logged in user
        if (!sessionToken.equals(requestToken)) {
            log.error(CharityConstants.RETURN_USER_HAS_NO_PERMISSION);
            throw new UserNotLogInException(CharityConstants.RETURN_USER_HAS_NO_PERMISSION);
        }
    }
}
