package com.pip_coursework.multipleKeys;
import com.pip_coursework.entity.Genre;
import com.pip_coursework.entity.User;

import java.io.Serializable;

public class GenresUsersKey implements Serializable {
    User user;
    Genre genre;
}
