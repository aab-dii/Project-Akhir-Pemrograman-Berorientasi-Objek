package Product;

public class rumahTangga extends product implements printProduk {
    private String bahan;
    private String ukuran;

    public rumahTangga(int id, String nama, String deskripsi, int harga, int stok, String bahan, String ukuran,
            String merk, String jenis) {
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

    @Override
    public void printProductInfo(int no) {
        System.out.printf("| %-5d | %-20s | %-20s | %-10d | %-10d | %-10s | %-10s | %-10d |\n",
                no, getNama(), getDeskripsi(), getHarga(), getStok(), getMerk(), getBahan(), getUkuran());
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
    }

}
