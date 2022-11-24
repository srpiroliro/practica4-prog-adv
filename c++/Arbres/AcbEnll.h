#include <queue>

class AcbEnll{
    public:
        AcbEnll(NodeA n){arrel=n; cua=nullptr;}
    
    class NodeA{
        NodeA esq;
        NodeA dret; 
        int inf; // template<typename T> T 
        
        NodeA(int val){inf=val; esq=nullptr; dret=nullptr;}
        NodeA(int val, NodeA e, NodeA d){inf=val; esq=e; dret=d;}


    };
    NodeA arrel;
    queue<int> cua;
};