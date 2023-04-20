package com.kh.app.domain.member.dao;

import com.kh.app.domain.member.entity.MemberPerson;

import java.util.List;

public interface MemberPersonDAO {
  //가입
  MemberPerson insertMember(MemberPerson memberPerson);
  //수정
  void updateMemberPerson(MemberPerson memberPerson);
  //조회 아이디로
  MemberPerson selectMemberByIdPerson(String idPerson);
  //조회 번호로
  MemberPerson selectMemberByPersonIdPk(Long personIdPk);
  //전체조회
  List<MemberPerson> selectAll();
  //탈퇴
  void deleteMemberPerson(String idPerson);
  //회원 유무체크
  boolean exitMemberPerson(String idPerson);
  //로그인 인증
  MemberPerson login(String idPerson, String pwPerson);
  //비밀번호 일치여부 체크
  boolean isMember(String idPerson, String pwPerson);
  //아이디 찾기
  String findidPersonByEmail(String emailPerson);

}
