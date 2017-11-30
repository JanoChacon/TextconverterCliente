/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.alejandro.textconverter.view;

import cl.alejandro.textconverter.logic.Archivo;
import cl.alejandro.textconverter.logic.Paquete;
import cl.alejandro.textconverter.logic.Proyecto;

import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Alejandro
 */
public class PanelProyectos extends JScrollPane {

    JTree tree;
    DefaultTreeModel modelo = null;
    public ArrayList<Proyecto> proyectos = new ArrayList<>();
    private PanelVistaProyecto pvp;

    public PanelProyectos(PanelVistaProyecto pvp) {
        this.pvp = pvp;
        
        this.initComponents();
    }

    private void initComponents() {
        
        //aca iria la llamada al metodo que rescata la estructura de la database
        
        Proyecto pro1 = new Proyecto("proyecto1");
        Paquete paq1 = new Paquete("paquete1");
        Archivo ar1 = new Archivo("archivo1", "hola hola hola");
        
        pro1.addPaquete(paq1);
        pro1.getPaquete(0).addArchivo(ar1);
        proyectos.add(pro1);

        Proyecto pro2 = new Proyecto("proyecto2");
        Paquete paq2 = new Paquete("paquete2");
        Archivo ar2 = new Archivo("archivo2", "hola2 hola2 hola2");

        pro2.addPaquete(paq2);
        pro2.getPaquete(0).addArchivo(ar2);
        proyectos.add(pro2);
        
        cargarProyectos();
        
        
    }

    void cargarProyectos() {

        DefaultMutableTreeNode titulo = null;
        DefaultMutableTreeNode proyecto = null;
        DefaultMutableTreeNode paquete = null;
        DefaultMutableTreeNode archivo = null;

        titulo = new DefaultMutableTreeNode("Proyectos");
        modelo = new DefaultTreeModel(titulo);

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
