package com.example.moment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
  // 테스트 시점에 H2 메모리 DB를 사용
  "spring.datasource.url=jdbc:h2:mem:testdb",
  "spring.datasource.driver-class-name=org.h2.Driver",
  "spring.datasource.username=sa",
  "spring.datasource.password="
})
class MomentApplicationTests {

  @Test
  void contextLoads() {
    // 단순히 컨텍스트 로딩만 검사
  }
}
