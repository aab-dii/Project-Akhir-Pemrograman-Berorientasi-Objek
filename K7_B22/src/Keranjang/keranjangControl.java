package Keranjang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import Connection.DatabaseConnection;
import User.customer;

public class keranjangControl {
    private static ArrayList<keranjang> dataKeranjang = new ArrayList<>();

    public static void tambahKeranjang(keranjang newKeranjang) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO tbkeranjang (idCust, idProduk, jumlah) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, newKeranjang.getCustId());
            statement.setInt(2, newKeranjang.getIdProduk());
            statement.setInt(3, newKeranjang.getJumlah());

            statement.executeUpdate();

            // Tambahkan juga ke ArrayList
            dataKeranjang.add(newKeranjang);

            System.out.println("Produk berhasil ditambahkan ke keranjang.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void lihatKeranjang(customer customer) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int total = 0;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbkeranjang WHERE idCust = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            resultSet = statement.executeQuery();
            int no = 0;

            while (resultSet.next()) {
                int idProdukK = resultSet.getInt("idProduk");
                int jumlah = resultSet.getInt("jumlah");

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

    public static void checkOut(){
        System.out.println("================================");
        System.out.println("|       Struk Pembayaran       |");
        System.out.println("================================");
        System.out.println("================================");
    }
}
