/**
 * Clase con todos los algoritmos de ordenamiento a implementar
 */
public class sortAlgorithms {


    int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {// recorre todo el arreglo
            int minimo = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minimo]) {// buscamos el valor minimo en cada iteracion
                    minimo = j;
                }

            }
            int aux = array[i]; // intercambiamos el valor minimo con los primeros elementos de la lista que no esten ordenados
            array[i] = array[minimo];
            array[minimo] = aux;
        }
        return array; // retorna el array ordenado
    }


    int[] bubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {// recorremos la lista
            for (int j = 0; j < array.length - i; j++) {// segundo for para comparar cada par de numeros e intercambiarlos
                if (array[j] > array[j + 1]) { // verifica si el de la izquierda es mayor
                    int aux = array[j + 1];// los intercambia en caso de que si
                    array[j + 1] = array[j];
                    array[j] = aux;
                }// al final de cada iteracion el mayor de la lista terminara al final de la misma
            }
        }
        return array;
    }


    int[] shellSort(int[] array) {
        int gap = array.length / 2; // escogemos el intervalo, llamado gap que se ira diviendo a la mitad hasta que sea igual a 1
        while (gap > 0) {
            for (int i = gap; i < array.length; i++) { // recorremos el arreglo desde el indice gap
                int current = array[i]; // guardamos el valor actual
                int j = i;
                while (j >= gap && array[j - gap] > current) { // retrocedemos a paso de tamaño gap, a todos los elementos que sean mayor del actual
                    array[j] = array[j - gap]; // movemos el elemento que es mayor a current hacia la derecha
                    j -= gap;
                }
                array[j] = current; //colocamos el valor actual en la posicion correcta
            }
            gap = gap / 2; // dividimos el gap de nuevo
        }
        return array;
    }


    private void merge(int[] arr, int low, int mid, int high) {
        int lenLeftArr = (mid - low) + 1;
        int lenRigthArr = high - mid;

        // Crear arreglos temporales para almacenar los subarreglos izquierdo y derecho
        int leftArr[] = new int[lenLeftArr];
        int rightArr[] = new int[lenRigthArr];

        // Copiar los elementos del subarreglo izquierdo al arreglo leftArr
        for (int i = 0; i < lenLeftArr; i++) {
            leftArr[i] = arr[low + i];
        }
        // Copiar los elementos del subarreglo derecho al arreglo rightArr
        for (int j = 0; j < lenRigthArr; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Inicializar índices para recorrer los subarreglos y el arreglo original
        int i, j, k;
        i = 0; // Índice para leftArr
        j = 0; // Índice para rightArr
        k = low; // Índice para el arreglo original

        // Combinar los subarreglos izquierdo y derecho en el arreglo original
        while (i < lenLeftArr && j < lenRigthArr) {
            // Si el elemento actual de leftArr es menor o igual que el de rightArr
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i]; // Copiar el elemento de leftArr al arreglo original
                i++; // Mover al siguiente elemento en leftArr
            } else {
                arr[k] = rightArr[j]; // Copiar el elemento de rightArr al arreglo original
                j++; // Mover al siguiente elemento en rightArr
            }
            k++; // Mover al siguiente espacio en el arreglo original
        }

        // Si quedan elementos en leftArr, copiarlos al arreglo original
        while (i < lenLeftArr) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Si quedan elementos en rightArr, copiarlos al arreglo original
        while (j < lenRigthArr) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    void mergeSort(int[] arr, int low, int high) {
        if (low < high) {

            int mid = (low + high) / 2;

            // Ordenar la mitad izquierda del arreglo
            mergeSort(arr, low, mid);
            // Ordenar la mitad derecha del arreglo
            mergeSort(arr, mid + 1, high);

            // Combinar las dos mitades ya ordenadas
            merge(arr, low, mid, high);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }

    private int partitioning(int[] arr, int low, int high) {
        int pivot = arr[low]; // Elegir el primer elemento como pivote

        int i, j;
        i = low + 1; // Iniciar i justo después del pivote
        j = high; // Iniciar j al final del arreglo

        while (i <= j) {
            // Mover i hacia la derecha mientras los elementos sean menores o iguales al pivote
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            // Mover j hacia la izquierda mientras los elementos sean mayores que el pivote
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            // Si i y j no se han cruzado, intercambiar los elementos en i y j
            if (i < j) {
                swap(arr, i, j);
            }
        }
        // Intercambiar el pivote con el elemento en j
        swap(arr, low, j);
        return j;
    }

    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Hallar la posición correcta del pivote
            int j = partitioning(arr, low, high);
            // Evita recursión en rangos inválidos
            if (low < j - 1) {
                quickSort(arr, low, j - 1);
            }
            if (j + 1 < high) {
                quickSort(arr, j + 1, high);
            }
        }
    }

}







