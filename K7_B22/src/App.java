import java.util.Scanner;
import java.util.ArrayList;

import User.customer;

public class App {
    static Scanner sc = new Scanner(System.in);
    static boolean loggedIn = false;
    static String pilih = "";
    static int custId = 0;

    static ArrayList<customer> datacust = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        while (!pilih.equals("3")) {
            System.out.println("===============================================");
            System.out.println("| SISTEM PENDATAAN RUMAH TANGGA DAN AKSESORIS |");
            System.out.println("===============================================");
            System.out.println("| [1]. Daftar                                 |");
            System.out.println("| [2]. Login                                  |");
            System.out.println("| [3]. Keluar                                 |");
            System.out.println("===============================================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    daftarCust();
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

    public static void daftarCust() {
        System.out.print("Masukkan Username: ");
        String newUsername = sc.nextLine();
        System.out.print("Masukkan Password: ");
        String newPassword = sc.nextLine();
    
        boolean usernameExists = false;
    
        // Periksa apakah username sudah digunakan oleh pelanggan lain
        for (customer customer : datacust) {
            if (customer.getUsername().equals(newUsername)) {
                System.out.println("Username telah digunakan, Silahkan gunakan username lain");
                usernameExists = true;
                break; // Keluar dari loop karena username sudah ditemukan
            }
        }
    
        // Jika username belum digunakan, tambahkan pelanggan baru
        if (!usernameExists) {
            customer newCustomer = new customer(
                datacust.size() + 1, // ID pelanggan diatur sesuai dengan indeks ArrayList + 1
                "", // Nama pelanggan sementara diatur sebagai string kosong
                newUsername,
                newPassword,
                "", // Email pelanggan sementara diatur sebagai string kosong
                0, // Nomor telepon pelanggan sementara diatur sebagai 0
                "", // Alamat pengiriman sementara diatur sebagai string kosong
                "" // Riwayat pembelian sementara diatur sebagai string kosong
            );
    
            // Tambahkan objek Customer baru ke dalam ArrayList datacust
            datacust.add(newCustomer);
            System.out.println("Pendaftaran berhasil!");
        }
    }

    public static void login() {
        System.out.print("Masukkan username: ");
        String inputUsername = sc.nextLine();
        System.out.print("Masukkan password: ");
        String inputPassword = sc.nextLine();

        boolean loginSuccess = false;

        // Iterasi melalui setiap objek Customer dalam ArrayList datacust
        for (customer customer : datacust) {
            // Memeriksa apakah inputUsername dan inputPassword cocok dengan yang ada dalam
            // objek Customer saat ini
            if (customer.username.equals(inputUsername) && customer.password.equals(inputPassword)) {
                System.out.println("Login berhasil!");
                loggedIn = true;
                custId = customer.getId();
                menuCust();
                loginSuccess = true;
                break; // Keluar dari loop jika login berhasil
            }
        }

        if (!loginSuccess) {
            // Jika tidak ada username dan password yang cocok dalam data pelanggan, coba
            // untuk login sebagai admin atau kurir
            if (inputUsername.equals("admin") && inputPassword.equals("admin")) {
                System.out.println("Login berhasil sebagai admin!");
                loggedIn = true;
                menuAdmin();
            } else if (inputUsername.equals("kurir") && inputPassword.equals("kurir")) {
                System.out.println("Login berhasil sebagai kurir!");
                loggedIn = true;
                menuKurir();
            } else {
                System.out.println("Username atau password salah!");
            }
        }
    }

    public static void menuAdmin() {
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
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }

    public static void menuKurir() {
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
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }

    public static void menuCust() {
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
                    break;
                case "2":
                    System.out.println("Lihat Barang");
                    break;
                case "3":
                    System.out.println("Keranjang");
                    break;
                case "4":
                    System.out.println("Profil");
                    menuProfile();
                    break;
                case "5":
                    System.out.println("Keluar dari Menu Customer");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }

    public static void menuProfile(){
        while (!pilih.equals("2") ) {
        profileCust();
        System.out.println("=====================");
        System.out.println("| [1]. Ubah Profile |");
        System.out.println("| [2]. Keluar       |");
        System.out.println("=====================");
        System.out.print(">> ");
        pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    ubahProfile();
                    break;
                default:
                    break;
            }
            
        }
    }

    public static void profileCust() {
        for (customer customer : datacust) {
            if (customer.getId() == (custId)) {
                System.out.println("Username : " + customer.getUsername());
                System.out.println("Password : " + customer.getPassword());
                System.out.println("Nama     : " + customer.getNama());
                System.out.println("No. Telp : " + customer.getTelp());
                System.out.println("Email    : " + customer.getEmail());
                System.out.println("Alamat   : " + customer.getAlamatPengiriman());
            }
        }
    }

    public static void ubahProfile() {
        customer customerToUpdate = null; // Deklarasikan variabel customerToUpdate untuk menyimpan pelanggan yang akan diperbarui
    
        // Temukan pelanggan yang sesuai berdasarkan username
        for (customer customer : datacust) {
            if (customer.getId() == custId) {
                customerToUpdate = customer; // Simpan referensi pelanggan yang sesuai
                break; // Keluar dari loop setelah menemukan pelanggan yang sesuai
            }
        }
    
        if (customerToUpdate != null) {
            System.out.println("================================");
            System.out.println("| Pilih data yang ingin diubah |");
            System.out.println("================================");
            System.out.println("| [1]. Username                |");
            System.out.println("| [2]. Password                |");
            System.out.println("| [3]. Nama                    |");
            System.out.println("| [4]. No. Telpon              |");
            System.out.println("| [5]. Email                   |");
            System.out.println("| [6]. Alamat                  |");
            System.out.println("================================");
            System.out.print(">> ");
            String pilih = sc.nextLine();
    
            switch (pilih) {
                case "1":
                    System.out.print("Masukkan username baru: ");
                    String usernamebr = sc.nextLine();
                    customerToUpdate.setUsername(usernamebr); // Ubah username pelanggan
                    break;
                case "2":
                    System.out.print("Masukkan password baru: ");
                    String newPassword = sc.nextLine();
                    customerToUpdate.setPassword(newPassword); // Ubah password pelanggan
                    break;
                case "3":
                    System.out.print("Masukkan nama baru: ");
                    String namaBaru = sc.nextLine();
                    customerToUpdate.setNama(namaBaru); // Ubah nama pelanggan
                    break;
                case "4":
                    System.out.print("Masukkan nomor telepon baru: ");
                    int telpBaru = Integer.parseInt(sc.nextLine());
                    customerToUpdate.setTelp(telpBaru); // Ubah nomor telepon pelanggan
                    break;
                case "5":
                    System.out.print("Masukkan email baru: ");
                    String emailBaru = sc.nextLine();
                    customerToUpdate.setEmail(emailBaru); // Ubah email pelanggan
                    break;
                case "6":
                    System.out.print("Masukkan alamat baru: ");
                    String alamatBaru = sc.nextLine();
                    customerToUpdate.setAlamatPengiriman(alamatBaru); // Ubah alamat pengiriman pelanggan
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
            System.out.println("Profil pelanggan berhasil diubah.");
        } else {
            System.out.println("Pelanggan tidak ditemukan.");
        }
    }
    

}
