 

//--AgentGen BEGIN=_BEGIN
//--AgentGen END
package agente.snmp;

import org.snmp4j.agent.mo.*;
import org.snmp4j.log.LogFactory;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOServer;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.smi.OctetString;


//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class Modules implements MOGroup {

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(Modules.class);

  private SNMPv2Conf sNMPv2Conf;
  private SNMPv2Smi sNMPv2Smi;
  private SNMPv2Tc sNMPv2Tc;

  private MOFactory factory;

//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  public Modules() {
   sNMPv2Conf = new SNMPv2Conf(); 
   sNMPv2Smi = new SNMPv2Smi(); 
   sNMPv2Tc = new SNMPv2Tc(); 
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  public Modules(MOFactory factory) {
   sNMPv2Conf = new SNMPv2Conf(factory); 
   sNMPv2Smi = new SNMPv2Smi(factory); 
   sNMPv2Tc = new SNMPv2Tc(factory); 
//--AgentGen BEGIN=_CONSTRUCTOR
//--AgentGen END
  } 

  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
	  sNMPv2Conf.registerMOs(server, context);
	  sNMPv2Smi.registerMOs(server, context);
	  sNMPv2Tc.registerMOs(server, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
	  sNMPv2Conf.unregisterMOs(server, context);
	  sNMPv2Smi.unregisterMOs(server, context);
	  sNMPv2Tc.unregisterMOs(server, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  public SNMPv2Conf getSNMPv2Conf() {
    return sNMPv2Conf;
  }
  public SNMPv2Smi getSNMPv2Smi() {
    return sNMPv2Smi;
  }
  public SNMPv2Tc getSNMPv2Tc() {
    return sNMPv2Tc;
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END

}

