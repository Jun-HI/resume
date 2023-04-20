package com.kh.app.domain.member.resume;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResumeDetail {
  private Long resumeDetailId;  //이력서 상세 번호
  private Long resumeId;  //이력서 관리번호
  private Long resumeItemId;  //이력서 관리번호
  private String idPerson;  //개인멤버 아이디
  private String businessName;  //이력서 상세 회사이름
  private Long businessDate;    //이력서 상세 근무월
  private String businessWork;  //이력서 상세 근무명

}
