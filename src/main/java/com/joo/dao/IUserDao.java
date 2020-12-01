package com.joo.dao;

import domain.User;
import java.util.List;


/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return 所有user对象
     */
    List<User> findAll();


    /**
     * 增加一条信息
     * @param user
     */
    void saveUser(User user);


    /**
     * 更新信息
     * @param user
     */
    void updateUser(User user);


    /**
     * 根据id删除一条信息
     * @param userId
     */
    void deleteUser(Integer userId);


    /**
     * 根据id查询一条信息
     * @param userId
     * @return 一个user对象
     */
    User findById(Integer userId);


    /**
     * 通过名字 模糊查询
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 根据传递参数查询数据
     * @param user 查询条件：有可能是用户名、性别、地址 还有可能都有 都没有
     * @return
     */
    List<User> findUserByCondition(User user);
}
