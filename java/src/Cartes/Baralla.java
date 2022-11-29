package Cartes;

import java.util.ArrayList;
import java.util.Collections;
public class Baralla{
    public class node{
        node seg=null;
        Carta c=null;
        node(Carta carta){c=carta;}

        public node getSeg(){return seg;}
        public Carta getC(){return c;}
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

        root=new node(cartes.get(0)); 
        node aux=root; cartes.remove(0);
        for(Carta carta:cartes){
            aux.seg=new node(carta); aux=aux.seg; 
        }
    }

    public node getRoot(){return root;}
}