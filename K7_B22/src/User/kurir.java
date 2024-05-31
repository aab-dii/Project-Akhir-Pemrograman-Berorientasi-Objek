package User;

public class kurir extends user {
    public kurir(int id, String nama, String username, String password, String email, String telp, String role) {
        super(id, nama, username, password, email, telp, role);
    }
    @Override
    public void setUsername(String username){
        this.username = username;
    }
    //tambahkan metode untuk kurir
}
