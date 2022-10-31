package com.springboot.demoredis.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.demoredis.entity.Student;


public interface StudentRepository extends CrudRepository<Student, String> {
}