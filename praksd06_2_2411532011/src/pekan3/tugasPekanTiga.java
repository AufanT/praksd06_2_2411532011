package pekan3;

import java.util.*;

class Buku {
    String judul;

    public Buku(String judul) {
        this.judul = judul;
    }

    @Override
    public String toString() {
        return judul;
    }
}


class PerpustakaanMini {
    Stack<Buku> tumpukanBuku = new Stack<>();

    void tambahBuku(String judul) {
        tumpukanBuku.push(new Buku(judul));
        System.out.println("Buku \"" + judul + "\" telah ditambahkan");
    }

    public PerpustakaanMini() {
        String[] bukuAwal = {
        	    "Algoritma Dasar",
        	    "Struktur Data",
        	    "Basis Data",
        	    "Pemrograman Java",
        	    "Jaringan Komputer",
        	    "Sistem Operasi",
        	    "Kecerdasan Buatan"
        	};

        	for (String judul : bukuAwal) {
                tumpukanBuku.push(new Buku(judul)); 
            }
    }
    
    void ambilBuku() {
        if (!tumpukanBuku.isEmpty()) {
            Buku buku = tumpukanBuku.pop();
            System.out.println("Buku diambil: " + buku.judul);
        } else {
            System.out.println("Tumpukan buku kosong.");
        }
    }

    void lihatTumpukan() {
        if (tumpukanBuku.isEmpty()) {
			System.out.println("Tumpukan buku kosong.");
		} else {
			System.out.println("Tumpukan Buku Saat Ini: ");
			for (Buku buku : tumpukanBuku) {
				System.out.println(" - " + buku);
			}
		}
    }

    void cariBuku(String judul) {
        boolean ditemukan = false;
        for (Buku buku : tumpukanBuku) {
            if (buku.judul.equalsIgnoreCase(judul)) {
                ditemukan = true;
                break;
            }
        }
        if (ditemukan) {
            System.out.println("Buku \"" + judul + "\" ditemukan dalam tumpukan.");
        } else {
            System.out.println("Buku \"" + judul + "\" tidak ditemukan dalam tumpukan.");
        }
    }
}

public class tugasPekanTiga {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PerpustakaanMini perpustakaan = new PerpustakaanMini();

        int pilihan = 0;
        while (pilihan != 5) {
            System.out.println("\n=== MENU PERPUSTAKAAN MINI ===");
            System.out.println("1. Tambah Buku ke Tumpukan");
            System.out.println("2. Ambil Buku Teratas");
            System.out.println("3. Lihat Tumpukan Buku");
            System.out.println("4. Cari Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            
            if (scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                scanner.nextLine();

                if (pilihan == 1) {
                    System.out.print("Masukkan judul buku: ");
                    String judulBaru = scanner.nextLine();
                    perpustakaan.tambahBuku(judulBaru);
                } else if (pilihan == 2) {
                    perpustakaan.ambilBuku();
                } else if (pilihan == 3) {
                    perpustakaan.lihatTumpukan();
                } else if (pilihan == 4) {
                    System.out.print("Masukkan judul buku yang dicari: ");
                    String cariJudul = scanner.nextLine();
                    perpustakaan.cariBuku(cariJudul);
                } else if (pilihan == 5) {
                    System.out.println("Terima kasih telah menggunakan Perpustakaan Mini!");
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            } else {
                System.out.println("Input harus berupa angka.");
                scanner.nextLine(); 
            }
        }

        scanner.close();
    }
}
