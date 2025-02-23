public class Borraresto {
    private static void merge(int arr[], int low, int mid, int high) {
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

    private static void mergeSort(int arr[], int low, int high) {
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

    private static void swap (int arr[], int i, int j) {
        int aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }

    private static int partitioning (int arr[], int low, int high) {
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

    private static void quickSort (int arr[], int low, int high) {
        if (low < high) {
            // Hallar la posición correcta del pivote
            int j = partitioning(arr,low,high);
            // Ordenar el subarreglo izquierdo, es decir, los elementos menores al pivote
            quickSort(arr,low,j-1);
            // Ordenar el subarreglo derecho, es decir, los elementos mayores al pivote
            quickSort(arr,j+1,high);
        }
    }

    private static int linearSearch (int arr[], int n, int num) {
        // Recorrer todo nuestro arreglo
        for (int i = 0; i < n; i++ ){

            // Si el elemento actual 'i' es igual al número que buscamos
            if (arr[i] == num) {
                return i; // Retornar la posición en la que está el número que buscamos
            }
        }
        // Si el elemento no se encuentra, retornar -1
        return -1;
    }

    private static boolean binarySearch(int arr[], int n, int num) {
        int low = 0;
        int high = n - 1; // Fin del arreglo


        while (low <= high) {
            int mid = ((high - low) / 2) + low;

            // Caso base, si el elemento está en el medio, devolver true
            if (arr[mid] == num) {
                return true;
            }
            // Si el elemento es menor, buscar en la mitad izquierda
            else if (num < arr[mid]) {
                high = mid - 1;
            }
            // Si el elemento es mayor, buscar en la mitad derecha
            else {
                low = mid + 1;
            }
        }
        // Si el elemento no se encuentra, devolver false
        return false;
    }
} //End Main