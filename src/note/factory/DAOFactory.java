package note.factory;

import note.dao.NoteDAO;
import note.dao.PersonDAO;
import note.daoImpl.NoteDAOImpl;
import note.daoImpl.PersonDAOImpl;

public class DAOFactory {
    public static PersonDAO getPersonDAOInstance(){
        return new PersonDAOImpl();
    }
    public static NoteDAO getNoteDAOInstance(){
        return new NoteDAOImpl();
    }
}
