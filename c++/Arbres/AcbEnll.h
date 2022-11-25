#include <queue>
#include "Acb.h"

using namespace std;

class AcbEnll: public Acb{
    class NodeA{
        NodeA esq,dret; // !!! NodeA not int !!! create seperate files (.h and .cpp for each class)  
        int inf; // template<typename T> T 
        
        NodeA():NodeA(0){}
        NodeA(int val){inf=val; esq=0; dret=0;}
        NodeA(int val, NodeA e, NodeA d){inf=val; esq=e; dret=d;}

        template<typename T>
        void inserir(T einf){}

        template<typename T>
        NodeA esborrar(T einf){}

        template<typename T>
        bool hiEs(T e){}

        template<typename T> T buscarMinim(){}

        void inordre(bool sentit, queue<int> c){}

        int cardinalitat(){
            int h=0;
            if(esq!=NULL) h+=esq.cardinalitat();
            if(dret!=NULL) h+=dret.cardinalitat();
        }
        
    };

    NodeA arrel;
    queue<int> cua;

    public:
        AcbEnll(NodeA n){
            arrel=n; 
            // cua=0; // can't be equal to NULL.
        }
        template<typename T> T getArrel(){
            if(arrel==NULL) throw Exception(0);
            return arrel;
        }

        Acb fillEsquerre(){
            return (arrel==0)? 0 : AcbEnll(arrel.esq);
        }

        Acb fillDret(){

        }

        bool abBuidat(){}
        void buidar(){}
        template<typename T>
        void inserir(T e){}
        template<typename T>
        void esborrar(T e){}
        template<typename T>
        bool membre(T e){}

        void iniRecorregut(bool sentit){}
        bool finalRecorregut(){}

        template<typename T> T segRecorregut(){}
        
        AcbEnll clone(){}

    int cardinalitat(){

    }
    

    
    
};