package Pesanan;

public class pesanan {
    private int idPesanan, idCust, idProduk;
    private String status;
    public pesanan(int idPesanan, int idCust, int idProduk, String status) {
        this.idPesanan = idPesanan;
        this.idCust = idCust;
        this.idProduk = idProduk;
        this.status = status;
    }
    
    public int getIdPesanan() {
        return idPesanan;
    }
    public void setIdPesanan(int idPesanan) {
        this.idPesanan = idPesanan;
    }
    public int getIdCust() {
        return idCust;
    }
    public void setIdCust(int idCust) {
        this.idCust = idCust;
    }
    public int getIdProduk() {
        return idProduk;
    }
    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    
}
