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
            String sql = "INSERT INTO tbproduk (nama, stok, harga, deskripsi, merk, jenis, bahan, ukuran, tipe, model, warna) VALUES (? , ?, ?, ?, ?, ?, ?, ?, ?, ?,? )";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newProduct.getNama());
            statement.setInt(2, newProduct.getStok());
            statement.setInt(3, newProduct.getHarga());
            statement.setString(4, newProduct.getDeskripsi());
            statement.setString(5, newProduct.getMerk());
            statement.setString(6, newProduct.getJenis());
            if (newProduct instanceof furniture) {
                furniture f = (furniture) newProduct;
                statement.setString(7, f.getBahan());
                statement.setString(8, f.getUkuran());
                statement.setString(9, null);
                statement.setString(10, null);
                statement.setString(11, null);
            } else if (newProduct instanceof rumahTangga) {
                rumahTangga r = (rumahTangga) newProduct;
                statement.setString(7, r.getBahan());
                statement.setString(8, r.getUkuran());
                statement.setString(9, null);
                statement.setString(10, null);
                statement.setString(11, null);
            } else if (newProduct instanceof elektronik) {
                elektronik e = (elektronik) newProduct;
                statement.setString(7, null);
                statement.setString(8, null);
                statement.setString(9, e.getTipe());
                statement.setString(10, e.getModel());
                statement.setString(11, e.getWarna());
            } else {
                statement.setString(7, null);
                statement.setString(8, null);
                statement.setString(9, null);
                statement.setString(10, null);
                statement.setString(11, null);
            }
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
                String bahan = resultSet.getString("bahan");
                String ukuran = resultSet.getString("ukuran");
                String jenis = resultSet.getString("jenis");

                switch (jenis.toLowerCase()) {
                    case "perkakas":
                        perkakas newPerkakas = new perkakas(id, nama, deskripsi, harga, stok, merk, jenis);
                        dataPerkakas.add(newPerkakas);
                        break;
                    case "furniture":
                        furniture newFurniture = new furniture(id, nama, deskripsi, harga, stok, merk, bahan, ukuran, jenis);
                        dataFurniture.add(newFurniture);
                        break;
                    case "elektronik":
                        String tipe = resultSet.getString("tipe");
                        String model = resultSet.getString("model");
                        String warna = resultSet.getString("warna");
                        elektronik newElektronik = new elektronik(id, nama, deskripsi, harga, stok, merk, tipe, model, warna, jenis);
                        dataElektronik.add(newElektronik);
                        break;
                    case "rumahtangga":
                        // String bahan = resultSet.getString("bahan");
                        // String ukuran = resultSet.getString("ukuran");
                        rumahTangga newRumahTangga = new rumahTangga(id, nama, deskripsi, harga, stok, bahan, ukuran, merk, jenis);
                        datArt.add(newRumahTangga);
                        break;
                    default:
                        System.out.println("Jenis produk tidak dikenal: " + jenis);
                }
            }
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

    public static void lihatProduk(int numover) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbproduk WHERE stok > 0";
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
                String bahan = resultSet.getString("bahan");
                String ukuran = resultSet.getString("ukuran");
                String jenis = resultSet.getString("jenis");

                switch (jenis.toLowerCase()) {
                    case "perkakas":
                        perkakas newPerkakas = new perkakas(id, nama, deskripsi, harga, stok, merk, jenis);
                        dataPerkakas.add(newPerkakas);
                        break;
                    case "furniture":
                        furniture newFurniture = new furniture(id, nama, deskripsi, harga, stok, merk, bahan, ukuran, jenis);
                        dataFurniture.add(newFurniture);
                        break;
                    case "elektronik":
                        String tipe = resultSet.getString("tipe");
                        String model = resultSet.getString("model");
                        String warna = resultSet.getString("warna");
                        elektronik newElektronik = new elektronik(id, nama, deskripsi, harga, stok, merk, tipe, model, warna, jenis);
                        dataElektronik.add(newElektronik);
                        break;
                    case "rumahtangga":
                        // String bahan = resultSet.getString("bahan");
                        // String ukuran = resultSet.getString("ukuran");
                        rumahTangga newRumahTangga = new rumahTangga(id, nama, deskripsi, harga, stok, bahan, ukuran, merk, jenis);
                        datArt.add(newRumahTangga);
                        break;
                    default:
                        System.out.println("Jenis produk tidak dikenal: " + jenis);
                }
            }
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

    public static void kurangStok(int idProduk, int jumlah) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            connection = DatabaseConnection.getConnection();
            
            // Mengambil stok saat ini
            String sql = "SELECT stok FROM tbproduk WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProduk);
            resultSet = statement.executeQuery();
    
            int newStok = 0; // Inisialisasi newStok
            if (resultSet.next()) {
                int stok = resultSet.getInt("stok");
                newStok = stok - jumlah;
            } else {
                System.out.println("Produk dengan ID " + idProduk + " tidak ditemukan.");
                return; // Keluar dari metode jika produk tidak ditemukan
            }
    
            // Tutup statement sebelumnya sebelum membuat yang baru
            statement.close();
    
            // Memperbarui stok
            String sql2 = "UPDATE tbproduk SET stok = ? WHERE id = ?";
            statement = connection.prepareStatement(sql2);
            statement.setInt(1, newStok);
            statement.setInt(2, idProduk);
            statement.executeUpdate();
    
            System.out.println("Stok berhasil diperbarui.");
            lihatProduk(); // Menampilkan produk untuk memastikan pembaruan stok
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    
}
    
