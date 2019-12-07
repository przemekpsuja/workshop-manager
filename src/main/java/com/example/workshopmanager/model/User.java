package com.example.workshopmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "users")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String userPassword;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Column(name = "MAIL", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "ROLES", nullable = false)
    private String roles;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private Set<Authorities> authorities = new HashSet<>();



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    //    public Set<Authorities> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<Authorities> authorities) {
//        this.authorities = authorities;
//    }

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @NotNull
//    private String userName;
//    @NotNull
//    private String userSurname;
//    @NotNull
//    @Column(unique = true)
//    private String userEmail;
//    @NotNull
//    private String userPassword;
//    private Enum role = Role.USER;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserSurname() {
//        return userSurname;
//    }
//
//    public void setUserSurname(String userSurname) {
//        this.userSurname = userSurname;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }
//
//    public Enum getRole() {
//        return role;
//    }
//
//    public void setRole(Enum role) {
//        this.role = role;
//    }
//
//    @Override
//    public String toString() {
//        return "Users{" +
//                "userName='" + userName + '\'' +
//                ", userSurname='" + userSurname + '\'' +
//                ", userEmail='" + userEmail + '\'' +
//                ", userPassword='" + userPassword + '\'' +
//                ", role=" + role +
//                '}';
//    }
}
