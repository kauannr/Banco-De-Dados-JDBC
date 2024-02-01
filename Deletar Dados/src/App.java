import java.sql.Connection;
import java.sql.PreparedStatement;

import DB.DbIntegrityException;
import DB.DB;


public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getconnection();
            st = conn.prepareStatement("DELETE FROM department "
                    + "WHERE "
                    + "Id = ?");
            st.setInt(1, 2);

            int linhasAfetadas = st.executeUpdate();
            System.out.println("Sucesso! linhas afetadas: " + linhasAfetadas);

        } catch (Exception e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatemet(st);
            DB.closeConnection();
        }
    }
}
