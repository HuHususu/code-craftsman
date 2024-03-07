package com.husu.home.mapper;

import com.husu.home.model.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huyong(husu)
 * @date 2024/3/7 15:39
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<User> listAll();


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    User getById(Integer id);

    /**
     * 新增，插入所有字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
    int insert(User user);

    /**
     * 新增，忽略null字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
    int insertIgnoreNull(User user);

    /**
     * 修改，修改所有字段
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
    int update(User user);

    /**
     * 修改，忽略null字段
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
    int updateIgnoreNull(User user);

    /**
     * 删除记录
     *
     * @param user 待删除的记录
     * @return 返回影响行数
     */
    int delete(User user);
}
