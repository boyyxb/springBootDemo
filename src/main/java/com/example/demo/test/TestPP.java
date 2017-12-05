package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ben.TestBen;
import com.example.demo.redis.RedisServcie;
import com.example.demo.service.TestBenservice;

@RestController
public class TestPP {
	
	@Autowired
	TestBenservice testBenservice;
	@Autowired
	RedisServcie redisServcie;
	
	@RequestMapping("/test")
	public String test(){
		redisServcie.set("abc", 123456+"");
		redisServcie.set("bbc", "88889999",1000L);
		return "设置redis 成功 ";
	}
	@RequestMapping("/test1")
	public String test1(){
		String a = String.valueOf(redisServcie.get("abc"));
		String b = String.valueOf(redisServcie.get("bbc"));
		return "取出成功。 a--"+a+"----b--"+b;
	}
	@RequestMapping("/test2")
	public String test2(){
		redisServcie.remove("abc");
		
		return "删除redis成功";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/authorize")
	public String authorize(){
		return "有权限访问";
	}
}
