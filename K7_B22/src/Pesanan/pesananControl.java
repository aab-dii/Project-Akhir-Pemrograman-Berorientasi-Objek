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

    public static void lihatPesananKonfir() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int total = 0;
        dataPesanan.clear();

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbpesanan WHERE status = 'Menunggu Konfirmasi'";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int no = 0;
            dataPesanan.clear();

            while (resultSet.next()) {
                int idPesanan = resultSet.getInt("idPesanan");
                int idCust = resultSet.getInt("idCust");
                int idProdukK = resultSet.getInt("idProduk");
                int jumlah = resultSet.getInt("jumlah");
                String status = resultSet.getString("status");

                pesanan newPesanan = new pesanan(idPesanan,idCust,idProdukK,jumlah,status);
                dataPesanan.add(newPesanan);

                String sql2 = "SELECT nama,harga FROM tbproduk WHERE id = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setInt(1, idProdukK);
                ResultSet resultSet2 = statement2.executeQuery();

                no++;
                if (resultSet2.next()) {
                    String nama = resultSet2.getString("nama");
                    int harga = resultSet2.getInt("harga");
                    System.out.println(no + "  Nama Barang: " + nama);
                    System.out.println("   Jumlah barang: " + jumlah);
                    System.out.println("   Status: " + status);
                    System.out.println("------------------------------------");
                    total += jumlah*harga;
                }
                resultSet2.close();
                statement2.close();
            }
            System.out.println("Total Harga : Rp. " + total);
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

    public static void lihatPesananProses() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int total = 0;
        dataPesanan.clear();

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbpesanan WHERE status = 'Pesanan Diproses'";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int no = 0;
            dataPesanan.clear();

            while (resultSet.next()) {
                int idPesanan = resultSet.getInt("idPesanan");
                int idCust = resultSet.getInt("idCust");
                int idProdukK = resultSet.getInt("idProduk");
                int jumlah = resultSet.getInt("jumlah");
                String status = resultSet.getString("status");

                pesanan newPesanan = new pesanan(idPesanan,idCust,idProdukK,jumlah,status);
                dataPesanan.add(newPesanan);

                String sql2 = "SELECT nama,harga FROM tbproduk WHERE id = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setInt(1, idProdukK);
                ResultSet resultSet2 = statement2.executeQuery();

                no++;
                if (resultSet2.next()) {
                    String nama = resultSet2.getString("nama");
                    int harga = resultSet2.getInt("harga");
                    System.out.println(no + "  Nama Barang: " + nama);
                    System.out.println("   Jumlah barang: " + jumlah);
                    System.out.println("   Status: " + status);
                    System.out.println("------------------------------------");
                    total += jumlah*harga;
                }
                resultSet2.close();
                statement2.close();
            }
            System.out.println("Total Harga : Rp. " + total);
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

    public static void lihatPesananCust(int id)throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int total = 0;
        dataPesanan.clear();

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbpesanan WHERE idCust = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            int no = 0;
            
            while (resultSet.next()) {
                int idPesanan = resultSet.getInt("idPesanan");
                int idCust = resultSet.getInt("idCust");
                int idProdukK = resultSet.getInt("idProduk");
                int jumlah = resultSet.getInt("jumlah");
                String status = resultSet.getString("status");

                pesanan newPesanan = new pesanan(idPesanan,idCust,idProdukK,jumlah,status);
                dataPesanan.add(newPesanan);

                String sql2 = "SELECT nama,harga FROM tbproduk WHERE id = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setInt(1, idProdukK);
                ResultSet resultSet2 = statement2.executeQuery();

                no++;
                if (resultSet2.next()) {
                    String nama = resultSet2.getString("nama");
                    int harga = resultSet2.getInt("harga");
                    System.out.println(no + "  Nama Barang: " + nama);
                    System.out.println("   Jumlah barang: " + jumlah);
                    System.out.println("   Status: " + status);
                    System.out.println("------------------------------------");
                    total += jumlah*harga;
                }
                resultSet2.close();
                statement2.close();
            }
            System.out.println("Total Harga : Rp. " + total);
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

    public static void lihatPesananDikirim() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int total = 0;
        dataPesanan.clear();

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbpesanan WHERE status = 'Pesanan Sedang Dikirim'";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int no = 0;
            dataPesanan.clear();

            while (resultSet.next()) {
                int idPesanan = resultSet.getInt("idPesanan");
                int idCust = resultSet.getInt("idCust");
                int idProdukK = resultSet.getInt("idProduk");
                int jumlah = resultSet.getInt("jumlah");
                String status = resultSet.getString("status");

                pesanan newPesanan = new pesanan(idPesanan,idCust,idProdukK,jumlah,status);
                dataPesanan.add(newPesanan);

                String sql2 = "SELECT nama,harga FROM tbproduk WHERE id = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setInt(1, idProdukK);
                ResultSet resultSet2 = statement2.executeQuery();

                no++;
                if (resultSet2.next()) {
                    String nama = resultSet2.getString("nama");
                    int harga = resultSet2.getInt("harga");
                    System.out.println(no + "  Nama Barang: " + nama);
                    System.out.println("   Jumlah barang: " + jumlah);
                    System.out.println("   Status: " + status);
                    System.out.println("------------------------------------");
                    total += jumlah*harga;
                }
                resultSet2.close();
                statement2.close();
            }
            System.out.println("Total Harga : Rp. " + total);
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

    
    public static ArrayList<pesanan> getDataPesanan(){
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
