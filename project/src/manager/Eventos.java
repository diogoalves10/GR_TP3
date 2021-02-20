package manager;

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

    public int search(Evento e){

        return eventos.indexOf(e);
    }
}
