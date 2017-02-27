package com.example.morga.practicamapas;

import android.graphics.drawable.Icon;
import android.media.Image;

/**
 * Created by morga on 24/02/2017.
 */

public class Monumento {
    private String titulo;
    private String descripcion;
    private String imagen;

    public Monumento (String tit, String des, String im)
    {
        titulo=tit;
        descripcion=des;
        imagen=im;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getSubtitulo()
    {
        return descripcion;
    }

    public String getImagen() { return imagen; }
}
