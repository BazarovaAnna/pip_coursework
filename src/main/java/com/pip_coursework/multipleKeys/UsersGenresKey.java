package com.pip_coursework.multipleKeys;
import com.pip_coursework.entity.Genre;
import com.pip_coursework.entity.User;

import java.io.Serializable;

public class UsersGenresKey implements Serializable {
    private User user;
    private Genre genre;
}