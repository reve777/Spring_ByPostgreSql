package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DeleteUserDetail;
import com.example.demo.entity.User;
import com.example.demo.repository.DeleteUserRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DeleteUserRepository deleteuserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public List<DeleteUserDetail> getDeleteAll() {
		return deleteuserRepository.findAll();
	}

	/** By ID */
	public User get(Integer id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	/**
	 * 查詢條件：帳號account、部門department、 組別groups、狀態status、入職時間joiningdate
	 */
	public List<User> searchUsers(String account, String department, String groups, String status, String joiningDate) {
		return userRepository.searchUsers(account, department, groups, status, joiningDate);

	}

	/** 新增 */
	public Object add(Map<String, String> map) {
		LocalDateTime now = LocalDateTime.now();

		try {
			boolean eqUserName = getAll().stream().anyMatch(user -> user.getUserName().equals(map.get("account")));
			if (eqUserName) {
				throw new IllegalArgumentException("帳號已存在，無法新增");
			}

			User user = new User();
			user.setUserName(map.get("account"));
			user.setPassword(passwordEncoder.encode(map.get("password")));
			user.setName(map.get("name"));
			user.setBirthday(map.get("birthday"));
			user.setDepartment(map.get("department"));
			user.setUser_groups(map.get("groups"));
			user.setStatus(map.get("status"));

			if ("停用".equals(map.get("status"))) {
				user.setDeactivationDate(now.format(formatter));
				user.setActive(false);
			} else {
				user.setDeactivationDate(null);
				user.setActive(true);
			}

			user.setEffectiveDate(map.get("effectiveDate"));
			user.setJoiningDate(map.get("joiningDate"));
			user.setCreationDate(now.format(formatter));
			user.setModificationDate(now.format(formatter));
			user.setMemo(map.get("memo"));
			user.setRoles("MANAGER");

			user = userRepository.saveAndFlush(user);
			return user;

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("message", "新增使用者時發生錯誤：" + e.getMessage()));
		}
	}

	/** 刪除 */
	public Boolean delete(Integer id) {
		try {
			User user = get(id);
			DeleteUserDetail deleteUserDetail = new DeleteUserDetail(user);
			deleteuserRepository.save(deleteUserDetail);

			userRepository.deleteById(id);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/** 修改 */
	public Object update(Integer id, Map<String, String> map) {
	    LocalDateTime now = LocalDateTime.now();
	    
	    try {
	        final User user = get(id); 
	        List<User> allUsers = getAll();
	        
	        boolean eqUserName = allUsers.stream()
	                .filter(u -> !u.getUserName().equals(user.getUserName()))
	                .anyMatch(u -> u.getUserName().equals(map.get("account")));

	        if (eqUserName) {
	            throw new IllegalArgumentException("帳號已存在，無法修改");
	        }

	        user.setUserName(map.get("account"));
	        user.setPassword(passwordEncoder.encode(map.get("password")));
	        user.setName(map.get("name"));
	        user.setDepartment(map.get("department"));
	        user.setUser_groups(map.get("groups"));
	        user.setStatus(map.get("status"));
	        user.setEffectiveDate(map.get("effectiveDate"));
	        
	        if (map.get("status").equals("停用")) {
	            user.setDeactivationDate(now.format(formatter));
	            user.setActive(false);
	        } else {
	            user.setDeactivationDate(null);
	            user.setActive(true);
	        }
	        
	        user.setJoiningDate(map.get("joiningDate"));
	        user.setCreationDate(map.get("creationDate"));
	        user.setModificationDate(now.format(formatter));
	        user.setMemo(map.get("memo"));

	        return userRepository.saveAndFlush(user);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("message", "修改使用者時發生錯誤：" + e.getMessage()));
	    }
	}



}
