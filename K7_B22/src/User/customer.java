package User;

public class customer extends user {
    private String alamatPengiriman;
    private String riwayatPembelian;

    public customer(int id,String nama, String username, String password, String email, int telp, String alamatPengiriman, String riwayatPembelian){
        super(id, nama, username, password, email, telp);
        this.alamatPengiriman = alamatPengiriman;
        this.riwayatPembelian = riwayatPembelian;
    }

    public String getAlamatPengiriman() {
        return alamatPengiriman;
    }

    public void setAlamatPengiriman(String alamatPengiriman) {
        this.alamatPengiriman = alamatPengiriman;
    }

    public String getRiwayatPembelian() {
        return riwayatPembelian;
    }

    public void setRiwayatPembelian(String riwayatPembelian) {
        this.riwayatPembelian = riwayatPembelian;
    }

    @Override
    public void setUsername(String username){
        this.username = username;
    }
    // Tambahan metode khusus untuk kelas Customer jika diperlukan
}
