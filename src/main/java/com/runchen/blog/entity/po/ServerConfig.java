package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 服务自身配置
 */
public class ServerConfig extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 配置项Key */
	private String key;
	/** 配置项值 */
	private String value;
	/** 注释 */
	private String comment;


	public Map<String, Object> only() {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.hasLength(this.getKey())) {
			map.put("key", this.getKey());
		}
		return map;
	}

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getKey).to(key -> { if (StringUtils.hasLength(key)) map.put("key", key); });
		mapper.from(this::getValue).to(value -> { if (StringUtils.hasLength(value)) map.put("value", value); });
		mapper.from(this::getComment).to(comment -> { if (StringUtils.hasLength(comment)) map.put("comment", comment); });
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public ServerConfig setKey(String key) {
		this.key = key;
        return this;
	}

	public String getKey() {
		return this.key;
	}

	public ServerConfig setValue(String value) {
		this.value = value;
        return this;
	}

	public String getValue() {
		return this.value;
	}

	public ServerConfig setComment(String comment) {
		this.comment = comment;
        return this;
	}

	public String getComment() {
		return this.comment;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        ServerConfig bean = (ServerConfig) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(key, bean.key)
        	&& Objects.equals(value, bean.value)
        	&& Objects.equals(comment, bean.comment)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, key, value, comment, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "ServerConfig{" +
				"id=" + id +
				", key='" + key + '\'' +
				", value='" + value + '\'' +
				", comment='" + comment + '\'' +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
