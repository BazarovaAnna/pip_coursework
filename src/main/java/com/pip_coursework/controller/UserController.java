package com.pip_coursework.controller;

import com.pip_coursework.entity.Genre;
import com.pip_coursework.entity.UserGenre;
import com.pip_coursework.repository.UserGenreRepository;
import com.pip_coursework.repository.GenreRepository;
import com.pip_coursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.pip_coursework.entity.User;
import com.pip_coursework.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("sex", user.getSex());

        model.addAttribute("filename", userService.getUserAvatar(user));

        return "user";
    }

    // Загрузка аватарки пользователя на сервер
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@AuthenticationPrincipal User user,
                            @RequestParam("file") MultipartFile file,
                             Model model) throws IOException {

        if(file != null){
            userService.addUsersAvatar(file, user);
        }

        return new ResponseEntity<String>((String) userService.getUserAvatar(user), HttpStatus.OK);
    }


    /*
    Код для смены пароля авторизированного пользователя

    @RequestMapping(value = "/change")
    public String change(Model modlel,
                         @AuthenticationPrincipal User user) {
        userService.changePassword(user);

        return "user";
    }

    @RequestMapping(value = "/confirmation/{code}", method = RequestMethod.GET)
    public String confirmation(Model model,
                               @PathVariable String code){

        boolean isConfirmed = userService.confirmationPassword(code);

        if(isConfirmed){
            model.addAttribute("message", "Пароль был успешно изменен!");
        }
        else {
            model.addAttribute("message", "Что-то пошло не так!");
        }

        return "redirect:/user?success="+isConfirmed;
    }
    */








    @Autowired
    UserGenreRepository user_genreRepository;
    @Autowired
    GenreRepository genreRepository;

    @RequestMapping("/user/add")
    @ResponseBody
    public String add(@RequestParam("login") String login,
                      @RequestParam("password") String password,
                      @RequestParam("mail") String mail,
                      @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                      @RequestParam("lang") char[] lang,
                      @RequestParam("sex") char sex){
        String executiongStatus = "";

        try{
            if(repository.findByLogin(login) != null){
                throw  new DataIntegrityViolationException("");
            }

            repository.save(new User(login, password,
                    mail, sex));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Такой логин уже существует";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("/user/findall")
    @ResponseBody
    public String findAll(){
        String result = "";

        for(User user : repository.findAll()){
            result += user.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/user/findByLogin")
    @ResponseBody
    public String fetchDataByLogin(@RequestParam("login") String login){
        String result = repository.findByLogin(login).toString();

        return  result;
    }

    @RequestMapping("/user/findById")
    @ResponseBody
    public String findById(@RequestParam("id") long id){
        String result = "";

        for(User user: repository.findById(id)){
            result += user.toString() + "<br>";
        }

        return  result;
    }

    @RequestMapping("/user/findgenres")
    @ResponseBody
    public String findGenresById(@RequestParam("id") long id){
        String result = "";
        result = user_genreRepository.findByUserId(id).toString();
        if (result.equals("")) {
            return "There're no genres referenced to user with this id";
        }
        return  result;
    }

    @RequestMapping("/user/setgenre")
    @ResponseBody
    public String setGenreById(@RequestParam("id") long id, long genreId){
        String result = "done";

        try {
            Genre genre = genreRepository.findById(genreId).get(0);
            User user = repository.findById(id).get(0);
            repository.save(user);
            genreRepository.save(genre);
            user_genreRepository.save(new UserGenre(user, genre));
        }

        catch (DataIntegrityViolationException e) {
            result = "There're no user or genre with this id";
        }

        finally {
            return result;
        }
    }

    //TODO: @RequestMapping("/UserController/deletegenre")
}