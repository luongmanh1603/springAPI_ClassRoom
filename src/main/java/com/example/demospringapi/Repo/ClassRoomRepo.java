package com.example.demospringapi.Repo;

import com.example.demospringapi.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomRepo extends JpaRepository<ClassRoom, Integer> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClassRoom c WHERE c.class_name = :class_name")
    Boolean existsByClass_name(String class_name);

}
