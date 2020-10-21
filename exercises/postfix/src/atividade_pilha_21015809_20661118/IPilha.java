/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_pilha_21015809_20661118;

/**
 *
 * @author 20661118
 */
public interface IPilha {

    public boolean isEmpty();

    public void push(char valor);

    public char pop();

    public void display();

    public char peek();
}
