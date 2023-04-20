package com.kh.app.domain.member.svc;

import com.kh.app.domain.member.dao.MemberPersonDAO;
import com.kh.app.domain.member.entity.MemberPerson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberPersonSVCImpl implements MemberPersonSVC{

  private final MemberPersonDAO memberPersonDAO;

  @Override
  public MemberPerson join(MemberPerson memberPerson) {
    return memberPersonDAO.insertMember(memberPerson);
  }

  @Override
  public void modify(MemberPerson memberPerson) {
    memberPersonDAO.updateMemberPerson(memberPerson);
  }

  @Override
  public MemberPerson findByIdPerson(String idPerson) {
    return memberPersonDAO.selectMemberByIdPerson(idPerson);
  }

  @Override
  public MemberPerson findByPersonIdPk(Long personIdPk) {
    return memberPersonDAO.selectMemberByPersonIdPk(personIdPk);
  }

  @Override
  public List<MemberPerson> findAll() {
    return memberPersonDAO.selectAll();
  }

  @Override
  public void out(String idPerson) {
memberPersonDAO.deleteMemberPerson(idPerson);
  }

  @Override
  public boolean exitMemberPerson(String idPerson) {
    return memberPersonDAO.exitMemberPerson(idPerson);
  }

  @Override
  public MemberPerson login(String idPerson, String pwPerson) {
    return memberPersonDAO.login(idPerson,pwPerson);
  }

  @Override
  public boolean isMember(String idPerson, String pwPerson) {
    return memberPersonDAO.isMember(idPerson, pwPerson);
  }

  @Override
  public String findidPersonByEmail(String emailPerson) {
    return memberPersonDAO.findidPersonByEmail(emailPerson);
  }
}
