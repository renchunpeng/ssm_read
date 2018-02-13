# ssm_read

## 2018/2/6 16：12 
## 数据库连接池修改为druid
## 所需jar包：druid-1.1.2.jar
### 1.spring整合
	<!-- 2.数据库连接池 -->
	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
	    init-method="init" destroy-method="close">
	    <!-- 数据库基本信息配置 -->
	    <property name="url" value="${druid.url}" />
	    <property name="username" value="${druid.username}" />
	    <property name="password" value="${druid.password}" />
	    <property name = "driverClassName" value = "${druid.driverClassName}" />  
	
	    <!-- 初始化连接数量 -->
	    <property name="initialSize" value="${druid.initialSize}" />
	    <!-- 最小空闲连接数 -->
	    <property name="minIdle" value="${druid.minIdle}" />
	    <!-- 最大并发连接数 -->
	    <property name="maxActive" value="${druid.maxActive}" />
	    <!-- 配置获取连接等待超时的时间 -->
	    <property name="maxWait" value="${druid.maxWait}" />
	
	    <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
	    <property name="timeBetweenEvictionRunsMillis" value="${druid.timeBetweenEvictionRunsMillis}" />
	
	    <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
	    <property name="minEvictableIdleTimeMillis" value="${druid.minEvictableIdleTimeMillis}" />
	    <property name="validationQuery" value="${druid.validationQuery}" />
	    <property name="testWhileIdle" value="${druid.testWhileIdle}" />
	    <property name="testOnBorrow" value="${druid.testOnBorrow}" />
	    <property name="testOnReturn" value="${druid.testOnReturn}" />
	
	    <!-- 打开PSCache，并且指定每个连接上PSCache的大小 如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。 -->
	    <property name="poolPreparedStatements" value="${druid.poolPreparedStatements}" />
	    <property name="maxPoolPreparedStatementPerConnectionSize"
	        value="${druid.maxPoolPreparedStatementPerConnectionSize}" />
	
	    <!-- 配置监控统计拦截的filters -->
	    <property name="filters" value="${druid.filters}" />
	</bean> 

### 2.web.xml增加
	<!-- druid过滤器 -->
	<filter>
	    <filter-name>DruidWebStatFilter</filter-name>
	    <filter-class>com.alibaba.druid.support.http.WebStatFilter</filter-class>
	    <init-param>
	        <param-name>exclusions</param-name>
	        <param-value>/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*</param-value>
	    </init-param>
	</filter>
	<filter-mapping>
	    <filter-name>DruidWebStatFilter</filter-name>
	    <url-pattern>/*</url-pattern>
	</filter-mapping>
	
	<!-- druid配置文件 -->
	<servlet>
	    <servlet-name>DruidStatView</servlet-name>
	    <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
	</servlet>
	<servlet-mapping>
	    <servlet-name>DruidStatView</servlet-name>
	    <url-pattern>/druid/*</url-pattern>
	</servlet-mapping> 
	
## 2018/2/12 10：19
## Spring另一种加载方式
### 1.web.xml
	<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
	                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
		version="3.1" metadata-complete="true">
		<!-- 如果是用mvn命令生成的xml，需要修改servlet版本为3.1 -->
		<!-- 配置DispatcherServlet -->
		<servlet>
			<servlet-name>mvc-dispatcher</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<!-- 配置springMVC需要加载的配置文件
				spring-dao.xml,spring-service.xml,spring-web.xml
				Mybatis - > spring -> springmvc
			 -->
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>classpath:spring/spring-web.xml</param-value>
			</init-param>
		</servlet>
		<servlet-mapping>
			<servlet-name>mvc-dispatcher</servlet-name>
			<!-- 默认匹配所有的请求 -->
			<url-pattern>/</url-pattern>
		</servlet-mapping>
		
		
		<!-- 上下文配置文件位置 -->
		<context-param>
		   <param-name>contextConfigLocation</param-name>
		   <!-- spring 配置文件所在位置，启动 spring 时会去该路径下查找该配置文件 -->
		    <param-value>classpath*:spring/spring-dao.xml</param-value>
		</context-param>
		<!-- spring 上下文监听器，初始化启动容器时启动 spring -->
		<listener>
		   <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		</listener>
		
	</web-app>
	
### 2.spring-dao.xml修改如下，spring-service.xml不要了，spring-web.xml保持不变
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:tx="http://www.springframework.org/schema/tx"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/tx
		http://www.springframework.org/schema/tx/spring-tx.xsd">
		
		<!-- 扫描service包下所有使用注解的类型 -->
		<context:component-scan base-package="com.soecode.lyf.service" />
		
		<!-- 配置整合mybatis过程 -->
		<!-- 1.配置数据库相关参数properties的属性：${url} -->
		<context:property-placeholder location="classpath:jdbc.properties" />
	
		<!-- 2.数据库连接池 -->
		<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
			<!-- 配置连接池属性 -->
			<property name="driverClass" value="${jdbc.driver}" />
			<property name="jdbcUrl" value="${jdbc.url}" />
			<property name="user" value="${jdbc.username}" />
			<property name="password" value="${jdbc.password}" />
	
			<!-- c3p0连接池的私有属性 -->
			<property name="maxPoolSize" value="30" />
			<property name="minPoolSize" value="10" />
			<!-- 关闭连接后不自动commit -->
			<property name="autoCommitOnClose" value="false" />
			<!-- 获取连接超时时间 -->
			<property name="checkoutTimeout" value="10000" />
			<!-- 当获取连接失败重试次数 -->
			<property name="acquireRetryAttempts" value="2" />
		</bean>
	
		<!-- 3.配置SqlSessionFactory对象 -->
		<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
			<!-- 注入数据库连接池 -->
			<property name="dataSource" ref="dataSource" />
			<!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
			<property name="configLocation" value="classpath:mybatis-config.xml" />
			<!-- 扫描entity包 使用别名 -->
			<property name="typeAliasesPackage" value="com.soecode.lyf.entity" />
			<!-- 扫描sql配置文件:mapper需要的xml文件 -->
			<property name="mapperLocations" value="classpath:mapper/*.xml" />
		</bean>
	
		<!-- 4.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中 -->
		<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
			<!-- 注入sqlSessionFactory -->
			<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
			<!-- 给出需要扫描Dao接口包 -->
			<property name="basePackage" value="com.soecode.lyf.dao" />
		</bean>
		
		<!-- 配置事务管理器 -->
		<bean id="transactionManager"
			class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
			<!-- 注入数据库连接池 -->
			<property name="dataSource" ref="dataSource" />
		</bean>
	
		<!-- 配置基于注解的声明式事务 -->
		<tx:annotation-driven transaction-manager="transactionManager" />
	</beans>
