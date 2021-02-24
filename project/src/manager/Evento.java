package manager;

import agente.snmp.GrEventsMib;
import org.snmp4j.agent.mo.snmp.DisplayString;
import org.snmp4j.smi.*;

import java.util.GregorianCalendar;

public class Evento{
    private Integer id;
    private String nome;
    private Integer duracao;
    private String deltaT;
    private String deltaLimite;
    private Integer passou;


    public Evento(){
        id = 0;
        nome = "";
        duracao = 0;
        deltaT = "";
        deltaLimite = "";
        passou =0;

    }

    public Evento(Integer i, String n, int d, String dt, String dl, Integer p){
        id = i;
        nome = n;
        duracao = d;
        deltaT = dt;
        deltaLimite =dl;
        passou = p;
    }

    public String getNome(){ return nome; }
    public Integer getDuracao(){ return duracao; }
    public String getDeltaT(){ return deltaT; }
    public String getDeltaLimite(){ return deltaLimite; }
    public Integer getId() { return id; }
    public Integer getPassou() { return passou; }


    public void setNome(String n){ nome = n; }

    public  void setDeltaT(String dt){ deltaT = dt; }
    public  void setDeltaLimite(String dl){ deltaT = dl; }
    public void setId(Integer id) { this.id = id; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public void setPassou(Integer passou) { this.passou = passou; }

    public void decrDucacao(){ duracao--; }

    public Variable[] vars(){
        Variable[] variables = new Variable[6];
        variables[0] = new Integer32(id);
        variables[1] = new OctetString(nome);
        variables[2] = new Integer32(duracao);
        variables[3] = new OctetString(deltaT);
        variables[4] = new OctetString(deltaLimite);
        variables[5] = new Integer32(passou);

        return variables;
    }

}
