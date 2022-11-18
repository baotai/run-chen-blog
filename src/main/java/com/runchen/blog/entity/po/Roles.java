package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 角色表
 */
public class Roles extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** Role name */
	private String name;
	/** 描述 */
	private String roleDesc;


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
		mapper.from(this::getRoleDesc)
				.to(roleDesc -> { if (StringUtils.hasLength(roleDesc)) map.put("roleDesc", roleDesc); });
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public Roles setName(String name) {
		this.name = name;
        return this;
	}

	public String getName() {
		return this.name;
	}

	public Roles setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
        return this;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Roles bean = (Roles) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(name, bean.name)
        	&& Objects.equals(roleDesc, bean.roleDesc)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, roleDesc, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "Roles{" +
				"id=" + id +
				", name='" + name + '\'' +
				", roleDesc='" + roleDesc + '\'' +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
