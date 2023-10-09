package com.c201.aebook.api.user.persistence.repository;

import com.c201.aebook.api.user.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {



}
