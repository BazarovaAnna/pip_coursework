package com.pip_coursework.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "Login", unique = true, nullable = false)
    private String login;

    public String getLogin() {
        return login;
    }

    @Column(name =  "Password", length = 40, nullable = false)
    private char[] password;

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Column(name = "Mail", nullable = false)
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic(optional = false)
    @Column(name = "Date_Birth", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBirth;

    public Date getDateBirth() {
        return dateBirth;
    }

    @Column(name = "Language", nullable = false, length = 5)
    private char[] language;

    public char[] getLanguage() {
        return language;
    }

    @Column(name = "Sex", length = 1, nullable = false)
    private char sex;

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

    @Basic(optional = false)
    @Column(name = "Date_Exit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExit;

    public Date getDateExit() {
        return dateExit;
    }

    public void setDateExit(Date dateExit) {
        this.dateExit = dateExit;
    }

    // Нужен для получения данных из БД
    protected User(){ }

    // Нужен для добавления данных в БД
    public User(String login, char[] password, String mail,
                Date dateBirth, char[] language,char sex){
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.dateBirth = dateBirth;
        this.language = language;
        this.sex = sex;
        this.dateRegister = new Date();
    }

    @Override
    public String toString() {
        return String.format("Пользователь[id=%d, логин='%s', пароль='%s']", id, login, String.copyValueOf(password));
    }
}
