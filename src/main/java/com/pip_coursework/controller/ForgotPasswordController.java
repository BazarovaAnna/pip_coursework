package com.pip_coursework.controller;

import com.pip_coursework.entity.User;
import com.pip_coursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForgotPasswordController {
    @Autowired
    private UserService userService;

    // Переход на страницу для восстановления пароля
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotpassword(Model model){
        return "forgotpassword";
    }

    // Отправка на почту сообщения о смене пароля в случае существования такого логина
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public String sendRequestByChangePassword(Model model, User user) {

        boolean isSuccessfully = userService.sendRequestByChangePassword(user);

        if(isSuccessfully){
            model.addAttribute("message", "Подтвердите смену пароля на вашей почте");
        }
        else {
            model.addAttribute("error", "Такого логина не существует!");
        }

        return "forgotpassword";
    }

    // Проверка на существование кода активации и редирект
    @RequestMapping(value = "/confirmation/{code}", method = RequestMethod.GET)
    public String confirmation(Model model, @PathVariable String code){

        boolean isConfirmed = userService.isCorrectActivationCode(code);

        if(isConfirmed){
            model.addAttribute("code", code);
        }
        else {
            return "redirect:/";
        }

        return "confirmation";
    }

    // Смена пароля
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePassword(Model model, User user){

        boolean isConfirmed = userService.changePassword(user);

        if(isConfirmed){
            model.addAttribute("message", "Пароль был успешно изменен!");
        }
        else {
            model.addAttribute("error", "Что-то пошло не так!");
        }

        return "confirmation";
    }
}
