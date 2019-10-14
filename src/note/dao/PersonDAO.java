package note.dao;

import note.vo.Person;

public interface PersonDAO {
    //登录验证
    public boolean login(Person person) throws Exception;

}
