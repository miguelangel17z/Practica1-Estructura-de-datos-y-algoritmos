import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase donde se haran las pruebas de los algoritmos, midiendo su tiempo en funcion del numero de elementos
 */
public class TestClass {
    public static ArrayList<String[]> algorithmTimes = new ArrayList<>();
    // arraylist de arrays tipo string que guardara los tiempos de ejecucion de cada algoritmo para luego escribirlo en un archivo csv

    int[] fullList(int n) { // funcion auxiliar para crear un array de datos en orden ascendente
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        return array;
    }

    static int[] randomList(int n) { // funcion auxiliar para crear un array de datos en orden aleatorio con datos de entre 0 y 100,000
        Random random = new Random();
        int[] randomArray = new int[n];
        for (int i = 0; i < n; i++) {
            randomArray[i] = random.nextInt(100000);
        }
        return randomArray;
    }

    /**
     * funcion para llamar la funcion auxiliar createTimeListAuxSortAlgorithm() y ejecutarla en todos los algoritmos disponibles.
     */
    void createTimeListSortAlgorithm() {
        algorithmTimes.clear(); //Limpiamos la lista para que no se mezcle con los datos de los algoritmos de busqueda
        createTimeListAuxSortAlgorithm("Bubble Sort");
        createTimeListAuxSortAlgorithm("Insertion Sort");
        createTimeListAuxSortAlgorithm("Shell Sort");
        createTimeListAuxSortAlgorithm("Quick Sort");
        createTimeListAuxSortAlgorithm("Merge Sort");
    }

    /**
     * funcion para llamar la funcion auxiliar createTimeListAuxSearchAlgorithm() y ejecutarla en todos los algoritmos disponibles.
     *
     * @param num numero a buscar
     */
    void createTimeListSearchAlgorithm(int num) {
        algorithmTimes.clear(); //Limpiamos la lista para que no se mezcle con los datos de los algoritmos de ordenamiento
        createTimeListAuxSearchAlgorithm("Lineal Search", 9, false);
        System.out.println();
        createTimeListAuxSearchAlgorithm("Binary Search", 8, false);
    }

    /**
     * funcion para llamar la funcion auxiliar createTimeListAuxSearchAlgorithm() y ejecutarla en todos los algoritmos disponibles.
     * No recibe parametro ya que el numero a buscar sera el ultimo numero de la lista
     */
    void createTimeListSearchAlgorithmWorstCase() {
        algorithmTimes.clear();
        createTimeListAuxSearchAlgorithm("Lineal Search", -1, true);
        createTimeListAuxSearchAlgorithm("Binary Search", -1, true);
    }

    /**
     * Funcion para ejecutar algun algoritmo de busqueda, esta se encarga de ejecutarlo en listas de longitud desde 100 hasta 100 millones, mulplicandose
     * de 10 en 10
     *
     * @param algorithm string que es el nombre del algoritmo a usar
     * @param num       entero a buscar
     * @param worstCase booleano para verificar si estamos en la prueba del peor caso,en especial en el caso de lineal search,
     *                  osea con los datos ordenamos de manera ascendente y buscando el ultimo elemento
     */
    private void createTimeListAuxSearchAlgorithm(String algorithm, int num, boolean worstCase) {
        sortAlgorithms sortAlgorithmObject = new sortAlgorithms();
        SearchAlgorithms algorithmObject = new SearchAlgorithms();
        if (!worstCase) {  // condicional debido a la interfaz por consola, esta solo muestra cuando se busca en un caso de numero aleatorios
            System.out.println(algorithm);
        }
        int n = 10;
        int i = 1;
        String[] staticAlgorithmTimes = new String[8]; // string[] estatico que guardara los datos de cada algoritmo y luego se agregaran al arraylist algorithmTimes
        staticAlgorithmTimes[0] = algorithm;
        while (n < 100000000 && i < 8) { // verifica que llegamos a nuestro valor limite o no hay mas espacio en el arreglo
            n *= 10; // n aumentara de magnitud por cada iteracion
            int[] array;
            if (worstCase) {//verificamos si es el peor caso y si lo es, se crea un arreglo ordenado de manera ascendente y se escoge num como el ultimo elemento
                array = fullList(n);
                num = array[n - 1];
            } else {
                array = randomList(n); // en caso de que sea una peruba estandar se llena de numeros aleatorios
            }
            if (algorithm.equals("Binary Search") && !worstCase) { // codicion para ordenar el arreglo en caso de binary search, para que funcione correctamente
                // se verifica que no sea el peor caso, ya que ese tendria la lista ordenada desde el inicio
                sortAlgorithmObject.mergeSort(array, 0, array.length - 1);
            }
            double time = timer(array, algorithmObject, algorithm, num, worstCase);
            // se llama la funcion timer para tomar el tiempo que tarda en ejecutarse y se guarda

            staticAlgorithmTimes[i] = String.valueOf(time); //agregamos el valor del timer en el string[]
            i++;
        }
        algorithmTimes.add(staticAlgorithmTimes);
    }


