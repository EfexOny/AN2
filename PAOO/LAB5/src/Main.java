import java.sql.*;
public class Main {
    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tiode.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE Rankings " +
                    "(May2019 INT PRIMARY KEY NOT NULL," +
                    " May2018 INT NOT NULL, " +
                    " Change REAL, " +
                    " PrgLang CHAR(50), " +
                    " Ratings REAL)";
            stmt.execute(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");

        c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tiode.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        exB();
        exC();
        exE();
    }

    public static void exB(){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tiode.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO Rankings (May2019, May2018, Change, PrgLang, Ratings) " +
                    "VALUES (1, 1, 0.7, 'Java', 16.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Rankings (May2019, May2018, Change, PrgLang, Ratings) " +
                    "VALUES (2, 2, 0.0, 'C', 14.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Rankings (May2019, May2018, Change, PrgLang, Ratings) " +
                    "VALUES (3, 3, 0.0, 'Python', 10.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Rankings (May2019, May2018, Change, PrgLang, Ratings) " +
                    "VALUES (4, 4, 0.0, 'C++', 8.00 );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created");

    }

    public static void exC(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tiode.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Rankings;" );
            while ( rs.next() ) {
                int may1 = rs.getInt("May2019");
                int may2 = rs.getInt("May2018");
                String change = rs.getString("Change");
                String prgLange = rs.getString("PrgLang");
                String Ratings = rs.getString("Ratings");
                System.out.println( "may2019 = " + may1 );
                System.out.println( "may2018 = " + may2 );
                System.out.println( "Change = " + change );
                System.out.println( "Progamming Language = " + prgLange );
                System.out.println( "Ratings = " + Ratings );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    public static void exE(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/efex/Facultate/AN2/PAOO/LAB5/tiode.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Rankings WHERE Change > 0;" );

            while ( rs.next() ) {
                String prgLange = rs.getString("PrgLang");
                System.out.println( "Progamming Language = " + prgLange );
                System.out.println();
            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}


