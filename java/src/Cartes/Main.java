package Cartes;

import java.util.Random;

import Arbres.AcbEnll;
import Arbres.ArbreException;
import Keyboard.Keyboard;

public class Main{
    private static AcbEnll<Carta> baralla_cartes(){ 
        AcbEnll<Carta> a=new AcbEnll<Carta>();  
        Baralla b=new Baralla();
        Baralla aux=b;
        
        while(aux.getCarta()!=null){
            try{
                a.inserir(aux.getCarta());
                aux=aux.getseg();
            }catch(ArbreException e){
                e.printStackTrace();
                return null;
            }
        }
        return a;
    }
    private static void visualitzar(AcbEnll<Carta> b, boolean sentit){

        b.iniRecorregut(sentit);
        while(!b.finalRecorregut()){
            try {
                Carta c=b.segRecorregut();
                System.out.print(c);
            } catch (ArbreException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n");
    }
    private static boolean menu_options(){
        System.out.println("Menu Opcions\n==============\n1.- Eliminar carta\n2.- Acabar\nTria que vols fer (1 o 2):");
        int response=Keyboard.readInt();
        System.out.println();

        return(1==response);
    }
    private static Carta triar_carta_existent(AcbEnll<Carta> b){
        Carta c=null; boolean missing=true;
        while(missing){
            System.out.println("=================");

            System.out.println("Numero de carta"); 
            int numero=expected_val_int(1, 12);

            System.out.println("Pal de la carta"); 
            System.out.println("\n[0] - COPA\n[1] - ESPASA\n[2] - ORO\n[3] - BASTONS\n");
            int pal=expected_val_int(0, 3);

            c=new Carta(pal, numero);
            if(!b.membre(c)){
                System.out.println("CARTA NO EXISTEIX!");
            } else missing=false;
        }
        return c;
    }
    private static int getRandInt(int min, int max){
        Random random=new Random();
        return random.nextInt(max - min) + min;
    }
    private static int expected_val_int(int min,int max){
        int r=max+1;
        while(r<min||r>max){
            System.out.println("["+min+", "+max+"]: ");
            r=Keyboard.readInt();
        }
        return r;
    }
    private static void eliminar(AcbEnll<Carta> b){
        Carta carta=null;

        System.out.println("Menu Eliminar Cartes");
        carta=triar_carta_existent(b);

        try{ 
            b.esborrar(carta);
            System.out.println("\ncarta eliminada "+carta);
        }catch(ArbreException e){e.printStackTrace();}

        if(b.cardinalitat()==0) 
            System.out.println("Ja no hi han mes cartes per eliminar.");
    }
    private static void eliminar_randoms(AcbEnll<Carta> b){
        for(int i=0;i<getRandInt(1,b.cardinalitat());i++) {
            Carta c=null; boolean is404=true;
            
            while(is404){
                c=new Carta(getRandInt(0,3), getRandInt(1,12));
                is404=!b.membre(c);
            }

            try{
                b.esborrar(c);
                System.out.println("\ncarta eliminada "+c);
            }catch(ArbreException e){e.printStackTrace();}

            visualitzar(b, true);
        }
    }


    public static void main(String[] args) {
        boolean cont=true, fst=true;
        AcbEnll<Carta> baralla=baralla_cartes();
        AcbEnll<Carta> backup=(AcbEnll<Carta>) baralla.clone();

        while(cont){
            if(!fst) eliminar(baralla);
            visualitzar(baralla, false);
            cont=menu_options(); fst=false;
        }
        visualitzar(backup, true);
        eliminar_randoms(backup);

        int res=baralla.cardinalitat()-backup.cardinalitat();
        System.out.println("Resultat: "+((res==0)?"son iguals": (res>0)?"Original es mes gran":"Copia es mes gran"));
    }
}