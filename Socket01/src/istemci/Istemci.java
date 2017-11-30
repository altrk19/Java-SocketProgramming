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

	//kullan�c�n�n veri girebilmesi i�in bir metin alan�,sunucuya g�nderilen ve sunucudan gelen datalar�n g�sterilmesi i�in metin alan� tan�mlayal�m
	
	//sunucuya data g�ndemrek i�in bir DataOutputStream ve gelen datay� alabilmek i�in bir DataInputStream tan�mlayal�m
	
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
		//metinArea'yi jscrollpanel i�erisine koyarak layout'a ekleyelim.
		add(new JScrollPane(metinArea),BorderLayout.CENTER);
		//olay dinleyici s�n�ftan sonra burda dinleyici atamas� yapal�m
		metinAlani.addActionListener(new MetinAlanDinleyici());
		
		//try catch blogu i�erisinde bir soket olustural�m
		try {
			Socket istemciSoket=new Socket("localhost",9999);
			//data stream'lar� olustural�m
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
				
				//metin alan�na girilen de�eri getText() metodu ile alal�m,trim() metodu ile bo�luklar� silelim ve metin alan� string de�eri ta��d�g�ndan de�eri double tipine �evirelim
				double x=Double.parseDouble(metinAlani.getText().trim());
				//kullan�c�n�n girdi�i de�eri sunucuya g�nderelim
				yazici.writeDouble(x);
				//sunucudan gelen datay� alal�m.
				double karesiniAl=okuyucu.readDouble();
				//bu bilgileri metinArea'ya ekleyelim.
				metinArea.append("x degeri "+x);
				metinArea.append(" sunucunun g�nderdigi deger "+karesiniAl+'\n');
				
			}
			catch(IOException istisna) {
				System.err.print(istisna);
			}
			//�imdi metin alan�m�za oaly s�n�f� d�nleyici oalrak atayal�m
		}

		
	}
	
}
