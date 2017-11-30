package sunucu;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Sunucu extends JFrame{

	private JTextArea metinArea=new JTextArea();
	public Sunucu() {
		setTitle("SunucuTarafi");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(metinArea));
		
		//try bloðu içerisinde sunucumuzu düzenleyelim
		try {
			ServerSocket sunucuSoket =new ServerSocket(9999);
			metinArea.append("Sunucu : "+new Date()+" tarihinde calismaya basladi \n");
			//sunucuya baðlantýlarý dinletelim
			Socket soket=sunucuSoket.accept();
			
			//istemcinin ip adresini ve adýný alýp metinArea'da gösterelim
			//bu iþlem baðlantý geldikten sonra yapýlmalýdýr.. accept'ten sonra
			
			InetAddress inetAdres=soket.getInetAddress();
			metinArea.append("sunucuya baglanan istemci adi : "+inetAdres.getHostName()+'\n');
			metinArea.append("sunucuya baglanan istemci adresi : "+inetAdres.getHostAddress()+'\n');
			
			//veri aktarýmý yapmak için ilgili stream nesnelerini olusturulým
			DataInputStream okuyucu=new DataInputStream(soket.getInputStream());
			DataOutputStream yazici=new DataOutputStream(soket.getOutputStream());
			
			//uygulama kapanana kadar döngünün çalýþmasý için döngüden çýkýs ifadesi koymayacagýz
			
			while(true) {
				
				//readDouble() metodu ile istemciden gelecek datayý alalým
				//burada x degerini alalým ve bu degerin karesini hesaplatalým
				double x=okuyucu.readDouble();
				double karesiniAl=x*x;
				
				//writeDouble() metodu ile hesaplanan degeri tekar istemciye gönderelim
				yazici.writeDouble(karesiniAl);
				//bu bilgileri ekranda görüntüleyebilmek için append metodu ile textArea'ya ekleyelim.
				metinArea.append("istemciden gelen deger "+x+'\n');
				metinArea.append("istemciye gonderilen deger "+karesiniAl+'\n');
				
			}
			
			
		}
		catch(IOException istisna) {
			System.err.println(istisna);
		}
		
	}
}
