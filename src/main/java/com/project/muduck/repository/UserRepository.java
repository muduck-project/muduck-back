package com.project.muduck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.muduck.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);


    UserEntity findByUserId(String userId);
    UserEntity findByTelNumber(String telNumber);

    UserEntity findBySnsIdAndJoinPath(String snsId, String joinPath);
}
