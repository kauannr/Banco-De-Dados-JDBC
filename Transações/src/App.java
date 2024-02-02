import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DB;
import DB.DbException;
import DB.DbIntegrityException;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DB.getconnection();
            st = conn.createStatement();
             conn.setAutoCommit(false);

            int linhas1 = st.executeUpdate("UPDATE seller SET BaseSalary = 1000 WHERE DepartmentId = 1");
            int linhas2 = st.executeUpdate("UPDATE seller SET BaseSalary = 2000 WHERE DepartmentId = 2");

            System.out.println("Linhas1: " + linhas1);
            System.out.println("Linhas2: " + linhas2);

            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
                throw new DbException("Transação Cancelada. Motivo: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Erro no rollback. Motivo: " + e1.getMessage());
            }
        } finally {
            DB.closeStatemet(st);
            DB.closeConnection();
        }
    }
}
