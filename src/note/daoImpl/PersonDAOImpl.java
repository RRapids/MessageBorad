package note.daoImpl;

import note.dao.PersonDAO;
import note.util.DataBaseConnection;
import note.vo.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonDAOImpl implements PersonDAO {
    private DataBaseConnection dataBaseConnection;

    @Override
    public boolean login(Person person) throws Exception {
        Boolean flag = false;
        String sql = "select * from person where name=? and password=? and flag=?";
        PreparedStatement pstmt = null;
        dataBaseConnection = new DataBaseConnection();
        pstmt = dataBaseConnection.getConnection().prepareStatement(sql);
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
        dataBaseConnection.close();
        return flag;
    }
}
