package test;

import com.joo.dao.IUserDao;
import com.mysql.cj.Session;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    InputStream in;
    SqlSession session;

    /**
     * 入门案例
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        MyBatisTest myBatisTest = new MyBatisTest();

        IUserDao userDao = myBatisTest.before();

        //myBatisTest.save(userDao);    //增

        //myBatisTest.update(userDao);  //改

        //myBatisTest.delete(userDao);  //删

        //System.out.println(myBatisTest.findById(userDao));  //查

        //System.out.println(myBatisTest.findByName(userDao));

        System.out.println(myBatisTest.findUserByCondition(userDao));

        myBatisTest.findAll(userDao);

        myBatisTest.after();


    }

    /**
     * before
     *
     * @return
     * @throws IOException
     */
    public IUserDao before() throws IOException {

        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建sqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        //3.使用工厂生产sqlSession对象
        session = factory.openSession();

        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);

        return userDao;
    }

    /**
     * 释放资源
     *
     * @throws IOException
     */
    public void after() throws IOException {
        //提交事务
        session.commit();

        //6.释放资源
        session.close();
        in.close();

    }

    /**
     * 查询所有
     *
     * @param userDao
     */
    public void findAll(IUserDao userDao) {
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();

        for (User user : users) {

            System.out.println(user);
        }

    }

    /**
     * 添加
     *
     * @param userDao
     */
    public void save(IUserDao userDao) {

        User user = new User();
        user.setSex("男");
        user.setBirthday(new Date());
        user.setUsername("FAKER");
        user.setAddress("SKT");

        //5.使用代理对象执行方法
        userDao.saveUser(user);

    }

    /**
     * 更新
     *
     * @param userDao
     */
    public void update(IUserDao userDao) {

        User user = userDao.findById(57);

        user.setAddress("SKT");
        //5.使用代理对象执行方法
        userDao.updateUser(user);

    }

    /**
     * 删除
     *
     * @param userDao
     */
    public void delete(IUserDao userDao) {

        //5.使用代理对象执行方法
        userDao.deleteUser(56);
    }

    /**
     * 通过id查询数据
     *
     * @param userDao
     * @return
     */
    public User findById(IUserDao userDao) {

        return userDao.findById(43);

    }

    /**
     * 通过姓名 模糊查询
     * @param userDao
     * @return
     */
    public List<User> findByName(IUserDao userDao){

        return userDao.findByName("FA%");

    }

    /**
     * 通过姓名 模糊查询
     * @param userDao
     * @return
     */
    public List<User> findUserByCondition(IUserDao userDao){

        User user = new User();
        user.setUsername("FAKER");

        return userDao.findUserByCondition(user);

    }
}
