package principal;

import controlador.ControladorPrincipal;
import controlador.ControladorProducto;
import modelo.ProductoModelo;
import vista.Principal;
import vista.ProductoFormulario;

public class App {

	public static void main(String[] args) {
		Principal frmPrincipal = new Principal();
		
		ControladorPrincipal principalC = new ControladorPrincipal(frmPrincipal);
		principalC.inicializar();
		frmPrincipal.setVisible(true);
		
	}

}
