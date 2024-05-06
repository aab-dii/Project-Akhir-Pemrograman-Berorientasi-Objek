package Product;

public class elektronik extends product {
    protected String tipe, model, warna;

    public elektronik(int id, String nama, String deskripsi, int harga, int stok, String merk, String tipe, String model, String warna) {
        super(id, nama, deskripsi, harga, stok, merk);
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
    
}
