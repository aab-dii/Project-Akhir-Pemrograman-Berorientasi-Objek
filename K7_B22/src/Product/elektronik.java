package Product;

public class elektronik extends product implements printProduk{
    protected String tipe, model, warna;

    public elektronik(int id, String nama, String deskripsi, int harga, int stok, String merk, String tipe, String model, String warna, String jenis) {
        super(id, nama, deskripsi, harga, stok, merk,"elektronik");
        this.tipe = tipe;
        this.model = model;
        this.warna = warna;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
    
    @Override
    public void printProductInfo(int no) {
        System.out.printf("| %-5d | %-20s | %-20s | %-10d | %-10d | %-10s | %-10s | %-10s | %-10s |\n",
                no, getNama(), getDeskripsi(), getHarga(), getStok(), getMerk(), getTipe(), getModel(), getWarna());
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------");
    }
    
}
