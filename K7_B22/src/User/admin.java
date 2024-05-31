package User;

public class admin extends user {
    public admin(int id, String nama, String username, String password, String email, String telp, String role) {
        super(id, nama, username, password, email, telp, role);
    }
    @Override
    public void setUsername(String username){
        this.username = username;
    }
    // tambahkan metode crud untuk admin
}
