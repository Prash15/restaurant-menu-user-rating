package com.hpe.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpe.casestudy.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
