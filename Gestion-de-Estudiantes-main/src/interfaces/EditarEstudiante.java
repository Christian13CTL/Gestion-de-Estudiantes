package interfaces;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONObject;

public class EditarEstudiante extends javax.swing.JPanel {

    Estudiante estudiante;
    Color colorDefecto, colorError, colorCambio;

    public EditarEstudiante(Estudiante estudiante) {
        initComponents();
        colorDefecto = new Color(108, 116, 128);
        colorError = new Color(255, 0, 51);
        colorCambio = new Color(1, 154, 214);
        this.estudiante = estudiante;
        cargarDatosEstudiante();
        nombrarCampos();
        bloquearNavegacionTab();
        this.requestFocus();
    }

    private void cargarDatosEstudiante() {
        jtxtCedula.setText(estudiante.getCedula());
        jtxtNombre.setText(estudiante.getNombre());
        jtxtApellido.setText(estudiante.getApellido());
        jtxtTelefono.setText(estudiante.getTelefono());
        jtxaDireccion.setText(estudiante.getDireccion());
    }

    private void bloquearNavegacionTab() {
        jtxtNombre.setFocusTraversalKeysEnabled(false);
        jtxtApellido.setFocusTraversalKeysEnabled(false);
        jtxtTelefono.setFocusTraversalKeysEnabled(false);
        jtxaDireccion.setFocusTraversalKeysEnabled(false);
    }

    private void nombrarCampos() {
        jtxtNombre.setName(estudiante.getCedula());
        jtxtApellido.setName(estudiante.getApellido());
        jtxtTelefono.setName(estudiante.getTelefono());
        jtxaDireccion.setName(estudiante.getDireccion());
    }

    private void validarVacios(JComponent campo) {
        ArrayList<JComponent> formulario = new ArrayList<>(Arrays.asList(jtxtNombre, jtxtApellido, jtxtTelefono, jtxaDireccion));
        formulario.remove(campo);
        for (JComponent campoF : formulario) {
            if (campoF instanceof JTextField) {
                if (((JTextField) campoF).getText().isEmpty()) {
                    if (campoF != jtxtTelefono) {
                        ((JTextField) campoF).setText(campoF.getName());
                        campoF.setForeground(colorDefecto);
                    } else if (verificarCambios(campoF)) {
                        campoF.setForeground(colorCambio);
                    }
                }
            } else {
                if (((JTextArea) campoF).getText().isEmpty() && verificarCambios(campoF)) {
                    campoF.setForeground(colorCambio);
                }
            }
        }
        definirEstadoBotones();
    }

    public boolean validarLongitudMinima(JTextField campo, int longitudMinima) {
        if (campo.getText().length() < longitudMinima) {
            campo.setForeground(colorError);
            return false;
        } else {
            campo.setForeground(colorCambio);
            return true;
        }
    }

    private boolean validarTelefono() {
        if (!Validacion.validarTelefono(jtxtTelefono.getText())) {
            jtxtTelefono.setForeground(colorError);
            return false;
        }
        if (jtxtTelefono.getText().isEmpty()) {
            jtxtTelefono.setForeground(colorDefecto);
        }
        return true;
    }

    private void validarDireccion(java.awt.event.KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (validar == '\n' || validar == '\t') {
            String aux = jtxaDireccion.getText().trim();
            jtxaDireccion.setText(aux);
        }
    }

    private boolean verificarCambios(JComponent campo) {
        if (campo instanceof JTextField) {
            if (((JTextField) campo).getText().equalsIgnoreCase(campo.getName())) {
                campo.setForeground(colorDefecto);
                return false;
            } else {
                campo.setForeground(colorCambio);
                return true;
            }
        } else {
            if (((JTextArea) campo).getText().equalsIgnoreCase(campo.getName())) {
                campo.setForeground(colorDefecto);
                return false;
            } else {
                campo.setForeground(colorCambio);
                return true;
            }
        }
    }

    private void definirEstadoBotones() {
        ArrayList<JComponent> formulario = new ArrayList<>(Arrays.asList(jtxtNombre, jtxtApellido, jtxtTelefono, jtxaDireccion));
        boolean cambio = false;
        boolean error = false;
        for (JComponent campoF : formulario) {
            if (campoF.getForeground().equals(colorCambio)) {
                cambio = true;
            } else if (campoF.getForeground().equals(colorError)) {
                error = true;
            }
        }
        if (cambio) {
            jtxtCancelar.setEnabled(true);
            jtxtConfirmar.setEnabled(true);
        }
        if (error) {
            jtxtCancelar.setEnabled(true);
            jtxtConfirmar.setEnabled(false);
        }
        if (!cambio && !error) {
            jtxtCancelar.setEnabled(false);
            jtxtConfirmar.setEnabled(false);
        }
    }

