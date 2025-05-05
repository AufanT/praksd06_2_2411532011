package pekan2;

import java.util.*;

class Mobil {
    private String platNomor;
    Mobil(String platNomor) {
        this.platNomor = platNomor;
    }
    public String getPlatNomor() {
        return platNomor;
    }

    @Override
    public String toString() {
        return platNomor;
    }
}

class Parkir {
    private ArrayList<Mobil> daftarMobil = new ArrayList<>();

    public Parkir() {
        String[] awal = {
            "BA1111AA", "BA2222BB", "BA3333CC",
            "BA4444DD", "BA5555EE", "BA6666FF",
            "BA7777GG"
        };
        for (String plat : awal) {
            daftarMobil.add(new Mobil(plat));
        }
    }

    void tambahMobil(String platNomor) {
        daftarMobil.add(new Mobil(platNomor));
        System.out.println("Mobil dengan plat " + platNomor + " berhasil ditambahkan.");
    }
    void keluarkanMobil(String platNomor) {
        for (Mobil mobil : daftarMobil) {
            if (mobil.getPlatNomor().equals(platNomor)) {
                daftarMobil.remove(mobil);
                System.out.println("Mobil dengan plat " + platNomor + " telah keluar dari parkiran.");
                return;
            }
        }
        System.out.println("Mobil dengan plat " + platNomor + " tidak ditemukan.");
    }
    void tampilkanParkiran() {
        if (daftarMobil.isEmpty()) {
			System.out.println("Tidak ada mobil dalam parkiran");
		} else {
			System.out.println("Daftar mobil dalam parkiran: ");
			for (Mobil mobil : daftarMobil) {
				System.out.println(" - " + mobil);
			}
		}
    }
    void cariMobil(String platNomor) {
        for (Mobil mobil : daftarMobil) {
            if (mobil.getPlatNomor().equals(platNomor)) {
                System.out.println("Mobil dengan plat " + platNomor + " TERPARKIR.");
                return;
            }
        }
        System.out.println("Mobil dengan plat " + platNomor + " TIDAK TERPARKIR.");
    }

    public static void main(String[] args) {
        Parkir parkir = new Parkir();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1.Tambah Mobil");
            System.out.println("2.Keluarkan Mobil");
            System.out.println("3.Tampilkan Parkiran");
            System.out.println("4.Cari Mobil");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.println("Masukkan Platnomor: ");
                    String tambah = scanner.nextLine();
                    parkir.tambahMobil(tambah);
                    break;
                case 2:
                    System.out.println("Masukkan Platnomor: ");
                    String keluar = scanner.nextLine();
                    parkir.keluarkanMobil(keluar);
                    break;
                case 3:
                    System.out.println("Berikut semua mobil yang ada diparkiran:");
                    parkir.tampilkanParkiran();
                    break;
                case 4:
                    System.out.println("Masukkan Platnomor: ");
                    String cari = scanner.nextLine();
                    parkir.cariMobil(cari);
                    break;
                case 5:
                    System.out.println("Program selesai.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }   
    
}
