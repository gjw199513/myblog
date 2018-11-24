package com.gjw.blog.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * 权限.
 * 
 * @since 1.0.0 2018年11月24日
 * @author gjw19913
 */
@Entity // 实体
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 2767199123459463035L;

	/**
	 * 用户的唯一标识
	 */
	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id;

    /**
     * 权限名称
     */
	@Column(nullable = false) // 映射为字段，值不能为空
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    /**
     *  重写获取权限方法，返回name值
     */
	@Override
	public String getAuthority() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
