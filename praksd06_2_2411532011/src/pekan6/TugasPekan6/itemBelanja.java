package pekan6.TugasPekan6;

public class itemBelanja {
    String nama, kategori;
    int kuantitas;

    public itemBelanja(String nama, int kuantitas, String kategori) {
        this.nama = nama;
        this.kuantitas = kuantitas;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public String getKategori() {
        return kategori;
    }

    @Override
    public String toString() {
        return getNama() + " (" + getKuantitas() + ") " + "[" + getKategori() + "]";
    }
}
