import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import DB.DB;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getconnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from department");

            while (rs.next()) {
                System.out.println(rs.getInt("ID") + "- " +
                        rs.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
            DB.closeResultSet(rs);
            DB.closeStatemet(st);
        }
    }
}
