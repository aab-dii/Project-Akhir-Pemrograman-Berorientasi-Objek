package User;

public class admin extends user {
    public admin(int id, String nama, String username, String password, String email, int telp) {
        super(id, nama, username, password, email, telp);
    }
    @Override
    public void setUsername(String username){
        this.username = username;
    }
    // tambahkan metode crud untuk admin
}
