package manager;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.time.LocalDateTime;

public class Manager {
    private Snmp snmp = null;


   public OID[] OIDsEventosTable = {
            new OID(new int[] {1,3,6,1,4,1,1,1,1}), // OID tabela de eventos
    };

    OID[] OIDsEventosTableId = {
            new OID(new int[] {1,3,6,1,4,1,1,1,1,1}), // OID tabela de eventos
    };
    OID[] OIDsEventosTableNome = {
            new OID(new int[] {1,3,6,1,4,1,1,1,1,2}), // OID tabela de eventos
    };

    OID[] OIDsEventosTableDuracao = {
            new OID(new int[] {1,3,6,1,4,1,1,1,1,3}), // OID tabela de eventos
    };

    OID[] OIDsEventosTableDeltaT = {
            new OID(new int[] {1,3,6,1,4,1,1,1,1,4}), // OID tabela de eventos
    };

    OID[] OIDsEventosTableDataLimite = {
            new OID(new int[] {1,3,6,1,4,1,1,1,1,5}), // OID tabela de eventos
    };

    OID[] OIDsEventosTablePassou = {
            new OID(new int[] {1,3,6,1,4,1,1,1,1,6}), // OID tabela de eventos
    };

    public List<VariableBinding[]> getBulk(OID[] OIDsProcesses) throws IOException { //GetBULK
        DefaultPDUFactory pduFactory = new DefaultPDUFactory(PDU.GETBULK);
        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
        snmp.listen(); // open port to receive response
        TreeUtils tree = new TreeUtils(snmp, pduFactory);
        tree.setMaxRepetitions(100); // 100 is the max

        List<TreeEvent> listWalk = tree.walk(getTargetForWrite(), OIDsProcesses);
        List<VariableBinding[]> vbs = new ArrayList<>(listWalk.size());

        int errorStatus = PDU.noError;
        for (TreeEvent treeEvent : listWalk) {
            errorStatus = treeEvent.getStatus();
            if (errorStatus == PDU.noError)  // check for errors
                vbs.add(treeEvent.getVariableBindings()); // copying the results to a data collection that can be manipulated later
            else
                System.out.println("error: " +errorStatus +"\n");
        }
        snmp.close();
        return vbs;

    }

    public TreeMap<Integer,String> vbToArray (List<VariableBinding[]> vbs) { //transforma o list de variableBinding  num treemap

        TreeMap <Integer,String> array = new TreeMap<>();

        for (VariableBinding[] vba : vbs) {

            for (VariableBinding vb : vba) {
                Integer pid = vb.getOid().last();
                array.put(pid,vb.toValueString());
            }

        }
        return  array;
    }


    public void searchEvent(String nome) throws IOException {
        TreeMap<Integer,String> nomes = new TreeMap<Integer, String>(vbToArray(getBulk(OIDsEventosTableNome)));
        TreeMap<Integer,String> duracao = new TreeMap<Integer, String>(vbToArray(getBulk(OIDsEventosTableDuracao)));
        TreeMap<Integer,String> deltaT = new TreeMap<Integer, String>(vbToArray(getBulk(OIDsEventosTableDeltaT)));
        TreeMap<Integer,String> dataLimite = new TreeMap<Integer, String>(vbToArray(getBulk(OIDsEventosTableDataLimite)));

        int key;

        for(Integer i : nomes.keySet()){
            String aux = nomes.get(i);
            if(aux.equals(nome)){
                key = i;
                System.out.println("ID: "+key
                        + "\nNome: " + nomes.get(key)+
                        "\nDuração: " + duracao.get(key)
                        + "\nIntervalo de tempo que falta/já passou para a data de um evento: "+ deltaT.get(key)
                        + "\nData limite da duração de um evento: "+ dataLimite.get(key));
                break;
            }
            else{
                System.out.println("O evento não existe");
            }

        }



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
