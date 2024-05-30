import java.util.Scanner;

import Product.rumahTangga;
import Product.elektronik;
import Product.furniture;
import Product.perkakas;
import Product.product;
import Product.productControl;
import Keranjang.keranjang;
import Keranjang.keranjangControl;
import Pesanan.pesanan;
import Pesanan.pesananControl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import User.customer;
import User.admin;
import User.kurir;
import User.user;
import User.userControl;

public class App {
    static Scanner sc = new Scanner(System.in);
    static boolean loggedIn = false;

    static int custId = 0;
    static String username = "";
    static boolean exit = true;
    static String storedPasswordHash = "";

    // static ArrayList<customer> dataCust = new ArrayList<>();
    // static ArrayList<kurir> dataKurir = new ArrayList<>();
    // static ArrayList<admin> dataAdmin = new ArrayList<>();

    // static ArrayList<keranjang> dataKeranjang = new ArrayList<>();

    // static ArrayList<rumahTangga> datArt = new ArrayList<>();
    // static ArrayList<elektronik> dataElektronik = new ArrayList<>();
    // static ArrayList<furniture> dataFurniture = new ArrayList<>();
    // static ArrayList<perkakas> dataPerkakas = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        productControl.lihatProduk();
        String pilih = "";
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

        // Periksa apakah username sudah ada
        boolean usernameExists = userControl.checkUsernameExists(newUsername);

