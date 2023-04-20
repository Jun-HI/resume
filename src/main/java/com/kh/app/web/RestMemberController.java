package com.kh.app.web;

import com.kh.app.domain.common.mail.MailService;
import com.kh.app.domain.common.util.PasswordGenerator;
import com.kh.app.web.rest.RestResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class RestMemberController {

  private final MailService ms;

  @GetMapping("/emailAuth")
  public void emailAuth(){
    log.info("emailAuth 호출됨");
  }
  @GetMapping("/emailAuth/{email:.+}")
  public RestResponse<Object> emailAuth(
      @PathVariable("email") String email, HttpServletRequest request
  ){
    RestResponse<Object> response = null;

    log.info("email={}", email);

    //2) 인증 번호 생성
    PasswordGenerator.PasswordGeneratorBuilder passwordGeneratorBuilder = new PasswordGenerator.PasswordGeneratorBuilder();
    String tmpNumber = passwordGeneratorBuilder
        .useDigits(true)  //숫자포함여부
        .useLower(true)   //소문자포함
        .useUpper(true)   //대문자포함
        .usePunctuation(false) //특수문자포함
        .build()
        .generate(6); //비밀번호 자리수
    
    //3) 메일 발송.
    String subject = "메일 인증 확인";

    //로긴주소
    StringBuilder url = new StringBuilder();
    url.append("http://" + request.getServerName());    //localhost
    url.append(":" + request.getServerPort());          //prot
    url.append(request.getContextPath());               // /
    url.append("/login");

    //메일본문내용
    StringBuilder sb = new StringBuilder();
    sb.append("<!DOCTYPE html>");
    sb.append("<html lang='ko'>");
    sb.append("<head>");
    sb.append("  <meta charset='UTF-8'>");
    sb.append("  <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
    sb.append("  <title>이메일 인증</title>");
    sb.append("</head>");
    sb.append("<body>");
    sb.append("  <h1>이메일 인증 확인</h1>");
    sb.append("  <p>아래 이메일 인증번호를 확인하고 회원가입 이메일 인증란을 통해 확인바랍니다</p>");
    sb.append("  <p>비밀번호 :" + tmpNumber + "</p>");
    sb.append("</body>");
    sb.append("</html>");

    ms.sendMail(email, subject , sb.toString());

    response = RestResponse.createRestResponse("00","성공",tmpNumber);
    return  response;
  }
}
