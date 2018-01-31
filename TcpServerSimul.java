import java.io.*;
import java.net.*;

class Send extends Thread
{
  ServerSocket server;
  Socket connected;
  public Send(ServerSocket server1,Socket connected1) {
    server = server1;
	connected=connected1;
  }

  public void run() {
    String toClient;

    try {
      System.out.println("TCPServer waiting for client on port 5000");
      System.out.println(" THE CLIENT "+ connected.getInetAddress() + " is on sending port " + connected.getPort());
      while (true) {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outToClient = new PrintWriter(connected.getOutputStream(),true);
        // System.out.println("SEND(Type Q or q to Quit):");
        toClient = inFromUser.readLine();
        if(toClient.equals("q") || toClient.equals("Q"))
        {
          outToClient.println(toClient);
          connected.close();
          break;
        }
        else
        {
          outToClient.println(toClient);
        }
      }
    } catch(Exception e){}
  }
}


class Receive extends Thread
{
  ServerSocket server;
  Socket connected;
  public Receive(ServerSocket server1,Socket connected1) {
    server = server1;
	connected=connected1;
  }

  public void run() {
    String fromClient;

    try {
      System.out.println(" THE CLIENT "+ connected.getInetAddress() + " is on receiving port " + connected.getPort());
      while (true) {
        BufferedReader inFromClient = new BufferedReader( new InputStreamReader(connected.getInputStream()));
        fromClient=inFromClient.readLine();
        if(fromClient.equals("q") || fromClient.equals("Q"))
        {
          connected.close();
          break;
        }
        else
        {
          System.out.println("RECEIVED:"+fromClient);
        }
      }
    } catch(Exception e){}
  }
}


class TcpServerSimul
{
  public static void main(String[] args)
  {
	  try{
		   ServerSocket server = new ServerSocket(5000);
			Socket connected=server.accept();

			Thread r = new Receive(server,connected);
			r.start();
			Thread s = new Send(server,connected);
			s.start();
	  }
	  catch(Exception e){
		  
	  }
    // String fromclient,toclient;
   
    // System.out.println("TCPServer waiting for client on port 5000");
    // while(true)
    // {
    //   Socket connected = server.accept();
    //   System.out.println(" THE CLIENT "+ connected.getInetAddress() + ":" + connected.getPort() + " Is CONNECTED");
    //   BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
    //   BufferedReader inFromClient = new BufferedReader( new InputStreamReader(connected.getInputStream()));
    //   PrintWriter outToClient = new PrintWriter(connected.getOutputStream(),true);
    //   while(true)
    //   {
    //     System.out.println("SEND(Type Q or q to Quit):");
    //     toclient=inFromUser.readLine();
    //     if(toclient.equals("q")||toclient.equals("Q"))
    //     {
    //       outToClient.println(toclient);
    //       connected.close();
    //       break;
    //     }
    //     else
    //     {
    //       outToClient.println(toclient);
    //     }
    //     fromclient=inFromClient.readLine();
    //     if(fromclient.equals("q")|| fromclient.equals("Q"))
    //     {
    //       connected.close();
    //       break;
    //     }
    //     else
    //     {
    //       System.out.println("RECEIVED:"+fromclient);
    //     }
    //   }
    // }
  }
}
