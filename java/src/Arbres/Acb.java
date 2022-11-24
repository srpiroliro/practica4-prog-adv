package Arbres;

interface Acb<E extends Comparable<E>>{
    public E arrel() throws ArbreException;
    public Acb<E> fillEsquerre();
    public Acb<E> fillDret();
    public boolean abBuit();
    public void buidar();
    public void inserir(E e) throws ArbreException;
    public void esborrar(E e) throws ArbreException;
    public boolean membre(E e);
}
