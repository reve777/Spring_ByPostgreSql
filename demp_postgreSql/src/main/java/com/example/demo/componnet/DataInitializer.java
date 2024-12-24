package com.example.demo.componnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.annotation.PostConstruct;

/**
 * 初始第一筆登入資料
 */
@Component
public class DataInitializer {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	@Transactional
	public void initData() {

		if (userRepository.count() == 0) {
			User user = new User();
			user.setActive(true);
			user.setBirthday("2024-12-12");
			user.setCreationDate("2024-12-19");
			user.setDeactivationDate(null);
			user.setDepartment("A部門");
			user.setEffectiveDate("2024-12-20");
			user.setJoiningDate("2024-12-20");
			user.setMemo("我是備註");
			user.setModificationDate("2024-12-19");
			user.setName("管理者");
//        user.setPassword("$2a$10$Hc4xQTf2ygkiUQp2POoD4.Qd59I3/gfAn1..u7IStBQMsSRSlriJ2"); // 1234
			user.setPassword(passwordEncoder.encode("Aa1234"));// Aa1234
			user.setRoles("MANAGER");
			user.setStatus("啟用");
			user.setUserName("Aa01");
			user.setUser_groups("a群組");
			userRepository.save(user);
		}
	}
}
