package com.project.muduck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.muduck.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserId(String userId);

    UserEntity findBySnsIdAndJoinPath(String snsId, String joinPath);
}
