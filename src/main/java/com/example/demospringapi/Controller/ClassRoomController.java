package com.example.demospringapi.Controller;

import com.example.demospringapi.Service.ClassRoomService;
import com.example.demospringapi.entity.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@CrossOrigin(origins = "http://localhost:3000")

public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("")
    public List<ClassRoom> getAllClassRooms() {
        return classRoomService.getAllClassRooms();
    }

    @GetMapping("/{id}")
    public ClassRoom getClassRoomById(@PathVariable Integer id) {
        return classRoomService.getClassRoomById(id).orElse(null);
    }
    @PostMapping("")
    public ResponseEntity<?> createClassRoom(@RequestBody ClassRoom classRoom) {
        if (classRoomService.existsByClassName(classRoom.getClass_name())) {
            return ResponseEntity.badRequest().body("A class with this name already exists.");
        }
        return ResponseEntity.ok(classRoomService.saveClassRoom(classRoom));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClassRoom(@PathVariable Integer id, @RequestBody ClassRoom classRoom) {
        if (classRoomService.existsByClassName(classRoom.getClass_name())) {
            return ResponseEntity.badRequest().body("Class name already exists");
        }
        classRoom.setId_class(id);
        ClassRoom updatedClassRoom = classRoomService.saveClassRoom(classRoom);
        classRoomService.updateNumberMember(updatedClassRoom.getId_class());
        return ResponseEntity.ok(updatedClassRoom);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassRoom(@PathVariable Integer id) {
        ClassRoom classRoom = classRoomService.getClassRoomById(id).orElse(null);
        if (classRoom != null) {
            if (!classRoom.getStudents().isEmpty()) {
                return ResponseEntity.badRequest().body("Cannot delete class with students");
        } else {
            classRoomService.deleteClassRoom(id);
            return ResponseEntity.ok("Classroom deleted");
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
