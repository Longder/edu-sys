package com.longder.edusys.repository;

import com.longder.edusys.entity.po.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysUserRepository {

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    @Select("SELECT s.id_ as user_id,s.name_ as user_name,s.login_name_,s.email_,s.active_,s.password_, " +
            "       g.id_ as class_id,g.name_ as class_name,g.description_ " +
            "FROM SYS_USER s left join GRADE_CLASS g on s.grade_class_id_ = g.id_ where s.id_=#{id}")
    @ResultMap("com.longder.edusys.repository.SysUserRepository.SysUserResultMap")
    SysUser getOne(Long id);

    /**
     * 新增插入一个用户
     * @param sysUser
     * @return 受影响的条数
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO SYS_USER(name_,login_name_,password_,email_,active_,grade_class_id_) values(#{name},#{loginName},#{password},#{email},#{active},#{gradeClass.id})")
    void insert(SysUser sysUser);

    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    @Select("SELECT s.id_ as user_id,s.name_ as user_name,s.login_name_,s.email_,s.active_,s.password_, " +
            "       g.id_ as class_id,g.name_ as class_name,g.description_ " +
            "FROM SYS_USER s left join GRADE_CLASS g on s.grade_class_id_ = g.id_ where s.login_name_=#{loginName}")
    @ResultMap("com.longder.edusys.repository.SysUserRepository.SysUserResultMap")
    SysUser getByLoginName(String loginName);

    /**
     * 查询所有普通用户
     * @return
     */
    @Select("SELECT U.* FROM SYS_USER U LEFT JOIN SYS_USER_ROLE UR on U.id_ = UR.sys_user_id_ WHERE UR.role_ = 'ROLE_USER'")
    @ResultMap("com.longder.edusys.repository.SysUserRepository.SysUserResultMap")
    List<SysUser> listCommonUser();

    /**
     * 删除用户
     * @param id
     */
    @Delete("DELETE FROM SYS_USER WHERE id_ = #{id}")
    void deleteById(Long id);

    /**
     * 修改用户
     * （目前只修改email和姓名）
     * @param sysUser
     */
    @Update("UPDATE SYS_USER SET name_ = #{name},email_ = #{email} WHERE id_ = #{id}")
    void update(SysUser sysUser);

    /**
     * 根据角色查询
     * @param role
     * @return
     */
    @ResultMap("com.longder.edusys.repository.SysUserRepository.SysUserResultMap")
    @Select("SELECT s.id_ as user_id,s.name_ as user_name,s.login_name_,s.email_,s.active_,s.password_," +
            "       g.id_ as class_id,g.name_ as class_name,g.description_" +
            "    FROM SYS_USER S " +
            "    LEFT JOIN SYS_USER_ROLE SUR on S.id_ = SUR.id_ " +
            "    LEFT JOIN GRADE_CLASS g on S.grade_class_id_ = g.id_ " +
            "    WHERE SUR.role_ = #{role}")
    List<SysUser> listByRole(String role);

}
