package com.kh.app.web;

import com.kh.app.domain.member.entity.MemberPerson;
import com.kh.app.domain.member.svc.MemberPersonSVC;
import com.kh.app.web.form.login.LoginForm;
import com.kh.app.web.form.login.LoginMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberPersonSVC memberPersonSVC;

  @GetMapping("/login")
  public String loginForm(Model model){
    model.addAttribute("loginForm", new LoginForm());
    return "login/loginForm";
  }


  @PostMapping("/login")
  public String login(
      @Valid
      @ModelAttribute
      LoginForm loginForm,
      BindingResult bindingResult,
      HttpServletRequest request,
      @RequestParam(required = false,defaultValue = "/") String redirectUrl
  ){

    //필드 유효성
    if(bindingResult.hasErrors()){
      log.info("loginError={}", bindingResult);
      return "login/loginForm";
    }
  //오브젝트 체크 : 회원유무
    if(!memberPersonSVC.exitMemberPerson(loginForm.getIdPerson())) {
      bindingResult.reject("loginFail.idPerson");

      return "login/loginForm";
    }
    //오브젝트 체크 :로그인
    MemberPerson memberPerson = memberPersonSVC.login(loginForm.getIdPerson(), loginForm.getPwPerson());
    if(memberPerson == null){
      bindingResult.reject("loginFail.pwPerson");
      return "login/loginForm";

  }

    //회원 세션 정보
    LoginMember loginMember = new LoginMember(
        memberPerson.getPersonIdPk(), memberPerson.getIdPerson(), memberPerson.getNamePerson());


    //인증성공
    //세션이 있으면 세션 반환, 없으면 새로이 생성
    HttpSession session = request.getSession(true);
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    return "redirect:"+redirectUrl;
}
  //로그아웃
  @GetMapping("/logout")
  public String logout(HttpServletRequest request){

    //세션이 있으면 세션을 반환하고 없으면 null반환
    HttpSession session = request.getSession(false);
    if(session != null){
      session.invalidate();  //세션 제거
    }

    return "redirect:/";
  }
}

