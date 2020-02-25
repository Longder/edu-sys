package com.longder.edusys.repository;


import com.longder.edusys.BaseTest;
import com.longder.edusys.entity.po.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;

public class SysUserRepositoryTest extends BaseTest {

    @Resource
    private SysUserRepository sysUserRepository;

    @Test
    public void testGetByLoginName(){
        SysUser sysUser = sysUserRepository.getByLoginName("admin");
        Assert.notNull(sysUser,"message");
    }
}
