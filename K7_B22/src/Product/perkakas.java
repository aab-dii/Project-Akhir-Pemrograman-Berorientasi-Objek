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
        System.out.printf("| %-5d | %-20s | %-20s | %-10d | %-10d | %-10s |\n",
                no, getNama(), getDeskripsi(), getHarga(), getStok(), getMerk());
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
    }
}
