package manager;

import org.snmp4j.agent.mo.snmp.DateAndTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        Manager m = new Manager();

        ArrayList<Evento> evs = new ArrayList<Evento>();
        //  Eventos events = new Eventos();
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

       for(Evento e : evs){
           System.out.println("Event id: "+ e.getNome());
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

