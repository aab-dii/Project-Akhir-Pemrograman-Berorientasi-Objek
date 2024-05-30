package Product;

public class rumahTangga extends product implements printProduk {
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

    @Override
    public void printProductInfo(int no) {
        System.out.println(no + "  | Nama: " + this.getNama());
        System.out.println("   | Deskripsi: " + this.getDeskripsi());
        System.out.println("   | Harga: " + this.getHarga());
        System.out.println("   | Stok: " + this.getStok());
        System.out.println("   | Bahan: " + this.getBahan());
        System.out.println("   | Ukuran: " + this.getUkuran());
        System.out.println("   | Merk: " + this.getMerk());
        System.out.println("----------------------------------");
    }
}
