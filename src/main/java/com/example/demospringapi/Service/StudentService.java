package com.example.demospringapi.Service;

import com.example.demospringapi.Repo.StudentRepo;
import com.example.demospringapi.dto.StudentDTO;
import com.example.demospringapi.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Student not found for this id :: " + id));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setEmail(studentDetails.getEmail());
        student.setClassRoom(studentDetails.getClassRoom());

        final Student updatedStudent = studentRepo.save(student);
        return updatedStudent;
    }
    public StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setAge(student.getAge());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setClassName(student.getClassRoom().getClass_name());
        studentDTO.setClassId(student.getClassRoom().getId_class());

        return studentDTO;
    }

}
