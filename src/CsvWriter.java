import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase para crear y escribir sobre el archivo .cvs los datos dados por la clase TestClass
 */
public class CsvWriter {

    /**
     * Funcion donde se creara el archivo para los algoritmos de ordenamiento
     */
    void writeCsvSortAlgorithm() {
        TestClass testObject = new TestClass();
        String archivo = "Sort Algorithms Time Data.csv"; // nombre del archivo
        System.out.println("\n-Creando archivo con datos de algoritmos de ordenamiento");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) { // creamos un objeto de tipo BufferedWriter para escribir en el archivo
            writer.write("Algorithm,100,1.000,10.000,100.000,1.000.000,10.000.000,100.000.000");// asignamos nombres para las columnas
            writer.newLine();
            testObject.createTimeListSortAlgorithm(); // Llamamos la funcion donde se ejecutaran todos los algoritmos y se guardara su informacion
            //en el arraylist algorithmTimes
            for (String[] row : TestClass.algorithmTimes) {// escribimos los datos del arraylist en el archivo
                writer.write(String.join(",", row));
                writer.newLine();
            }
            System.out.println("Archivo "+archivo +" fue creado con exito");
        } catch (IOException e) {//para manejar errores
            throw new RuntimeException("Error al escribir el archivo", e);
        }
    }

    /**
     * Funcion donde se creara el archivo para los algoritmos de busqueda
     * @param num numero a buscar
     * @param worstCase booleano para verificar si es el caso estandar o el peor caso
     */
    void writeCsvSearchAlgorithm(int num, boolean worstCase) {
        TestClass testObject = new TestClass();
        String archivo;
        if(worstCase){// dependiendo de si es o no el peor caso, se le dara un nombre al archivo y se llamara su funcion correspondiente
            archivo = "Search Algorithms Worst Case Time Data.csv";
            testObject.createTimeListSearchAlgorithmWorstCase();
        }else{
            archivo = "Search Algorithms Time Data.csv";
            testObject.createTimeListSearchAlgorithm(num);
        }
        //las funciones ejecutaran los algoritmos y guardaran los tiempos en el arraylist algorithmTimes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("Algorithm,100,1.000,10.000,100.000,1.000.000,10.000.000,100.000.000"); // asignamos nombres para las columnas
            writer.newLine();
            System.out.println("\n-Creando archivo con datos de algoritmos de busqueda");
            for (String[] row : TestClass.algorithmTimes) { // escribimos los datos del arraylist en el archivo
                writer.write(String.join(",", row));
                writer.newLine();
            }
            System.out.println("Archivo "+archivo +" fue creado con exito");
        } catch (IOException e) {// para manejar errores
            throw new RuntimeException("Error al escribir el archivo", e);
        }
    }

}



