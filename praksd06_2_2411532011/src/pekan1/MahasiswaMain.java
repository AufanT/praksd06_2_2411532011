package pekan1;

import java.util.*;

public class MahasiswaMain {
    public static void main(String[] args) {
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
        Scanner scanner = new Scanner (System.in);
        int choice;
        do { 
            System.out.println("Menu:");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan semua mahasiswa");
            System.out.println("Hapus mahasiswa berdasarkan NIM");
            System.out.println("Cari mahasiswa berdasarkan NIM");
            System.out.println("Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Masukkan nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan prodi: ");
                    String prodi = scanner.nextLine();
                    mahasiswaList.add(new Mahasiswa(nim, nama, prodi));
                    break;
                case 2:
                    System.out.println("Data Mahasiswa: ");
                    for (Mahasiswa mhs : mahasiswaList) {
                        System.out.println(mhs);
                    }
                    break;
                case 3:
                    System.out.println("Masukkan NIM yang akan dihapus: ");
                    String nimHapus = scanner.nextLine();
                    mahasiswaList.removeIf(mhs -> mhs.nim.equals(nimHapus));
                    break;
                case 4:
                    System.out.print("Masukkan NIM yang dicari: ");
                    String nimCari = scanner.nextLine();
                    for (Mahasiswa mhs : mahasiswaList) {
                        if (mhs.nim.equals(nimCari)) {
                            System.out.println(mhs);
                            break;
                        } else {
                            System.out.println("nim tidak ada");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        } while (choice != 5);

        scanner.close();    
    }
}
