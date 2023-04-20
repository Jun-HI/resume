package com.kh.app.web.form.resume;

import lombok.Data;

import java.util.List;

@Data
public class ResumeDetailForm {

  private Long resumeId;  //이력서 관리번호
  private String idPerson;  //개인멤버 아이디
  private String resumeTitle; //이력서 제목
  private String selfIntro; //이력서 자기소개서

  List<ResumeItem> resumeItems; //이력서 아이템

}
