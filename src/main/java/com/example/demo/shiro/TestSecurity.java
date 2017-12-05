package com.example.demo.shiro;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurity {

	@RequestMapping("/securityTest")
	public String securityTest(){
		
		return "听说是安全框架，我怎么不信呢。";
	}
}
