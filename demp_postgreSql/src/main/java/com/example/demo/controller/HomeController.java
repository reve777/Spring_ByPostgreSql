package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "loginpage";
	}

	@RequestMapping("/userdetail")
	public String userdetail() {
		return "userdetail";
	}
	@RequestMapping("/fail")
	@ResponseBody
	public String fail() {
		return "Login fail(帳號或密碼錯誤)";
	}
	
	@RequestMapping("/loginerror")
	@ResponseBody
	public String error() {
		return "Login error(帳號已停用)";
	}

}
