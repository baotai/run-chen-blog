package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 博客分类表
 */
public class BlogCategory extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 分类名称 */
	private String name;
	/** 分类描述 */
	private String categoryDesc;
	/** 分类的图标 */
	private String icon;
	/** 分类权重 */
	private Integer rank;

	public Map<String, Object> only() {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.hasLength(this.getName())) {
			map.put("name", this.getName());
		}
		return map;
	}

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getName).to(name -> { if (StringUtils.hasLength(name)) map.put("name", name); });
		mapper.from(this::getCategoryDesc)
				.to(categoryDesc -> { if (StringUtils.hasLength(categoryDesc)) map.put("categoryDesc", categoryDesc); });
		mapper.from(this::getIcon).to(icon -> { if (StringUtils.hasLength(icon)) map.put("icon", icon); });
		mapper.from(this::getRank).to(rank -> map.put("rank", rank));
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public BlogCategory setName(String name) {
		this.name = name;
        return this;
	}

	public String getName() {
		return this.name;
	}

	public BlogCategory setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
        return this;
	}

	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	public BlogCategory setIcon(String icon) {
		this.icon = icon;
        return this;
	}

	public String getIcon() {
		return this.icon;
	}

	public BlogCategory setRank(Integer rank) {
		this.rank = rank;
        return this;
	}

	public Integer getRank() {
		return this.rank;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        BlogCategory bean = (BlogCategory) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(name, bean.name)
        	&& Objects.equals(categoryDesc, bean.categoryDesc)
        	&& Objects.equals(icon, bean.icon)
        	&& Objects.equals(rank, bean.rank)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, categoryDesc, icon, rank, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "BlogCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", categoryDesc='" + categoryDesc + '\'' +
				", icon='" + icon + '\'' +
				", rank=" + rank +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
