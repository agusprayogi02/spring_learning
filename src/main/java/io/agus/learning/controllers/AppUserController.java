package io.agus.learning.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agus.learning.dto.AppUserData;
import io.agus.learning.dto.ResponseData;
import io.agus.learning.models.entity.AppUser;
import io.agus.learning.services.AppUserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AppUserController {

  @Autowired
  private AppUserService appUserService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<ResponseData<AppUser>> register(@Valid @RequestBody AppUserData appUserData, Errors errors) {
    ResponseData<AppUser> responseData = new ResponseData<AppUser>();
    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessage().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.badRequest().body(responseData);
    }
    AppUser appUser = modelMapper.map(appUserData, AppUser.class);
    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
    AppUser createdAppUser = appUserService.registerAppUser(appUser);
    responseData.setPayload(createdAppUser);
    responseData.setStatus(true);
    return ResponseEntity.ok(responseData);
  }

  // @PostMapping("/login")
  // public ResponseEntity<ResponseData<AppUser>> login(@RequestBody AppUserData
  // appUserData) {
  // ResponseData<AppUser> responseData = new ResponseData<AppUser>();
  // AppUser appUser = modelMapper.map(appUserData, AppUser.class);
  // AppUser loggedAppUser =
  // appUserService.loadUserByUsername(appUserData.getEmail());
  // responseData.setPayload(loggedAppUser);
  // responseData.setStatus(true);
  // return ResponseEntity.ok(responseData);
  // }
}
