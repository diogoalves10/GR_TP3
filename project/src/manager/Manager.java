package manager;


import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.agent.mo.snmp.DateAndTime;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;

import java.io.IOException;

public class Manager {
    private Snmp snmp = null;
    //ler do ficheiro de configuração
    //adicionar novos eventos


    /*
    Evento no ficheiro :
    "nome" : nomedoficheiro
    duracao:
    deltaT
    datalimite
     */

    /*Falta :
    Ir buscar os eventos à mib para ver o Id de último evento
    Acabar a função insert
    Função de procurar um evento especifico dando o nome do evento?
     */

    public ResponseEvent set(OID oid, String val) throws IOException
    {
        PDU pdu = new PDU();
        VariableBinding varBind = new VariableBinding(oid,new OctetString(val));
        pdu.add(varBind);
        pdu.setType(PDU.SET);
        pdu.setRequestID(new Integer32(1));
        Target target=getTargetForWrite();

        ResponseEvent event = snmp.set(pdu, target);
        if(event != null)
        {
            System.out.println("\nResponse:\nGot Snmp Set Response from Agent");
            System.out.println("Snmp Set Request = " + event.getRequest().getVariableBindings());
            PDU responsePDU = event.getResponse();
            System.out.println("\nresponsePDU = "+responsePDU);
            if (responsePDU != null)
            {
                int errorStatus = responsePDU.getErrorStatus();
                int errorIndex = responsePDU.getErrorIndex();
                String errorStatusText = responsePDU.getErrorStatusText();
                System.out.println("\nresponsePDU = "+responsePDU);
                if (errorStatus == PDU.noError)
                {
                    System.out.println("Snmp Set Response = " + responsePDU.getVariableBindings());
                }
                else
                {
                    System.out.println("errorStatus = "+responsePDU);
                    System.out.println("Error: Request Failed");
                    System.out.println("Error Status = " + errorStatus);
                    System.out.println("Error Index = " + errorIndex);
                    System.out.println("Error Status Text = " + errorStatusText);
                }
            }

            return event;
        }
        throw new RuntimeException("GET timed out");
    }

    public void insert(int id /*, evento */) { //insere evento na MIB

       // set();
        
    }

    private Target getTargetForWrite()
    {
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public")); //verificar se tem de ser public ou private
        target.setVersion(SnmpConstants.version2c);
        target.setAddress(GenericAddress.parse("127.0.0.1/3003"));
        target.setRetries(2);
        target.setTimeout(1500);

        return target;
    }


}
