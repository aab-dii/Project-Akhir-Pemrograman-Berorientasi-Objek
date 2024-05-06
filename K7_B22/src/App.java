import java.util.Scanner;

import Product.rumahTangga;
import Product.elektronik;
import Product.furniture;
import Product.perkakas;

import Keranjang.keranjang;

import java.util.ArrayList;
import java.util.InputMismatchException;

import User.customer;
import User.admin;
import User.kurir;

public class App {
    static Scanner sc = new Scanner(System.in);
    static boolean loggedIn = false;

    static int custId = 0;
    static String username = "";
    static boolean exit = true;

    static ArrayList<customer> dataCust = new ArrayList<>();
    static ArrayList<kurir> dataKurir = new ArrayList<>();
    static ArrayList<admin> dataAdmin = new ArrayList<>();

    static ArrayList<keranjang> dataKeranjang = new ArrayList<>();

    static ArrayList<rumahTangga> datArt = new ArrayList<>();
    static ArrayList<elektronik> dataElektronik = new ArrayList<>();
    static ArrayList<furniture> dataFurniture = new ArrayList<>();
    static ArrayList<perkakas> dataPerkakas = new ArrayList<>();

    public static void main(String[] args) throws Exception {
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
        System.out.print("Masukkan Password: ");
        String newPassword = sc.nextLine();

        boolean usernameExists = false;

        // Periksa apakah username sudah digunakan oleh pelanggan lain
        for (customer customer : dataCust) {
            if (customer.getUsername().equals(newUsername)) {
                System.out.println("Username telah digunakan, Silahkan gunakan username lain");
                usernameExists = true;
                break; // Keluar dari loop karena username sudah ditemukan
            }
        }

        // Jika username belum digunakan, tambahkan pelanggan baru
        if (!usernameExists) {
            customer newCustomer = new customer(
                    dataCust.size() + 1, // ID pelanggan diatur sesuai dengan indeks ArrayList + 1
                    "", // Nama pelanggan sementara diatur sebagai string kosong
                    newUsername,
                    newPassword,
                    "", // Email pelanggan sementara diatur sebagai string kosong
                    0, // Nomor telepon pelanggan sementara diatur sebagai 0
                    "", // Alamat pengiriman sementara diatur sebagai string kosong
                    "" // Riwayat pembelian sementara diatur sebagai string kosong
            );

            // Tambahkan objek Customer baru ke dalam ArrayList dataCust
            dataCust.add(newCustomer);
            System.out.println("Pendaftaran berhasil!");
        }
    }

