package pekan8;

// Nama: Aufan Taufiqurrahman
// NIM: 2411532011

import java.util.Arrays;

public class TugasSortingLanjutan {
    
    public static void main(String[] args) {
        int[] array = {43, 23, 37, 47, 41, 19, 31, 29, 5, 3, 7, 13, 11, 17, 2};
        
        System.out.println("Deret awal: " + Arrays.toString(array));
        System.out.println("Algoritma: Insertion Sort");
        System.out.println();
        
        insertionSortWithSteps(array);
        
        System.out.println("Hasil: " + Arrays.toString(array));
    }

    public static void insertionSortWithSteps(int[] arr) {
        int stepCount = 1;
        
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; 
            int j = i - 1;      
            int originalPosition = i; 
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            arr[j + 1] = key;
            
            if ((j + 1) != originalPosition) {
                System.out.println("Langkah " + stepCount + ": " + Arrays.toString(arr));
                stepCount++;
            }
        }
        System.out.println();
    }
}