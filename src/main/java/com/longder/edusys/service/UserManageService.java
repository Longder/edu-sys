package com.longder.edusys.service;



import com.longder.edusys.entity.po.SysRole;
import com.longder.edusys.entity.po.SysUser;

import java.util.List;

public interface UserManageService {
    /**
     * 保存一个用户，新增和修改都可用
     * @param sysUser
     * @param role
     */
    void saveOneUser(SysUser sysUser, SysRole role);

    /**
     * 检查登录名
     * @param loginName
     * @return true:可以注册  false：不能注册
     */
    Boolean checkLoginName(String loginName);

}
