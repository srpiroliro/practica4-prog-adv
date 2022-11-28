// CHECK: fix compareTo
package Cartes;

public class Carta implements Comparable<Carta>{ // <E extends Comparable<E>>
    private String pals[]={"COPA","ESPASA","OROS","BASTONS"};

    private int numer; // 1-12
    private int pal; // 0-3

    Carta(int p, int n){numer=n; pal=p;}

    @Override
    public int compareTo(Carta e){
        return (
            (pal*100+numer)-
            ((Carta) e).getPal()*100+((Carta) e).getNum()
        );

        // if(((Carta) e).getPal()==pal){
        //     return numer-((Carta) e).getNum();
        // }else return (pal-((Carta) e).getPal())*100;
    }

    public String toString(){
        return pals[pal]+"-"+numer+"; ";
    }

    public int getPal(){return pal;}
    public int getNum(){return numer;}
}
