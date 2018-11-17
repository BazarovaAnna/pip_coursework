package com.pip_coursework.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Users")
public class User  implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsersSequence")
    private long id;

    @Column(name = "Login", unique = true, nullable = false)
    private  String login;

    @Column(name =  "Password", length = 40, nullable = false)
    private char[] password;

    @Column(name = "Mail", nullable = false)
    private  String mail;

    @Basic(optional = false)
    @Column(name = "Date_Birth", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBirth;

    @Column(name = "Language", nullable = false, length = 5)
    private char[] language;

    @Column(name = "Sex", length = 1)
    private  char sex;

    @Basic(optional = false)
    @Column(name = "Date_Register", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegister;

    @Basic(optional = false)
    @Column(name = "Date_Exit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExit;

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
        return String.format("User[id=%d, login='%s', password='%s']", id, login, String.copyValueOf(password));
    }
}
