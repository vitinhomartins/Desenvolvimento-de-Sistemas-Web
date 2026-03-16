package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class StatusConnection implements ConnectionCONTS{
    public static Connection conect() throws Exception {
        return DriverManager.getConnection(ConnectionCONTS.user, ConnectionCONTS.url, ConnectionCONTS.password);
    }
}
