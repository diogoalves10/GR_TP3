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

    public Evento (){

    }

    public void updteMIB(){
        insertEvents();
        Eventos evs = new Eventos();
        MOTable table = mib.getEventsMIBEntry();
        for(Integer i=1; i<=mib.getEventsMIBEntry().getModel().getRowCount();i++){
           MOTableRow row =  table.getModel().getRow(new OID(i.toString()));
            Variable dt =  row.getValue(4); //deltaT
            Variable duracao = row.getValue(3); //duracao
            Variable dl = row.getValue(5); //dataLimite
            Variable p = row.getValue(6); //passou

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
