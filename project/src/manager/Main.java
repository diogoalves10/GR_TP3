package manager;

//import org.snmp4j.agent.mo.snmp.DateAndTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        Manager m = new Manager();
        ArrayList<Evento> evs = new ArrayList<Evento>();
        Scanner sc = new Scanner(System.in);

        TreeMap<Integer,String> arrayids = new TreeMap<>(m.vbToArray(m.getBulk(m.OIDsEventosTableId)));
        TreeMap<Integer,String> arraynomes = new TreeMap<>(m.vbToArray(m.getBulk(m.OIDsEventosTableNome)));
        TreeMap<Integer,String> arrayduracao = new TreeMap<>(m.vbToArray(m.getBulk(m.OIDsEventosTableDuracao)));
        TreeMap<Integer,String> deltaT = new TreeMap<>(m.vbToArray(m.getBulk(m.OIDsEventosTableDeltaT)));
        TreeMap<Integer,String> dataLimite = new TreeMap<>(m.vbToArray(m.getBulk(m.OIDsEventosTableDataLimite)));
        TreeMap<Integer,String> passou = new TreeMap<>(m.vbToArray(m.getBulk(m.OIDsEventosTablePassou)));

        TreeMap finalNome = arraynomes;
        TreeMap finalDuracao = arrayduracao;
        TreeMap finalDeltaT = deltaT;
        TreeMap finalDataLimite = dataLimite;
        TreeMap finalpassou  = passou;

        arrayids.forEach((key, value) -> {
            Evento e = new Evento();
            e.setId(Integer.parseInt(value));
            e.setNome(finalNome.get(key).toString());
            e.setDuracao((Integer.parseInt(String.valueOf(finalDuracao.get(key)))));
            e.setDeltaT(finalDeltaT.get(key).toString());
            e.setdataLimite(finalDataLimite.get(key).toString());
            e.setPassou(Integer.parseInt(String.valueOf(finalpassou.get(key))));

            evs.add(e);
        });
        /*
        Evento f = new Evento(1, "Evento futuro", 5, "0-3-2-5-6", "2-4-5-0-0-2", 0);
        Evento c = new Evento(2, "Evento presente", 5, "0-0-0-0-0", "2-4-5-0-0-2", 0);
        Evento p = new Evento(3, "Evento passado", 5, "0-3-2-5-6", "2-4-5-0-0-2", 1);
        evs.add(f);
        evs.add(c);
        evs.add(p);
         */
        System.out.println("Welcome to SNMP event manager!");
        boolean exit = false;
        while(exit == false){
            System.out.println("What would you like to do?");
            System.out.println("1 -> Search event by name;");
            System.out.println("2 -> Search events still to come;");
            System.out.println("3 -> search events currently going;");
            System.out.println("4 -> search events already finished;");
            System.out.println("5 -> exit;");
            int opt = sc.nextInt();
            switch(opt){
                case 1:
                    System.out.println("What is the desired event name?");
                    String name = sc.next();
                    Eventos.searchByName(name, evs);
                    break;
                case 2:
                    System.out.println("Past events list:");
                    Eventos.searchPastEvents(evs);
                    break;
                case 3:
                    System.out.println("Present events list:");
                    Eventos.searchCurrentEvents(evs);
                    break;
                case 4:
                    System.out.println("Future events list:");
                    Eventos.searchFutureEvents(evs);
                    break;
                default:
                    break;
            }
        }

    }}
/*
      Evento event = new Evento("Primeiro teste",5,"2021-02-21T18:04:0.0","2021-02-21T18:09:0.0",false);

      events.setEvento(event);
      events.saveEventos();

      System.out.println("Bem vindo ao gestor de eventos!");

      while(true) {
          System.out.println("O que deseja fazer?\n1 -> Adicionar eventos do ficheiro de eventos;\n2 -> Procurar um evento;");
          int opt = sc.nextInt();
          switch(opt) {
              case 1:
                evs.loadEventos();

                for (Evento e : evs.getEventos()) {
                     int id;
                     id = m.vbToArray(m.getBulk(m.OIDsEventosTable)).size();
                     m
                             .insert(id + 1, e);
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

 */

