package pekan7;

public class TugasSorting {
        public static void insertionSort(char[] data) {
        for (int i = 1; i < data.length; i++) {
            char key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        // Deretan huruf dari z ke a
        char[] huruf = {
            'z','y','x','w','v','u','t','s','r','q','p','o','n',
            'm','l','k','j','i','h','g','f','e','d','c','b','a'
        };

        char[] dataSort = new char[11];
        for (int i = 0; i < 11; i++) {
            dataSort[i] = huruf[huruf.length - 11 + i];
        }

        insertionSort(dataSort);

        for (int i = 0; i < 11; i++) {
            huruf[huruf.length - 11 + i] = dataSort[i];
        }

        for (int i = 0; i < huruf.length; i++) {
            System.out.print(huruf[i]);
            if (i < huruf.length - 1) {
                System.out.print(" - ");
            }
        }
        System.out.println();
    }
}