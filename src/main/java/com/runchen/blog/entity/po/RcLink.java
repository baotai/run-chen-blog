package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 友链表
 */
public class RcLink extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 友链类别 友链 推荐 个人网站 */
	private String type;
	/** 网站名称 */
	private String name;
	/** 网站链接 */
	private String url;
	/** 网站描述 */
	private String linkDesc;
	/** 友链权重 */
	private Integer rank;


	public Map<String, Object> only() {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.hasLength(this.getName())) {
			map.put("name", this.getName());
		}
		if (StringUtils.hasLength(this.getType())) {
			map.put("type", this.getType());
		}
		if (StringUtils.hasLength(this.getUrl())) {
			map.put("url", this.getUrl());
		}
		return map;
	}

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getName).to(name -> { if (StringUtils.hasLength(name)) map.put("name", name); });
		mapper.from(this::getType).to(type -> { if (StringUtils.hasLength(type)) map.put("type", type); });
		mapper.from(this::getUrl).to(url -> { if (StringUtils.hasLength(url)) map.put("url", url); });
		mapper.from(this::getLinkDesc).to(linkDesc -> { if (StringUtils.hasLength(linkDesc)) map.put("linkDesc", linkDesc); });
		mapper.from(this::getRank).to(rank -> map.put("rank", rank));
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public RcLink setType(String type) {
		this.type = type;
        return this;
	}

	public String getType() {
		return this.type;
	}

	public RcLink setName(String name) {
		this.name = name;
        return this;
	}

	public String getName() {
		return this.name;
	}

	public RcLink setUrl(String url) {
		this.url = url;
        return this;
	}

	public String getUrl() {
		return this.url;
	}

	public RcLink setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
        return this;
	}

	public String getLinkDesc() {
		return this.linkDesc;
	}

	public RcLink setRank(Integer rank) {
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
        RcLink bean = (RcLink) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(type, bean.type)
        	&& Objects.equals(name, bean.name)
        	&& Objects.equals(url, bean.url)
        	&& Objects.equals(linkDesc, bean.linkDesc)
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
        return Objects.hash( id, type, name, url, linkDesc, rank, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "RcLink{" +
				"id=" + id +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", linkDesc='" + linkDesc + '\'' +
				", rank=" + rank +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
