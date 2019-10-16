package note.dao;

import note.vo.Person;

public interface PersonDAO {
    //登录验证
    public boolean login(Person person) throws Exception;
    //增加
    public int insert(Person person) throws Exception;
    //检查注册用户是否存在
    public boolean checkUser(Person person) throws Exception ;
    //查询用户id
    public String QueryId(Person person) throws Exception ;
    //根据id查询用户是否存在
    public boolean checkUserById(String id) throws Exception ;
    //根据id查询用户
    public Person checkPersonById(String id) throws Exception;

}
