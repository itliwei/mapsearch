package com.yimayhd.mapsearch.filter;

import net.pocrd.define.CommonParameter;
import net.pocrd.entity.CallerInfo;
import net.pocrd.util.AESTokenHelper;
import net.pocrd.util.AesHelper;
import net.pocrd.util.Base64Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.pajk.snscenter.entity.PromotionConfig;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by jianming on 2014/6/13.
 * Token验证与SessionFilter
 */
public class SessionFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
    private static ThreadLocal<AESTokenHelper> apiTokenHelper = new ThreadLocal<AESTokenHelper>();
    private static ThreadLocal<AesHelper> apiAesHelper = new ThreadLocal<AesHelper>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	CallerInfo callerInfo = null;
        HttpServletRequest req = (HttpServletRequest) request;
        String tk = req.getParameter(CommonParameter.token);
        boolean isWeb = false;
        //logger.info("session filter doFilter tk={}",tk);
        if (tk == null) {//从parameter取TK
            Cookie[] cookies = req.getCookies();
            if (cookies != null && cookies.length > 0) {  
                for (Cookie ck : cookies) {//Cookie中取。
                    if (CommonParameter.token.equals(ck.getName())) {
                        tk = ck.getValue();
                        isWeb = true;
                        break;
                    }
                }
            }
            if (tk == null) {//从Header 中的Referer中取。
                tk = req.getHeader("Referer");
            }
        }
        if (tk != null) {//解析TK
            if (isWeb) {//医生端
                try {
					callerInfo = parseUserCaller(URLDecoder.decode(tk, "UTF-8"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {//手机端。
                callerInfo = parseUserCaller(tk);
            }
        }
        if ((callerInfo != null && callerInfo.uid > 0)) {//判断
            request.setAttribute("caller", callerInfo);
            chain.doFilter(request, response);
            return;
        }

        response.getWriter().write("{\"code\":");
    }
    
    @Override
    public void destroy() {
        apiTokenHelper.remove();
        apiAesHelper.remove();
    }

    /**
     * parse caller info from token
     *
     * @param token user token
     * @return caller info
     */
    private CallerInfo parseUserCaller(String token) {
        CallerInfo caller = null;
        if (token != null && token.length() > 0) {
            caller = getApiTokenHelper().parseUserToken(token);
        }
        return caller;
    }

    /**
     * parse caller info from token
     *
     * @param token user token
     * @return caller info
     */
    private CallerInfo parseDeviceCaller(String token) {
        CallerInfo caller = null;
        if (token != null && token.length() > 0) {
            caller = getApiTokenHelper().parseDeviceToken(token);
        }
        return caller;
    }

    /**
     * tokenhelper is not thread safe
     *
     * @return aestoken helper
     */
    public AESTokenHelper getApiTokenHelper() {
        AESTokenHelper helper = apiTokenHelper.get();
        if (helper == null) {
            helper = new AESTokenHelper(getApiAesHelper());
            apiTokenHelper.set(helper);
        }
        return helper;
    }

    /**
     * aeshelper is not thread safe
     *
     * @return aeshelper
     */
    public AesHelper getApiAesHelper() {
        AesHelper helper = apiAesHelper.get();
        if (helper == null) {
            helper = new AesHelper(Base64Util.decode(/*PromotionConfig.getConfig().getToken()*/""), null);
            apiAesHelper.set(helper);
        }
        return helper;
    }
}
