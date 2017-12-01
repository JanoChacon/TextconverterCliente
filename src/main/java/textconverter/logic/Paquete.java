/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textconverter.logic;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Paquete {
    
    private String nombre;
    private ArrayList<Archivo> Archivos = new ArrayList<>();

    public Paquete(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
      
    public Archivo getArchivo(int i){
        return this.Archivos.get(i);
    }    
    
    public int getSize(){
        return this.Archivos.size();
    }
    
    public void addArchivo(Archivo archivo){
        this.Archivos.add(archivo);
    }
     
}
