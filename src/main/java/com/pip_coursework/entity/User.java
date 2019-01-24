package com.pip_coursework.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements Serializable, UserDetails {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "Login", unique = true, nullable = false)
    private String login;

    public  void setLogin(String login){this.login = login;}

    public String getLogin() {
        return login;
    }

    @Column(name =  "Password", length = 40, nullable = false)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Email", nullable = false)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Sex", length = 1, nullable = false)
    private char sex;

    public  void setSex(char sex){this.sex = sex;}
    public char getSex() {
        return sex;
    }

    @Basic(optional = false)
    @Column(name = "Date_Register", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegister;

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="User_Role", joinColumns = @JoinColumn(name="User_Id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "Active")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Нужен для получения данных из БД
    public User(){ }

    // Нужен для добавления данных в БД
    public User(String login, String password, String email, char sex){
        this.login = login;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.dateRegister = new Date();
    }

    @Override
    public String toString() {
        return String.format("Пользователь[id=%d, логин='%s', пароль='%s']", id, login, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
