package Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DatabaseConnection;

public class productControl {

    public static void tambahProduk(product newProduct) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO tbproduk (nama, stok, harga, deskripsi, merk, jenis) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newProduct.getNama());
            statement.setInt(2, newProduct.getStok());
            statement.setInt(3, newProduct.getHarga());
            statement.setString(4, newProduct.getDeskripsi());
            statement.setString(5, newProduct.getMerk());
            statement.setString(6, newProduct.getJenis());
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

    // public void ambilProduk() {
    //     Connection connection = null;
    //     PreparedStatement statement = null;
    //     ResultSet resultSet = null;

    //     try {
    //         connection = DatabaseConnection.getConnection();
    //         String sql = "SELECT * FROM tbproduk";
    //         statement = connection.prepareStatement(sql);

    //         resultSet = statement.executeQuery();

    //         // Process the ResultSet, e.g., iterate over the rows and display each product
    //         while (resultSet.next()) {
    //             int id = resultSet.getInt("id");
    //             String nama = resultSet.getString("nama");
    //             int stok = resultSet.getInt("stok");
    //             int harga = resultSet.getInt("harga");
    //             String deskripsi = resultSet.getString("deskripsi");
    //             String merk = resultSet.getString("merk");
    //             String jenis = resultSet.getString("jenis");

    //             if(jenis.equals("perkakas")) {
    //             }
    //         }
    //     } catch (SQLException | ClassNotFoundException e) {
    //         e.printStackTrace();
    //         System.out.println("Terjadi kesalahan saat mengambil produk dari database.");
    //     } finally {
    //         // Close the resources
    //         try {
    //             if (resultSet != null) {
    //                 resultSet.close();
    //             }
    //             if (statement != null) {
    //                 statement.close();
    //             }
    //             if (connection != null) {
    //                 connection.close();
    //             }
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //             System.out.println("Terjadi kesalahan saat menutup koneksi ke database.");
    //         }
    //     }
    // }

}
