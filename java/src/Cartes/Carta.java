package Cartes;

public class Carta implements Comparable<Object>{
    private String pals[]={"COPA","ESPASA","OROS","BASTONS"};


    private int numer; // 1-12
    private int pal; // 0-3

    Carta(int p, int n){numer=n; pal=p;}

    @Override
    public int compareTo(Object e){
        int y=((Carta) e).getPal()*100+((Carta) e).getNum();
        return (pal*100+numer)-y;

        // if(((Carta) e).getPal()==pal){
        //     return numer-((Carta) e).getNum();
        // }else return (pal-((Carta) e).getPal())*100;
    }

    public String toString(){
        return "Num. : "+numer+"\nPal: "+pals[pal];
    }

    public int getPal(){return pal;}
    public int getNum(){return numer;}
}
