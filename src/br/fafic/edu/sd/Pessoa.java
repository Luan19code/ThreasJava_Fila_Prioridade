/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fafic.edu.sd;

/**
 *
 * @author luanl
 */
public class Pessoa {
    private String nome;
    private int perfil;

    public Pessoa(String nome, int perfil) {
        this.nome = nome;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
    
    
    
}
