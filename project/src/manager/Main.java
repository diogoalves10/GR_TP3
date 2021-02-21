package manager;

import org.snmp4j.agent.mo.snmp.DateAndTime;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
      Manager m = new Manager();
      Eventos evs = new Eventos();

      Scanner sc = new Scanner(System.in);

      evs.loadEventos();

      for(Evento e : evs.getEventos()){
          int id;
          id = m.vbToArray(m.getBulk(m.OIDsEventosTable)).size();
          m.insert(id+1,e);
      }

      
      System.out.println("Deseja procurar um evento? (s/n)");
      String opt = sc.next();

    }
}
