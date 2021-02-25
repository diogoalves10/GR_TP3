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
    /*
    Modules modules = new Modules();
    GrEventsMib mib = new GrEventsMib (GrEventsMib.getFactory())
          //  modules.getGrEventsMib();
    GrEventsMib.EventsMIBEntryRowFactory factoryrow;

    public static final OID oidGrEventsMibTable =
            new OID(new int[] { 1,3,6,1,4,1,1,1});


     */

    public void updateMIB(GrEventsMib mib){
        insertEvents(mib);
        Eventos evs = new Eventos();
        Evento e = new Evento();


        for(Integer i=1; i<=mib.getEventsMIBEntry().getModel().getRowCount();i++){ //verificar esta


           Integer id = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getId().toInt();
           String nome = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getNome().toString();
           Integer duracao = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getDuracao().toInt();
            String dt =  mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getDeltaT().toString();
            String dl = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getDataLimite().toString();
            Integer p = mib.getEventsMIBEntry().getModel().getRow(new OID(i.toString())).getPassou().toInt();

            e.setId(id);
            e.setNome(nome);
            e.setDuracao(duracao);
            e.setDeltaT(dt);
            e.setDeltaLimite(dl);
            e.setPassou(duracao);

            Data dataDt = new Data();
            dataDt.parseData(dt.toString());

            Data dataDl = new Data();
            dataDl.parseData(dl.toString());

            Integer duracaoI =e.getDuracao();
            String dtRes =e.getDeltaT();
            String dlRes = e.getDeltaLimite();
            Integer pI =e.getPassou();


            if(p == 0 && !(dataDt.isZero())){
                dtRes = dataDt.decrementaData(dt.toString());
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
                dtRes = dataDt.incrementaData(dt.toString());
                dlRes = dataDl.decrementaData(dl.toString());
                e.setDeltaT(dtRes);
                e.setDeltaLimite(dlRes);
            }
            if(dataDl.isZero()){
                mib.getEventsMIBEntry().removeRow(new OID(i.toString()));
            }
            else {
                //atualizar os valores na linha de cada coluna
                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setDuracao(new Integer32(duracaoI));
                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setDeltaT(new OctetString(dtRes));
                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setDataLimite(new OctetString(dlRes));
                mib.getEventsMIBEntry().getModel().getRow(
                        new OID (i.toString())).setPassou(new Integer32(pI));


                evs.setEvento(new Evento(e));
            }
            
        }
        evs.saveEventos(); //guardar a table no ficheiro json


    }

    public void insertEvents(GrEventsMib mib){ //fazer a população da mib
        Eventos evs = new Eventos();
        evs.loadEventos();

        //Percorre o arraylist de eventos e adiciona um evento de cada vez
        for(Evento e : evs.getEventos()){
            try {
                //  if(!mib.getEventsMIBEntry().getModel().containsRow(oidGrEventsMibTable.append(e.getId())));
                OID oidIndex = new OID(e.getId().toString());
                mib.getEventsMIBEntry().addNewRow(oidIndex, e.vars());

            } catch (Exception ex){
                ex.printStackTrace();
            }


        }
    }

}
