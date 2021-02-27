package manager;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.JsonGenerationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Eventos{
    private ArrayList<Evento> eventos;

    public Eventos(){
        eventos = new ArrayList<>();
    }

    public Eventos(ArrayList<Evento> e){
        eventos = new ArrayList<Evento>(e);
    }

    public void setEvento(Evento e,Integer id) {
        eventos.set(id-1,e);
    }

    public Evento getEvento(int i){
        return eventos.get(i);
    }

    public ArrayList<Evento>getEventos(){
        return new ArrayList<Evento>(eventos);
    }

    public void removeEvento(Integer id){
        eventos.remove(id-1);
    }

    public void removeEventos(Evento apagarEvento){
        eventos.remove(apagarEvento.getId()-1);
        System.out.println("Removi o "+apagarEvento.getId());
        for(Integer i =0; i<eventos.size();i++){
            eventos.get(i).setId(i+1);
        }
    }



    public int search(Evento e){

        return eventos.indexOf(e);
    }

    public void saveEventos(String nome){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("/home/diogo/Desktop/GestãodeRedes/TP3/GR_TP3/project/eventosDB/"+nome);
           Eventos e = new Eventos(eventos);
            mapper.defaultPrettyPrintingWriter().writeValue(json, e);
        }
        catch (JsonGenerationException ge) {
            ge.printStackTrace();
        }
        catch (JsonMappingException me) {
            me.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void loadEventos(){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("/home/diogo/Desktop/GestãodeRedes/TP3/GR_TP3/project/eventosDB/eventos.json");
           Eventos e = mapper.readValue(json, Eventos.class);

            eventos = new ArrayList<Evento>(e.getEventos());
        }
        catch (JsonGenerationException ge) {
            ge.printStackTrace();
        }
        catch (JsonMappingException me) {
            me.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void searchByName(String name, ArrayList<Evento> es){
        for(Evento e :  es){
            if(e.getNome().equals(name)){
                System.out.println("Desired event: " + e.toString());
            }
        }
    }

    public static void searchFutureEvents(ArrayList<Evento> es){
        for(Evento e : es){
            Data dt = new Data();
            dt.parseData(e.getDeltaT());
            if(!dt.isZero() && e.getPassou() == 0){
                System.out.println("Future event: " + e.toString());
            }
        }
    }
    public static void searchCurrentEvents(ArrayList<Evento> es){
        for(Evento e : es){
            Data dt = new Data();
            dt.parseData(e.getDeltaT());
            if(dt.isZero() && e.getPassou() == 0){
                System.out.println("Current event: " + e.toString());
            }
        }
    }
    public static void searchPastEvents(ArrayList<Evento> es){
        for(Evento e : es){
            Data dt = new Data();
            dt.parseData(e.getDeltaT());
            if(e.getPassou() == 1){
                System.out.println("Past event: " + e.toString());
            }
        }
    }


}
