import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastChatServer {
    public static void main(String[] args)
        throws Exception {

        //Portnummer vi använder
        //Vi väljer 1050 eftersom en kontroll säger att den inte är upptagen  och inte reserverad för specifika tjänster.
        int portNumber = 1050;
        if(args.length >= 1){
            portNumber = Integer.parseInt(args[0]);
        }

        //Skapa en MulticastSocket
        MulticastSocket serverMulticastSocket = new MulticastSocket(portNumber);
        System.out.println("MulticastSocket listening on port " + portNumber);

        //Avgör IP-adressen av en host
        InetAddress group = InetAddress.getByName("225.4.5.6");

        //Returnera givna IP-adress
        serverMulticastSocket.joinGroup(group);
        System.out.println("joingroup method is called... ");
        boolean infinite = true;

        while(infinite){
            byte[] buf = new byte[1024];
            DatagramPacket data  = new DatagramPacket(buf, buf.length);
            serverMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();

            System.out.println("Message received from client: " + msg);
        }
        serverMulticastSocket.close();
        }
}
