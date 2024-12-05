
package jarvis;


public class Dispositivo {
    
    private String nombre;
    private double consumoDeEnergia;
    private boolean funcionando;
    
public Dispositivo(String nombre,int consumoDeEnergia){

    this.nombre = nombre;
    this.consumoDeEnergia = consumoDeEnergia;
    this.funcionando = true;
}    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getConsumoDeEnergia() {
        return consumoDeEnergia;
    }

    public void setConsumoDeEnergia(double consumoDeEnergia) {
        this.consumoDeEnergia = consumoDeEnergia;
    }

    public boolean isFuncionando() {
        return funcionando;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }
//------------------------------------------------------------------------------
public double usar(NivelConsumo intensidad, int tiempo){
/*A veces los dispositivos de la armadura sufren daños para esto cada dispositivo contiene un 
 atributo que dice si el dispositivo se encuentra dañado o no. Al utilizar un dispositivo existe un 
 30% de posibilidades de que se dañe.
 La armadura solo podrá utilizar dispositivos que no se encuentren dañados.*/

    double resultado=0;

    if (intensidad == NivelConsumo.NORMAL){
    resultado = (tiempo * consumoDeEnergia)*2 ;
    }
    if (intensidad == NivelConsumo.INTENSIVO){
    resultado =  (tiempo * consumoDeEnergia)*3 ;       
    }
    if(intensidad == NivelConsumo.BASICO){
    resultado =tiempo * consumoDeEnergia ;
    }
    sufriendoDaños();
    
    return resultado;
} 
//------------------------------------------------------------------------------        
public void sufriendoDaños(){        
/* Al utilizar un dispositivo existe un 30% de posibilidades de que se dañe. */       
    if (Math.random() <= 0.3){
        funcionando = false;
        System.out.println("El dispositivo "+this.nombre +" se ha dañado durante su uso");
    }          
}      
//---------------------------------------------------------------------------------------------------------------
public void repararDaños(){
/*Reparando Daños   
Hay veces que se puede reparar los daños de un dispositivo, en general es el 40% de las veces que 
se puede hacer. Utilizar la clase Random para modelar este comportamiento.  En caso de estar 
dentro de la probabilidad (es decir probabilidad menor o igual al 40%) marcar el dispositivo como 
sano. Si no dejarlo dañado.*/
    if (Math.random() <= 0.4){
        funcionando = true;
        System.out.println("El dispositivo " + this.nombre + " ha sido reparado");
    } 
}    
//------------------------------------------------------------------------------
//public String infoDispositivo1(){
//
//    return "---------------------------------------------"+"\n"   
//           +"Dispositivo : "+ nombre +"\n" 
//           +"Consumo De Energia = " + consumoDeEnergia +"\n" 
//           +"Funcionando        = " + funcionando + "\n" 
//           +"---------------------------------------------";
//}
public String infoDispositivo() {
    StringBuilder info = new StringBuilder();
    info.append("---------------------------------------------\n")
        .append("Dispositivo : ").append(nombre).append("\n")
        .append("Consumo De Energia = ").append(consumoDeEnergia).append("\n")
        .append("Funcionando        = ").append(funcionando).append("\n")
        .append("---------------------------------------------");
    return info.toString();
}
}//fin class
