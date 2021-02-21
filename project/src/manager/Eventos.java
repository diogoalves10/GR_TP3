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

    public void setEvento(Evento e){
        eventos.add(e);
    }

    public Evento getEvento(int i){
        return eventos.get(i);
    }

    public ArrayList<Evento>getEventos(){
        return new ArrayList<Evento>(eventos);
    }

    public int search(Evento e){

        return eventos.indexOf(e);
    }

    public void saveEventos(){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("/home/diogo/Desktop/GestãodeRedes/TP3/GR_TP3/project/eventosDB/eventos.json");
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
}
