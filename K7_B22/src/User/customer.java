package User;

public class customer extends user {
    private String alamatPengiriman;

    public customer(int id,String nama, String username, String password, String email, int telp, String alamatPengiriman, String role){
        super(id, nama, username, password, email, telp, "customer");
        this.alamatPengiriman = alamatPengiriman;
        
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
}
