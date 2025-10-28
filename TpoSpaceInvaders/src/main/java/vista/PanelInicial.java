package vista;

import modelo.CreditoManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInicial extends JPanel {
    private CreditoManager creditoManager;
    private JTextField campoCreditos;
    private JButton botonCargar;
    private JButton botonJugar;
    private JLabel etiquetaCreditos;
    private JLabel etiquetaTitulo;
    private PanelPrincipal panelPrincipal;
    private Ventana ventana;
    
    public PanelInicial(Ventana ventana) {
        this.ventana = ventana;
        this.creditoManager = new CreditoManager();
        configurarPanel();
        crearComponentes();
    }
    
    private void configurarPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 600));
    }
    
    private void crearComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Título
        etiquetaTitulo = new JLabel("SPACE INVADERS");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        etiquetaTitulo.setForeground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(etiquetaTitulo, gbc);
        
        // Etiqueta de créditos actuales
        etiquetaCreditos = new JLabel("Créditos: 0");
        etiquetaCreditos.setFont(new Font("Arial", Font.PLAIN, 18));
        etiquetaCreditos.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(etiquetaCreditos, gbc);
        
        // Campo de créditos
        JLabel etiquetaCargar = new JLabel("Cargar créditos:");
        etiquetaCargar.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaCargar.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(etiquetaCargar, gbc);
        
        campoCreditos = new JTextField(10);
        campoCreditos.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoCreditos, gbc);
        
        // Botón Cargar
        botonCargar = new JButton("Cargar Créditos");
        botonCargar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonCargar.setPreferredSize(new Dimension(150, 30));
        botonCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarCreditos();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(botonCargar, gbc);
        
        // Botón Jugar
        botonJugar = new JButton("Jugar");
        botonJugar.setFont(new Font("Arial", Font.BOLD, 16));
        botonJugar.setPreferredSize(new Dimension(200, 40));
        botonJugar.setEnabled(false);
        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarPartida();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(botonJugar, gbc);
        
        // Botón Salir
        JButton botonSalir = new JButton("Salir del juego");
        botonSalir.setFont(new Font("Arial", Font.PLAIN, 14));
        botonSalir.setPreferredSize(new Dimension(200, 40));
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(botonSalir, gbc);
    }
    
    private void cargarCreditos() {
        try {
            String texto = campoCreditos.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese una cantidad de créditos", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int cantidad = Integer.parseInt(texto);
            
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "La cantidad debe ser mayor a 0", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            creditoManager.cargarCreditos(cantidad);
            actualizarEtiquetaCreditos();
            campoCreditos.setText("");
            botonJugar.setEnabled(true);
            
            JOptionPane.showMessageDialog(this, 
                "Se cargaron " + cantidad + " créditos exitosamente", 
                "Créditos cargados", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese un número entero válido", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarEtiquetaCreditos() {
        etiquetaCreditos.setText("Créditos: " + creditoManager.getCreditos());
    }
    
    private void iniciarPartida() {
        if (creditoManager.consumirCredito()) {
            // Crear el panel de juego
            panelPrincipal = new PanelPrincipal(creditoManager, this);
            ventana.cambiarPanel(panelPrincipal);
            actualizarEtiquetaCreditos();
        }
    }
    
    public void regresar() {
        if (panelPrincipal != null) {
            ventana.cambiarPanel(this);
            // Si no tiene créditos, deshabilitar el botón de jugar
            botonJugar.setEnabled(creditoManager.tieneCreditos());
        }
    }
    
    public CreditoManager getCreditoManager() {
        return creditoManager;
    }
}

