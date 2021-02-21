package manager;

import org.snmp4j.smi.*;

public class Evento{
    private String nome;
    private Integer duracao;
    private String deltaT;
    private String deltaLimite;


    public Evento(){
        nome = "";
        duracao = 0;
        deltaT = new OctetString();
        deltaLimite = new OctetString();

    }

    public Evento(String n, int d, String dt, String dl, boolean p){
        nome = n;
        duracao = d;
        deltaT = new OctetString(dt);
        deltaLimite = new OctetString(dl);

    }

    public String getNome(){ return nome; }
    public Integer getDuracao(){ return duracao; }
    public String getDeltaT(){ return deltaT; }
    public String getDeltaLimite(){ return deltaLimite; }


    public void setNome(String n){ nome = n; }
    public void setDuracao(int d){ duracao = d; }
    public  void setDeltaT(String dt){ deltaT = dt; }
    public  void setDeltaLimite(String dl){ deltaT = dl; }

    public void decrDucacao(){ duracao--; }
}
