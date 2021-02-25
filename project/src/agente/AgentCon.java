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
       Evento e = new Evento();

        
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
            System.out.println("Row count: " +evs.getEventos().size());
          // Integer id = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getId().toInt();
            Integer id = i;
           String nome = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getNome().toString();
           Integer duracao = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getDuracao().toInt();
           String dt =  mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getDeltaT().toString();
           String dl = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getDataLimite().toString();
           Integer p = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getPassou().toInt();

            System.out.println("Id : "+id);
            System.out.println("nome : "+nome);
            System.out.println("Duracao : "+duracao);
            System.out.println("DataT : "+dt);
            System.out.println("DataL : "+dl);
            System.out.println("Passou : "+p);

            e.setId(i);
            e.setNome(nome);
            e.setDuracao(duracao);
            e.setDeltaT(dt);
            e.setdataLimite(dl);
            e.setPassou(p);

            Data dataDt = new Data();
            dataDt.parseData(dt);

            Data dataDl = new Data();
            dataDl.parseData(dl);

            Integer duracaoI =e.getDuracao();
            String dtRes =e.getDeltaT();
            String dlRes = e.getdataLimite();
            Integer pI =e.getPassou();


            if(p == 0 && !(dataDt.isZero())){
                dtRes = dataDt.decrementaData(dt);
               e.setDeltaT(dtRes);

            }
            if(p == 0 && (dataDt.isZero())){
                duracaoI = duracao;
                duracaoI --;
             // String duracaoS = duracaoI.toString();
               e.setDuracao(duracaoI);
            }

            if(duracao == 0){
               pI = 1;
             //  String pS = pI.toString();
               e.setPassou(pI);
            }
            else if (p == 1) {
                dtRes = dataDt.incrementaData(dt);
                dlRes = dataDl.decrementaData(dl);
                e.setDeltaT(dtRes);
                e.setdataLimite(dlRes);
            }
            if(dataDl.isZero()){
               // mib.getEventsMIBEntry().removeRow(new OID(id.toString()));
                evs.removeEvento(id);
                for(Integer j=1; j<= mib.getEventsMIBEntry().getModel().getRowCount();j++) {
                    mib.getEventsMIBEntry().removeRow(new OID(j.toString()));
                }
                evs.saveEventos("eventos.json");
                insertEvents(mib);
                i--;
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
        evs.saveEventos("eventos.json"); //guardar a table no ficheiro json


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
