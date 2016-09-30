package com.yimayhd.mapsearch.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by menhaihao on 15/5/26.
 *
 */
public class NetworkUtil {
    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("unable to getLocalIp",e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getLocalIp());
    }
}
