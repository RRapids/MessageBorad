package note.daoImpl;

import note.dao.NoteDAO;
import note.util.DataBaseConnection;
import note.util.SplitPage;
import note.vo.Note;
import note.vo.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteDAOImpl implements NoteDAO {
    @Override
    public List<Note> queryAll() throws Exception {
        List all = new ArrayList();
        String sql = "select id,title,author,content from note";
        DataBaseConnection dbc = new DataBaseConnection();
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Note note = new Note();
            note.setId(rs.getInt(1));
            note.setTitle(rs.getString(2));
            note.setAuthor(rs.getString(3));
            note.setContent(rs.getString(4));
            all.add(note);
        }
        dbc.close();
        pstmt.close();
        rs.close();
        return all;
    }

    @Override
    public List<Note> findAll(SplitPage sp) throws Exception {
        List<Note> list = new ArrayList<Note>();
        String sql = "select * from note limit " + sp.getPageRows() * (sp.getCurrentPage() - 1) + "," + sp.getPageRows();
        DataBaseConnection dbc = new DataBaseConnection();
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Note note = new Note();
            note.setId(rs.getInt(1));
            note.setTitle(rs.getString(2));
            note.setAuthor(rs.getString(3));
            note.setContent(rs.getString(4));
            list.add(note);
        }
        dbc.close();
        pstmt.close();
        rs.close();
        return list;
    }

    @Override
    public int getRows(HashMap tm) throws Exception {
        return 0;
    }

    @Override
    public List<Note> queryByLike(HashMap cond) throws Exception {
        List<Note> all = new ArrayList<>();
        String str = null;
        if (cond.containsKey("title")) {
            str = "title LIKE " + "'" + cond.get("title") + "'";
            String sql = "select * from note where " + str;
        }

        if (cond.containsKey("author")) {
            str = "author LIKE " + "'" + cond.get("author") + "'";
            String sql = "select * from note where " + str;
        }
        return all;
    }

    @Override
    public List<Note> queryByLike(HashMap cond, SplitPage sp) throws Exception {
        return null;
    }
}
