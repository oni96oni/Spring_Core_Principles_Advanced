package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CacheProxy implements Subject{

    private Subject target; // 실제 객체에서 호출될 대상을 타겟이라고 한다.
    private String cacheValue; // 캐시된 결과를 저장할 변수

    public CacheProxy(Subject target) { // 클라이언트가 프록시를
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheValue == null) {
            cacheValue = target.operation(); // 여기서 타겟은 리얼!서브젝트
        }
        return cacheValue;
    }

}
