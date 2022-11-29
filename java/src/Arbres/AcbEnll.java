package Arbres;

import java.util.LinkedList;
import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E>, Cloneable {

    public AcbEnll(NodeA n){arrel=n;cua=null;}
    public AcbEnll(){this(null);}

    public E arrel() throws ArbreException{
        if(arrel==null) throw new ArbreException("Arbre buit");
        return (E) arrel;
    }

    public Acb<E> fillEsquerre(){ return new AcbEnll<E>((arrel==null)?null:arrel.esq); }
    public Acb<E> fillDret(){ return new AcbEnll<E>((arrel==null)?null:arrel.dret); }
    public boolean abBuit(){return arrel==null;}
    public void buidar(){arrel=null;cua=null;}

    public void inserir(E e) throws ArbreException{
        if(arrel==null) arrel=new NodeA(e, null, null);
        else arrel.inserir(e);
        cua=null;
    }

    public void esborrar(E e) throws ArbreException{
        if(arrel==null) throw new ArbreException("l'arbre es buit");
        arrel=arrel.esborrar(e);
        cua=null;
    }

    public boolean membre(E e){
        return (arrel==null)?false:arrel.hiEs(e);
    }

    public void iniRecorregut(boolean sentit){
        if(arrel!=null){
            cua=new LinkedList<>();
            arrel.inordre(sentit, cua);
        }
    }

    public boolean finalRecorregut(){
        return cua==null || cua.isEmpty();
    }

    public E segRecorregut() throws ArbreException {
        if(finalRecorregut()) throw new ArbreException("Cal iniciar recorregut!");
        E element=cua.remove();
        return element;
    }

    public Object clone(){
        if(abBuit()) return null;

        AcbEnll<E> c=null;
        try{
            c=(AcbEnll<E>) super.clone();
            c.arrel=(AcbEnll<E>.NodeA) arrel.clone();
        }catch(CloneNotSupportedException e){e.printStackTrace();}
        return c;
    }
    
    public int cardinalitat(){return(abBuit())?0:arrel.cardinalitat();}

    private class NodeA implements Cloneable{
        NodeA esq, dret; E inf;

        NodeA(E e){this(e,null,null);}
        NodeA(E e, NodeA l, NodeA r){inf=e; esq=l; dret=r;}

        private void inserir(E einf) throws ArbreException{
            if(inf.compareTo(einf)<0){
                if(esq!=null) esq.inserir(einf);
                else esq=new NodeA(einf);
            } else if(inf.compareTo(einf)>0){
                if(dret!=null) dret.inserir(einf);
                else dret=new NodeA(einf);
            } else throw new ArbreException("element ja hi es");
        }

        private NodeA esborrar(E einf) throws ArbreException{
            if(inf.compareTo(einf)<0){
                if(esq!=null){
                    esq=esq.esborrar(einf); return this;
                } else throw new ArbreException("no hi es");
            }else if(inf.compareTo(einf)>0){
                if(dret!=null){
                    dret=dret.esborrar(einf); return this;
                } else throw new ArbreException("no hi es");
            }else if(esq!=null&&dret!=null){
                inf=dret.buscarMinim();
                dret=dret.esborrar(inf);
                return this;
            }else 
                return(
                    (esq==null&&dret==null)?
                    null:
                    ((esq==null)?dret:esq)
                );
        }

        private boolean hiEs(E einf){
            if(inf.compareTo(einf)<0) return (esq==null)?false:esq.hiEs(einf);
            else if (inf.compareTo(einf)>0) return (dret==null)?false:dret.hiEs(einf);
            else return true;
        }

        private E buscarMinim(){
            if(esq==null) return inf;
            NodeA a=esq; while(a.esq!=null) a=a.esq;
            return a.inf;
        }
        private void inordre(boolean sentit, Queue<E> c){
            NodeA a1=(sentit)?esq:dret;
            NodeA a2=(sentit)?dret:esq;

            if(a1!=null) a1.inordre(sentit, c);
            c.add(inf);
            if(a2!=null) a2.inordre(sentit, c);
        }

        public Object clone(){
            NodeA copia=null;
            try{
                copia=(NodeA) super.clone();
                if(esq!=null) copia.esq=(NodeA) esq.clone();
                if(dret!=null) copia.dret=(NodeA) dret.clone();
            }catch(CloneNotSupportedException e){
                System.out.println("ERROR!");
                e.printStackTrace();
            }
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
}