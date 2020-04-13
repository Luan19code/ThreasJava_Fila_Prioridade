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
public class Processo {

    //Contadores Para Controle Das Filas
    public int minPrioritario = 0, maxPrioritario = 3;
    public int minEspecial = 0, maxEspecial = 2;
    public int minNormal = 0, maxNormal = 1;

    //Pegando o vetor pessoa da principal
    Pessoa[] pessoaP = main.pessoa;

    //Salvar THR Futuras Para Indentificação
    Thread t1;
    Thread t2;
    Thread t3;

    public void prioritario() {
        //Fila de Priorida 1 da THR1 
        synchronized (this) {
            //For para percorer todo o vetor pessoa da classe atual "this"
            for (int i = 0; i < pessoaP.length; i++) {

                //Verifica se a pessoa tem prioridade 1
                if (pessoaP[i].getPerfil() == 1) {

                    //Verificas se todas as THRs Estão vazias evita conflitos
                    //futuros
                    if (t1 != null && t2 != null && t3 != null) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    System.out.println(pessoaP[i].getNome() + " Com a Prioridade " + pessoaP[i].getPerfil()
                            + " Foi Atendida ");
                    //contador
                    maxPrioritario--;

                    //verificar se todas as pessoas foram atendidas
                    if (maxPrioritario == minPrioritario) {

                        //riniaia o contador
                        maxPrioritario = 3;

                        //Salva a THR atual 
                        t1 = Thread.currentThread();
                        try {
                            //Faz a THR atual esperar quando atinge a o limite 
                            //De pessoas atendiade com essa Prioridade
                            wait();

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
            //Aqui so aparece quanda a THR é finalizada quando todo o vetor 
            //Foi percorrido e todos com a prioridade da THR foram atendidas
            System.out.println(" ");
            System.out.println("TODOS COM PRIORIDADE *1* FORAM ATENDIDOS ");
            System.out.println(" ");

            //Notifica a Procima THR que Pode continuar seu processo casp estaja
            //Pausada
            notify();
        }

    }

    public void especial() {
        //Fila de Priorida 2 da THR2 

        synchronized (this) {
            //For para percorer todo o vetor pessoa da classe atual "this"
            for (int i = 0; i < pessoaP.length; i++) {

                //Verifica se a pessoa tem prioridade 2
                if (pessoaP[i].getPerfil() == 2) {

                    //Verificas se todas as THRs Estão vazias evita conflitos
                    //futuros
                    if (t1 != null && t2 != null && t3 != null) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println(pessoaP[i].getNome() + " Com a Prioridade " + pessoaP[i].getPerfil()
                            + " Foi Atendida");

                    //contador
                    maxEspecial--;

                    //verificar se todas as pessoas foram atendidas
                    if (maxEspecial == minEspecial) {

                        //Reinicia o Contador
                        maxEspecial = 2;

                        //Salva a THR atual 
                        t2 = Thread.currentThread();

                        String fim = t1.getState() + "";

                        //Verifica se a THR1 foi finalizada
                        //Caso notifique com a THR1 ainda ativa
                        //Ira ativala e a THR1 Ira furar fila
                        if (fim.equals("TERMINATED")) {
                            notify();
                        }

                        try {
                            //Faz a THR atual esperar quando atinge a o limite 
                            //De pessoas atendiade com essa Prioridade
                            wait();

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            //Aqui so aparece quanda a THR é finalizada quando todo o vetor 
            //Foi percorrido e todos com a prioridade da THR foram atendidas
            System.out.println(" ");
            System.out.println("TODOS COM PRIORIDADE *2* FORAM ATENDIDOS ");
            System.out.println(" ");

            //Notifica a Procima THR que Pode continuar seu processo casp estaja
            //Pausada
            notify();

        }
    }

    public void normal() {
        //Fila de Priorida 3 da THR3 

        synchronized (this) {

            //For para percorer todo o vetor pessoa da classe atual "this"
            for (int i = 0; i < pessoaP.length; i++) {

                //Verifica se a pessoa tem prioridade 2
                if (pessoaP[i].getPerfil() == 3) {

                    //Verificas se todas as THRs Estão vazias evita conflitos
                    //futuros
                    if (t1 != null && t2 != null && t3 != null) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println(pessoaP[i].getNome() + " Com a Prioridade " + pessoaP[i].getPerfil()
                            + " Foi Atendida");

                    //Contador
                    maxNormal--;

                    //verificar se todas as pessoas foram atendidas
                    if (maxNormal == minNormal) {

                        //Reinicia o Contador
                        maxNormal = 1;

                        //Salva a THR atual 
                        t3 = Thread.currentThread();

                        //Notifica a Procima THR que Pode continuar seu processo casp estaja
                        //Pausada
                        notify();

                        String fim = t2.getState() + "";

                        try {
                            //Verifica se a THR2 se ainda não foi finalizada
                            if (!fim.equals("TERMINATED")) {
                                wait();
                            }

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
            //Aqui so aparece quanda a THR é finalizada quando todo o vetor 
            //Foi percorrido e todos com a prioridade da THR foram atendidas
            System.out.println(" ");
            System.out.println("TODOS COM PRIORIDADE *3* FORAM ATENDIDOS ");
            System.out.println(" ");

        }

    }
}
