package com.longder.edusys.service.impl;

import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.repository.SysUserRepository;
import com.longder.edusys.service.TeacherManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师管理相关业务
 */
@Service
public class TeacherManageServiceImpl implements TeacherManageService {

    @Resource
    private SysUserRepository sysUserRepository;

    /**
     * 查询教师列表
     *
     * @return
     */
    @Override
    public List<SysUser> listAllTeacher() {
        return sysUserRepository.listByRole("ROLE_TEACHER");
    }

    /**
     * 查询获取一个教师信息
     *
     * @param teacherId
     * @return
     */
    @Override
    public SysUser getOneTeacher(Long teacherId) {
        return sysUserRepository.getOne(teacherId);
    }

    /**
     * 编辑一个教师信息
     *
     * @param teacher
     */
    @Override
    @Transactional
    public void editTeacher(SysUser teacher) {
        SysUser dbTeacher = sysUserRepository.getOne(teacher.getId());
        //目前只更新姓名和email
        dbTeacher.setName(teacher.getName());
        dbTeacher.setEmail(teacher.getEmail());
        sysUserRepository.update(dbTeacher);
    }

    /**
     * 删除一个教师
     *
     * @param teacherId
     */
    @Override
    @Transactional
    public void deleteOneTeacher(Long teacherId) {
        sysUserRepository.deleteById(teacherId);
    }


}
