#include <queue>
#include "Acb.h"

using namespace std;

class AcbEnll: public Acb{
    class NodeA{
        int esq,dret; // !!! NodeA not int !!! create seperate files (.h and .cpp for each class)  
        int inf; // template<typename T> T 
        
        NodeA():NodeA(0){}
        NodeA(int val){inf=val; esq=0; dret=0;}
        NodeA(int val, NodeA e, NodeA d){inf=val; esq=e; dret=d;}
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
        
        Object 

    

    
    
};