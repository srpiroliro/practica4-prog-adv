// CHECK: how to fix "AcbEnll is a raw type. References to generic type AcbEnll<E> should be parameterized"

package Cartes;

import Arbres.AcbEnll;
import Arbres.ArbreException;

public class Main{
    private static AcbEnll<Carta> baralla_cartes(){ 
        AcbEnll<Carta> a=new AcbEnll<Carta>();  
        Baralla b=new Baralla();

        // START -
        Baralla.node seg=b.getRoot();
        while(seg!=null){
            try{
                a.inserir(seg.getC()); // FIX
                seg=seg.getSeg();
            }catch(ArbreException e){
                System.out.println(e);
                return null;
            }
        }
        return a;
        // - END -> CHECK: solution based on methods inside Baralla?
    }

    private static void visualitzar(AcbEnll<Carta> b, boolean sentit){ // LinkedList<Carta> q
        b.iniRecorregut(sentit);
        while(!b.finalRecorregut()){
            Carta c=null;
            try{
                c=(Carta) b.segRecorregut();
            }catch(ArbreException e){}
            System.out.print(c);
        }

        // q.forEach((e)->System.out.print(e));
    }

    private static boolean menu_options(){
        System.out.println("Menu Opcions\n==============\n1.- Eliminar carta\n2.- Acabar\nTria que vols fer (1 o 2):");
        int response=1; // Keyboard.readInt();
        return(1==response);
    }

    private static void eliminar(){
        // CHECK: what to delete? 1st? last?
    }

    private static void eliminar_randoms(){
        // "Elimina de l’arbre clonat cartes aleatòries"

        // generate random card.
        // clonat.esborrar(carta)

        // Mostreu el contingut de l’arbre després de cada eliminació. Compte, si s’intenta 
        // eliminar una carta que no està a l’arbre, no és una eliminació!
    }

    public static void main(String[] args) {
        boolean cont=true, fst=true;
        AcbEnll<Carta> baralla=baralla_cartes();
        AcbEnll<Carta> copia=(AcbEnll<Carta>) baralla.clone();

        while(cont){
            visualitzar(baralla, false); // (LinkedList<Carta>) baralla.getQueue()

            if(!fst) eliminar();

            cont=menu_options() && baralla.cardinalitat()>0; fst=false;
        }
        visualitzar(copia, true);
        eliminar_randoms();
        // cloner.cardinalitat()-baralla.cardinalitat()
    }   
}