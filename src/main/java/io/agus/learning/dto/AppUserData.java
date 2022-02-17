package io.agus.learning.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AppUserData {
  @NotNull(message = "Name is required")
  private String fullName;

  @Email(message = "Email is not valid")
  @NotNull(message = "Email is required")
  private String email;

  @NotNull(message = "Password is required")
  private String password;

  @NotNull(message = "Role is required")
  private String role;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
