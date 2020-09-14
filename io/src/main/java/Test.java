import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test{

    private static String ClassName = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static String SeverName = "jdbc:odbc:myData";
    private static String UserName = "root";
    private static String PassWord = "1234";
    private static Statement stm;

    public static void main(String[] a){

        try {

            Class.forName(ClassName);

            Connection sqlConn = DriverManager.getConnection(SeverName,UserName, PassWord);
            stm = sqlConn.createStatement();

            ResultSet r = stm.executeQuery("select * from goods where price>300000");

            while (r.next()){
                System.out.print("number:"+r.getString("number"));
                System.out.print("name:"+r.getString("name"));
                System.out.print("madeTime:"+r.getTimestamp("madeTime"));
                System.out.print("price:"+r.getDouble("price"));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
