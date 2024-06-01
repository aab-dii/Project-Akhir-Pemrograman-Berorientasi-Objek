import java.util.Scanner;

import Antar.antar;
import Antar.antarControl;
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
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.sql.Date;

import User.customer;
import User.admin;
import User.kurir;
import User.user;
import User.userControl;

public class App {
    static LocalDate localDate = LocalDate.now();
    static Date sqlDate = Date.valueOf(localDate);
    static Scanner sc = new Scanner(System.in);
    static boolean loggedIn = false;

    static int custId = 0;
    static String username = "";
    static boolean exit = true;

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
        String newUsername = cekInputStr(sc, "Masukkan Username :");

        // Periksa apakah username sudah ada
        boolean dataExists = userControl.checkDataExists("username", newUsername);

        // Jika username belum digunakan, tambahkan pengguna baru
        if (!dataExists) {

            String newPassword = cekInputStr(sc, "Masukkan Password : ");

            try {
                customer newCustomer = new customer(0, "", newUsername, newPassword, "", "", "", 0, "customer"); // Sesuaikan
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
        String newUsername = cekInputStr(sc, "Masukkan Username :");

        // Periksa apakah username sudah ada
        boolean dataExists = userControl.checkDataExists("username", newUsername);

        // Jika username belum digunakan, tambahkan pengguna baru
        if (!dataExists) {
            String newPassword = cekInputStr(sc, "Masukkan Password : ");
            String nama = cekInputStr(sc, "Masukkan Nama : ");
            System.out.print("Masukkan email: ");
            String email = sc.nextLine();
            while (!isValidEmail(email)) {
                System.out.println("Alamat email tidak valid. Harap masukkan alamat email yang benar.");
                System.out.print("Masukkan email baru: ");
                email = sc.nextLine();
            }
            System.out.print("Masukkan Nomor Telepon :");
            int telp = cekInputInt(sc);
            String telpp = String.valueOf(telp);

            try {
                kurir newKurir = new kurir(0, nama, newUsername, newPassword, email, telpp, "kurir");
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
        String inputUsername = cekInputStr(sc, "Masukkan Username : ");
        String inputPassword = cekInputStr(sc, "Masukkan Password : ");

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
            System.out.println("============================");
            System.out.println("|        Menu Admin        |");
            System.out.println("============================");
            System.out.println("| [1]. Kelola Produk       |");
            System.out.println("| [2]. Kelola Kurir        |");
            System.out.println("| [3]. Profile             |");
            System.out.println("| [4]. Kelola Pesanan      |");
            System.out.println("| [5]. Keluar              |");
            System.out.println("============================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    kelolaProduk();
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
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
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
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void kelolaProduk() {
        String pilih = "";
        while (!pilih.equals("6")) {
            System.out.println("=======================");
            System.out.println("|    Kelola Barang    |");
            System.out.println("=======================");
            System.out.println("| [1]. Tambah Barang  |");
            System.out.println("| [2]. Lihat Barang   |");
            System.out.println("| [3]. Update Barang  |");
            System.out.println("| [4]. Delete Barang  |");
            System.out.println("| [6]. Keluar         |");
            System.out.println("=======================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    tambahData();
                    break;
                case "2":
                    lihatData();
                    break;
                case "3":
                    ubahData();
                    break;
                case "4":
                    hapusData();
                    break;
                case "5":
                    System.out.println("Keluar dari Menu Admin");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    // Tambah Data

    public static void tambahData() {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("===============================");
            System.out.println("|        Tambah Produk        |");
            System.out.println("===============================");
            printData();
            System.out.print(">> ");
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
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void tambahRt() {
        System.out.println("Masukkan data untuk Peralatan Rumah Tangga:");
        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");
        String bahan = cekInputStr(sc, "Bahan : ");
        String ukuran = cekInputStr(sc, "Ukuran : ");

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
        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");
        String tipe = cekInputStr(sc, "Tipe : ");
        String model = cekInputStr(sc, "Model : ");
        String warna = cekInputStr(sc, "Warna : ");

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
        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");
        String bahan = cekInputStr(sc, "Bahan : ");
        String ukuran = cekInputStr(sc, "Ukuran : ");

        product newProduct = new furniture(0, nama, deskripsi, harga, stok, merk, bahan, ukuran, "furnitur");
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
        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");

        product newProduct = new perkakas(0, nama, deskripsi, harga, stok, merk, "perkakas");

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
            System.out.println("===============================");
            System.out.println("|         Lihat Produk        |");
            System.out.println("===============================");
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
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void lihatRumahTangga() {
        int no = 0;
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
                "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk", "Bahan", "Ukuran");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        for (rumahTangga rt : productControl.getDatArt()) {
            no++;
            rt.printProductInfo(no);
        }
    }

    public static void lihatPerkakas() {
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s |\n",
                "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        int no = 0;
        for (perkakas pk : productControl.getDataPerkakas()) {
            no++;
            pk.printProductInfo(no);
        }
    }

    public static void lihatElektronik() {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
                "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk", "Tipe", "Model", "Warna");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------");
        int no = 0;
        for (elektronik el : productControl.getDataElektronik()) {
            no++;
            el.printProductInfo(no);
        }
    }

    public static void lihatFurniture() {
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
                "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk", "Bahan", "Ukuran");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        int no = 0;
        for (furniture fn : productControl.getDataFurniture()) {
                no++;
                fn.printProductInfo(no);
        }
    }

    // Ubah Data

    public static void ubahData() {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("===============================");
            System.out.println("|         Ubah Produk         |");
            System.out.println("===============================");
            printData();
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    ubahRt();
                    break;
                case "2":
                    ubahPerkakas();
                    break;
                case "3":
                    ubahElektronik();
                    break;
                case "4":
                    ubahFurniture();
                    break;

                default:
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void ubahRt() {
        // rumahTangga rtToUpdate = null;
        lihatRumahTangga();
        System.out.println("Data berapa yang ingin diubah");
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDatArt().size()) {
            System.out.println("Produk Tidak Ada.");
            return;
        }

        rumahTangga rtToUpdate = productControl.getDatArt().get(index);

        // Meminta input dari pengguna untuk memperbarui atribut produk
        System.out.println("Masukkan data untuk Peralatan Rumah Tangga:");
        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");
        String bahan = cekInputStr(sc, "Bahan : ");
        String ukuran = cekInputStr(sc, "Ukuran : ");

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

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat memperbarui produk di database.");
        }
    }

    public static void ubahElektronik() {
        // elektronik elektronikToUpdate = null;
        lihatElektronik();
        System.out.println("Data berapa yang ingin diubah");
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDataElektronik().size()) {
            System.out.println("Produk Tidak Ada.");
            return;
        }

        elektronik elektronikToUpdate = productControl.getDataElektronik().get(index);

        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");
        String tipe = cekInputStr(sc, "Tipe : ");
        String model = cekInputStr(sc, "Model : ");
        String warna = cekInputStr(sc, "Warna : ");

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
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDataPerkakas().size()) {
            System.out.println("Produk Tidak Ada.");
            return;
        }

        perkakas perkakasToUpdate = productControl.getDataPerkakas().get(index);

        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");

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
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDataPerkakas().size()) {
            System.out.println("Produk Tidak Ada.");
            return;
        }

        furniture furnitureToUpdate = productControl.getDataFurniture().get(index);

        String nama = cekInputStr(sc, "Nama Produk : ");
        String deskripsi = cekInputStr(sc, "Deskripsi : ");
        System.out.print("Harga: ");
        int harga = cekInputInt(sc);
        System.out.print("Stok: ");
        int stok = cekInputInt(sc);
        sc.nextLine();
        String merk = cekInputStr(sc, "Merk : ");
        String bahan = cekInputStr(sc, "Bahan : ");
        String ukuran = cekInputStr(sc, "Ukuran : ");

        // Mengubah atribut produk yang sesuai dengan input baru
        furnitureToUpdate.setNama(nama);
        furnitureToUpdate.setDeskripsi(deskripsi);
        furnitureToUpdate.setHarga(harga);
        furnitureToUpdate.setStok(stok);
        furnitureToUpdate.setMerk(merk);
        furnitureToUpdate.setBahan(bahan);
        furnitureToUpdate.setUkuran(ukuran);

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
            System.out.println("===============================");
            System.out.println("|         Hapus Produk        |");
            System.out.println("===============================");
            printData();
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    hapusRt();
                    break;
                case "2":
                    hapusPerkakas();
                    break;
                case "3":
                    hapusElektronik();
                    break;
                case "4":
                    hapusFurnitur();
                    break;
                default:
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }

    }

    public static void hapusRt() {
        lihatRumahTangga();
        System.out.println("Data berapa yang ingin dihapus");

        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= productControl.getDatArt().size()) {
            System.out.println("Produk Tidak Ada.");
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
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        // Periksa apakah nomor urut valid
        if (index < 0 || index >= productControl.getDataElektronik().size()) {
            System.out.println("Produk Tidak Ada.");
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
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        // Periksa apakah nomor urut valid
        if (index < 0 || index >= productControl.getDataPerkakas().size()) {
            System.out.println("Produk Tidak Ada.");
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
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        // Periksa apakah nomor urut valid
        if (index < 0 || index >= productControl.getDataFurniture().size()) {
            System.out.println("Produk Tidak Ada.");
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

    public static void kelolaPesanan() {
        String pilih = "";
        while (!pilih.equals("3")) {
            System.out.println("===========================");
            System.out.println("|      Kelola Pesanan     |");
            System.out.println("===========================");
            System.out.println("| [1]. Konfirmasi Pesanan |");
            System.out.println("| [2]. Kirim Pesanan      |");
            System.out.println("| [3]. Keluar             |");
            System.out.println("===========================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    konfirmasiPesanan();
                    break;
                case "2":
                    kirimPesanan();
                    break;
                default:
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void konfirmasiPesanan() {
        pesananControl.lihatPesanan("Menunggu Konfirmasi");
        System.out.println("Pilih pesanan yang ingin diproses");
        System.out.print(">> ");
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer
        if (index < 0 || index >= pesananControl.getDataPesanan().size()) {
            System.out.println("Produk Tidak Ada.");
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
        pesananControl.lihatPesanan("Pesanan Diproses");
        System.out.println("Pilih pesanan yang ingin dikirim");
        System.out.print(">> ");
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        if (index < 0 || index >= pesananControl.getDataPesanan().size()) {
            System.out.println("Produk Tidak Ada.");
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

        int index = cekInputInt(sc) - 1;
        sc.nextLine();

        if (index < 0 || index >= userControl.getDataKurir().size()) {
            System.out.println("Produk Tidak Ada.");
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
    public static void menuKurir(kurir kurir) throws ClassNotFoundException, SQLException {
        antarControl.perbaruiArrayAntar(kurir.getId());
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("===============================");
            System.out.println("|          Menu Kurir         |");
            System.out.println("===============================");
            System.out.println("| [1]. Tambah Pengiriman      |");
            System.out.println("| [2]. Lihat Pengiriman       |");
            System.out.println("| [3]. Konfirmasi Pengiriman  |");
            System.out.println("| [4]. Profil                 |");
            System.out.println("| [5]. Keluar                 |");
            System.out.println("===============================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    System.out.println("Tambah Pengiriman");
                    tambahPengiriman(kurir);
                    // Tambahkan logika untuk menambahkan pengiriman
                    break;
                case "2":
                    System.out.println("Lihat Pengiriman");
                    antarControl.tampilkanAntar();
                    // pesananControl.lihatPesanan("Pesanan Sedang Dikirim");
                    break;
                case "3":
                    System.out.println("Konfirmasi Pengiriman");
                    konfirmasiAntar(kurir);
                    // Tambahkan logika untuk mengkonfirmasi pengiriman
                    break;
                case "4":
                    System.out.println("Profil Kurir");
                    menuProfile(kurir);
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

    public static void tambahPengiriman(kurir kurir) throws ClassNotFoundException, SQLException {
        pesananControl.lihatPesanan("Pesanan Sedang Dikirim");
        System.out.println("Pilih pesanan yang ingin diantarkan");
        System.out.print(">> ");
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer
        if (index < 0 || index >= pesananControl.getDataPesanan().size()) {
            System.out.println("Produk Tidak Ada.");
            return;
        }
        pesanan ambildata = pesananControl.getDataPesanan().get(index);
        antar newAntar = new antar(0, ambildata.getIdProduk(), ambildata.getIdCust(), ambildata.getIdPesanan(),
                kurir.getId());
        antarControl.tambahAntar(newAntar);
        antarControl.perbaruiArrayAntar(kurir.getId());
        // antar newantar = new antar(1,idbarang, iduser, idpesan);
        // antarControl.tambahAntar(antar newantar);
        // System.out.println("kirim");
    }

    public static void konfirmasiAntar(kurir kurir) throws ClassNotFoundException, SQLException {
        antarControl.perbaruiArrayAntar(kurir.getId());
        antarControl.tampilkanAntar();
        System.out.println("Pilih pesanan yang telah diantarkan");
        System.out.print(">> ");
        int index = cekInputInt(sc) - 1;
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer
        if (index < 0 || index >= antarControl.getDataAntar().size()) {
            System.out.println("Produk Tidak Ada.");
            return;
        }
        antar ambildata = antarControl.getDataAntar().get(index);
        int idpesan = ambildata.getIdPesanan();
        antarControl.hapusAntar(idpesan);

    }

    public static void menuProfile(kurir kurir) {
        String pilih = "";
        while (!pilih.equals("2")) {
            profileKurir(kurir);
            System.out.println("=====================");
            System.out.println("| [1]. Ubah Profile |");
            System.out.println("| [2]. Keluar       |");
            System.out.println("=====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    ubahProfile(kurir);
                    break;
                default:
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void profileKurir(kurir kurir) {
        // Mengecek apakah pelanggan sudah login
        if (kurir != null) {
            System.out.println("| Profil");
            System.out.println("| Username : " + kurir.getUsername());
            System.out.println("| Password : " + kurir.getPassword());
            System.out.println("| Nama     : " + kurir.getNama());
            System.out.println("| No. Telp : " + kurir.getTelp());
            System.out.println("| Email    : " + kurir.getEmail());
        } else {
            System.out.println("Anda belum login.");
        }
    }

    public static void ubahProfile(kurir kurir) {
        if (kurir != null) {
            System.out.println("================================");
            System.out.println("| Pilih data yang ingin diubah |");
            System.out.println("================================");
            System.out.println("| [1]. Username                |");
            System.out.println("| [2]. Password                |");
            System.out.println("| [3]. Nama                    |");
            System.out.println("| [4]. No. Telpon              |");
            System.out.println("| [5]. Email                   |");
            System.out.println("================================");
            System.out.print(">> ");
            String pilih = sc.nextLine();

            String kolom = "";
            String nilaiBaru = "";

            switch (pilih) {
                case "1":
                    nilaiBaru = cekInputStr(sc, "Masukkan Username Baru : ");
                    boolean dataExists = userControl.checkDataExists("username", nilaiBaru);
                    if (!dataExists) {
                        kolom = "username";
                        kurir.setUsername(nilaiBaru);
                    } else {
                        System.out.println("Username Telah Digunakan");
                    }
                    break;
                case "2":
                    nilaiBaru = cekInputStr(sc, "Masukkan Password Baru : ");
                    kolom = "password";
                    kurir.setPassword(nilaiBaru);
                    break;
                case "3":
                    nilaiBaru = cekInputStr(sc, "Masukkan Nama Baru : ");
                    kolom = "nama";
                    kurir.setNama(nilaiBaru);
                    break;
                case "4":
                    System.out.print("Masukkan nomor telepon baru: ");
                    int telp = cekInputInt(sc);
                    String telpBaru = String.valueOf(telp);
                    dataExists = userControl.checkDataExists("telp", telpBaru);
                    if (!dataExists) {
                        kolom = "telp";
                        kurir.setTelp(telpBaru);
                    } else {
                        System.out.println("Nomor Telepon Telah Digunakan");
                    }
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
                    dataExists = userControl.checkDataExists("email", nilaiBaru);
                    if (!dataExists) {
                        kurir.setEmail(nilaiBaru);
                    } else {
                        System.out.println("Email Telah Digunakan");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    return;
            }
            try {
                userControl.updateCustomer(kurir.getId(), kolom, nilaiBaru);
                System.out.println("Profil pelanggan berhasil diubah.");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Terjadi kesalahan saat memperbarui profil pelanggan.");
            }
        } else {
            System.out.println("Pelanggan tidak ditemukan.");
        }
    }

    // Menu Customer
    public static void menuCust(customer customer) {
        String pilih = "";
        while (!pilih.equals("6")) {
            System.out.println("Hai " + customer.getUsername());
            System.out.println("Saldo : Rp." + customer.getSaldo());
            System.out.println("=====================");
            System.out.println("|   Menu Customer   |");
            System.out.println("=====================");
            System.out.println("| [1]. Beli Barang  |");
            System.out.println("| [2]. Keranjang    |");
            System.out.println("| [3]. Profile      |");
            System.out.println("| [4]. Pesanan      |");
            System.out.println("| [5]. Top Up Saldo |");
            System.out.println("| [6]. Keluar       |");
            System.out.println("=====================");
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    menuBeli(customer);
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
                    topUp(customer);
                    break;
                case "6":
                    System.out.println("Keluar dari Menu Customer");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void topUp(customer customer) {
        String pilih = "";
        System.out.println("[1]. Rp. 25.000");
        System.out.println("[2]. Rp. 50.000");
        System.out.println("[3]. Rp. 100.000");
        System.out.println("[4]. Rp. 150.000");
        System.out.println("[5]. Rp. 200.000");
        System.out.println("[6]. Rp. 300.000");
        System.out.println("[7]. Rp. 1.000.000");
        System.out.println("[8]. Rp. 2.000.000");
        pilih = sc.nextLine();

        int jumlahTopUp = 0;
        switch (pilih) {
            case "1":
                jumlahTopUp = 25000;
                break;
            case "2":
                jumlahTopUp = 50000;
                break;
            case "3":
                jumlahTopUp = 100000;
                break;
            case "4":
                jumlahTopUp = 150000;
                break;
            case "5":
                jumlahTopUp = 200000;
                break;
            case "6":
                jumlahTopUp = 300000;
                break;
            case "7":
                jumlahTopUp = 1000000;
                break;
            case "8":
                jumlahTopUp = 2000000;
                break;
            default:
                System.out.println("Pilihan tidak tersedia.");
                return;
        }
        try {
            customer.setSaldo(customer.getSaldo() + jumlahTopUp);
            userControl.updateSaldo(customer.getId(), jumlahTopUp, true);
            System.out.println("Top up berhasil sebesar Rp. " + jumlahTopUp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat melakukan top up.");
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

    public static void menuBeli(customer customer) {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("===============================");
            System.out.println("|         Beli Barang         |");
            System.out.println("===============================");
            printData();
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    productControl.lihatProduk(1);
                    beliRt(customer);
                    productControl.lihatProduk();
                    break;
                case "2":
                    productControl.lihatProduk(1);
                    beliPerkakas(customer);
                    productControl.lihatProduk();
                    break;
                case "3":
                    productControl.lihatProduk(1);
                    beliElektronik(customer);
                    productControl.lihatProduk();
                    break;
                case "4":
                    productControl.lihatProduk(1);
                    beliFurniture(customer);
                    productControl.lihatProduk();
                    break;
                default:
                    System.out.println("Pilihan Tida Tersedia");
                    break;
            }
        }
    }

    public static void menuKeranjang(customer customer) {
        try {
            String pilih = " ";

            // Periksa apakah keranjang kosong sebelum menampilkan menu
            keranjangControl.lihatKeranjang(customer);
            if (keranjangControl.getKeranjang().isEmpty()) {
                System.out.println("Keranjang kosong.");
                return;
            }

            // Tampilkan menu hingga pengguna memilih untuk checkout atau tidak
            while (!pilih.equals("1") && !pilih.equals("2")) {
                System.out.println("Ingin CheckOut?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak");
                pilih = sc.nextLine();
                switch (pilih) {
                    case "1":
                        menuCheckOutKr(customer);
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

    public static void menuCheckout(customer customer, pesanan newPesanan, int total) {
        try {
            if (customer.getNama() == null && customer.getAlamatPengiriman() == null && customer.getTelp() == null) {
                System.out.println("Mohon melengkapi data terlebih dahulu");
                return;
            }
            if (customer.getSaldo() < total) {
                System.out.println("Maaf saldo anda tidak mencukupi");
                return;
            }
            int saldo = customer.getSaldo() - total;
            customer.setSaldo(saldo);

            userControl.updateSaldo(customer.getId(), saldo, false);
            pesananControl.tambahPesanan(newPesanan);
            productControl.kurangStok(newPesanan.getIdProduk(), newPesanan.getJumlah());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menambahkan produk ke pesanan.");
        }
        System.out.println("Produk berhasil dibeli.");

    }

    public static void menuCheckOutKr(customer customer) {
        try {
            if (customer.getNama() == null && customer.getAlamatPengiriman() == null && customer.getTelp() == null) {
                System.out.println("Mohon melengkapi data terlebih dahulu");
                return;
            }

            int total = keranjangControl.lihatKeranjang(customer); // Dapatkan total harga dari lihatKeranjang
            if (customer.getSaldo() < total) {
                System.out.println("Maaf saldo anda tidak mencukupi");
                return;
            }

            for (Keranjang.keranjang keranjang : keranjangControl.getKeranjang()) {
                int idKeranjang = keranjang.getIdKeranjang();
                int idCust = keranjang.getCustId();
                int idProduk = keranjang.getIdProduk();
                int jumlah = keranjang.getJumlah();

                pesanan newPesanan = new pesanan(0, idCust, idProduk, jumlah, "Menunggu Konfirmasi", sqlDate);

                try {
                    pesananControl.tambahPesanan(newPesanan);
                    keranjangControl.hapusKeranjang(idKeranjang);
                    productControl.kurangStok(idProduk, jumlah);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                }
            }
            System.out.println("Produk berhasil dibeli.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat melakukan checkout.");
        }
    }

    // public static void lihatRumahTangga(int numover) {
    //     int no = 0;
    //     System.out.println(
    //             "--------------------------------------------------------------------------------------------------------------------------------");
    //     System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
    //             "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk", "Bahan", "Ukuran");
    //     System.out.println(
    //             "--------------------------------------------------------------------------------------------------------------------------------");
    //     for (rumahTangga rt : productControl.getDatArt()) {
    //         if (rt.getStok() != 0) {
    //             no++;
    //             rt.printProductInfo(no);
    //         }
    //     }
    // }
    
    // public static void lihatPerkakas(int numover) {
    //     System.out.println(
    //             "--------------------------------------------------------------------------------------------------------------------------------");
    //     System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s |\n",
    //             "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk");
    //     System.out.println(
    //             "--------------------------------------------------------------------------------------------------------------------------------");
    //     int no = 0;
    //     for (perkakas pk : productControl.getDataPerkakas()) {
    //         if (pk.getStok() != 0) {
    //             no++;
    //             pk.printProductInfo(no);
    //         }
    //     }
    // }
    
    // public static void lihatElektronik(int numover) {
    //     System.out.println(
    //             "------------------------------------------------------------------------------------------------------------------------------------");
    //     System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
    //             "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk", "Tipe", "Model", "Warna");
    //     System.out.println(
    //             "------------------------------------------------------------------------------------------------------------------------------------");
    //     int no = 0;
    //     for (elektronik el : productControl.getDataElektronik()) {
    //         if (el.getStok() != 0) {
    //             no++;
    //             el.printProductInfo(no);
    //         }
    //     }
    // }
    
    // public static void lihatFurniture(int numover) {
    //     System.out.println(
    //             "--------------------------------------------------------------------------------------------------------------------------------");
    //     System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
    //             "No", "Nama Produk", "Deskripsi", "Harga", "Stok", "Merk", "Bahan", "Ukuran");
    //     System.out.println(
    //             "--------------------------------------------------------------------------------------------------------------------------------");
    //     int no = 0;
    //     for (furniture fn : productControl.getDataFurniture()) {
    //         if (fn.getStok() != 0) {
    //             no++;
    //             fn.printProductInfo(no);
    //         }
    //     }
    // }
    

    public static void beliRt(customer customer) {
        while (true) {
            System.out.println("Peralatan Rumah Tangga");
            lihatRumahTangga();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih produk yang ingin Anda beli (atau q untuk keluar): ");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = cekInputInt(sc);
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = cekInputInt(sc) - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDatArt().size()) {
                    System.out.println("Produk Tidak Ada.");
                    return;
                }

                rumahTangga rtBeli = productControl.getDatArt().get(index);

                System.out.print("Masukkan jumlah: ");
                int jumlah = cekInputInt(sc);
                sc.nextLine();
                if (jumlah <= 0 || jumlah > rtBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok atau 0");
                    break;
                }
                System.out.println("==================");
                System.out.println("| [1]. Beli      |");
                System.out.println("| [2]. Keranjang |");
                System.out.println("==================");
                System.out.print(">> ");
                String cekBeli = sc.nextLine();

                int total = 0;
                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, rtBeli.getId(), jumlah, "Menunggu Konfirmasi" ,sqlDate);
                    total = rtBeli.getHarga() * jumlah;
                    menuCheckout(customer, newPesanan, total);

                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, rtBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                    System.out.println("Produk berhasil ditambahkan ke keranjang.");
                } else {
                    System.out.println("Pilihan Tidak Tersedia");
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

    public static void beliElektronik(customer customer) {
        while (true) {
            System.out.println("Elektronik");
            lihatElektronik();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih produk yang ingin Anda beli (atau q untuk keluar): ");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = cekInputInt(sc);
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = cekInputInt(sc) - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDataElektronik().size()) {
                    System.out.println("Produk tidak ada.");
                    return;
                }

                elektronik elBeli = productControl.getDataElektronik().get(index);

                System.out.print("Masukkan jumlah: ");
                int jumlah = cekInputInt(sc);
                sc.nextLine();
                if (jumlah <= 0 || jumlah > elBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok atau 0");
                    break;
                }

                System.out.println("==================");
                System.out.println("| [1]. Beli      |");
                System.out.println("| [2]. Keranjang |");
                System.out.println("==================");
                System.out.print(">> ");
                String cekBeli = sc.nextLine();

                int total = 0;
                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, elBeli.getId(), jumlah, "Menunggu Konfirmasi", sqlDate);
                    total = elBeli.getHarga() * jumlah;
                    menuCheckout(customer, newPesanan, total);
                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, elBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                    System.out.println("Produk berhasil ditambahkan ke keranjang.");
                } else {
                    System.out.println("Pilihan Tidak Tersedia");
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

    public static void beliFurniture(customer customer) {
        while (true) {
            System.out.println("Furniture");
            lihatFurniture();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih produk yang ingin Anda beli (atau q untuk keluar): ");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = cekInputInt(sc);
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = cekInputInt(sc) - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDataFurniture().size()) {
                    System.out.println("Produk Tidak Ada.");
                    return;
                }

                furniture frBeli = productControl.getDataFurniture().get(index);

                System.out.println("Masukkan jumlah yang inign dibeli");
                int jumlah = cekInputInt(sc);
                sc.nextLine();
                if (jumlah <= 0 || jumlah > frBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok atau 0");
                    break;
                }

                System.out.println("==================");
                System.out.println("| [1]. Beli      |");
                System.out.println("| [2]. Keranjang |");
                System.out.println("==================");
                System.out.print(">> ");
                String cekBeli = sc.nextLine();

                int total = 0;
                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, frBeli.getId(), jumlah, "Menunggu Konfirmasi",sqlDate);
                    total = frBeli.getHarga() * jumlah;
                    menuCheckout(customer, newPesanan, total);
                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, frBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                    System.out.println("Produk berhasil ditambahkan ke keranjang.");
                } else {
                    System.out.println("Pilihan Tidak Tersedia");
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

    public static void beliPerkakas(customer customer) {
        while (true) {
            System.out.println("Perkakas");
            lihatPerkakas();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih produk yang ingin Anda beli (atau q untuk keluar): ");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = cekInputInt(sc);
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                int index = cekInputInt(sc) - 1;
                sc.nextLine();
                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                if (index < 0 || index >= productControl.getDataPerkakas().size()) {
                    System.out.println("Produk Tidak Ada.");
                    return;
                }

                perkakas prBeli = productControl.getDataPerkakas().get(index);

                System.out.print("Masukkan jumlah: ");
                int jumlah = cekInputInt(sc);
                sc.nextLine();
                if (jumlah <= 0 || jumlah > prBeli.getStok()) {
                    System.out.println("Jumlah yang anda beli melebihi stok atau 0");
                    break;
                }

                System.out.println("==================");
                System.out.println("| [1]. Beli      |");
                System.out.println("| [2]. Keranjang |");
                System.out.println("==================");
                System.out.print(">> ");
                String cekBeli = sc.nextLine();

                int total = 0;
                if (cekBeli.equals("1")) {
                    pesanan newPesanan = new pesanan(0, custId, prBeli.getId(), jumlah, "Menunggu Konfirmasi",sqlDate);
                    total = prBeli.getHarga() * jumlah;
                    menuCheckout(customer, newPesanan, total);
                } else if (cekBeli.equals("2")) {
                    keranjang newKeranjang = new keranjang(0, custId, prBeli.getId(), jumlah);

                    try {
                        keranjangControl.tambahKeranjang(newKeranjang);

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Terjadi kesalahan saat menambahkan produk ke keranjang.");
                    }
                    System.out.println("Produk berhasil ditambahkan ke keranjang.");
                } else {
                    System.out.println("Pilihan Tidak Tersedia");
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
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }

    public static void profileCust(customer customer) {
        // Mengecek apakah pelanggan sudah login
        if (customer != null) {
            System.out.println("| Profil");
            System.out.println("| Username : " + customer.getUsername());
            System.out.println("| Password : " + customer.getPassword());
            System.out.println("| Nama     : " + customer.getNama());
            System.out.println("| No. Telp : " + customer.getTelp());
            System.out.println("| Email    : " + customer.getEmail());
            System.out.println("| Alamat   : " + customer.getAlamatPengiriman());
        } else {
            System.out.println("Anda belum login.");
        }
    }

    public static void ubahProfile(customer customer) {
        if (customer != null) {
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
                    nilaiBaru = cekInputStr(sc, "Masukkan Username Baru : ");
                    boolean dataExists = userControl.checkDataExists("username", nilaiBaru);
                    if (!dataExists) {
                        kolom = "username";
                        customer.setUsername(nilaiBaru);
                    } else {
                        System.out.println("Username Telah Digunakan");
                    }
                    break;
                case "2":
                    nilaiBaru = cekInputStr(sc, "Masukkan Password Baru : ");
                    kolom = "password";
                    customer.setPassword(nilaiBaru);
                    break;
                case "3":
                    nilaiBaru = cekInputStr(sc, "Masukkan Nama Baru : ");
                    kolom = "nama";
                    customer.setNama(nilaiBaru);
                    break;
                case "4":
                    System.out.print("Masukkan nomor telepon baru: ");
                    int telp = cekInputInt(sc);
                    String telpBaru = String.valueOf(telp);
                    dataExists = userControl.checkDataExists("telp", telpBaru);
                    if (!dataExists) {
                        kolom = "telp";
                        customer.setTelp(telpBaru);
                    } else {
                        System.out.println("Nomor Telepon Telah Digunakan");
                    }
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
                    dataExists = userControl.checkDataExists("email", nilaiBaru);
                    if (!dataExists) {
                        customer.setEmail(nilaiBaru);
                    } else {
                        System.out.println("Email Telah Digunakan");
                    }
                    break;
                case "6":
                    nilaiBaru = cekInputStr(sc, "Masukkan Alamat Baru : ");
                    kolom = "alamat";
                    customer.setAlamatPengiriman(nilaiBaru); // Ubah alamat pengiriman pelanggan
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    return; // Keluar dari metode jika pilihan tidak valid
            }

            try {
                userControl.updateCustomer(customer.getId(), kolom, nilaiBaru);
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
        System.out.println("| [1]. Peralatan Rumah Tangga |");
        System.out.println("| [2]. Perkakas               |");
        System.out.println("| [3]. Elektronik             |");
        System.out.println("| [4]. Furniture              |");
        System.out.println("| [5]. Keluar                 |");
        System.out.println("===============================");
        System.out.print(">> ");
    }

    // Error Handling
    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }

    public static String cekInputStr(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input tidak boleh kosong atau hanya spasi. Silakan coba lagi.");
            } else {
                break;
            }
        }
        return input;
    }

    public static int cekInputInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt(); // Membaca input langsung dari scanner
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
                sc.next(); // Membersihkan buffer input setelah input tidak valid
            }
        }
    }
}
