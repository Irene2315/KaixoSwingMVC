package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import modelo.Producto;
import modelo.ProductoModelo;
import vista.ProductoFormulario;

public class ControladorProducto implements ActionListener, MouseListener {

	private ProductoModelo productoM;
	private vista.ProductoFormulario formularioProducto;

	public ControladorProducto(ProductoModelo productoM, ProductoFormulario formularioProducto) {
		this.productoM = productoM;
		this.formularioProducto = formularioProducto;
		
		this.formularioProducto.btnBuscar.addActionListener(this);
		this.formularioProducto.btnEliminar.addActionListener(this);
		this.formularioProducto.btnGuardar.addActionListener(this);
		this.formularioProducto.btnLimpiar.addActionListener(this);
		this.formularioProducto.btnModificar.addActionListener(this);
		this.formularioProducto.btnBuscarTodos.addActionListener(this);
		
		this.formularioProducto.table.addMouseListener(this);
	}

	public void inicializar() {
		this.formularioProducto.setTitle("Productos");
		formularioProducto.setLocationRelativeTo(null);
		formularioProducto.setVisible(false);
		
		productoM.conectar();
		ArrayList<Producto> productos = productoM.productos();
		productoM.cerrar();
		
		formularioProducto.rellenarTablaProductos(productos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == formularioProducto.btnGuardar) {
			
			Producto producto = formularioProducto.getDatosProducto();
			
			productoM.conectar();
			if(productoM.registrar(producto)) {
				JOptionPane.showMessageDialog(formularioProducto, "Producto registrado", "Ok", JOptionPane.INFORMATION_MESSAGE);
				formularioProducto.limpiar();
			}else {
				JOptionPane.showMessageDialog(formularioProducto, "Error en el registro", "Error", JOptionPane.ERROR_MESSAGE);
			}
			productoM.cerrar();
		}
		
		if (e.getSource() == formularioProducto.btnLimpiar) {
			formularioProducto.limpiar();
		}
		
		if (e.getSource() == formularioProducto.btnEliminar) {
			String codigoProducto = formularioProducto.textCodigo.getText();
			Producto producto = new Producto();
			producto.setCodigo(codigoProducto);
			
			productoM.conectar();
			if(productoM.eliminar(producto)) {
				
				formularioProducto.limpiarTablaProductos();
				ArrayList<Producto> productos = productoM.productos();
				formularioProducto.rellenarTablaProductos(productos);
				
				JOptionPane.showMessageDialog(formularioProducto, "Producto eliminado", "Ok", JOptionPane.INFORMATION_MESSAGE);
				formularioProducto.limpiar();
			}else {
				JOptionPane.showMessageDialog(formularioProducto, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);
			}
			productoM.cerrar();
		}
		
		if(e.getSource() == formularioProducto.btnBuscarTodos) {
			productoM.conectar();
			ArrayList<Producto> productos = productoM.productos();
			productoM.cerrar();
			
			formularioProducto.limpiarTablaProductos();
			formularioProducto.rellenarTablaProductos(productos);
		}
		
		if (e.getSource() == formularioProducto.btnModificar) {
			Producto producto = formularioProducto.getDatosProducto();
			productoM.conectar();
			productoM.modificar(producto);
			productoM.cerrar();
		}
		
		if (e.getSource() == formularioProducto.btnBuscar) {
			String codigo = formularioProducto.textCodigo.getText();
			this.productoM.conectar();
			Producto producto = this.productoM.buscar(codigo);
			if (producto != null) {
				formularioProducto.rellenarFormulario(producto);
				this.productoM.cerrar();
			}
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		JTable source = (JTable)e.getSource();
        int row = source.rowAtPoint( e.getPoint() );
        formularioProducto.rellenarFormularioDeSeleccionDeTabla(row);
//        int column = source.columnAtPoint( e.getPoint() );
//        String s=source.getModel().getValueAt(row, column)+"";
//
//        JOptionPane.showMessageDialog(null, s);	
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
