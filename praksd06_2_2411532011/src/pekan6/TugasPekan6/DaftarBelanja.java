package pekan6.TugasPekan6;

import java.util.Scanner;

public class DaftarBelanja {
    private Node head;
    private Node tail;

    public DaftarBelanja() {
        this.head = null;
        this.tail = null;
    }
    
    public void tambahItem(String nama, int kuantitas, String kategori) {
        itemBelanja item = new itemBelanja(nama, kuantitas, kategori);
        Node newNode = new Node(item);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        System.out.println("Item berhasil ditambahkan!");
    }
    
    public void hapusItem(String nama) {
        if (head == null) {
            System.out.println("Daftar belanja kosong.");
            return;
        }
        
        Node current = head;
        
        while (current != null) {
            if (current.item.getNama().equalsIgnoreCase(nama)) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else if (current == head) {
                    head = current.next;
                    head.prev = null;
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Item berhasil dihapus.");
                return;
            }
            current = current.next;
        }
        
        System.out.println("Item tidak ditemukan.");
    }
    
    public void tampilkanSemuaItem() {
        if (head == null) {
            System.out.println("Daftar belanja kosong.");
            return;
        }
        
        System.out.println("--- Daftar Belanja ---");
        Node current = head;
        while (current != null) {
            System.out.println(current.item.toString());
            current = current.next;
        }
    }
    
    public void tampilkanPerKategori(String kategori) {
        System.out.println("--- Item dalam kategori '" + kategori + "' ---");
        Node current = head;
        boolean found = false;
        
        while (current != null) {
            if (current.item.getKategori().equalsIgnoreCase(kategori)) {
                System.out.println("- " + current.item.getNama() + " (" + current.item.getKuantitas() + ")");
                found = true;
            }
            current = current.next;
        }
        
        if (!found) {
            System.out.println("Tidak ada item dalam kategori '" + kategori + "'.");
        }
    }
    
    public void cariItem(String nama) {
        if (head == null) {
            System.out.println("Daftar belanja kosong.");
            return;
        }
        
        Node current = head;
        while (current != null) {
            if (current.item.getNama().equalsIgnoreCase(nama)) {
                System.out.println("Item ditemukan:");
                System.out.println(current.item.toString());
                return;
            }
            current = current.next;
        }
        
        System.out.println("Item '" + nama + "' tidak ditemukan.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DaftarBelanja daftar = new DaftarBelanja();

        while (true) {
            System.out.println("\n=== MENU DAFTAR BELANJA ===");
            System.out.println("1. Tambah Item");
            System.out.println("2. Hapus Item");
            System.out.println("3. Tampilkan Semua Item");
            System.out.println("4. Tampilkan Item Berdasarkan Kategori");
            System.out.println("5. Cari Item");
            System.out.println("6. Keluar");
            System.out.print("Pilih Menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama item: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan kuantitas: ");
                    int kuantitas = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Masukkan kategori: ");
                    String kategori = scanner.nextLine();
                    daftar.tambahItem(nama, kuantitas, kategori);
                    break;
                case 2:
                    System.out.print("Masukkan nama item yang ingin dihapus: ");
                    String namaHapus = scanner.nextLine();
                    daftar.hapusItem(namaHapus);
                    break;
                case 3:
                    daftar.tampilkanSemuaItem();
                    break;
                case 4:
                    System.out.print("Masukkan kategori yang ingin ditampilkan: ");
                    String kategoriTampil = scanner.nextLine();
                    daftar.tampilkanPerKategori(kategoriTampil);
                    break;
                case 5:
                    System.out.print("Masukkan nama item yang ingin dicari: ");
                    String namaCari = scanner.nextLine();
                    daftar.cariItem(namaCari);
                    break;
                case 6:
                    System.out.println("Keluar dari program.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }

    }
}
