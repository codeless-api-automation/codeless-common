package com.codeless.api.automation.entity.security;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonView(View.ALLOWED.class)
public class UserDto {

  @Column(name = "username")
  @NotEmpty
  @Email
  private String username;

  @Size(max = 100, min = 5, message = "Invalid password size. Min - 5, Max - 100.")
  @NotEmpty
  @Column(name = "password")
  @JsonView(View.NOT_ALLOWED.class)
  private String password;
}
