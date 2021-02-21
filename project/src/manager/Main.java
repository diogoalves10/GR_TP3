package manager;

import org.snmp4j.agent.mo.snmp.DateAndTime;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
      Manager m = new Manager();
      Eventos evs = new Eventos();
      Scanner sc = new Scanner(System.in);

      System.out.println("Benvindo ao gestor de eventos!");

      while(true) {
          System.out.println("O que deseja fazer?\n1 -> Adicionar eventos do ficheiro de de eventos;\n2 -> Procurar um evento;");
          int opt = sc.nextInt();
          switch(opt) {
              case 1:
                evs.loadEventos();

                for (Evento e : evs.getEventos()) {
                     int id;
                     id = m.vbToArray(m.getBulk(m.OIDsEventosTable)).size();
                     m.insert(id + 1, e);
                 }
                break;

              case 2:
                  System.out.println("Qual o nome do evento a procurar?");
                  String nome = sc.next();
                  m.searchEvent(nome);
                  break;

              default:
                  System.out.println("Opção inválida");
          }
      }
    }
}
