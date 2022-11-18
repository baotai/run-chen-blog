package com.runchen.blog.entity.po;

import org.springframework.boot.context.properties.PropertyMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * user-role关联表
 */
public class UserRole extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 用户id */
	private Long userId;
	/** 角色id */
	private Long roleId;

	public Map<String, Object> only() {
		Map<String, Object> map = new HashMap<>();
		if (this.getUserId() != null && this.getUserId() > 0) {
			map.put("userId", this.getUserId());
		}
		if (this.getRoleId() != null && this.getRoleId() > 0) {
			map.put("roleId", this.getRoleId());
		}
		return map;
	}

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getUserId).to(userId -> { if (userId > 0) map.put("userId", userId); });
		mapper.from(this::getRoleId).to(roleId -> { if (roleId > 0) map.put("roleId", roleId); });
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public UserRole setUserId(Long userId) {
		this.userId = userId;
        return this;
	}

	public Long getUserId() {
		return this.userId;
	}

	public UserRole setRoleId(Long roleId) {
		this.roleId = roleId;
        return this;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        UserRole bean = (UserRole) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(userId, bean.userId)
        	&& Objects.equals(roleId, bean.roleId)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, userId, roleId, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "UserRole{" +
				"id=" + id +
				", userId=" + userId +
				", roleId=" + roleId +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
