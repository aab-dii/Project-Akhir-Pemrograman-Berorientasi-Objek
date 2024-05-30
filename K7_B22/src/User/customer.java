package User;

public class customer extends user {
    private String alamatPengiriman;
    private int saldo;

    public customer(int id,String nama, String username, String password, String email, int telp, String alamatPengiriman, int saldo, String role){
        super(id, nama, username, password, email, telp, "customer");
        this.alamatPengiriman = alamatPengiriman;
        this.saldo = saldo;
    }

    
    public String getAlamatPengiriman() {
        return alamatPengiriman;
    }

    public void setAlamatPengiriman(String alamatPengiriman) {
        this.alamatPengiriman = alamatPengiriman;
    }

    @Override
    public void setUsername(String username){
        this.username = username;
    }
    // Tambahan metode khusus untuk kelas Customer jika diperlukan


    public int getSaldo() {
        return saldo;
    }


    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
