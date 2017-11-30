/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.alejandro.textconverter.logic;

/**
 *
 * @author Alejandro
 */
public class Archivo {
    
    private String nombre;
    private String text;

    public Archivo(String nombre, String text) {
        this.nombre = nombre;
        this.text = text;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
 
}
