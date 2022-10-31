package com.springboot.demoredis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demoredis.entity.Student;
import com.springboot.demoredis.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
	private StudentRepository studentRepository;

    @PostMapping("")
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		try {
            Student savedStudent = studentRepository.save(student);
		    return new ResponseEntity<>(savedStudent, HttpStatus.OK);
        } catch(Exception err) {
            err.printStackTrace();
            return null;
        }
	}

    @GetMapping("")
	public ResponseEntity<?> getStudents() {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(name = "id") String id, @RequestBody Student student) {
		Student std = studentRepository.findById(id).get();
		if (std != null) {
			std.setGrade(student.getGrade());
			std.setName(student.getName());
			Student updatedStudent = studentRepository.save(std);
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		}
		return null;
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") String id) {
		studentRepository.deleteById(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}
}