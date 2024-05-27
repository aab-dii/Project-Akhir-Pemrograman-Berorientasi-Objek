package Product;

public abstract class product {
    protected int id, harga, stok;
    protected String nama, deskripsi, merk, jenis;

    public product(int id, String nama, String deskripsi, int harga, int stok, String merk, String jenis) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.merk = merk;
        this.jenis = jenis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    
    
}
