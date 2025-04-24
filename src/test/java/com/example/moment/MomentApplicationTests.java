package com.example.moment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
  // 테스트 시에는 H2 메모리 DB를 쓰도록 오버라이드
  "spring.datasource.url=jdbc:h2:mem:testdb",
  "spring.datasource.driver-class-name=org.h2.Driver",
  "spring.datasource.username=sa",
  "spring.datasource.password="
})
class MomentApplicationTests {

  @Test
  void contextLoads() {
    // 빈 로딩만 확인
  }

}
