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

    private ProyectoDao proyectoDao;
    private PaqueteDao paqueteDao;
    private ArchivoDao archivoDao;
    private static DAOFactory fabrica;
    
    public ArrayList<Proyecto> proyectos;


    public PanelProyectos(PanelVistaProyecto pvp) {
        
        fabrica = DAOFactory.getFactory(TipoBD.MYSQL);
        
        proyectoDao = fabrica.getProyectoDao();
        paqueteDao = fabrica.getPaqueteDao();
        archivoDao = fabrica.getArchivoDao();
        
        this.pvp = pvp;
        
        this.initComponents();
        
    }

    private void initComponents() {

        cargarProyectos();

    }

    void cargarProyectos() {

        DefaultMutableTreeNode titulo = null;
        DefaultMutableTreeNode proyecto = null;
        DefaultMutableTreeNode paquete = null;
        DefaultMutableTreeNode archivo = null;

        titulo = new DefaultMutableTreeNode("Proyectos");
        modelo = new DefaultTreeModel(titulo);
        
        crearListas();
        
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

       /*
    guarda el proyecto nuevo en la base de datos
    */
    
    public boolean guardarProyecto(Proyecto pro) {
        return proyectoDao.guardar(pro);
    }
    
    /*
    guarda el paquete nuevo en la base de datos
    */
    public boolean guardarPaquete(Paquete paq, int id_pro) {
        return paqueteDao.guardar(paq, id_pro);
    }
    
    /*
    guarda el archivo nuevo en la base de datos
    */
    public boolean guardarArchivo(Archivo arch, int id_paq) {
        return archivoDao.guardar(arch, id_paq);
    }
    
    public void crearListas() {
        
        proyectos = proyectoDao.listar();
        for (int i = 0; i < proyectos.size(); i++) {
            proyectos.get(i).setPaquetes(paqueteDao.listar(proyectos.get(i)));
            for (int j = 0; j < proyectos.get(i).getPaquetes().size(); j++) {
                proyectos.get(i).getPaquetes().get(j).setArchivos(archivoDao.listar(proyectos.get(i).getPaquetes().get(j)));
            }
        }
    }

    private TreeSelectionListener createSelectionListener() {
        return (TreeSelectionEvent e) -> {

            Object obj = e.getNewLeadSelectionPath().getLastPathComponent();
            System.out.println("" + obj.toString());
            String text = "";

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
