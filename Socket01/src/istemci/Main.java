package istemci;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//istemci sýnýfýnýnnesnesiþni olusturup pencere düzenini belirleyelim
		Istemci istemci=new Istemci();
		istemci.setTitle("Istemci Tarafi");
		istemci.setSize(400,300);
		istemci.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		istemci.setVisible(true);
	}

}
