package Cartes;

public class Carta implements Comparable<Carta>{
    private String pals[]={"COPA","ESPASA","OROS","BASTONS"};

    private int numer;
    private int pal;

    Carta(int p, int n){numer=n; pal=p;}

    public int getPal(){return pal;}
    public int getNum(){return numer;}

    @Override
    public int compareTo(Carta e){
        return (
            (pal*100+numer)-
            (((Carta) e).getPal()*100+((Carta) e).getNum())
        );
    }

    @Override
    public String toString(){
        return pals[pal]+"-"+numer+"; ";
    }
}