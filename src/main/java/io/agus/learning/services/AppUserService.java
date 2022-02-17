package io.agus.learning.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.agus.learning.models.entity.AppUser;
import io.agus.learning.models.repo.AppUserRepo;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

  @Autowired
  private AppUserRepo repo;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repo.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User with Email " + email + " not found"));
  }

  public AppUser registerAppUser(AppUser user) {
    boolean isEmailExist = repo.findByEmail(user.getEmail()).isPresent();
    if (isEmailExist) {
      throw new RuntimeException(String.format("Email %s is already exist", user.getEmail()));
    }
    user.setPassword(encoder.encode(user.getPassword()));
    return repo.save(user);
  }

}
