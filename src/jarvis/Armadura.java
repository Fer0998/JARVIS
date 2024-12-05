
package jarvis;

import java.util.Arrays;
import jarvis.NivelConsumo;
import jarvis.MaterialArmadura;


public class Armadura {
    
    private String colorPrimario;
    private String colorSecundario;
    private MaterialArmadura materialDeArmadura;
    private int resistenciaRockwellHRC;
    private int nivelDeSalud;
    private double energiaGenerador;
    private static final double ENERGIA_MAXIMA = 10000000;
    private static final int NIVEL_SALUD = 100;
    private static final int ENERG_BOTAS =200;
    private static final int ENERG_GUANTE =100;
    private static final int ENERG_CONSOLA =50;
    private static final int ENERG_SINTE =50;
    private Dispositivo botas;
    private Dispositivo guantes;  
    private Dispositivo consola;
    private Dispositivo sintetizador;
    
 public Armadura(String colorPrimario, String colorSecundario,String materialDeArmadura){
 
     this.colorPrimario = colorPrimario;
     this.colorSecundario = colorSecundario;
     this.materialDeArmadura =  tipoDeArmaduras(materialDeArmadura);
     // hacer un método privado que detecte cuatro tipos de armadura
     // y si no se selecciona ninguna que elija una por defecto
     this.resistenciaRockwellHRC = this.materialDeArmadura.getDurezaMaxima();
     // hacer un metodo privado que segun el material de la armadura tenga su nivel de resistencia 
     this.nivelDeSalud = NIVEL_SALUD;  
     this.energiaGenerador =ENERGIA_MAXIMA;
     this.botas = new Dispositivo("Botas",ENERG_BOTAS);
     this.guantes = new Dispositivo("Guantes",ENERG_GUANTE);
     this.consola = new Dispositivo("Consola",ENERG_CONSOLA);
     this.sintetizador = new Dispositivo("Sintetizador",ENERG_SINTE);
 }  
//---------------------------------------------------------------------------------------------------------------
    public String getColorPrimario() {
        return colorPrimario;
    }

    public void setColorPrimario(String colorPrimario) {
        this.colorPrimario = colorPrimario;
    }

    public String getColorSecundario() {
        return colorSecundario;
    }

