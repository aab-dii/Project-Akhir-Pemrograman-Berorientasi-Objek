package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.DatabaseConnection;

public class userControl {
    private static ArrayList<kurir> dataKurir = new ArrayList<>();

    public static void registerCustomer(customer newCustomer) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO tbuser (username, password, role) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newCustomer.getUsername());
            statement.setString(2, newCustomer.getPassword());
            statement.setString(3, newCustomer.getRole());
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

    public static boolean checkDataExists(String kolom, String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean usernameExists = false;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbuser WHERE "+ kolom+ "= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            // Jika ada baris hasil, berarti username sudah ada
            usernameExists = resultSet.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Gagal memeriksa username.");
        } finally {
            // Pastikan untuk menutup statement, result set, dan koneksi
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return usernameExists;
    }

    

    public static user checkCredentials(String username, String password)
        throws SQLException, ClassNotFoundException {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM tbuser WHERE username = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nama = resultSet.getString("nama");
            String storedPasswordHash = resultSet.getString("password"); // Mengambil password yang sudah di-hash
            String alamatPengiriman = resultSet.getString("alamat");
            String telp = resultSet.getString("telp");
            String email = resultSet.getString("email");
            int saldo = resultSet.getInt("saldo");
            String role = resultSet.getString("role");
            
            // Bandingkan password yang dimasukkan oleh pengguna dengan password yang disimpan di database
            // tanpa melakukan hashing lagi
            if (password.equals(storedPasswordHash)) {
                // Membuat objek user berdasarkan role
                if ("customer".equalsIgnoreCase(role)) {
                    return new customer(id, nama, username, storedPasswordHash, email, telp, alamatPengiriman, saldo, role);
                } else if ("admin".equalsIgnoreCase(role)) {
                    return new admin(id, nama, username, storedPasswordHash, email, telp, role);
                } else if ("kurir".equalsIgnoreCase(role)) {
                    return new kurir(id, nama, username, storedPasswordHash, email, telp, role);
                } else {
                    return null;
                }
            } else {
                return null; // Password tidak cocok
            }
        } else {
            return null; // Jika pengguna tidak ditemukan
        }
    } finally {
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

    public static void updateCustomer(int id, String kolom, String nilaiBaru) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
    
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE tbuser SET " + kolom + " = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, nilaiBaru);
            statement.setInt(2, id);
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

    public static void updateSaldo(int id, int saldo, boolean topUp) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            connection = DatabaseConnection.getConnection();
            
            // Mengambil saldo saat ini
            String sql = "SELECT saldo FROM tbuser WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
    
            int newSaldo = 0; // Inisialisasi newSaldo
            if (resultSet.next()) {
                int currentSaldo = resultSet.getInt("saldo");
                if (topUp) {
                    newSaldo = currentSaldo + saldo;
                } else {
                    newSaldo = saldo;
                }
            } else {
                System.out.println("User dengan ID " + id + " tidak ditemukan.");
                return; // Keluar dari metode jika user tidak ditemukan
            }
    
            // Tutup statement sebelumnya sebelum membuat yang baru
            statement.close();
    
            // Memperbarui saldo
            String sql2 = "UPDATE tbuser SET saldo = ? WHERE id = ?";
            statement = connection.prepareStatement(sql2);
            statement.setInt(1, newSaldo);
            statement.setInt(2, id);
            statement.executeUpdate();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    
    public static void registerKurir(kurir newkurir)throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO tbuser (nama, username, password, email, telp, role) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, newkurir.getNama());
            statement.setString(2, newkurir.getUsername());
            statement.setString(3, newkurir.getPassword());
            statement.setString(4, newkurir.getEmail());
            statement.setString(5, newkurir.getTelp());
            statement.setString(6, newkurir.getRole());
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

    public static void lihatKurir() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        dataKurir.clear();

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbuser WHERE role = 'kurir'";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String telp = resultSet.getString("telp");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                
                kurir newKurir = new kurir(id, nama,username,password, email,telp,"kurir");
                dataKurir.add(newKurir);

                int no = 1;
                System.out.println("No      : " + no);
                System.out.println("Nama    : " + nama);
                System.out.println("Telepon : " + telp);
                System.out.println("Email   : " + email);
                System.out.println("-------------------------------");
                no++;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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

    public static void hapusKurir(int id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
    
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM tbuser WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Kurir berhasil dihapus.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menghapus kurir dari database.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public static ArrayList<kurir> getDataKurir() {
        return dataKurir;
    }

}
