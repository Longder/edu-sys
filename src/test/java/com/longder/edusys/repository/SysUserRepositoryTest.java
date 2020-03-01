package com.longder.edusys.repository;


import com.longder.edusys.BaseTest;
import com.longder.edusys.entity.po.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

public class SysUserRepositoryTest extends BaseTest {

    @Resource
    private SysUserRepository sysUserRepository;

    @Test
    public void testGetByLoginName(){
        SysUser sysUser = sysUserRepository.getByLoginName("stu1");
        Assert.notNull(sysUser,"message");
    }

    @Test
    public void testListByRole(){
        List<SysUser> list = sysUserRepository.listByRole("ROLE_STUDENT");
        System.out.println(list.size());
    }
}
