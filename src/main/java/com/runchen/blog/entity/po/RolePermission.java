package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * role-permission关联表
 */
public class RolePermission extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 角色id */
	private Long roleId;
	/** 权限id */
	private Long permissionId;


	public Map<String, Object> only() {
		Map<String, Object> map = new HashMap<>();
		if (this.getRoleId() != null && this.getRoleId() > 0) {
			map.put("roleId", this.getRoleId());
		}
		if (this.getPermissionId() != null && this.getPermissionId() > 0) {
			map.put("permissionId", this.getPermissionId());
		}
		return map;
	}

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getRoleId).to(roleId -> { if (roleId > 0) map.put("roleId", roleId); });
		mapper.from(this::getPermissionId)
				.to(permissionId -> { if (permissionId > 0) map.put("permissionId", permissionId); });
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public RolePermission setRoleId(Long roleId) {
		this.roleId = roleId;
        return this;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public RolePermission setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
        return this;
	}

	public Long getPermissionId() {
		return this.permissionId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        RolePermission bean = (RolePermission) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(roleId, bean.roleId)
        	&& Objects.equals(permissionId, bean.permissionId)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, roleId, permissionId, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "RolePermission{" +
				"id=" + id +
				", roleId=" + roleId +
				", permissionId=" + permissionId +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
