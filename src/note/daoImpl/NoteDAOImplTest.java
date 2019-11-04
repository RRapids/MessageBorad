package note.daoImpl;

import note.dao.NoteDAO;
import note.factory.DAOFactory;
import note.vo.Note;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteDAOImplTest {
    private NoteDAO noteDAO = DAOFactory.getNoteDAOInstance();

    @org.junit.jupiter.api.Test
    void queryAll() throws Exception {
        List<Note> list  = noteDAO.queryAll();
        System.out.println(list);

    }

    @org.junit.jupiter.api.Test
    void findAll() {
    }

    @org.junit.jupiter.api.Test
    void getRows() {
    }

    @org.junit.jupiter.api.Test
    void queryByLike() {
    }

    @org.junit.jupiter.api.Test
    void testQueryByLike() {
    }
}