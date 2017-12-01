package persistencia.factory;


import persistencia.dao.ArchivoDao;
import persistencia.dao.PaqueteDao;
import persistencia.impl.ProyectoImpl;
import persistencia.dao.ProyectoDao;
import persistencia.impl.ArchivoImpl;
import persistencia.impl.PaqueteImpl;

public abstract class DAOFactory {

    public static DAOFactory getFactory(TipoBD bd) {

        switch (bd) {

            case MYSQL:
                return new MysqlDaoFactory();
            case ORACLE:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException();
        }

    }

    public ProyectoDao getProyectoDao() {
        return new ProyectoImpl();
    }
    
    public PaqueteDao getPaqueteDao() {
        return new PaqueteImpl();
    }
    
    public ArchivoDao getArchivoDao() {
        return new ArchivoImpl();
    }

}
