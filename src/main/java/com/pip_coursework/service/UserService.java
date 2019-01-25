package com.pip_coursework.service;

import com.pip_coursework.entity.Role;
import com.pip_coursework.entity.User;
import com.pip_coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    // Параметр, отвечающий за сервис на который будет направлен пользователь после перехода по ссылке
    private static final String EMAILURL = "http://localhost:8080/confirmation/";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }

    // Добавление нового пользователя
    public boolean addUser(User user){
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if(userFromDb != null){
            return false;
        }

        user.setActive(true);
        user.setDateRegister(new Date());
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return true;
    }

    // Отпавка запроса на смену пароля
    public boolean sendRequestByChangePassword(User user){
        user = userRepository.findByLogin(user.getLogin());

        if(user != null){
            user.setActivationCode(UUID.randomUUID().toString());

            String message = String.format(
                    "Доброго времени суток, %s! \n" +
                            "Не переходите по ссылке, если не меняли пароль от аккауна D&D!\n" +
                            "%s%s", user.getLogin(), EMAILURL, user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код подтверждения", message);

            userRepository.save(user);

            return true;
        }

        return false;
    }

    // Смена пароля
    public boolean changePassword(User userWithNewPassword) {
        User user = userRepository.
                findByActivationCode(userWithNewPassword.getActivationCode());

        if(user == null){
            return  false;
        }

        user.setActivationCode(null);

        user.setPassword(userWithNewPassword.getPassword());

        userRepository.save(user);

        return true;
    }

    // Проверка на наличие корректного кода активации
    public boolean isCorrectActivationCode(String code){
        User user = userRepository.findByActivationCode(code);

        if(user == null){
            return  false;
        }

        return true;
    }

    /*
    TODO Для версии с изменением пароля из личного кабинета
    public boolean confirmationPassword(String code) {
        User user = userRepository.findByActivationCode(code);

        if(user == null){
            return  false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }
    */
}
