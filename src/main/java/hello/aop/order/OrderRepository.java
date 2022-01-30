package hello.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Slf4j
@Repository
public class OrderRepository {

    public String save(String itemId){   

        log.info("[OrderRepository] 싫행");
        //저장로직
        if(itemId.equals("ex")){
            throw new IllegalStateException("예외 발생!");
        }
        return "ok";

    }

}
