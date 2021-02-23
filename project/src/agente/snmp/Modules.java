 

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

  private GrEventsMib grEventsMib;

  private MOFactory factory;

//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  public Modules() {
   grEventsMib = new GrEventsMib(); 
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  public Modules(MOFactory factory) {
   grEventsMib = new GrEventsMib(factory); 
//--AgentGen BEGIN=_CONSTRUCTOR
//--AgentGen END
  } 

  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
	  grEventsMib.registerMOs(server, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
	  grEventsMib.unregisterMOs(server, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  public GrEventsMib getGrEventsMib() {
    return grEventsMib;
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END

}

