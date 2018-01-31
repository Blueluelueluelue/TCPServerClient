import java.io.*;
import java.net.*;

class Send extends Thread
{
  Socket clientSocket;

  public Send(Socket clientSocket1) {
    clientSocket = clientSocket1;
  }

  public void run() {
    try {
      String toServer;
      BufferedReader inFromUser= new BufferedReader(new InputStreamReader(System.in));
      PrintWriter outToServer= new PrintWriter(clientSocket.getOutputStream(),true);
      while (true) {
        System.out.println("SEND(Type Q or q to Quit):");
        toServer = inFromUser.readLine();
        if(toServer.equals("Q")||toServer.equals("q"))
        {
          outToServer.println(toServer);
          clientSocket.close();
          break;
        } else {
          outToServer.println(toServer);
        }
      }
    } catch(Exception e){}
  }
}


class Receive extends Thread
{
  Socket clientSocket;
  public Receive(Socket clientSocket1) {
    clientSocket = clientSocket1;
  }

  public void run() {
    try {
      String fromServer;
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      while (true) {
        fromServer = inFromServer.readLine();
        if(fromServer.equals("q")||fromServer.equals("Q"))
        {
          clientSocket.close();
          break;
        } else {
          System.out.println("RECEIVED:"+fromServer);
        }
      }
    } catch(Exception e){}
  }
}

class TcpClientSimul
{
  public static void main(String args[]) throws Exception
  {
    // String FromServer;
    // String ToServer;
    Socket clientSocket = new Socket("localhost",5000);

    Thread r = new Receive(clientSocket);
    r.start();
    Thread s = new Send(clientSocket);    
    s.start();

    // BufferedReader inFromUser= new BufferedReader(new InputStreamReader(System.in));
    // PrintWriter outToServer= new PrintWriter(clientSocket.getOutputStream(),true);
    // BufferedReader inFromServer = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
    // while(true)
    // {
    //   FromServer = inFromServer.readLine();
    //   if(FromServer.equals("q")||FromServer.equals("Q"))
    //   {
    //     clientSocket.close();
    //     break;
    //   }
    //   else
    //   {
    //     System.out.println("RECEIVED:"+FromServer);
    //     System.out.println("SEND(Type Q or q to Quit):");
    //     ToServer=inFromUser.readLine();
    //     if(ToServer.equals("Q")||ToServer.equals("q"))
    //     {
    //       outToServer.println(ToServer);
    //       clientSocket.close();
    //       break;
    //     }
    //     else
    //     {
    //       outToServer.println(ToServer);
    //     }
    //   }
    // }
  }
}
