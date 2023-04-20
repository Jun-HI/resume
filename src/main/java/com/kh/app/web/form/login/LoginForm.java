package com.kh.app.web.form.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {
  @NotBlank// null아니고 적어도 공백문자가 아닌문자가 1개이상인지 체크
  @Size(min = 4, max=50)
  private String idPerson;

  @NotBlank
  @Size(min = 4, max=8)
  private String pwPerson;
}
