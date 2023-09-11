package com.rioinvest.authms.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.rioinvest.authms.domain.user.User;

public interface UserRepository extends MongoRepository<User, String> {

    UserDetails findByUsername(String username);

    User findByEmail(String email);

    User findByCpf(String cpf);

}