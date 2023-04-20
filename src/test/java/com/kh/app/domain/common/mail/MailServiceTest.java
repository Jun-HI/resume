package com.kh.app.domain.common.mail;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MailServiceTest {

  @Autowired
  private MailService mailService;

  @Test
  void sendSimpleMail() {
    StringBuffer str = new StringBuffer();
    str.append("<html>");
    str.append("<a href='http://localhost/login'>로그인</a>");
    str.append("</html>");
    mailService.sendMail("rmfls2355@gmail.com","제목",str.toString());
  }
}