package User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DatabaseConnection;

public class userControl {
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

    // public static String hashPass(String password) {
    //     try {
    //         // Buat instance MessageDigest dengan algoritma SHA-256
    //         MessageDigest digest = MessageDigest.getInstance("SHA-256");
    //         // Ubah password menjadi array byte
    //         byte[] encodedhash = digest.digest(password.getBytes());
    //         // Ubah array byte menjadi representasi hex
    //         StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
    //         for (byte b : encodedhash) {
    //             String hex = Integer.toHexString(0xff & b);
    //             if (hex.length() == 1) {
    //                 hexString.append('0');
    //             }
    //             hexString.append(hex);
    //         }
    //         System.out.println(hexString.toString());
    //         return hexString.toString();
    //     } catch (NoSuchAlgorithmException e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    public static boolean checkUsernameExists(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean usernameExists = false;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbuser WHERE username = ?";
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
        String sql = "SELECT id, nama, password, alamat, telp, email, role FROM tbuser WHERE username = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nama = resultSet.getString("nama");
            String storedPasswordHash = resultSet.getString("password"); // Mengambil password yang sudah di-hash
            String alamatPengiriman = resultSet.getString("alamat");
            int telp = resultSet.getInt("telp");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");
            
            // Bandingkan password yang dimasukkan oleh pengguna dengan password yang disimpan di database
            // tanpa melakukan hashing lagi
            if (password.equals(storedPasswordHash)) {
                // Membuat objek user berdasarkan role
                if ("customer".equalsIgnoreCase(role)) {
                    return new customer(id, nama, username, storedPasswordHash, email, telp, alamatPengiriman, role);
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
    

}
