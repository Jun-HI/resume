package com.kh.app.web.form.member;

import lombok.Data;

@Data
public class JoinForm {
    //NotBlank는 String만 가능하다
//  @Size(min=4,max=15)
  private String idPerson;

//  @Size(min=8,max=20)
  private String pwPerson;
  //  @Size(min=8,max=20)
  private String pwChkPerson;

  private String namePerson;

  private Long birthPerson;

  private String genderPerson;

  private String addressPerson;

  private String addressDetailPerson;

  private String emailPerson;

  private Long phonePerson;

}
