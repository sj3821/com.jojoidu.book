package com.jojoidu.book.springboot.web;

import com.jojoidu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트를 진행할때 JUnit에 내장된 실행자가 아닌 스프링실행자인 SpringRunner를 사용한다
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중할 수 있는 어노테이션이다. @Controller, @ControllerAdvice등 사용 가능하다. @Service, @Component, @Repository 등은 사용 불가하다.
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈(Bean)을 주입받는다.
    private MockMvc mvc; // 웹 API를 테스트할때 사용, 스프링 MVC 테스트의 시작점. 이 클래스를 통해 HTTP GET,POST 등에 대한 API 테스트를 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소롤 HTTP GET을 요청한다. 체이닝이 지원되어 여러 검증기능을 이어서 선언할 수 있다.
            .andExpect(status().isOk()) // mvc.perform의 결과를 검증, HTTP Header의 Status를 검증한다. 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증한다. 여기서는 OK(200)인지 검증한다.
            .andExpect(content().string(hello)); // mvc.poerform의 결과를 검증, 응답 본문의 내용을 검증한다. Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증한다.
    }


}
