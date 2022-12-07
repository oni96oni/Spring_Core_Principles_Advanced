package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        // newProxyInstance로 프록시를 만들어준다.(동적 생성), handler는 프록시가 사용할 로직을 넣어주는것

        proxy.call(); // Object에서 AInterface로 캐스팅해준 이유 바로 call() 사용을 위해서
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        // newProxyInstance로 프록시를 만들어준다.(동적 생성), handler는 프록시가 사용할 로직을 넣어주는것

        proxy.call(); // Object에서 AInterface로 캐스팅해준 이유 바로 call() 사용을 위해서
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

    }
}
