package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직 1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드만 다르고 나머지는 같은 로직
        log.info("result1: {}", result1);
        // 공통 로직 1 종료

        // 공통 로직 2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result2: {}", result2);
        // 공통 로직 2 종료

    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보 ( 이런식으로 메타정보를 얻을 수 있다.)
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");// 내부클래스에 있을때는 달러표시를 사용해서 구분한다.

        Hello target = new Hello();
        // callA의 메서드 정보를 획득 ()
        Method methodCallA = classHello.getMethod("callA"); // String result1 = target.callA(); 이 부분을 이렇게 바꿧다고 생각해보자!
        Object result1 = methodCallA.invoke(target); // callA라는 메서드를 호출 target의 인스턴스에있는!
        log.info("result1: {}", result1);

        // callB의 메서드 정보를 획득 ()
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target); // callB라는 메서드를 호출 target의 인스턴스에있는!
        log.info("result2: {}", result2);

    }

    @Test
    void reflection2() throws Exception {
        // 클래스 정보 ( 이런식으로 메타정보를 얻을 수 있다.)
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");// 내부클래스에 있을때는 달러표시를 사용해서 구분한다.

        Hello target = new Hello();
        // callA의 메서드 정보를 획득 ()
        Method methodCallA = classHello.getMethod("callA"); // String result1 = target.callA(); 이 부분을 이렇게 바꿧다고 생각해보자!
        dynamicCall(methodCallA, target);

        // callB의 메서드 정보를 획득 ()
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target); // 이 부분이 추상화된것! -> 그래서 이렇게 공통화 시키는것이 가능하다.
        log.info("result: {}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
