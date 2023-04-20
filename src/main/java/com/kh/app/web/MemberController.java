package com.kh.app.web;


import com.kh.app.domain.member.entity.MemberPerson;
import com.kh.app.domain.member.svc.MemberPersonSVC;
import com.kh.app.web.form.member.JoinForm;
import com.kh.app.web.form.member.ModifyForm;
import com.kh.app.web.form.member.OutForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberPersonSVC memberPersonSVC;

  @GetMapping("/ad")
  public String joinFormBefore(){
    return "member/joinFormSelect";
  }
//  @PostMapping("/ad")
//  public String joinFormBefore2(){
//    return "redirect:/members/add";
//  }

  //회원가입 조회
  @GetMapping("/add")
  public String joinForm(@ModelAttribute JoinForm joinForm) {
    log.info("joinForm() 호출됨!");
    return "member/joinForm";
  }

  //회원가입 처리
  @PostMapping("/add")
  public String join(
      @Valid @ModelAttribute JoinForm joinForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    log.info("join() 호출됨!");
    log.info("idPerson={}, pwPerson={}, pwChkPerson={}, namePerson={},birthPerson={},genderPerson={}," +
            "addressPerson={}, addressDetailPerson={}, emailPerson={}, phonePerson={}",
        joinForm.getIdPerson(), joinForm.getPwPerson(), joinForm.getPwChkPerson(),
        joinForm.getNamePerson(), joinForm.getBirthPerson(), joinForm.getGenderPerson(),
        joinForm.getAddressPerson(), joinForm.getAddressDetailPerson(), joinForm.getEmailPerson(), joinForm.getPhonePerson()
    );

    //1) 유효성체크 - 필드오류
    if (bindingResult.hasErrors()) {
      log.info("error={}", bindingResult);
      return "member/JoinForm";
    }

    //2) 아이디 중복 체크
    if(memberPersonSVC.exitMemberPerson(joinForm.getIdPerson())){
      bindingResult.rejectValue("idPerson","joinForm.idPerson.dup");
      log.info("error={}", bindingResult);
      return "member/joinForm";
    }
    //3) 비밀번호 != 비밀번호 체크
    if (!joinForm.getPwPerson().equals(joinForm.getPwChkPerson())) {
      bindingResult.reject("memberperson.pawChkPerson");
      return "member/joinForm";
    }

    //4 이메일 인증




    //5)정상처리로직
    log.info("joinForm={}", joinForm);
    MemberPerson memberPerson = new MemberPerson();
    memberPerson.setIdPerson(joinForm.getIdPerson());
    memberPerson.setPwPerson(joinForm.getPwPerson());
    memberPerson.setNamePerson(joinForm.getNamePerson());
    memberPerson.setBirthPerson(joinForm.getBirthPerson());
    memberPerson.setGenderPerson(joinForm.getGenderPerson());
    memberPerson.setAddressPerson(joinForm.getAddressPerson());
    memberPerson.setAddressdetailPerson(joinForm.getAddressDetailPerson());
    memberPerson.setEmailPerson(joinForm.getEmailPerson());
    memberPerson.setPhonePerson(joinForm.getPhonePerson());

    MemberPerson joinedMember = memberPersonSVC.join(memberPerson);
    log.info("idPerson={}, pwPerson={}, namePerson={}, birthPerson={}, genderPerson={}," +
            "addressPerson={}, adressDetailPerson={}, emailPerson={}, phonePerson={}",
        joinedMember.getIdPerson(), joinedMember.getPwPerson(), joinedMember.getNamePerson()
        , joinedMember.getBirthPerson(), joinedMember.getGenderPerson(), joinedMember.getAddressPerson(), joinedMember.getAddressdetailPerson(),
        joinedMember.getEmailPerson(), joinedMember.getPhonePerson());

    return "mainBeforeLogin";
  }

  @GetMapping("/{idPerson}/edit")
  public String editForm(
      @PathVariable("idPerson") String idPerson,
      Model model){
    log.info("idPerson={}",idPerson);
    MemberPerson memberPerson = memberPersonSVC.findByIdPerson(idPerson);


    ModifyForm modifyForm = new ModifyForm();
    modifyForm.setIdPerson(memberPerson.getIdPerson());
    modifyForm.setNamePerson(memberPerson.getNamePerson());
    modifyForm.setAddressPerson(memberPerson.getAddressPerson());
    modifyForm.setAddressDetailPerson(memberPerson.getAddressdetailPerson());
    modifyForm.setEmailPerson(memberPerson.getEmailPerson());
    modifyForm.setPhonePerson(memberPerson.getPhonePerson());


    model.addAttribute("modifyForm", modifyForm);

    return "member/modifyForm";
  }

  //회원수정 처리
  @PostMapping("/edit")
  public String edit(
      @Valid @ModelAttribute ModifyForm modifyForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes){

    //1) 유효성 체크  - 필드오류
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      return "member/modifyForm";
    }
//
//    //2) 비밀번호가 일치하는지 체크
//    if(!memberPersonSVC.isMember(modifyForm.getIdPerson(), modifyForm.getPwPerson())){
//      bindingResult.rejectValue("pwPerson","memberPerson.pwChkPerson");
//      log.info("bindingResult={}", bindingResult);
//      return "member/modifyForm";
//    }

    //3) 회원정보 수정
    MemberPerson memberPerson = new MemberPerson();
    memberPerson.setIdPerson(modifyForm.getIdPerson());
    memberPerson.setNamePerson(modifyForm.getNamePerson());
    memberPerson.setAddressPerson(modifyForm.getAddressPerson());
    memberPerson.setAddressdetailPerson(modifyForm.getAddressDetailPerson());
    memberPerson.setEmailPerson(modifyForm.getEmailPerson());
    memberPerson.setPhonePerson(modifyForm.getPhonePerson());

    memberPersonSVC.modify(memberPerson);
    redirectAttributes.addAttribute("idPerson", memberPerson.getIdPerson());

    return "redirect:/members/{idPerson}/edit";  //회원 상세화면 이동
  }


  //회원탈퇴
  @GetMapping("/{idPerson}/out")
  public String outForm(@ModelAttribute OutForm outForm ){
    log.info("outForm 호출됨!");
    return "member/outForm";
  }

  @PostMapping("/out")
  public String out(
      @Valid @ModelAttribute OutForm outForm,
      BindingResult bindingResult,
      HttpSession session){

    log.info("out 호출됨");
    //1)유효성체크
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}",bindingResult);
      return "/member/outForm";
    }

    //3) 비밀번호가 일치하는지 체크
    if(!memberPersonSVC.isMember(outForm.getIdPerson(), outForm.getPwPerson())){
      bindingResult.rejectValue("pwPerson","memberPerson.pwChkPerson");
      log.info("bindingResult={}", bindingResult);
      return "member/outForm";
    }

    //4) 탈퇴로직 수행
    //4-1) 회원정보 삭제
    memberPersonSVC.out(outForm.getIdPerson());
    //4-2) 센션정보 제거
    if(session != null){
      session.invalidate(); //세션 제거
    }
    log.info(outForm.getIdPerson());

    return "redirect:/members/outCompleted";
  }

  @GetMapping("/outCompleted")
  public String outCompleted(){

    return "mainBeforeLogin"; //탈퇴수행 완료 view
  }
}
