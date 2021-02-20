package manager;

import org.snmp4j.smi.*;

public class Evento{
    private String nome;
    private int duracao;
    private OctetString deltaT;
    private OctetString deltaLimite;
    boolean passou;

    public Evento(){
        nome = "";
        duracao = 0;
        deltaT = new OctetString();
        deltaLimite = new OctetString();
        passou = false;
    }

    public Evento(String n, int d, OctetString dt, OctetString dl, boolean p){
        nome = n;
        duracao = d;
        deltaT = new OctetString(dt);
        deltaLimite = new OctetString(dl);
        passou = p;
    }

    public String getNome(){ return nome; }
    public int getDuracao(){ return duracao; }
    public OctetString getDeltaT(){ return new OctetString(deltaT); }
    public OctetString getDeltaLimite(){ return new OctetString(deltaLimite); }
    public boolean getPassou(){ return passou; }

    public void setNome(String n){ nome = n; }
    public void setDuracao(int d){ duracao = d; }
    public  void setDeltaT(OctetString dt){ deltaT = new OctetString(dt); }
    public  void setDeltaLimite(OctetString dl){ deltaT = new OctetString(dl); }
    public void setPassou(boolean p){ passou = p; }

    public void decrDucacao(){ duracao--; }
}
