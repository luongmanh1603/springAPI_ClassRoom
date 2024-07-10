package com.example.demospringapi.Controller;

import com.example.demospringapi.Service.ClassRoomService;
import com.example.demospringapi.Service.StudentService;
import com.example.demospringapi.dto.StudentDTO;
import com.example.demospringapi.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("")
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(studentService.convertToDTO(student));
        }
        return studentDTOs;
    }
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return studentService.convertToDTO(student);
    }
    @PostMapping("")
    public Student createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        classRoomService.updateNumberMember(savedStudent.getClassRoom().getId_class());
        return studentService.saveStudent(student);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student existingStudent = studentService.getStudentById(id).orElse(null);
        if (existingStudent == null) {
            return ResponseEntity.notFound().build();
        }

        Integer oldClassId = existingStudent.getClassRoom().getId_class();
        Integer newClassId = studentDetails.getClassRoom().getId_class();

        Student updatedStudent = studentService.updateStudent(id, studentDetails);

        if (!oldClassId.equals(newClassId)) {
            // If the student has moved to a new class, update the number_member for both the old and new class
            classRoomService.updateNumberMember(oldClassId);
            classRoomService.updateNumberMember(newClassId);
        } else {
            // If the student has not moved classes, just update the number_member for their class
            classRoomService.updateNumberMember(oldClassId);
        }

        return ResponseEntity.ok(studentService.convertToDTO(updatedStudent));
    }



}
