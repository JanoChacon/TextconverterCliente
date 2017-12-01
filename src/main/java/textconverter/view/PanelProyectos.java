/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textconverter.view;

import textconverter.logic.Archivo;
import textconverter.logic.Paquete;
import textconverter.logic.Proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import persistencia.dao.ArchivoDao;
import persistencia.dao.PaqueteDao;
import persistencia.dao.ProyectoDao;
import persistencia.factory.DAOFactory;
import persistencia.factory.TipoBD;
import persistencia.impl.ProyectoImpl;

/**
 *
 * @author Alejandro
 */
public class PanelProyectos extends JScrollPane {

    JTree tree;
    DefaultTreeModel modelo = null;
    private PanelVistaProyecto pvp;
    private static DAOFactory fabrica;
    public ArrayList<Proyecto> proyectos;

    public PanelProyectos(PanelVistaProyecto pvp) {
        this.pvp = pvp;   
        this.initComponents();
    }

    private void initComponents() {      
        fabrica = DAOFactory.getFactory(TipoBD.MYSQL);     
        ProyectoDao proyectoDao = fabrica.getProyectoDao();
        this.proyectos = proyectoDao.listar();

        cargarProyectos();

    }

    void cargarProyectos() {
        
        DefaultMutableTreeNode titulo = null;
        DefaultMutableTreeNode proyecto = null;
        DefaultMutableTreeNode paquete = null;
        DefaultMutableTreeNode archivo = null;

        titulo = new DefaultMutableTreeNode("Proyectos");
        modelo = new DefaultTreeModel(titulo);
        
//        proyectos.get(0).setPaquetes(PaqueteDao.listar(proyectos.get(i)));
        for (int i = 0; i < proyectos.size(); i++) {
            proyecto = new DefaultMutableTreeNode(proyectos.get(i).getNombre());
            modelo.insertNodeInto(proyecto, titulo, 0);

            for (int j = 0; j < proyectos.get(i).getSize(); j++) {
                if (proyectos.get(i).getPaquete(j) != null) {
                    paquete = new DefaultMutableTreeNode(proyectos.get(i).getPaquete(j).getNombre());
                    modelo.insertNodeInto(paquete, proyecto, 0);

                }
                for (int k = 0; k < proyectos.get(i).getPaquete(j).getSize(); k++) {
                    if (proyectos.get(i).getPaquete(j).getArchivo(k) != null) {
                        archivo = new DefaultMutableTreeNode(proyectos.get(i).getPaquete(j).getArchivo(k).getNombre());
                        modelo.insertNodeInto(archivo, paquete, 0);
                    }
                }
            }
        }
        JTree tree = new JTree(modelo);
        this.add(tree);
        this.setViewportView(tree);
        tree.addTreeSelectionListener(createSelectionListener());
    }

    private TreeSelectionListener createSelectionListener() {
        return (TreeSelectionEvent e) -> {
            
            Object obj = e.getNewLeadSelectionPath().getLastPathComponent();
            System.out.println("" + obj.toString());
            String text ="";
            
            for (int i = 0; i < proyectos.size(); i++) {
                for (int j = 0; j < proyectos.get(i).getSize(); j++) {
                    for (int k = 0; k < proyectos.get(i).getPaquete(j).getSize(); k++) {
                        if (obj.toString().equals(proyectos.get(i).getPaquete(j).getArchivo(k).getNombre())) {
                                text = proyectos.get(i).getPaquete(j).getArchivo(k).getText();
                                
                        }
                    }
                }
            }
            pvp.setText(text);
        };
    }
}