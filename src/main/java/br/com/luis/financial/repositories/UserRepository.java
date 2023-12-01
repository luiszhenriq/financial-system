package br.com.luis.financial.repositories;

import br.com.luis.financial.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

   UserDetails findByLogin(String login);
}
