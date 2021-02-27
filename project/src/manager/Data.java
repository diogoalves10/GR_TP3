package manager;

public class Data {

    Integer ano;
    Integer mes;
    Integer semana;
    Integer dia;
    Integer hora;
    Integer minuto;

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
        ano = (Integer.valueOf(data.split("-")[0]));
        mes = (Integer.valueOf(splited[1]));
        semana = (Integer.valueOf(splited[2]));
        dia = (Integer.valueOf(splited[3]));
        hora = (Integer.valueOf(splited[4]));
        minuto = (Integer.valueOf(splited[5]));

    }

    public boolean isZero(){
        if(ano == 0 && mes == 0 && semana == 0 && dia == 0 && hora == 0 && minuto == 0){
            return true;
        }
        return false;
    }
    public String toString(){
        return ano + "-" + mes + "-" + semana + "-" + dia + "-" + hora + "-" + minuto;
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
    public String decrementaData(String data){
        String result = "";
        parseData(data);

        decrementa();

        return this.toString();
    }
    public void incrementaAno(){
        ano++;
    }
    public void incrementaMes(){
        mes++;
        if(mes > 11){
            mes = 0;
            incrementaAno();
        }
    }
    public void incrementaSemana(){
        semana++;
        if(semana > 3){
            semana = 0;
            incrementaMes();
        }
    }
    public void incrementaDia(){
        dia++;
        if(dia > 30){
            dia = 0;
            incrementaSemana();
        }
    }
    public void incrementaHora(){
        hora++;
        if(hora > 23){
            hora = 0;
            incrementaDia();
        }
    }
    public void incrementaMinuto(){
        minuto++;
        if(minuto > 59){
            minuto = 0;
            incrementaHora();
        }
    }
    public void incrementa(){
        incrementaMinuto();
    }

    public String incrementaData(String data){
        String result = "";
        parseData(data);

        incrementa();

        return this.toString();
    }

}