    private void restablecerColorTextos() {
        ArrayList<JComponent> formulario = new ArrayList<>(Arrays.asList(jtxtNombre, jtxtApellido, jtxtTelefono, jtxaDireccion));
        for (JComponent campoF : formulario) {
            campoF.setForeground(colorDefecto);
        }
    }

    private void restablecerDatosUsuario() {
        cargarDatosEstudiante();
        restablecerColorTextos();
        jtxtCancelar.setEnabled(false);
        jtxtConfirmar.setEnabled(false);
    }

    private void editarEstudiante() {
        Cliente cliente = new Cliente();
        RequestBody requestBody = new FormBody.Builder().add("EST_CEDULA", jtxtCedula.getText())
                .add("EST_NOMBRE", jtxtNombre.getText()).add("EST_APELLIDO", jtxtApellido.getText())
                .add("EST_TELEFONO", jtxtTelefono.getText()).add("EST_DIRECCION", jtxaDireccion.getText()).build();
        JSONObject response = cliente.postJSON("https://proyectosuta.000webhostapp.com/aos_proyecto/models2/editar.php", requestBody);
        boolean verificar = response.getBoolean("ok");
        if (verificar) {
            JOptionPane.showMessageDialog(this, "Se editó correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "No se editó el estudiante");
        }
        cargarDatosEstudiante();
        restablecerColorTextos();
        nombrarCampos();
        jtxtCancelar.setEnabled(false);
        jtxtConfirmar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtApellido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxaDireccion = new javax.swing.JTextArea();
        jpnlCancelar = new javax.swing.JPanel();
        jtxtCancelar = new javax.swing.JLabel();
        jlblBotonCancelar = new javax.swing.JLabel();
        jpnlConfirmar = new javax.swing.JPanel();
        jtxtConfirmar = new javax.swing.JLabel();
        jlblBoton = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(415, 390));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitulo.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jlblTitulo.setForeground(new java.awt.Color(171, 26, 32));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("EDITAR ESTUDIANTE");
        add(jlblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 400, 40));

