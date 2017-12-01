package persistencia.factory;


import persistencia.impl.ProyectoImpl;
import persistencia.dao.ProyectoDao;

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

}
