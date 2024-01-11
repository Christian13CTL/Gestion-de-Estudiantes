package interfaces;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

public class CrearEstudiante extends javax.swing.JPanel {

    Cliente cliente = new Cliente();
    Color colorDefecto, colorError;
    int xMouse, yMouse;
    boolean claveOculta;

    public CrearEstudiante() {
        initComponents();
        colorDefecto = new Color(204, 204, 204);
        colorError = new Color(255, 0, 51);
        nombrarCampos();
        bloquearNavegacionTab();
        this.requestFocus();
    }

    private void bloquearNavegacionTab() {
        jtxtCedula.setFocusTraversalKeysEnabled(false);
        jtxtNombre.setFocusTraversalKeysEnabled(false);
        jtxtApellido.setFocusTraversalKeysEnabled(false);
        jtxtTelefono.setFocusTraversalKeysEnabled(false);
    }

    private void nombrarCampos() {
        jtxtNombre.setName("NOMBRE");
        jtxtApellido.setName("APELLIDO");
        jtxtCedula.setName("CÉDULA");
        jtxtTelefono.setName("TELÉFONO");
        jtxaDireccion.setName("DIRECCIÓN");
    }

    private void textosPorDefecto(JComponent campo) {
        ArrayList<JComponent> formulario = new ArrayList<>(Arrays.asList(jtxtNombre, jtxtApellido, jtxtCedula, jtxtTelefono, jtxaDireccion));
        if (campo instanceof JTextField) {
            if (((JTextField) campo).getText().equals(campo.getName())) {
                ((JTextField) campo).setText("");
                campo.setForeground(Color.black);
            }
        } else {
            if (((JTextArea) campo).getText().equals(campo.getName())) {
                ((JTextArea) campo).setText("");
                campo.setForeground(Color.black);
            }
        }
        formulario.remove(campo);
        for (JComponent campoF : formulario) {
            if (campoF instanceof JTextField) {
                if (((JTextField) campoF).getText().isEmpty()) {
                    ((JTextField) campoF).setText(campoF.getName());
                    campoF.setForeground(colorDefecto);
                }
            } else {
                if (((JTextArea) campoF).getText().isEmpty()) {
                    ((JTextArea) campoF).setText(campoF.getName());
                    campoF.setForeground(colorDefecto);
                }
            } 
        }
    }

    public boolean validarLongitudMinima(JTextField campo, int longitudMinima) {
        if (campo.getText().length() < longitudMinima) {
            campo.setForeground(colorError);
            return false;
        } else {
            campo.setForeground(Color.black);
            return true;
        }
    }

    private void validarTelefono() {
        if (!Validacion.validarTelefono(jtxtTelefono.getText())) {
            jtxtTelefono.setForeground(colorError);
        } else {
            jtxtTelefono.setForeground(Color.black);
        }
        if (jtxtTelefono.getText().isEmpty()) {
            jtxtTelefono.setForeground(colorDefecto);
        }
    }

    private void validarDireccion() {
        if (jtxaDireccion.getText().isEmpty()) {
            jtxaDireccion.setForeground(colorDefecto);
        } else {
            jtxaDireccion.setForeground(Color.black);
        }
    }

    private void bloquearEspaciados(java.awt.event.KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (validar == '\n' || validar == '\t') {
            String aux = jtxaDireccion.getText().trim();
            jtxaDireccion.setText(aux);
        }
    }

    private void validarCedula() {
        if (!Validacion.validarCedula(jtxtCedula.getText())) {
            jtxtCedula.setForeground(colorError);
        } else {
            jtxtCedula.setForeground(Color.black);
        }
    }

    private void definirEstadoBoton() {
        ArrayList<JComponent> formulario = new ArrayList<>(Arrays.asList(jtxtNombre, jtxtApellido, jtxtTelefono, jtxtCedula, jtxaDireccion));
        for (JComponent campoF : formulario) {
            if (campoF.getForeground().equals(colorError) || campoF.getForeground().equals(colorDefecto)) {
                jtxtCrear.setEnabled(false);
                return;
            }
        }
        jtxtCrear.setEnabled(true);
    }

    private boolean verificarCedula() {
        if (cedulaRegistrada()) {
            JOptionPane.showMessageDialog(this, "Cédula ya registrada", "Error", 0);
            jtxtCedula.requestFocus();
            jtxtCedula.setForeground(colorError);
            return false;
        }
        return true;
    }

