package com.gjw.blog.domain;

import com.github.rjeschke.txtmark.Processor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Blog 实体
 * 
 * @since 2018年11月25日
 * @author gjw199513
 */
@Entity // 实体
public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识
	
	@NotEmpty(message = "标题不能为空")
	@Size(min=2, max=50)
	@Column(nullable = false, length = 50) // 映射为字段，值不能为空
	private String title;
	
	@NotEmpty(message = "摘要不能为空")
	@Size(min=2, max=300)
	@Column(nullable = false) // 映射为字段，值不能为空
	private String summary;

	@Lob  // 大对象，映射 MySQL 的 Long Text 类型
	@Basic(fetch=FetchType.LAZY) // 懒加载
	@NotEmpty(message = "内容不能为空")
	@Size(min=2)
	@Column(nullable = false) // 映射为字段，值不能为空
	private String content;
	
	@Lob  // 大对象，映射 MySQL 的 Long Text 类型
	@Basic(fetch=FetchType.LAZY) // 懒加载
	@NotEmpty(message = "内容不能为空")
	@Size(min=2)
	@Column(nullable = false) // 映射为字段，值不能为空
	private String htmlContent; // 将 md 转为 html
 	
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(nullable = false) // 映射为字段，值不能为空
	@org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
	private Timestamp createTime;

	@Column(name="readSize")
	private Integer readSize = 0; // 访问量、阅读量
	 
	@Column(name="commentSize")
	private Integer commentSize = 0;  // 评论量

	@Column(name="likeSize")
	private Integer likeSize = 0;  // 点赞量
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "blog_comment", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
	private List<Comment> comments;
	
	protected Blog() {
		// TODO Auto-generated constructor stub
	}
	public Blog(String title, String summary,String content) {
		this.title = title;
		this.summary = summary;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.htmlContent = Processor.process(content);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
 
	public Timestamp getCreateTime() {
		return createTime;
	}
	
	public String getHtmlContent() {
		return htmlContent;
	}
	public Integer getReadSize() {
		return readSize;
	}
	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}
	public Integer getCommentSize() {
		return commentSize;
	}
	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}
	public Integer getLikeSize() {
		return likeSize;
	}
	public void setLikeSize(Integer likeSize) {
		this.likeSize = likeSize;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
		this.commentSize = this.comments.size();
	}
 
	/**
	 * 添加评论
	 * @param comment
	 */
	public void addComment(Comment comment) {
		this.comments.add(comment);
		this.commentSize = this.comments.size();
	}
	/**
	 * 删除评论
	 * @param commentId
	 */
	public void removeComment(Long commentId) {
		for (int index=0; index < this.comments.size(); index ++ ) {
			if (comments.get(index).getId().equals(commentId)) {
				this.comments.remove(index);
				break;
			}
		}
		
		this.commentSize = this.comments.size();
	}
	 
}