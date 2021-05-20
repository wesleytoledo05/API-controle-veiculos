package com.controle.controlecarros.entidades;

import java.util.ArrayList;

public class FipeResposta {
    
    private String nome;
    private String codigo;
    private ArrayList<Object> modelos = new ArrayList<Object>();
    private ArrayList<Object> anos = new ArrayList<Object>();

    public FipeResposta() {
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public ArrayList<Object> getModelos() {
        return modelos;
    }
    public void setModelos(ArrayList<Object> modelos) {
        this.modelos = modelos;
    }
    public ArrayList<Object> getAnos() {
        return anos;
    }
    public void setAnos(ArrayList<Object> anos) {
        this.anos = anos;
    }
    
}
