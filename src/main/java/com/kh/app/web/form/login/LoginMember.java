package com.kh.app.web.form.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginMember {
  private Long personIdPk;
  private String idPerson;
  private String namePerson;
}
