import java.io.*;
import java.net.*;

class TcpServer1 extends Thread
{
	int tno;
	ServerSocket server;
	//Socket connected;

	public TcpServer1(int x,ServerSocket server1/*,Socket connected1*/)
	{
		//super.start();
		tno=x;
		server=server1;
		//connected=connected1;
	}

	public void run()
	{
		try
		{
			String fromclient, toclient;

			Socket connected=server.accept();
			while(true)
			{
				//connect=server.accept(); // connect client to server
				System.out.println("\nClient"+" "+ connected.getInetAddress()+":"+connected.getPort()+" is Connected");

				BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
				BufferedReader inFromClient = new BufferedReader( new InputStreamReader(connected.getInputStream()));

				PrintWriter outToClient = new PrintWriter(connected.getOutputStream(),true);

				while(true)
				{
					System.out.println("\nType bye to Close Session for Client "+(tno+1)+"\n");

					System.out.print("Enter Message to Send to Client " + (tno+1) +" : ");
					toclient=inFromUser.readLine();// data typed at server side

					if(toclient.equals("bye"))
					{
						outToClient.println("bye");
						//connected.close();
						return;
						//break;
					}
					else if(connected!=null)
					{
						StringBuffer sb= new StringBuffer(toclient);
						outToClient.println(sb);// send message to client machine
					}
					fromclient=inFromClient.readLine();

					if(fromclient.equals("bye"))
					{
						System.out.println("\nClient " + (tno+1) + " Terminating Connection");
						//connected.close();
						return;
						//break;
					}
					else
					{
						System.out.println("Received from Client " + (tno+1) +" : "+fromclient);
					}
				}
			}
		}
		catch(Exception e)
		{

		}
	}
}

class TcpServerThread
{
	public static int i=0;

	public static void main(String args[]) throws IOException
	{
		ServerSocket server=null;
		Thread []t=new Thread[10];

		System.out.println("\nTCPServer waiting for client on port 5000");
		while(i<10)
		{
			if(server==null)
			{
				server= new ServerSocket(5000);
			}
			////Socket connected=server.accept();
			t[i]=new TcpServer1(i,server/*,connected*/);
			t[i].start();
			//System.out.println("\nClient "+ (i+1) + " Has Connected");
			i++;

			// for(int k=0;k<=i;k++)
			// {
				// try
				// {
					// t[i].wait();
				// }
				// catch(Exception e){}
			// }
		}
		while(true)
		{
			for(int l=0;l<10;l++)
			{
				if(t[l]==null)
				{
					t[l]=new TcpServer1(l,server/*,connected*/);
					t[l].start();
				}
			}
		}
	}
}
