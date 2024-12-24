package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
    public Object Add(@RequestBody Map<String, String> map) {
        return userService.add(map);
    }


	// 修改
	@PutMapping(value = { "/{id}", "/update/{id}" })
	@Transactional
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, String> map) {
		return userService.update(id, map);
	}

	// 刪除
	@DeleteMapping(value = { "/{id}", "/delete/{id}" })
	public Boolean Delete(@PathVariable("id") Integer id) {
		return userService.delete(id);
	}

	// 查詢
	@GetMapping(value = { "/{id}", "/get/{id}" })
	public Object get(@PathVariable("id") Integer id) {
		return userService.get(id);
	}

	// 條件查詢查詢 or 查詢全部
	@GetMapping("/")
	public ResponseEntity<?> getSearch(@RequestParam(required = false) String searchAccount,
			@RequestParam(required = false) String searchDepartment,
			@RequestParam(required = false) String searchGroups, @RequestParam(required = false) String searchStatus,
			@RequestParam(required = false) String searchJoiningDate) {
		if (searchDepartment != null && searchDepartment.trim().isEmpty()) {
			searchDepartment = null;
		}
		if (searchAccount != null && searchAccount.trim().isEmpty()) {
			searchAccount = null;
		}
		if (searchGroups != null && searchGroups.trim().isEmpty()) {
			searchGroups = null;
		}
		if (searchStatus != null && searchStatus.trim().isEmpty()) {
			searchStatus = null;
		}
		if (searchJoiningDate != null && searchJoiningDate.trim().isEmpty()) {
			searchJoiningDate = null;
		}
//		List<User> searchResults = new ArrayList<>();
		List<User> searchResults = userService.searchUsers(searchAccount, searchDepartment, searchGroups,
				searchStatus, searchJoiningDate);

		if (searchAccount == null && searchDepartment == null && searchGroups == null && searchJoiningDate == null
				&& searchStatus == null) {
			return ResponseEntity.ok(userService.getAll());
		} else {
			return ResponseEntity.ok(searchResults);
		}
	}

	// 查詢已刪除的
	@GetMapping("/getDelete")
	public Object getDelete() {
		return userService.getDeleteAll();

	}

}
