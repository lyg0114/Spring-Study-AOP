package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;


    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod(){
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    void exactMatch(){

       //public java.lang.String hello.aop.memter.MemberServiceImpl.hello(java.lang.String)
       pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
       assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void allMatch(){

        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void nameMatch(){

        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void nameMatchStar1(){

        pointcut.setExpression("execution(* hell*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void nameMatchStar2(){

        pointcut.setExpression("execution(* *ell*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void nameMatchFalse(){

        pointcut.setExpression("execution(* nano(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();

    }

    @Test
    void packageExactMatch1(){

        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void packageExactMatch2(){

        pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void packageExactFalse(){

        // hello.aop 패키지에 정확히 class 가 존재해야한다.
        pointcut.setExpression("execution(* hello.aop.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();

    }

    @Test
    void packageMatchSubPackage1(){

        // hello.aop.member 하위 패키지까지 모두 적용
        pointcut.setExpression("execution(* hello.aop.member..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();

    }

    @Test
    void packageMatchSubPackage2(){

        // hello.aop 하위 패키지까지 모두 적용
        pointcut.setExpression("execution(* hello.aop..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }








}
















