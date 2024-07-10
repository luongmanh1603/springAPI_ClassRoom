package com.example.demospringapi.Repo;

import com.example.demospringapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("SELECT COUNT(s) FROM Student s WHERE s.classRoom.id_class = :classId")
    Integer countByClassRoomId(Integer classId);

}
