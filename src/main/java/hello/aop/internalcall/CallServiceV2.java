package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

//    private final ApplicationContext applicationContext;

    private final ObjectProvider<CallServiceV2> callServicProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServicProvider) {
        this.callServicProvider = callServicProvider;
    }

    public void external(){
        log.info("call external");
        CallServiceV2 callServiceV2 = callServicProvider.getObject();
        callServiceV2.internal();// 외부 메서드 호출
    }

    public void internal(){
        log.info("call internal");
    }

}
