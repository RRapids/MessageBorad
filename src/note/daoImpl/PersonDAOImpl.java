package note.daoImpl;

import note.dao.PersonDAO;
import note.util.DataBaseConnection;
import note.vo.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAOImpl extends DataBaseConnection implements PersonDAO {
    PreparedStatement pstmt = null;
    DataBaseConnection dataBaseConnection = null;
    @Override
    public boolean login(Person person) throws Exception{
        Boolean flag = false;
        String sql = "select * from person where name=? and password=? and flag=?";
        try {
            ResultSet rs = pstmt.executeQuery();
            pstmt = dataBaseConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, person.getUserName());
            pstmt.setString(2, person.getUserPassword());
            pstmt.setString(3, person.getFlag());
            if (rs.next()) {
                flag = true;
                person.setActive(rs.getString(1));
                person.setImage(rs.getString(2));
                person.setUserId(rs.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            dataBaseConnection.close();
            pstmt.close();
        return flag;
    }

    @Override
    public int insert(Person person) {
        String sql = "insert into notes(image,name,password,email,flag) VALUES(?,?,?,?,?)";
        Object[] params = {person.getImage(), person.getUserName(), person.getUserPassword(), person.getEmail(), person.getFlag()};
        try {
            ResultSet resultSet = pstmt.executeQuery();
            pstmt = dataBaseConnection.getConnection().prepareStatement(sql);
            person.setImage("image");
            person.setUserName("name");
            person.setUserPassword("password");
            person.setEmail("email");
            person.getFlag();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean checkUser(Person person) throws Exception {
        return false;
    }

    @Override
    public String QueryId(Person person) throws Exception {
        return null;
    }

    @Override
    public boolean checkUserById(String id) throws Exception {
        return false;
    }

    @Override
    public Person checkPersonById(String id) throws Exception {
        return null;
    }
}
