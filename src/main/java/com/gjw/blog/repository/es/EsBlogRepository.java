package com.gjw.blog.repository.es;

import com.gjw.blog.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Blog 存储库.
 * 
 * @since 1.0.0 2018年11月26日
 * @author gjw199513
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
 
	/**
	 * 模糊查询(去重)
	 * @param title
	 * @param Summary
	 * @param content
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title, String Summary, String content, String tags, Pageable pageable);

	/**
	 * 根据Blog的id查询EsBlog
	 * @param blogId
	 * @return
	 */
	EsBlog findByBlogId(Long blogId);
}