    /**
     * Funcion para ejecutar algun algoritmo de busqueda, esta se encarga de ejecutarlo en listas de longitud desde 100 hasta 100 millones, mulplicandose
     * de 10 en 10
     *
     * @param algorithm string que es el nombre del algoritmo a usar
     */
    private void createTimeListAuxSortAlgorithm(String algorithm) {
        sortAlgorithms algorithmObject = new sortAlgorithms();
        int n = 10;
        int i = 1;
        String[] staticAlgorithmTimes = new String[8]; // string[] estatico que guardara los datos de cada algoritmo y luego se agregaran al arraylist algorithmTimes
        staticAlgorithmTimes[0] = algorithm;
        while (n < 100000000 && i < 8) {
            n *= 10;
            //bubble sort e insertion sort estan solo hasta 100 mil por motivos de tiempo para probar el codigo, en el test se toman datos hata 1 millon-
            if (n == 100000000 && algorithm.equals("Quick Sort") || n == 1000000 && algorithm.equals("Bubble Sort") || n == 1000000 && algorithm.equals("Insertion Sort") || n == 100000000 && algorithm.equals("Shell Sort") ) { // condicional para evitar que el quick sort o el bubble sort haga 100 millones,
                // debido a que en este caso el quick sort no soporta cuando son 100 millones, solo hasta 10 millones. Y el bubble sort y insertion sort tarda demasiado para hacer la prueba.
                //Tambien el shell sort se tiene que poner un limite o sino tarda demasiado
                break;
            }
            int[] array = randomList(n); // se crea una lista con datos aleatorio
            double time = timer(array, algorithmObject, algorithm);
            // se llama la funcion timer para tomar el tiempo que tarda en ejecutarse y se guarda
            staticAlgorithmTimes[i] = String.valueOf(time);
            i++;
        }
        algorithmTimes.add(staticAlgorithmTimes);
    }

    /**
     * Funcion para verificar el tiempo que tarda el algoritmo en ejecutarse, esta hecha con sobrecarga de metodos por lo que
     * esta solo se encarga de los algoritmos de busqueda. Se le da el nombre del algoritmo como parametro y se ejecuta ese en especifico
     *
     * @param array        arreglo el cual se le hara el proceso
     * @param searchObject objeto tipo SearchAlgorithms para llamar a las funciones que ejecuten los algoritmos
     * @param algorithm    nombre del algoritmo a ejecutar
     * @param num          entero a buscar
     * @param worstCase    booleano para saber si es la prueba del peor caso
     * @return tiempo que demoro el algoritmo en milisegundos, tipo double
     */
    double timer(int[] array, SearchAlgorithms searchObject, String algorithm, int num, boolean worstCase) {
        long beginTime = 0, endTime = 0; // variables tipo long pa tomar el tiempo
        int indexResult = 0; // entero donde se guardara el resultado del algoritmo
        switch (algorithm) {
            case "Binary Search":
                beginTime = System.nanoTime(); // toma el tiempo en nanosegundos, debe ser este valor ya que con longitudes cortas
                //el resultado final con long puede dar muy cercano a 0 o 0
                indexResult = searchObject.binarySearch(array, num);
                endTime = System.nanoTime();
                if (!worstCase) { // Verifica para que solo se imprima en la prueba estandar y no para el worst case
                    System.out.print(indexResult + "|");
                }
                break;
            case "Lineal Search":
                beginTime = System.nanoTime();
                indexResult = searchObject.linearSearch(array, num);
                endTime = System.nanoTime();
                if (!worstCase) {
                    System.out.print(indexResult + "|");
                }
                break;
            default:
                throw new IllegalArgumentException("Algoritmo no reconocido: " + algorithm);
                // para manejar errores en caso de haber escrito el nombre mal
        }
        return (endTime - beginTime) / 1000000.0;  // se divide por 1 millon para convertilo a decimal
    }

