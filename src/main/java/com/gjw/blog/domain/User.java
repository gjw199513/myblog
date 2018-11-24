package com.gjw.blog.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * User 实体
 * 
 * @since 1.0.0 2018年11月24日
 * @author gjw
 */
@Entity  // 实体
@XmlRootElement // MediaType 转为 XML
public class User implements Serializable{
 
	private static final long serialVersionUID = 1L;

	// 用户的唯一标识
	@Id  // 主键
    @GeneratedValue(strategy=GenerationType.IDENTITY) // 自增长策略
	private Long id;

	@NotEmpty(message = "姓名不能为空")
	@Size(max = 50)
	@Column(nullable = false,length = 20)
	private String name;

	@NotEmpty(message = "邮箱不能为空")
	@Size(max=50)
	@Email(message = "邮箱格式不正确")
	@Column(nullable = false,length = 50,unique = true)
	private String email;

	@NotEmpty(message = "账号不能为空")
	@Size(min=3, max=20)
	@Column(nullable = false,length = 20,unique = true)
	private String username;

	@NotEmpty(message = "密码不能为空")
	@Size(max=100)
	@Column(length = 100)
	private String password;

	// 头像
	@Column(length = 200)
	private String avatar;


	protected User() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用 
	}

	public User(String name, String email, String username, String password, String avatar) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", avatar='" + avatar + '\'' +
				'}';
	}
}
