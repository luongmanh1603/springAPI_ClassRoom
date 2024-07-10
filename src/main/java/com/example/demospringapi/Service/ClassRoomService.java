package com.example.demospringapi.Service;

import com.example.demospringapi.Repo.ClassRoomRepo;
import com.example.demospringapi.Repo.StudentRepo;
import com.example.demospringapi.entity.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {
    @Autowired
    private ClassRoomRepo classRoomRepository;
    @Autowired
    private StudentRepo studentRepository;

    public List<ClassRoom> getAllClassRooms() {
        return classRoomRepository.findAll();
    }
    public Optional<ClassRoom> getClassRoomById(Integer id) {
        return classRoomRepository.findById(id);
    }
    public ClassRoom saveClassRoom(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }
    public void deleteClassRoom(Integer id) {
        classRoomRepository.deleteById(id);
    }
    public boolean existsByClassName(String className) {
        return classRoomRepository.existsByClass_name(className);
    }
    public void  updateNumberMember(Integer id) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElse(null);
       int count = studentRepository.countByClassRoomId(id);
       classRoom.setNumber_member(count);
         classRoomRepository.save(classRoom);
        }
    }



