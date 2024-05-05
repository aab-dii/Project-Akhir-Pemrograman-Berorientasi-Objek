import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static boolean loggedIn = false;
    static String username = "";
    static String pilih = ""; 

    public static void main(String[] args) throws Exception {
        while (!pilih.equals("3")) {
            System.out.println("===============================================");
            System.out.println("| SISTEM PENDATAAN RUMAH TANGGA DAN AKSESORIS |");
            System.out.println("===============================================");
            System.out.println("| [1]. Daftar                                 |");
            System.out.println("| [2]. Login                                  |");
            System.out.println("| [3]. Keluar                                 |");
            System.out.println("===============================================");
            System.out.println(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    daftar();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Terimakasih Telah Menggunakan Program Kami");
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }

    public static void daftar() {
        System.out.print("Masukkan Username: ");
        String username = sc.nextLine();
        System.out.print("Masukkan Password: ");
        String password = sc.nextLine();
        // Tambahkan logika untuk menyimpan informasi pengguna yang baru didaftarkan
        System.out.println("Pendaftaran berhasil!");
    }

    public static void login() {
        System.out.println("Masukkan username: ");
        String inputUsername = sc.nextLine();
        System.out.println("Masukkan password: ");
        String inputPassword = sc.nextLine();
        // Tambahkan logika untuk memeriksa kecocokan username dan password dengan data yang ada
        // Misalnya, jika username dan password cocok, set loggedIn menjadi true
        if (inputUsername.equals("admin") && inputPassword.equals("admin")){
            menuAdmin();
        } else if (inputUsername.equals("kurir") && inputPassword.equals("kurir")){
            menuKurir();
        }
        loggedIn = true;
        username = inputUsername;
        System.out.println("Login berhasil!");
    }

    public static void menuAdmin(){
        while (!pilih.equals("5")) {
            System.out.println("====================");
            System.out.println("|    Menu Admin    |");
            System.out.println("====================");
            System.out.println("| [1]. Tambah Data |");
            System.out.println("| [2]. Lihat Data  |");
            System.out.println("| [3]. Update Data |");
            System.out.println("| [4]. Delete Data |");
            System.out.println("| [5]. Keluar      |");
            System.out.println("====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    System.out.println("Tambah Data");
                    // Tambahkan logika untuk menambah data
                    break;
                case "2":
                    System.out.println("Lihat Data");
                    // Tambahkan logika untuk melihat data
                    break;
                case "3":
                    System.out.println("Update Data");
                    // Tambahkan logika untuk mengupdate data
                    break;
                case "4":
                    System.out.println("Delete Data");
                    // Tambahkan logika untuk menghapus data
                    break;
                case "5":
                    System.out.println("Keluar dari Menu Admin");
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }

    public static void menuKurir(){
        while (!pilih.equals("4")) {
            System.out.println("===============================");
            System.out.println("|         Menu Kurir          |");
            System.out.println("===============================");
            System.out.println("| [1]. Tambah Pengiriman      |");
            System.out.println("| [2]. Lihat Pengiriman       |");
            System.out.println("| [3]. Konfirmasi Pengiriman  |");
            System.out.println("| [4]. Keluar                 |");
            System.out.println("===============================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    System.out.println("Tambah Pengiriman");
                    // Tambahkan logika untuk menambahkan pengiriman
                    break;
                case "2":
                    System.out.println("Lihat Pengiriman");
                    // Tambahkan logika untuk melihat pengiriman
                    break;
                case "3":
                    System.out.println("Konfirmasi Pengiriman");
                    // Tambahkan logika untuk mengkonfirmasi pengiriman
                    break;
                case "4":
                    System.out.println("Keluar dari Menu Kurir");
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }
    
    public static void menuCust(){
        while (!pilih.equals("5")) {
            System.out.println("=====================");
            System.out.println("|   Menu Customer   |");
            System.out.println("=====================");
            System.out.println("| [1]. Beli Barang  |");
            System.out.println("| [2]. Lihat Barang |");
            System.out.println("| [3]. Keranjang    |");
            System.out.println("| [4]. Profile      |");
            System.out.println("| [5]. Keluar       |");
            System.out.println("=====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    System.out.println("Beli Barang");
                    // Tambahkan logika untuk proses pembelian barang
                    break;
                case "2":
                    System.out.println("Lihat Barang");
                    // Tambahkan logika untuk melihat barang yang tersedia
                    break;
                case "3":
                    System.out.println("Keranjang");
                    // Tambahkan logika untuk melihat keranjang belanja dan melakukan pembayaran
                    break;
                case "4":
                    System.out.println("Profile");
                    // Tambahkan logika untuk melihat dan mengubah informasi profil pengguna
                    break;
                case "5":
                    System.out.println("Keluar dari Menu Customer");
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }
    
}
