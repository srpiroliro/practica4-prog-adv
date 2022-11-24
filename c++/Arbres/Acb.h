class Acb{
    public:
        template<typename T> T arrel();
        Acb fillEsquerre();
        Acb fillDret();
        virtual bool abBuit();
        virtual void buidar();

        virtual void inserir(auto e)=0;

        virtual void esborrar(auto e)=0;

        virtual bool membre(auto e)=0;
};