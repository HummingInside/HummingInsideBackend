package com.backend.core.user;

import com.backend.core.BaseTimeEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String userId;
    private String userPassword;
    private String userEmail;
    private String userName;

    @Builder
    public User(String id, String password, String email, String name){
        this.userId = id;
        this.userPassword = password;
        this.userEmail = email;
        this.userName = name;
    }
}
