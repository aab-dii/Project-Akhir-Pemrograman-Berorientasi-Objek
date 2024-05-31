package Antar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.DatabaseConnection;

public class antarControl {
    private static ArrayList<antar> dataAntar = new ArrayList<antar>();
    public static void tambahAntar(int id_produk, int id_pelanggan, int id_pesanan) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        
        try {
            // Menghubungkan ke database
            connection = DatabaseConnection.getConnection();
            String sql = "select * from tbantar where id_pesanan = ?";
            statement1 = connection.prepareStatement(sql);
            statement1.setInt(1, id_pesanan);
            ResultSet rowdetect = statement1.executeQuery();
            if (rowdetect.next()) {
                System.out.println("Barang sudah anda ambil");
                return;
            }
            // Query untuk memasukkan data
            String query = "INSERT INTO tbantar (id_barang, id_user, id_pesanan) VALUES (?, ?, ?)";

            // Menyiapkan statement SQL
            statement = connection.prepareStatement(query);

            // Mengisi nilai parameter dengan data dari objek newAntar
            statement.setInt(1, id_produk);
            statement.setInt(2, id_pelanggan);
            statement.setInt(3, id_pesanan);
            
            // Menjalankan pernyataan SQL
            statement.executeUpdate();

            // Menambahkan data baru ke dalam ArrayList jika berhasil
            //dataAntar.add(newAntar);
            System.out.println("Data berhasil ditambahkan ke dalam database.");

        } finally {
            // Menutup statement dan koneksi
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
    }
    public static void hapusAntar(int id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Menghubungkan ke database
            connection = DatabaseConnection.getConnection();

            // Query untuk menghapus data berdasarkan ID
            String query = "DELETE FROM tbantar WHERE id_pesanan = ?";

            // Menyiapkan statement SQL
            statement = connection.prepareStatement(query);

            // Mengisi nilai parameter dengan ID yang akan dihapus
            statement.setInt(1, id);

            // Menjalankan pernyataan SQL
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                // ubah status pesanan yang telah diantar
                String query2 = "UPDATE tbpesanan SET status = ? WHERE idPesanan = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, "Pesanan telah sampai");
                statement2.setInt(2, id);
                statement2.executeUpdate();
                // Hapus juga data dari ArrayList jika berhasil dihapus dari database
                for (antar a : dataAntar) {
                    if (a.getIdPesanan() == id) {
                        dataAntar.remove(a);
                        break;
                    }
                }
                System.out.println("Data berhasil dihapus dari database.");
            } else {
                System.out.println("Data dengan ID tersebut tidak ditemukan.");
            }
        } finally {
            // Menutup statement dan koneksi
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static void perbaruiArrayAntar() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            // Menghubungkan ke database
            connection = DatabaseConnection.getConnection();
    
            // Query untuk mengambil semua data antar
            String query = "SELECT * FROM tbantar";
    
            // Menyiapkan statement SQL
            statement = connection.prepareStatement(query);
    
            // Menjalankan pernyataan SQL dan mendapatkan hasilnya
            resultSet = statement.executeQuery();
    
            // Mengosongkan dataAntar sebelum memasukkan data baru
            dataAntar.clear();
    
            // Memproses hasil query dan memasukkan data ke dalam ArrayList
            while (resultSet.next()) {
                int id_antar = resultSet.getInt("id_antar");
                int id_barang = resultSet.getInt("id_barang");
                int id_user = resultSet.getInt("id_user");
                int id_pesanan = resultSet.getInt("id_pesanan");
    
                antar a = new antar(id_antar, id_barang, id_user, id_pesanan);
                dataAntar.add(a);
            }
    
            System.out.println("Data Antar berhasil diperbarui dari database.");
        } finally {
            // Menutup statement, koneksi, dan result set
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public static ArrayList<antar> getDataAntar() {
        return dataAntar;
    }

    public static void tampilkanAntar() {
        ArrayList<antar> dataAntar = getDataAntar();
        System.out.println("Data Antar:");
        System.out.println("--------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-10s | %-10s |\n", "Index", "ID Antar", "ID Barang", "ID Pesanan");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < dataAntar.size(); i++) {
            antar a = dataAntar.get(i);
            System.out.printf("| %-5d | %-10d | %-10d | %-10d |\n", i+1, a.getIdAntar(), a.getIdProduks(), a.getIdPesanan());
        }
        System.out.println("--------------------------------------------------");
    }
    
}
