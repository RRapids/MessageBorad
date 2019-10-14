package com.messageBoard.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAOSQLServletImpl extends JDBCUtil implements userDAO {
    @Override
    public boolean findUser(Users user) {
        Boolean flag = false;
        String sql = "select * from users where count=? and password=?";
        Object[] params = {user.getUserCount(), user.getUserPassword()};
        this.executeQuery(sql, params);
        List<HashMap> listUser = new ArrayList<>();
        listUser = this.executeQuery(sql, params);
        if (listUser.size() > 0) {
            flag = true;
        }
        return flag;
    }
}
