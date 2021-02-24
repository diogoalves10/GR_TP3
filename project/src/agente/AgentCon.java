package agente;


import agente.snmp.GrEventsMib;
import agente.snmp.Modules;
import manager.*;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class AgentCon {
    Modules modules = new Modules();
    GrEventsMib mib = modules.getGrEventsMib();
    GrEventsMib.EventsMIBEntryRowFactory factoryrow;

    public static final OID oidGrEventsMibTable =
            new OID(new int[] { 1,3,6,1,4,1,1,1});


    public void updteMIB(){
        insertEvents();
        Eventos evs = new Eventos();
        Evento e = new Evento();
        Data data = new Data();
        MOTable table = mib.getEventsMIBEntry();
        for(Integer i=1; i<=mib.getEventsMIBEntry().getModel().getRowCount();i++){
           MOTableRow row =  table.getModel().getRow(new OID(i.toString()));
            Variable dt =  row.getValue(4); //deltaT
            Variable duracao = row.getValue(3); //duracao
            Variable dl = row.getValue(5); //dataLimite
            Variable p = row.getValue(6); //passou

            Data dataDt = new Data();
            dataDt.parseData(dt.toString());

            Data dataDl = new Data();
            dataDl.parseData(dl.toString());

            if(p.toInt() == 0 && !(dataDt.isZero())){
              String dtRes = dataDt.updateData(dt.toString());

            }
            if(p.toInt() == 0 && (dataDt.isZero())){
                Integer duracaoI = duracao.toInt();
                duracaoI --;
               String duracaoS = duracaoI.toString();

            }
            if(duracao.toInt() == 0){
               Integer pI = 1;
               String pS = pI.toString();
            }
            else if (p.toInt() == 1) {
                String dtRes = dataDt.updateData(dt.toString());
                String dlRes = dataDl.updateData(dl.toString());
            }
            if(dataDl.isZero()){
                //apagar a row
            }
            
        }
    }

    public void insertEvents(){ //fazer a população da mib
        Eventos evs = new Eventos();
        evs.loadEventos();

        //Percorre o arraylist de eventos e adiciona um evento de cada vez
        for(Evento e : evs.getEventos()){
           if(!mib.getEventsMIBEntry().getModel().containsRow(oidGrEventsMibTable.append(e.getId())));
        //   mib.getEventsMIBEntry().addNewRow(oidGrEventsMibTable,e.vars());
            OID oidIndex = new OID(e.getId().toString());
            /*
            MOTable table  = mib.getEventsMIBEntry();
            table.createRow(new OID("1"), e.vars());
            */
            mib.getEventsMIBEntry().addRow(factoryrow.createRow(oidIndex,e.vars()));

        }
    }

}
