package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) throws Exception {
		try {
			
			int cntLetter=0;
			int cntVocali = 0;
		    int cntConsonanti = 0;
			
			// Listen to port
			ServerSocket server = new ServerSocket(8698);
			System.out.println("Apertura del socket e attesa connessioni");
			Socket serverClientSocket = server.accept();
			
			DataInputStream inStream = new DataInputStream(serverClientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClientSocket.getOutputStream());
			
			String clientMessage = "";
			
			while(!clientMessage.equals("end")) 
			{
				clientMessage = inStream.readUTF();
		    
			    for(int i = 0; i < clientMessage.length(); i++)
			    {
			    	char ch = clientMessage.charAt(i);
			    	ch = Character.toLowerCase(ch);
			    	if (Character.isLetter(ch)) {
			    		cntLetter++;
						
						if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
						{
							cntVocali++;
						}
						else if ((ch >= 'a' && ch <= 'z'))
						{
							cntConsonanti++;
						}
			    	}
				}
			      
				outStream.writeUTF("Server: Lettere: " + cntLetter + " consonanti: " + cntConsonanti + "vocali: " + cntVocali);
				
				cntLetter=0;
				
				outStream.flush();
				
				 if(cntConsonanti==cntVocali)
		         {
		        	 clientMessage="end";
		         }
			
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
