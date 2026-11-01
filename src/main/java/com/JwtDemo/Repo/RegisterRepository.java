package com.JwtDemo.Repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JwtDemo.Models.Register;



@Repository
public interface RegisterRepository
        extends JpaRepository<Register, Long> {

    Optional<Register> findByEmail(String email);

    boolean existsByEmail(String email);

}