package app;

//import com.sun.tools.javac.util.Convert;
import org.firebirdsql.gds.impl.ServiceRequestBufferImp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class DB {



    public static ArrayList<sekcii> select(String number, String datefrom, String dateto){

        ArrayList<sekcii> sport = new ArrayList<sekcii>();

        Properties properties=new Properties();

        try {
            String path=System.getProperty("user.dir");
            FileInputStream  fis = new FileInputStream(path+"\\config.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String login=properties.getProperty("db.login");
        String password=properties.getProperty("db.password");
        String way=properties.getProperty("db.host");

        String strSQL="";
        String strURL="jdbc:firebirdsql:localhost:"+way+"?encoding=utf8";
        if(!number.isEmpty()) {
            if(!datefrom.isEmpty() && !dateto.isEmpty()){
                    strSQL = String.format("SELECT DOCNUMBER, DOCCHANGEDATE, CREDIT, NOTE FROM FACIALFINCAPTION WHERE DOCNUMBER='%1$s' AND DOCCHANGEDATE BETWEEN '%2$s' AND '%3$s'", number, datefrom, dateto);
            }else{
                strSQL = String.format("SELECT DOCNUMBER, DOCCHANGEDATE, CREDIT, NOTE FROM FACIALFINCAPTION WHERE DOCNUMBER='%1$s'", number);
            }
        }else {
            if(!datefrom.isEmpty() && !dateto.isEmpty()){
                strSQL = String.format("SELECT DOCNUMBER, DOCCHANGEDATE, CREDIT, NOTE FROM FACIALFINCAPTION WHERE DOCCHANGEDATE BETWEEN '%1$s' AND '%2$s'", datefrom, dateto);
            }
        }

        try{

            try (Connection conn = DriverManager.getConnection(strURL, login, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(strSQL);
                while(resultSet.next()){

                   // int Id=resultSet.getInt(1);
                    String Number =resultSet.getString(1);
                    String Date = resultSet.getString(2);
                    String Sum = resultSet.getString(3);
                    String Note = resultSet.getString(4);

                    sekcii Sekcii = new sekcii(Number, Date, Sum, Note);
                    sport.add(Sekcii);
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return sport;
    }
}
