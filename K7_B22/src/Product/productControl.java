package Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.DatabaseConnection;

public class productControl {
    private static ArrayList<rumahTangga> datArt = new ArrayList<>();
    private static ArrayList<elektronik> dataElektronik = new ArrayList<>();
    private static ArrayList<furniture> dataFurniture = new ArrayList<>();
    private static ArrayList<perkakas> dataPerkakas = new ArrayList<>();

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
            lihatProduk();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void lihatProduk() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbproduk";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            datArt.clear();
            dataElektronik.clear();
            dataFurniture.clear();
            dataPerkakas.clear();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                int stok = resultSet.getInt("stok");
                int harga = resultSet.getInt("harga");
                String deskripsi = resultSet.getString("deskripsi");
                String merk = resultSet.getString("merk");
                String jenis = resultSet.getString("jenis");

                switch (jenis.toLowerCase()) {
                    case "perkakas":
                        perkakas newPerkakas = new perkakas(id, nama, deskripsi, harga, stok, merk, jenis);
                        dataPerkakas.add(newPerkakas);
                        break;
                    case "furniture":
                        furniture newFurniture = new furniture(id, nama, deskripsi, harga, stok, merk, jenis);
                        dataFurniture.add(newFurniture);
                        break;
                    case "elektronik":
                        // String tipe = resultSet.getString("tipe");
                        // String model = resultSet.getString("model");
                        // String warna = resultSet.getString("warna");
                        elektronik newElektronik = new elektronik(id, nama, deskripsi, harga, stok, merk, "", "model", "warna", jenis);
                        dataElektronik.add(newElektronik);
                        break;
                    case "rumahtangga":
                        // String bahan = resultSet.getString("bahan");
                        // String ukuran = resultSet.getString("ukuran");
                        rumahTangga newRumahTangga = new rumahTangga(id, nama, deskripsi, harga, stok, "", "", merk, jenis);
                        datArt.add(newRumahTangga);
                        break;
                    default:
                        System.out.println("Jenis produk tidak dikenal: " + jenis);
                }
            }
            System.out.println("Produk berhasil dimuat dari database.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat mengambil produk dari database.");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Terjadi kesalahan saat menutup koneksi ke database.");
            }
        }
    }
    public static ArrayList<rumahTangga> getDatArt() {
        return datArt;
    }
    public static ArrayList<elektronik> getDataElektronik() {
        return dataElektronik;
    }

    public static ArrayList<furniture> getDataFurniture() {
        return dataFurniture;
    }

    public static ArrayList<perkakas> getDataPerkakas() {
        return dataPerkakas;
    }

    public static void ubahProduk(product newProduct) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE tbproduk SET nama = ?, stok = ?, harga = ?, deskripsi = ?, merk = ?, jenis = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newProduct.getNama());
            statement.setInt(2, newProduct.getStok());
            statement.setInt(3, newProduct.getHarga());
            statement.setString(4, newProduct.getDeskripsi());
            statement.setString(5, newProduct.getMerk());
            statement.setString(6, newProduct.getJenis());
            statement.setInt(7, newProduct.getId());
            statement.executeUpdate();
            lihatProduk();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public static void hapusProduk(int id, String jenis) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
    
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM tbproduk WHERE id = ?";
            statement = connection.prepareStatement(sql);
            
            // Mengatur parameter ID
            statement.setInt(1, id);
            // statement.setString(2, jenis);
    
            // Mengeksekusi pernyataan SQL untuk menghapus produk
            statement.executeUpdate();
            lihatProduk();
            // Hapus objek dari ArrayList berdasarkan jenisnya
            // switch (jenis.toLowerCase()) {
            //     case "perkakas":
            //         for (perkakas perkakas : dataPerkakas) {
            //             if (perkakas.getId() == id) {
            //                 dataPerkakas.remove(perkakas);
            //                 break;
            //             }
            //         }
            //         break;
            //     case "furniture":
            //         for (furniture furniture : dataFurniture) {
            //             if (furniture.getId() == id) {
            //                 dataFurniture.remove(furniture);
            //                 break;
            //             }
            //         }
            //         break;
            //     case "elektronik":
            //         for (elektronik elektronik : dataElektronik) {
            //             if (elektronik.getId() == id) {
            //                 dataElektronik.remove(elektronik);
            //                 break;
            //             }
            //         }
            //         break;
            //     case "rumahtangga":
            //         for (rumahTangga rt : datArt) {
            //             if (rt.getId() == id) {
            //                 datArt.remove(rt);
            //                 break;
            //             }
            //         }
            //         break;
            //     default:
            //         System.out.println("Jenis produk tidak dikenal: " + jenis);
            // }
            System.out.println("Produk dengan ID " + id + " berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menghapus produk dari database.");
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
    
    

}