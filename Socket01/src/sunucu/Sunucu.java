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
		
		//try blo�u i�erisinde sunucumuzu d�zenleyelim
		try {
			ServerSocket sunucuSoket =new ServerSocket(9999);
			metinArea.append("Sunucu : "+new Date()+" tarihinde calismaya basladi \n");
			//sunucuya ba�lant�lar� dinletelim
			Socket soket=sunucuSoket.accept();
			
			//istemcinin ip adresini ve ad�n� al�p metinArea'da g�sterelim
			//bu i�lem ba�lant� geldikten sonra yap�lmal�d�r.. accept'ten sonra
			
			InetAddress inetAdres=soket.getInetAddress();
			metinArea.append("sunucuya baglanan istemci adi : "+inetAdres.getHostName()+'\n');
			metinArea.append("sunucuya baglanan istemci adresi : "+inetAdres.getHostAddress()+'\n');
			
			//veri aktar�m� yapmak i�in ilgili stream nesnelerini olusturul�m
			DataInputStream okuyucu=new DataInputStream(soket.getInputStream());
			DataOutputStream yazici=new DataOutputStream(soket.getOutputStream());
			
			//uygulama kapanana kadar d�ng�n�n �al��mas� i�in d�ng�den ��k�s ifadesi koymayacag�z
			
			while(true) {
				
				//readDouble() metodu ile istemciden gelecek datay� alal�m
				//burada x degerini alal�m ve bu degerin karesini hesaplatal�m
				double x=okuyucu.readDouble();
				double karesiniAl=x*x;
				
				//writeDouble() metodu ile hesaplanan degeri tekar istemciye g�nderelim
				yazici.writeDouble(karesiniAl);
				//bu bilgileri ekranda g�r�nt�leyebilmek i�in append metodu ile textArea'ya ekleyelim.
				metinArea.append("istemciden gelen deger "+x+'\n');
				metinArea.append("istemciye gonderilen deger "+karesiniAl+'\n');
				
			}
			
			
		}
		catch(IOException istisna) {
			System.err.println(istisna);
		}
		
	}
}
