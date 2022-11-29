package Cartes;

import java.util.ArrayList;
import java.util.Collections;
public class Baralla{
    private class node{
        node seg=null;
        Carta c=null;
        node(Carta carta){c=carta;}
    }
    node root;

    Baralla(){
        ArrayList<Carta> cartes=new ArrayList<Carta>();
        for(int pal=0;pal<4;pal++){
            for(int n=1;n<=12;n++){
                cartes.add(new Carta(pal,n));
            }
        }
        Collections.shuffle(cartes);

        root=new node(cartes.remove(0)); 
        node aux=root;
        for(Carta carta:cartes){
            aux.seg=new node(carta); aux=aux.seg; 
        }
    }
    Baralla(node r){root=r;}

    public Baralla getseg(){return new Baralla(root.seg);}
    public Carta getCarta(){return (root==null)?null:root.c;}
}