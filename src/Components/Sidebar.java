package Components;

import Events.SidebarMouseListener;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONException;
//import pages_content.DashboardPage;
import java.util.prefs.Preferences;
import java.util.logging.Logger;
import java.util.logging.Level;
//import pages_content.login;

public class Sidebar extends javax.swing.JPanel {

    private SidebarMouseListener sidebarMouseListener;
    private int top = 3, left = 3, bottom = 3, right = 3;
    private Insets i = new Insets(top, left, bottom, right);
//     Preferences userPreferences = Preferences.userNodeForPackage(DashboardPage.class);
    private Object[][] menu_list_data_owner = {
        {"icf_home.svg", "icn_home.svg", "Home", true},
        {"icf_obat.svg", "icn_obat.svg", "Medicine", false},
        {"icf_supplier.svg", "icn_supplier.svg", "Supplier", false},
        {"icf_kategori.svg", "icn_kategori.svg", "Category", false},
        {"icf_trx.svg", "icn_trx.svg", "Transaction", false},
        {"icf_report.svg", "icn_report.svg", "Report", false},
        {"icf_user.svg", "icn_user.svg", "User", false},
            {"icf_pengeluaran.svg", "icn_pengeluaran.svg", "Pengeluaran", false}};

    private Object[][] menu_list_data_kasir = {
        {"icf_home.svg", "icn_home.svg", "Home", true},
        {"icf_obat.svg", "icn_obat.svg", "Medicine", false},
        {"icf_supplier.svg", "icn_supplier.svg", "Supplier", false},
        {"icf_kategori.svg", "icn_kategori.svg", "Category", false},
        {"icf_trx.svg", "icn_trx.svg", "Transaction", false},
    };
 private Object[][] menu_list_data;

    private int indexActive = 0;
    private int prevIndex = indexActive;
    private int topPadding = 10;
    private int leftPadding = 4;
    private int bottomPadding = 10;
    private int rightPadding = 10;
    ArrayList<JPanel> panels = new ArrayList<>();
    ArrayList<JLabel> labels = new ArrayList<>();
    ArrayList<JLabel> iconLabel = new ArrayList<>();
    ArrayList<FlatSVGIcon> defaultIcons = new ArrayList<>();
    ArrayList<FlatSVGIcon> clickedIcons = new ArrayList<>();

    public Sidebar() {
//        try {
//
////            String datalogin = userPreferences.get("localLogin", null);
//           
////            if (datalogin != null) {
//                           
////                JSONArray retrievedArray = new JSONArray(datalogin);
////                System.out.println(retrievedArray.getString(1));
////                menu_list_data=(retrievedArray.getString(3).equalsIgnoreCase("owner"))?menu_list_data_owner:menu_list_data_kasir;
////            }else{
////                new login().setVisible(true);
////            }
//        } catch (JSONException ex) {
////            Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.iconLabel = new ArrayList<JPanel>();
        initComponents();
        setOpaque(false);
        menu_list.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

//        defaultIcons = new FlatSVGIcon[menu_list_data.length];
//        clickedIcons = new FlatSVGIcon[menu_list_data.length];
        for (int i = 0; i < menu_list_data.length; i++) {
            final int index = i;
            JPanel mainPanel = new JPanel();

            defaultIcons.add(new FlatSVGIcon("Assets/ic_header/" + menu_list_data[i][1]));
            clickedIcons.add(new FlatSVGIcon("Assets/ic_header/" + menu_list_data[i][0]));

            mainPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
            mainPanel.setBackground(Color.LIGHT_GRAY);
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
            // Add a JLabel for the left position
            JLabel label = new JLabel((String) menu_list_data[i][2]);
            JLabel leftLabel = new JLabel(defaultIcons.get(i));
            leftLabel.setIcon(defaultIcons.get(i));
            label.setFont(new Font("Poppins", Font.PLAIN, 16));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setBounds(100, 50, 200, 50);
            leftLabel.setBounds(10, 50, 80, 50);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));

            if ((boolean) menu_list_data[i][3]) {
                label.setForeground(Color.WHITE);
                mainPanel.setBackground(Color.decode("#3762d8"));
                System.out.println(clickedIcons.get(i));
                leftLabel.setIcon(clickedIcons.get(i));

            } else {
                label.setForeground(Color.decode("#3762d8"));
                mainPanel.setBackground(Color.decode("#eeeeee"));
                leftLabel.setIcon(defaultIcons.get(i));

            }

            mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    updateFocus(panels, labels, iconLabel, index, evt);
                }
            });
            panels.add(mainPanel);
            labels.add(label);
            iconLabel.add(leftLabel);
            menu_list.add(Box.createRigidArea(new Dimension(10, 10)));
            mainPanel.add(leftLabel);
            mainPanel.add(label);
            menu_list.add(mainPanel);
        }

    }

    private void updateFocus(ArrayList<JPanel> panels, ArrayList<JLabel> labels, ArrayList<JLabel> leftLabel, int focusedIndex, MouseEvent evt) {
if(focusedIndex==prevIndex)return;
        labels.get(focusedIndex).setForeground(Color.WHITE);
        panels.get(focusedIndex).setBackground(Color.decode("#3762d8"));
        leftLabel.get(focusedIndex).setIcon(clickedIcons.get(focusedIndex));
        labels.get(prevIndex).setForeground(Color.decode("#3762d8"));
        panels.get(prevIndex).setBackground(Color.decode("#eeeeee"));
        leftLabel.get(prevIndex).setIcon(defaultIcons.get(prevIndex));
        indexActive = focusedIndex;
        prevIndex = indexActive;
     
        if (sidebarMouseListener != null) {
            sidebarMouseListener.sidebarMouseClicked(evt);
        }

    }

    public void setSidebarMouseListener(SidebarMouseListener sidebarMouseListener) {
        this.sidebarMouseListener = sidebarMouseListener;
    }

    public int getInfo() {
        return indexActive;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menu_list = new javax.swing.JPanel();
        ic_profil = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("APOTEKER");

        menu_list.setBackground(new java.awt.Color(255, 255, 255));
        menu_list.setAutoscrolls(true);
        menu_list.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_list.setLayout(new javax.swing.BoxLayout(menu_list, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Akbar");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Owner");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_list, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(ic_profil, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ic_profil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ic_profil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel menu_list;
    // End of variables declaration//GEN-END:variables
}
