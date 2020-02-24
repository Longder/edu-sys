package com.longder.edusys.service.impl;


import com.longder.edusys.entity.po.SysRole;
import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.entity.po.SysUserRole;
import com.longder.edusys.repository.SysUserRepository;
import com.longder.edusys.repository.SysUserRoleRepository;
import com.longder.edusys.security.SecurityUtil;
import com.longder.edusys.service.UserManageService;
import com.longder.edusys.util.EncryptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理业务
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;
    /**
     * 保存一个用户，新增和修改都可用
     *
     * @param sysUser
     * @param role
     */
    @Override
    @Transactional
    public void saveOneUser(SysUser sysUser, SysRole role) {
        if(ObjectUtils.isEmpty(sysUser.getId())){//空的 新增
            //处理下密码
            sysUser.setPassword(EncryptionUtil.encrypt(sysUser.getPassword().trim()));
            sysUserRepository.insert(sysUser);
            SysUserRole userRole = new SysUserRole(sysUser.getId(),role);
            sysUserRoleRepository.insert(userRole);
        }
    }

    /**
     * 检查登录名
     *
     * @param loginName 登录名
     * @return true:可以注册  false：不能注册
     */
    @Override
    public Boolean checkLoginName(String loginName) {
        SysUser sysUser = sysUserRepository.getByLoginName(loginName);
        return ObjectUtils.isEmpty(sysUser);
    }


    /**
     * 普通用户的列表
     *
     * @return
     */
    @Override
    public List<SysUser> listCommonUser() {
        return sysUserRepository.listCommonUser();
    }

    /**
     * 删除一个用户
     *
     * @param userId
     */
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        sysUserRepository.deleteById(userId);
    }

    /**
     * 修改密码
     *
     * @param newPassword
     */
    @Override
    @Transactional
    public void changePassword(String newPassword) {
        SysUser sysUser = SecurityUtil.getCurrentUser();
        assert sysUser != null;
        sysUser.setPassword(EncryptionUtil.encrypt(newPassword.trim()));
        sysUserRepository.update(sysUser);
    }
}
