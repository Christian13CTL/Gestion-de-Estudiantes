package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelPrincipal extends javax.swing.JFrame {

    int xMouse, yMouse;
    Cursor cursorDefecto, cursorMano;
    Color colorSeleccion, colorDefecto;

    public PanelPrincipal() {
        initComponents();
        cursorDefecto = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
        cursorMano = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
        colorSeleccion = new Color(214, 15, 15);
        colorDefecto = new Color(255, 51, 51);
        this.setLocationRelativeTo(null);
        establecerFecha();
        redefinirPanelContenido(new Principal());
    }

    private void establecerFecha() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int dia = now.getDayOfMonth();
        int month = now.getMonthValue();
        String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
        jtxtFecha.setText(dia + " DE " + meses[month - 1] + " DE " + year);
    }

    private void cambiarColor(JPanel panel) {
        panel.setBackground(colorSeleccion);
    }

    private void restablecerColor(JPanel panel) {
        panel.setBackground(colorDefecto);
    }

    private void redefinirFondosMenu(JPanel panel) {
        ArrayList<JPanel> subMenu = new ArrayList<>(Arrays.asList(jpnlSMPrincipal, jpnlSMRegistrar));
        cambiarColor(panel);
        panel.setCursor(cursorDefecto);
        subMenu.remove(panel);
        for (JPanel item : subMenu) {
            restablecerColor(item);
            item.setCursor(cursorMano);
        }
    }

    protected static void redefinirPanelContenido(Component contenido) {
        contenido.setBounds(0, 0, 623, 585);
        jpnlContenido.removeAll();
        jpnlContenido.add(contenido, BorderLayout.CENTER);
        jpnlContenido.revalidate();
        jpnlContenido.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        jpnlSMPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jpnlSMRegistrar = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jpnlSMCerrarSesión = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlblSubtitulo = new javax.swing.JLabel();
        jlblFondoMenu = new javax.swing.JLabel();
        Cabecera = new javax.swing.JPanel();
        jpnlSalir = new javax.swing.JPanel();
        jtxtSalir = new javax.swing.JLabel();
        jpnlContenido = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jtxtFecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(255, 255, 255));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnlSMPrincipal.setBackground(new java.awt.Color(214, 15, 15));
        jpnlSMPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnlSMPrincipal.setPreferredSize(new java.awt.Dimension(150, 50));
        jpnlSMPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlSMPrincipalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnlSMPrincipalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnlSMPrincipalMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnlSMPrincipalMousePressed(evt);
            }
        });
        jpnlSMPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpnlSMPrincipal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 45, 45));

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 21)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PRINCIPAL");
        jpnlSMPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 15, 187, 45));

        Menu.add(jpnlSMPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 225, 277, 75));

        jpnlSMRegistrar.setBackground(new java.awt.Color(255, 51, 51));
        jpnlSMRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnlSMRegistrar.setPreferredSize(new java.awt.Dimension(150, 50));
        jpnlSMRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlSMRegistrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnlSMRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnlSMRegistrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnlSMRegistrarMousePressed(evt);
            }
        });
        jpnlSMRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpnlSMRegistrar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 45, 45));

        jLabel14.setFont(new java.awt.Font("Roboto Light", 1, 21)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("REGISTRAR");
        jpnlSMRegistrar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 15, 187, 45));

        Menu.add(jpnlSMRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 277, 75));

        jpnlSMCerrarSesión.setBackground(new java.awt.Color(255, 51, 51));
        jpnlSMCerrarSesión.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnlSMCerrarSesión.setPreferredSize(new java.awt.Dimension(150, 50));
        jpnlSMCerrarSesión.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlSMCerrarSesiónMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnlSMCerrarSesiónMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnlSMCerrarSesiónMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnlSMCerrarSesiónMousePressed(evt);
            }
        });
        jpnlSMCerrarSesión.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpnlSMCerrarSesión.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 45, 45));

        jLabel10.setFont(new java.awt.Font("Roboto Light", 1, 21)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("CERRAR SESIÓN");
        jpnlSMCerrarSesión.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 15, 187, 45));

        Menu.add(jpnlSMCerrarSesión, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 562, 277, 75));

        jlblTitulo.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jlblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jlblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitulo.setText("IDK");
        Menu.add(jlblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 277, 45));

        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 5));
        Menu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 127, 277, 15));

        jlblSubtitulo.setFont(new java.awt.Font("Poppins SemiBold", 0, 22)); // NOI18N
        jlblSubtitulo.setForeground(new java.awt.Color(255, 255, 255));
        jlblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSubtitulo.setText("TECHNOLOGIES");
        Menu.add(jlblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 135, 277, 45));

        jlblFondoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menuLateral_277_675.png"))); // NOI18N
        jlblFondoMenu.setOpaque(true);
        Menu.add(jlblFondoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 277, 675));

        Fondo.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 277, 675));

        Cabecera.setBackground(new java.awt.Color(255, 255, 255));
        Cabecera.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                CabeceraMouseDragged(evt);
            }
        });
        Cabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CabeceraMousePressed(evt);
            }
        });

        jpnlSalir.setBackground(new java.awt.Color(255, 255, 255));
        jpnlSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnlSalir.setPreferredSize(new java.awt.Dimension(45, 45));

        jtxtSalir.setFont(new java.awt.Font("Rubik Light", 0, 21)); // NOI18N
        jtxtSalir.setForeground(new java.awt.Color(54, 58, 64));
        jtxtSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtSalir.setText("X");
        jtxtSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtxtSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtxtSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtxtSalirMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpnlSalirLayout = new javax.swing.GroupLayout(jpnlSalir);
        jpnlSalir.setLayout(jpnlSalirLayout);
        jpnlSalirLayout.setHorizontalGroup(
            jpnlSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtxtSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jpnlSalirLayout.setVerticalGroup(
            jpnlSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtxtSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout CabeceraLayout = new javax.swing.GroupLayout(Cabecera);
        Cabecera.setLayout(CabeceraLayout);
        CabeceraLayout.setHorizontalGroup(
            CabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CabeceraLayout.createSequentialGroup()
                .addGap(0, 855, Short.MAX_VALUE)
                .addComponent(jpnlSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        CabeceraLayout.setVerticalGroup(
            CabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Fondo.add(Cabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 45));

        jpnlContenido.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnlContenidoLayout = new javax.swing.GroupLayout(jpnlContenido);
        jpnlContenido.setLayout(jpnlContenidoLayout);
        jpnlContenidoLayout.setHorizontalGroup(
            jpnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
        jpnlContenidoLayout.setVerticalGroup(
            jpnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );

        Fondo.add(jpnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 90, 623, 585));

        jPanel1.setPreferredSize(new java.awt.Dimension(415, 30));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtFecha.setFont(new java.awt.Font("Roboto Medium", 0, 21)); // NOI18N
        jtxtFecha.setForeground(new java.awt.Color(255, 255, 255));
        jtxtFecha.setText("Fecha");
        jPanel1.add(jtxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 0, 300, 45));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo_623_45.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 623, 45));

        Fondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 45, 623, 45));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void CabeceraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CabeceraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_CabeceraMouseDragged

    private void CabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CabeceraMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_CabeceraMousePressed

    private void jpnlSMPrincipalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMPrincipalMousePressed
        redefinirFondosMenu(jpnlSMPrincipal);
        redefinirPanelContenido(new Principal());
    }//GEN-LAST:event_jpnlSMPrincipalMousePressed

    private void jpnlSMRegistrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMRegistrarMousePressed
        redefinirFondosMenu(jpnlSMRegistrar);
        redefinirPanelContenido(new CrearEstudiante());
    }//GEN-LAST:event_jpnlSMRegistrarMousePressed

    private void jpnlSMRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMRegistrarMouseEntered
        if (jpnlSMRegistrar.getBackground().getRGB() == colorDefecto.getRGB()) {
            cambiarColor(jpnlSMRegistrar);
        }
    }//GEN-LAST:event_jpnlSMRegistrarMouseEntered

    private void jpnlSMRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMRegistrarMouseExited
        if (jpnlSMPrincipal.getBackground().getRGB() != colorDefecto.getRGB()) {
            restablecerColor(jpnlSMRegistrar);
        }
    }//GEN-LAST:event_jpnlSMRegistrarMouseExited

    private void jpnlSMPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMPrincipalMouseEntered
        if (jpnlSMPrincipal.getBackground().getRGB() == colorDefecto.getRGB()) {
            cambiarColor(jpnlSMPrincipal);
        }
    }//GEN-LAST:event_jpnlSMPrincipalMouseEntered

    private void jpnlSMPrincipalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMPrincipalMouseExited
        if (jpnlSMRegistrar.getBackground().getRGB() != colorDefecto.getRGB()) {
            restablecerColor(jpnlSMPrincipal);
        }
    }//GEN-LAST:event_jpnlSMPrincipalMouseExited

    private void jtxtSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtSalirMouseEntered
        jpnlSalir.setBackground(new Color(147, 27, 23));
        jtxtSalir.setForeground(Color.white);
    }//GEN-LAST:event_jtxtSalirMouseEntered

    private void jtxtSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtSalirMouseExited
        jpnlSalir.setBackground(Color.white);
        jtxtSalir.setForeground(new Color(54, 58, 64));
    }//GEN-LAST:event_jtxtSalirMouseExited

    private void jtxtSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jtxtSalirMouseClicked

    private void jpnlSMCerrarSesiónMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMCerrarSesiónMouseEntered
        if (jpnlSMCerrarSesión.getBackground().getRGB() == colorDefecto.getRGB()) {
            cambiarColor(jpnlSMCerrarSesión);
        }
    }//GEN-LAST:event_jpnlSMCerrarSesiónMouseEntered

    private void jpnlSMCerrarSesiónMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMCerrarSesiónMouseExited
        if (jpnlSMCerrarSesión.getBackground().getRGB() != colorDefecto.getRGB() || jpnlSMPrincipal.getBackground().getRGB() != colorDefecto.getRGB()) {
            restablecerColor(jpnlSMCerrarSesión);
        }
    }//GEN-LAST:event_jpnlSMCerrarSesiónMouseExited

    private void jpnlSMCerrarSesiónMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMCerrarSesiónMousePressed
        if (JOptionPane.showConfirmDialog(new JInternalFrame(), "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                this.dispose();
                Login login = new Login();
                login.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_jpnlSMCerrarSesiónMousePressed

    private void jpnlSMPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMPrincipalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnlSMPrincipalMouseClicked

    private void jpnlSMRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMRegistrarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnlSMRegistrarMouseClicked

    private void jpnlSMCerrarSesiónMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlSMCerrarSesiónMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnlSMCerrarSesiónMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cabecera;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlblFondoMenu;
    private javax.swing.JLabel jlblSubtitulo;
    private javax.swing.JLabel jlblTitulo;
    public static javax.swing.JPanel jpnlContenido;
    private javax.swing.JPanel jpnlSMCerrarSesión;
    private javax.swing.JPanel jpnlSMPrincipal;
    private javax.swing.JPanel jpnlSMRegistrar;
    private javax.swing.JPanel jpnlSalir;
    private javax.swing.JLabel jtxtFecha;
    private javax.swing.JLabel jtxtSalir;
    // End of variables declaration//GEN-END:variables
}
