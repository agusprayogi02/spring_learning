package io.agus.learning.models.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.agus.learning.models.entity.AppUser;

@Repository
@Transactional
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

  Optional<AppUser> findByEmail(String email);
}
