package Product;

public class rumahTangga extends product {
    private String bahan;
    private String ukuran;

    public rumahTangga(int id, String nama, String deskripsi, int harga, int stok, String bahan, String ukuran, String merk, String jenis) {
        super(id, nama, deskripsi, harga, stok, merk, "rumahtangga");
        this.bahan = bahan;
        this.ukuran = ukuran;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
}
