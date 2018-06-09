package com.soecode.system.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class ErrAspect {
    // 切入点表达式按需配置
    @Pointcut("execution(* com.soecode.lyf.web.*.*(..))")
    private void myPointcut() {
    }

    @Before("myPointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("[Aspect1] before advise");
        System.out.println("@Before：模拟权限检查...");
        System.out.println("@Before：目标方法为：" +
                joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + joinPoint.getTarget());
    }

/*    @Around("myPointcut()")
    public void around(ProceedingJoinPoint pjp) throws  Throwable{
        System.out.println("[Aspect1] around advise 1");
        pjp.proceed();
        System.out.println("[Aspect1] around advise2");
    }*/

    @AfterReturning("myPointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterReturning advise");
    }

    //这个总是捕捉不到错误
    @AfterThrowing(value = "myPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterThrowing advise");
    }

    @After(value = "myPointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[Aspect1] after advise");
    }

}