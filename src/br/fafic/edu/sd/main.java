/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fafic.edu.sd;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luanl
 */
public class main {

    //Vetor de pessoas da Classe Pessoa
    public static Pessoa[] pessoa = {new Pessoa("Luan", 3), new Pessoa("Joao", 1), new Pessoa("Maria", 3),
        new Pessoa("carlos", 3), new Pessoa("pedro", 2), new Pessoa("igor", 1), new Pessoa("matheus", 2),
        new Pessoa("mica", 3), new Pessoa("sion", 3), new Pessoa("diogo", 3), new Pessoa("dian", 1),
        new Pessoa("fagunder", 2), new Pessoa("laila", 3), new Pessoa("pissino", 1), new Pessoa("crass", 2),
        new Pessoa("romario", 3), new Pessoa("micamica", 2), new Pessoa("talon", 3), new Pessoa("popos", 1),
        new Pessoa("pessoa aleatoria", 3)};

    public static void main(String[] args) {

        System.out.println("FILA");
        //Imprime a fila com sua sequencia de Prioridade
        for (int i = 0; i < pessoa.length; i++) {
            System.out.print(pessoa[i].getPerfil() + " ");
        }
        System.out.println("");

        Processo processo = new Processo();
        //Criação da Thread 1
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                processo.prioritario();

            }
        });
        //Criação da Thread 2
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                processo.especial();
            }
        });
        //Criação da Thread 3
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                processo.normal();

            }
        });
        //Start THR1
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Start THR2
        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //O Tempo de Espera para não atrapalhar as THR Anteriores
        //Start THR3
        t3.start();

    }

}
