package com.project.muduck.common.object;

import java.util.ArrayList;
import java.util.List;



import com.project.muduck.entity.UserEntity;

import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String password;
    private String name;
    private String telNumber;
    private String address;
    private String joinPath;
    private String snsId;
    private Boolean isAdmin;
    private String profilePicture;

    public User(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.password = userEntity.getPassword();
        this.name = userEntity.getName();
        this.telNumber = userEntity.getTelNumber();
        this.address = userEntity.getAddress();
        this.joinPath = userEntity.getJoinPath();
        this.snsId = userEntity.getSnsId();
        this.isAdmin = userEntity.getIsAdmin();
        this.profilePicture = userEntity.getProfilePicture();
    }

    public static List<User> getList(List<UserEntity> userEntities) {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity: userEntities) {
            User user = new User(userEntity);
            users.add(user);
        }

        return users;
    } 
}
