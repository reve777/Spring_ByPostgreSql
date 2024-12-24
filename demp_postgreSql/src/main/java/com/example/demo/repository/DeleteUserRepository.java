package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DeleteUserDetail;

@Repository
public interface DeleteUserRepository extends JpaRepository<DeleteUserDetail, Integer> {

}
