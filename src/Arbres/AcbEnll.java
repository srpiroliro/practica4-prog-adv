package Arbres;

import java.util.LinkedList;
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
    public void buidar(){
        arrel=null;
        cua=null; // CHECK
    }

    public void inserir(E e) throws ArbreException{
        cua=null; // CHECK

        if(arrel==null) arrel=new NodeA(e, null, null);
        else arrel.inserir(e);
    }

    public void esborrar(E e) throws ArbreException{
        cua=null; // CHECK

        if(arrel==null) throw new ArbreException("l'arbre es buit");
        arrel=arrel.esborrar(e);
    }

    public boolean membre(E e){
        return (arrel==null)?false:arrel.hiEs(e);
    }

    private class NodeA{
        NodeA esq, dret; 
        E inf;

        NodeA(E e){this(e,null,null);}
        NodeA(E e, NodeA l, NodeA r){inf=e; esq=l; dret=r;}

        // compareTo(einf) or just einf-inf

        private void inserir(E einf) throws ArbreException{
            if(compareTo(einf)<0){
                if(esq!=null) esq.inserir(einf);
                else esq=new NodeA(einf);
            } else if(compareTo(einf)>0){
                if(dret!=null) dret.inserir(einf);
                else dret=new NodeA(einf);
            } else throw new ArbreException("element ja hi es");
        }

        private NodeA esborrar(E einf) throws ArbreException{
            if(compareTo(einf)<0){
                if(esq!=null){
                    esq=esq.esborrar(einf); return this;
                } else throw new ArbreException("no hi es");
            }else if(compareTo(einf)>0){
                if(dret!=null){
                    dret=dret.esborrar(einf); return this;
                } else throw new ArbreException("no hi es");
            }else if(esq!=null&&dret!=null){
                inf=dret.buscarMinim(); // ???? pq dret
                dret=dret.esborrar(inf);
                return this;
            }else return(esq==null&&dret==null)?null:((esq==null)?dret:esq);
        }

        private boolean hiEs(E einf){
            if(compareTo(einf)<0) return (esq==null)?false:esq.hiEs(einf);
            else if (compareTo(einf)>0) return (dret==null)?false:dret.hiEs(einf);
            else return true;
        }

        private E buscarMinim(){
            if(esq==null) return inf;
            NodeA a=esq; while(a.esq!=null) a=a.esq;
            return a.inf;
        }
        private void inordre(boolean sentit, Queue<E> c){ // TEST
            NodeA a1=(sentit)?arrel.esq:arrel.dret;
            NodeA a2=(sentit)?arrel.dret:arrel.esq;

            if(a1!=null) a1.inordre(sentit, c);
            c.add(arrel.inf);
            if(a2!=null) a2.inordre(sentit, c);
        }

        // CHECK: necessary?
        // @Override
        public int compareTo(E node) { // CHECK (check for correctnes + test)
            return (int)inf-(int)((NodeA)node).inf;
        }

        private Object cloner(){ // CHECK
            // "No es permet invocar a cap mètode de la classe en la seva implementació." ??????
            NodeA copia=null;
            try{
                copia=(NodeA) super.clone();
                if(esq!=null)  copia.esq=(NodeA)  esq.cloner();
                if(dret!=null) copia.dret=(NodeA) dret.cloner();
            }catch(CloneNotSupportedException e){}
            return copia;
        }

        private int cardinalitat(){
            int h=0;
            if(esq!=null) h+=esq.cardinalitat();
            if(dret!=null) h+=dret.cardinalitat();
            return 1+h;
        }
    }
    private Queue<E> cua=null;
    private NodeA arrel;


    public void iniRecorregut(boolean sentit){
        if(arrel!=null){
            cua=new LinkedList<>();
            arrel.inordre(sentit, cua);
        }
    }

    public boolean finalRecorregut(){
        /* "la darrera vegada que es va invocar segRecorregut aquest mètode  
            ja va retornar el darrer element en inordre de l’arbre."                ???????????? */

        return cua.isEmpty();
    }

    public E segRecorregut() throws ArbreException {
        if(cua==null||finalRecorregut()) throw new ArbreException("Cal iniciar recorregut!");
        E element=cua.peek(); cua.remove();
        return element;
    }

    public Object clone(){return arrel.cloner();}
    public int cardinalitat(){
        // iniRecorregut -> cua.size -> cua=old_cua
        return arrel.cardinalitat();
    }

}
