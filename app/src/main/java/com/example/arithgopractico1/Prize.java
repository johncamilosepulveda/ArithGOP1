package com.example.arithgopractico1;

public class Prize {

    private String articulo;
    private String descripcion;
    private int valor;
    private int imgFoto;
    private boolean cambiar;

    public Prize(String articulo, String descripcion, int valor, int imgFoto, boolean cambiar) {
        this.articulo = articulo;
        this.valor = valor;
        this.imgFoto = imgFoto;
        this.descripcion = descripcion;
        this.cambiar = cambiar;
    }

    public boolean isCambiar() {
        return cambiar;
    }

    public void setCambiar(boolean cambiar) {
        this.cambiar = cambiar;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
