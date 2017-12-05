package com.example.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ben.TestBen;
import com.example.demo.repository.TestBenRepository;
import com.example.demo.service.TestBenservice;

@Service
public class TestBenServiceImpl implements TestBenservice {

	@Autowired
	private TestBenRepository testBenRepository;
	
	@Override
	public void add(TestBen test) {
		// TODO Auto-generated method stub
		//System.out.println("999");
		this.testBenRepository.save(test);
	}

}
