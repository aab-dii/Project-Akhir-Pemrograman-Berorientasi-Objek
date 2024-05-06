package Keranjang;

public class keranjang {
    protected int custId, idKeranjang;
    protected String kode;

    // Konstruktor 
    public keranjang(int custId, int idKeranjang, String kode) {
        this.custId = custId;
        this.idKeranjang = idKeranjang;
        this.kode = kode;
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

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
