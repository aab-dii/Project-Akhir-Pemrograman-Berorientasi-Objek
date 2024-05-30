package Product;

// Kelas perkakas yang mengimplementasikan interface PrintableProduct
public class perkakas extends product implements printProduk {
    // Konstruktor Perkakas
    public perkakas(int id, String nama, String deskripsi, int harga, int stok, String merk, String jenis) {
        super(id, nama, deskripsi, harga, stok, merk, "perkakas");
    }

    // Implementasi metode printProductInfo() dari interface printProduk
    @Override
    public void printProductInfo(int no) {
        System.out.println("ID: " + getId());
        System.out.println("Nama: " + getNama());
        System.out.println("Deskripsi: " + getDeskripsi());
        System.out.println("Harga: " + getHarga());
        System.out.println("Stok: " + getStok());
        System.out.println("Merk: " + getMerk());
        System.out.println("------------------------------------");
    }
}
