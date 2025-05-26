package pekan4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pelanggan {
    String id;
    int jumlahPesanan;

    Pelanggan(String id, int jumlahPesanan) {
        this.id = id;
        this.jumlahPesanan = jumlahPesanan;
    }
}

public class MakananCepatSaji {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Pelanggan> queue = new LinkedList<>();

        System.out.print("Jumlah pelanggan: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.print("Masukkan ID pelanggan ke-" + (i + 1) + ":");
            String id = scanner.next();
            System.out.print("Masukkan jumlah pesanan: ");
            int jumlahPesanan = scanner.nextInt();
            if (jumlahPesanan < 1 || jumlahPesanan > 100) {
                System.out.println("Jumlah pesanan tidak valid. Harus 1-100.");
            } else {
                queue.add(new Pelanggan(id, jumlahPesanan));
            }
        }

        int waktu = 0;
        while (!queue.isEmpty()) {
            Pelanggan p = queue.poll();
            waktu += p.jumlahPesanan;
            System.out.println();
            System.out.println(p.id + " selesai dalam " + waktu + " menit");
        }

        scanner.close();
    }
}

