package br.com.luis.financial.repositories;

import br.com.luis.financial.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   UserDetails findByLogin(String login);

   Optional<User> findById(Long id);
}
