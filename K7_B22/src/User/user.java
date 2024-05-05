package User;

public abstract class user {
    protected int id, telp;
    public String nama, username, password, email;

    public user(int id, String nama, String username, String password, String email, int telp){
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telp = telp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelp() {
        return telp;
    }

    public void setTelp(int telp) {
        this.telp = telp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public abstract void setUsername(String username);

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    


}
