import java.sql.Connection;
import java.sql.PreparedStatement;

import DB.DB;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getconnection();
            st = conn.prepareStatement("UPDATE seller "
                    + "SET Name = BaseSalary + ? "
                    + "WHERE "
                    + "(DepartmentId = ?)");

            st.setDouble(1, 200.0);
            st.setInt(2, 2);
            int linhasAfetadas = st.executeUpdate();
            System.out.println("Sucesso! Linhas afetadas: " + linhasAfetadas);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DB.closeStatemet(st);
            DB.closeConnection();
        }
    }
}
