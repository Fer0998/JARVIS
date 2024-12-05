
package jarvis;

import java.util.Scanner;


public class JARVIS {

  
    public static void main(String[] args) {
        /*En esta primera etapa con una armadura podremos: caminar, correr, propulsar, volar, escribir y 
    leer.  
    • Al caminar la armadura hará un uso básico de las botas y se consumirá la energía establecida 
    como consumo en la bota por el tiempo en el que se camine.      */

        Armadura ar = new Armadura("Gris", "Rojo", "Acero");

        Scanner leer = new Scanner(System.in);

//        int tiempo = 4444;
//        System.out.println("CAMINAR : ");
//        ar.caminar(tiempo);
//        System.out.println("CORRER : ");
//        ar.correr(tiempo);
//        System.out.println("VOLAR : ");
//        ar.volar(tiempo);
//        System.out.println("PROPULSAR : ");
//        ar.propulsar(tiempo);
//        System.out.println("DISPARAR : ");
//        ar.dispararGuante(tiempo);
//        System.out.println("LEER : ");
//        ar.leer(tiempo);
//        System.out.println("ESCRIBIR : ");
//        ar.escribir(tiempo);
//        System.out.println("");
//        ar.revisandoDispositivos();
//        System.out.println("");
//        ar.estadoDispositivos();
//          Llamar al método simulador y obtener la matriz devuelta
//        int[][] radar = ar.simulador();
//        mostrarRadar(radar);
//        ar.agregarObjetoEnRadar(radar);
//        ar.agregarObjetoEnRadar(radar);
//     
//        ar.distancia(radar);
//        ar.destruir(radar);
//        
//        ar.agregarObjetoEnRadar(radar);
//        mostrarRadar(radar);
//        int[][] radar = ar.inicializarMatriz(10, 5);
//        ar.agregarObjetoEnRadar(radar);
//        ar.agregarObjetoEnRadar(radar);
//        ar.agregarObjetoEnRadar(radar);
//     
//  
//
//        mostrarRadar(radar);
//        ar.distancia(radar);
//        ar.destruir(radar);
//        ar.agregarObjetoEnRadar(radar);
//        ar.agregarObjetoEnRadar(radar);
//        mostrarRadar(radar);
//        System.out.printf("Nivel de energía     = %.2f %%\n", ar.estadoDeBateria());
    }
//--------------------------------------------------------------------------------
public static void mostrarRadar(int[][] radar) {
    // Ejemplo de uso de la matriz devuelta
    System.out.println("");
    System.out.println("Radar cargado con objetos:");
    for (int i = 0; i < radar.length; i++) {
        System.out.print("Objeto " + i + ": ");
        for (int j = 0; j < radar[i].length; j++) {
            System.out.print(radar[i][j] + " ");
        }
        System.out.println(); // Salto de línea después de cada fila
    }
}    
}

    
    

