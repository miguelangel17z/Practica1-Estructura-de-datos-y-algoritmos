public class SearchAlgorithms {

     int linearSearch(int[] arr, int num) {
        int n = arr.length;
        // Recorre todo nuestro arreglo
        for (int i = 0; i < n; i++) {

            // Si el elemento actual 'i' es igual al número que buscamos
            if (arr[i] == num) {
                return i; // Retornar la posición en la que está el número que buscamos
            }
        }
        // Si el elemento no se encuentra, retornar -1
        return -1;
    }

     int binarySearch(int[] arr, int num) {
        int n = arr.length;
        int low = 0;
        int high = n - 1; // Fin del arreglo


        while (low <= high) {
            int mid =  low + (high - low) / 2;


            // Caso base, si el elemento está en el medio, devolver el indice donde esta
            if (arr[mid] == num) {
                return mid;
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
        // Si el elemento no se encuentra, devolver -1
        return -1;
    }
}
