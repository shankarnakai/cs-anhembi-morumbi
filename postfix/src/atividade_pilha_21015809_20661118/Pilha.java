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
public class Pilha implements IPilha {
    public NodeChar topo;

    public Pilha() {
        topo = null;      //Pilha vazia
    }           

    @Override
    public boolean isEmpty() {
        return this.topo == null;
    }

    @Override
    public void push(char valor) {
        NodeChar nodeTopo = new NodeChar(valor);
        NodeChar TopoAnterior = this.topo;
        nodeTopo.setNext(TopoAnterior);
        this.topo = nodeTopo;
    }

    @Override
    public char pop() {
        NodeChar aux = this.topo;
        this.topo = aux.getNext();
        return aux.getValue();
    }

    @Override
    public void display() {
        NodeChar atual = topo;

        while(atual != null) {
            System.out.print(atual.getValue());
            atual = atual.getNext();
        }       
    }
    
    public Pilha inverse() {
        NodeChar atual = topo;
        Pilha inverse = new Pilha();
        while(atual != null) {
        	inverse.push(atual.getValue());
            atual = atual.getNext();
        }       
        
        return inverse;
    }
    
    @Override
    public char peek() {
        return this.topo.getValue();
    }
}

