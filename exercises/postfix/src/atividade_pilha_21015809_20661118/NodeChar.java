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
public class NodeChar implements INodeChar{
    private NodeChar next;
    private final char value;
    
    public NodeChar(char value) {
        this.value = value;
    }
    
    @Override
    public char getValue() {
        return this.value;
    }
    @Override
    public void setNext(NodeChar next){
        this.next = next;
    }
    
    @Override
    public NodeChar getNext(){
        return this.next;
    }
}
