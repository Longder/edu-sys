package com.longder.edusys.service.impl;

import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.repository.ExamPaperRepository;
import com.longder.edusys.repository.ExamResultRepository;
import com.longder.edusys.repository.SysUserRepository;
import com.longder.edusys.service.StudentManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生管理的业务
 */
@Service
public class StudentManageServiceImpl implements StudentManageService {

    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private ExamPaperRepository examPaperRepository;
    @Resource
    private ExamResultRepository examResultRepository;

    /**
     * 查询所有学生
     *
     * @return
     */
    @Override
    public List<SysUser> listAllStudent() {
        return sysUserRepository.listByRole("ROLE_STUDENT");
    }

    /**
     * 查询获取一个学生信息
     *
     * @param studentId
     * @return
     */
    @Override
    public SysUser getOneStudent(Long studentId) {
        return sysUserRepository.getOne(studentId);
    }

    /**
     * 编辑更新学生信息
     *
     * @param student
     */
    @Override
    @Transactional
    public void editStudent(SysUser student) {
        SysUser dbStudent = sysUserRepository.getOne(student.getId());
        //目前只更新姓名和email
        dbStudent.setName(student.getName());
        dbStudent.setEmail(student.getEmail());
        sysUserRepository.update(dbStudent);
    }

    /**
     * 删除一个学生
     * @param studentId
     */
    @Override
    @Transactional
    public void deleteOneStudent(Long studentId) {
        //删除考试结果
        examResultRepository.deleteByStudentId(studentId);
        //删除自测试卷
        examPaperRepository.deleteByStudentId(studentId);
        //删除学生
        sysUserRepository.deleteById(studentId);
    }
}
