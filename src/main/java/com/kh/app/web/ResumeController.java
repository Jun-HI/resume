package com.kh.app.web;

import com.kh.app.domain.member.entity.MemberPerson;
import com.kh.app.domain.member.svc.MemberPersonSVC;
import com.kh.app.domain.member.resume.Resume;
import com.kh.app.domain.member.resume.ResumeDetail;
import com.kh.app.domain.member.resume.svc.ResumeDetailSVC;
import com.kh.app.domain.member.resume.svc.ResumeSVC;
import com.kh.app.web.form.resume.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {

  private final MemberPersonSVC memberPersonSVC;
  private final ResumeSVC resumeSVC;
  private final ResumeDetailSVC resumeDetailSVC;



  //이력서 목록 조회
  @GetMapping("/{idPerson}/blank")
  public String resumeForm(
      @PathVariable("idPerson") String idPerson,
      RedirectAttributes redirectAttributes,
      Model model) {
    log.info("idPerson={}", idPerson);

    List<Resume> list = resumeSVC.findAll();

    List<ResumeList> resumeLists = new ArrayList<>();
    for(Resume resume : list) {
      ResumeList resumeList = new ResumeList();
      resumeList.setResumeId(resume.getResumeId());
      resumeList.setResumeTitle(resume.getResumeTitle());
      resumeLists.add(resumeList);
    }

    model.addAttribute("resumeLists",resumeLists);

    return "resume/resumeFormBlank";
  }

  //이력서 삭제 처리
  @GetMapping("/{idPerson}/{resumeId}/del")
  public String MoveResumeForm(
      @PathVariable("idPerson") String idPerson,
      @PathVariable("resumeId") String resumeId,
      RedirectAttributes redirectAttributes) {
    log.info("아이디 idPerson={}", idPerson);
    log.info("이력서 삭제 처리 resumeId={}", resumeId);


      resumeDetailSVC.detailRemove(Long.valueOf(resumeId));
      resumeSVC.remove(Long.valueOf(resumeId));


  redirectAttributes.addAttribute("idPerson",idPerson);

    return "redirect:/resume/{idPerson}/blank";
  }

  //이력서 등록 양식
  @GetMapping("/{idPerson}/add")
  public String resumePersonForm(
      @PathVariable("idPerson") String idPerson,
      Model model) {

    MemberPerson memberPerson = memberPersonSVC.findByIdPerson(idPerson);

    ResumePersonForm resumePersonForm = new ResumePersonForm();
    resumePersonForm.setNamePerson(memberPerson.getNamePerson());
    resumePersonForm.setGenderPerson(memberPerson.getGenderPerson());
    resumePersonForm.setBirthPerson(memberPerson.getBirthPerson());
    resumePersonForm.setPhonePerson(memberPerson.getPhonePerson());
    resumePersonForm.setAddressPerson(memberPerson.getAddressPerson());
    resumePersonForm.setAddressdetailPerson(memberPerson.getAddressdetailPerson());
    resumePersonForm.setEmailPerson(memberPerson.getEmailPerson());


    model.addAttribute("resumePersonForm", resumePersonForm);
    model.addAttribute("resumeDetailForm", new ResumeDetailForm());

    return "resume/resumeForm";
  }

  //이력서 등록처리
  @PostMapping("/{idPerson}/add")
  public String resumeAddForm(@Valid @ModelAttribute ResumeForm resumeForm,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @PathVariable("idPerson") String idPerson,
                              Model model) {

    log.info("resumeForm={}", resumeForm);

    //1)유효성체크 - 필드오류
    if (bindingResult.hasErrors()) {
      log.info("error={}", bindingResult);
      return "member/joinForm";
    }

    log.info("ResumeForm1={}", resumeForm);
    Resume resume = new Resume();
    resume.setSelfIntro(resumeForm.getSelfIntro());
    resume.setResumeTitle(resumeForm.getResumeTitle());
    resume.setIdPerson(resumeForm.getIdPerson());

    Resume joinedResume = resumeSVC.write(resume);
    log.info("joinedResume={}", joinedResume);

    List<ResumeDetail> resumeDetails = new ArrayList<>();

    long index = 0L;
    for (ResumeItem resumeItem : resumeForm.getResumeItems()) {
      //아이템 정보가 없으면 패스
      if (StringUtils.isEmpty(resumeItem.getBusinessName()) ||
          StringUtils.isEmpty(String.valueOf(resumeItem.getBusinessDate())) ||
          StringUtils.isEmpty(resumeItem.getBusinessWork())) {
        continue;
      }
      index++;
      ResumeDetail resumeDetail = new ResumeDetail();
      resumeDetail.setResumeDetailId(resumeItem.getResumeDetailId());
      resumeDetail.setResumeId(joinedResume.getResumeId());
      resumeDetail.setResumeItemId(index);
      resumeDetail.setIdPerson(resumeForm.getIdPerson());
      resumeDetail.setBusinessName(resumeItem.getBusinessName());
      resumeDetail.setBusinessDate(resumeItem.getBusinessDate());
      resumeDetail.setBusinessWork(resumeItem.getBusinessWork());
      resumeDetails.add(resumeDetail);
    }

    log.info("resumeDetails={}", resumeDetails);

    resumeDetailSVC.detailWrite(resumeDetails);

    Long resumeId = joinedResume.getResumeId();
    redirectAttributes.addAttribute("resumeId", resumeId);

    String personId = joinedResume.getIdPerson();
    redirectAttributes.addAttribute("idPerson", idPerson);


    return "redirect:/resume/{idPerson}/resumeFormDetail/{resumeId}";
  }

  //이력서 상세
  @GetMapping("/{idPerson}/resumeFormDetail/{resumeId}")
  public String resumeDetailForm(
      @PathVariable("idPerson") String idPerson,
      @PathVariable("resumeId") Long resumeId,
      RedirectAttributes redirectAttributes,
      Model model) {

    MemberPerson memberPerson = memberPersonSVC.findByIdPerson(idPerson);

    ResumePersonForm resumePersonForm = new ResumePersonForm();
    resumePersonForm.setNamePerson(memberPerson.getNamePerson());
    resumePersonForm.setGenderPerson(memberPerson.getGenderPerson());
    resumePersonForm.setBirthPerson(memberPerson.getBirthPerson());
    resumePersonForm.setPhonePerson(memberPerson.getPhonePerson());
    resumePersonForm.setAddressPerson(memberPerson.getAddressPerson());
    resumePersonForm.setAddressdetailPerson(memberPerson.getAddressdetailPerson());
    resumePersonForm.setEmailPerson(memberPerson.getEmailPerson());

    Resume resume = resumeSVC.findByResumeId(resumeId);
    List<ResumeDetail> resumeDetails = resumeDetailSVC.findByResumeDetailId(resumeId);

    ResumeDetailForm resumeDetailForm = new ResumeDetailForm();
    resumeDetailForm.setResumeId(resume.getResumeId());
    resumeDetailForm.setIdPerson(resume.getIdPerson());
    resumeDetailForm.setResumeTitle(resume.getResumeTitle());
    resumeDetailForm.setSelfIntro(resume.getSelfIntro());

    List<ResumeItem> resumeItems = new ArrayList<>();
    for (ResumeDetail resumeDetail : resumeDetails) {
      ResumeItem resumeItem = new ResumeItem();

      resumeItem.setResumeDetailId(resumeDetail.getResumeDetailId());
      resumeItem.setResumeId(resumeDetail.getResumeId());
      resumeItem.setResumeItemId(resumeDetail.getResumeItemId());
      resumeItem.setBusinessName(resumeDetail.getBusinessName());
      resumeItem.setBusinessDate(resumeDetail.getBusinessDate());
      resumeItem.setBusinessWork(resumeDetail.getBusinessWork());

      resumeItems.add(resumeItem);
    }

    resumeDetailForm.setResumeItems(resumeItems);

    model.addAttribute("resumePersonForm", resumePersonForm);
    model.addAttribute("resumeDetailForm", resumeDetailForm);

    return "resume/resumeFormDetail";
  }


  // 이력서 수정 조회
  @GetMapping("/{idPerson}/resumeFormModify/{resumeId}")
  public String resumeModifyForm(@PathVariable String idPerson,
                                 @PathVariable Long resumeId,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

    MemberPerson memberPerson = memberPersonSVC.findByIdPerson(idPerson);

    ResumePersonForm resumePersonForm = new ResumePersonForm();
    resumePersonForm.setNamePerson(memberPerson.getNamePerson());
    resumePersonForm.setGenderPerson(memberPerson.getGenderPerson());
    resumePersonForm.setBirthPerson(memberPerson.getBirthPerson());
    resumePersonForm.setPhonePerson(memberPerson.getPhonePerson());
    resumePersonForm.setAddressPerson(memberPerson.getAddressPerson());
    resumePersonForm.setAddressdetailPerson(memberPerson.getAddressdetailPerson());
    resumePersonForm.setEmailPerson(memberPerson.getEmailPerson());

    Resume resume = resumeSVC.findByResumeId(resumeId);
    List<ResumeDetail> resumeDetails = resumeDetailSVC.findByResumeDetailId(resumeId);
    ResumeDetailForm resumeDetailForm = new ResumeDetailForm();


    resumeDetailForm.setResumeId(resume.getResumeId());
    resumeDetailForm.setIdPerson(resume.getIdPerson());
    resumeDetailForm.setResumeTitle(resume.getResumeTitle());
    resumeDetailForm.setSelfIntro(resume.getSelfIntro());

    List<ResumeItem> resumeItems = new ArrayList<>();
    for (ResumeDetail resumeDetail : resumeDetails) {
      ResumeItem resumeItem = new ResumeItem();

      resumeItem.setResumeDetailId(resumeDetail.getResumeDetailId());
      resumeItem.setResumeId(resumeDetail.getResumeId());
      resumeItem.setResumeItemId(resumeDetail.getResumeItemId());
      resumeItem.setBusinessName(resumeDetail.getBusinessName());
      resumeItem.setBusinessDate(resumeDetail.getBusinessDate());
      resumeItem.setBusinessWork(resumeDetail.getBusinessWork());

      resumeItems.add(resumeItem);
    }

    resumeDetailForm.setResumeItems(resumeItems);

    model.addAttribute("resumePersonForm", resumePersonForm);
    model.addAttribute("resumeDetailForm", resumeDetailForm);
    log.info("조회-resumeDetailForm={}",resumeDetailForm);


    redirectAttributes.addAttribute("idPerson", idPerson);
    redirectAttributes.addAttribute("resumeId", resumeId);

    return "resume/resumeFormModify";
  }

  //이력서 수정 처리
  @PostMapping("/{idPerson}/resumeFormModify/{resumeId}")
  public String resumeModifyFormEnd(@PathVariable String idPerson,
                                    @PathVariable Long resumeId,
                                    RedirectAttributes redirectAttributes,
                                    @ModelAttribute ResumeModifyForm resumeModifyForm,
                                    Model model) {

    log.info("수정처리-resumeModifyForm={}", resumeModifyForm);

    Resume resume = new Resume();
    resume.setResumeTitle(resumeModifyForm.getResumeTitle());
    resume.setSelfIntro(resumeModifyForm.getSelfIntro());
    resume.setResumeId(resumeModifyForm.getResumeId());

    log.info(resumeModifyForm.getSelfIntro());
    Resume modifiedResume = resumeSVC.modify(resume);
    log.info("modifiedResume={}", modifiedResume);

    List<ResumeDetail> resumeDetails = new ArrayList<>();

    long index = 0L;
    for (ResumeItem resumeItem : resumeModifyForm.getResumeItems()) {
      //아이템 정보가 없으면 패스
      if (StringUtils.isEmpty(resumeItem.getBusinessName()) ||
          StringUtils.isEmpty(String.valueOf(resumeItem.getBusinessDate())) ||
          StringUtils.isEmpty(resumeItem.getBusinessWork())) {
        continue;
      }
      index++;
      ResumeDetail resumeDetail = new ResumeDetail();
      resumeDetail.setResumeDetailId(resumeItem.getResumeDetailId());
      resumeDetail.setResumeId(modifiedResume.getResumeId());
      resumeDetail.setResumeItemId(index);
      resumeDetail.setIdPerson(resumeModifyForm.getIdPerson());
      resumeDetail.setBusinessName(resumeItem.getBusinessName());
      resumeDetail.setBusinessDate(resumeItem.getBusinessDate());
      resumeDetail.setBusinessWork(resumeItem.getBusinessWork());
      log.info("resumeDetail={}",resumeDetail.getBusinessWork());
      log.info("resumeDetail={}",resumeDetail.getBusinessDate());
      log.info("resumeDetail={}",resumeDetail.getBusinessName());
      resumeDetails.add(resumeDetail);
    }
    log.info("resumeDetails={}", resumeDetails);

    resumeDetailSVC.detailModify(resumeDetails);

    log.info("resumeDetails={}", resumeDetails);


    Long resumeId2 = modifiedResume.getResumeId();
    redirectAttributes.addAttribute("resumeId", resumeId2);

    String personId2 = modifiedResume.getIdPerson();
    redirectAttributes.addAttribute("idPerson", personId2);


    return "redirect:/resume/{idPerson}/resumeFormDetail/{resumeId}";
  }



  //이력서 수정 후 조회
//  @GetMapping("/{idPerson}/resumeFormDetail/{resumeId}/resumeModifyForm")
//  public String resumeDetailFormEnd(
//      @PathVariable("idPerson") String idPerson,
//      @PathVariable("resumeId") Long resumeId,
//      RedirectAttributes redirectAttributes,
//      Model model){
//
//    MemberPerson memberPerson = memberPersonSVC.findByIdPerson(idPerson);
//
//    ResumePersonForm resumePersonForm  = new ResumePersonForm();
//    resumePersonForm.setNamePerson(memberPerson.getNamePerson());
//    resumePersonForm.setGenderPerson(memberPerson.getGenderPerson());
//    resumePersonForm.setBirthPerson(memberPerson.getBirthPerson());
//    resumePersonForm.setPhonePerson(memberPerson.getPhonePerson());
//    resumePersonForm.setAddressPerson(memberPerson.getAddressPerson());
//    resumePersonForm.setAddressdetailPerson(memberPerson.getAddressdetailPerson());
//    resumePersonForm.setEmailPerson(memberPerson.getEmailPerson());
//
//    Resume resume = resumeSVC.findByResumeId(resumeId);
//    List<ResumeDetail> resumeDetails = resumeDetailSVC.findByResumeDetailId(resumeId);
//
//    ResumeDetailForm resumeDetailForm = new ResumeDetailForm();
////    private Long resumeId;  //이력서 관리번호
////    private String idPerson;  //개인멤버 아이디
////    private String resumeTitle; //이력서 제목
////    private String selfIntro; //이력서 자기소개서
////
////    List<ResumeItem> resumeItems; //이력서 아이템
//    resumeDetailForm.setResumeId(resume.getResumeId());
//    resumeDetailForm.setIdPerson(resume.getIdPerson());
//    resumeDetailForm.setResumeTitle(resume.getResumeTitle());
//    resumeDetailForm.setSelfIntro(resume.getSelfIntro());
//
//    List<ResumeItem> resumeItems = new ArrayList<>();
//    for (ResumeDetail resumeDetail : resumeDetails) {
//      ResumeItem resumeItem = new ResumeItem();
//
//      resumeItem.setResumeDetailId(resumeDetail.getResumeDetailId());
//      resumeItem.setResumeId(resumeDetail.getResumeId());
//      resumeItem.setResumeItemId(resumeDetail.getResumeItemId());
//      resumeItem.setBusinessName(resumeDetail.getBusinessName());
//      resumeItem.setBusinessDate(resumeDetail.getBusinessDate());
//      resumeItem.setBusinessWork(resumeDetail.getBusinessWork());
//
//      resumeItems.add(resumeItem);
//    }
//
//    resumeDetailForm.setResumeItems(resumeItems);
//
//    model.addAttribute("resumePersonForm", resumePersonForm);
//    model.addAttribute("resumeDetailForm", resumeDetailForm);
//
//    return "resume/resumeFormDetail";
//  }

}