    public void setColorSecundario(String colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

//--------------------------------------------------------------------------------------------------------------- 
private MaterialArmadura tipoDeArmaduras(String selec) {
/*1. Titanio (aleaciones de titanio)
Dureza Rockwell: Alrededor de 36-40 HRC.
Propiedades: El titanio es conocido por ser extremadamente resistente y ligero, 
con una excelente resistencia a la corrosión. Las aleaciones de titanio se usan 
en la industria aeroespacial y militar, lo que lo hace ideal para una armadura avanzada.
2. Acero Inoxidable (Tipo 440C)
Dureza Rockwell: Alrededor de 55-60 HRC.
Propiedades: Este tipo de acero es muy duro, resistente al desgaste y a la corrosión. 
Es utilizado en herramientas de corte y componentes de alta durabilidad.
3. Carburo de tungsteno
Dureza Rockwell: Puede superar los 70 HRC.
Propiedades: Uno de los materiales más duros conocidos. Es extremadamente resistente 
al desgaste y a la compresión, pero algo más frágil. Se usa en herramientas de corte de alto rendimiento.
4. Aluminio de alta resistencia (Serie 7000)
Dureza Rockwell: Alrededor de 45-50 HRB.
Propiedades: Muy ligero y resistente. Se utiliza en estructuras aeronáuticas 
y en vehículos ligeros. Aunque no es tan duro como el acero, su ligereza lo 
hace ideal para partes que necesitan ser móviles.*/
    return switch (selec.toLowerCase()) {
        case "titanio" -> MaterialArmadura.TITANIO;
        case "acero inoxidable" -> MaterialArmadura.ACERO_INOXIDABLE;
        case "carburo de tungsteno" -> MaterialArmadura.CARBURO_DE_TUNGSTENO;
        case "aluminio" -> MaterialArmadura.ALUMINIO;
        default -> MaterialArmadura.CARBURO_DE_TUNGSTENO;
    };

}   
//---------------------------------------------------------------------------------------------------------------  
public boolean revisarEnergia(double energiaNecesaria) {
    // Comprueba si hay suficiente energía para realizar la acción.
    if (energiaGenerador >= energiaNecesaria) {
        // Hay suficiente energía, la acción puede proceder.
        return true;
    } else {
        // No hay suficiente energía; recarga el generador y devuelve falso para indicar que fue necesario recargar.
        energiaGenerador = ENERGIA_MAXIMA;
        System.out.println("Energía insuficiente, recargando generador al máximo.");
        return false;
    }
}
//---------------------------------------------------------------------------------------------------------------  
public void caminar(int tiempo){
/* Al caminar la armadura hará un uso básico de las botas y se consumirá la energía establecida 
como consumo en la bota por el tiempo en el que se camine.   
• Cada vez que se efectúa una acción se llama a los métodos usar del dispositivo se le pasa el nivel 
de intensidad y el tiempo. El dispositivo debe retornar la energía consumida y la armadura deberá 
informar al generador se ha consumido esa cantidad de energía.  */

// primero se supone que el generador de energia es ilimitado
// solo se hace la resta para saber que porcentaje de energia se esta utilizando
// pero para que no se produzca un fallo deberia revisar y en el caso de quedar negativo
// poner en positivo

    // Comprueba si las botas están en funcionamiento
    if (!botas.isFuncionando()) {          
        System.out.println("El dispositivo " + botas.getNombre() + " se encuentra dañado.");
        System.out.println("No se puede realizar la acción.");
        return;
    }

    // Calcula la energía que se va a consumir
    double energiaConsumida = botas.usar(NivelConsumo.BASICO, tiempo);

    // Revisa si hay energía suficiente para realizar la acción
    if (!revisarEnergia(energiaConsumida)) {
        System.out.println("El generador fue recargado antes de caminar.");
    }

    // Realiza la acción de caminar y actualiza la energía del generador
    energiaGenerador = Math.max(0, energiaGenerador - energiaConsumida);

    // Calcula el porcentaje de energía consumida después de la actualización
    double porcentajeConsumido = (energiaConsumida / energiaGenerador) * 100;

    System.out.println("Se caminó durante " + tiempo + " segundos.");
    System.out.printf("La energía consumida es: %.2f %%\n", porcentajeConsumido);
}
//---------------------------------------------------------------------------------------------------------------   
public void correr(int tiempo){
/*Al correr la armadura hará un uso normal de las botas y se consumirá el doble de la energía 
establecida como consumo en la bota por el tiempo en el que se corra. */  
    if (!botas.isFuncionando()){          
        System.out.println("El dispositivo "+botas.getNombre()+" se encuentra dañado.");;
        System.out.println("No se puede realizar la acción");
        return;
    } 
    
    double energiaConsumida=botas.usar(NivelConsumo.NORMAL, tiempo); 
    //comprueba si tiene la cantidad de energia para realizar la accion
    revisarEnergia(energiaConsumida);
    double porcentajeConsumido = (energiaConsumida / energiaGenerador) * 100;
      
    System.out.println("Se corrió durante : " + tiempo + " segundos");
    System.out.printf("La energía consumida es: %.2f %%\n", porcentajeConsumido);
    // evita que la energia del generador quede negativa
    energiaGenerador = Math.max(0, energiaGenerador - energiaConsumida);
}
//---------------------------------------------------------------------------------------------------------------   
public void propulsar(int tiempo){
 /*Al propulsarse la armadura hará un uso intensivo de las botas utilizando el triple de la energía 
por el tiempo que dure la propulsión.*/
    if (!botas.isFuncionando()){          
        System.out.println("El dispositivo "+botas.getNombre()+" se encuentra dañado");
        System.out.println("No se puede realizar la acción");
        return;
    }    
    
    double energiaConsumida=botas.usar(NivelConsumo.INTENSIVO, tiempo); 
    //comprueba si tiene la cantidad de energia para realizar la accion
    revisarEnergia(energiaConsumida);
    double porcentajeConsumido = (energiaConsumida / energiaGenerador) * 100;
    
    System.out.println("Se propulsó durante : " + tiempo + " segundos");
    System.out.printf("La energía consumida es: %.2f %%\n", porcentajeConsumido);
    // evita que la energia del generador quede negativa
    energiaGenerador = Math.max(0, energiaGenerador - energiaConsumida);
} 
//---------------------------------------------------------------------------------------------------------------   
public void volar(int tiempo){
/*Al volar la armadura hará un uso intensivo de las botas y de los guantes un uso 
     normal consumiendo el triple de la energía establecida para las botas y el doble para los guantes*/

    if (!botas.isFuncionando()){          
        System.out.println("El dispositivo "+botas.getNombre()+" se encuentra dañado.");
        System.out.println("No se puede realizar la acción");
        return;       
    }
    if(!guantes.isFuncionando()){
        System.out.println("El dispositivo "+guantes.getNombre()+" se encuentra dañado.");
        System.out.println("No se puede realizar la acción");
        return;        
    }    
    
    double energiaConsumida=botas.usar(NivelConsumo.INTENSIVO, tiempo)
                        +guantes.usar(NivelConsumo.NORMAL, tiempo);  
    //comprueba si tiene la cantidad de energia para realizar la accion
    revisarEnergia(energiaConsumida);
    double porcentajeConsumido = (energiaConsumida / energiaGenerador) * 100;
    
    System.out.println("Se voló durante : " + tiempo + " segundos");
    System.out.printf("La energía consumida es: %.2f %%\n", porcentajeConsumido); 
    // evita que la energia del generador quede negativa
    energiaGenerador = Math.max(0, energiaGenerador - energiaConsumida);
}
//---------------------------------------------------------------------------------------------------------------
public void dispararGuante(int tiempo){
 /*Al utilizar los guantes como armas el consumo se triplica durante el tiempo del disparo. */
     if(!guantes.isFuncionando()){
        System.out.println("El dispositivo "+guantes.getNombre()+" se encuentra dañado.");
        System.out.println("No se puede realizar la acción");
        return;        
    }
     
    double energiaConsumida=guantes.usar(NivelConsumo.INTENSIVO, tiempo); 
    //comprueba si tiene la cantidad de energia para realizar la accion
    revisarEnergia(energiaConsumida);
    double porcentajeConsumido = (energiaConsumida / energiaGenerador) * 100;
    
    System.out.println("Se disparó durante : " + tiempo + " segundos");
    System.out.printf("La energía consumida es: %.2f %%\n", porcentajeConsumido);
    // evita que la energia del generador quede negativa
    energiaGenerador = Math.max(0, energiaGenerador - energiaConsumida);
}
//---------------------------------------------------------------------------------------------------------------
public void leer(int tiempo){
/*Cada vez que se escribe en la consola o se habla a través del sintetizador se consume lo 
establecido en estos dispositivos. Solo se usa en nivel básico.   */   
    if(!sintetizador.isFuncionando()){
        System.out.println("El dispositivo "+sintetizador.getNombre()+" se encuentra dañado.");
        System.out.println("No se puede realizar la acción");
        return;        
    }
    
    double energiaConsumida=sintetizador.usar(NivelConsumo.BASICO, tiempo);
    //comprueba si tiene la cantidad de energia para realizar la accion
    revisarEnergia(energiaConsumida);
    double porcentajeConsumido = (energiaConsumida / energiaGenerador) * 100;
    
    System.out.println("Se leyó durante : " + tiempo + " segundos");
    System.out.printf("La energía consumida es: %.2f %%\n", porcentajeConsumido); 
    // evita que la energia del generador quede negativa
    energiaGenerador = Math.max(0, energiaGenerador - energiaConsumida);
}
//---------------------------------------------------------------------------------------------------------------
public void escribir(int tiempo){
/*Cada vez que se escribe en la consola o se habla a través del sintetizador se consume lo 
establecido en estos dispositivos. Solo se usa en nivel básico.   */   
    if(!consola.isFuncionando()){
        System.out.println("El dispositivo "+consola.getNombre()+" se encuentra dañado.");
        System.out.println("No se puede realizar la acción");
        return;        
    }
    
    double energiaConsumida=consola.usar(NivelConsumo.BASICO, tiempo);
    //comprueba si tiene la cantidad de energia para realizar la accion
    revisarEnergia(energiaConsumida);
    double porcentajeConsumido = (energiaConsumida / energiaGenerador) * 100;
    
    System.out.println("Se escribió durante : " + tiempo + " segundos");
    System.out.printf("La energía consumida es: %.2f %%\n", porcentajeConsumido);
    // evita que la energia del generador quede negativa
    energiaGenerador = Math.max(0, energiaGenerador - energiaConsumida);
}
//---------------------------------------------------------------------------------------------------------------
public void estadoDispositivos(){ 
/*Hacer un método que JARVIS muestre el estado de todos los dispositivos y toda la información 
de la Armadura.*/   
// Usar StringBuilder para construir la cadena de estado
    StringBuilder estado = new StringBuilder();
    estado.append("Armadura\n")
          .append("---------------------------------------------\n")
          .append(String.format("Colores              = %s %s\n", colorPrimario, colorSecundario))
          .append(String.format("Material             = %s\n", materialDeArmadura))
          .append(String.format("Nivel de resistencia = %.2f HRC\n", resistenciaRockwellHRC))
          .append(String.format("Nivel de salud       = %s\n", nivelDeSalud))
          .append(String.format("Nivel de energía     = %.2f %%\n", estadoDeBateria()))
          .append(botas.infoDispositivo()).append("\n")
          .append(guantes.infoDispositivo()).append("\n")
          .append(consola.infoDispositivo()).append("\n")
          .append(sintetizador.infoDispositivo()).append("\n")
          .append("---------------------------------------------");

    // Imprimir el estado de la armadura
    System.out.print(estado.toString());
}
//---------------------------------------------------------------------------------------------------------------
public double estadoDeBateria(){
/*Hacer un método para que JARVIS informe el estado de la batería en porcentaje a través de la 
consola. Poner como carga máxima del reactor el mayor float posible. Ejecutar varias acciones y 
mostrar el estado de la misma.   */
    return (energiaGenerador/ENERGIA_MAXIMA )*100;   
}
//---------------------------------------------------------------------------------------------------------------
public void infoReactor(){
/*Mostrar Información del Reactor   
Hacer un método para que JARVIS informe el estado del reactor en otras dos unidades de medida. 
Hay veces en las que Tony tiene pretensiones extrañas. Buscar en Wikipedia la tabla de 
transformaciones.*/

/* El reactor de Iron Man, conocido como el Arc Reactor, se basa en la ciencia de la fusión nuclear. 
Genera energía utilizando un núcleo de paladio y un anillo de electroimanes que crean una reacción 
energética intensa, similar a la energía liberada en la fusión, pero a menor escala. Aunque en la 
vida real la energía de la fusión nuclear se mide en joules o megajoules, en el caso del Arc Reactor,
es más un dispositivo ficticio que genera cantidades masivas de energía para alimentar tanto el traje
de Iron Man como otros sistemas tecnológicos avanzad
Sí, podríamos suponer que la energía del Reactor Arc de Iron Man podría medirse en
joules (J) o, más apropiadamente para su escala, en megajoules (MJ). 
Esto se basa en que el reactor es un dispositivo de fusión nuclear que genera enormes 
cantidades de energía, comparable a los reactores de la vida real que también utilizan
estas unidades de medida. Aunque el Arc Reactor es ficción, su función se aproxima a 
tecnologías que en el mundo real medirían la energía en esas unidades
En muchos lenguajes de programación, el valor máximo de un número de tipo float está definido 
como el mayor número que se puede almacenar antes de que ocurra una pérdida de precisión. 
Por ejemplo, en Java y muchos otros lenguajes, el valor máximo de un float es aproximadamente:

Float.MAX_VALUE=3.4028235×10^38
 
Paso 1: Representar el valor máximo de float como MJ
Si interpretamos este valor como megajoules (MJ), entonces tenemos:
MJ=3.4028235×10^38 MJ
Paso 2: Convertir megajoules (MJ) a kilovatios-hora (kWh)
Sabemos que:
1MJ=0.27778kWh
Por lo tanto, para convertir el valor máximo de float en megajoules a kilovatios-hora:
3.4028235×10^38 MJ × 0.27778 kWh/MJ = 9.4538674 ×10^37 kWh

*/
    double elec = (energiaGenerador * 0.27778);
    double si = (energiaGenerador * 1000000);
    double cal= (energiaGenerador * 239006.1);
    
    System.out.printf("MEGAJOULE (MJ)\n");
    System.out.printf("------------------\n");
    System.out.printf("Estado del Reactor : %.2f MJ\n\n", energiaGenerador);

    System.out.printf("SISTEMA INTERNACIONAL DE UNIDADES (SI)\n");
    System.out.printf("-------------------------------------\n");
    System.out.printf("Estado del Reactor : %.2f J\n\n", si);

    System.out.printf("ELECTRICIDAD (kWh)\n");
    System.out.printf("------------------\n");
    System.out.printf("Estado del Reactor : %.2f kWh\n\n", elec);

    System.out.printf("CALOR (cal)\n");
    System.out.printf("------------------\n");
    System.out.printf("Estado del Reactor : %.2f cal\n\n", cal);
    System.out.println("");
} 
//---------------------------------------------------------------------------------------------------------------
public void revisandoDispositivos(){    
    /*Revisando Dispositivos   
Los dispositivos son revisados por JARVIS para ver si se encuentran dañados. En caso de 
encontrar un dispositivo dañado se debe intentar arreglarlo de manera insistente. Para esos intentos 
hay un 30% de posibilidades de que el dispositivo quede destruido, pero se deberá intentar 
arreglarlo hasta que lo repare, o bien hasta que quede destruido.   
Hacer un método llamado revisar dispositivos que efectúe lo anteriormente descrito, el mecanismo 
insistente debe efectuarlo con un bucle do while.   */

    Dispositivo dis[] = {botas, guantes, consola, sintetizador};

    System.out.println("REVISANDO DISPOSITIVOS ... ");
    if (botas.isFuncionando() && guantes.isFuncionando() && consola.isFuncionando() && sintetizador.isFuncionando()) {
        System.out.println("Todos los dispositivos funcionan correctamente");
        return;
    }
    for (int i = 0; i < dis.length; i++) {
        if (!dis[i].isFuncionando()) {
            System.out.println("Intentando arreglar el dispositivo ... " + dis[i].getNombre());
            do {//si la probabilidad es menor o igual el dispositivo queda destruido
                if (Math.random() <= 0.3) {
                    System.out.println("El dispositivo " + dis[i].getNombre() + " quedó destruido");
                    break;
                } else {
                    //si no se destruye intentamos repararlo
                    dis[i].repararDaños();
                    if (dis[i].isFuncionando()) {                      
                        break;
                    }
                }
            } while (true);
        }
    }
}
//---------------------------------------------------------------------------------------------------------------
public void agregarObjetoEnRadar(int obj[][]) {
// hacer el metodo agregarObjetoEnRadar. el metodo va a agregar un objeto a la matriz
// y lo va a hacer dandole valores de manera aleatoria. se le pasa una matriz y devuelve una matriz
// con un objeto agregado si hay lugar.

    boolean control = false;
    for (int i = 0; i < obj.length; i++) {
        if (obj[i][0] == -1) {//hay un lugar
            for (int j = 0; j < obj[i].length; j++) {
                switch (j) {
                    case 3:
                        //en la posicion 3 genera la hostilidad (0 si no es hostil y 1 hostil)
                        obj[i][j] = (int) (Math.random() * 2);
                        break;
                    case 4:
                        //genera el valor de la resistencia del objeto en una escala entre 0 y 80 HRC
                        obj[i][j] = (int) (Math.random() * 80);
                        break;
                    default:
                        // son las coordeandas x,y,z en las posiciones 0,1,2 respectivamente
                        obj[i][j] = (int) (Math.random() * 5000/*Integer.MAX_VALUE*/);
                        break;
                }
            }
            System.out.println("Se agregó un objeto en la posicion " + i);
            control = true;
            break;
        }
    }// Si no se encontró ningún lugar disponible
    if (!control) {
        System.out.println("El radar está lleno, no se puede agregar otro objeto.");
    }
}
//---------------------------------------------------------------------------------------------------------------
public void distancia(int obj[][]) {
/*Hacer un método que informen a qué distancia se encuentra cada uno de los enemigos.
Usar la clase Math de Java.   */

    double distancia;
    int objDetectados = 0;

// buscamos cuantos objetos posee la matriz
    for (int i = 0; i < obj.length; i++) {
        if (obj[i][0] != -1) {
            objDetectados++; // Aumentamos el contador si la fila no está vacía
        } else {
            break; // Si encontramos un -1, asumimos que ya no hay más objetos
        }
    }
    System.out.println("");
    System.out.println("DISTANCIA (objetos detectados) : " + objDetectados);// informar cuantos objetos reconocio
    System.out.println("---------");


    for (int i = 0; i < objDetectados; i++) {
// formula de distancia euclediana es distancia 
        distancia = Math.sqrt(Math.pow(obj[i][0], 2) + Math.pow(obj[i][1], 2) + Math.pow(obj[i][2], 2));
// mostramos por pantalla la distancia de cada objeto
        System.out.printf("Distancia a objeto %d es: %.2f\n", i, distancia);
    }
    System.out.println("");
    }
//---------------------------------------------------------------------------------------------------------------
public int[][] inicializarMatriz(int filas, int columnas) {
// Método para inicializar la matriz    
    int matriz[][] = new int[filas][columnas];

// Inicializar la matriz con -1 para indicar que está vacía
//    for (int i = 0; i < matriz.length; i++) {
//        for (int j = 0; j < matriz[i].length; j++) {
//            matriz[i][j] = -1;
//        }
//    }
    for (int[] fila : matriz) {
        Arrays.fill(fila, -1); // llena la fila con -1
    }
    return matriz;
    }
//---------------------------------------------------------------------------------------------------------------
public int[][] simulador(){
/*Hacer un método en JARVIS que agregue en radar objetos, hacer que la resistencia, 
las coordenadas y la hostilidad sean aleatorios utilizando la clase Random. 
Qué ocurre si quiero añadir más de 10 objetos?   
¿Qué ocurre si cuando llevo 8 enemigos aumento la capacidad del vector?  */

    int objeto [][] = inicializarMatriz(10,5);
    
     // Generar aleatoriamente la cantidad de objetos (entre 1 y 10)
    int cantObj = (int)(Math.random()*10)+1;
    
    // Agregar esa cantidad de objetos al radar
    for(int i=0;i<cantObj;i++){
        agregarObjetoEnRadar(objeto);
    }
    
    return objeto;// Devolver la matriz actualizada
}
//----------------------------------------------------------------------------------
public void destruir(int objeto[][]){
/*JARVIS al detectar un enemigo lo atacará hasta destruirlo, la potencia del disparo es inversamente 
proporcional a la distancia al a que se encuentra el enemigo y se descontará de la resistencia del 
enemigo. El enemigo se considera destruido si su resistencia es menor o igual a cero.   
JARVIS solo podrá disparar si el dispositivo está sano y si su nivel de batería lo permite.  Si tiene 
los dos guantes sanos podrá disparar con ambos guantes haciendo más daño.  Resolver utilizando 
un for each para recorrer el arreglo y un while para destruir al enemigo.*/
// tengo que corregir que en la armadura los dispositivos guantes y botas son cuatro 

// en el radar en la posicion 3 genera la hostilidad (0 si no es hostil y 1 hostil)
//en la posicion 4 genera el valor de la resistencia del objeto en una escala entre 0 y 80 HRC

// recorrer la matriz buscando obj[0..9][3]=1
// si es igual a 1, hay que verificar si esta al alcance, menos de 5000 metros
// si esta a mas de 5000 no se dispara, sino entra en un bucle hasta destruir
// el enemigo se considera destruido si su resistencia es menor o igual a cero
// la potencia del dispara es inversamente proporcional a la distancia, se descontara de la resistencia del enemigo
// en este caso de la posicion 4
// solo se dispara o se usa el dispositivo si este esta sano y el nivel de bateria lo permite
    
    for(int i=0;i<objeto.length;i++){     
        double distancia = Math.sqrt(Math.pow(objeto[i][0], 2) + Math.pow(objeto[i][1], 2) + Math.pow(objeto[i][2], 2));
        int tiempo=200;
        
        if((objeto[i][3]==1)&&(distancia <= 5000)){
            System.out.println("");
            System.out.println("Se detectó objeto hostil "+i+" a una distancia "+(int)distancia+" con resistencia "+objeto[i][4]);
            System.out.println("Iniciando ataque....");
            int bat = 10;//(int)estadoDeBateria();
            //mientras la resistencia sea mayor que cero y el guante este funcionando y tenga bateria mayor a 10%
            while ((objeto[i][4]>0)&&(guantes.isFuncionando())&& (bat > 10)){             
               // dispararGuante(tiempo);
                //FORMULA PARA LA POTENCIA DEL DISPARO
                //Consumo de energia para el disparo = consumo de energia del guanto *3*tiempo
                //Consumo de energia = 30 * 3* 200 = 18000
                //Potencia de disparo = (Consumo de energia/ Distancia)
                int cantEnerg = (int)guantes.usar(NivelConsumo.INTENSIVO, tiempo);
                energiaGenerador-=cantEnerg;
                int potenciaDisparo = (int)(cantEnerg /distancia);
                System.out.println("La potencia del disparo es : "+potenciaDisparo);         
                //Reducir la resistencia del enemigo
                objeto[i][4]-=potenciaDisparo;
                System.out.println("resistencia : "+objeto[i][4]);               
            }
            
            if(objeto[i][4]<=0){
                System.out.println("");
                System.out.println("El enemigo ha sido destruido...");
                System.out.println("Se libero un espacio en el radar");
                for (int h=0;h<5;h++)
                objeto[i][h]=-1;// como se elimino se pone de nuevo en -1
            }else if(!guantes.isFuncionando()){
                System.out.println("");
                System.out.println("El guante se ha dañado. Suspender ataque....");
                System.out.println("");
                break;
            }else{
                System.out.println("El nivel de bateria es bajo, menor a 10%.");
                System.out.println("Suspender ataque....");
                System.out.println("Iniciando maniobra de evasión....");
                System.out.println("Alejarse "+Math.round(10000-distancia)+" metros, para estar a salvo");
                System.out.println("");
           /*  Modificar ese método para que si el nivel de batería es menor al 10% se corten los 
        ataques y se vuelve lo suficientemente lejos para que el enemigo no nos ataque. Deberíamos 
        alejarnos por lo menos 10 km enemigo. Tener en cuenta que la velocidad de vuelo promedio es de 
        300 km / hora.*/  
                //velocidad = (distancia/tiempo)
                //300 km/h = (10km/tiempo)
                //tiempo = (distancia/velocidad)
                //tiempo = (10km / 300km/h) 1km= 1000m 1h=3600s
                //tiempo = (10000m - distancia /300000m)* 3600 s

                tiempo = (int)(((10000 - distancia)/300000)*3600);
                volar(tiempo);
           
                break;     

            }
    }
        //verificar la bateria, y modificar la version que usa uno o ambos guantes 

    }
    


}


}// fin class