        // Jika username belum digunakan, tambahkan pengguna baru
        if (!usernameExists) {
            System.out.print("Masukkan Password: ");
            String newPassword = sc.nextLine();

            try {
                customer newCustomer = new customer(0, "", newUsername, newPassword, "", 0, "", "customer"); // Sesuaikan
                                                                                                             // dengan
                // konstruktor kelas
                // customer
                userControl.registerCustomer(newCustomer);
                System.out.println("Pendaftaran berhasil!");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Gagal mendaftar.");
            }
        } else {
            System.out.println("Username telah digunakan. Silahkan gunakan username lain.");
        }
    }

    public static void daftarKurir() {
        System.out.print("Masukkan Username: ");
        String newUsername = sc.nextLine();

        // Periksa apakah username sudah ada
        boolean usernameExists = userControl.checkUsernameExists(newUsername);

        // Jika username belum digunakan, tambahkan pengguna baru
        if (!usernameExists) {
            System.out.print("Masukkan Password: ");
            String newPassword = sc.nextLine();
            sc.nextLine();
            System.out.print("Masukkan Nama :");
            String nama = sc.nextLine();
            System.out.print("Masukkan email: ");
            String email = sc.nextLine();
            while (!isValidEmail(email)) {
                System.out.println("Alamat email tidak valid. Harap masukkan alamat email yang benar.");
                System.out.print("Masukkan email baru: ");
                email = sc.nextLine();
            }
            System.out.print("Masukkan Nomor Telepon :");
            int telp = cekinput(sc);

            try {
                kurir newKurir = new kurir(0, nama, newUsername, newPassword, email, telp, "kurir"); // Sesuaikan
                // dengan
                // konstruktor kelas
                // customer
                userControl.registerKurir(newKurir);
                System.out.println("Pendaftaran berhasil!");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Gagal mendaftar.");
            }
        } else {
            System.out.println("Username telah digunakan. Silahkan gunakan username lain.");
        }
    }

    // public static void login() {
    // System.out.print("Masukkan username: ");
    // String inputUsername = sc.nextLine();
    // System.out.print("Masukkan password: ");
    // String inputPassword = sc.nextLine(); // Tidak melakukan hashing pada
    // password input pengguna

    // try {
    // user userData = userControl.checkCredentials(inputUsername, inputPassword);

    // if (userData != null) {
    // String storedPasswordHash = userData.getPassword();

    // // Hash password yang dimasukkan oleh pengguna
    // String hashedInputPassword = inputPassword;
    // System.out.println(storedPasswordHash);
    // System.out.println(hashedInputPassword);

    // // Bandingkan password yang di-hash dengan password yang disimpan di database
    // if (hashedInputPassword.equals(storedPasswordHash)) {
    // System.out.println("Login berhasil!");
    // loggedIn = true;
    // username = inputUsername;
    // custId = userData.getId();

    // if (userData instanceof customer) {
    // profileCust((customer) userData);
    // menuCust((customer) userData);
    // } else if (userData instanceof admin) {
    // menuAdmin((admin) userData);
    // } else if (userData instanceof kurir) {
    // // profileKurir((kurir) userData);
    // // menuKurir((kurir) userData);
    // }
    // } else {
    // System.out.println("Username atau password salah!");
    // }
    // } else {
    // System.out.println("Username atau password salah!");
    // }
    // } catch (SQLException | ClassNotFoundException e) {
    // e.printStackTrace();
    // System.out.println("Terjadi kesalahan saat memeriksa kredensial pengguna.");
    // }
    // }

    public static void login() {
        System.out.print("Masukkan username: ");
        String inputUsername = sc.nextLine();
        System.out.print("Masukkan password: ");
        String inputPassword = sc.nextLine();

        try {
            user userData = userControl.checkCredentials(inputUsername, inputPassword);

            if (userData != null) {
                System.out.println("Login berhasil!");
                loggedIn = true;
                username = inputUsername;
                custId = userData.getId();

                if (userData instanceof customer) {
                    menuCust((customer) userData);
                } else if (userData instanceof admin) {
                    menuAdmin((admin) userData);
                } else if (userData instanceof kurir) {
                    menuKurir((kurir) userData);
                }
            } else {
                System.out.println("Username atau password salah!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memeriksa kredensial pengguna.");
        }
    }

    public static void menuAdmin(admin loggedInAdmin) {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("Menu Admin");
            System.out.println(" [1]. Kelola Produk ");
            System.out.println(" [2]. Kelola Kurir ");
            System.out.println(" [3]. Profile ");
            System.out.println(" [4]. Kelola Pesanan ");
            System.out.println(" [5]. Keluar");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    kelolaData();
                    break;
                case "2":
                    kelolaKurir();
                    break;
                case "3":
                    profileAdmin(loggedInAdmin);
                    break;
                case "4":
                    kelolaPesanan();
                    break;
                case "5":
                    break;
                default:
                    break;
            }
        }
    }

    public static void kelolaPesanan() {
        String pilih = "";
        while (!pilih.equals("3")) {
            System.out.println("Menu Admin");
            System.out.println(" [1]. Konfirmasi Pesanan ");
            System.out.println(" [2]. Kirim Pesanan ");
            System.out.println(" [5]. Keluar");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    konfirmasiPesanan();
                    break;
                case "2":
                    kirimPesanan();
                    break;
                case "3":
                    break;
                default:
                    break;
            }
        }

    }

    public static void konfirmasiPesanan() {
        pesananControl.lihatPesananKonfir();
        System.out.println("Pilih pesanan yang ingin diproses");
        System.out.print(">> ");
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer
        if (index < 0 || index >= pesananControl.getDataPesanan().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }
        pesanan psToUpdate = pesananControl.getDataPesanan().get(index);
        String status = "Pesanan Diproses";
        psToUpdate.setStatus(status);
        try {
            pesananControl.updatePesanan(psToUpdate);
            System.out.println("Pesanan Berhasil Diproses.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui produk di database.");
        }
    }

    public static void kirimPesanan() {
        pesananControl.lihatPesananProses();
        System.out.println("Pilih pesanan yang ingin dikirim");
        System.out.print(">> ");
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= pesananControl.getDataPesanan().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }
        pesanan psToUpdate = pesananControl.getDataPesanan().get(index);
        String status = "Pesanan Sedang Dikirim";
        psToUpdate.setStatus(status);
        try {
            pesananControl.updatePesanan(psToUpdate);
            System.out.println("Pesanan Berhasil Dikirim.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui produk di database.");
        }
    }

    public static void profileAdmin(admin userData) {
        if (userData instanceof admin) {
            admin adm = (admin) userData;
            System.out.println("| Profil Admin");
            System.out.println("| Username : " + adm.getUsername());
            System.out.println("| Password : " + adm.getPassword());
            System.out.println("| Nama     : " + adm.getNama());
            System.out.println("| No. Telp : " + adm.getTelp());
            System.out.println("| Email    : " + adm.getEmail());
            System.out.println("| Role     : " + adm.getRole());
        } else {
            System.out.println("Akses ditolak. Anda bukan admin.");
        }
    }

    public static void kelolaKurir() {
        String pilih = "";
        while (!pilih.equals("6")) {
            System.out.println("=====================");
            System.out.println("|   Kelola Kurir    |");
            System.out.println("=====================");
            System.out.println("| [1]. Tambah Kurir |");
            System.out.println("| [2]. Lihat Kurir  |");
            System.out.println("| [3]. Delete Kurir |");
            System.out.println("| [6]. Keluar       |");
            System.out.println("=====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    daftarKurir();
                    break;
                case "2":
                    try {
                        userControl.lihatKurir();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        // Anda bisa menambahkan logika penanganan kesalahan tambahan di sini
                        System.out.println("Terjadi kesalahan saat mengambil data kurir: " + e.getMessage());
                    }
                    break;
                case "3":
                    hapusKurir();
                default:
                    break;
            }
        }
    }

    // public static void tambahKurir() {
    // System.out.println("Masukkan username :");
    // String username = sc.nextLine();System.out.println("Masukkan password :");
    // String password = sc.nextLine();System.out.println("Masukkan Nama :");
    // String nama = sc.nextLine();System.out.println("Masukkan Email :");
    // String email = sc.nextLine();System.out.println("Masukkan Nomor Telepon :");
    // int nomor = sc.nextInt();

    // boolean usernameExists = false;

    // for(kurir kurir:dataKurir)
    // {
    // if (kurir.getUsername().equals(username)) {
    // System.out.println("Username telah digunakan, Silahkan gunakan username
    // lain");
    // usernameExists = true;
    // break; // Keluar dari loop karena username sudah ditemukan
    // }
    // }

    // if(!usernameExists)
    // {
    // kurir newKurir = new kurir(
    // dataKurir.size() + 1,
    // nama,
    // username,
    // password,
    // email,
    // nomor);

    // dataKurir.add(newKurir);
    // System.out.println("Pendaftaran berhasil!");
    // }}

    // public static void lihatKurir() {
    // for(

    // kurir kurir:dataKurir)
    // {
    // System.out.println("Nama : " + kurir.getNama());
    // System.out.println("Email : " + kurir.getEmail());
    // System.out.println("No. Telp : " + kurir.getTelp());
    // System.out.println("Username : " + kurir.getUsername());
    // System.out.println("Password : " + kurir.getPassword());
    // }}

    public static void kelolaData() {
        String pilih = "";
        while (!pilih.equals("6")) {
            System.out.println("====================");
            System.out.println("| Kelola Barang |");
            System.out.println("====================");
            System.out.println("| [1]. Tambah Data |");
            System.out.println("| [2]. Lihat Data |");
            System.out.println("| [3]. Update Data |");
            System.out.println("| [4]. Delete Data |");
            System.out.println("| [6]. Keluar |");
            System.out.println("====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    System.out.println("Tambah Data");
                    tambahData();
                    break;
                case "2":
                    System.out.println("Lihat Data");
                    lihatData();
                    break;
                case "3":
                    System.out.println("Update Data");
                    ubahData();
                    break;
                case "4":
                    System.out.println("Delete Data");
                    hapusData();
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

    // Tambah Data

    public static void tambahData() {
        String pilih = "";
        while (!pilih.equals("5")) {
            printData();
            System.out.println(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    tambahRt();
                    break;
                case "2":
                    tambahPerkakas();
                    break;
                case "3":
                    tambahElektronik();
                    break;
                case "4":
                    tambahFurnitur();
                    break;
                default:
                    break;
            }
        }
    }

    public static void tambahRt() {
        System.out.println("Masukkan data untuk Peralatan Rumah Tangga:");
        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Bahan: ");
        String bahan = sc.nextLine();
        System.out.print("Ukuran: ");
        String ukuran = sc.nextLine();
        System.out.print("Merk: ");
        String merk = sc.nextLine();

        // Buat objek product dengan jenis rumahTangga
        product newProduct = new rumahTangga(0, nama, deskripsi, harga, stok, bahan, ukuran, merk, "rumahTangga");

        try {
            productControl.tambahProduk(newProduct); // Panggil metode untuk menambahkan produk ke database
            System.out.println("Data Peralatan Rumah Tangga berhasil ditambahkan.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan data Peralatan Rumah Tangga.");
        }
    }

    public static void tambahElektronik() {
        System.out.println("Masukkan data untuk Elektronik:");
        System.out.print("Nama:");

        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Merk: ");
        String merk = sc.nextLine();
        System.out.println("Tipe: ");
        String tipe = sc.nextLine();
        System.out.println("Model: ");
        String model = sc.nextLine();
        System.out.println("Warna: ");
        String warna = sc.nextLine();

        product newProduct = new elektronik(0, nama, deskripsi, harga, stok, merk, tipe, model, warna, "elektronik");
        try {
            productControl.tambahProduk(newProduct); // Panggil metode untuk menambahkan produk ke database
            System.out.println("Data Elektronik berhasil ditambahkan.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan data Elektronik.");
        }
    }

    public static void tambahFurnitur() {
        System.out.println("Masukkan data untuk Furniture:");
        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Merk: ");
        String merk = sc.nextLine();

        product newProduct = new furniture(0, nama, deskripsi, harga, stok, merk, "furnitur");
        try {
            productControl.tambahProduk(newProduct); // Panggil metode untuk menambahkan produk ke database
            System.out.println("Data Furnitur berhasil ditambahkan.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan data Furnitur.");
        }
    }

    public static void tambahPerkakas() {
        System.out.println("Masukkan data untuk Perkakas:");
        System.out.print("Nama: ");

        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Merk: ");
        String merk = sc.nextLine();

        product newProduct = new perkakas(0, nama, deskripsi, harga, stok, merk, "Perkakas");

        try {
            productControl.tambahProduk(newProduct); // Panggil metode untuk menambahkan produk ke database
            System.out.println("Data Perkakas berhasil ditambahkan.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan data Perkakas.");
        }
    }

    // Lihat Data

    public static void lihatData() {
        String pilih = "";
        while (!pilih.equals("5")) {
            printData();
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    lihatRumahTangga();
                    break;
                case "2":
                    lihatPerkakas();
                    break;
                case "3":
                    lihatElektronik();
                    break;
                case "4":
                    lihatFurniture();
                    break;
                default:
                    break;
            }
        }
    }

    public static void lihatRumahTangga() {
        for (rumahTangga rt : productControl.getDatArt()) {
            rt.printProductInfo();
        }
    }

    public static void lihatPerkakas() {
        for (perkakas pk : productControl.getDataPerkakas()) {
            pk.printProductInfo();
        }
    }

    public static void lihatElektronik() {
        for (elektronik el : productControl.getDataElektronik()) {
            el.printProductInfo();
        }
    }

    public static void lihatFurniture() {
        for (furniture fn : productControl.getDataFurniture()) {
            fn.printProductInfo();
        }
    }

    // Ubah Data

    public static void ubahData() {
        String pilih = "";
        while (!pilih.equals("5")) {

            System.out.println("| Ubah Data");
            printData();
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    ubahRt();
                    break;
                case "2":
                    ubahElektronik();
                    break;
                case "3":
                    ubahPerkakas();
                    break;
                case "4":
                    ubahFurniture();
                    break;

                default:
                    break;
            }
        }
    }

    public static void ubahRt() {
        // rumahTangga rtToUpdate = null;
        lihatRumahTangga();
        System.out.println("Data berapa yang ingin diubah");
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDatArt().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        rumahTangga rtToUpdate = productControl.getDatArt().get(index);

        // Meminta input dari pengguna untuk memperbarui atribut produk
        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Bahan: ");
        String bahan = sc.nextLine();
        System.out.print("Ukuran: ");
        String ukuran = sc.nextLine();
        System.out.print("Merk: ");
        String merk = sc.nextLine();

        // Mengubah atribut produk yang sesuai dengan input baru
        rtToUpdate.setNama(nama);
        rtToUpdate.setDeskripsi(deskripsi);
        rtToUpdate.setHarga(harga);
        rtToUpdate.setStok(stok);
        rtToUpdate.setBahan(bahan);
        rtToUpdate.setUkuran(ukuran);
        rtToUpdate.setMerk(merk);

        try {
            productControl.ubahProduk(rtToUpdate);
            System.out.println("Data produk peralatan rumah tangga berhasil diubah.");

            // Panggil kembali metode ambilProduk untuk memperbarui ArrayList dengan data
            // terbaru dari database
            // productControl.ambilProduk();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui produk di database.");
        }
    }

    public static void ubahElektronik() {
        // elektronik elektronikToUpdate = null;
        lihatElektronik();
        System.out.println("Data berapa yang ingin diubah");
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDataElektronik().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        elektronik elektronikToUpdate = productControl.getDataElektronik().get(index);

        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Merk: ");
        String merk = sc.nextLine();
        System.out.print("Tipe: ");
        String tipe = sc.nextLine();
        System.out.print("Model: ");
        String model = sc.nextLine();
        System.out.print("Warna: ");
        String warna = sc.nextLine();

        // Mengubah atribut produk yang sesuai dengan input baru
        elektronikToUpdate.setNama(nama);
        elektronikToUpdate.setDeskripsi(deskripsi);
        elektronikToUpdate.setHarga(harga);
        elektronikToUpdate.setStok(stok);
        elektronikToUpdate.setMerk(merk);
        elektronikToUpdate.setTipe(tipe);
        elektronikToUpdate.setModel(model);
        elektronikToUpdate.setWarna(warna);

        try {
            productControl.ubahProduk(elektronikToUpdate);
            System.out.println("Data produk peralatan rumah tangga berhasil diubah.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui produk di database.");
        }
    }

    public static void ubahPerkakas() {
        lihatPerkakas();
        System.out.println("Data berapa yang ingin diubah");
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDataPerkakas().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        perkakas perkakasToUpdate = productControl.getDataPerkakas().get(index);

        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Merk: ");
        String merk = sc.nextLine();

        // Mengubah atribut produk yang sesuai dengan input baru
        perkakasToUpdate.setNama(nama);
        perkakasToUpdate.setDeskripsi(deskripsi);
        perkakasToUpdate.setHarga(harga);
        perkakasToUpdate.setStok(stok);
        perkakasToUpdate.setMerk(merk);

        try {
            productControl.ubahProduk(perkakasToUpdate);
            System.out.println("Data produk peralatan rumah tangga berhasil diubah.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui produk di database.");
        }
    }

    public static void ubahFurniture() {
        lihatFurniture();
        System.out.println("Data berapa yang ingin diubah");
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDataPerkakas().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        furniture furnitureToUpdate = productControl.getDataFurniture().get(index);

        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = sc.nextLine();
        System.out.print("Harga: ");
        int harga = Integer.parseInt(sc.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(sc.nextLine());
        System.out.print("Merk: ");
        String merk = sc.nextLine();

        // Mengubah atribut produk yang sesuai dengan input baru
        furnitureToUpdate.setNama(nama);
        furnitureToUpdate.setDeskripsi(deskripsi);
        furnitureToUpdate.setHarga(harga);
        furnitureToUpdate.setStok(stok);
        furnitureToUpdate.setMerk(merk);

        try {
            productControl.ubahProduk(furnitureToUpdate);
            System.out.println("Data produk peralatan rumah tangga berhasil diubah.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui produk di database.");
        }
    }

    // Hapus Data

    public static void hapusData() {
        String pilih = "";
        while (!pilih.equals("5")) {

            System.out.println("| Hapus Data");
            printData();
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    hapusRt();
                    break;
                case "2":
                    hapusElektronik();
                    break;
                case "3":
                    hapusPerkakas();
                    break;
                case "4":
                    hapusFurnitur();
                    break;

                default:
                    break;
            }
        }

    }

    public static void hapusRt() {
        lihatRumahTangga();
        System.out.println("Data berapa yang ingin dihapus");

        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDatArt().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        rumahTangga rtToDelete = productControl.getDatArt().get(index);

        try {
            productControl.hapusProduk(rtToDelete.getId(), rtToDelete.getJenis());

            System.out.println("Data produk peralatan rumah tangga berhasil dihapus.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menghapus produk dari database.");
        }
    }

    public static void hapusElektronik() {
        // Dapatkan objek elektronik yang akan dihapus dari dataElektronik
        elektronik elektronikToRemove = null;

        // Tampilkan daftar produk elektronik
        lihatElektronik();
        System.out.println("Data berapa yang ingin dihapus");

        // Baca nomor urut produk yang akan dihapus dari input pengguna
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        // Periksa apakah nomor urut valid
        if (index < 0 || index >= productControl.getDataElektronik().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        // Dapatkan objek elektronik yang akan dihapus berdasarkan indeks
        elektronikToRemove = productControl.getDataElektronik().get(index);

        // Hapus produk elektronik dari database
        try {
            productControl.hapusProduk(elektronikToRemove.getId(), elektronikToRemove.getJenis());
            System.out.println("Data produk Elektronik berhasil dihapus.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menghapus produk Elektronik dari database.");
        }
    }

    public static void hapusPerkakas() {
        // Dapatkan objek perkakas yang akan dihapus dari dataPerkakas
        perkakas perkakasToRemove = null;

        // Tampilkan daftar produk perkakas
        lihatPerkakas();
        System.out.println("Data berapa yang ingin dihapus");

        // Baca nomor urut produk yang akan dihapus dari input pengguna
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        // Periksa apakah nomor urut valid
        if (index < 0 || index >= productControl.getDataPerkakas().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        // Dapatkan objek perkakas yang akan dihapus berdasarkan indeks
        perkakasToRemove = productControl.getDataPerkakas().get(index);

        // Hapus produk perkakas dari database
        try {
            productControl.hapusProduk(perkakasToRemove.getId(), perkakasToRemove.getJenis());
            System.out.println("Data produk Perkakas berhasil dihapus.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menghapus produk Perkakas dari database.");
        }
    }

    public static void hapusFurnitur() {
        // Dapatkan objek furnitur yang akan dihapus dari dataFurniture
        furniture furnitureToRemove = null;

        // Tampilkan daftar produk furnitur
        lihatFurniture();
        System.out.println("Data berapa yang ingin dihapus");

        // Baca nomor urut produk yang akan dihapus dari input pengguna
        int index = sc.nextInt() - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        // Periksa apakah nomor urut valid
        if (index < 0 || index >= productControl.getDataFurniture().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }

        // Dapatkan objek furnitur yang akan dihapus berdasarkan indeks
        furnitureToRemove = productControl.getDataFurniture().get(index);

        // Hapus produk furnitur dari database
        try {
            productControl.hapusProduk(furnitureToRemove.getId(), furnitureToRemove.getJenis());
            System.out.println("Data produk Furnitur berhasil dihapus.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menghapus produk Furnitur dari database.");
        }
    }

    public static void lihatKurir() {
        try {
            userControl.lihatKurir();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Anda bisa menambahkan logika penanganan kesalahan tambahan di sini
            System.out.println("Terjadi kesalahan saat mengambil data kurir: " + e.getMessage());
        }
    }

    public static void hapusKurir() {
        lihatKurir();
        System.out.println("Pilih kurir yang ingin dihapus");

        int index = sc.nextInt() - 1;
        sc.nextLine();

        if (index < 0 || index >= userControl.getDataKurir().size()) {
            System.out.println("Nomor urut tidak valid.");
            return;
        }
        kurir krToDelete = userControl.getDataKurir().get(index);
        try {
            userControl.hapusKurir(krToDelete.getId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menghapus produk dari database.");
        }
    }

    // Menu Kurir
    public static void menuKurir(kurir kurir) {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("===============================");
            System.out.println("| Menu Kurir |");
            System.out.println("===============================");
            System.out.println("| [1]. Tambah Pengiriman |");
            System.out.println("| [2]. Lihat Pengiriman |");
            System.out.println("| [3]. Konfirmasi Pengiriman |");
            System.out.println("| [4]. Profil |");
            System.out.println("| [5]. Keluar |");
            System.out.println("===============================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    System.out.println("Tambah Pengiriman");
                    tambahPengiriman();
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
                    break;
                case "5":
                    System.out.println("Keluar dari Menu Kurir");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Input harus angka !!!");
                    break;
            }
        }
    }

    public static void tambahPengiriman() {
        pesananControl.lihatPesananDikirim();
        System.out.println("kirim");
    }

    // Menu Customer
    public static void menuCust(customer customer) {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("Hai " + username);
            System.out.println("=====================");
            System.out.println("|   Menu Customer   |");
            System.out.println("=====================");
            System.out.println("| [1]. Beli Barang  |");
            System.out.println("| [2]. Keranjang    |");
            System.out.println("| [3]. Profile      |");
            System.out.println("| [4]. Pesanan      |");
            System.out.println("| [5]. Keluar       |");
            System.out.println("=====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    menuBeli();
                    break;
                case "2":
                    System.out.println("Keranjang");
                    menuKeranjang(customer);
                    break;
                case "3":
                    menuProfile(customer);
                    break;
                case "4":
                    menuPesanan(customer);
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

    public static void menuPesanan(customer customer) {
        try {
            pesananControl.lihatPesananCust(customer.getId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menampilkan pesanan.");
        }
    }

    public static void menuBeli() {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("Beli Barang");
            printData();
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    beliRt();
                    break;
                case "2":
                    beliPerkakas();
                    break;
                case "3":
                    beliElektronik();
                    break;
                case "4":
                    beliFurniture();
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuKeranjang(customer customer) {
        try {
            String pilih = " ";
            while (!pilih.equals("1") && !pilih.equals("2")) {
                keranjangControl.lihatKeranjang(customer);
                System.out.println("Ingin CheckOut?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak");
                pilih = sc.nextLine();
                switch (pilih) {
                    case "1":
                        menuCheckOut();
                        break;
                    case "2":
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia");
                        break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memuat keranjang.");
        }
    }

    public static void menuCheckOut() {
        for (Keranjang.keranjang keranjang : keranjangControl.getKeranjang()) {
            int idKeranjang = keranjang.getIdKeranjang();
            int idCust = keranjang.getCustId();
            int idProduk = keranjang.getIdProduk();
            int jumlah = keranjang.getJumlah();

            System.out.println("ID Keranjang: " + idKeranjang);
            System.out.println("ID Customer: " + idCust);
            System.out.println("ID Produk: " + idProduk);
            System.out.println("Jumlah: " + jumlah);

            pesanan newPesanan = new pesanan(0, idCust, idProduk, jumlah, "Menunggu Konfirmasi");
            try {
                pesananControl.tambahPesanan(newPesanan);
                keranjangControl.hapusKeranjang(idKeranjang);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
            }
        }
        System.out.println("Produk berhasil dibeli.");
    }

    public static void beliRt() {
        while (true) {
            System.out.println("Peralatan Rumah Tangga");
            lihatRumahTangga();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih ID produk yang ingin Anda beli (atau q untuk keluar):");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = sc.nextInt();
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = sc.nextInt() - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDatArt().size()) {
                    System.out.println("Nomor urut tidak valid.");
                    return;
                }

                rumahTangga rtBeli = productControl.getDatArt().get(index);

                System.out.print("Masukkan jumlah: ");
                int jumlah = sc.nextInt();
                sc.nextLine();
                if (jumlah < 0 || jumlah > rtBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok");
                    break;
                }
                System.out.println(" [1]. Beli");
                System.out.println(" [2]. Keranjang");
                String cekBeli = sc.nextLine();

                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, rtBeli.getId(), jumlah, "Pesanan Diproses");
                    try {
                        pesananControl.tambahPesanan(newPesanan);
                        System.out.println("Produk berhasil dibeli.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, rtBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);
                        System.out.println("Produk berhasil ditambahkan ke keranjang.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else {
                    System.out.println("Input Salah");
                }

            } else {
                // Menangani input yang bukan angka
                String input = sc.nextLine();
                if (input.equals("q")) {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                } else {
                    System.out.println(
                            "Input tidak valid. Harap masukkan ID produk yang ingin Anda beli atau q untuk keluar.");
                }
            }
        }
    }

    public static void beliElektronik() {
        while (true) {
            System.out.println("Elektronik");
            lihatElektronik();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih ID produk yang ingin Anda beli (atau q untuk keluar):");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = sc.nextInt();
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = sc.nextInt() - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDataElektronik().size()) {
                    System.out.println("Nomor urut tidak valid.");
                    return;
                }

                elektronik elBeli = productControl.getDataElektronik().get(index);

                System.out.print("Masukkan jumlah: ");
                int jumlah = sc.nextInt();

                if (jumlah < 0 || jumlah > elBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok");
                    break;
                }

                System.out.println(" [1]. Beli");
                System.out.println(" [2]. Keranjang");
                String cekBeli = sc.nextLine();

                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, elBeli.getId(), jumlah, "Pesanan Diproses");
                    try {
                        pesananControl.tambahPesanan(newPesanan);
                        System.out.println("Produk berhasil dibeli.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, elBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);
                        System.out.println("Produk berhasil ditambahkan ke keranjang.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else {
                    System.out.println("Salah Input");
                }

            } else {
                // Menangani input yang bukan angka
                String input = sc.nextLine();
                if (input.equals("q")) {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                } else {
                    System.out.println(
                            "Input tidak valid. Harap masukkan ID produk yang ingin Anda beli atau q untuk keluar.");
                }
            }
        }
    }

    public static void beliFurniture() {
        while (true) {
            System.out.println("Furniture");
            lihatElektronik();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih ID produk yang ingin Anda beli (atau q untuk keluar):");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = sc.nextInt();
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = sc.nextInt() - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDataFurniture().size()) {
                    System.out.println("Nomor urut tidak valid.");
                    return;
                }

                furniture frBeli = productControl.getDataFurniture().get(index);

                System.out.println("Masukkan jumlah yang inign dibeli");
                int jumlah = sc.nextInt();

                if (jumlah < 0 || jumlah > frBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok");
                    break;
                }

                System.out.println(" [1]. Beli");
                System.out.println(" [2]. Keranjang");
                String cekBeli = sc.nextLine();

                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, frBeli.getId(), jumlah, "Pesanan Diproses");
                    try {
                        pesananControl.tambahPesanan(newPesanan);
                        System.out.println("Produk berhasil dibeli.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, frBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);
                        System.out.println("Produk berhasil ditambahkan ke keranjang.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else {
                    System.out.println("Salah Input");
                }

            } else {
                // Menangani input yang bukan angka
                String input = sc.nextLine();
                if (input.equals("q")) {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                } else {
                    System.out.println(
                            "Input tidak valid. Harap masukkan ID produk yang ingin Anda beli atau q untuk keluar.");
                }
            }
        }
    }

    public static void beliPerkakas() {
        while (true) {
            System.out.println("Perkakas");
            lihatElektronik();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih ID produk yang ingin Anda beli (atau q untuk keluar):");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = sc.nextInt();
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = sc.nextInt() - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDataPerkakas().size()) {
                    System.out.println("Nomor urut tidak valid.");
                    return;
                }

                perkakas prBeli = productControl.getDataPerkakas().get(index);

                System.out.print("Masukkan jumlah: ");
                int jumlah = sc.nextInt();

                if (jumlah < 0 || jumlah > prBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok");
                    break;
                }

                System.out.println(" [1]. Beli");
                System.out.println(" [2]. Keranjang");
                String cekBeli = sc.nextLine();

                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, prBeli.getId(), jumlah, "Menunggu Konfirmasi");
                    try {
                        pesananControl.tambahPesanan(newPesanan);
                        System.out.println("Produk berhasil dibeli.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, prBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);
                        System.out.println("Produk berhasil ditambahkan ke keranjang.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                } else {
                    System.out.println("Salah Input");
                }

            } else {
                // Menangani input yang bukan angka
                String input = sc.nextLine();
                if (input.equals("q")) {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                } else {
                    System.out.println(
                            "Input tidak valid. Harap masukkan ID produk yang ingin Anda beli atau q untuk keluar.");
                }
            }
        }
    }
    // public static void lihatKeranjang() {
    // String nama = "";
    // int harga = 0;
    // int total = 0;
    // for (keranjang keranjang : dataKeranjang) {
    // if (keranjang.getCustId() == custId) {
    // if (keranjang.getKode() == "rt") {
    // for (rumahTangga rumahTangga : datArt) {
    // if (keranjang.getIdKeranjang() == rumahTangga.getId()) {
    // nama = rumahTangga.getNama();
    // harga = rumahTangga.getHarga();
    // break;
    // }
    // }
    // } else if (keranjang.getKode() == "perkakasprBeli) {
    // for (elektronik elektronik : dataElektronik) {
    // if (keranjang.getIdKeranjang() == elektronik.getId()) {
    // nama = elektronik.getNama();
    // harga = elektronik.getHarga();
    // break;
    // }
    // }
    // } else if (keranjang.getKode() == "perkakas") {
    // for (perkakas perkakas : dataPerkakas) {
    // if (keranjang.getIdKeranjang() == perkakas.getId()) {
    // nama = perkakas.getNama();
    // harga = perkakas.getHarga();
    // break;
    // }
    // }
    // } else if (keranjang.getKode() == "furnitur") {
    // for (furniture furniture : dataFurniture) {
    // if (keranjang.getIdKeranjang() == furniture.getId()) {
    // nama = furniture.getNama();
    // harga = furniture.getHarga();
    // break;
    // }
    // }
    // }
    // }
    // total += harga;
    // System.out.println(nama + " " + harga);
    // }
    // System.out.println(total);
    // }

    public static void menuProfile(customer customer) {
        String pilih = "";
        while (!pilih.equals("2")) {
            profileCust(customer);
            System.out.println("=====================");
            System.out.println("| [1]. Ubah Profile |");
            System.out.println("| [2]. Keluar       |");
            System.out.println("=====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    ubahProfile(customer);
                    break;
                default:
                    break;
            }
        }
    }

    public static void profileCust(customer loggedInCustomer) {
        // Mengecek apakah pelanggan sudah login
        if (loggedInCustomer != null) {
            System.out.println("| Profil");
            System.out.println("| Username : " + loggedInCustomer.getUsername());
            System.out.println("| Password : " + loggedInCustomer.getPassword());
            System.out.println("| Nama     : " + loggedInCustomer.getNama());
            System.out.println("| No. Telp : " + loggedInCustomer.getTelp());
            System.out.println("| Email    : " + loggedInCustomer.getEmail());
            System.out.println("| Alamat   : " + loggedInCustomer.getAlamatPengiriman());
        } else {
            System.out.println("Anda belum login.");
        }
    }

    public static void ubahProfile(customer loggedInCustomer) {
        if (loggedInCustomer != null) {
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

            String kolom = "";
            String nilaiBaru = "";

            switch (pilih) {
                case "1":
                    System.out.print("Masukkan username baru: ");
                    nilaiBaru = sc.nextLine();
                    kolom = "username";
                    loggedInCustomer.setUsername(nilaiBaru); // Ubah username pelanggan
                    break;
                case "2":
                    System.out.print("Masukkan password baru: ");
                    nilaiBaru = sc.nextLine();
                    kolom = "password";
                    loggedInCustomer.setPassword(nilaiBaru); // Ubah password pelanggan
                    break;
                case "3":
                    System.out.print("Masukkan nama baru: ");
                    nilaiBaru = sc.nextLine();
                    kolom = "nama";
                    loggedInCustomer.setNama(nilaiBaru); // Ubah nama pelanggan
                    break;
                case "4":
                    System.out.print("Masukkan nomor telepon baru: ");
                    int telpBaru = cekinput(sc);
                    nilaiBaru = String.valueOf(telpBaru);
                    kolom = "telp";
                    loggedInCustomer.setTelp(telpBaru); // Ubah nomor telepon pelanggan
                    break;
                case "5":
                    System.out.print("Masukkan email baru: ");
                    nilaiBaru = sc.nextLine();
                    kolom = "email";
                    while (!isValidEmail(nilaiBaru)) {
                        System.out.println("Alamat email tidak valid. Harap masukkan alamat email yang benar.");
                        System.out.print("Masukkan email baru: ");
                        nilaiBaru = sc.nextLine();
                    }
                    loggedInCustomer.setEmail(nilaiBaru);
                    break;
                case "6":
                    System.out.print("Masukkan alamat baru: ");
                    nilaiBaru = sc.nextLine();
                    kolom = "alamat";
                    loggedInCustomer.setAlamatPengiriman(nilaiBaru); // Ubah alamat pengiriman pelanggan
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    return; // Keluar dari metode jika pilihan tidak valid
            }

            try {
                userControl.updateCustomer(loggedInCustomer.getId(), kolom, nilaiBaru);
                System.out.println("Profil pelanggan berhasil diubah.");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Terjadi kesalahan saat memperbarui profil pelanggan.");
            }
        } else {
            System.out.println("Pelanggan tidak ditemukan.");
        }
    }

    public static void printData() {
        System.out.println("| [1]. Peralatan Rumah Tangga");
        System.out.println("| [2]. Perkakas");
        System.out.println("| [3]. Elektronik");
        System.out.println("| [4]. Furniture");
        System.out.println("| [5]. Keluar");
    }

    // Error Handling
    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }

    public static int cekinput(Scanner sc) {
        int input = 0; // Inisialisasi input dengan nilai default
        while (true) {
            try {
                input = sc.nextInt(); // Mengambil input dari scanner
                break; // Keluar dari loop jika input berhasil
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
                sc.next(); // Membersihkan buffer input setelah input tidak valid
            }
        }
        return input; // Mengembalikan nilai input yang valid
    }
}
