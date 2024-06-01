package Antar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.DatabaseConnection;

public class antarControl {
    private static ArrayList<antar> dataAntar = new ArrayList<antar>();

    public static void tambahAntar(antar antar) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement updateStatement = null;
    
        try {
            // Menghubungkan ke database
            connection = DatabaseConnection.getConnection();
    
            // Query untuk memasukkan data ke dalam tbantar
            String query = "INSERT INTO tbantar (id_barang, id_user, id_pesanan, id_kurir) VALUES (?, ?, ?, ?)";
    
            // Menyiapkan statement SQL untuk memasukkan data
            statement = connection.prepareStatement(query);
            statement.setInt(1, antar.getIdProduks());
            statement.setInt(2, antar.getIdPelanggan());
            statement.setInt(3, antar.getIdPesanan());
            statement.setInt(4, antar.getIdKurir());
            
            // Menjalankan pernyataan SQL untuk memasukkan data
            statement.executeUpdate();
    
            // Query untuk mengubah status pada tbpesanan
            String updateQuery = "UPDATE tbpesanan SET status = 'Sedang Diantar Kurir' WHERE idPesanan = ?";
    
            // Menyiapkan statement SQL untuk memperbarui status
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, antar.getIdPesanan());
            
            // Menjalankan pernyataan SQL untuk memperbarui status
            updateStatement.executeUpdate();
    
            System.out.println("Data berhasil ditambahkan ke dalam database dan status pesanan diperbarui.");
    
        } finally {
            // Menutup statement dan koneksi
            if (statement != null) {
                statement.close();
            }
            if (updateStatement != null) {
                updateStatement.close();
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
    public static void perbaruiArrayAntar(int idKurir) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            // Menghubungkan ke database
            connection = DatabaseConnection.getConnection();
    
            // Query untuk mengambil semua data antar
            String query = "SELECT * FROM tbantar WHERE id_kurir = ?";
    
            // Menyiapkan statement SQL
            statement = connection.prepareStatement(query);
            statement.setInt(1, idKurir);
    
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
                int id_kurir = resultSet.getInt("id_kurir");
    
                antar a = new antar(id_antar, id_barang, id_user, id_pesanan, id_kurir);
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

    public static void tampilkanAntar() throws SQLException, ClassNotFoundException {
        ArrayList<antar> dataAntar = getDataAntar();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        System.out.println("Data Antar:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-10s | %-20s | %-30s | %-15s |\n", "Index", "Nama Produk", "Jumlah", "Nama Customer", "Alamat", "Telepon");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    
        try {
            connection = DatabaseConnection.getConnection();
            for (int i = 0; i < dataAntar.size(); i++) {
                antar a = dataAntar.get(i);
    
                // Mengambil data dari tbuser
                String userSql = "SELECT nama, alamat, telp FROM tbuser WHERE id = ?";
                statement = connection.prepareStatement(userSql);
                statement.setInt(1, a.getIdPelanggan());
                resultSet = statement.executeQuery();
    
                String namaUser = "", alamat = "", telp = "";
                if (resultSet.next()) {
                    namaUser = resultSet.getString("nama");
                    alamat = resultSet.getString("alamat");
                    telp = resultSet.getString("telp");
                }
    
                // Mengambil data dari tbproduk
                String produkSql = "SELECT nama FROM tbproduk WHERE id = ?";
                statement = connection.prepareStatement(produkSql);
                statement.setInt(1, a.getIdProduks());
                resultSet = statement.executeQuery();
    
                String namaProduk = "";
                if (resultSet.next()) {
                    namaProduk = resultSet.getString("nama");
                }
    
                // Mengambil data dari tbpesanan
                String pesananSql = "SELECT jumlah FROM tbpesanan WHERE idPesanan = ?";
                statement = connection.prepareStatement(pesananSql);
                statement.setInt(1, a.getIdPesanan());
                resultSet = statement.executeQuery();
    
                int jumlah = 0;
                if (resultSet.next()) {
                    jumlah = resultSet.getInt("jumlah");
                }
    
                System.out.printf("| %-5d | %-20s | %-10d | %-20s | %-30s | %-15s |\n", i + 1, namaProduk, jumlah, namaUser, alamat, telp);
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    }
    
}
