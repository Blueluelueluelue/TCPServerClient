import java.io.*;
import java.net.*;

class TcpClientThread
{
	public static void main(String args[]) throws Exception
	{
		String FromServer;
		String ToServer;
		
		Socket clientSocket = new Socket("localhost",5000);//connect here
		 
		BufferedReader inFromUser= new BufferedReader(new InputStreamReader(System.in));
		BufferedReader inFromServer = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
		
		PrintWriter outToServer= new PrintWriter(clientSocket.getOutputStream(),true);
				
		while(true)
		{
			FromServer = inFromServer.readLine();
			
			if(FromServer.equals("bye"))
			{
				System.out.println("\nServer Terminating Connection");
				clientSocket.close();
				break;
			}
			else
			{
				System.out.println("RECEIVED:"+FromServer);
				System.out.println("SEND(Type bye to Quit):");
				ToServer=inFromUser.readLine();
				
				if(ToServer.equals("bye"))
				{
					outToServer.println(ToServer);
					clientSocket.close();
					break;
				}
				else
				{
					StringBuffer sb= new StringBuffer(ToServer);
					outToServer.println(sb);
				}
			}
		}
	}
}
