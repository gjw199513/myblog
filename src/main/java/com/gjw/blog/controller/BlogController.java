package com.gjw.blog.controller;

import com.gjw.blog.domain.User;
import com.gjw.blog.domain.es.EsBlog;
import com.gjw.blog.service.EsBlogService;
import com.gjw.blog.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Blog控制器.
 *
 * @author gjw
 * @since 1.0.0 2018年11月24日
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

//    @GetMapping
//    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order,
//                            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
//        System.out.println("order:" + order + ";keyword:" + keyword);
//        return "redirect:/index?order=" + order + "&keyword=" + keyword;
//    }

    @Autowired
    private EsBlogService esBlogService;

	@GetMapping
	public String listEsBlogs(
			@RequestParam(value="order",required=false,defaultValue="new") String order,
			@RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
			@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			Model model) {

		Page<EsBlog> page = null;
		List<EsBlog> list = null;
		boolean isEmpty = true; // 系统初始化时，没有博客数据
		try {
			if (order.equals("hot")) { // 最热查询
				Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize","createTime");
				Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
				page = esBlogService.listHotestEsBlogs(keyword, pageable);
			} else if (order.equals("new")) { // 最新查询
				Sort sort = new Sort(Sort.Direction.DESC,"createTime");
				Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
				page = esBlogService.listNewestEsBlogs(keyword, pageable);
			}

			isEmpty = false;
		} catch (Exception e) {
			Pageable pageable = new PageRequest(pageIndex, pageSize);
			page = esBlogService.listEsBlogs(pageable);
		}

        // 当前所在页面数据列表
		list = page.getContent();


		model.addAttribute("order", order);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		model.addAttribute("blogList", list);

		// 首次访问页面才加载
		if (!async && !isEmpty) {
			List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
			model.addAttribute("newest", newest);
			List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
			model.addAttribute("hotest", hotest);
			List<TagVO> tags = esBlogService.listTop30Tags();
			model.addAttribute("tags", tags);
			List<User> users = esBlogService.listTop12Users();
			model.addAttribute("users", users);
		}

		return (async==true?"/index :: #mainContainerRepleace":"/index");
	}

	@GetMapping("/newest")
	public String listNewestEsBlogs(Model model) {
		List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
		model.addAttribute("newest", newest);
		return "newest";
	}

	@GetMapping("/hotest")
	public String listHotestEsBlogs(Model model) {
		List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
		model.addAttribute("hotest", hotest);
		return "hotest";
	}

}
