package Antar;

public class antar {
    private int id_antar;
    private int idProduks;
    private int idPelanggan;
    private int idPesanan;
    public antar(int id_antar,int idProduks, int idPelanggan, int idPesanan){
        this.id_antar = id_antar;
        this.idProduks = idProduks;
        this.idPelanggan = idPelanggan;
        this.idPesanan = idPesanan;
    }
    public int getIdProduks() {
        return idProduks;
    }
    public void setIdProduks(int idProduks) {
        this.idProduks = idProduks;
    }
    public int getIdPelanggan() {
        return idPelanggan;
    }
    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
    public int getIdPesanan() {
        return idPesanan;
    }
    public void setIdPesanan(int idPesanan) {
        this.idPesanan = idPesanan;
    }
    public int getIdAntar() {
        return id_antar;
    }
    public void setIdAntar(int id_antar) {
        this.id_antar = id_antar;
    }
}
