package com.miramicodigo.restful2;

import java.io.Serializable;

/**
 * Created by Mel on 15/12/2017.
 */

public class Materia implements Serializable {

    private String codMateria;
    private String nombreMateria;
    private int imagenMaateria;

/*    public Materia() {
    }*/

    /**
     *
     * @param codMateria
     * @param nombreMateria
     * @param imagenMaateria
     */
    public Materia(String codMateria, String nombreMateria, int imagenMaateria) {
        this.codMateria = codMateria;
        this.nombreMateria = nombreMateria;
        this.imagenMaateria = imagenMaateria;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getImagenMaateria() {
        return imagenMaateria;
    }

    public void setImagenMaateria(int imagenMaateria) {
        this.imagenMaateria = imagenMaateria;
    }
}
