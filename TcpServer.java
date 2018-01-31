import java.io.*;
import java.net.*;
class TcpServer
{
  public static void main(String args[]) throws IOException
  {
    String fromclient,toclient;
    ServerSocket server= new ServerSocket(5000);
    System.out.println("TCPServer waiting for client on port 5000");
    while(true)
    {
      Socket connected=server.accept();
      System.out.println(" THE CLIENT "+ connected.getInetAddress() + ":" + connected.getPort() + " Is CONNECTED");
      BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
      BufferedReader inFromClient = new BufferedReader( new InputStreamReader(connected.getInputStream()));
      PrintWriter outToClient = new PrintWriter(connected.getOutputStream(),true);
      while(true)
      {
        System.out.println("SEND(Type Q or q to Quit):");
        toclient=inFromUser.readLine();
        if(toclient.equals("q")||toclient.equals("Q"))
        {
          outToClient.println(toclient);
          connected.close();
          break;
        }
        else
        {
          outToClient.println(toclient);
        }
        fromclient=inFromClient.readLine();
        if(fromclient.equals("q")|| fromclient.equals("Q"))
        {
          connected.close();
          break;
        }
        else
        {
          System.out.println("RECEIVED:"+fromclient);
        }
      }
    }
  }
}
