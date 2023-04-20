package com.kh.app.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPerson {
  private Long personIdPk;               //PERSON_ID_PK	NUMBER(8,0)
  private String idPerson;               //ID_PERSON	VARCHAR2(20 BYTE)
  private String pwPerson;               //PW_PERSON	VARCHAR2(30 BYTE)
  private String namePerson;             //NAME_PERSON	VARCHAR2(30 BYTE)
  private Long birthPerson;            //BIRTH_PERSON	NUMBER(8,0)
  private String genderPerson;           //GENDER_PERSON	CHAR(3 BYTE)
  private String addressPerson;          //ADDRESS_PERSON	VARCHAR2(300 BYTE)
  private String addressdetailPerson;    //ADDRESSDETAIL_PERSON	VARCHAR2(300 BYTE)
  private String emailPerson;            //EMAIL_PERSON	VARCHAR2(40 BYTE)
  private Long phonePerson;              //PHONE_PERSON	NUMBER(11,0)
  //CDATE_DATE_PERSON	TIMESTAMP(6)
  //UDATE_DATE_PERSON	TIMESTAMP(6)

  public MemberPerson(String idPerson, String pwPerson, String namePerson){
    this.idPerson = idPerson;
    this.pwPerson = pwPerson;
    this.namePerson = namePerson;
  }
}
