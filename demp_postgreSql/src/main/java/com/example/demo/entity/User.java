package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//在 PostgreSQL 中，user 是一個保留字，需要使用雙引號來標識這個表名
@Entity
//@Table(name ="User")//MySQL 不需要可使用
@Table(name = "\"user\"")
public class User {
	/**
	 * 流水號:Id
	 * 帳號:Account
	 * 密碼:Password
	 * 姓名:Name
	 * 生日:Birthday
	 * 部門:Department
	 * 組別:Groups
	 * 狀態:Status
	 * (帳號)生效日期:EffectiveDate
	 * (帳號) 停用日期:DeactivationDate
	 * 入職日期:JoiningDate
	 * 建立日期:CreationDate
	 * 修改日期:ModificationDate
	 * Memo:Memo
	 */
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
	private Integer id;
	@Column(name = "user_name", nullable = false)
	private String userName;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String birthday;
	@Column
	private String department;
	@Column
	private String user_groups;
	@Column
	private String status;
	@Column
	private String effectiveDate;
	@Column
	private String deactivationDate;
	@Column
	private String joiningDate;
	@Column
	private String creationDate;
	@Column
	private String modificationDate;
	@Column
	private String memo;
	@Column
	private boolean active;
	@Column
	private String roles;
	
	public User() {
		super();
	}
	
	public User(int id, String userName, String password, String name, String birthday, String department,
			String user_groups, String status, String effectiveDate, String deactivationDate, String joiningDate,
			String creationDate, String modificationDate, String memo) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.department = department;
		this.user_groups = user_groups;
		this.status = status;
		this.effectiveDate = effectiveDate;
		this.deactivationDate = deactivationDate;
		this.joiningDate = joiningDate;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.memo = memo;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUser_groups() {
		return user_groups;
	}

	public void setUser_groups(String user_groups) {
		this.user_groups = user_groups;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getDeactivationDate() {
		return deactivationDate;
	}
	public void setDeactivationDate(String deactivationDate) {
		this.deactivationDate = deactivationDate;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name + ", birthday="
				+ birthday + ", department=" + department + ", user_groups=" + user_groups + ", status=" + status
				+ ", effectiveDate=" + effectiveDate + ", deactivationDate=" + deactivationDate + ", joiningDate="
				+ joiningDate + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + ", memo="
				+ memo + ", active=" + active + ", roles=" + roles + "]";
	}

	
	
}
