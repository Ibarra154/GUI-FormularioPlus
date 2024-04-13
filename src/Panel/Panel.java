package Panel;

import Scripts.ListaDoble;
import Scripts.ListaDoble.NodoDoble;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ivan_
 */
public class Panel extends JFrame {

    ListaDoble lista = new ListaDoble();
    NodoDoble actual;
    int contador = 1;

    private final JPanel lblJPanel;
    private final JPanel lbl2JPanel;
    private final JPanel txtJPanel;
    private final JPanel btnJPanel;
    private final JPanel jPanel;

    private final JLabel lblNombre;
    private final JLabel lblEdad;
    private final JLabel lblPeso;
    private final JLabel lblAltura;
    private final JLabel lblIMC;
    private final JLabel lblContador;
    private final JLabel lblMensaje;

    private final JTextField txtNombre;
    private final JTextField txtEdad;
    private final JTextField txtAltura;
    private final JTextField txtPeso;
    private final JTextField txtIMC;

    private final JButton btnGuardar;
    private final JButton btnNuevo;
    private final JButton btnBuscar;
    private final JButton btnSig;
    private final JButton btnAnt;

    public Panel() {
        super("Nutricion");
        if (!lista.archivoNulo("Datos.txt")) {
            lista.leerDeArchivo("Datos.txt");
            actual = lista.getNodoInicio();
        } else {

        }

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 1));
        lblMensaje = new JLabel("         ");
        jPanel.add(lblMensaje);
        add(jPanel, BorderLayout.NORTH);

        lblJPanel = new JPanel();
        lblJPanel.setLayout(new GridLayout(5, 1));
        lblNombre = new JLabel("Nombre:");
        lblJPanel.add(lblNombre);
        lblEdad = new JLabel("Edad:");
        lblJPanel.add(lblEdad);
        lblPeso = new JLabel("Peso:");
        lblJPanel.add(lblPeso);
        lblAltura = new JLabel("Altura:");
        lblJPanel.add(lblAltura);
        lblIMC = new JLabel("IMC:");
        lblJPanel.add(lblIMC);
        add(lblJPanel, BorderLayout.WEST);

        if (!lista.archivoNulo("Datos.txt")) {
            txtJPanel = new JPanel();
            txtJPanel.setLayout(new GridLayout(5, 1));
            txtNombre = new JTextField(lista.getNombre(actual));
            txtJPanel.add(txtNombre);
            txtEdad = new JTextField(lista.getEdad(actual));
            txtJPanel.add(txtEdad);
            txtPeso = new JTextField(lista.getPeso(actual));
            txtJPanel.add(txtPeso);
            txtAltura = new JTextField(lista.getAltura(actual));
            txtJPanel.add(txtAltura);
            txtIMC = new JTextField(lista.getIMC(actual));
            txtJPanel.add(txtIMC);
            add(txtJPanel, BorderLayout.CENTER);
        } else {
            txtJPanel = new JPanel();
            txtJPanel.setLayout(new GridLayout(5, 1));
            txtNombre = new JTextField();
            txtJPanel.add(txtNombre);
            txtEdad = new JTextField();
            txtJPanel.add(txtEdad);
            txtPeso = new JTextField();
            txtJPanel.add(txtPeso);
            txtAltura = new JTextField();
            txtJPanel.add(txtAltura);
            txtIMC = new JTextField();
            txtJPanel.add(txtIMC);
            add(txtJPanel, BorderLayout.CENTER);
        }

        btnJPanel = new JPanel();
        btnJPanel.setLayout(new GridLayout(1, 5));
        btnGuardar = new JButton("Guardar");
        btnNuevo = new JButton("Nuevo");
        btnBuscar = new JButton("Buscar");
        btnSig = new JButton(">");
        btnAnt = new JButton("<");
        btnJPanel.add(btnGuardar);
        btnJPanel.add(btnNuevo);
        btnJPanel.add(btnBuscar);
        btnJPanel.add(btnAnt);
        btnJPanel.add(btnSig);
        add(btnJPanel, BorderLayout.SOUTH);

        lbl2JPanel = new JPanel();
        lbl2JPanel.setLayout(new GridLayout(5, 1));
        lblContador = new JLabel("Persona " + contador + " de " + lista.size());
        lbl2JPanel.add(lblContador);
        add(lbl2JPanel, BorderLayout.EAST);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String datos[] = new String[5];
                datos[0] = txtNombre.getText();
                datos[1] = txtEdad.getText();
                datos[2] = txtPeso.getText();
                datos[3] = txtAltura.getText();
                datos[4] = txtIMC.getText();
                lista.add(datos);
                lista.guardarEnArchivo("Datos.txt");
                lblContador.setText("Persona " + contador + " de " + lista.size());
            }
        });

        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                txtNombre.setText("");
                txtEdad.setText("");
                txtPeso.setText("");
                txtAltura.setText("");
                txtIMC.setText("");
            }
        });
        btnSig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (lista.getNodoNext(actual) != null) {
                    contador++;
                    actual = lista.getNodoNext(actual);
                    txtNombre.setText(lista.getNombre(actual));
                    txtEdad.setText(lista.getEdad(actual));
                    txtPeso.setText(lista.getPeso(actual));
                    txtAltura.setText(lista.getAltura(actual));
                    txtIMC.setText(lista.getIMC(actual));
                    lblContador.setText("Persona " + contador + " de " + lista.size());
                }

            }
        });
        btnAnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (lista.getNodoPrev(actual) != null) {
                    contador--;
                    actual = lista.getNodoPrev(actual);
                    txtNombre.setText(lista.getNombre(actual));
                    txtEdad.setText(lista.getEdad(actual));
                    txtPeso.setText(lista.getPeso(actual));
                    txtAltura.setText(lista.getAltura(actual));
                    txtIMC.setText(lista.getIMC(actual));
                    lblContador.setText("Persona " + contador + " de " + lista.size());
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    actual = lista.buscar(JOptionPane.showInputDialog(null, "Ingresa el nombre", "busqueda", 1));
                } catch (NullPointerException ex) {

                }
                if (actual != null) {
                    txtNombre.setText(lista.getNombre(actual));
                    txtEdad.setText(lista.getEdad(actual));
                    txtPeso.setText(lista.getPeso(actual));
                    txtAltura.setText(lista.getAltura(actual));
                    txtIMC.setText(lista.getIMC(actual));
                    lblContador.setText("Persona " + contador + " de " + lista.size());
                } else {
                    JOptionPane.showMessageDialog(null, "Persona no encontrada");
                }
            }
        });
        
        noEditable();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    private void noEditable() {
        txtNombre.setEditable(false);
        txtEdad.setEditable(false);
        txtPeso.setEditable(false);
        txtAltura.setEditable(false);
        txtIMC.setEditable(false);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnSig.setEnabled(false);
        btnAnt.setEnabled(false);
    }
    public void editable() {
        txtNombre.setEditable(true);
        txtEdad.setEditable(true);
        txtPeso.setEditable(true);
        txtAltura.setEditable(true);
        txtIMC.setEditable(true);
        btnGuardar.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnSig.setEnabled(true);
        btnAnt.setEnabled(true);
    }

}
