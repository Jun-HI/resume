package com.kh.app.web.form.resume;

import lombok.Data;

@Data
public class ResumeItem {
  private Long resumeDetailId;  //이력서 상세 번호
  private Long resumeId;        //이력서 번호
  private Long resumeItemId;    //이력서 아이템 번호
  private String businessName;  //이력서 상세 회사이름
  private Long businessDate;    //이력서 상세 근무월
  private String businessWork;  //이력서 상세 근무명
}
