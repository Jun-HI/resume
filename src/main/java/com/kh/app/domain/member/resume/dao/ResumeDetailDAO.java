package com.kh.app.domain.member.resume.dao;

import com.kh.app.domain.member.resume.ResumeDetail;

import java.util.List;

public interface ResumeDetailDAO {
  //이력서 등록
  void create(List<ResumeDetail> resumeDetails);

  //이력서 조회
  List<ResumeDetail> selectOne(Long resumeId);


  //이력서 수정
  void update(List<ResumeDetail> resumeDetails);

  //이력서 삭제
  int delete(Long resumeId);



}
