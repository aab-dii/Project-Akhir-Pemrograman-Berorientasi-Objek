package Product;

public class furniture extends product implements printProduk {


    public furniture(int id, String nama, String deskripsi, int harga, int stok, String merk, String jenis) {
        super(id, nama, deskripsi, harga, stok, merk, "furniture");
        
    }

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