        jLabel1.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(171, 26, 32));
        jLabel1.setText("NOMBRE");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 165, 30));

        jtxtNombre.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jtxtNombre.setForeground(new java.awt.Color(108, 116, 128));
        jtxtNombre.setText("NOMBRE");
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border empty = new EmptyBorder(6, 5, 2, 2);
        CompoundBorder border = new CompoundBorder(line, empty);
        jtxtNombre.setBorder(border);
        jtxtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtNombreMousePressed(evt);
            }
        });
        jtxtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtNombreKeyTyped(evt);
            }
        });
        add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 250, 45));

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(171, 26, 32));
        jLabel6.setText("APELLIDO");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 165, 30));

        jtxtApellido.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jtxtApellido.setForeground(new java.awt.Color(108, 116, 128));
        jtxtApellido.setText("APELLIDO");
        jtxtApellido.setBorder(border);
        jtxtApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtApellidoMousePressed(evt);
            }
        });
        jtxtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtApellidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtApellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtApellidoKeyTyped(evt);
            }
        });
        add(jtxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 250, 45));

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(171, 26, 32));
        jLabel9.setText("CÉDULA");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 165, 30));

        jtxtCedula.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jtxtCedula.setForeground(new java.awt.Color(108, 116, 128));
        jtxtCedula.setText("XXXXXXXXXX");
        jtxtCedula.setBorder(border);
        jtxtCedula.setEnabled(false);
        add(jtxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 45));

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(171, 26, 32));
        jLabel4.setText("DIRECCIÓN");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 200, 30));

        jtxtTelefono.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jtxtTelefono.setForeground(new java.awt.Color(108, 116, 128));
        jtxtTelefono.setText("09XXXXXXXX");
        jtxtTelefono.setBorder(border);
        jtxtTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtTelefonoMousePressed(evt);
            }
        });
        jtxtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtTelefonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtTelefonoKeyTyped(evt);
            }
        });
        add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 250, 45));

        jLabel5.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(171, 26, 32));
        jLabel5.setText("NÚMERO DE TELÉFONO");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 270, 30));

        jtxaDireccion.setColumns(20);
        jtxaDireccion.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jtxaDireccion.setForeground(new java.awt.Color(108, 116, 128));
        jtxaDireccion.setRows(5);
        jtxaDireccion.setText("Avenida Ejemplo\nCalle primaria 1-23 y\ncalle secundaria\nAmbato - Ecuador");
        jtxaDireccion.setLineWrap(true);
        jtxaDireccion.setWrapStyleWord(true);
        jtxaDireccion.setBorder(border);
        jtxaDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxaDireccionMousePressed(evt);
            }
        });
        jtxaDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxaDireccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxaDireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxaDireccionKeyTyped(evt);
            }
        });
        add(jtxaDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 445, 300, 120));

        jpnlCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jpnlCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtCancelar.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jtxtCancelar.setForeground(new java.awt.Color(171, 26, 32));
        jtxtCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtCancelar.setText("CANCELAR");
        jtxtCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtxtCancelar.setEnabled(false);
        jtxtCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtCancelarMouseClicked(evt);
            }
        });
        jpnlCancelar.add(jtxtCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 180, 45));

        jlblBotonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buttonB_180_45.png"))); // NOI18N
        jpnlCancelar.add(jlblBotonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 45));

        add(jpnlCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 450, 180, 45));

        jpnlConfirmar.setBackground(new java.awt.Color(255, 255, 255));
        jpnlConfirmar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtConfirmar.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jtxtConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        jtxtConfirmar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtConfirmar.setText("GUARDAR");
        jtxtConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtxtConfirmar.setEnabled(false);
        jtxtConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtConfirmarMouseClicked(evt);
            }
        });
        jpnlConfirmar.add(jtxtConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 45));

        jlblBoton.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jlblBoton.setForeground(new java.awt.Color(255, 255, 255));
        jlblBoton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/button_180_45.png"))); // NOI18N
        jlblBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnlConfirmar.add(jlblBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 45));

        add(jpnlConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 510, 180, 45));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras_40_35.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 5, 40, 35));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 45));
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtNombreMousePressed
        validarVacios(jtxtNombre);
    }//GEN-LAST:event_jtxtNombreMousePressed

    private void jtxtApellidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtApellidoMousePressed
        validarVacios(jtxtApellido);
    }//GEN-LAST:event_jtxtApellidoMousePressed

    private void jtxtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNombreKeyTyped
        Validacion.convertirMayuscula(evt);
        Validacion.validarNLetras(evt, jtxtNombre, 25);
    }//GEN-LAST:event_jtxtNombreKeyTyped

    private void jtxtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtApellidoKeyTyped
        Validacion.convertirMayuscula(evt);
        Validacion.validarNLetras(evt, jtxtApellido, 25);
    }//GEN-LAST:event_jtxtApellidoKeyTyped

    private void jtxtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTelefonoKeyTyped
        Validacion.validarNNumeros(evt, jtxtTelefono, 10);
    }//GEN-LAST:event_jtxtTelefonoKeyTyped

    private void jtxtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTelefonoKeyReleased
        if (validarTelefono()) {
            verificarCambios(jtxtTelefono);
        }
        definirEstadoBotones();
    }//GEN-LAST:event_jtxtTelefonoKeyReleased

    private void jtxtCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtCancelarMouseClicked
        if (jtxtCancelar.isEnabled()) {
            restablecerDatosUsuario();
        }
    }//GEN-LAST:event_jtxtCancelarMouseClicked

    private void jtxtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNombreKeyReleased
        if (validarLongitudMinima(jtxtNombre, 2)) {
            verificarCambios(jtxtNombre);
        }
        definirEstadoBotones();
    }//GEN-LAST:event_jtxtNombreKeyReleased

    private void jtxtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtApellidoKeyReleased
        if (validarLongitudMinima(jtxtApellido, 2)) {
            verificarCambios(jtxtApellido);
        }
        definirEstadoBotones();
    }//GEN-LAST:event_jtxtApellidoKeyReleased

    private void jtxaDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxaDireccionKeyReleased
        verificarCambios(jtxaDireccion);
        definirEstadoBotones();
    }//GEN-LAST:event_jtxaDireccionKeyReleased

    private void jtxtTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtTelefonoMousePressed
        validarVacios(jtxtTelefono);
    }//GEN-LAST:event_jtxtTelefonoMousePressed

    private void jtxaDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxaDireccionMousePressed
        validarVacios(jtxaDireccion);
    }//GEN-LAST:event_jtxaDireccionMousePressed

    private void jtxaDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxaDireccionKeyTyped
        validarDireccion(evt);
        Validacion.validarNCaracteres(evt, jtxaDireccion, 100);
    }//GEN-LAST:event_jtxaDireccionKeyTyped

    private void jtxtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNombreKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxtNombreKeyPressed

    private void jtxtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtApellidoKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxtApellidoKeyPressed

    private void jtxtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTelefonoKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxtTelefonoKeyPressed

    private void jtxaDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxaDireccionKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxaDireccionKeyPressed

    private void jtxtConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtConfirmarMouseClicked
        if (jtxtConfirmar.isEnabled()) {
            editarEstudiante();
        }
    }//GEN-LAST:event_jtxtConfirmarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlblBoton;
    private javax.swing.JLabel jlblBotonCancelar;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JPanel jpnlCancelar;
    private javax.swing.JPanel jpnlConfirmar;
    private javax.swing.JTextArea jtxaDireccion;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JLabel jtxtCancelar;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JLabel jtxtConfirmar;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtTelefono;
    // End of variables declaration//GEN-END:variables
}
