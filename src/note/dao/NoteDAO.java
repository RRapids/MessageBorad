package note.dao;

import note.util.SplitPage;
import note.vo.Note;

import java.util.HashMap;
import java.util.List;

public interface NoteDAO {
    public List<Note> queryAll() throws Exception ;

    public List<Note> findAll(SplitPage sp)throws Exception;

    public int getRows(HashMap tm) throws Exception;

    public List<Note>  queryByLike(HashMap cond)throws Exception;

    public List<Note> queryByLike(HashMap cond,SplitPage sp)throws Exception;

}
