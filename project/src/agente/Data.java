package agente;

public class Data {

    int ano;
    int mes;
    int semana;
    int dia;
    int hora;
    int minuto;

    public Data(){
        ano = 0;
        mes = 0;
        semana = 0;
        dia = 0;
        hora = 0;
        minuto = 0;
    }

    public void parseData(String data){

        String [] splited= data.split("-");
        ano = (Integer.parseInt(splited[0]));
        mes = (Integer.parseInt(splited[1]));
        semana = (Integer.parseInt(splited[2]));
        dia = (Integer.parseInt(splited[3]));
        hora = (Integer.parseInt(splited[4]));
        minuto = (Integer.parseInt(splited[5]));

    }

    public boolean isZero(){
        if(ano == 0 && mes == 0 && semana == 0 && dia == 0 && hora == 0 && minuto == 0){
            return true;
        }
        return false;
    }
    public void decrementaAno(){
        ano--;
        if(ano < 0){
            ano = 0;
        }
    }
    public void decrementaMes(){
        mes--;
        if(mes < 0){
            mes = 11;
            decrementaAno();
        }
    }
    public void decrementaSemana(){
        semana--;
        if(semana < 0){
            semana = 3;
            decrementaMes();
        }
    }
    public void decrementaDia(){
        dia--;
        if(dia < 0){
            dia = 30;
            decrementaSemana();
        }
    }
    public void decrementaHora(){
        hora--;
        if(hora < 0){
            hora = 23;
            decrementaDia();
        }
    }
    public void decrementaMinuto(){
        minuto--;
        if(minuto < 0){
            minuto = 59;
            decrementaHora();
        }
    }

    public void decrementa(){
        decrementaMinuto();
    }
    public String updateData(String data){
        String result = "";
        parseData(data);

        decrementa();

        return ano + "-" + mes + "-" + semana + "-" + dia + "-" + hora + "-" + minuto;
    }
}
