package util;

import objects.Product;

import java.sql.*;

public class DBConnector {
    private String insert = "INSERT INTO LittleTask.Products (name, model, currentPrice, oldPrice, url) "
            + "values(?,?,?,?,?)";
    private Connection conn = null;

    /**
     * Initialises database connection
     * @param connString Database URL and port
     * @param user Database username
     * @param password Database password
     */
    public DBConnector(String connString, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connString, user, password);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Stores a product in the database
     * @param p Product to add to database
     * @return  boolean whether insertion was successful
     */
    public boolean insertProduct(Product p) {
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getModel());
            ps.setBigDecimal(3, p.getcPrice());
            ps.setBigDecimal(4, p.getoPrice());
            ps.setString(5, p.getHref());

            ps.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}