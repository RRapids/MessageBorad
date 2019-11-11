package note.daoImpl;

import note.dao.NoteDAO;
import note.util.DataBaseConnection;
import note.util.SplitPage;
import note.vo.Note;

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
        List<Note> list = null;
        if (tm.isEmpty()) {
            list = queryAll();
        } else {
            list = queryByLike(tm);
        }
        int num = list.size();
        return num;
    }

    @Override
    public List<Note> queryByLike(HashMap cond) throws Exception {
        List<Note> all = new ArrayList<>();
        String str = null;
        if (cond.containsKey("title")) {
            str = "title LIKE " + "'%" + cond.get("title") + "%'";
        }
        if (cond.containsKey("author")) {
            str = "author LIKE " + "'%" + cond.get("author") + "%'";
        }
        if (cond.containsKey("content")) {
            str = "author LIKE " + "'%" + cond.get("content") + "%'";
        }
        String sql = "select * from note where " + str;
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
    public List<Note> queryByLike(HashMap cond, SplitPage sp) throws Exception {
        List<Note> list = new ArrayList();
        String str = null;
        if (cond.containsKey("title")) {
            str = "title LIKE " + " '%" + cond.get("title") + "%'";
        }
        if (cond.containsKey("author")) {
            str = "author LIKE " + " '%" + cond.get("author") + "%'";
        }
        if (cond.containsKey("content")) {
            str = "content LIKE" + " '%" + cond.get("content") + "%'";
        }
        String sql = "SELECT * FROM note WHERE " + str + " LIMIT " + sp.getPageRows() * (sp.getCurrentPage() - 1) + "," + sp.getPageRows();
        DataBaseConnection dbc = new DataBaseConnection();
        PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(sql);
        while (rs.next()) {
            Note note = new Note();
            note.setId(rs.getInt(1));
            note.setTitle(rs.getString(2));
            note.setAuthor(rs.getString(3));
            note.setContent(rs.getString(4));
            list.add(note);
        }
        dbc.close();
        rs.close();
        pstmt.close();
        return list;
    }
}