/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import textconverter.logic.Proyecto;
import persistencia.factory.DAOFactory;
import persistencia.factory.MysqlDaoFactory;
import persistencia.dao.ProyectoDao;

/**
 *
 * @author dci
 */
public class ProyectoImpl implements ProyectoDao {

    /**
     * Consulta sql para obtener todas las mesas
     */
    private static final String SQL_SELECT = "select * from Proyecto";

    private static final String SQL_INSERT = "insert into tre_mesa(mes_numero, mes_reservado) values (?, ?)";

    private static final String SQL_DELETE = "delete from tre_mesa where mes_id = ?";

    private static final String SQL_UPDATE = "update tre_mesa set mes_numero = ?, mes_reservado = ? where mes_id = ?";

    private final Connection conn;

    public ProyectoImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public List<Proyecto> listar() {

        List<Proyecto> proyectos = new ArrayList<>();
        ResultSet rs;

        try {
            PreparedStatement pstm = this.conn.prepareStatement(SQL_SELECT);
            rs = pstm.executeQuery();
            if (!rs.next()) {
                Logger.getLogger(ProyectoImpl.class.getName()).log(Level.INFO,
                        "No hay Proyectos");
            } else {

                do {
                    Proyecto proj = new Proyecto();
                    proj.setNombre(rs.getString("nombre"));
                    System.out.println(proj.getNombre());
                    proyectos.add(proj);

                } while (rs.next());

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return proyectos;
    }

    @Override
    public boolean guardar(Proyecto mesa) {

        boolean resultado = false;

        try {
            PreparedStatement pstm = this.conn.prepareStatement(SQL_INSERT);
//            pstm.setInt(1, mesa.getMesNumero());
//            pstm.setInt(2, mesa.getMesReservado());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(Proyecto mesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
