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
        // START -
        ArrayList<Carta> cartes=new ArrayList<Carta>();
        for(int pal=0;pal<4;pal++){
            for(int n=1;n<=12;n++){
                cartes.add(new Carta(pal,n));
            }
        }
        Collections.shuffle(cartes);

        node aux=null;
        for(Carta carta:cartes){
            aux=new node(carta);
            aux=aux.seg;
        }
        root=aux;
        // - END -> INEFFICIENT? (48+48)

        // OTHER SOLUTIONS:
        //  - list of used pals/nums and generate numbers randomly until 
        //    theres no card with the same specs.
        //    POSSIBLE ITERATIONS: min. 48/ max. 
    }

    public node getRoot(){return root;}
}
