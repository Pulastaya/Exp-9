package com.AIML2A.Rest_Api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AIML2A.Rest_Api.model.Student;
import com.AIML2A.Rest_Api.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }

    // ✅ FIXED UPDATE (NO ID MODIFICATION)
    public Student updateStudent(int id, Student updatedStudent) {
        Optional<Student> existing = repository.findById(id);

        if (existing.isPresent()) {
            Student student = existing.get();

            // ONLY update allowed fields
            student.setName(updatedStudent.getName());
            student.setCourse(updatedStudent.getCourse());

            return repository.save(student);
        } else {
            return null;
        }
    }

    // ✅ DELETE
    public boolean deleteStudent(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}