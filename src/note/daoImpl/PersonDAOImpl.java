package note.daoImpl;

import note.dao.PersonDAO;
import note.util.DataBaseConnection;
import note.vo.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAOImpl extends DataBaseConnection implements PersonDAO {

    @Override
    public boolean login(Person person) throws SQLException {
        Boolean flag = false;
        DataBaseConnection dbc = new DataBaseConnection();
        String sql = "select active,image,id from person where name=? and password=? and flag=?";
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1, person.getUserName());
        pstmt.setString(2, person.getUserPassword());
        pstmt.setString(3, person.getFlag());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            flag = true;
            person.setActive(rs.getString(1));
            person.setImage(rs.getString(2));
            person.setUserId(rs.getInt(3));
        }
        rs.close();
        pstmt.close();
        dbc.close();
        return flag;
    }

    @Override
    public int insert(Person person) throws SQLException {
        DataBaseConnection dbc = new DataBaseConnection();
        PreparedStatement pstmt;
        String sql = "insert into person(image,name,password,email,flag,active) VALUES(?,?,?,?,?,?)";
        pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1, person.getImage());
        pstmt.setString(2, person.getUserName());
        pstmt.setString(3, person.getUserPassword());
        pstmt.setString(4, person.getEmail());
        pstmt.setString(5, person.getFlag());
        pstmt.setString(6,person.getActive());
        int n = pstmt.executeUpdate();
        pstmt.close();
        dbc.close();
        return n;
    }

    @Override
    public boolean checkUser(Person person) throws Exception {
        Boolean flag = false;
        DataBaseConnection dbc = new DataBaseConnection();
        String sql = "select * from person where name=?";
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1, person.getUserName());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            flag = true;
        }
        dbc.close();
        pstmt.close();
        return flag;
    }

    @Override
    public int QueryId(Person person) throws Exception {
        int b = 0;
        DataBaseConnection dbc = new DataBaseConnection();
        String sql = "select id from person where name =?";
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1, person.getUserName());
        //查询的结果保存在结果集
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            //取id
            b = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        return b;
    }

    @Override
    public boolean checkUserById(String id) throws Exception {
        Boolean flag = false;
        DataBaseConnection dbc = new DataBaseConnection();
        String sql = "select id from person where id =?";
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        //如果查询到了
        if (rs.next()) {
            flag = true;
        }
        rs.close();
        pstmt.close();
        return flag;
    }

    @Override
    public Person checkPersonById(String id) throws Exception {
        DataBaseConnection dbc = new DataBaseConnection();
        String sql = "select * from person where id =?";
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Person person = new Person();
        if (rs.next()) {
            person.setUserId(person.getUserId());
            person.setUserName(person.getUserName());
            person.setUserPassword(person.getUserPassword());
            person.setEmail(person.getEmail());
            person.setFlag(person.getFlag());
            person.setActive(person.getActive());
        }
        return null;
    }

    @Override
    public void update(String id) throws Exception {
        DataBaseConnection dbc = new DataBaseConnection();
        String sql = "UPDATE person SET active =? WHERE id=?";
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1, "1");
        pstmt.setString(2,id);
        //execute执行
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public void update(Person person) throws Exception {
        DataBaseConnection dbc = new DataBaseConnection();
        String sql = "UPDATE person SET image=? WHERE id =?";
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        pstmt.setString(1,person.getImage());
        pstmt.setInt(2,person.getUserId());
        pstmt.executeUpdate();
        pstmt.close();
        dbc.close();
    }
}
