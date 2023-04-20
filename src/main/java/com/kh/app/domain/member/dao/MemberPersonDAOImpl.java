package com.kh.app.domain.member.dao;

import com.kh.app.domain.member.entity.MemberPerson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberPersonDAOImpl implements MemberPersonDAO {

  private final JdbcTemplate jdbcTemplate;


  @Override
  public MemberPerson insertMember(MemberPerson memberPerson) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into MEMBER_PERSON (PERSON_ID_PK, ID_PERSON,PW_PERSON,NAME_PERSON,BIRTH_PERSON, ");
    sql.append("GENDER_PERSON,ADDRESS_PERSON,ADDRESSDETAIL_PERSON,EMAIL_PERSON,PHONE_PERSON ) ");
    sql.append("values( MEMBER_PERSON_PERSON_ID_PK_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

    KeyHolder keyHolder = new GeneratedKeyHolder();

      jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[] {"PERSON_ID_PK"}
        );
        pstmt.setString(1,memberPerson.getIdPerson());
        pstmt.setString(2,memberPerson.getPwPerson());
        pstmt.setString(3,memberPerson.getNamePerson());
        pstmt.setString(4, String.valueOf(memberPerson.getBirthPerson()));
        pstmt.setString(5,memberPerson.getGenderPerson());
        pstmt.setString(6,memberPerson.getAddressPerson());
        pstmt.setString(7,memberPerson.getAddressdetailPerson());
        pstmt.setString(8,memberPerson.getEmailPerson());
        pstmt.setString(9, String.valueOf(memberPerson.getPhonePerson()));

        return pstmt;
      }
    },keyHolder);

      long PERSON_ID_PK = keyHolder.getKey().longValue();
      log.info("신규회원등록={} 후 PERSON_ID_PK 반환값={}",memberPerson, keyHolder.getKey());
      return selectMemberByPersonIdPk(PERSON_ID_PK);

  }

  @Override
  public void updateMemberPerson(MemberPerson memberPerson) {

    StringBuffer sql = new StringBuffer();
    sql.append("update  MEMBER_PERSON ");
    sql.append(" set    NAME_PERSON = ?, ");
    sql.append("        ADDRESS_PERSON = ?, ");
    sql.append("        ADDRESSDETAIL_PERSON = ?, ");
    sql.append("        EMAIL_PERSON = ?, ");
    sql.append("        PHONE_PERSON= ? ");
    sql.append("   where ID_PERSON = ? ");



    jdbcTemplate.update(
        sql.toString(),
        memberPerson.getNamePerson(),
        memberPerson.getAddressPerson(),
        memberPerson.getAddressdetailPerson(),
        memberPerson.getEmailPerson(),
        memberPerson.getPhonePerson(),
        memberPerson.getIdPerson());
  }

  @Override
  public MemberPerson selectMemberByIdPerson(String idPerson) {
    StringBuffer sql = new StringBuffer();
    sql.append("select  PERSON_ID_PK as personIdPk,  ");
    sql.append("         ID_PERSON, ");
    sql.append("        PW_PERSON, ");
    sql.append("        NAME_PERSON, ");
    sql.append("        BIRTH_PERSON, ");
    sql.append("        GENDER_PERSON, ");
    sql.append("        ADDRESS_PERSON, ");
    sql.append("        ADDRESSDETAIL_PERSON, ");
    sql.append("        EMAIL_PERSON, ");
    sql.append("        PHONE_PERSON ");
    sql.append("  from MEMBER_PERSON ");
    sql.append(" where ID_PERSON = ? ");

    try {
      MemberPerson memberPerson = jdbcTemplate.queryForObject(
          sql.toString(),
          new BeanPropertyRowMapper<>(MemberPerson.class),
          idPerson
      );
      return memberPerson;
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  @Override
  public MemberPerson selectMemberByPersonIdPk(Long personIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append("select PERSON_ID_PK as personIdPk, ");
    sql.append("        ID_PERSON, ");
    sql.append("        PW_PERSON, ");
    sql.append("        NAME_PERSON, ");
    sql.append("        BIRTH_PERSON, ");
    sql.append("        GENDER_PERSON, ");
    sql.append("        ADDRESS_PERSON, ");
    sql.append("        ADDRESSDETAIL_PERSON, ");
    sql.append("        EMAIL_PERSON, ");
    sql.append("        PHONE_PERSON ");
    sql.append("   from MEMBER_PERSON ");
    sql.append("   where PERSON_ID_PK = ? ");


      MemberPerson memberPerson = jdbcTemplate.queryForObject(
          sql.toString(),
          new BeanPropertyRowMapper<>(MemberPerson.class),
          personIdPk
      );
      return memberPerson;
    }


  @Override
  public List<MemberPerson> selectAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select PERSON_ID_PK as PERSON_ID_PK, ");
    sql.append("        ID_PERSON, ");
    sql.append("        PW_PERSON, ");
    sql.append("        NAME_PERSON, ");
    sql.append("        BIRTH_PERSON, ");
    sql.append("        GENDER_PERSON, ");
    sql.append("        ADDRESS_PERSON, ");
    sql.append("        ADDRESSDETAIL_PERSON, ");
    sql.append("        EMAIL_PERSON, ");
    sql.append("        PHONE_PERSON ");
    sql.append("   from MEMBER_PERSON ");
    sql.append("   where PERSON_ID_PK desc ");

    List<MemberPerson> list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(MemberPerson.class)
    );
    return list;
  }

  @Override
  public void deleteMemberPerson(String idPerson) {
  StringBuffer sql = new StringBuffer();
  sql.append("delete from MEMBER_PERSON ");
  sql.append(" where ID_PERSON = ? ");

  jdbcTemplate.update(sql.toString(), idPerson);
  }

  @Override
  public boolean exitMemberPerson(String idPerson) {

    String sql = "select count(ID_PERSON) from MEMBER_PERSON where ID_PERSON = ? ";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idPerson);
    return (count==1) ? true : false;
  }

  @Override
  public MemberPerson login(String idPerson, String pwPerson) {

    StringBuffer sql = new StringBuffer();
    sql.append("select PERSON_ID_PK, ID_PERSON, PW_PERSON, NAME_PERSON ");
    sql.append("  from MEMBER_PERSON ");
    sql.append("  where ID_PERSON = ? and PW_PERSON = ? ");

    List<MemberPerson> list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(MemberPerson.class),
        idPerson,pwPerson
    );

    return list.size() == 1 ? list.get(0) : null;
  }

  @Override
  public boolean isMember(String idPerson, String pwPerson) {

    StringBuffer sql = new StringBuffer();
    sql.append("select count(*) ");
    sql.append("  from MEMBER_PERSON ");
    sql.append("  where ID_PERSON = ? and PW_PERSON = ? ");

    Integer count = jdbcTemplate.queryForObject(
        sql.toString(), Integer.class, idPerson, pwPerson
    );
    return (count == 1) ? true : false;
  }

  @Override
  public String findidPersonByEmail(String emailPerson) {
    StringBuffer sql = new StringBuffer();
    sql.append("select ID_PERSON ");
    sql.append("  from MEMBER_PERSON ");
    sql.append("  where EMAIL_PERSON = ? ");

    List<String> result = jdbcTemplate.query(
        sql.toString(),
        new RowMapper<String>() {
          @Override
          public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getNString("ID_PERSON");
          }
        },
        emailPerson
    );
    return (result.size() == 1) ? result.get(0) : null;
}

}