    public static void login() {
        System.out.print("Masukkan username: ");
        String inputUsername = sc.nextLine();
        System.out.print("Masukkan password: ");
        String inputPassword = sc.nextLine();

        boolean loginSuccess = false;

        // Iterasi melalui setiap objek Customer dalam ArrayList dataCust
        for (customer customer : dataCust) {
            // Memeriksa apakah inputUsername dan inputPassword cocok dengan yang ada dalam
            // objek Customer saat ini
            if (customer.username.equals(inputUsername) && customer.password.equals(inputPassword)) {
                System.out.println("Login berhasil!");
                loggedIn = true;
                username = inputUsername;
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
        String pilih = "";
        while (!pilih.equals("3")) {
            System.out.println("Menu Admin");
            System.out.println(" [1]. Kelola Produk ");
            System.out.println(" [2]. Kelola Kurir ");
            System.out.println(" [3]. Keluar");
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
                    break;
                default:
                    break;
            }
        }
    }

    public static void kelolaKurir() {
        String pilih = "";
        while (!pilih.equals("6")) {
            System.out.println("=====================");
            System.out.println("|    Kelola Kurir   |");
            System.out.println("=====================");
            System.out.println("| [1]. Tambah Kurir |");
            System.out.println("| [2]. Lihat Kurir  |");
            System.out.println("| [3]. Update Kurir |");
            System.out.println("| [4]. Delete Kurir |");
            System.out.println("| [6]. Keluar       |");
            System.out.println("=====================");
            System.out.println(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    tambahKurir();
                    break;
                case "2":
                    lihatKurir();
                    break;
                default:
                    break;
            }
        }
    }

    public static void tambahKurir() {
        System.out.println("Masukkan username :");
        String username = sc.nextLine();
        System.out.println("Masukkan password :");
        String password = sc.nextLine();
        System.out.println("Masukkan Nama :");
        String nama = sc.nextLine();
        System.out.println("Masukkan Email :");
        String email = sc.nextLine();
        System.out.println("Masukkan Nomor Telepon :");
        int nomor = sc.nextInt();

        boolean usernameExists = false;

        for (kurir kurir : dataKurir) {
            if (kurir.getUsername().equals(username)) {
                System.out.println("Username telah digunakan, Silahkan gunakan username lain");
                usernameExists = true;
                break; // Keluar dari loop karena username sudah ditemukan
            }
        }

        if (!usernameExists) {
            kurir newKurir = new kurir(
                    dataKurir.size() + 1,
                    nama,
                    username,
                    password,
                    email,
                    nomor);

            dataKurir.add(newKurir);
            System.out.println("Pendaftaran berhasil!");
        }
    }

    public static void lihatKurir() {
        for (kurir kurir : dataKurir) {
            System.out.println("Nama : " + kurir.getNama());
            System.out.println("Email : " + kurir.getEmail());
            System.out.println("No. Telp : " + kurir.getTelp());
            System.out.println("Username : " + kurir.getUsername());
            System.out.println("Password : " + kurir.getPassword());
        }
    }

    public static void kelolaData() {
        String pilih = "";
        while (!pilih.equals("6")) {
            System.out.println("====================");
            System.out.println("|   Kelola Barang  |");
            System.out.println("====================");
            System.out.println("| [1]. Tambah Data |");
            System.out.println("| [2]. Lihat Data  |");
            System.out.println("| [3]. Update Data |");
            System.out.println("| [4]. Delete Data |");
            System.out.println("| [6]. Keluar      |");
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
        System.out.println("Merk: ");
        String merk = sc.nextLine();

        rumahTangga newRumahTangga = new rumahTangga(datArt.size() + 1, nama, deskripsi, harga, stok, bahan, ukuran,
                merk);
        datArt.add(newRumahTangga);

        System.out.println("Data Peralatan Rumah Tangga berhasil ditambahkan.");
    }

    public static void tambahElektronik() {
        System.out.println("Masukkan data untuk Elektronik:");
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
        System.out.println("Tipe: ");
        String tipe = sc.nextLine();
        System.out.println("Model: ");
        String model = sc.nextLine();
        System.out.println("Warna: ");
        String warna = sc.nextLine();

        elektronik newElektronik = new elektronik(dataElektronik.size() + 1, nama, deskripsi, harga, stok, merk, tipe,
                model,
                warna);

        dataElektronik.add(newElektronik);

        System.out.println("Data Elektronik berhasil ditambahkan.");
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

        furniture newFurniture = new furniture(dataFurniture.size() + 1, nama, deskripsi, harga, stok, merk);

        dataFurniture.add(newFurniture);

        System.out.println("Data Furniture berhasil ditambahkan.");
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

        perkakas newPerkakas = new perkakas(dataPerkakas.size() + 1, nama, deskripsi, harga, stok, merk);

        dataPerkakas.add(newPerkakas);

        System.out.println("Data Furniture berhasil ditambahkan.");
    }

// Lihat Data
    public static void lihatData() {
        String pilih = "";
        while (!pilih.equals("5")) {

            System.out.println("| Lihat Data");
            printData();
            System.out.print(">> ");
            pilih = sc.nextLine();
            switch (pilih) {
                case "1":
                    lihatRt();
                    break;
                case "2":
                    lihatPerkakas();
                    break;
                case "3":
                    lihatElektronik();
                    break;
                case "4":
                    lihatFurnitur();
                    break;
                default:
                    break;
            }
        }
    }

    public static void lihatRt() {
        for (rumahTangga rumahTangga : datArt) {
            System.out.println(rumahTangga.getNama());
            System.out.println(rumahTangga.getDeskripsi());
            System.out.println(rumahTangga.getHarga());
            System.out.println(rumahTangga.getStok());
            System.out.println(rumahTangga.getBahan());
            System.out.println(rumahTangga.getUkuran());
            System.out.println(rumahTangga.getMerk());
        }
    }

    public static void lihatElektronik() {
        for (elektronik elektronik : dataElektronik) {
            System.out.println(elektronik.getNama());
            System.out.println(elektronik.getDeskripsi());
            System.out.println(elektronik.getHarga());
            System.out.println(elektronik.getStok());
            System.out.println(elektronik.getMerk());
            System.out.println(elektronik.getTipe());
            System.out.println(elektronik.getModel());
            System.out.println(elektronik.getWarna());
        }
    }

    public static void lihatFurnitur() {
        for (furniture furniture : dataFurniture) {
            System.out.println(furniture.getNama());
            System.out.println(furniture.getDeskripsi());
            System.out.println(furniture.getHarga());
            System.out.println(furniture.getStok());
            System.out.println(furniture.getMerk());
        }
    }

    public static void lihatPerkakas() {
        for (perkakas perkakas : dataPerkakas) {
            System.out.println(perkakas.getNama());
            System.out.println(perkakas.getDeskripsi());
            System.out.println(perkakas.getHarga());
            System.out.println(perkakas.getStok());
            System.out.println(perkakas.getMerk());
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
                    break;
                case "3":
                    break;
                case "4":
                    break;

                default:
                    break;
            }
        }
    }

    public static void ubahRt() {
        rumahTangga rtToUpdate = null;
        lihatRt();
        System.out.println("Data berapa yang ingin diubah");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (rumahTangga rumahTangga : datArt) {
            if (rumahTangga.getId() == id) {
                rtToUpdate = rumahTangga; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (rtToUpdate != null) {
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

            System.out.println("Data produk peralatan rumah tangga berhasil diubah.");
            kelolaData();
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
        }
    }

    public static void ubahElektronik() {
        elektronik elektronikToUpdate = null;
        lihatElektronik();
        System.out.println("Data berapa yang ingin diubah");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (elektronik elektronik : dataElektronik) {
            if (elektronik.getId() == id) {
                elektronikToUpdate = elektronik; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (elektronikToUpdate != null) {
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

            System.out.println("Data produk Elektronik berhasil diubah.");
            kelolaData();
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
        }
    }

    public static void ubahPerkakas() {
        perkakas perkakasToUpdate = null;
        lihatPerkakas();
        System.out.println("Data berapa yang ingin diubah");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (perkakas perkakas : dataPerkakas) {
            if (perkakas.getId() == id) {
                perkakasToUpdate = perkakas; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (perkakasToUpdate != null) {
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

            System.out.println("Data produk Elektronik berhasil diubah.");
            kelolaData();
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
        }
    }

    public static void ubahFurniture() {
        furniture furnitureToUpdate = null;
        lihatFurnitur();
        System.out.println("Data berapa yang ingin diubah");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (furniture furniture : dataFurniture) {
            if (furniture.getId() == id) {
                furnitureToUpdate = furniture; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (furnitureToUpdate != null) {
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

            System.out.println("Data produk Furniture berhasil diubah.");
            kelolaData();
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
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
                    break;
                case "3":
                    break;
                case "4":
                    break;

                default:
                    break;
            }
        }

    }

    public static void hapusRt() {
        rumahTangga rtToRemove = null;
        lihatRt();
        System.out.println("Data berapa yang ingin dihapus");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (rumahTangga rumahTangga : datArt) {
            if (rumahTangga.getId() == id) {
                rtToRemove = rumahTangga; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (rtToRemove != null) {
            // Menghapus produk dari daftar produk
            datArt.remove(rtToRemove);

            System.out.println("Data produk peralatan rumah tangga berhasil dihapus.");
            kelolaData(); // Kembali ke menu admin setelah penghapusan berhasil
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
        }
    }

    public static void hapusElektronik() {
        elektronik elektronikToRemove = null;
        lihatElektronik();
        System.out.println("Data berapa yang ingin dihapus");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (elektronik elektronik : dataElektronik) {
            if (elektronik.getId() == id) {
                elektronikToRemove = elektronik; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (elektronikToRemove != null) {
            // Menghapus produk dari daftar produk
            dataElektronik.remove(elektronikToRemove);

            System.out.println("Data produk Elektronik berhasil dihapus.");
            kelolaData(); // Kembali ke menu admin setelah penghapusan berhasil
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
        }
    }

    public static void hapusPerkakas() {
        perkakas perkakasToRemove = null;
        lihatPerkakas();
        System.out.println("Data berapa yang ingin dihapus");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (perkakas perkakas : dataPerkakas) {
            if (perkakas.getId() == id) {
                perkakasToRemove = perkakas; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (perkakasToRemove != null) {
            // Menghapus produk dari daftar produk
            dataPerkakas.remove(perkakasToRemove);

            System.out.println("Data produk perkakas berhasil dihapus.");
            kelolaData(); // Kembali ke menu admin setelah penghapusan berhasil
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
        }
    }

    public static void hapusFurnitur() {
        furniture furnitureToRemove = null;
        lihatFurnitur();
        System.out.println("Data berapa yang ingin dihapus");
        int id = sc.nextInt();
        sc.nextLine(); // Menangkap karakter baris baru yang tersisa di dalam buffer

        for (furniture furniture : dataFurniture) {
            if (furniture.getId() == id) {
                furnitureToRemove = furniture; // Simpan referensi rt yang sesuai
                break;
            }
        }

        if (furnitureToRemove != null) {
            // Menghapus produk dari daftar produk
            dataFurniture.remove(furnitureToRemove);

            System.out.println("Data produk Furnitur berhasil dihapus.");
            kelolaData(); // Kembali ke menu admin setelah penghapusan berhasil
        } else {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
        }
    }

// Menu Kurir
    public static void menuKurir() {
        String pilih = "";
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

// Menu Customer
    public static void menuCust() {
        String pilih = "";
        while (!pilih.equals("5")) {
            System.out.println("Hai " + username);
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
                    menuBeli();
                    break;
                case "2":
                    System.out.println("Lihat Barang");
                    break;
                case "3":
                    System.out.println("Keranjang");
                    lihatKeranjang();
                    break;
                case "4":
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

                default:
                    break;
            }
        }
    }

    public static void beliRt() {
        while (true) {
            System.out.println("Peralatan Rumah Tangga");
            lihatRt();
            System.out.println("[q]. Keluar");
            System.out.print("Pilih ID produk yang ingin Anda beli (atau q untuk keluar): ");

            // Memeriksa apakah pengguna memasukkan angka atau q untuk keluar
            if (sc.hasNextInt()) {
                int beli = sc.nextInt();
                sc.nextLine(); // Membersihkan karakter baris baru di buffer

                if (beli == 'q') {
                    System.out.println("Keluar dari menu pembelian.");
                    break; // Keluar dari loop saat pengguna memilih untuk keluar
                }

                // Mencari produk yang sesuai dengan ID yang dipilih pengguna
                boolean produkDitemukan = false;
                for (rumahTangga rt : datArt) {
                    if (beli == rt.getId()) {
                        produkDitemukan = true;
                        keranjang newKeranjang = new keranjang(custId, beli, "rt");
                        dataKeranjang.add(newKeranjang);
                        System.out.println("Produk berhasil ditambahkan ke keranjang.");
                        break; // Keluar dari loop setelah menambahkan produk ke keranjang
                    }
                }

                // Memberikan umpan balik jika produk tidak ditemukan
                if (!produkDitemukan) {
                    System.out.println("Produk tidak ditemukan. Silakan masukkan ID produk yang valid.");
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

    public static void lihatKeranjang() {
        String nama = "";
        int harga = 0;
        int total = 0;
        for (keranjang keranjang : dataKeranjang) {
            if (keranjang.getCustId() == custId) {
                if (keranjang.getKode() == "rt") {
                    for (rumahTangga rumahTangga : datArt) {
                        if (keranjang.getIdKeranjang() == rumahTangga.getId()) {
                            nama = rumahTangga.getNama();
                            harga = rumahTangga.getHarga();
                            break;
                        }
                    }
                } else if (keranjang.getKode() == "elektronik") {
                    for (elektronik elektronik : dataElektronik) {
                        if (keranjang.getIdKeranjang() == elektronik.getId()) {
                            nama = elektronik.getNama();
                            harga = elektronik.getHarga();
                            break;
                        }
                    }
                }
                else if (keranjang.getKode() == "perkakas") {
                    for (perkakas perkakas : dataPerkakas) {
                        if (keranjang.getIdKeranjang() == perkakas.getId()) {
                            nama = perkakas.getNama();
                            harga = perkakas.getHarga();
                            break;
                        }
                    }
                }
                else if (keranjang.getKode() == "furnitur") {
                    for (furniture furniture : dataFurniture) {
                        if (keranjang.getIdKeranjang() == furniture.getId()) {
                            nama = furniture.getNama();
                            harga = furniture.getHarga();
                            break;
                        }
                    }
                }
            }
            total += harga;
            System.out.println(nama + " " + harga);
        }
        System.out.println(total);
    }

    public static void menuProfile() {
        String pilih = "";
        while (!pilih.equals("2")) {
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
        for (customer customer : dataCust) {
            System.out.println("| Profil");
            if (customer.getId() == (custId)) {
                System.out.println("| Username : " + customer.getUsername());
                System.out.println("| Password : " + customer.getPassword());
                System.out.println("| Nama     : " + customer.getNama());
                System.out.println("| No. Telp : " + customer.getTelp());
                System.out.println("| Email    : " + customer.getEmail());
                System.out.println("| Alamat   : " + customer.getAlamatPengiriman());
            }
        }
    }

    public static void ubahProfile() {
        customer customerToUpdate = null; // Deklarasikan variabel customerToUpdate untuk menyimpan pelanggan yang akan
                                          // diperbarui

        // Temukan pelanggan yang sesuai berdasarkan Id
        for (customer customer : dataCust) {
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
                    int telpBaru = cekinput(sc);
                    customerToUpdate.setTelp(telpBaru); // Ubah nomor telepon pelanggan
                    break;
                case "5":
                    System.out.print("Masukkan email baru: ");
                    String emailBaru = sc.nextLine();

                    while (!isValidEmail(emailBaru)) {
                        System.out.println("Alamat email tidak valid. Harap masukkan alamat email yang benar.");
                        System.out.print("Masukkan email baru: ");
                        emailBaru = sc.nextLine();
                    }
                    customerToUpdate.setEmail(emailBaru);
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
