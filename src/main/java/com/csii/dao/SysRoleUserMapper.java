package com.csii.dao;

import com.csii.model.SysRoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.31 11:01
 */
public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);

    List<Integer> getRoleIdListByUserId(int userId);

    List<Integer> getUserIdListByRoleId(int roleId);

    void deleteByRoleId(int roleId);

    void batchInsert(@Param("roleUserList") List<SysRoleUser> roleUserList);

    List<Integer> getUserIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);
}