
package jarvis;


public enum MaterialArmadura {
    
/*La escala Rockwell C (HRC) es una de las más comunes y se utiliza principalmente para 
medir la dureza de materiales duros, como aceros templados o aleaciones metálicas duras. 
La prueba en la escala HRC se realiza con un indentador de cono de diamante y una carga total de 150 kg.

A continuación te doy una referencia general de los valores en la escala Rockwell C:

Menos de 20 HRC: Materiales muy blandos, como metales no tratados térmicamente o 
metales más maleables como el cobre o el aluminio.
20-30 HRC: Metales con dureza moderada, incluyendo algunos aceros al carbono sin tratamiento térmico.
30-40 HRC: Aceros más resistentes y aleaciones tratadas térmicamente.
40-50 HRC: Aceros endurecidos utilizados en herramientas de corte o cuchillas.
50-60 HRC: Aceros templados de alta dureza y alta resistencia al desgaste,
           comúnmente usados en herramientas industriales, rodamientos, y cuchillas.
60-70 HRC: Materiales extremadamente duros, como ciertos aceros especiales y 
           carburo de tungsteno, que se utilizan en herramientas de corte y maquinaria de precisión.*/

    TITANIO("Titanio", 36, 40),
    ACERO_INOXIDABLE("Acero Inoxidable", 55, 60),
    CARBURO_DE_TUNGSTENO("Carburo de Tungsteno", 70, 80),
    ALUMINIO("Aluminio", 45, 50);

    private final String nombre;
    private final int durezaMinima;
    private final int durezaMaxima;

    MaterialArmadura(String nombre, int durezaMinima, int durezaMaxima) {
        this.nombre = nombre;
        this.durezaMinima = durezaMinima;
        this.durezaMaxima = durezaMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDurezaMinima() {
        return durezaMinima;
    }

    public int getDurezaMaxima() {
        return durezaMaxima;
    }
}
    
