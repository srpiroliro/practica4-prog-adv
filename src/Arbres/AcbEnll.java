package Arbres;

import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E>{

    AcbEnll(NodeA n){arrel=n;cua=null;}

    public E arrel() throws ArbreException{
        if(arrel==null) throw new ArbreException("Arbre buit");
        return (E) arrel;
    }

    public Acb<E> fillEsquerre(){ return new AcbEnll<E>((arrel==null)?null:arrel.esq); }
    public Acb<E> fillDret(){ return new AcbEnll<E>((arrel==null)?null:arrel.dret); }
    public boolean abBuit(){return arrel==null;}
    public void buidar(){arrel=null;}

    public void inserir(E e) throws ArbreException{
        if(arrel==null) arrel=new NodeA(e, null, null);
        else arrel.inserir(e);
    }

    public void esborrar(E e) throws ArbreException{
        if(arrel==null) throw new ArbreException("l'arbre es buit");
        arrel=arrel.esborrar(e);
    }

    public boolean membre(E e){
        return (arrel==null)?false:arrel.hiEs(e);
    }

    // @Override
    // public int compareTo(E node) {
    //     return arrel.inf.compareTo(node);
    // }

    private class NodeA{ 
        NodeA esq, dret; 
        E inf;

        NodeA(E e){this(e,null,null);}
        NodeA(E e, NodeA l, NodeA r){inf=e; esq=l; dret=r;}

        private void inserir(E e) throws ArbreException{
            if(e.compareTo(inf)<0){
                if(esq!=null) esq.inserir(e);
                else esq=new NodeA(e);
            } else if (e.compareTo(inf)>0) {
                if(dret!=null) dret.inserir(e);
                else dret=new NodeA(e);
            } else throw new ArbreException("element ja hi es");
        }

        private NodeA esborrar(E e) throws ArbreException{
            if(e.compareTo(inf)<0){
                if(esq!=null){
                    esq=esq.esborrar(e); return this;
                } else throw new ArbreException("no hi es");
            } else if (e.compareTo(inf)>0){
                if(dret!=null){
                    dret=dret.esborrar(e); return this;
                } else throw new ArbreException("no hi es");
            } else if(esq!=null&&dret!=null){
                inf=dret.buscarMinim();
                dret=dret.esborrar(inf);
                return this;
            } else return(esq==null && dret==null)?null:((esq==null)?dret:esq);
        }

        private boolean hiEs(E e){
            if(e.compareTo(inf)<0) return (esq==null)?false:esq.hiEs(e);
            else if (e.compareTo(inf)>0) return (dret==null)?false:dret.hiEs(e);
            else return true;
        }

        private E buscarMinim(){
            if(esq==null) return inf;
            NodeA a=esq; while(a.esq!=null) a=a.esq;
            return a.inf;
        }
        private void inordre(boolean sentit, Queue<E> c){
            // OPTIMITZAR

            if(arrel.esq!=null && sentit) arrel.esq.inordre(sentit, c);
            else if(arrel.dret!=null && !sentit) arrel.dret.inordre(sentit, c);

            c.add(arrel.inf);

            if(arrel.dret!=null && sentit) arrel.dret.inordre(sentit, c);
            else if(arrel.esq!=null && !sentit) arrel.esq.inordre(sentit, c);
        }
    }
    private Queue<E> cua;
    private NodeA arrel;


    public void iniRecorregut(boolean sentit){
        /* 
            prepara l’arbre per a ser recorregut en inordre. Després d’invocar  
            aquest mètode, la invocació del mètode segRecorregut retornarà el  
            primer element en inordre de l’arbre. Aquest mètode ha d’emplenar l’atribut 
            cua amb els elements de l’arbre aplicant un recorregut en inordre. Cal tenir 
            present el paràmetre alhora d’emplenar la cua

            inicialització del recorregut té un paràmetre de true el recorregut a seguir 
            serà l’inordre treballat a classe de teoria, si és false serà un inordre
            modificat, concretament intercanviant el tractament dels fills esquerra i dret. 


            Un recorregut no es pot iniciar si prèviament no s’ha invocat el mètode 
            iniRecorregut. I si un cop inicialitzat s’invoca algun mètode que modifica
            el contingut de l’arbre tampoc, caldria invocar novament al  mètode d’inicialització.
        */ 
        if(arrel!=null) 
        arrel.inordre(sentit, cua);

    }

    public boolean finalRecorregut(){
        /* retorna true si ja s’ha arribat al final del recorregut en inordre de 
            l’arbre. Això és si:  
            ‐ l’arbre és buit  
            ‐ la darrera vegada que es va invocar segRecorregut aquest mètode  
            ja va retornar el darrer element en inordre de l’arbre.  
            Tot això és el mateix que dir que retorna true quan no té sentit invocar el 
            mètode segRecorregut */
    }

    public E segRecorregut() throws ArbreException {
        /* retorna el següent element en inordre, si n’hi ha.  
            Llença una excepció si:  
            ‐ abans d’invocar‐lo no s’ha invocat el mètode iniRecorregut  
            ‐ la darrera vegada que es va invocar ja va retornar  
            el darrer element del recorregut (finalRecorregut retornaria true)  
            ‐ s’invoca quan entre la invocació de iniRecorregut i la del mètode 
            s’ha produït una modificació de l’arbre, això és, s’ha fet ús del 
            mètode inserir, esborrar, buidar */
    }

}
