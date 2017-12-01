package persistencia.factory;

import javax.servlet.ServletContext;
import persistencia.impl.ProyectoImpl;
import persistencia.dao.ProyectoDao;

public abstract class DAOFactory {

    public static DAOFactory getFactory(TipoBD bd, ServletContext context) {

        switch (bd) {

            case MYSQL:
                return new MysqlDaoFactory(context);
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