    private boolean cedulaRegistrada() {
        RequestBody requestBody = new FormBody.Builder().add("EST_CEDULA", jtxtCedula.getText()).build();
        JSONObject response = cliente.postJSON("https://proyectosuta.000webhostapp.com/aos_proyecto/models2/buscarId.php", requestBody);
        return !((JSONArray) response.get("estudiantes")).isEmpty();
    }

    private void crearEstudiante() {
        RequestBody requestBody = new FormBody.Builder().add("EST_CEDULA", jtxtCedula.getText())
                .add("EST_NOMBRE", jtxtNombre.getText()).add("EST_APELLIDO", jtxtApellido.getText())
                .add("EST_TELEFONO", jtxtTelefono.getText()).add("EST_DIRECCION", jtxaDireccion.getText()).build();
        JSONObject response = cliente.postJSON("https://proyectosuta.000webhostapp.com/aos_proyecto/models2/guardar.php", requestBody);
        boolean verificar = response.getBoolean("ok");
        if (verificar) {
            JOptionPane.showMessageDialog(this, "Se registró correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "No se registró el estudiante");
        }
    }

    private void restablecerCampos() {
        ArrayList<JComponent> formulario = new ArrayList<>(Arrays.asList(jtxtNombre, jtxtApellido, jtxtTelefono, jtxtCedula, jtxaDireccion));
        for (JComponent campoF : formulario) {
            if (campoF instanceof JTextField) {
                ((JTextField) campoF).setText(campoF.getName());
            } else {
                ((JTextArea)campoF).setText(campoF.getName());
            }
            campoF.setForeground(colorDefecto);
        }
        this.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblTitulo = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jtxtCedula = new javax.swing.JTextField();
        jtxtTelefono = new javax.swing.JTextField();
        jpnlCrear = new javax.swing.JPanel();
        jtxtCrear = new javax.swing.JLabel();
        jlblBoton = new javax.swing.JLabel();
        jtxaDireccion = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(415, 390));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitulo.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jlblTitulo.setForeground(new java.awt.Color(171, 26, 32));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("INGRESAR ESTUDIANTE");
        add(jlblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 563, 40));

