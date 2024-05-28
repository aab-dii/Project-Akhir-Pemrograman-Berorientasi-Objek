package Keranjang;

public class keranjang {
    protected int custId, idKeranjang, idProduk, jumlah;

    // Konstruktor 
    public keranjang(int idKeranjang,int custId,  int idProduk, int jumlah) {
        this.idKeranjang = idKeranjang;
        this.custId = custId;
        this.idProduk = idProduk;
        this.jumlah = jumlah;
    }
    
    // Getter dan setter
    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(int idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

}
