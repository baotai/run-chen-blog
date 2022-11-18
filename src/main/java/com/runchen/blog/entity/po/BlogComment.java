package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 博客评论表
 */
public class BlogComment extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 上一级评论ID */
	private Long parentId;
	/** 博文id */
	private Long blogId;
	/** 评论内容 */
	private String content;
	/** 评论时的ip地址 */
	private String ipAddress;
	/** 是否审核通过 0-未审核 1-审核通过 */
	private Boolean status;

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getParentId).to(parentId -> { if (parentId > 0) map.put("parentId", parentId); });
		mapper.from(this::getBlogId).to(blogId -> { if (blogId > 0) map.put("blogId", blogId); });
		mapper.from(this::getContent).to(content -> { if (StringUtils.hasLength(content)) map.put("content", content); });
		mapper.from(this::getIpAddress).to(ipAddress -> { if (StringUtils.hasLength(ipAddress)) map.put("ipAddress", ipAddress); });
		mapper.from(this::getStatus).to(status -> map.put("status", status));
		mapper.from(getDeleted()).to(delete -> map.put("delete", delete));

		return map;
	}

	public BlogComment setParentId(Long parentId) {
		this.parentId = parentId;
        return this;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public BlogComment setBlogId(Long blogId) {
		this.blogId = blogId;
        return this;
	}

	public Long getBlogId() {
		return this.blogId;
	}

	public BlogComment setContent(String content) {
		this.content = content;
        return this;
	}

	public String getContent() {
		return this.content;
	}

	public BlogComment setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
        return this;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public BlogComment setStatus(Boolean status) {
		this.status = status;
        return this;
	}

	public Boolean getStatus() {
		return this.status;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        BlogComment bean = (BlogComment) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(parentId, bean.parentId)
        	&& Objects.equals(blogId, bean.blogId)
        	&& Objects.equals(content, bean.content)
        	&& Objects.equals(ipAddress, bean.ipAddress)
        	&& Objects.equals(status, bean.status)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, parentId, blogId, content, ipAddress, status, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "BlogComment{" +
				"id=" + id +
				", parentId=" + parentId +
				", blogId=" + blogId +
				", content='" + content + '\'' +
				", ipAddress='" + ipAddress + '\'' +
				", status=" + status +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
