package com.kh.app.domain.member.svc;

import com.kh.app.domain.member.entity.MemberPerson;

import java.util.List;

public interface MemberPersonSVC {
  //가입
  MemberPerson join(MemberPerson memberPerson);
  //수정
  void modify(MemberPerson memberPerson);
  //조회 아이디로
  MemberPerson findByIdPerson(String idPerson);
  //조회 번호로
  MemberPerson findByPersonIdPk(Long personIdPk);
  //전체조회
  List<MemberPerson> findAll();
  //탈퇴
  void out(String idPerson);
  //회원 유무체크
  boolean exitMemberPerson(String idPerson);
  //로그인 인증
  MemberPerson login(String idPerson, String pwPerson);
  //비밀번호 일치여부 체크
  boolean isMember(String idPerson, String pwPerson);
  //아이디 찾기
  String findidPersonByEmail(String emailPerson);

}
