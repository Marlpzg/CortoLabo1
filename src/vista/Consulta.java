package vista;

import conexion.Conexion;
import dao.ProductoDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

public class Consulta extends JFrame{
    
    public JLabel lblCodigo, lblTipo, lblStock, lblExistencia, lblNombre, lblPrecio;
    public JTextField codigo, descripcion, stock, nombre, precio;
    public JComboBox tipo;
    
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton si;
    public JRadioButton no;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm;

    public static final Conexion con = Conexion.conectar();
    
    public Consulta(){
    
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblNombre);
        container.add(lblTipo);
        container.add(lblPrecio);
        container.add(lblStock);
        container.add(lblExistencia);
        container.add(nombre);
        container.add(codigo);
        container.add(tipo);
        container.add(precio);
        container.add(stock);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(610,550);
        eventos();
    }

    public final void agregarLabels() {
    
        lblCodigo = new JLabel("Codigo");
        lblNombre = new JLabel("Nombre");
        lblTipo = new JLabel("Tipo");
        lblPrecio = new JLabel("Precio");
        lblStock = new JLabel("Cantidad");
        lblExistencia = new JLabel("Stock en tienda");
        lblNombre.setBounds(10,60, ANCHOC, ALTOC);
        lblCodigo.setBounds(10,10, ANCHOC, ALTOC);
        lblTipo.setBounds(300,100, ANCHOC, ALTOC);
        lblPrecio.setBounds(10,100, ANCHOC, ALTOC);
        lblStock.setBounds(10,140, ANCHOC, ALTOC);
        lblExistencia.setBounds(300,140, ANCHOC, ALTOC);
        
    }
    
    public final void formulario(){
    
        nombre = new JTextField();
        codigo = new JTextField();
        tipo = new JComboBox();
        stock = new JTextField();
        precio = new JTextField();
        si = new JRadioButton("Si", true);
        no = new JRadioButton("No");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();

        tipo.addItem("Fruta");
        tipo.addItem("Verdura");
        tipo.addItem("Bebida");
        tipo.addItem("Dulce");

        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        nombre.setBounds(140,60,ANCHOC, ALTOC);
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        precio.setBounds(140, 100, ANCHOC, ALTOC);
        tipo.setBounds(340, 100, ANCHOC, ALTOC);
        stock.setBounds(140, 140, ANCHOC, ALTOC);
        si.setBounds(420, 140, 50, ALTOC);
        no.setBounds(480, 140, 50, ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 200, ANCHOC, ALTOC);
        actualizar.setBounds(150, 200, ANCHOC, ALTOC);
        eliminar.setBounds(300, 200, ANCHOC, ALTOC);
        limpiar.setBounds(450, 200, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
        
    }
    
    public void llenarTabla(){
    
        tm = new DefaultTableModel(){
        
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
            
        };
        tm.addColumn("Nombre");
        tm.addColumn("Codigo");
        tm.addColumn("Tipo");
        tm.addColumn("Cantidad");
        tm.addColumn("Precio");
        tm.addColumn("Disponibilidad");
        
        ProductoDao pd = new ProductoDao();
        
        ArrayList<Producto> productos = pd.readAll();
        
        for (Producto prod: productos){
            tm.addRow(new Object[]{prod.getNombre(), prod.getCodigo(), prod.getTipo(), prod.getCantidad(), prod.getPrecio(), prod.getDisponibilidad()});
        }
        
        resultados.setModel(tm);
        
    }
    
    public void eventos(){
    
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ProductoDao pd = new ProductoDao();
                Producto p = new Producto(nombre.getText(), codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(stock.getText()), Float.parseFloat(precio.getText()), true);
                
                if(no.isSelected()){
                    p.setDisponibilidad(false);
                }
                
                if(pd.create(p)){
                
                    JOptionPane.showMessageDialog(null, "Producto registrado con éxito");
                    limpiarCampos();
                    llenarTabla();
                    
                }else{
                
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de crear el producto");
                
                }
                
            }
            
        });
        
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ProductoDao pd = new ProductoDao();
                Producto p = new Producto(nombre.getText(), codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(stock.getText()), Float.parseFloat(precio.getText()), true);
                
                if(no.isSelected()){
                    p.setDisponibilidad(false);
                }
                
                if(pd.update(p)){
                
                    JOptionPane.showMessageDialog(null, "Producto modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                    
                }else{
                
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de modificar el producto");
                
                }
                
            }
            
        });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ProductoDao fd = new ProductoDao();
               
                if(fd.delete(codigo.getText())){
                
                    JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                    
                }else{
                
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de eliminar el producto");
                
                }
                
            }
            
        });
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ProductoDao fd = new ProductoDao();
                Producto p = fd.read(codigo.getText());

                if(p == null){
                
                    JOptionPane.showMessageDialog(null, "El producto buscado no se ha encontrado");
                    
                }else{
                    
                    nombre.setText(p.getNombre());
                    codigo.setText(p.getCodigo());
                    tipo.setSelectedItem(p.getTipo());
                    precio.setText(Float.toString(p.getPrecio()));
                    stock.setText(Integer.toString(p.getCantidad()));
                    
                    if(p.getDisponibilidad()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(true);
                    }
                
                }
                
            }
            
        });
        
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                limpiarCampos();
                
            }
            
        });
                
    }
    
    public void limpiarCampos(){
        nombre.setText("");
        codigo.setText("");
        tipo.setSelectedItem("Fruta");
        stock.setText("");
        precio.setText("");
        si.setSelected(true);
    }
    
    public static void main(String[] args){
    
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                
                new Consulta().setVisible(true);

            }
            
        });
        
    }

}
