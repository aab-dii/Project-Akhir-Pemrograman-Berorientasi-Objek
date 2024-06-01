package Pesanan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.DatabaseConnection;
import Keranjang.keranjang;
import User.customer;

public class pesananControl {
    private static ArrayList<pesanan> dataPesanan = new ArrayList<>();

    public static void tambahPesanan(pesanan newPesanan) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO tbpesanan (idCust, idProduk, jumlah, status) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, newPesanan.getIdCust());
            statement.setInt(2, newPesanan.getIdProduk());
            statement.setInt(3, newPesanan.getJumlah());
            statement.setString(4, newPesanan.getStatus());

            statement.executeUpdate();

            // Tambahkan juga ke ArrayList
            dataPesanan.add(newPesanan);

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void lihatPesanan(String statuss) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        dataPesanan.clear();

        try {
            connection = DatabaseConnection.getConnection();
            // Query SQL dengan JOIN antara tbpesanan, tbuser, dan tbproduk dengan alias
            // untuk kolom 'nama'
            String sql = "SELECT p.*, u.nama AS namaPemesan, u.alamat, u.telp, pr.nama AS namaProduk, pr.harga FROM tbpesanan p "
                    +
                    "JOIN tbuser u ON p.idCust = u.id " +
                    "JOIN tbproduk pr ON p.idProduk = pr.id " +
                    "WHERE p.status = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, statuss);
            resultSet = statement.executeQuery();
            int no = 0;
            dataPesanan.clear();

            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %-5s | %-20s | %-10s | %-30s | %-20s | %-20s | %-15s | %-10s |\n",
                    "No", "Nama Barang", "Jumlah", "Status", "Nama Pemesan", "Alamat", "Telepon", "Harga");
                    System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                
            while (resultSet.next()) {
                int idPesanan = resultSet.getInt("idPesanan");
                int idCust = resultSet.getInt("idCust");
                int idProdukK = resultSet.getInt("idProduk");
                int jumlah = resultSet.getInt("jumlah");
                String status = resultSet.getString("status");

                // Informasi tambahan dari tbuser dan tbproduk
                String namaPemesan = resultSet.getString("namaPemesan");
                String alamatPemesan = resultSet.getString("alamat");
                String noTeleponPemesan = resultSet.getString("telp");
                String namaProduk = resultSet.getString("namaProduk");
                int harga = resultSet.getInt("harga");

                pesanan newPesanan = new pesanan(idPesanan, idCust, idProdukK, jumlah, status);
                dataPesanan.add(newPesanan);

                no++;
                System.out.printf("| %-5d | %-20s | %-10d | %-30s | %-20s | %-20s | %-15s | %-10d |\n",
                        no, namaProduk, jumlah, status, namaPemesan, alamatPemesan, noTeleponPemesan, harga * jumlah);
                System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void lihatPesananCust(int id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        dataPesanan.clear();
    
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbpesanan WHERE idCust = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            int no = 0;
    
            // Header tabel
            System.out.println("----------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-20s |\n",
                    "No", "Nama Barang", "Jumlah", "Harga", "Status");
            System.out.println("----------------------------------------------------------------------------------");
    
            while (resultSet.next()) {
                int idPesanan = resultSet.getInt("idPesanan");
                int idCust = resultSet.getInt("idCust");
                int idProdukK = resultSet.getInt("idProduk");
                int jumlah = resultSet.getInt("jumlah");
                String status = resultSet.getString("status");
    
                pesanan newPesanan = new pesanan(idPesanan, idCust, idProdukK, jumlah, status);
                dataPesanan.add(newPesanan);
    
                String sql2 = "SELECT nama, harga FROM tbproduk WHERE id = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setInt(1, idProdukK);
                ResultSet resultSet2 = statement2.executeQuery();
    
                no++;
                if (resultSet2.next()) {
                    String nama = resultSet2.getString("nama");
                    int harga = resultSet2.getInt("harga");
                    System.out.printf("| %-5d | %-20s | %-10d | %-10d | %-20s |\n",
                            no, nama, jumlah, harga * jumlah, status);
                    System.out.println("----------------------------------------------------------------------------------");
                }
                resultSet2.close();
                statement2.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static ArrayList<pesanan> getDataPesanan() {
        return dataPesanan;
    }

    public static void updatePesanan(pesanan newPesanan) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE tbpesanan SET status = ? WHERE idPesanan = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newPesanan.getStatus());
            statement.setInt(2, newPesanan.getIdPesanan());

            // Jalankan pernyataan SQL
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
