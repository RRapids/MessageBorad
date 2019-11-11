package note.dao;

import note.vo.Person;

public interface PersonDAO {
    //登录验证
    public boolean login(Person person) throws Exception;
    //插入
    public int insert(Person person) throws Exception;
    //检查注册用户是否存在
    public boolean checkUser(Person person) throws Exception ;
    //查询用户id
    public int QueryId(Person person) throws Exception ;
    //根据id查询用户是否存在
    public boolean checkUserById(String id) throws Exception ;
    //根据id查询用户
    public Person checkPersonById(String id) throws Exception;
    //修改已通过邮件进行激活的用户
    public void update(String id) throws Exception;
    //修改用户信息
    public void update(Person person) throws Exception;
}
