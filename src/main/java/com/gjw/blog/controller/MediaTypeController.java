package com.gjw.blog.controller;

import com.gjw.blog.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Media Type 控制器.
 * 
 * @author gjw
 * @date 2018年11月24日
 */
@RestController
public class MediaTypeController {

	/**
	 * 根据客户端请求的 Content-Type，响应相应的 UserVO 类型.
	 * 
	 * @return
	 */
	@RequestMapping("/user")
	public User getUser() {
		return new User("waylau", 30);
	}

}
