package com.gjw.blog.controller;

import com.gjw.blog.domain.User;
import com.gjw.blog.repository.UserRepository;
import com.gjw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户控制器.
 *
 * @author gjw
 * @date 2018年11月24日
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    /**
     * 查询所用用户
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param model
     * @return
     */
	@GetMapping
	public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {

		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<User> page = userService.listUsersByNameLike(name, pageable);
		// 当前所在页面数据列表
		List<User> list = page.getContent();

		model.addAttribute("page", page);
		model.addAttribute("userList", list);
		return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
	}

	/**
	 * 获取 form 表单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User(null, null, null, null,null));
		return new ModelAndView("users/add", "userModel", model);
	}

//	/**
//	 * 新建用户
//	 * @param user
//	 * @param result
//	 * @param redirect
//	 * @return
//	 */
//	@PostMapping
//	public ResponseEntity<Response> create(User user, Long authorityId) {
//		List<Authority> authorities = new ArrayList<>();
//		authorities.add(authorityService.getAuthorityById(authorityId));
//		user.setAuthorities(authorities);
//
//		if(user.getId() == null) {
//			user.setEncodePassword(user.getPassword()); // 加密密码
//		}else {
//			// 判断密码是否做了变更
//			User originalUser = userService.getUserById(user.getId());
//			String rawPassword = originalUser.getPassword();
//			PasswordEncoder encoder = new BCryptPasswordEncoder();
//			String encodePasswd = encoder.encode(user.getPassword());
//			boolean isMatch = encoder.matches(rawPassword, encodePasswd);
//			if (!isMatch) {
//				user.setEncodePassword(user.getPassword());
//			}else {
//				user.setPassword(user.getPassword());
//			}
//		}
//
//		try {
//			userService.saveUser(user);
//		}  catch (ConstraintViolationException e)  {
//			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
//		}
//
//		return ResponseEntity.ok().body(new Response(true, "处理成功", user));
//	}


    /**
     * 根据id查询用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }


    /**
     * 新建用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public ModelAndView create(User user) {
        userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Model model) {
        userRepository.delete(id);
//        model.addAttribute("userList", getUserlist());
        model.addAttribute("title", "删除用户");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 修改用户
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/form", "userModel", model);
    }

}
