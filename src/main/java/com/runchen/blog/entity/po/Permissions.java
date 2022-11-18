package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * permission表
 */
public class Permissions extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 权限名称 */
	private String name;
	/** 描述 */
	private String permissionDesc;


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
		mapper.from(this::getPermissionDesc)
				.to(permissionDesc -> { if (StringUtils.hasLength(permissionDesc)) map.put("permissionDesc", permissionDesc); });
		mapper.from(getDeleted()).to(delete -> map.put("delete", delete));

		return map;
	}

	public Long getId() {
		return this.id;
	}

	public Permissions setName(String name) {
		this.name = name;
        return this;
	}

	public String getName() {
		return this.name;
	}

	public Permissions setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
        return this;
	}

	public String getPermissionDesc() {
		return this.permissionDesc;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Permissions bean = (Permissions) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(name, bean.name)
        	&& Objects.equals(permissionDesc, bean.permissionDesc)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, permissionDesc, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "Permissions{" +
				"id=" + id +
				", name='" + name + '\'' +
				", permissionDesc='" + permissionDesc + '\'' +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