        jtxtNombre.setFont(new java.awt.Font("Poppins", 0, 21)); // NOI18N
        jtxtNombre.setForeground(new java.awt.Color(204, 204, 204));
        jtxtNombre.setText("NOMBRE");
        jtxtNombre.setBorder(null);
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
        add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 266, 50));

        jtxtApellido.setFont(new java.awt.Font("Poppins", 0, 21)); // NOI18N
        jtxtApellido.setForeground(new java.awt.Color(204, 204, 204));
        jtxtApellido.setText("APELLIDO");
        jtxtApellido.setBorder(null);
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
        add(jtxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 267, 50));

        jtxtCedula.setFont(new java.awt.Font("Poppins", 0, 21)); // NOI18N
        jtxtCedula.setForeground(new java.awt.Color(204, 204, 204));
        jtxtCedula.setText("CÉDULA");
        jtxtCedula.setBorder(null);
        jtxtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtCedulaFocusLost(evt);
            }
        });
        jtxtCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtCedulaMousePressed(evt);
            }
        });
        jtxtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtCedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtCedulaKeyTyped(evt);
            }
        });
        add(jtxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 266, 50));

        jtxtTelefono.setFont(new java.awt.Font("Poppins", 0, 21)); // NOI18N
        jtxtTelefono.setForeground(new java.awt.Color(204, 204, 204));
        jtxtTelefono.setText("TELÉFONO");
        jtxtTelefono.setBorder(null);
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
        add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 267, 50));

        jpnlCrear.setBackground(new java.awt.Color(255, 255, 255));
        jpnlCrear.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtCrear.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jtxtCrear.setForeground(new java.awt.Color(255, 255, 255));
        jtxtCrear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtCrear.setText("CREAR");
        jtxtCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtxtCrear.setEnabled(false);
        jtxtCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtCrearMouseClicked(evt);
            }
        });
        jpnlCrear.add(jtxtCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 180, 45));

        jlblBoton.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jlblBoton.setForeground(new java.awt.Color(255, 255, 255));
        jlblBoton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/button_180_45.png"))); // NOI18N
        jlblBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnlCrear.add(jlblBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 45));

        add(jpnlCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 510, 180, 45));

        jtxaDireccion.setColumns(20);
        jtxaDireccion.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jtxaDireccion.setForeground(new java.awt.Color(204, 204, 204));
        jtxaDireccion.setRows(5);
        jtxaDireccion.setText("DIRECCIÓN");
        jtxaDireccion.setLineWrap(true);
        jtxaDireccion.setWrapStyleWord(true);
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
        add(jtxaDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 300, 120));
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtNombreMousePressed
        textosPorDefecto(jtxtNombre);
    }//GEN-LAST:event_jtxtNombreMousePressed

    private void jtxtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNombreKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxtNombreKeyPressed

    private void jtxtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNombreKeyReleased
        validarLongitudMinima(jtxtNombre, 2);
        definirEstadoBoton();
    }//GEN-LAST:event_jtxtNombreKeyReleased

    private void jtxtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNombreKeyTyped
        Validacion.convertirMayuscula(evt);
        Validacion.validarNLetras(evt, jtxtNombre, 25);
    }//GEN-LAST:event_jtxtNombreKeyTyped

    private void jtxtApellidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtApellidoMousePressed
        textosPorDefecto(jtxtApellido);
    }//GEN-LAST:event_jtxtApellidoMousePressed

    private void jtxtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtApellidoKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxtApellidoKeyPressed

    private void jtxtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtApellidoKeyReleased
        validarLongitudMinima(jtxtApellido, 2);
        definirEstadoBoton();
    }//GEN-LAST:event_jtxtApellidoKeyReleased

    private void jtxtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtApellidoKeyTyped
        Validacion.convertirMayuscula(evt);
        Validacion.validarNLetras(evt, jtxtApellido, 25);
    }//GEN-LAST:event_jtxtApellidoKeyTyped

    private void jtxtCedulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtCedulaMousePressed
        textosPorDefecto(jtxtCedula);
    }//GEN-LAST:event_jtxtCedulaMousePressed

    private void jtxtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCedulaKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxtCedulaKeyPressed

    private void jtxtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCedulaKeyReleased
        validarCedula();
        definirEstadoBoton();
    }//GEN-LAST:event_jtxtCedulaKeyReleased

    private void jtxtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCedulaKeyTyped
        Validacion.validarNNumeros(evt, jtxtCedula, 10);
    }//GEN-LAST:event_jtxtCedulaKeyTyped

    private void jtxtTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtTelefonoMousePressed
        textosPorDefecto(jtxtTelefono);
    }//GEN-LAST:event_jtxtTelefonoMousePressed

    private void jtxtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTelefonoKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxtTelefonoKeyPressed

    private void jtxtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTelefonoKeyReleased
        validarTelefono();
        definirEstadoBoton();
    }//GEN-LAST:event_jtxtTelefonoKeyReleased

    private void jtxtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTelefonoKeyTyped
        Validacion.validarNNumeros(evt, jtxtTelefono, 10);
    }//GEN-LAST:event_jtxtTelefonoKeyTyped

    private void jtxtCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtCrearMouseClicked
        if (jtxtCrear.isEnabled()) {
            crearEstudiante();
            restablecerCampos();
        }
    }//GEN-LAST:event_jtxtCrearMouseClicked

    private void jtxaDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxaDireccionMousePressed
        textosPorDefecto(jtxaDireccion);
    }//GEN-LAST:event_jtxaDireccionMousePressed

    private void jtxaDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxaDireccionKeyPressed
        Validacion.bloquearCopyPaste(evt);
    }//GEN-LAST:event_jtxaDireccionKeyPressed

    private void jtxaDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxaDireccionKeyReleased
        validarDireccion();
        definirEstadoBoton();
    }//GEN-LAST:event_jtxaDireccionKeyReleased

    private void jtxaDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxaDireccionKeyTyped
        bloquearEspaciados(evt);
        Validacion.validarNCaracteres(evt, jtxaDireccion, 100);
    }//GEN-LAST:event_jtxaDireccionKeyTyped

    private void jtxtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtCedulaFocusLost
        verificarCedula();
    }//GEN-LAST:event_jtxtCedulaFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlblBoton;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JPanel jpnlCrear;
    private javax.swing.JTextArea jtxaDireccion;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JLabel jtxtCrear;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtTelefono;
    // End of variables declaration//GEN-END:variables
}
