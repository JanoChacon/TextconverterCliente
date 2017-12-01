package persistencia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author oancan
 */
public class MysqlDaoFactory extends DAOFactory {

    private static String BDNAME;
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String CLAVE;

    public MysqlDaoFactory(ServletContext servletContext) {

        MysqlDaoFactory.DRIVER = "com.mysql.jdbc.Driver";
        MysqlDaoFactory.URL = "jdbc:mysql://localhost:3306/";
        MysqlDaoFactory.BDNAME = "textconverter";
        MysqlDaoFactory.USER = "root";
        MysqlDaoFactory.CLAVE = "";
    }

    public static Connection createConnection() {

        Connection con = null;
        try {
            Class.forName(MysqlDaoFactory.DRIVER);
            con = DriverManager.getConnection(MysqlDaoFactory.URL + MysqlDaoFactory.BDNAME,
                    MysqlDaoFactory.USER, MysqlDaoFactory.CLAVE);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MysqlDaoFactory.class.getName()).log(Level.INFO, "ERROR CON");
        }

        return con;
    }
}
