package pekan5;

import java.util.Scanner;

public class AntrianPasien {
    private class Node {
        int noAntrian;
        String namaPasien;
        String keluhan;
        Node next;

        Node(int noAntrian, String namaPasien, String keluhan) {
            this.noAntrian = noAntrian;
            this.namaPasien = namaPasien;
            this.keluhan = keluhan;
            this.next = null;
        }

        @Override
        public String toString() {
            return "[" + noAntrian + "] " + namaPasien + " - " + keluhan;
        }
    }

    private Node head;
    private Node tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void tambahPasien(int noAntrian, String nama, String keluhan) {
        Node newNode = new Node(noAntrian, nama, keluhan);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        System.out.println("\nData pasien berhasil ditambahkan!\n");
    }

    public void tampilkanAntrian() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
        } else {
            Node current = head;
            int index = 1;
            while (current != null) {
                System.out.println(index + ". " + current);
                current = current.next;
                index++;
            }
        }
    }

    public void hapusPasienPertama() {
        if (isEmpty()) {
            System.out.println("Tidak ada pasien dalam antrian.");
        } else {
            System.out.println("Pasien dengan nama " + head.namaPasien + " telah dilayani (dihapus dari antrian).");
            head = head.next;
            if (head == null) tail = null;
        }
    }

    public void cariPasien(String nama) {
        Node current = head;
        while (current != null) {
            if (current.namaPasien.equalsIgnoreCase(nama)) {
                System.out.println("Pasien " + nama + " ditemukan, nomor antrian: " + current.noAntrian + ", keluhan: " + current.keluhan);
                return;
            }
            current = current.next;
        }
        System.out.println("Pasien dengan nama " + nama + " tidak ada di antrian.");
    }

    public int hitungPasien() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AntrianPasien antrian = new AntrianPasien();

        while (true) {
            System.out.println("\n=== SISTEM ANTRIAN PASIEN KLINIK ===");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Tampilkan Antrian");
            System.out.println("3. Layani Pasien (Hapus Antrian Pertama)");
            System.out.println("4. Cari Pasien");
            System.out.println("5. Jumlah Pasien");
            System.out.println("6. Keluar");
            System.out.print("\nPilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nomor Antrian: ");
                    int no = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan Nama Pasien: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Keluhan: ");
                    String keluhan = scanner.nextLine();
                    antrian.tambahPasien(no, nama, keluhan);
                    break;
                case 2:
                    System.out.println("\n--- Daftar Antrian Pasien ---");
                    antrian.tampilkanAntrian();
                    break;
                case 3:
                    antrian.hapusPasienPertama();
                    break;
                case 4:
                    System.out.print("Masukkan nama pasien yang dicari: ");
                    String cari = scanner.nextLine();
                    antrian.cariPasien(cari);
                    break;
                case 5:
                    System.out.println("Jumlah pasien saat ini: " + antrian.hitungPasien());
                    break;
                case 6:
                    System.out.println("Anda keluar dari sistem.");
                    scanner.close();
                    return;
                default:
                    System.out.println("PILIHAN TIDAK VALID!");
            }
        }
    }
}
