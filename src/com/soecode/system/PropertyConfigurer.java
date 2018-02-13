package com.soecode.system;

import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *       
 * 类描述：     读取properties文件
 *
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) {
        logger.info("读取properties文件");
        super.processProperties(beanFactory, props);

        AppConstants.loadSaleURL(props);
        logger.info("加载完成");
    }
}
