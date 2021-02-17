package com.example.demo.controller;

import com.example.demo.domain.Student;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StudentController {

	final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/student/{id}")
	public Student findStudentById(@PathVariable String id) {
		log.info("Searching by ID  : " + id);
		return studentService.getStudentByID(id);
	}
}
