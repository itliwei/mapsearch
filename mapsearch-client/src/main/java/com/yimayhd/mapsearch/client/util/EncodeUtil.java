package com.yimayhd.mapsearch.client.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by menhaihao on 15/6/5.
 *
 */
public class EncodeUtil {
    private final static Logger log = LoggerFactory.getLogger(EncodeUtil.class);

    public static String encode(String in) {
        if (in == null) {
            return null;
        }
        try {
            return URLEncoder.encode(in,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("encode exception,in:" + in,e);
        }
        return null;
    }

    public static String decode(String in) {
        if (in == null) {
            return null;
        }
        try {
            return URLDecoder.decode(in, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("decode exception,in:" + in, e);
        }
        return null;
    }

    public static String encodeForLikeQuery(String in) {
        if (in == null) {
            return null;
        }
        try {
            return StringUtils.replace(URLEncoder.encode(in, "UTF-8"),"%","\\%");
        } catch (UnsupportedEncodingException e) {
            log.error("encode exception,in:" + in,e);
        }
        return null;
    }
    public static void main(String[] args) {
        String a = " 今天走了{stepCount}步，将获得{sliverCount}健康点奖励（次日发放）";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<a.length();i++) {
            stringBuilder.append("\\u").append(
                    Integer.toHexString(a.charAt(i)));
        }
        System.out.println(stringBuilder.toString());
        System.out.println("\u4eca\u5929\u8d70\u4e86{stepCount}\u6b65\uff0c\u6d88\u8017\u4e86{calories}\u5361\u8def\u91cc\uff0c\u76f8\u5f53\u4e8e{fat}g\u8102\u80aa");
    }
}
