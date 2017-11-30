package istemci;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Istemci extends JFrame{

	//kullanýcýnýn veri girebilmesi için bir metin alaný,sunucuya gönderilen ve sunucudan gelen datalarýn gösterilmesi için metin alaný tanýmlayalým
	
	//sunucuya data göndemrek için bir DataOutputStream ve gelen datayý alabilmek için bir DataInputStream tanýmlayalým
	
	private JTextField metinAlani =new JTextField();
	private JTextArea metinArea=new JTextArea();
	private DataInputStream okuyucu;
	private DataOutputStream yazici;
	
	public Istemci() {
		
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(metinAlani,BorderLayout.CENTER);
		metinAlani.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(new JLabel("bir deger giriniz"),BorderLayout.WEST);
		
		//istemci penceresinin layotunu belirleyelim
		setLayout(new BorderLayout());
		add(panel,BorderLayout.NORTH);
		//metinArea'yi jscrollpanel içerisine koyarak layout'a ekleyelim.
		add(new JScrollPane(metinArea),BorderLayout.CENTER);
		//olay dinleyici sýnýftan sonra burda dinleyici atamasý yapalým
		metinAlani.addActionListener(new MetinAlanDinleyici());
		
		//try catch blogu içerisinde bir soket olusturalým
		try {
			Socket istemciSoket=new Socket("localhost",9999);
			//data stream'larý olusturalým
			okuyucu=new DataInputStream(istemciSoket.getInputStream());
			yazici=new DataOutputStream(istemciSoket.getOutputStream());
		}
		catch(IOException istisna) {
			System.err.print(istisna);
		}
		
	}
	
	private class MetinAlanDinleyici implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				
				//metin alanýna girilen deðeri getText() metodu ile alalým,trim() metodu ile boþluklarý silelim ve metin alaný string deðeri taþýdýgýndan deðeri double tipine çevirelim
				double x=Double.parseDouble(metinAlani.getText().trim());
				//kullanýcýnýn girdiði deðeri sunucuya gönderelim
				yazici.writeDouble(x);
				//sunucudan gelen datayý alalým.
				double karesiniAl=okuyucu.readDouble();
				//bu bilgileri metinArea'ya ekleyelim.
				metinArea.append("x degeri "+x);
				metinArea.append(" sunucunun gönderdigi deger "+karesiniAl+'\n');
				
			}
			catch(IOException istisna) {
				System.err.print(istisna);
			}
			//Þimdi metin alanýmýza oaly sýnýfý dþnleyici oalrak atayalým
		}

		
	}
	
}
