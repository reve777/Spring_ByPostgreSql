package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM \"user\" u WHERE "
	        + "(:userName IS NULL OR u.user_name = :userName) AND "
	        + "(:department IS NULL OR u.department = :department) AND "
	        + "(:user_groups IS NULL OR u.user_groups = :user_groups) AND "
	        + "(:status IS NULL OR u.status = :status) AND "
	        + "(:joiningDate IS NULL OR u.joining_date = :joiningDate)", nativeQuery = true)
//	@Query(value = "SELECT * FROM user u WHERE "
//	        + "(:userName IS NULL OR u.user_name = :userName) AND "
//	        + "(:department IS NULL OR u.department = :department) AND "
//	        + "(:user_groups IS NULL OR u.user_groups = :user_groups) AND "
//	        + "(:status IS NULL OR u.status = :status) AND "
//	        + "(:joiningDate IS NULL OR u.joining_date = :joiningDate)", nativeQuery = true)
	List<User> searchUsers(@Param("userName") String userName, 
	                       @Param("department") String department, 
	                       @Param("user_groups") String user_groups, 
	                       @Param("status") String status, 
	                       @Param("joiningDate") String joiningDate);

    Optional<User> findByUserName(String userName);
}


