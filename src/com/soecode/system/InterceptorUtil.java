package com.soecode.system;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * InterceptorUil工具类
 * Created by lwl on 2016/7/8.
 */
public class InterceptorUtil {

    private static  PathMatcher pathMatcher = new AntPathMatcher();


    public static boolean lookupUrl(String urlPath, List<String> excludes) {

        for (String registeredPattern : excludes) {
            if (pathMatcher.match(registeredPattern, urlPath)) {
                return true;
            }
        }
        return false;
    }
}
