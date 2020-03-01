package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 系统用户表，实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseIdEntity implements UserDetails {
    /**
     * 姓名
     */
    private String name;

    /**
     * 登录名（用户名）
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 有效性
     */
    private Boolean active = true;


    /**
     * 用户角色
     */
    private List<SysUserRole> roles;

    /**
     * 用户当前角色，不持久化。传递用
     */
    private SysRole role;

    /**
     * 所属班级，学生，教师用户有
     */
    private GradeClass gradeClass;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
