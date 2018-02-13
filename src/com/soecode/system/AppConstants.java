package com.soecode.system;

import java.util.Properties;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 获取properties常量类
 * 李文龙
 */
public final class AppConstants {


    private static Properties saleUrl;

    /**
     * 私有构造
     */
    private AppConstants() {
    }

    /**
     * 加载props路径
     */
    public static void loadSaleURL(Properties props) {
        saleUrl = props;
    }

    /**
     * <获取Property属性文本>
     * <功能详细描述>
     */
    public static String getProperty(String key) {
        return AppConstants.saleUrl.getProperty(key);
    }
    
    public static Number getNumberValue(final String key) {
        String value = saleUrl.getProperty(key);
        if (NumberUtils.isNumber(value)) {
            return NumberUtils.createNumber(value);
        }
        return null;
    }

    public static Number getNumberValue(final String key, final Number defaultValue) {
        String value = saleUrl.getProperty(key);
        if (NumberUtils.isNumber(value)) {
            return NumberUtils.createNumber(value);
        }
        return defaultValue;
    }
}
