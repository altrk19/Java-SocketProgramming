package istemci;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//istemci s�n�f�n�nnesnesi�ni olusturup pencere d�zenini belirleyelim
		Istemci istemci=new Istemci();
		istemci.setTitle("Istemci Tarafi");
		istemci.setSize(400,300);
		istemci.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		istemci.setVisible(true);
	}

}
