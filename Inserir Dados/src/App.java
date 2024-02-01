import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import DB.DB;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement st = null;
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DB.getconnection();

            st = conn.prepareStatement("INSERT INTO department "
                    + "(Name Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES"
                    + "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "José Ferreira Rodrigues");
            st.setString(2, "jose@gmail.com");
            st.setDate(3, new java.sql.Date(sd.parse("04/03/2002").getTime()));
            st.setDouble(4, 1400d);
            st.setInt(5, 2);

            int linhasAfetadas = st.executeUpdate();
            if(linhasAfetadas>0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Sucesso! Id: " + id);
                }
            }else{
                System.out.println("Sem linhas afetadas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatemet(st);
            DB.closeConnection();
        }
    }
}
