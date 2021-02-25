package agente;


import agente.snmp.GrEventsMib;
import agente.snmp.Modules;
import manager.*;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class AgentCon {

    public void updateMIB(GrEventsMib mib){
        Eventos evs = new Eventos();
      // insertEvents(mib);
       evs.loadEventos();
       Evento listaApagar =  new Evento();
        boolean apagar = false;
       /*
       atualiza-se o ficheiro por iteração
       guardar os ids para apagar num arrayList
       no fim do for
       apagar os ids do ficheiro
       e rescrever o ficheiro com os ids corrigidos
       limpar a mib
       popular a mib com o ficheiro
        */

        for(Integer i=1;i<=evs.getEventos().size();i++){ //verificar esta
            Evento e = new Evento(evs.getEvento(i-1));


            Integer id = e.getId();
            String nome = e.getNome();
            Integer duracao = e.getDuracao();
            String deltaT = e.getDeltaT();
            String dataLimite = e.getdataLimite();
            Integer passou = e.getPassou();

            Data dataDt = new Data();
            dataDt.parseData(deltaT);

            Data dataDl = new Data();
            dataDl.parseData(dataLimite);

            Integer duracaoI =e.getDuracao();
            String dtRes =e.getDeltaT();
            String dlRes = e.getdataLimite();
            Integer pI =e.getPassou();


            if(passou == 0 && !(dataDt.isZero())){
                dtRes = dataDt.decrementaData(deltaT);
               e.setDeltaT(dtRes);
            }
            if(passou == 0 && (dataDt.isZero())){
               e.setDuracao(duracao-1);
            }
            if(duracao <= 0){
               e.setDuracao(duracao);
               e.setPassou(1);
            }
            if (passou == 1) {
                dtRes = dataDt.incrementaData(deltaT);
                dlRes = dataDl.decrementaData(dataLimite);

                e.setDeltaT(dtRes);
                e.setdataLimite(dlRes);

            }
            if(dataDl.isZero()){

               listaApagar=new Evento(e);
               apagar=true;
            }
            else {
                //atualizar os valores na linha de cada coluna
                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setId(new Integer32(id));

                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setDuracao(new Integer32(duracaoI));

                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setDeltaT(new OctetString(dtRes));
                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setDataLimite(new OctetString(dlRes));
                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setPassou(new Integer32(pI));


                evs.setEvento(new Evento(e),id);
            }
            
        }

        if(apagar==true) {
            System.out.println("Entrei no lista apagar");
            evs.removeEventos(listaApagar);
            evs.saveEventos("eventos.json");
            for (Integer j = 1; j <= mib.getEventsMIBEntry().getModel().getRowCount(); j++) {
                mib.getEventsMIBEntry().removeRow(new OID(j.toString()));
            }
            insertEvents(mib);
        }
        else{
            evs.saveEventos("eventos.json"); //guardar a table no ficheiro json
        }

    }

    public void insertEvents(GrEventsMib mib){ //fazer a população da mib
        Eventos evs = new Eventos();
        evs.loadEventos();

        //Percorre o arraylist de eventos e adiciona um evento de cada vez
        for(Evento e : evs.getEventos()){
            try {
                if(!mib.getEventsMIBEntry().getModel().containsRow(new OID(e.getId().toString()))) {
                    OID oidIndex = new OID(e.getId().toString());
                    mib.getEventsMIBEntry().addNewRow(oidIndex, e.vars());

                }
            } catch (Exception ex){
                ex.printStackTrace();
            }



        }

    }

}
