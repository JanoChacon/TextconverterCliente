/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao;

import java.util.List;
import textconverter.logic.Proyecto;

/**
 *
 * @author dci
 */
public interface ProyectoDao {
    /**
     * Permite obtener todas las mesas
     * @return 
     */
    public List<Proyecto> listar();
    
    /**
     * Almacena un objeto de tipo mesa
     * @param mesa
     * @return 
     */
    public boolean guardar(Proyecto mesa);
    
    /**
     * Elimina un mesa a traves de su id
     * @param id
     * @return 
     */
    public boolean borrar(int id);
    
    /**
     * Busca una mesa en base a un id
     * @param id
     * @return 
     */
    public Proyecto buscar(int id);

    /**
     * Permite editar los datos de una mesa
     * @param mesa
     * @return 
     */
    public boolean editar(Proyecto mesa);
}
