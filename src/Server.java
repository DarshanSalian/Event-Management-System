import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Server{


    @FXML
    private Stage stage;
    ActionEvent event;

    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter prnt;
    OutputStream output;
    ObjectOutputStream obwrite;
    Connection conn;
    ResultSet rslt;
    PreparedStatement prst;

    public Server(){
       
            try {
                server=new ServerSocket(8000);
                System.out.println("server is listening");
                while (true) {
                    socket=server.accept();
                    br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    prnt=new PrintWriter(socket.getOutputStream());
                    output=socket.getOutputStream();
                    obwrite=new ObjectOutputStream(output);
                    String ss=br.readLine();
                    Multiclient mltclient=new Multiclient(ss);
                    new Thread(mltclient).start();
                    
                }   
            } catch (Exception e) {
                // TODO: handle exception
            }
       
    }

    public class Multiclient implements Runnable {
        String usnn;
        String passs;
        public Multiclient(String ss){
            String[] det=ss.split(" ");
            usnn=det[0];
            passs=det[1];
        }

        @Override
        public void run() {
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Serverside", "root", "12@#HardhiK");
                prst=conn.prepareStatement("SELECT username,password FROM Login WHERE username=? AND password=?");
                prst.setString(1, usnn);
                prst.setString(2, passs);

                rslt=prst.executeQuery();

                if (rslt.next()) {
                    try {
                        System.out.println("welcome");
                        InetSocketAddress connectedsocket=(InetSocketAddress) socket.getRemoteSocketAddress();
                        String clientip=connectedsocket.getAddress().getHostAddress();
                        obwrite.writeObject("success");
                        obwrite.writeObject(clientip);
                        
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();

                        
                    }  
                
                } else {
                   try {
                     obwrite.writeObject("unsuccess");
                     obwrite.writeObject("unsuccess");
                   } catch (Exception e) {
                    // TODO: handle exception
                   }
                }

                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    
            }
        
        }
    
        
    }

    /**
     * Innerserver4nm21is034
     */
    // public class Innerserver {

    //     public void nextScene(){
    //         Platform.runLater(()->{
    //             try {
    //                 FXMLLoader loader=new FXMLLoader(getClass().getResource("succes.fxml"));
    //                 Parent root=loader.load();
    //                 //stage=(Stage)((Node).event).getScene().getWindow();
    //                 Scene scene=new Scene(root);
    //                 stage.setScene(scene);
    //                 stage.show();
    //                 //System.out.println(clientip);
    //             } catch (Exception e) {
    //                 // TODO: handle exception
    //             }

    //         });
    //     }

    //     public static void main(String[] args) {
            
    //     }

    // }


    public static void main(String[] args) {
        Server so=new Server();
        
    }


  


}
