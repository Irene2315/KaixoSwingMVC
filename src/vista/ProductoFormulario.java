package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ProductoFormulario extends JDialog {

	private JPanel contentPane;
	public JTextField textNombre;
	public JTextField textPrecio;
	public JTextField textCantidad;
	public JTextField textId;
	public JTextField textCodigo;
	public JButton btnBuscar;
	public JButton btnModificar;
	public JButton btnGuardar;
	public JButton btnEliminar;
	public JButton btnLimpiar;
	public JButton btnBuscarTodos;

	public JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProductoFormulario frame = new ProductoFormulario();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ProductoFormulario(JFrame padre, boolean modal) {
		super(padre, modal);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 30, 46, 14);
		contentPane.add(lblCodigo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 74, 46, 14);
		contentPane.add(lblNombre);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 118, 46, 14);
		contentPane.add(lblPrecio);

		textCodigo = new JTextField();
		textCodigo.setBounds(68, 27, 86, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(66, 71, 86, 20);
		contentPane.add(textNombre);

		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(66, 115, 86, 20);
		contentPane.add(textPrecio);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 162, 46, 14);
		contentPane.add(lblCantidad);

		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(68, 159, 86, 20);
		contentPane.add(textCantidad);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(11, 206, 89, 23);
		contentPane.add(btnGuardar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(116, 206, 89, 23);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(215, 206, 89, 23);
		contentPane.add(btnEliminar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(314, 206, 89, 23);
		contentPane.add(btnLimpiar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(317, 25, 89, 23);
		contentPane.add(btnBuscar);

		textId = new JTextField();
		textId.setEditable(false);
		textId.setEnabled(false);
		textId.setColumns(10);
		textId.setBounds(317, 60, 86, 20);
		contentPane.add(textId);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(162, 87, 262, 112);
		contentPane.add(scrollPane);

		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"id", "codigo", "nombre", "precio", "cantidad"
//			}
//		));
		this.tableModel = new DefaultTableModel();
		table.setModel(tableModel);
		
		tableModel.addColumn("id");
		tableModel.addColumn("codigo");
		tableModel.addColumn("nombre");
		tableModel.addColumn("cantidad");
		tableModel.addColumn("precio");

		scrollPane.setViewportView(table);

		btnBuscarTodos = new JButton("ver todos");
		btnBuscarTodos.setBounds(190, 26, 89, 23);
		contentPane.add(btnBuscarTodos);
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void limpiar() {
		textId.setText("");
		textCodigo.setText("");
		textNombre.setText("");
		textCantidad.setText("");
		textPrecio.setText("");
	}

	public Producto getDatosProducto() {
		Producto producto = new Producto();
		producto.setId(Integer.parseInt(this.textId.getText()));
		producto.setNombre(this.textNombre.getText());
		producto.setCantidad(Integer.parseInt(this.textCantidad.getText()));
		producto.setCodigo(this.textCodigo.getText());
		producto.setPrecio(Double.parseDouble(this.textPrecio.getText()));
		return producto;
	}

	public void rellenarTablaProductos(ArrayList<Producto> productos) {
		for (Producto producto : productos) {
			Object[] fila = new Object[6];
			fila[0] = producto.getId();
			fila[1] = producto.getCodigo();
			fila[2] = producto.getNombre();
			fila[3] = producto.getCantidad();
			fila[4] = producto.getPrecio();

			this.tableModel.addRow(fila);
		}
	}
	
	public void limpiarTablaProductos() {
		for (int i = this.tableModel.getRowCount() - 1; i >= 0; i--) {
			this.tableModel.removeRow(i);
		}
	}

	public void rellenarFormulario(Producto producto) {
		textId.setText(String.valueOf(producto.getId()));
		textCodigo.setText(producto.getCodigo());
		textNombre.setText(producto.getNombre());
		textCantidad.setText(String.valueOf(producto.getCantidad()));
		textPrecio.setText(String.valueOf(producto.getPrecio()));
	}

	public void rellenarFormularioDeSeleccionDeTabla(int row) {
		textId.setText(String.valueOf(tableModel.getValueAt(row, tableModel.findColumn("id"))));
		textCodigo.setText(String.valueOf(tableModel.getValueAt(row, tableModel.findColumn("codigo"))));
		textNombre.setText(String.valueOf(tableModel.getValueAt(row, tableModel.findColumn("nombre"))));
		textCantidad.setText(String.valueOf(tableModel.getValueAt(row, tableModel.findColumn("cantidad"))));
		textPrecio.setText(String.valueOf(tableModel.getValueAt(row, tableModel.findColumn("precio"))));		
	}
}