    /** Funcion para verificar el tiempo que tarda el algoritmo en ejecutarse, esta hecha con sobrecarga de metodos por lo que
     * esta solo se encarga de los algoritmos de ordenamiento.
     * @param array arreglo al cual se le va a hacer el proceso
     * @param algorithmObject objeto tipo sortAlgorithms para llamar la funciones que ejecutaran los algoritmos
     * @param algorithm string que almacena el nombre del algoritmo a ejecutarse
     * @return tiempo que tardo el algoritmo en ejecutarse, tipo double
     */
    double timer(int[] array, sortAlgorithms algorithmObject, String algorithm) {
        long beginTime = 0, endTime = 0;
        //verifica el nombre del algoritmo y ejecuta especificamente ese
        switch (algorithm) {
            case "Shell Sort":
                beginTime = System.nanoTime();
                algorithmObject.shellSort(array);
                endTime = System.nanoTime();

                break;
            case "Bubble Sort":
                beginTime = System.nanoTime();
                algorithmObject.bubbleSort(array);
                endTime = System.nanoTime();

                break;

            case "Insertion Sort":
                beginTime = System.nanoTime();
                algorithmObject.selectionSort(array);
                endTime = System.nanoTime();

                break;
            case "Merge Sort":
                beginTime = System.nanoTime();
                algorithmObject.mergeSort(array, 0, array.length - 1);
                endTime = System.nanoTime();

                break;
            case "Quick Sort":
                beginTime = System.nanoTime();
                algorithmObject.quickSort(array, 0, array.length - 1);
                endTime = System.nanoTime();

                break;
            default:
                throw new IllegalArgumentException("Algoritmo no reconocido: " + algorithm);
                // para manejar errores en caso de haber escrito el nombre mal
        }

        return (endTime - beginTime) / 1000000.0; // devuelve el tiempo que tardo, convertido en decimales
    }

    /**
     * funcion main para ejecutar el programa, esta da una pequeña descripcion del programa y lo que hace, tambien da la forma de probarlo al poder insertar
     * un numero a buscar en las distintas listas que se crearan
     *
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        sortAlgorithms algorithmObject = new sortAlgorithms();
        SearchAlgorithms s = new SearchAlgorithms();
        System.out.println("TEST ALGORITMOS DE ORDENAMIENTO Y BUSQUEDA\nEste programa se basa en tomar las medidas del tiempo de algoritmos de ordenamiento y busqueda" +
                " en funcion del volumen de la entrada");
        System.out.println("\nA continuacion el programa buscara su dato en listas de longitud 100, 1.000, 100.000, 1.000.000, 10.000.000 y 100.000.000 con datos de valor entre 0 y 100.000 . Si lo " +
                "\nencuentra devolvera su indice correspondiente, sino esta en el conjunto el programa devolvera -1.");
        System.out.println("\nPor otro lado se crearan 3 archivos tipo csv con medidas de tiempo de algunos algoritmos de busqueda y ordenamiento, esto puede tardar unos minutos, recibira un mensaje" +
                " de exito\ncuando el proceso este terminado ");
        System.out.println("\nIngrese un numero a buscar");
        int num = input.nextInt();
        CsvWriter writer = new CsvWriter(); // crear objeto tipo CsvWriter donde se hara el resto de la ejecucion y la creacion del archivo
        writer.writeCsvSearchAlgorithm(num, false);
        writer.writeCsvSearchAlgorithm(num, true);
        writer.writeCsvSortAlgorithm();

        System.out.println();
        System.out.println("Proceso finalizado con exito");
    }
}