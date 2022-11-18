package com.runchen.blog.entity.po;

import com.runchen.blog.common.Constants;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户表
 */
public class Users extends BasePO implements Serializable {

	protected static final long serialVersionUID = 1L;

	/** 账号 */
	private String username;
	/** 密码 */
	private String password;
	/** 用户名 */
	private String nickName;
	/** 密码盐 */
	private String salt;
	/** 手机号 */
	private String mobile;
	/** 邮箱地址 */
	private String email;

	public Users() {
	}

	public Users(String username) {
		this.username = username;
	}

	public Users(String username, String password, String nickName, String salt, String mobile, String email) {
		this.username = username;
		this.password = password;
		this.nickName = nickName;
		this.salt = salt;
		this.mobile = mobile;
		this.email = email;
	}

	public Users mask() {
		return this
				.setPassword(Constants.MASK)
				.setSalt(Constants.MASK);
	}

	public Map<String, Object> only() {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.hasLength(this.getUsername())) {
			map.put("username", this.getUsername());
		}
		return map;
	}

	public Map<String, Object> parseToMap() {

		Map<String, Object> map = new HashMap<>();

		PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		mapper.from(getId()).to(id -> { if (id > 0) map.put("id", id); });
		mapper.from(this::getUsername).to(username -> { if (StringUtils.hasLength(username)) map.put("username", username); });
		mapper.from(this::getPassword).to(password -> { if (StringUtils.hasLength(password)) map.put("password", password); });
		mapper.from(this::getUsername).to(nickName -> { if (StringUtils.hasLength(nickName)) map.put("nickName", nickName); });
		mapper.from(this::getSalt).to(salt -> { if (StringUtils.hasLength(salt)) map.put("salt", salt); });
		mapper.from(this::getMobile).to(mobile -> { if (StringUtils.hasLength(mobile)) map.put("mobile", mobile); });
		mapper.from(this::getEmail).to(email -> { if (StringUtils.hasLength(email)) map.put("email", email); });
		mapper.from(this::getDeleted).to(delete -> map.put("delete", delete));

		return map;
	}

	public Users setUsername(String username) {
		this.username = username;
        return this;
	}

	public String getUsername() {
		return this.username;
	}

	public Users setPassword(String password) {
		this.password = password;
        return this;
	}

	public String getPassword() {
		return this.password;
	}

	public Users setNickName(String nickName) {
		this.nickName = nickName;
        return this;
	}

	public String getNickName() {
		return this.nickName;
	}

	public Users setSalt(String salt) {
		this.salt = salt;
        return this;
	}

	public String getSalt() {
		return this.salt;
	}

	public Users setMobile(String mobile) {
		this.mobile = mobile;
        return this;
	}

	public String getMobile() {
		return this.mobile;
	}

	public Users setEmail(String email) {
		this.email = email;
        return this;
	}

	public String getEmail() {
		return this.email;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Users bean = (Users) o;
        return  Objects.equals(id, bean.id)
        	&& Objects.equals(username, bean.username)
        	&& Objects.equals(password, bean.password)
        	&& Objects.equals(nickName, bean.nickName)
        	&& Objects.equals(salt, bean.salt)
        	&& Objects.equals(mobile, bean.mobile)
        	&& Objects.equals(email, bean.email)
        	&& Objects.equals(deleted, bean.deleted)
        	&& Objects.equals(createdBy, bean.createdBy)
        	&& Objects.equals(createdTime, bean.createdTime)
        	&& Objects.equals(modifiedBy, bean.modifiedBy)
        	&& Objects.equals(modifiedTime, bean.modifiedTime)
        	;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, username, password, nickName, salt, mobile, email, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", nickName='" + nickName + '\'' +
				", salt='" + salt + '\'' +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", deleted=" + deleted +
				", createdBy='" + createdBy + '\'' +
				", createdTime=" + createdTime +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedTime=" + modifiedTime +
				'}';
	}
}
