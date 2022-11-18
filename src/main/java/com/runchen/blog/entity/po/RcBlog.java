package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 博客表
 */
public class RcBlog extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 博客标题 */
	private String title;
	/** 路径url */
	private String url;
	/** 博客封面图 */
	private String coverImage;
	/** 博客内容 */
	private String blogContent;
	/** 博客分类id */
	private Long categoryId;
	/** 发布转态：0-未发布、1-已发布 */
	private Boolean releaseStatus;
	/** 评论开关：0-允许评论 1-不允许评论 */
	private Boolean commentLock;
	/** 作者 */
	private String bloggerName;

	public Map<String, Object> only() {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.hasLength(this.getUrl())) {
			map.put("url", this.getUrl());
		}
		return map;
	}

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getTitle).to(title -> { if (StringUtils.hasLength(title)) map.put("title", title); });
		mapper.from(this::getUrl).to(url -> { if (StringUtils.hasLength(url)) map.put("url", url); });
		mapper.from(this::getCoverImage).to(coverImage -> { if (StringUtils.hasLength(coverImage)) map.put("coverImage", coverImage); });
		mapper.from(this::getBlogContent).to(blogContent -> { if (StringUtils.hasLength(blogContent)) map.put("blogContent", blogContent); });
		mapper.from(this::getCategoryId).to(categoryId -> { if (categoryId > 0) map.put("categoryId", categoryId); });
		mapper.from(this::getReleaseStatus).to(releaseStatus -> map.put("releaseStatus", releaseStatus));
		mapper.from(this::getCommentLock).to(commentLock -> map.put("commentLock", commentLock));
		mapper.from(this::getBloggerName).to(bloggerName -> { if (StringUtils.hasLength(bloggerName)) map.put("bloggerName", bloggerName); });
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public RcBlog setTitle(String title) {
		this.title = title;
        return this;
	}

	public String getTitle() {
		return this.title;
	}

	public RcBlog setUrl(String url) {
		this.url = url;
        return this;
	}

	public String getUrl() {
		return this.url;
	}

	public RcBlog setCoverImage(String coverImage) {
		this.coverImage = coverImage;
        return this;
	}

	public String getCoverImage() {
		return this.coverImage;
	}

	public RcBlog setBlogContent(String blogContent) {
		this.blogContent = blogContent;
        return this;
	}

	public String getBlogContent() {
		return this.blogContent;
	}

	public RcBlog setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
        return this;
	}

	public Long getCategoryId() {
		return this.categoryId;
	}

	public RcBlog setReleaseStatus(Boolean releaseStatus) {
		this.releaseStatus = releaseStatus;
        return this;
	}

	public Boolean getReleaseStatus() {
		return this.releaseStatus;
	}

	public RcBlog setCommentLock(Boolean commentLock) {
		this.commentLock = commentLock;
        return this;
	}

	public Boolean getCommentLock() {
		return this.commentLock;
	}

	public RcBlog setBloggerName(String bloggerName) {
		this.bloggerName = bloggerName;
        return this;
	}

	public String getBloggerName() {
		return this.bloggerName;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        RcBlog bean = (RcBlog) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(title, bean.title)
        	&& Objects.equals(url, bean.url)
        	&& Objects.equals(coverImage, bean.coverImage)
        	&& Objects.equals(blogContent, bean.blogContent)
        	&& Objects.equals(categoryId, bean.categoryId)
        	&& Objects.equals(releaseStatus, bean.releaseStatus)
        	&& Objects.equals(commentLock, bean.commentLock)
        	&& Objects.equals(bloggerName, bean.bloggerName)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, title, url, coverImage, blogContent, categoryId, releaseStatus, commentLock, bloggerName, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "RcBlogs{" +
				"id=" + id +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", coverImage='" + coverImage + '\'' +
				", blogContent='" + blogContent + '\'' +
				", categoryId=" + categoryId +
				", releaseStatus=" + releaseStatus +
				", commentLock=" + commentLock +
				", bloggerName='" + bloggerName + '\'' +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
