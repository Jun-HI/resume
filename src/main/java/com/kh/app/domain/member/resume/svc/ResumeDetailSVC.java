package com.kh.app.domain.member.resume.svc;

import com.kh.app.domain.member.resume.ResumeDetail;

import java.util.List;

public interface ResumeDetailSVC {
  //등록
  void detailWrite(List<ResumeDetail> resumeDetails);
  //상세
  List<ResumeDetail> findByResumeDetailId(Long resumeId);
  //수정
  void detailModify(List<ResumeDetail> resumeDetail);
  //삭제
  int detailRemove(Long resumeId);
}
