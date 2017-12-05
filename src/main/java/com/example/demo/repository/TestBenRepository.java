package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ben.TestBen;

@Repository
public interface TestBenRepository extends JpaRepository<TestBen, Long>  {

}
