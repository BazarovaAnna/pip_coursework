package com.pip_coursework.repository;

import com.pip_coursework.entity.Race;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface RaceRepository extends CrudRepository<Race, Long> {
    @Query("select distinct r.type from Race r")
    ArrayList<String> findDistinctTypes();

    @Query("select r.imgPath from Race r where r.type = :type")
    ArrayList<String> findImgByRace(@Param("type") String type);

    Race findByImgPath(String imgPath);
}
