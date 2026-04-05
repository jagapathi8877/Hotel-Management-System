/*
 * ============================================================
 *  HOTEL MANAGEMENT SYSTEM v2.0 - Enterprise Desktop Application
 *  Single-File Java Swing + MySQL JDBC Application
 *  Premium Dark Luxury Theme | Role-Based Access | Audit Logs
 *  UPI Payments | Charts | Export | Global Search
 * ============================================================
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.print.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Date;
import java.io.*;

public class HotelManagementSystem {

    // ═══════════════════════════════════════════════════════════════
    //  DATABASE CONFIGURATION
    // ═══════════════════════════════════════════════════════════════
    static final String DB_URL  = "jdbc:mysql://localhost:3306/hotel_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASS = "ASDFGHJKL";
    static final double GST_RATE = 0.18;
    // [MODIFIED] Hotel name updated to NOVOTEL
    static final String HOTEL_NAME = "NOVOTEL";
    static final String HOTEL_ADDRESS = "123 Luxury Avenue, Mumbai, India";
    static final String HOTEL_PHONE = "+91-22-12345678";
    static final String HOTEL_UPI_ID = "8332828877@ybl";

    // ═══════════════════════════════════════════════════════════════
    //  ROYAL MIDNIGHT LUXURY PALETTE
    // ═══════════════════════════════════════════════════════════════
    static boolean darkMode = true;

    // Core Surfaces
    static Color BG_DARK         = new Color(11, 18, 32);    // #0B1220 Deep midnight blue
    static Color BG_DARKER       = new Color(8, 12, 24);     // Deeper variant
    static Color SIDEBAR_BG_CLR  = new Color(22, 33, 62);    // #16213E Secondary panels
    static Color CARD_BG_CLR     = new Color(17, 26, 46);    // #111A2E Primary cards
    static Color INPUT_BG_CLR    = new Color(15, 23, 42);    // #0F172A Input backgrounds
    static Color BORDER_CLR      = new Color(30, 42, 68);    // Subtle, not harsh

    // Brand & Accents
    static Color ACCENT          = new Color(58, 111, 248);  // #3A6FF8 Royal Blue
    static Color ACCENT_HOVER    = new Color(78, 130, 255);  // Lighter on hover
    static Color ACCENT_PRESS    = new Color(42, 90, 220);   // Darker on press
    static Color ACCENT_GLOW     = new Color(120, 165, 255); // Soft glow
    static Color GOLD            = new Color(200, 169, 81);  // #C8A951 Muted Gold
    static Color GOLD_DIM        = new Color(160, 135, 65);  // Dimmed gold

    // Semantic Colors
    static Color SUCCESS_CLR     = new Color(46, 204, 113);  // #2ECC71
    static Color SUCCESS_BG      = new Color(16, 52, 36);
    static Color WARNING_CLR     = new Color(244, 185, 66);  // #F4B942
    static Color WARNING_BG      = new Color(52, 44, 16);
    static Color DANGER_CLR      = new Color(231, 76, 60);   // #E74C3C
    static Color DANGER_BG       = new Color(52, 18, 16);
    static Color INFO_CLR        = new Color(93, 169, 233);  // #5DA9E9
    static Color PURPLE_CLR      = new Color(168, 85, 247);

    // Text Colors
    static Color TXT_PRIMARY     = new Color(230, 234, 242); // #E6EAF2
    static Color TXT_SECONDARY   = new Color(169, 177, 199); // #A9B1C7
    static Color TXT_MUTED       = new Color(125, 134, 156); // #7D869C

    // Table Colors
    static Color TABLE_ROW_CLR   = new Color(17, 26, 46);
    static Color TABLE_ALT_CLR   = new Color(22, 33, 56);
    static Color TABLE_HOVER_CLR = new Color(30, 44, 72);
    static Color TABLE_HDR_CLR   = new Color(13, 20, 38);

    // LIGHT THEME
    static Color L_BG            = new Color(241, 245, 249);
    static Color L_BG_DARKER     = new Color(226, 232, 240);
    static Color L_SIDEBAR       = new Color(255, 255, 255);
    static Color L_CARD          = new Color(255, 255, 255);
    static Color L_INPUT         = new Color(241, 245, 249);
    static Color L_BORDER        = new Color(203, 213, 225);
    static Color L_TXT_PRIMARY   = new Color(15, 23, 42);
    static Color L_TXT_SECONDARY = new Color(71, 85, 105);
    static Color L_TXT_MUTED     = new Color(148, 163, 184);
    static Color L_TABLE_ROW     = new Color(255, 255, 255);
    static Color L_TABLE_ALT     = new Color(248, 250, 252);
    static Color L_TABLE_HOVER   = new Color(226, 232, 240);
    static Color L_TABLE_HDR     = new Color(241, 245, 249);

    // Theme accessor methods
    static Color bg()       { return darkMode ? BG_DARK : L_BG; }
    static Color bgDarker() { return darkMode ? BG_DARKER : L_BG_DARKER; }
    static Color sidebarBg(){ return darkMode ? SIDEBAR_BG_CLR : L_SIDEBAR; }
    static Color cardBg()   { return darkMode ? CARD_BG_CLR : L_CARD; }
    static Color inputBg()  { return darkMode ? INPUT_BG_CLR : L_INPUT; }
    static Color border()   { return darkMode ? BORDER_CLR : L_BORDER; }
    static Color txtP()     { return darkMode ? TXT_PRIMARY : L_TXT_PRIMARY; }
    static Color txtS()     { return darkMode ? TXT_SECONDARY : L_TXT_SECONDARY; }
    static Color txtM()     { return darkMode ? TXT_MUTED : L_TXT_MUTED; }
    static Color tblRow()   { return darkMode ? TABLE_ROW_CLR : L_TABLE_ROW; }
    static Color tblAlt()   { return darkMode ? TABLE_ALT_CLR : L_TABLE_ALT; }
    static Color tblHov()   { return darkMode ? TABLE_HOVER_CLR : L_TABLE_HOVER; }
    static Color tblHdr()   { return darkMode ? TABLE_HDR_CLR : L_TABLE_HDR; }
    static Color dialogBg() { return darkMode ? BG_DARKER : L_CARD; }

    // ═══════════════════════════════════════════════════════════════
    //  FONTS
    // ═══════════════════════════════════════════════════════════════
    static final Font F_TITLE     = new Font("Segoe UI", Font.BOLD, 26);
    static final Font F_HEADER    = new Font("Segoe UI", Font.BOLD, 18);
    static final Font F_SUBHEADER = new Font("Segoe UI", Font.BOLD, 15);
    static final Font F_BODY      = new Font("Segoe UI", Font.PLAIN, 14);
    static final Font F_SMALL     = new Font("Segoe UI", Font.PLAIN, 12);
    static final Font F_TINY      = new Font("Segoe UI", Font.PLAIN, 11);
    static final Font F_BUTTON    = new Font("Segoe UI", Font.BOLD, 13);
    static final Font F_SIDEBAR   = new Font("Segoe UI", Font.PLAIN, 14);
    static final Font F_BRAND     = new Font("Segoe UI", Font.BOLD, 20);
    static final Font F_INPUT     = new Font("Segoe UI", Font.PLAIN, 14);
    static final Font F_TABLE_HDR = new Font("Segoe UI", Font.BOLD, 12);
    static final Font F_TABLE     = new Font("Segoe UI", Font.PLAIN, 13);
    static final Font F_KPI_VAL   = new Font("Segoe UI", Font.BOLD, 32);
    static final Font F_KPI_LBL   = new Font("Segoe UI", Font.PLAIN, 13);
    static final Font F_BADGE     = new Font("Segoe UI", Font.BOLD, 11);

    // ═══════════════════════════════════════════════════════════════
    //  SESSION STATE
    // ═══════════════════════════════════════════════════════════════
    static int    loggedInUserId   = -1;
    static String loggedInUsername = "";
    static String loggedInRole     = "";
    static String loggedInFullName = "";
    static JFrame mainFrame;
    static String currentNav = "dashboard";
    static JPanel contentArea;
    static ArrayList<JPanel> navPanels = new ArrayList<>();
    static ArrayList<JLabel> navLabels = new ArrayList<>();

    // ═══════════════════════════════════════════════════════════════
    //  ENTRY POINT
    // ═══════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        applyDialogTheme();

        SwingUtilities.invokeLater(() -> {
            if (!testConnection()) {
                JOptionPane.showMessageDialog(null,
                    "Cannot connect to MySQL.\n\n" +
                    "1. Ensure MySQL is running on localhost:3306\n" +
                    "2. Run hotel_management.sql first\n" +
                    "3. Check DB_USER/DB_PASS in source code\n\n" +
                    "Current: " + DB_USER + " / " + (DB_PASS.isEmpty() ? "(empty)" : "****"),
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            showLoginScreen();
        });
    }

    static void applyDialogTheme() {
        UIManager.put("OptionPane.background", dialogBg());
        UIManager.put("Panel.background", dialogBg());
        UIManager.put("OptionPane.messageForeground", txtP());
        UIManager.put("OptionPane.messageFont", F_BODY);
        UIManager.put("Button.background", ACCENT);
        UIManager.put("Button.foreground", Color.WHITE);
        // Dialog/OptionPane comprehensive dark mode fixes
        UIManager.put("TextField.background", inputBg());
        UIManager.put("TextField.foreground", txtP());
        UIManager.put("TextField.caretForeground", txtP());
        UIManager.put("Label.foreground", txtP());
        UIManager.put("TextArea.background", inputBg());
        UIManager.put("TextArea.foreground", txtP());
        UIManager.put("ScrollPane.background", dialogBg());
        UIManager.put("Viewport.background", dialogBg());
        // ComboBox dark mode fixes
        UIManager.put("ComboBox.background", inputBg());
        UIManager.put("ComboBox.foreground", txtP());
        UIManager.put("ComboBox.selectionBackground", ACCENT);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.buttonBackground", inputBg());
        UIManager.put("ComboBox.buttonDarkShadow", border());
        UIManager.put("ComboBox.buttonHighlight", border());
        UIManager.put("ComboBox.buttonShadow", border());
        // List (dropdown popup) dark mode fixes
        UIManager.put("List.background", inputBg());
        UIManager.put("List.foreground", txtP());
        UIManager.put("List.selectionBackground", ACCENT);
        UIManager.put("List.selectionForeground", Color.WHITE);
        // TabbedPane dark mode fixes
        UIManager.put("TabbedPane.background", cardBg());
        UIManager.put("TabbedPane.foreground", txtP());
        UIManager.put("TabbedPane.selected", cardBg());
        UIManager.put("TabbedPane.contentAreaColor", cardBg());
        UIManager.put("TabbedPane.selectedForeground", ACCENT);
        UIManager.put("TabbedPane.unselectedBackground", inputBg());
        UIManager.put("TabbedPane.tabAreaBackground", cardBg());
        UIManager.put("TabbedPane.light", border());
        UIManager.put("TabbedPane.shadow", border());
        UIManager.put("TabbedPane.darkShadow", border());
        UIManager.put("TabbedPane.highlight", border());
        UIManager.put("TabbedPane.focus", ACCENT);
    }

    // ═══════════════════════════════════════════════════════════════
    //  DATABASE UTILITIES
    // ═══════════════════════════════════════════════════════════════
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    static boolean testConnection() {
        try (Connection c = getConnection()) { return c != null && !c.isClosed(); }
        catch (Exception e) { e.printStackTrace(); return false; }
    }

    static void logAudit(String action, String entityType, int entityId, String details) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO audit_logs (user_id, action, entity_type, entity_id, details) VALUES (?,?,?,?,?)")) {
            ps.setInt(1, loggedInUserId);
            ps.setString(2, action);
            ps.setString(3, entityType);
            ps.setInt(4, entityId);
            ps.setString(5, details);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    // ═══════════════════════════════════════════════════════════════
    //  UI COMPONENT FACTORIES
    // ═══════════════════════════════════════════════════════════════
    static JButton createBtn(String text, Color bg) {
        JButton b = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color fill = bg;
                if (getModel().isPressed()) fill = bg.darker();
                else if (getModel().isRollover()) fill = brighter(bg, 30);
                g2.setColor(fill);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setFont(F_BUTTON);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(140, 36));
        b.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        return b;
    }

    static Color brighter(Color c, int amt) {
        return new Color(Math.min(255, c.getRed()+amt), Math.min(255, c.getGreen()+amt), Math.min(255, c.getBlue()+amt));
    }

    static JTextField createField() {
        JTextField f = new JTextField() {
            @Override protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hasFocus() ? ACCENT : border());
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
            }
        };
        f.setFont(F_INPUT); f.setForeground(txtP()); f.setBackground(inputBg());
        f.setCaretColor(txtP());
        f.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        f.setPreferredSize(new Dimension(250, 38));
        return f;
    }

    static JPasswordField createPassField() {
        JPasswordField f = new JPasswordField() {
            @Override protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hasFocus() ? ACCENT : border());
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
            }
        };
        f.setFont(F_INPUT); f.setForeground(txtP()); f.setBackground(inputBg());
        f.setCaretColor(txtP());
        f.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        f.setPreferredSize(new Dimension(250, 38));
        return f;
    }

    static void styleCombo(JComboBox<String> cb) {
        cb.setFont(F_INPUT); cb.setForeground(txtP()); cb.setBackground(inputBg());
        cb.setPreferredSize(new Dimension(250, 38));
        cb.setOpaque(false);
        // Use BasicComboBoxUI so background/foreground are fully respected in dark mode
        cb.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override protected JButton createArrowButton() {
                JButton btn = new JButton("\u25BC");
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                btn.setBackground(inputBg()); btn.setForeground(txtS());
                btn.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, border()));
                btn.setFocusPainted(false);
                return btn;
            }
        });
        cb.setBorder(BorderFactory.createLineBorder(border()));
        cb.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object val, int idx, boolean sel, boolean foc) {
                super.getListCellRendererComponent(list, val, idx, sel, foc);
                list.setBackground(inputBg());
                setOpaque(true);
                setBackground(sel ? ACCENT : inputBg());
                setForeground(sel ? Color.WHITE : txtP());
                setFont(F_INPUT);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return this;
            }
        });
        // Also style the popup list directly
        Object comp = cb.getUI().getAccessibleChild(cb, 0);
        if (comp instanceof javax.swing.plaf.basic.ComboPopup) {
            JList<?> popupList = ((javax.swing.plaf.basic.ComboPopup) comp).getList();
            popupList.setBackground(inputBg());
            popupList.setForeground(txtP());
            popupList.setSelectionBackground(ACCENT);
            popupList.setSelectionForeground(Color.WHITE);
        }
    }

    static JComboBox<String> createCombo(String[] items) {
        JComboBox<String> cb = new JComboBox<>(items);
        styleCombo(cb);
        return cb;
    }

    static JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(F_BODY); l.setForeground(txtS());
        return l;
    }

    static JTextArea createTextArea(int rows, int cols) {
        JTextArea ta = new JTextArea(rows, cols);
        ta.setFont(F_INPUT); ta.setForeground(txtP()); ta.setBackground(inputBg());
        ta.setCaretColor(txtP());
        ta.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        ta.setLineWrap(true); ta.setWrapStyleWord(true);
        return ta;
    }

    static JPanel createFormRow(String label, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);
        JLabel lbl = createLabel(label);
        lbl.setPreferredSize(new Dimension(150, 36));
        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        row.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        return row;
    }

    static JPanel createCard(String title) {
        JPanel card = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(cardBg());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(border());
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 16, 16);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        if (title != null && !title.isEmpty()) {
            JLabel t = new JLabel(title);
            t.setFont(F_SUBHEADER); t.setForeground(txtP());
            t.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
            card.add(t, BorderLayout.NORTH);
        }
        return card;
    }

    static JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? tblRow() : tblAlt());
                } else {
                    c.setBackground(ACCENT);
                }
                c.setForeground(isRowSelected(row) ? Color.WHITE : txtP());
                if (c instanceof JComponent)
                    ((JComponent)c).setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return c;
            }
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table.setFont(F_TABLE); table.setForeground(txtP()); table.setBackground(tblRow());
        table.setSelectionBackground(ACCENT); table.setSelectionForeground(Color.WHITE);
        table.setGridColor(border()); table.setRowHeight(36);
        table.setShowGrid(false); table.setIntercellSpacing(new Dimension(0, 1));
        table.getTableHeader().setFont(F_TABLE_HDR);
        table.getTableHeader().setBackground(tblHdr());
        table.getTableHeader().setForeground(txtS());
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, border()));
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, s, f, r, c);
                l.setFont(F_TABLE_HDR); l.setForeground(txtS()); l.setBackground(tblHdr());
                l.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, border()),
                    BorderFactory.createEmptyBorder(0, 8, 0, 8)));
                return l;
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    static JScrollPane createScroll(Component view) {
        JScrollPane sp = new JScrollPane(view);
        sp.setBorder(BorderFactory.createLineBorder(border()));
        sp.getViewport().setBackground(bg());
        sp.setBackground(bg());
        sp.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override protected void configureScrollBarColors() {
                thumbColor = new Color(70, 80, 100); trackColor = bg();
            }
            @Override protected JButton createDecreaseButton(int o) { return zeroBtn(); }
            @Override protected JButton createIncreaseButton(int o) { return zeroBtn(); }
            private JButton zeroBtn() { JButton b=new JButton(); b.setPreferredSize(new Dimension(0,0)); return b; }
        });
        return sp;
    }

    // ═══════════════════════════════════════════════════════════════
    //  STATUS BADGE RENDERER
    // ═══════════════════════════════════════════════════════════════
    static JLabel createBadge(String text) {
        Color bgC, fgC;
        switch (text) {
            case "Available": bgC = SUCCESS_BG; fgC = SUCCESS_CLR; break;
            case "Booked": case "Checked-In": bgC = new Color(20, 40, 70); fgC = ACCENT; break;
            case "Reserved": bgC = WARNING_BG; fgC = WARNING_CLR; break;
            case "Under Maintenance": bgC = DANGER_BG; fgC = DANGER_CLR; break;
            case "Checked-Out": bgC = new Color(40, 25, 60); fgC = PURPLE_CLR; break;
            case "Cancelled": bgC = DANGER_BG; fgC = DANGER_CLR; break;
            case "VIP": bgC = WARNING_BG; fgC = WARNING_CLR; break;
            case "Returning": bgC = new Color(20, 40, 70); fgC = ACCENT; break;
            case "New": bgC = SUCCESS_BG; fgC = SUCCESS_CLR; break;
            default: bgC = cardBg(); fgC = txtS(); break;
        }
        JLabel badge = new JLabel(text, JLabel.CENTER) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgC);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setFont(F_BADGE); badge.setForeground(fgC);
        badge.setOpaque(false);
        badge.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        badge.setPreferredSize(new Dimension(120, 26));
        return badge;
    }

    static void showMsg(Component p, String msg, String title, int type) {
        applyDialogTheme();
        JOptionPane pane = new JOptionPane(msg, type);
        JDialog dlg = pane.createDialog(p, title);
        styleDialogComponents(dlg.getContentPane());
        dlg.setVisible(true);
    }

    static int showConfirm(Component p, String msg, String title) {
        applyDialogTheme();
        JOptionPane pane = new JOptionPane(msg, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        JDialog dlg = pane.createDialog(p, title);
        styleDialogComponents(dlg.getContentPane());
        dlg.setVisible(true);
        Object val = pane.getValue();
        if (val == null || val.equals(JOptionPane.UNINITIALIZED_VALUE)) return JOptionPane.CLOSED_OPTION;
        return (int) val;
    }

    static void styleDialogComponents(Container c) {
        c.setBackground(dialogBg());
        for (Component child : c.getComponents()) {
            if (child instanceof JPanel) {
                child.setBackground(dialogBg());
            }
            if (child instanceof JLabel) {
                child.setForeground(txtP());
                ((JComponent) child).setOpaque(false);
            }
            if (child instanceof JButton) {
                JButton btn = (JButton) child;
                btn.setBackground(ACCENT);
                btn.setForeground(Color.WHITE);
                btn.setFont(F_BUTTON);
                btn.setOpaque(true);
                btn.setFocusPainted(false);
            }
            if (child instanceof JScrollPane) {
                JScrollPane sp = (JScrollPane) child;
                sp.setBackground(dialogBg());
                sp.getViewport().setBackground(dialogBg());
                sp.setBorder(BorderFactory.createLineBorder(border()));
            }
            if (child instanceof JTextField) {
                child.setBackground(inputBg());
                child.setForeground(txtP());
                ((JTextField) child).setCaretColor(txtP());
            }
            if (child instanceof JTextArea) {
                child.setBackground(inputBg());
                child.setForeground(txtP());
                ((JTextArea) child).setCaretColor(txtP());
            }
            if (child instanceof JComboBox) {
                child.setBackground(inputBg());
                child.setForeground(txtP());
            }
            if (child instanceof Container) styleDialogComponents((Container) child);
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  LOGIN SCREEN — Royal Midnight Luxury
    // ═══════════════════════════════════════════════════════════════
    static void showLoginScreen() {
        if (mainFrame != null) mainFrame.dispose();

        // --- Colors specific to login ---
        final Color LOGIN_BG_LEFT  = new Color(11, 18, 32);   // #0B1220
        final Color LOGIN_BG_RIGHT = new Color(17, 26, 46);   // #111A2E card bg
        final Color LOGIN_CARD     = new Color(17, 26, 46);
        final Color LOGIN_INPUT_BG = new Color(15, 23, 42);   // #0F172A
        final Color LOGIN_ACCENT   = new Color(58, 111, 248); // #3A6FF8
        final Color LOGIN_GOLD     = new Color(200, 169, 81); // #C8A951
        final Color LOGIN_TXT      = new Color(230, 234, 242);
        final Color LOGIN_TXT2     = new Color(169, 177, 199);
        final Color LOGIN_TXT_M    = new Color(125, 134, 156);
        final Color LOGIN_ERR      = new Color(231, 76, 60);
        final Color LOGIN_BORDER_D = new Color(30, 42, 68);

        JFrame frame = new JFrame(HOTEL_NAME + " — Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1060, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // ────── Root split panel ──────
        JPanel root = new JPanel(new GridLayout(1, 2));
        root.setBackground(LOGIN_BG_LEFT);

        // ═══════════════════════════════════════════
        //  LEFT SECTION — Brand Experience (60%)
        // ═══════════════════════════════════════════
        JPanel leftPanel = new JPanel(new GridBagLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();

                // Deep gradient background
                g2.setPaint(new GradientPaint(0, 0, LOGIN_BG_LEFT, w, h, new Color(17, 26, 46)));
                g2.fillRect(0, 0, w, h);

                // Subtle geometric pattern (low-opacity architectural feel)
                g2.setColor(new Color(255, 255, 255, 6));
                for (int i = -h; i < w + h; i += 60) {
                    g2.drawLine(i, 0, i - h, h);
                }
                // Horizontal accent lines
                g2.setColor(new Color(LOGIN_GOLD.getRed(), LOGIN_GOLD.getGreen(), LOGIN_GOLD.getBlue(), 12));
                for (int y = 80; y < h; y += 120) {
                    g2.drawLine(40, y, w - 40, y);
                }

                // Soft radial glow behind logo area
                RadialGradientPaint rgp = new RadialGradientPaint(
                    new Point(w / 2, h / 2 - 30), Math.min(w, h) * 0.4f,
                    new float[]{0f, 1f},
                    new Color[]{new Color(58, 111, 248, 18), new Color(0, 0, 0, 0)}
                );
                g2.setPaint(rgp);
                g2.fillRect(0, 0, w, h);

                g2.dispose();
            }
        };
        leftPanel.setOpaque(false);

        JPanel brandContent = new JPanel();
        brandContent.setLayout(new BoxLayout(brandContent, BoxLayout.Y_AXIS));
        brandContent.setOpaque(false);

        // Hotel icon — elegant star / crown shape
        JLabel hotelIcon = new JLabel("\u2605", JLabel.CENTER) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Outer glow ring
                int cx = getWidth() / 2, cy = getHeight() / 2;
                g2.setColor(new Color(LOGIN_GOLD.getRed(), LOGIN_GOLD.getGreen(), LOGIN_GOLD.getBlue(), 30));
                g2.fillOval(cx - 44, cy - 44, 88, 88);
                g2.setColor(new Color(LOGIN_GOLD.getRed(), LOGIN_GOLD.getGreen(), LOGIN_GOLD.getBlue(), 50));
                g2.fillOval(cx - 36, cy - 36, 72, 72);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        hotelIcon.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        hotelIcon.setForeground(LOGIN_GOLD);
        hotelIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        hotelIcon.setPreferredSize(new Dimension(100, 100));
        hotelIcon.setMaximumSize(new Dimension(100, 100));

        JLabel brandName = new JLabel(HOTEL_NAME);
        brandName.setFont(new Font("Segoe UI", Font.BOLD, 36));
        brandName.setForeground(LOGIN_TXT);
        brandName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Gold accent line under hotel name
        JPanel goldLine = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int cx = getWidth() / 2;
                GradientPaint gp = new GradientPaint(cx - 60, 0, new Color(LOGIN_GOLD.getRed(), LOGIN_GOLD.getGreen(), LOGIN_GOLD.getBlue(), 0),
                    cx, 0, LOGIN_GOLD);
                g2.setPaint(gp);
                g2.fillRoundRect(cx - 60, 2, 60, 2, 2, 2);
                GradientPaint gp2 = new GradientPaint(cx, 0, LOGIN_GOLD,
                    cx + 60, 0, new Color(LOGIN_GOLD.getRed(), LOGIN_GOLD.getGreen(), LOGIN_GOLD.getBlue(), 0));
                g2.setPaint(gp2);
                g2.fillRoundRect(cx, 2, 60, 2, 2, 2);
                g2.dispose();
            }
        };
        goldLine.setOpaque(false);
        goldLine.setPreferredSize(new Dimension(200, 8));
        goldLine.setMaximumSize(new Dimension(200, 8));
        goldLine.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel brandSub = new JLabel("Management System");
        brandSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        brandSub.setForeground(LOGIN_TXT2);
        brandSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel("Operational Excellence for Modern Hospitality");
        tagline.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        tagline.setForeground(LOGIN_TXT_M);
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        brandContent.add(hotelIcon);
        brandContent.add(Box.createVerticalStrut(20));
        brandContent.add(brandName);
        brandContent.add(Box.createVerticalStrut(6));
        brandContent.add(goldLine);
        brandContent.add(Box.createVerticalStrut(10));
        brandContent.add(brandSub);
        brandContent.add(Box.createVerticalStrut(16));
        brandContent.add(tagline);

        leftPanel.add(brandContent);

        // ═══════════════════════════════════════════
        //  RIGHT SECTION — Authentication Card (40%)
        // ═══════════════════════════════════════════
        JPanel rightPanel = new JPanel(new GridBagLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, new Color(14, 21, 38), 0, getHeight(), LOGIN_BG_LEFT));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };

        // --- Login Card ---
        JPanel card = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Soft deep shadow
                for (int i = 6; i > 0; i--) {
                    g2.setColor(new Color(0, 0, 0, 8 * i));
                    g2.fillRoundRect(-i, -i + 4, getWidth() + i * 2, getHeight() + i * 2, 18 + i, 18 + i);
                }
                // Card fill
                g2.setColor(LOGIN_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                // Subtle top accent (gold shimmer)
                GradientPaint topGlow = new GradientPaint(
                    getWidth() / 3f, 0, new Color(LOGIN_GOLD.getRed(), LOGIN_GOLD.getGreen(), LOGIN_GOLD.getBlue(), 40),
                    getWidth() * 2f / 3, 0, new Color(LOGIN_GOLD.getRed(), LOGIN_GOLD.getGreen(), LOGIN_GOLD.getBlue(), 0)
                );
                g2.setPaint(topGlow);
                g2.fillRoundRect(0, 0, getWidth(), 3, 18, 18);
                g2.dispose();
            }
        };
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(40, 44, 40, 44));
        card.setPreferredSize(new Dimension(400, 460));
        card.setMaximumSize(new Dimension(400, 460));

        // Card header
        JLabel signInTitle = new JLabel("Sign In");
        signInTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        signInTitle.setForeground(LOGIN_TXT);
        signInTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel signInSub = new JLabel("Access your hotel management dashboard");
        signInSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        signInSub.setForeground(LOGIN_TXT2);
        signInSub.setAlignmentX(Component.LEFT_ALIGNMENT);
        signInSub.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        // --- Username field ---
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        userLabel.setForeground(LOGIN_TXT2);
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userLabel.setBorder(BorderFactory.createEmptyBorder(28, 0, 6, 0));

        JTextField userField = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(LOGIN_INPUT_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hasFocus() ? LOGIN_ACCENT : new Color(0, 0, 0, 0));
                g2.setStroke(new BasicStroke(hasFocus() ? 1.5f : 0));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                g2.dispose();
            }
        };
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userField.setForeground(LOGIN_TXT);
        userField.setBackground(new Color(0, 0, 0, 0));
        userField.setOpaque(false);
        userField.setCaretColor(LOGIN_TXT);
        userField.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        userField.setAlignmentX(Component.LEFT_ALIGNMENT);
        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        userField.setPreferredSize(new Dimension(Integer.MAX_VALUE, 48));

        // --- Password field with toggle ---
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passLabel.setForeground(LOGIN_TXT2);
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passLabel.setBorder(BorderFactory.createEmptyBorder(18, 0, 6, 0));

        JPanel passContainer = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(LOGIN_INPUT_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
            }
            boolean focused = false;
            @Override protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(focused ? LOGIN_ACCENT : new Color(0, 0, 0, 0));
                g2.setStroke(new BasicStroke(focused ? 1.5f : 0));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                g2.dispose();
            }
            {
                // Hook will be set via focus listener below
            }
        };
        passContainer.setOpaque(false);
        passContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        passContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        passContainer.setPreferredSize(new Dimension(Integer.MAX_VALUE, 48));

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passField.setForeground(LOGIN_TXT);
        passField.setBackground(new Color(0, 0, 0, 0));
        passField.setOpaque(false);
        passField.setCaretColor(LOGIN_TXT);
        passField.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 8));

        JButton eyeBtn = new JButton("\u25CF") {
            boolean showing = false;
            {
                setFont(new Font("Segoe UI", Font.PLAIN, 14));
                setForeground(LOGIN_TXT_M);
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setOpaque(false);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setPreferredSize(new Dimension(44, 48));
                addActionListener(ev -> {
                    showing = !showing;
                    if (showing) {
                        passField.setEchoChar((char) 0);
                        setText("\u25CB");
                        setForeground(LOGIN_ACCENT);
                    } else {
                        passField.setEchoChar('\u2022');
                        setText("\u25CF");
                        setForeground(LOGIN_TXT_M);
                    }
                });
            }
        };

        passContainer.add(passField, BorderLayout.CENTER);
        passContainer.add(eyeBtn, BorderLayout.EAST);

        // Focus listener for container border
        passField.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                try {
                    java.lang.reflect.Field f = passContainer.getClass().getDeclaredField("focused");
                    f.setAccessible(true); f.setBoolean(passContainer, true);
                } catch (Exception ignored) {}
                passContainer.repaint();
            }
            @Override public void focusLost(FocusEvent e) {
                try {
                    java.lang.reflect.Field f = passContainer.getClass().getDeclaredField("focused");
                    f.setAccessible(true); f.setBoolean(passContainer, false);
                } catch (Exception ignored) {}
                passContainer.repaint();
            }
        });

        // --- Error label (inline, hidden initially) ---
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        errorLabel.setForeground(LOGIN_ERR);
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        errorLabel.setBorder(BorderFactory.createEmptyBorder(10, 2, 0, 0));
        errorLabel.setVisible(false);

        // --- Sign In button ---
        JButton signInBtn = new JButton("Sign In") {
            boolean loading = false;
            String originalText = "Sign In";
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color fill;
                if (!isEnabled()) fill = new Color(LOGIN_ACCENT.getRed(), LOGIN_ACCENT.getGreen(), LOGIN_ACCENT.getBlue(), 120);
                else if (getModel().isPressed()) fill = new Color(42, 90, 220);
                else if (getModel().isRollover()) {
                    fill = new Color(78, 130, 255);
                    // Soft glow on hover
                    g2.setColor(new Color(58, 111, 248, 25));
                    g2.fillRoundRect(-4, -2, getWidth()+8, getHeight()+6, 18, 18);
                } else fill = LOGIN_ACCENT;
                g2.setColor(fill);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        signInBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        signInBtn.setForeground(Color.WHITE);
        signInBtn.setFocusPainted(false);
        signInBtn.setBorderPainted(false);
        signInBtn.setContentAreaFilled(false);
        signInBtn.setOpaque(false);
        signInBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        signInBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        signInBtn.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        signInBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // --- Role hint ---
        JLabel roleHint = new JLabel("Admin  \u00B7  Reception");
        roleHint.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        roleHint.setForeground(LOGIN_TXT_M);
        roleHint.setAlignmentX(Component.LEFT_ALIGNMENT);
        roleHint.setHorizontalAlignment(JLabel.CENTER);
        roleHint.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // --- Assemble card ---
        card.add(signInTitle);
        card.add(signInSub);
        card.add(userLabel);
        card.add(userField);
        card.add(passLabel);
        card.add(passContainer);
        card.add(errorLabel);
        card.add(Box.createVerticalStrut(22));
        card.add(signInBtn);
        card.add(roleHint);

        rightPanel.add(card);

        // ─── Layout weighting: 60/40 split ───
        root.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        gbc.gridx = 0; gbc.weightx = 0.6;
        root.add(leftPanel, gbc);

        gbc.gridx = 1; gbc.weightx = 0.4;
        root.add(rightPanel, gbc);

        // ═══════════ LOGIN ACTION ═══════════
        ActionListener loginAction = e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();
            errorLabel.setVisible(false);

            if (user.isEmpty() || pass.isEmpty()) {
                errorLabel.setText("Please enter both username and password.");
                errorLabel.setVisible(true);
                return;
            }

            // Disable and show loading
            signInBtn.setEnabled(false);
            signInBtn.setText("Signing in...");

            SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
                String role = "", fullName = "", username = "";
                int userId = -1;
                String errMsg = "";

                @Override protected Boolean doInBackground() {
                    try (Connection conn = getConnection();
                         PreparedStatement ps = conn.prepareStatement(
                             "SELECT id,username,role,full_name FROM users WHERE username=? AND password=?")) {
                        ps.setString(1, user); ps.setString(2, pass);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            userId = rs.getInt("id"); username = rs.getString("username");
                            role = rs.getString("role"); fullName = rs.getString("full_name");
                            return true;
                        } else {
                            errMsg = "Invalid username or password";
                            return false;
                        }
                    } catch (SQLException ex) {
                        errMsg = "Connection error. Please try again.";
                        return false;
                    }
                }

                @Override protected void done() {
                    try {
                        if (get()) {
                            loggedInUserId = userId; loggedInUsername = username;
                            loggedInRole = role; loggedInFullName = fullName;
                            logAudit("LOGIN", "USER", loggedInUserId, loggedInFullName + " logged in");
                            frame.dispose();
                            showMainApp();
                        } else {
                            errorLabel.setText(errMsg);
                            errorLabel.setVisible(true);
                            signInBtn.setEnabled(true);
                            signInBtn.setText("Sign In");
                            passField.requestFocusInWindow();
                        }
                    } catch (Exception ex) {
                        signInBtn.setEnabled(true);
                        signInBtn.setText("Sign In");
                    }
                }
            };
            worker.execute();
        };

        signInBtn.addActionListener(loginAction);
        passField.addActionListener(loginAction);
        userField.addActionListener(loginAction);

        frame.setContentPane(root);
        frame.setVisible(true);
        userField.requestFocusInWindow();
    }

    // ═══════════════════════════════════════════════════════════════
    //  MAIN APPLICATION
    // ═══════════════════════════════════════════════════════════════
    static void showMainApp() {
        mainFrame = new JFrame(HOTEL_NAME + " — " + loggedInFullName + " (" + loggedInRole + ")");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1360, 820);
        mainFrame.setMinimumSize(new Dimension(1100, 700));
        mainFrame.setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg());

        contentArea = new JPanel(new CardLayout());
        contentArea.setBackground(bg());

        JPanel dashPanel    = buildDashboard();
        JPanel roomPanel    = buildRoomPanel();
        JPanel custPanel    = buildCustomerPanel();
        JPanel bookPanel    = buildBookingPanel();
        JPanel billPanel    = buildBillingPanel();
        JPanel reportPanel  = buildReportPanel();

        contentArea.add(dashPanel,   "dashboard");
        contentArea.add(roomPanel,   "rooms");
        contentArea.add(custPanel,   "customers");
        contentArea.add(bookPanel,   "bookings");
        contentArea.add(billPanel,   "billing");
        contentArea.add(reportPanel, "reports");

        JPanel sidebar = buildSidebar();
        root.add(sidebar, BorderLayout.WEST);
        root.add(contentArea, BorderLayout.CENTER);

        mainFrame.setContentPane(root);
        mainFrame.setVisible(true);
        // Prevent search field from auto-getting focus
        SwingUtilities.invokeLater(() -> contentArea.requestFocusInWindow());
    }

    static void navigateTo(String key) {
        currentNav = key;
        CardLayout cl = (CardLayout) contentArea.getLayout();
        cl.show(contentArea, key);
        // Update nav button highlights and label styles
        for (int i = 0; i < navPanels.size(); i++) {
            JPanel np = navPanels.get(i);
            String navKey = (String) np.getClientProperty("navKey");
            JLabel lbl = navLabels.get(i);
            if (navKey != null && navKey.equals(key)) {
                lbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
                lbl.setForeground(ACCENT);
            } else {
                lbl.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
                lbl.setForeground(txtS());
            }
            np.revalidate();
            np.repaint();
        }
        // Force sidebar repaint to clear stale highlights
        if (!navPanels.isEmpty() && navPanels.get(0).getParent() != null) {
            navPanels.get(0).getParent().revalidate();
            navPanels.get(0).getParent().repaint();
        }
        for (Component c : contentArea.getComponents()) {
            if (c instanceof JPanel && c.isVisible()) {
                Runnable r = (Runnable) ((JPanel)c).getClientProperty("refresher");
                if (r != null) r.run();
            }
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  SIDEBAR
    // ═══════════════════════════════════════════════════════════════
    static JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(sidebarBg());
        sidebar.setPreferredSize(new Dimension(235, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, border()));

        // Brand
        JPanel brandP = new JPanel(new BorderLayout());
        brandP.setBackground(sidebarBg());
        brandP.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        brandP.setMaximumSize(new Dimension(235, 70));
        JLabel bl = new JLabel("\u2302 " + HOTEL_NAME);
        bl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20)); bl.setForeground(ACCENT_GLOW);
        brandP.add(bl);
        sidebar.add(brandP);

        JSeparator sep = new JSeparator(); sep.setForeground(border()); sep.setMaximumSize(new Dimension(235, 1));
        sidebar.add(sep); sidebar.add(Box.createVerticalStrut(8));

        // Global search
        JPanel searchP = new JPanel(new BorderLayout(6, 0));
        searchP.setBackground(sidebarBg());
        searchP.setBorder(BorderFactory.createEmptyBorder(4, 14, 8, 14));
        searchP.setMaximumSize(new Dimension(235, 44));
        JTextField searchF = createField();
        searchF.setPreferredSize(new Dimension(0, 32));
        searchF.setFont(F_SMALL);
        searchF.setText("");
        // Add placeholder behavior
        searchF.setForeground(txtM());
        searchF.setText("Search...");
        searchF.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { if (searchF.getText().equals("Search...")) { searchF.setText(""); searchF.setForeground(txtP()); } }
            @Override public void focusLost(FocusEvent e) { if (searchF.getText().isEmpty()) { searchF.setText("Search..."); searchF.setForeground(txtM()); } }
        });
        searchF.addActionListener(e -> {
            String q = searchF.getText().trim();
            if (!q.isEmpty() && !q.equals("Search...")) showGlobalSearch(q);
        });
        searchP.add(searchF, BorderLayout.CENTER);
        sidebar.add(searchP);
        sidebar.add(Box.createVerticalStrut(6));

        navPanels.clear(); navLabels.clear();

        String[][] navItems = {
            {"dashboard", "\u2302  Dashboard"},
            {"rooms",     "\u25A0  Rooms"},
            {"customers", "\u25CF  Customers"},
            {"bookings",  "\u2606  Bookings"},
            {"billing",   "\u25B2  Billing"},
            {"reports",   "\u25C6  Reports"}
        };

        for (String[] item : navItems) {
            String key = item[0]; String label = item[1];
            if (key.equals("reports") && !loggedInRole.equals("Admin")) continue;

            JPanel navBtn = new JPanel(new BorderLayout()) {
                @Override protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    if (currentNav.equals(key)) {
                        g2.setColor(new Color(ACCENT.getRed(), ACCENT.getGreen(), ACCENT.getBlue(), darkMode ? 40 : 30));
                        g2.fillRoundRect(6, 2, getWidth()-12, getHeight()-4, 10, 10);
                        g2.setColor(ACCENT);
                        g2.fillRoundRect(0, 6, 4, getHeight()-12, 4, 4);
                    }
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            navBtn.setOpaque(false);
            navBtn.setMaximumSize(new Dimension(235, 42));
            navBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            navBtn.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 14));

            JLabel navLbl = new JLabel(label);
            navLbl.setFont(currentNav.equals(key) ? new Font("Segoe UI Symbol", Font.BOLD, 14) : new Font("Segoe UI Symbol", Font.PLAIN, 14));
            navLbl.setForeground(currentNav.equals(key) ? ACCENT : txtS());
            navBtn.add(navLbl, BorderLayout.CENTER);

            navBtn.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { navigateTo(key); }
                @Override public void mouseEntered(MouseEvent e) { if (!currentNav.equals(key)) navBtn.repaint(); }
                @Override public void mouseExited(MouseEvent e) { navBtn.repaint(); }
            });

            navBtn.putClientProperty("navKey", key);
            navPanels.add(navBtn); navLabels.add(navLbl);
            sidebar.add(navBtn); sidebar.add(Box.createVerticalStrut(2));
        }

        sidebar.add(Box.createVerticalGlue());

        // Theme toggle
        JPanel themeP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        themeP.setBackground(sidebarBg());
        themeP.setMaximumSize(new Dimension(235, 44));
        JButton themeBtn = createBtn(darkMode ? "Light Mode" : "Dark Mode", new Color(55, 65, 81));
        themeBtn.setPreferredSize(new Dimension(180, 32));
        themeBtn.addActionListener(e -> {
            darkMode = !darkMode;
            applyDialogTheme();
            mainFrame.dispose();
            showMainApp();
        });
        themeP.add(themeBtn);
        sidebar.add(themeP);

        // User info
        JSeparator sep2 = new JSeparator(); sep2.setForeground(border()); sep2.setMaximumSize(new Dimension(235, 1));
        sidebar.add(sep2);

        JPanel userP = new JPanel(new BorderLayout());
        userP.setBackground(sidebarBg());
        userP.setBorder(BorderFactory.createEmptyBorder(10, 18, 6, 18));
        userP.setMaximumSize(new Dimension(235, 55));
        JLabel un = new JLabel(loggedInFullName);
        un.setFont(new Font("Segoe UI", Font.BOLD, 13)); un.setForeground(txtP());
        JLabel ur = new JLabel(loggedInRole);
        ur.setFont(F_SMALL); ur.setForeground(txtM());
        JPanel ut = new JPanel(new GridLayout(2, 1));
        ut.setBackground(sidebarBg()); ut.add(un); ut.add(ur);
        userP.add(ut, BorderLayout.CENTER);
        sidebar.add(userP);

        JPanel logoutP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutP.setBackground(sidebarBg());
        logoutP.setMaximumSize(new Dimension(235, 44));
        JButton logoutBtn = createBtn("Logout", DANGER_CLR);
        logoutBtn.setPreferredSize(new Dimension(180, 32));
        logoutBtn.addActionListener(e -> {
            loggedInUserId=-1; loggedInUsername=""; loggedInRole=""; loggedInFullName="";
            currentNav="dashboard"; mainFrame.dispose(); showLoginScreen();
        });
        logoutP.add(logoutBtn);
        sidebar.add(logoutP);
        sidebar.add(Box.createVerticalStrut(10));

        return sidebar;
    }

    // ═══════════════════════════════════════════════════════════════
    //  DASHBOARD
    // ═══════════════════════════════════════════════════════════════
    static JPanel buildDashboard() {
        JPanel panel = new JPanel(new BorderLayout(0, 16));
        panel.setBackground(bg());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        // Title row with notifications
        JPanel titleRow = new JPanel(new BorderLayout());
        titleRow.setOpaque(false);
        JLabel title = new JLabel("Dashboard");
        title.setFont(F_TITLE); title.setForeground(txtP());
        JLabel dateL = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));
        dateL.setFont(F_BODY); dateL.setForeground(txtM());
        titleRow.add(title, BorderLayout.WEST);
        titleRow.add(dateL, BorderLayout.EAST);
        panel.add(titleRow, BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(0, 16));
        body.setOpaque(false);

        // KPI cards row
        JPanel kpiRow = new JPanel(new GridLayout(1, 4, 14, 0));
        kpiRow.setOpaque(false);
        kpiRow.setPreferredSize(new Dimension(0, 110));

        JPanel occCard = buildKPICard("Occupancy Rate", "0%", ACCENT, "\u25CF");
        JPanel todayRevCard = buildKPICard("Today's Revenue", "\u20B9 0", SUCCESS_CLR, "\u20B9");
        JPanel monthRevCard = buildKPICard("Monthly Revenue", "\u20B9 0", PURPLE_CLR, "\u2605");
        JPanel availCard = buildKPICard("Available Rooms", "0", WARNING_CLR, "\u2302");
        kpiRow.add(occCard); kpiRow.add(todayRevCard); kpiRow.add(monthRevCard); kpiRow.add(availCard);
        body.add(kpiRow, BorderLayout.NORTH);

        // Middle section: chart full width
        JPanel midSection = new JPanel(new BorderLayout());
        midSection.setOpaque(false);

        // Revenue chart card
        JPanel chartCard = createCard("Revenue Trend (Last 7 Days)");
        JPanel chartPanel = new JPanel() {
            double[] values = new double[7];
            String[] labels = new String[7];
            {
                try (Connection conn = getConnection()) {
                    for (int i = 6; i >= 0; i--) {
                        LocalDate d = LocalDate.now().minusDays(i);
                        labels[6-i] = d.format(DateTimeFormatter.ofPattern("dd MMM"));
                        try (PreparedStatement ps = conn.prepareStatement(
                                "SELECT COALESCE(SUM(total_with_tax),0) FROM payments WHERE DATE(payment_date)=?")) {
                            ps.setDate(1, java.sql.Date.valueOf(d));
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) values[6-i] = rs.getDouble(1);
                        }
                    }
                } catch (Exception ex) { ex.printStackTrace(); }
            }
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                setBackground(cardBg());

                int w = getWidth(), h = getHeight();
                int pad = 50, barW = Math.max(20, (w - pad*2) / 7 - 10);
                double maxV = 1;
                for (double v : values) maxV = Math.max(maxV, v);

                // Grid lines
                g2.setColor(border());
                for (int i = 0; i <= 4; i++) {
                    int y = pad + (h - pad*2) * i / 4;
                    g2.drawLine(pad, y, w - 20, y);
                    g2.setFont(F_TINY); g2.setColor(txtM());
                    g2.drawString(String.format("\u20B9%.0f", maxV * (4-i) / 4), 2, y + 4);
                    g2.setColor(border());
                }

                // Bars
                for (int i = 0; i < 7; i++) {
                    int x = pad + i * (barW + 10);
                    int barH = (int)((values[i] / maxV) * (h - pad*2));
                    int y = h - pad - barH;

                    GradientPaint gp = new GradientPaint(x, y, ACCENT, x, h-pad, new Color(ACCENT.getRed(), ACCENT.getGreen(), ACCENT.getBlue(), 100));
                    g2.setPaint(gp);
                    g2.fillRoundRect(x, y, barW, barH, 6, 6);

                    g2.setColor(txtM()); g2.setFont(F_TINY);
                    g2.drawString(labels[i], x - 2, h - pad + 14);
                }
                g2.dispose();
            }
        };
        chartPanel.setOpaque(false);
        chartPanel.setPreferredSize(new Dimension(0, 200));
        chartCard.add(chartPanel, BorderLayout.CENTER);
        midSection.add(chartCard, BorderLayout.CENTER);

        body.add(midSection, BorderLayout.CENTER);

        // Recent bookings table
        JPanel recentCard = createCard("Latest Bookings");
        DefaultTableModel recentModel = new DefaultTableModel(
            new String[]{"Customer", "Room", "Check-In", "Check-Out", "Amount (\u20B9)", "Status"}, 0);
        JTable recentTable = createTable(recentModel);
        recentCard.add(createScroll(recentTable), BorderLayout.CENTER);
        body.add(recentCard, BorderLayout.SOUTH);
        recentCard.setPreferredSize(new Dimension(0, 220));

        panel.add(body, BorderLayout.CENTER);

        // Refresher
        Runnable refresher = () -> {
            try (Connection conn = getConnection()) {
                // KPIs
                int totalRooms = 0, availRooms = 0, bookedRooms = 0;
                try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM rooms")) {
                    if (rs.next()) totalRooms = rs.getInt(1);
                }
                try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM rooms WHERE status='Available'")) {
                    if (rs.next()) availRooms = rs.getInt(1);
                }
                try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM rooms WHERE status='Booked'")) {
                    if (rs.next()) bookedRooms = rs.getInt(1);
                }
                double occRate = totalRooms > 0 ? (bookedRooms * 100.0 / totalRooms) : 0;
                updateKPI(occCard, String.format("%.0f%%", occRate));
                updateKPI(availCard, String.valueOf(availRooms));

                // Today's revenue
                try (PreparedStatement ps = conn.prepareStatement("SELECT COALESCE(SUM(total_with_tax),0) FROM payments WHERE DATE(payment_date)=CURDATE()")) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) updateKPI(todayRevCard, "\u20B9 " + String.format("%,.0f", rs.getDouble(1)));
                }
                // Monthly revenue
                try (PreparedStatement ps = conn.prepareStatement("SELECT COALESCE(SUM(total_with_tax),0) FROM payments WHERE MONTH(payment_date)=MONTH(CURDATE()) AND YEAR(payment_date)=YEAR(CURDATE())")) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) updateKPI(monthRevCard, "\u20B9 " + String.format("%,.0f", rs.getDouble(1)));
                }

                // Recent bookings
                recentModel.setRowCount(0);
                try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                    "SELECT b.id, c.name, r.room_number, b.check_in_date, b.check_out_date, b.total_amount, b.status " +
                    "FROM bookings b JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id " +
                    "ORDER BY b.created_at DESC LIMIT 8")) {
                    while (rs.next()) {
                        recentModel.addRow(new Object[]{rs.getString(2), rs.getString(3),
                            rs.getDate(4), rs.getDate(5), String.format("%,.2f", rs.getDouble(6)), rs.getString(7)});
                    }
                }
            } catch (SQLException ex) { ex.printStackTrace(); }
        };
        panel.putClientProperty("refresher", refresher);
        refresher.run();
        return panel;
    }

    static JPanel buildKPICard(String label, String value, Color accent, String icon) {
        JPanel card = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(cardBg());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(border());
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 16, 16);
                // accent top strip
                g2.setColor(accent);
                g2.fillRoundRect(0, 0, getWidth(), 4, 4, 4);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 16, 20));

        JLabel vl = new JLabel(value); vl.setFont(F_KPI_VAL); vl.setForeground(txtP());
        JLabel ll = new JLabel(label); ll.setFont(F_KPI_LBL); ll.setForeground(txtS());
        JLabel il = new JLabel(icon); il.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24)); il.setForeground(accent);
        il.setHorizontalAlignment(JLabel.RIGHT);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(vl, BorderLayout.WEST);
        top.add(il, BorderLayout.EAST);

        card.add(top, BorderLayout.CENTER);
        card.add(ll, BorderLayout.SOUTH);
        card.putClientProperty("valueLabel", vl);
        return card;
    }

    static void updateKPI(JPanel card, String value) {
        JLabel vl = (JLabel) card.getClientProperty("valueLabel");
        if (vl != null) vl.setText(value);
    }

    static void addAlertItem(JPanel list, String text, Color accent) {
        JPanel item = new JPanel(new BorderLayout());
        item.setOpaque(false);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        item.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        JLabel l = new JLabel(text);
        l.setFont(F_BODY); l.setForeground(accent);
        item.add(l, BorderLayout.CENTER);
        list.add(item);
    }

    // ═══════════════════════════════════════════════════════════════
    //  ROOM MANAGEMENT
    // ═══════════════════════════════════════════════════════════════
    static JPanel buildRoomPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(bg());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel title = new JLabel("Room Management");
        title.setFont(F_TITLE); title.setForeground(txtP());
        panel.add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"S.No", "Room #", "Type", "Price/Night (\u20B9)", "Status", "Floor", "Description"}, 0);
        JTable table = createTable(model);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        ArrayList<Integer> roomDbIds = new ArrayList<>();

        JPanel body = new JPanel(new BorderLayout(0, 12));
        body.setOpaque(false);

        // Toolbar with filters
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        toolbar.setOpaque(false);

        JButton addBtn = createBtn("+ Add Room", ACCENT);
        JButton editBtn = createBtn("Edit", WARNING_CLR);
        JButton delBtn = createBtn("Delete", DANGER_CLR);
        JButton histBtn = createBtn("History", PURPLE_CLR);
        JButton refBtn = createBtn("Refresh", new Color(55, 65, 81));

        JComboBox<String> statusFilter = createCombo(new String[]{"All Status", "Available", "Booked", "Under Maintenance"});
        statusFilter.setPreferredSize(new Dimension(160, 36));
        JComboBox<String> typeFilter = createCombo(new String[]{"All Types", "Single", "Double", "Deluxe", "Suite"});
        typeFilter.setPreferredSize(new Dimension(130, 36));
        // [MODIFIED] Price/money filter removed from room management toolbar

        toolbar.add(addBtn); toolbar.add(editBtn); toolbar.add(delBtn); toolbar.add(histBtn); toolbar.add(refBtn);
        toolbar.add(Box.createHorizontalStrut(12));
        toolbar.add(statusFilter); toolbar.add(typeFilter);

        body.add(toolbar, BorderLayout.NORTH);
        body.add(createScroll(table), BorderLayout.CENTER);
        panel.add(body, BorderLayout.CENTER);

        Runnable refreshData = () -> {
            model.setRowCount(0);
            roomDbIds.clear();
            String sf = (String) statusFilter.getSelectedItem();
            String tf = (String) typeFilter.getSelectedItem();

            // [MODIFIED] Price filter logic removed — only status and type filters remain
            StringBuilder sql = new StringBuilder("SELECT id, room_number, room_type, price_per_night, status, floor, description FROM rooms WHERE 1=1");
            if (sf != null && !sf.startsWith("All")) sql.append(" AND status=?");
            if (tf != null && !tf.startsWith("All")) sql.append(" AND room_type=?");
            sql.append(" ORDER BY CAST(room_number AS UNSIGNED), room_number");

            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
                int idx = 1;
                if (sf != null && !sf.startsWith("All")) ps.setString(idx++, sf);
                if (tf != null && !tf.startsWith("All")) ps.setString(idx++, tf);
                ResultSet rs = ps.executeQuery();
                int seq = 1;
                while (rs.next()) {
                    roomDbIds.add(rs.getInt(1));
                    model.addRow(new Object[]{seq++, rs.getString(2), rs.getString(3),
                        String.format("%,.2f", rs.getDouble(4)), rs.getString(5), rs.getInt(6), rs.getString(7)});
                }
            } catch (Exception ex) { showMsg(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        };
        panel.putClientProperty("refresher", refreshData);
        refreshData.run();

        statusFilter.addActionListener(e -> refreshData.run());
        typeFilter.addActionListener(e -> refreshData.run());
        refBtn.addActionListener(e -> refreshData.run());

        addBtn.addActionListener(e -> {
            if (!loggedInRole.equals("Admin")) { showMsg(panel, "Only Admin can add rooms.", "Access Denied", JOptionPane.WARNING_MESSAGE); return; }
            showRoomDialog(null, refreshData);
        });
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a room to edit.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            showRoomDialog(roomDbIds.get(row), refreshData);
        });
        delBtn.addActionListener(e -> {
            if (!loggedInRole.equals("Admin")) { showMsg(panel, "Only Admin can delete rooms.", "Access Denied", JOptionPane.WARNING_MESSAGE); return; }
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a room.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            int roomId = roomDbIds.get(row);
            if (showConfirm(panel, "Delete this room permanently?", "Confirm") == JOptionPane.YES_OPTION) {
                try (Connection conn = getConnection()) {
                    try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM bookings WHERE room_id=? AND status IN ('Reserved','Checked-In')")) {
                        ps.setInt(1, roomId); ResultSet rs = ps.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) { showMsg(panel, "Cannot delete room with active bookings.", "Error", JOptionPane.ERROR_MESSAGE); return; }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM rooms WHERE id=?")) { ps.setInt(1, roomId); ps.executeUpdate(); }
                    logAudit("DELETE_ROOM", "ROOM", roomId, "Room deleted");
                    showMsg(panel, "Room deleted.", "Success", JOptionPane.INFORMATION_MESSAGE); refreshData.run();
                } catch (SQLException ex) { showMsg(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
            }
        });
        histBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a room.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            int roomId = roomDbIds.get(row);
            String roomNum = (String) model.getValueAt(row, 1);
            showRoomHistory(roomId, roomNum);
        });
        return panel;
    }

    static void showRoomDialog(Integer roomId, Runnable onSave) {
        JDialog dlg = new JDialog(mainFrame, roomId == null ? "Add Room" : "Edit Room", true);
        dlg.setSize(460, 480); dlg.setLocationRelativeTo(mainFrame);
        JPanel form = new JPanel(); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(cardBg()); form.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        JTextField numF = createField();
        JComboBox<String> typeB = createCombo(new String[]{"Single", "Double", "Deluxe", "Suite"});
        JTextField priceF = createField();
        JComboBox<String> statusB = createCombo(new String[]{"Available", "Booked", "Under Maintenance"});
        JTextField floorF = createField();
        JTextArea descA = createTextArea(3, 20);

        if (roomId != null) {
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM rooms WHERE id=?")) {
                ps.setInt(1, roomId); ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    numF.setText(rs.getString("room_number")); typeB.setSelectedItem(rs.getString("room_type"));
                    priceF.setText(String.valueOf(rs.getDouble("price_per_night"))); statusB.setSelectedItem(rs.getString("status"));
                    floorF.setText(String.valueOf(rs.getInt("floor"))); descA.setText(rs.getString("description"));
                }
            } catch (SQLException ex) { showMsg(dlg, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        }

        form.add(createFormRow("Room Number:", numF)); form.add(createFormRow("Room Type:", typeB));
        form.add(createFormRow("Price/Night (\u20B9):", priceF)); form.add(createFormRow("Status:", statusB));
        form.add(createFormRow("Floor:", floorF));
        JPanel descRow = new JPanel(new BorderLayout(10, 0)); descRow.setOpaque(false);
        JLabel dl = createLabel("Description:"); dl.setPreferredSize(new Dimension(150, 36));
        descRow.add(dl, BorderLayout.WEST);
        JScrollPane ds = new JScrollPane(descA); ds.setBorder(BorderFactory.createLineBorder(border()));
        descRow.add(ds, BorderLayout.CENTER); descRow.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        form.add(descRow); form.add(Box.createVerticalStrut(16));

        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0)); bp.setOpaque(false);
        JButton saveBtn = createBtn("Save", ACCENT); JButton canBtn = createBtn("Cancel", new Color(55, 65, 81));
        bp.add(saveBtn); bp.add(canBtn); form.add(bp);

        canBtn.addActionListener(e -> dlg.dispose());
        saveBtn.addActionListener(e -> {
            String rn = numF.getText().trim(), ps2 = priceF.getText().trim(), fs = floorF.getText().trim();
            if (rn.isEmpty() || ps2.isEmpty() || fs.isEmpty()) { showMsg(dlg, "Room Number, Price and Floor required.", "Validation", JOptionPane.WARNING_MESSAGE); return; }
            try {
                double price = Double.parseDouble(ps2); int floor = Integer.parseInt(fs);
                try (Connection conn = getConnection()) {
                    if (roomId == null) {
                        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO rooms (room_number,room_type,price_per_night,status,floor,description) VALUES (?,?,?,?,?,?)")) {
                            ps.setString(1, rn); ps.setString(2, (String)typeB.getSelectedItem()); ps.setDouble(3, price);
                            ps.setString(4, (String)statusB.getSelectedItem()); ps.setInt(5, floor); ps.setString(6, descA.getText().trim()); ps.executeUpdate();
                        }
                        logAudit("ADD_ROOM", "ROOM", 0, "Room " + rn + " added");
                    } else {
                        try (PreparedStatement ps = conn.prepareStatement("UPDATE rooms SET room_number=?,room_type=?,price_per_night=?,status=?,floor=?,description=? WHERE id=?")) {
                            ps.setString(1, rn); ps.setString(2, (String)typeB.getSelectedItem()); ps.setDouble(3, price);
                            ps.setString(4, (String)statusB.getSelectedItem()); ps.setInt(5, floor); ps.setString(6, descA.getText().trim()); ps.setInt(7, roomId); ps.executeUpdate();
                        }
                        logAudit("EDIT_ROOM", "ROOM", roomId, "Room " + rn + " updated");
                    }
                    showMsg(dlg, "Room saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dlg.dispose(); onSave.run();
                }
            } catch (Exception ex) { showMsg(dlg, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        });

        JScrollPane scroll = new JScrollPane(form); scroll.setBorder(null); scroll.getViewport().setBackground(cardBg());
        dlg.setContentPane(scroll); dlg.setVisible(true);
    }

    static void showRoomHistory(int roomId, String roomNum) {
        JDialog dlg = new JDialog(mainFrame, "Room History — " + roomNum, true);
        dlg.setSize(750, 450); dlg.setLocationRelativeTo(mainFrame);
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(cardBg()); p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JLabel t = new JLabel("Booking History for Room " + roomNum); t.setFont(F_HEADER); t.setForeground(txtP());
        t.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0)); p.add(t, BorderLayout.NORTH);

        DefaultTableModel m = new DefaultTableModel(new String[]{"Customer", "Check-In", "Check-Out", "Guests", "Amount", "Status"}, 0);
        JTable tb = createTable(m);
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
            "SELECT c.name, b.check_in_date, b.check_out_date, b.num_guests, b.total_amount, b.status FROM bookings b JOIN customers c ON b.customer_id=c.id WHERE b.room_id=? ORDER BY b.created_at DESC")) {
            ps.setInt(1, roomId); ResultSet rs = ps.executeQuery();
            while (rs.next()) m.addRow(new Object[]{rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), String.format("%,.2f", rs.getDouble(5)), rs.getString(6)});
        } catch (SQLException ex) { ex.printStackTrace(); }
        p.add(createScroll(tb), BorderLayout.CENTER);
        JButton cb = createBtn("Close", new Color(55, 65, 81)); cb.addActionListener(e -> dlg.dispose());
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT)); bp.setOpaque(false); bp.add(cb); p.add(bp, BorderLayout.SOUTH);
        dlg.setContentPane(p); dlg.setVisible(true);
    }

    // ═══════════════════════════════════════════════════════════════
    //  CUSTOMER MANAGEMENT
    // ═══════════════════════════════════════════════════════════════
    static JPanel buildCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(bg());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel title = new JLabel("Customer Management");
        title.setFont(F_TITLE); title.setForeground(txtP());
        panel.add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"_id", "Name", "Phone", "Email", "ID Proof", "Bookings", "Total Spent (\u20B9)", "Class"}, 0);
        JTable table = createTable(model);
        table.removeColumn(table.getColumnModel().getColumn(0));

        JPanel body = new JPanel(new BorderLayout(0, 12));
        body.setOpaque(false);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        toolbar.setOpaque(false);
        JButton addBtn = createBtn("+ Add Customer", ACCENT);
        JButton editBtn = createBtn("Edit", WARNING_CLR);
        JButton histBtn = createBtn("History", PURPLE_CLR);
        JButton csvBtn = createBtn("Export CSV", new Color(55, 65, 81));
        JTextField searchF = createField(); searchF.setPreferredSize(new Dimension(200, 36)); searchF.setFont(F_SMALL);
        JButton searchBtn = createBtn("Search", new Color(55, 65, 81));

        toolbar.add(addBtn); toolbar.add(editBtn); toolbar.add(histBtn); toolbar.add(csvBtn);
        toolbar.add(Box.createHorizontalStrut(16)); toolbar.add(searchF); toolbar.add(searchBtn);

        body.add(toolbar, BorderLayout.NORTH);
        body.add(createScroll(table), BorderLayout.CENTER);
        panel.add(body, BorderLayout.CENTER);

        Runnable refreshData = () -> {
            model.setRowCount(0);
            String q = searchF.getText().trim();
            String sql = "SELECT c.id, c.name, c.phone, c.email, c.id_proof_type, " +
                "(SELECT COUNT(*) FROM bookings WHERE customer_id=c.id) as bcount, " +
                "(SELECT COALESCE(SUM(total_amount),0) FROM bookings WHERE customer_id=c.id) as spent " +
                "FROM customers c";
            if (!q.isEmpty()) sql += " WHERE c.name LIKE ? OR c.phone LIKE ? OR c.email LIKE ?";
            sql += " ORDER BY c.id ASC";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                if (!q.isEmpty()) { String lk = "%"+q+"%"; ps.setString(1, lk); ps.setString(2, lk); ps.setString(3, lk); }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int bc = rs.getInt(6); double sp = rs.getDouble(7);
                    String cls = sp >= 50000 ? "VIP" : bc > 1 ? "Returning" : "New";
                    model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), bc, String.format("%,.2f", sp), cls});
                }
            } catch (SQLException ex) { showMsg(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        };
        panel.putClientProperty("refresher", refreshData);
        refreshData.run();

        searchBtn.addActionListener(e -> refreshData.run());
        searchF.addActionListener(e -> refreshData.run());
        addBtn.addActionListener(e -> showCustomerDialog(null, refreshData));
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a customer.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            showCustomerDialog((int) model.getValueAt(row, 0), refreshData);
        });
        histBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a customer.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            showBookingHistory((int) model.getValueAt(row, 0), (String) model.getValueAt(row, 1));
        });
        csvBtn.addActionListener(e -> exportTableCSV(table, "customers"));
        return panel;
    }

    static void showCustomerDialog(Integer custId, Runnable onSave) {
        JDialog dlg = new JDialog(mainFrame, custId == null ? "Add Customer" : "Edit Customer", true);
        dlg.setSize(500, 490); dlg.setLocationRelativeTo(mainFrame);
        JPanel form = new JPanel(); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(cardBg()); form.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        JTextField nameF = createField(), phoneF = createField(), emailF = createField(), idNumF = createField();
        JComboBox<String> idProofB = createCombo(new String[]{"Aadhar", "Passport", "Driving License", "Voter ID", "PAN Card"});
        JTextArea addrA = createTextArea(3, 20);

        if (custId != null) {
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers WHERE id=?")) {
                ps.setInt(1, custId); ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nameF.setText(rs.getString("name")); phoneF.setText(rs.getString("phone"));
                    emailF.setText(rs.getString("email")); idProofB.setSelectedItem(rs.getString("id_proof_type"));
                    idNumF.setText(rs.getString("id_proof_number")); addrA.setText(rs.getString("address"));
                }
            } catch (SQLException ex) { showMsg(dlg, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        }

        form.add(createFormRow("Name:", nameF)); form.add(createFormRow("Phone:", phoneF));
        form.add(createFormRow("Email:", emailF)); form.add(createFormRow("ID Proof Type:", idProofB));
        form.add(createFormRow("ID Number:", idNumF));
        JPanel addrRow = new JPanel(new BorderLayout(10, 0)); addrRow.setOpaque(false);
        JLabel al = createLabel("Address:"); al.setPreferredSize(new Dimension(150, 36));
        addrRow.add(al, BorderLayout.WEST);
        JScrollPane as = new JScrollPane(addrA); as.setBorder(BorderFactory.createLineBorder(border()));
        addrRow.add(as, BorderLayout.CENTER); addrRow.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        form.add(addrRow); form.add(Box.createVerticalStrut(16));

        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0)); bp.setOpaque(false);
        JButton saveBtn = createBtn("Save", ACCENT); JButton canBtn = createBtn("Cancel", new Color(55, 65, 81));
        bp.add(saveBtn); bp.add(canBtn); form.add(bp);

        canBtn.addActionListener(e -> dlg.dispose());
        saveBtn.addActionListener(e -> {
            String nm = nameF.getText().trim(), ph = phoneF.getText().trim(), idn = idNumF.getText().trim();
            if (nm.isEmpty() || ph.isEmpty() || idn.isEmpty()) { showMsg(dlg, "Name, Phone and ID Number required.", "Validation", JOptionPane.WARNING_MESSAGE); return; }
            try (Connection conn = getConnection()) {
                if (custId == null) {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO customers (name,phone,email,id_proof_type,id_proof_number,address) VALUES (?,?,?,?,?,?)")) {
                        ps.setString(1, nm); ps.setString(2, ph); ps.setString(3, emailF.getText().trim());
                        ps.setString(4, (String)idProofB.getSelectedItem()); ps.setString(5, idn); ps.setString(6, addrA.getText().trim()); ps.executeUpdate();
                    }
                } else {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE customers SET name=?,phone=?,email=?,id_proof_type=?,id_proof_number=?,address=? WHERE id=?")) {
                        ps.setString(1, nm); ps.setString(2, ph); ps.setString(3, emailF.getText().trim());
                        ps.setString(4, (String)idProofB.getSelectedItem()); ps.setString(5, idn); ps.setString(6, addrA.getText().trim()); ps.setInt(7, custId); ps.executeUpdate();
                    }
                }
                showMsg(dlg, "Customer saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dlg.dispose(); onSave.run();
            } catch (SQLException ex) { showMsg(dlg, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        });
        JScrollPane scroll = new JScrollPane(form); scroll.setBorder(null); scroll.getViewport().setBackground(cardBg());
        dlg.setContentPane(scroll); dlg.setVisible(true);
    }

    static void showBookingHistory(int custId, String custName) {
        JDialog dlg = new JDialog(mainFrame, "Booking History — " + custName, true);
        dlg.setSize(800, 450); dlg.setLocationRelativeTo(mainFrame);
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(cardBg()); p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JLabel t = new JLabel("Booking History: " + custName); t.setFont(F_HEADER); t.setForeground(txtP());
        t.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0)); p.add(t, BorderLayout.NORTH);

        DefaultTableModel m = new DefaultTableModel(new String[]{"Room", "Check-In", "Check-Out", "Guests", "Amount (\u20B9)", "Status"}, 0);
        JTable tb = createTable(m);
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
            "SELECT r.room_number,b.check_in_date,b.check_out_date,b.num_guests,b.total_amount,b.status FROM bookings b JOIN rooms r ON b.room_id=r.id WHERE b.customer_id=? ORDER BY b.created_at DESC")) {
            ps.setInt(1, custId); ResultSet rs = ps.executeQuery();
            while (rs.next()) m.addRow(new Object[]{rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), String.format("%,.2f", rs.getDouble(5)), rs.getString(6)});
        } catch (SQLException ex) { ex.printStackTrace(); }
        p.add(createScroll(tb), BorderLayout.CENTER);
        JButton cb = createBtn("Close", new Color(55, 65, 81)); cb.addActionListener(e -> dlg.dispose());
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT)); bp.setOpaque(false); bp.add(cb); p.add(bp, BorderLayout.SOUTH);
        dlg.setContentPane(p); dlg.setVisible(true);
    }

    // ═══════════════════════════════════════════════════════════════
    //  BOOKING MANAGEMENT
    // ═══════════════════════════════════════════════════════════════
    static JPanel buildBookingPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(bg());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel title = new JLabel("Booking Management");
        title.setFont(F_TITLE); title.setForeground(txtP());
        panel.add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"_id", "Customer", "Room", "Check-In", "Check-Out", "Guests", "Amount (\u20B9)", "Status"}, 0);
        JTable table = createTable(model);
        table.removeColumn(table.getColumnModel().getColumn(0));

        JPanel body = new JPanel(new BorderLayout(0, 12));
        body.setOpaque(false);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        toolbar.setOpaque(false);

        JButton newBtn = createBtn("+ New Booking", ACCENT);
        JButton checkInBtn = createBtn("Check-In", SUCCESS_CLR);
        JButton checkOutBtn = createBtn("Check-Out", WARNING_CLR);
        JButton cancelBtn = createBtn("Cancel", DANGER_CLR);
        JButton refBtn = createBtn("Refresh", new Color(55, 65, 81));

        JComboBox<String> statusFilter = createCombo(new String[]{"All", "Reserved", "Checked-In", "Checked-Out", "Cancelled"});
        statusFilter.setPreferredSize(new Dimension(140, 36));
        JComboBox<String> quickFilter = createCombo(new String[]{"All Time", "Today", "This Week", "This Month"});
        quickFilter.setPreferredSize(new Dimension(120, 36));

        toolbar.add(newBtn); toolbar.add(checkInBtn); toolbar.add(checkOutBtn); toolbar.add(cancelBtn); toolbar.add(refBtn);
        toolbar.add(Box.createHorizontalStrut(10)); toolbar.add(statusFilter); toolbar.add(quickFilter);

        body.add(toolbar, BorderLayout.NORTH);
        body.add(createScroll(table), BorderLayout.CENTER);
        panel.add(body, BorderLayout.CENTER);

        Runnable refreshData = () -> {
            model.setRowCount(0);
            String sf = (String) statusFilter.getSelectedItem();
            String qf = (String) quickFilter.getSelectedItem();
            StringBuilder sql = new StringBuilder(
                "SELECT b.id,c.name,r.room_number,b.check_in_date,b.check_out_date,b.num_guests,b.total_amount,b.status " +
                "FROM bookings b JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id WHERE 1=1");
            if (sf != null && !sf.equals("All")) sql.append(" AND b.status=?");
            if (qf != null) {
                switch(qf) {
                    case "Today": sql.append(" AND DATE(b.created_at)=CURDATE()"); break;
                    case "This Week": sql.append(" AND YEARWEEK(b.created_at)=YEARWEEK(CURDATE())"); break;
                    case "This Month": sql.append(" AND MONTH(b.created_at)=MONTH(CURDATE()) AND YEAR(b.created_at)=YEAR(CURDATE())"); break;
                }
            }
            sql.append(" ORDER BY b.created_at DESC");
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
                if (sf != null && !sf.equals("All")) ps.setString(1, sf);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getDate(5), rs.getInt(6), String.format("%,.2f", rs.getDouble(7)), rs.getString(8)});
                }
            } catch (SQLException ex) { showMsg(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        };
        panel.putClientProperty("refresher", refreshData);
        refreshData.run();

        statusFilter.addActionListener(e -> refreshData.run());
        quickFilter.addActionListener(e -> refreshData.run());
        refBtn.addActionListener(e -> refreshData.run());

        newBtn.addActionListener(e -> showNewBookingDialog(refreshData));

        // Double-click for detail
        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow(); if (row < 0) return;
                    int bookingId = (int) model.getValueAt(row, 0);
                    showBookingDetail(bookingId);
                }
            }
        });

        checkInBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a booking.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            int bid = (int) model.getValueAt(row, 0); String st = (String) model.getValueAt(row, 7);
            if (!"Reserved".equals(st)) { showMsg(panel, "Only 'Reserved' bookings can be checked in.", "Info", JOptionPane.WARNING_MESSAGE); return; }
            if (showConfirm(panel, "Check in this booking?", "Confirm") == JOptionPane.YES_OPTION) {
                try (Connection conn = getConnection()) {
                    conn.setAutoCommit(false);
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE bookings SET status='Checked-In', actual_check_in=NOW() WHERE id=?")) { ps.setInt(1, bid); ps.executeUpdate(); }
                    int roomId = 0;
                    try (PreparedStatement ps = conn.prepareStatement("SELECT room_id FROM bookings WHERE id=?")) { ps.setInt(1, bid); ResultSet rs = ps.executeQuery(); if (rs.next()) roomId = rs.getInt(1); }
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE rooms SET status='Booked' WHERE id=?")) { ps.setInt(1, roomId); ps.executeUpdate(); }
                    conn.commit();
                    logAudit("CHECK_IN", "BOOKING", bid, "Guest checked in");
                    showMsg(panel, "Checked in!", "Success", JOptionPane.INFORMATION_MESSAGE); refreshData.run();
                } catch (SQLException ex) { showMsg(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
            }
        });

        checkOutBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a booking.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            int bid = (int) model.getValueAt(row, 0); String st = (String) model.getValueAt(row, 7);
            if (!"Checked-In".equals(st)) { showMsg(panel, "Only 'Checked-In' bookings can be checked out.", "Info", JOptionPane.WARNING_MESSAGE); return; }
            if (showConfirm(panel, "Check out this booking?\nProceed to Billing for payment.", "Confirm") == JOptionPane.YES_OPTION) {
                try (Connection conn = getConnection()) {
                    conn.setAutoCommit(false);
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE bookings SET status='Checked-Out', actual_check_out=NOW() WHERE id=?")) { ps.setInt(1, bid); ps.executeUpdate(); }
                    int roomId = 0;
                    try (PreparedStatement ps = conn.prepareStatement("SELECT room_id FROM bookings WHERE id=?")) { ps.setInt(1, bid); ResultSet rs = ps.executeQuery(); if (rs.next()) roomId = rs.getInt(1); }
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE rooms SET status='Available' WHERE id=?")) { ps.setInt(1, roomId); ps.executeUpdate(); }
                    conn.commit();
                    logAudit("CHECK_OUT", "BOOKING", bid, "Guest checked out");
                    showMsg(panel, "Checked out! Go to Billing to generate invoice.", "Success", JOptionPane.INFORMATION_MESSAGE); refreshData.run();
                } catch (SQLException ex) { showMsg(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
            }
        });

        cancelBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a booking.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            int bid = (int) model.getValueAt(row, 0); String st = (String) model.getValueAt(row, 7);
            if ("Checked-Out".equals(st) || "Cancelled".equals(st)) { showMsg(panel, "Cannot cancel — already " + st, "Info", JOptionPane.WARNING_MESSAGE); return; }
            if (showConfirm(panel, "Cancel this booking?", "Confirm") == JOptionPane.YES_OPTION) {
                try (Connection conn = getConnection()) {
                    conn.setAutoCommit(false);
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE bookings SET status='Cancelled' WHERE id=?")) { ps.setInt(1, bid); ps.executeUpdate(); }
                    int roomId = 0;
                    try (PreparedStatement ps = conn.prepareStatement("SELECT room_id FROM bookings WHERE id=?")) { ps.setInt(1, bid); ResultSet rs = ps.executeQuery(); if (rs.next()) roomId = rs.getInt(1); }
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE rooms SET status='Available' WHERE id=?")) { ps.setInt(1, roomId); ps.executeUpdate(); }
                    conn.commit();
                    logAudit("CANCEL_BOOKING", "BOOKING", bid, "Booking cancelled");
                    showMsg(panel, "Booking cancelled.", "Success", JOptionPane.INFORMATION_MESSAGE); refreshData.run();
                } catch (SQLException ex) { showMsg(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
            }
        });
        return panel;
    }

    static void showBookingDetail(int bookingId) {
        JDialog dlg = new JDialog(mainFrame, "Booking Detail", true);
        dlg.setSize(520, 500); dlg.setLocationRelativeTo(mainFrame);
        JPanel p = new JPanel(); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(cardBg()); p.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
            "SELECT b.*, c.name cname, c.phone cphone, c.email cemail, r.room_number rnum, r.room_type rtype, r.price_per_night rprice " +
            "FROM bookings b JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id WHERE b.id=?")) {
            ps.setInt(1, bookingId); ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                addDetailRow(p, "Status", rs.getString("status"));
                addDetailRow(p, "Customer", rs.getString("cname") + " (" + rs.getString("cphone") + ")");
                addDetailRow(p, "Email", rs.getString("cemail"));
                addDetailRow(p, "Room", rs.getString("rnum") + " — " + rs.getString("rtype"));
                addDetailRow(p, "Price/Night", "\u20B9 " + String.format("%,.2f", rs.getDouble("rprice")));
                addDetailRow(p, "Check-In", rs.getDate("check_in_date").toString());
                addDetailRow(p, "Check-Out", rs.getDate("check_out_date").toString());
                if (rs.getTimestamp("actual_check_in") != null) addDetailRow(p, "Actual Check-In", rs.getTimestamp("actual_check_in").toString());
                if (rs.getTimestamp("actual_check_out") != null) addDetailRow(p, "Actual Check-Out", rs.getTimestamp("actual_check_out").toString());
                addDetailRow(p, "Guests", String.valueOf(rs.getInt("num_guests")));
                addDetailRow(p, "Total Amount", "\u20B9 " + String.format("%,.2f", rs.getDouble("total_amount")));

                // Check if paid
                try (PreparedStatement ps2 = conn.prepareStatement("SELECT receipt_number, payment_mode, total_with_tax FROM payments WHERE booking_id=?")) {
                    ps2.setInt(1, bookingId); ResultSet rs2 = ps2.executeQuery();
                    if (rs2.next()) {
                        addDetailRow(p, "Payment", "\u2713 Paid — " + rs2.getString("payment_mode") + " (" + rs2.getString("receipt_number") + ")");
                    } else {
                        addDetailRow(p, "Payment", "\u2717 Unpaid");
                    }
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }

        p.add(Box.createVerticalStrut(16));
        JButton cb = createBtn("Close", new Color(55, 65, 81)); cb.setAlignmentX(Component.LEFT_ALIGNMENT);
        cb.addActionListener(e -> dlg.dispose()); p.add(cb);
        JScrollPane scroll = new JScrollPane(p); scroll.setBorder(null); scroll.getViewport().setBackground(cardBg());
        dlg.setContentPane(scroll); dlg.setVisible(true);
    }

    static void addDetailRow(JPanel p, String label, String value) {
        JPanel row = new JPanel(new BorderLayout()); row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        JLabel lbl = new JLabel(label + ":"); lbl.setFont(F_BODY); lbl.setForeground(txtM()); lbl.setPreferredSize(new Dimension(150, 24));
        JLabel val = new JLabel(value); val.setFont(new Font("Segoe UI", Font.BOLD, 14)); val.setForeground(txtP());
        row.add(lbl, BorderLayout.WEST); row.add(val, BorderLayout.CENTER);
        p.add(row);
    }

    static void showNewBookingDialog(Runnable onSave) {
        JDialog dlg = new JDialog(mainFrame, "New Booking", true);
        dlg.setSize(560, 580); dlg.setLocationRelativeTo(mainFrame);
        JPanel form = new JPanel(); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(cardBg()); form.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        JComboBox<String> custBox = new JComboBox<>(); styleCombo(custBox);
        HashMap<String, Integer> custMap = new HashMap<>();
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT id,name,phone FROM customers ORDER BY name")) {
            while (rs.next()) { String d = rs.getString("name") + " (" + rs.getString("phone") + ")"; custBox.addItem(d); custMap.put(d, rs.getInt("id")); }
        } catch (SQLException ex) { ex.printStackTrace(); }

        JComboBox<String> typeBox = createCombo(new String[]{"All", "Single", "Double", "Deluxe", "Suite"});
        JComboBox<String> roomBox = new JComboBox<>(); styleCombo(roomBox);
        HashMap<String, Integer> roomMap = new HashMap<>();
        HashMap<String, Double> roomPriceMap = new HashMap<>();

        Runnable loadRooms = () -> {
            roomBox.removeAllItems(); roomMap.clear(); roomPriceMap.clear();
            String tp = (String) typeBox.getSelectedItem();
            String sql = "SELECT id,room_number,room_type,price_per_night FROM rooms WHERE status='Available'";
            if (tp != null && !tp.equals("All")) sql += " AND room_type=?";
            sql += " ORDER BY id ASC";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                if (tp != null && !tp.equals("All")) ps.setString(1, tp);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String d = rs.getString("room_number") + " — " + rs.getString("room_type") + " (\u20B9" + String.format("%,.0f", rs.getDouble("price_per_night")) + "/night)";
                    roomBox.addItem(d); roomMap.put(d, rs.getInt("id")); roomPriceMap.put(d, rs.getDouble("price_per_night"));
                }
            } catch (SQLException ex) { ex.printStackTrace(); }
        };
        loadRooms.run();
        typeBox.addActionListener(e -> loadRooms.run());

        JTextField ciF = createField(); ciF.setText(LocalDate.now().toString());
        JTextField coF = createField(); coF.setText(LocalDate.now().plusDays(1).toString());
        JTextField gF = createField(); gF.setText("1");

        JLabel amtLbl = new JLabel("\u20B9 0.00"); amtLbl.setFont(new Font("Segoe UI", Font.BOLD, 16)); amtLbl.setForeground(SUCCESS_CLR);

        Runnable calc = () -> {
            try {
                LocalDate ci = LocalDate.parse(ciF.getText().trim()), co = LocalDate.parse(coF.getText().trim());
                long n = ChronoUnit.DAYS.between(ci, co); String sr = (String) roomBox.getSelectedItem();
                if (n > 0 && sr != null && roomPriceMap.containsKey(sr)) {
                    amtLbl.setText(String.format("\u20B9 %,.2f (%d night%s)", n * roomPriceMap.get(sr), n, n > 1 ? "s" : ""));
                } else amtLbl.setText("\u20B9 0.00");
            } catch (Exception ex) { amtLbl.setText("Invalid dates"); }
        };
        ciF.addActionListener(e -> calc.run()); coF.addActionListener(e -> calc.run());
        ciF.addFocusListener(new FocusAdapter() { @Override public void focusLost(FocusEvent e) { calc.run(); } });
        coF.addFocusListener(new FocusAdapter() { @Override public void focusLost(FocusEvent e) { calc.run(); } });
        roomBox.addActionListener(e -> calc.run());

        form.add(createFormRow("Customer:", custBox)); form.add(createFormRow("Room Type:", typeBox));
        form.add(createFormRow("Room:", roomBox)); form.add(createFormRow("Check-In (YYYY-MM-DD):", ciF));
        form.add(createFormRow("Check-Out (YYYY-MM-DD):", coF)); form.add(createFormRow("Guests:", gF));
        form.add(Box.createVerticalStrut(8)); form.add(createFormRow("Estimated Total:", amtLbl));
        form.add(Box.createVerticalStrut(16));

        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0)); bp.setOpaque(false);
        JButton bookBtn = createBtn("Book Now", ACCENT); JButton canBtn = createBtn("Cancel", new Color(55, 65, 81));
        bp.add(bookBtn); bp.add(canBtn); form.add(bp);

        canBtn.addActionListener(e -> dlg.dispose());
        bookBtn.addActionListener(e -> {
            String sc = (String) custBox.getSelectedItem(), sr = (String) roomBox.getSelectedItem();
            if (sc == null || sr == null) { showMsg(dlg, "Select customer and room.", "Validation", JOptionPane.WARNING_MESSAGE); return; }
            try {
                LocalDate ci = LocalDate.parse(ciF.getText().trim()), co = LocalDate.parse(coF.getText().trim());
                int guests = Integer.parseInt(gF.getText().trim());
                if (!co.isAfter(ci)) { showMsg(dlg, "Check-out must be after check-in.", "Validation", JOptionPane.WARNING_MESSAGE); return; }
                if (guests < 1) { showMsg(dlg, "Guests must be >= 1.", "Validation", JOptionPane.WARNING_MESSAGE); return; }
                int custId = custMap.get(sc), roomId = roomMap.get(sr);
                long nights = ChronoUnit.DAYS.between(ci, co);
                double total = nights * roomPriceMap.get(sr);

                try (Connection conn = getConnection()) {
                    // Double-booking check
                    try (PreparedStatement ps = conn.prepareStatement(
                        "SELECT COUNT(*) FROM bookings WHERE room_id=? AND status IN ('Reserved','Checked-In') AND NOT (check_out_date<=? OR check_in_date>=?)")) {
                        ps.setInt(1, roomId); ps.setDate(2, java.sql.Date.valueOf(ci)); ps.setDate(3, java.sql.Date.valueOf(co));
                        ResultSet rs = ps.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) { showMsg(dlg, "Room already booked for selected dates.", "Double Booking", JOptionPane.ERROR_MESSAGE); return; }
                    }
                    conn.setAutoCommit(false);
                    int newId = 0;
                    try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO bookings (customer_id,room_id,check_in_date,check_out_date,status,num_guests,total_amount,created_by) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                        ps.setInt(1, custId); ps.setInt(2, roomId); ps.setDate(3, java.sql.Date.valueOf(ci));
                        ps.setDate(4, java.sql.Date.valueOf(co)); ps.setString(5, "Reserved");
                        ps.setInt(6, guests); ps.setDouble(7, total); ps.setInt(8, loggedInUserId);
                        ps.executeUpdate();
                        ResultSet keys = ps.getGeneratedKeys(); if (keys.next()) newId = keys.getInt(1);
                    }
                    conn.commit();
                    logAudit("CREATE_BOOKING", "BOOKING", newId, "Booking created — Room " + sr.split(" ")[0]);
                    showMsg(dlg, String.format("Booking created!\nRoom: %s\nTotal: \u20B9 %,.2f for %d night(s)", sr, total, nights), "Success", JOptionPane.INFORMATION_MESSAGE);
                    dlg.dispose(); onSave.run();
                }
            } catch (Exception ex) { showMsg(dlg, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        });
        calc.run();
        JScrollPane scroll = new JScrollPane(form); scroll.setBorder(null); scroll.getViewport().setBackground(cardBg());
        dlg.setContentPane(scroll); dlg.setVisible(true);
    }

    // ═══════════════════════════════════════════════════════════════
    //  BILLING & PAYMENTS
    // ═══════════════════════════════════════════════════════════════
    static JPanel buildBillingPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(bg());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel title = new JLabel("Billing & Payments");
        title.setFont(F_TITLE); title.setForeground(txtP());
        panel.add(title, BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(0, 12)); body.setOpaque(false);

        JPanel unpaidCard = createCard("Unpaid Bookings (Checked-Out)");
        DefaultTableModel unpaidM = new DefaultTableModel(new String[]{"Booking", "Customer", "Room", "Check-In", "Check-Out", "Amount (\u20B9)"}, 0);
        JTable unpaidT = createTable(unpaidM);
        unpaidCard.add(createScroll(unpaidT), BorderLayout.CENTER);

        JPanel paidCard = createCard("Payment History");
        DefaultTableModel paidM = new DefaultTableModel(new String[]{"Receipt", "Booking", "Base", "GST", "Total", "Mode", "Date"}, 0);
        JTable paidT = createTable(paidM);
        paidCard.add(createScroll(paidT), BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, unpaidCard, paidCard);
        split.setDividerLocation(280); split.setBackground(bg()); split.setBorder(null);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); toolbar.setOpaque(false);
        JButton genBtn = createBtn("Generate Bill", ACCENT);
        JButton printBtn = createBtn("Print Invoice", WARNING_CLR);
        JButton refBtn = createBtn("Refresh", new Color(55, 65, 81));
        toolbar.add(genBtn); toolbar.add(printBtn); toolbar.add(refBtn);

        body.add(toolbar, BorderLayout.NORTH);
        body.add(split, BorderLayout.CENTER);
        panel.add(body, BorderLayout.CENTER);

        Runnable refreshData = () -> {
            unpaidM.setRowCount(0);
            try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                "SELECT b.id,c.name,r.room_number,b.check_in_date,b.check_out_date,b.total_amount FROM bookings b " +
                "JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id " +
                "WHERE b.status='Checked-Out' AND b.id NOT IN (SELECT booking_id FROM payments) ORDER BY b.id DESC")) {
                while (rs.next()) unpaidM.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), String.format("%,.2f", rs.getDouble(6))});
            } catch (SQLException ex) { ex.printStackTrace(); }
            paidM.setRowCount(0);
            try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                "SELECT p.receipt_number,p.booking_id,p.amount,p.gst_amount,p.total_with_tax,p.payment_mode,p.payment_date FROM payments p ORDER BY p.id DESC")) {
                while (rs.next()) paidM.addRow(new Object[]{rs.getString(1), rs.getInt(2), String.format("%,.2f", rs.getDouble(3)),
                    String.format("%,.2f", rs.getDouble(4)), String.format("%,.2f", rs.getDouble(5)), rs.getString(6), rs.getTimestamp(7)});
            } catch (SQLException ex) { ex.printStackTrace(); }
        };
        panel.putClientProperty("refresher", refreshData);
        refreshData.run();
        refBtn.addActionListener(e -> refreshData.run());

        genBtn.addActionListener(e -> {
            int row = unpaidT.getSelectedRow(); if (row < 0) { showMsg(panel, "Select an unpaid booking.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            int bid = (int) unpaidM.getValueAt(row, 0); double amt = Double.parseDouble(((String) unpaidM.getValueAt(row, 5)).replace(",", ""));
            showPaymentDialog(bid, amt, refreshData);
        });
        printBtn.addActionListener(e -> {
            int row = paidT.getSelectedRow(); if (row < 0) { showMsg(panel, "Select a payment.", "Info", JOptionPane.INFORMATION_MESSAGE); return; }
            showInvoice((int) paidM.getValueAt(row, 1), (String) paidM.getValueAt(row, 0));
        });
        return panel;
    }

    static void showPaymentDialog(int bookingId, double baseAmt, Runnable onSave) {
        JDialog dlg = new JDialog(mainFrame, "Payment", true);
        dlg.setSize(550, 520); dlg.setLocationRelativeTo(mainFrame);

        JPanel main = new JPanel(new BorderLayout()); main.setBackground(cardBg());

        JPanel form = new JPanel(); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(cardBg()); form.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        double gst = baseAmt * GST_RATE, total = baseAmt + gst;

        JLabel amtL = new JLabel("\u20B9 " + String.format("%,.2f", baseAmt)); amtL.setFont(new Font("Segoe UI", Font.BOLD, 16)); amtL.setForeground(txtP());
        JLabel gstL = new JLabel("\u20B9 " + String.format("%,.2f (18%%)", gst)); gstL.setFont(new Font("Segoe UI", Font.BOLD, 16)); gstL.setForeground(WARNING_CLR);
        JLabel totL = new JLabel("\u20B9 " + String.format("%,.2f", total)); totL.setFont(new Font("Segoe UI", Font.BOLD, 22)); totL.setForeground(SUCCESS_CLR);

        JComboBox<String> modeBox = createCombo(new String[]{"Cash", "Card", "UPI"});

        form.add(createFormRow("Base Amount:", amtL)); form.add(createFormRow("GST (18%):", gstL));
        form.add(Box.createVerticalStrut(4));
        JSeparator sep = new JSeparator(); sep.setForeground(border()); sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        form.add(sep); form.add(Box.createVerticalStrut(4));
        form.add(createFormRow("Total Payable:", totL)); form.add(Box.createVerticalStrut(8));
        modeBox.setPreferredSize(new Dimension(160, 38));
        modeBox.setMaximumSize(new Dimension(160, 38));
        JPanel modeWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        modeWrapper.setOpaque(false);
        modeWrapper.add(modeBox);
        form.add(createFormRow("Payment Mode:", modeWrapper)); form.add(Box.createVerticalStrut(12));

        // [MODIFIED] UPI Payment Info section (QR removed; displays UPI ID directly)
        JPanel upiInfoPanel = new JPanel();
        upiInfoPanel.setLayout(new BoxLayout(upiInfoPanel, BoxLayout.Y_AXIS));
        upiInfoPanel.setOpaque(false);
        upiInfoPanel.setVisible(false);
        upiInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        upiInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        upiInfoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT, 1, true),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)));

        JLabel upiTitle = new JLabel("Pay via UPI"); upiTitle.setFont(F_SUBHEADER); upiTitle.setForeground(txtP());
        JLabel upiIdLabel = new JLabel("UPI ID:  " + HOTEL_UPI_ID); upiIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); upiIdLabel.setForeground(ACCENT);
        JLabel upiAmtLabel = new JLabel("Amount: \u20B9 " + String.format("%,.2f", total)); upiAmtLabel.setFont(F_BODY); upiAmtLabel.setForeground(SUCCESS_CLR);
        JLabel upiNote = new JLabel("Send payment to the above UPI ID using GPay / PhonePe / Paytm"); upiNote.setFont(F_TINY); upiNote.setForeground(txtM());

        upiInfoPanel.add(upiTitle); upiInfoPanel.add(Box.createVerticalStrut(6));
        upiInfoPanel.add(upiIdLabel); upiInfoPanel.add(Box.createVerticalStrut(4));
        upiInfoPanel.add(upiAmtLabel); upiInfoPanel.add(Box.createVerticalStrut(6));
        upiInfoPanel.add(upiNote);
        form.add(upiInfoPanel);

        modeBox.addActionListener(e -> upiInfoPanel.setVisible("UPI".equals(modeBox.getSelectedItem())));

        form.add(Box.createVerticalStrut(12));
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0)); bp.setOpaque(false);
        JButton payBtn = createBtn("Confirm Payment", SUCCESS_CLR); JButton canBtn = createBtn("Cancel", new Color(55, 65, 81));
        bp.add(payBtn); bp.add(canBtn); form.add(bp);

        canBtn.addActionListener(ev -> dlg.dispose());
        payBtn.addActionListener(ev -> {
            String mode = (String) modeBox.getSelectedItem();
            String receipt = "RCP" + System.currentTimeMillis();
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO payments (booking_id,amount,gst_amount,total_with_tax,payment_mode,receipt_number) VALUES (?,?,?,?,?,?)")) {
                ps.setInt(1, bookingId); ps.setDouble(2, baseAmt); ps.setDouble(3, gst);
                ps.setDouble(4, total); ps.setString(5, mode); ps.setString(6, receipt); ps.executeUpdate();
                logAudit("PAYMENT", "PAYMENT", bookingId, "Payment " + receipt + " — " + mode + " — \u20B9" + String.format("%,.2f", total));
                showMsg(dlg, String.format("Payment recorded!\nReceipt: %s\nTotal: \u20B9 %,.2f\nMode: %s", receipt, total, mode), "Success", JOptionPane.INFORMATION_MESSAGE);
                dlg.dispose(); onSave.run();
            } catch (SQLException ex) { showMsg(dlg, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        });

        JScrollPane payScroll = new JScrollPane(form); payScroll.setBorder(null); payScroll.getViewport().setBackground(cardBg());
        main.add(payScroll, BorderLayout.CENTER);
        dlg.setContentPane(main); dlg.setVisible(true);
    }

    // [MODIFIED] QR code rendering removed — UPI ID is displayed directly in payment dialog

    static void showInvoice(int bookingId, String receiptNo) {
        JDialog dlg = new JDialog(mainFrame, "Invoice — " + receiptNo, true);
        dlg.setSize(600, 750); dlg.setLocationRelativeTo(mainFrame);

        JPanel inv = new JPanel(); inv.setLayout(new BoxLayout(inv, BoxLayout.Y_AXIS));
        inv.setBackground(Color.WHITE); inv.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        inv.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font invTitle = new Font("Segoe UI", Font.BOLD, 22);
        Font invHead = new Font("Segoe UI", Font.BOLD, 14);
        Font invBody = new Font("Segoe UI", Font.PLAIN, 13);
        Color invDark = new Color(11, 19, 43);

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
            "SELECT c.name,c.phone,c.email,r.room_number,r.room_type,b.check_in_date,b.check_out_date,b.num_guests," +
            "p.amount,p.gst_amount,p.total_with_tax,p.payment_mode,p.payment_date " +
            "FROM payments p JOIN bookings b ON p.booking_id=b.id JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id WHERE p.receipt_number=?")) {
            ps.setString(1, receiptNo); ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Header
                JLabel hn = new JLabel(HOTEL_NAME); hn.setFont(invTitle); hn.setForeground(invDark); hn.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel ha = new JLabel(HOTEL_ADDRESS + "  |  " + HOTEL_PHONE); ha.setFont(invBody); ha.setForeground(Color.GRAY); ha.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel it = new JLabel("TAX INVOICE"); it.setFont(new Font("Segoe UI", Font.BOLD, 16)); it.setForeground(invDark); it.setAlignmentX(Component.CENTER_ALIGNMENT);
                it.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
                inv.add(hn); inv.add(ha); inv.add(it);
                inv.add(makeSep()); inv.add(Box.createVerticalStrut(8));

                addInvLine(inv, "Receipt No:", receiptNo, invBody); addInvLine(inv, "Date:", rs.getTimestamp(13).toString(), invBody);
                inv.add(Box.createVerticalStrut(8)); inv.add(makeSep()); inv.add(Box.createVerticalStrut(8));

                JLabel gt = new JLabel("GUEST DETAILS"); gt.setFont(invHead); gt.setForeground(invDark); gt.setAlignmentX(Component.CENTER_ALIGNMENT); inv.add(gt); inv.add(Box.createVerticalStrut(4));
                addInvLine(inv, "Name:", rs.getString(1), invBody); addInvLine(inv, "Phone:", rs.getString(2), invBody); addInvLine(inv, "Email:", rs.getString(3), invBody);
                inv.add(Box.createVerticalStrut(8));

                JLabel st = new JLabel("STAY DETAILS"); st.setFont(invHead); st.setForeground(invDark); st.setAlignmentX(Component.CENTER_ALIGNMENT); inv.add(st); inv.add(Box.createVerticalStrut(4));
                addInvLine(inv, "Room:", rs.getString(4) + " (" + rs.getString(5) + ")", invBody);
                addInvLine(inv, "Check-In:", rs.getDate(6).toString(), invBody); addInvLine(inv, "Check-Out:", rs.getDate(7).toString(), invBody);
                addInvLine(inv, "Guests:", String.valueOf(rs.getInt(8)), invBody);
                inv.add(Box.createVerticalStrut(8)); inv.add(makeSep()); inv.add(Box.createVerticalStrut(8));

                JLabel pt = new JLabel("PAYMENT SUMMARY"); pt.setFont(invHead); pt.setForeground(invDark); pt.setAlignmentX(Component.CENTER_ALIGNMENT); inv.add(pt); inv.add(Box.createVerticalStrut(4));
                addInvLine(inv, "Room Charges:", "\u20B9 " + String.format("%,.2f", rs.getDouble(9)), invBody);
                addInvLine(inv, "GST (18%):", "\u20B9 " + String.format("%,.2f", rs.getDouble(10)), invBody);
                inv.add(makeSep()); inv.add(Box.createVerticalStrut(4));

                JPanel totalRow = new JPanel(new BorderLayout()); totalRow.setBackground(Color.WHITE);
                totalRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28)); totalRow.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel tl = new JLabel("TOTAL PAID:"); tl.setFont(new Font("Segoe UI", Font.BOLD, 16)); tl.setForeground(invDark);
                JLabel tv = new JLabel("\u20B9 " + String.format("%,.2f", rs.getDouble(11))); tv.setFont(new Font("Segoe UI", Font.BOLD, 16)); tv.setForeground(new Color(16, 185, 94));
                tv.setHorizontalAlignment(JLabel.RIGHT); totalRow.add(tl, BorderLayout.WEST); totalRow.add(tv, BorderLayout.EAST); inv.add(totalRow);
                inv.add(Box.createVerticalStrut(4));
                addInvLine(inv, "Payment Mode:", rs.getString(12), invBody);
                inv.add(Box.createVerticalStrut(16));
                JLabel ty = new JLabel("Thank you for staying with us!"); ty.setFont(new Font("Segoe UI", Font.ITALIC, 13)); ty.setForeground(Color.GRAY);
                ty.setAlignmentX(Component.CENTER_ALIGNMENT); inv.add(ty);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10)); btns.setBackground(cardBg());
        JButton printBtn = createBtn("Print", ACCENT);
        printBtn.addActionListener(ev -> {
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable((graphics, pf, pi) -> {
                if (pi > 0) return Printable.NO_SUCH_PAGE;
                Graphics2D g2d = (Graphics2D) graphics;
                double sc = Math.min(pf.getImageableWidth() / inv.getWidth(), pf.getImageableHeight() / inv.getHeight());
                double xOff = (pf.getImageableWidth() - inv.getWidth() * sc) / 2.0;
                double yOff = (pf.getImageableHeight() - inv.getHeight() * sc) / 4.0;
                g2d.translate(pf.getImageableX() + xOff, pf.getImageableY() + yOff);
                g2d.scale(sc, sc); inv.printAll(g2d); return Printable.PAGE_EXISTS;
            });
            if (pj.printDialog()) { try { pj.print(); } catch (PrinterException ex) { showMsg(dlg, "Print error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } }
        });
        JButton closeBtn = createBtn("Close", DANGER_CLR); closeBtn.addActionListener(ev -> dlg.dispose());
        btns.add(printBtn); btns.add(closeBtn);

        JPanel wrapper = new JPanel(new BorderLayout()); wrapper.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(inv); scroll.setBorder(null); scroll.getViewport().setBackground(Color.WHITE);
        wrapper.add(scroll, BorderLayout.CENTER); wrapper.add(btns, BorderLayout.SOUTH);
        dlg.setContentPane(wrapper); dlg.setVisible(true);
    }

    static JSeparator makeSep() {
        JSeparator s = new JSeparator(); s.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); s.setForeground(Color.LIGHT_GRAY); return s;
    }

    static void addInvLine(JPanel panel, String label, String value, Font font) {
        JPanel row = new JPanel(new BorderLayout()); row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22)); row.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lbl = new JLabel(label); lbl.setFont(new Font(font.getName(), Font.PLAIN, 13)); lbl.setForeground(Color.GRAY);
        JLabel val = new JLabel(value); val.setFont(font); val.setForeground(new Color(11, 19, 43)); val.setHorizontalAlignment(JLabel.RIGHT);
        row.add(lbl, BorderLayout.WEST); row.add(val, BorderLayout.EAST); panel.add(row); panel.add(Box.createVerticalStrut(2));
    }

    // ═══════════════════════════════════════════════════════════════
    //  REPORTS
    // ═══════════════════════════════════════════════════════════════
    static JPanel buildReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(bg());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel title = new JLabel("Reports & Analytics");
        title.setFont(F_TITLE); title.setForeground(txtP());
        panel.add(title, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(F_BODY); tabs.setBackground(cardBg()); tabs.setForeground(txtP());
        tabs.setOpaque(true);
        tabs.setBorder(BorderFactory.createLineBorder(border()));
        // Custom UI for dark mode tab rendering
        tabs.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override protected void installDefaults() {
                super.installDefaults();
                contentBorderInsets = new Insets(1, 0, 0, 0);
                tabInsets = new Insets(8, 16, 8, 16);
                selectedTabPadInsets = new Insets(0, 0, 0, 0);
            }
            @Override protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (isSelected) {
                    g2.setColor(cardBg());
                } else {
                    g2.setColor(sidebarBg());
                }
                g2.fillRect(x, y, w, h);
                if (isSelected) {
                    g2.setColor(ACCENT);
                    g2.fillRect(x, y + h - 3, w, 3);
                }
                g2.dispose();
            }
            @Override protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(border());
                g2.drawLine(x, y, x + w, y);
                g2.drawLine(x, y, x, y + h);
                g2.drawLine(x + w, y, x + w, y + h);
                g2.dispose();
            }
            @Override protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(border());
                int y = calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
                g2.drawLine(0, y, tabPane.getWidth(), y);
                g2.dispose();
            }
            @Override protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {}
            @Override protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(sidebarBg());
                g2.fillRect(0, 0, tabPane.getWidth(), calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight));
                g2.dispose();
                super.paintTabArea(g, tabPlacement, selectedIndex);
            }
        });

        tabs.addTab("  Revenue  ", buildRevenueReportTab());
        tabs.addTab("  Bookings  ", buildBookingReportTab());
        tabs.addTab("  Customers  ", buildCustomerReportTab());
        tabs.addTab("  Room Utilization  ", buildRoomUtilTab());
        tabs.addTab("  Cancellations  ", buildCancelReportTab());

        panel.add(tabs, BorderLayout.CENTER);

        Runnable refresher = () -> {
            for (int i = 0; i < tabs.getTabCount(); i++) {
                JPanel tab = (JPanel) tabs.getComponentAt(i);
                Runnable loader = (Runnable) tab.getClientProperty("loader");
                if (loader != null) loader.run();
            }
        };
        panel.putClientProperty("refresher", refresher);
        return panel;
    }

    static JPanel buildRevenueReportTab() {
        JPanel tab = new JPanel(new BorderLayout(0, 12));
        tab.setBackground(bg()); tab.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel summaryRow = new JPanel(new GridLayout(1, 4, 12, 0)); summaryRow.setOpaque(false);
        JPanel totalRevC = buildKPICard("Total Revenue", "\u20B9 0", ACCENT, "\u20B9");
        JPanel totalGstC = buildKPICard("Total GST", "\u20B9 0", WARNING_CLR, "%");
        JPanel totalPayC = buildKPICard("Payments", "0", SUCCESS_CLR, "#");
        JPanel avgRevC = buildKPICard("Avg. Booking", "\u20B9 0", PURPLE_CLR, "\u00F8");
        summaryRow.add(totalRevC); summaryRow.add(totalGstC); summaryRow.add(totalPayC); summaryRow.add(avgRevC);
        summaryRow.setPreferredSize(new Dimension(0, 105));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Receipt", "Customer", "Room", "Base", "GST", "Total", "Mode", "Date"}, 0);
        JTable table = createTable(model);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); toolbar.setOpaque(false);
        JTextField fromF = createField(); fromF.setPreferredSize(new Dimension(110, 36)); fromF.setFont(F_SMALL); fromF.setText(LocalDate.now().minusMonths(1).toString());
        JTextField toF = createField(); toF.setPreferredSize(new Dimension(110, 36)); toF.setFont(F_SMALL); toF.setText(LocalDate.now().toString());
        JButton filterBtn = createBtn("Filter", ACCENT); JButton csvBtn = createBtn("Export CSV", new Color(55, 65, 81));
        toolbar.add(createLabel("From:")); toolbar.add(fromF); toolbar.add(createLabel("To:")); toolbar.add(toF);
        toolbar.add(filterBtn); toolbar.add(csvBtn);

        JPanel topP = new JPanel(new BorderLayout(0, 10)); topP.setOpaque(false);
        topP.add(toolbar, BorderLayout.NORTH); topP.add(summaryRow, BorderLayout.SOUTH);
        tab.add(topP, BorderLayout.NORTH);
        tab.add(createScroll(table), BorderLayout.CENTER);

        Runnable load = () -> {
            model.setRowCount(0);
            try (Connection conn = getConnection()) {
                try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                    "SELECT COALESCE(SUM(total_with_tax),0),COALESCE(SUM(gst_amount),0),COUNT(*),COALESCE(AVG(total_with_tax),0) FROM payments")) {
                    if (rs.next()) {
                        updateKPI(totalRevC, "\u20B9 " + String.format("%,.0f", rs.getDouble(1)));
                        updateKPI(totalGstC, "\u20B9 " + String.format("%,.0f", rs.getDouble(2)));
                        updateKPI(totalPayC, String.valueOf(rs.getInt(3)));
                        updateKPI(avgRevC, "\u20B9 " + String.format("%,.0f", rs.getDouble(4)));
                    }
                }
                try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT p.receipt_number,c.name,r.room_number,p.amount,p.gst_amount,p.total_with_tax,p.payment_mode,p.payment_date " +
                    "FROM payments p JOIN bookings b ON p.booking_id=b.id JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id " +
                    "WHERE DATE(p.payment_date) BETWEEN ? AND ? ORDER BY p.id DESC")) {
                    ps.setDate(1, java.sql.Date.valueOf(LocalDate.parse(fromF.getText().trim())));
                    ps.setDate(2, java.sql.Date.valueOf(LocalDate.parse(toF.getText().trim())));
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                        String.format("%,.2f", rs.getDouble(4)), String.format("%,.2f", rs.getDouble(5)),
                        String.format("%,.2f", rs.getDouble(6)), rs.getString(7), rs.getTimestamp(8)});
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        };
        tab.putClientProperty("loader", load);
        load.run();
        filterBtn.addActionListener(e -> load.run());
        csvBtn.addActionListener(e -> exportTableCSV(table, "revenue_report"));
        return tab;
    }

    static JPanel buildBookingReportTab() {
        JPanel tab = new JPanel(new BorderLayout(0, 12));
        tab.setBackground(bg()); tab.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Customer", "Room", "Type", "Check-In", "Check-Out", "Nights", "Amount", "Status"}, 0);
        JTable table = createTable(model);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); toolbar.setOpaque(false);
        JTextField fromF = createField(); fromF.setPreferredSize(new Dimension(110, 36)); fromF.setFont(F_SMALL); fromF.setText(LocalDate.now().minusMonths(1).toString());
        JTextField toF = createField(); toF.setPreferredSize(new Dimension(110, 36)); toF.setFont(F_SMALL); toF.setText(LocalDate.now().toString());
        JButton filterBtn = createBtn("Filter", ACCENT); JButton csvBtn = createBtn("Export CSV", new Color(55, 65, 81));
        toolbar.add(createLabel("From:")); toolbar.add(fromF); toolbar.add(createLabel("To:")); toolbar.add(toF);
        toolbar.add(filterBtn); toolbar.add(csvBtn);
        tab.add(toolbar, BorderLayout.NORTH);
        tab.add(createScroll(table), BorderLayout.CENTER);

        Runnable load = () -> {
            model.setRowCount(0);
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
                "SELECT b.id,c.name,r.room_number,r.room_type,b.check_in_date,b.check_out_date,DATEDIFF(b.check_out_date,b.check_in_date),b.total_amount,b.status " +
                "FROM bookings b JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id WHERE b.check_in_date>=? AND b.check_in_date<=? ORDER BY b.created_at DESC")) {
                ps.setDate(1, java.sql.Date.valueOf(LocalDate.parse(fromF.getText().trim())));
                ps.setDate(2, java.sql.Date.valueOf(LocalDate.parse(toF.getText().trim())));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) model.addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getDate(5), rs.getDate(6), rs.getInt(7), String.format("%,.2f", rs.getDouble(8)), rs.getString(9)});
            } catch (Exception ex) { ex.printStackTrace(); }
        };
        tab.putClientProperty("loader", load); load.run();
        filterBtn.addActionListener(e -> load.run());
        csvBtn.addActionListener(e -> exportTableCSV(table, "booking_report"));
        return tab;
    }

    static JPanel buildCustomerReportTab() {
        JPanel tab = new JPanel(new BorderLayout(0, 12));
        tab.setBackground(bg()); tab.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Phone", "Email", "Bookings", "Total Spent (\u20B9)", "Last Stay", "Class"}, 0);
        JTable table = createTable(model);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); toolbar.setOpaque(false);
        JButton refBtn = createBtn("Refresh", ACCENT); JButton csvBtn = createBtn("Export CSV", new Color(55, 65, 81));
        toolbar.add(refBtn); toolbar.add(csvBtn);
        tab.add(toolbar, BorderLayout.NORTH);
        tab.add(createScroll(table), BorderLayout.CENTER);

        Runnable load = () -> {
            model.setRowCount(0);
            try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                "SELECT c.id,c.name,c.phone,c.email,COUNT(b.id) bc,COALESCE(SUM(b.total_amount),0) sp,MAX(b.check_out_date) ls " +
                "FROM customers c LEFT JOIN bookings b ON c.id=b.customer_id GROUP BY c.id ORDER BY sp DESC")) {
                while (rs.next()) {
                    int bc = rs.getInt(5); double sp = rs.getDouble(6);
                    String cls = sp >= 50000 ? "VIP" : bc > 1 ? "Returning" : "New";
                    String ls = rs.getDate(7) != null ? rs.getDate(7).toString() : "N/A";
                    model.addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(4), bc, String.format("%,.2f", sp), ls, cls});
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        };
        tab.putClientProperty("loader", load); load.run();
        refBtn.addActionListener(e -> load.run());
        csvBtn.addActionListener(e -> exportTableCSV(table, "customer_report"));
        return tab;
    }

    static JPanel buildRoomUtilTab() {
        JPanel tab = new JPanel(new BorderLayout(0, 12));
        tab.setBackground(bg()); tab.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Room #", "Type", "Floor", "Price", "Total Bookings", "Revenue", "Current Status"}, 0);
        JTable table = createTable(model);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); toolbar.setOpaque(false);
        JButton refBtn = createBtn("Refresh", ACCENT); JButton csvBtn = createBtn("Export CSV", new Color(55, 65, 81));
        toolbar.add(refBtn); toolbar.add(csvBtn);
        tab.add(toolbar, BorderLayout.NORTH);
        tab.add(createScroll(table), BorderLayout.CENTER);

        Runnable load = () -> {
            model.setRowCount(0);
            try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                "SELECT r.room_number,r.room_type,r.floor,r.price_per_night,COUNT(b.id),COALESCE(SUM(b.total_amount),0),r.status " +
                "FROM rooms r LEFT JOIN bookings b ON r.id=b.room_id GROUP BY r.id ORDER BY r.id ASC")) {
                while (rs.next()) model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getInt(3),
                    String.format("%,.2f", rs.getDouble(4)), rs.getInt(5), String.format("%,.2f", rs.getDouble(6)), rs.getString(7)});
            } catch (Exception ex) { ex.printStackTrace(); }
        };
        tab.putClientProperty("loader", load); load.run();
        refBtn.addActionListener(e -> load.run());
        csvBtn.addActionListener(e -> exportTableCSV(table, "room_utilization"));
        return tab;
    }

    static JPanel buildCancelReportTab() {
        JPanel tab = new JPanel(new BorderLayout(0, 12));
        tab.setBackground(bg()); tab.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Customer", "Room", "Check-In", "Check-Out", "Amount", "Booked On"}, 0);
        JTable table = createTable(model);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); toolbar.setOpaque(false);
        JButton refBtn = createBtn("Refresh", ACCENT); JButton csvBtn = createBtn("Export CSV", new Color(55, 65, 81));
        toolbar.add(refBtn); toolbar.add(csvBtn);
        tab.add(toolbar, BorderLayout.NORTH);
        tab.add(createScroll(table), BorderLayout.CENTER);

        Runnable load = () -> {
            model.setRowCount(0);
            try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                "SELECT b.id,c.name,r.room_number,b.check_in_date,b.check_out_date,b.total_amount,b.created_at " +
                "FROM bookings b JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id WHERE b.status='Cancelled' ORDER BY b.created_at DESC")) {
                while (rs.next()) model.addRow(new Object[]{rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getDate(5), String.format("%,.2f", rs.getDouble(6)), rs.getTimestamp(7)});
            } catch (Exception ex) { ex.printStackTrace(); }
        };
        tab.putClientProperty("loader", load); load.run();
        refBtn.addActionListener(e -> load.run());
        csvBtn.addActionListener(e -> exportTableCSV(table, "cancellation_report"));
        return tab;
    }



    // ═══════════════════════════════════════════════════════════════
    //  GLOBAL SEARCH
    // ═══════════════════════════════════════════════════════════════
    static void showGlobalSearch(String query) {
        JDialog dlg = new JDialog(mainFrame, "Search Results — \"" + query + "\"", true);
        dlg.setSize(800, 500); dlg.setLocationRelativeTo(mainFrame);
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(cardBg()); p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel t = new JLabel("Search Results for: \"" + query + "\""); t.setFont(F_HEADER); t.setForeground(txtP());
        t.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0)); p.add(t, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Type", "Details", "Status"}, 0);
        JTable table = createTable(model);
        String like = "%" + query + "%";

        try (Connection conn = getConnection()) {
            // Search customers
            try (PreparedStatement ps = conn.prepareStatement("SELECT id,name,phone,email FROM customers WHERE name LIKE ? OR phone LIKE ? OR email LIKE ?")) {
                ps.setString(1, like); ps.setString(2, like); ps.setString(3, like);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) model.addRow(new Object[]{"Customer", rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4), ""});
            }
            // Search rooms
            try (PreparedStatement ps = conn.prepareStatement("SELECT id,room_number,room_type,status FROM rooms WHERE room_number LIKE ?")) {
                ps.setString(1, like); ResultSet rs = ps.executeQuery();
                while (rs.next()) model.addRow(new Object[]{"Room", "Room " + rs.getString(2) + " — " + rs.getString(3), rs.getString(4)});
            }
            // Search bookings by ID
            try {
                int bid = Integer.parseInt(query);
                try (PreparedStatement ps = conn.prepareStatement("SELECT b.id,c.name,r.room_number,b.status FROM bookings b JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id WHERE b.id=?")) {
                    ps.setInt(1, bid); ResultSet rs = ps.executeQuery();
                    while (rs.next()) model.addRow(new Object[]{"Booking", rs.getString(2) + " — Room " + rs.getString(3), rs.getString(4)});
                }
            } catch (NumberFormatException ignored) {}
            // Search bookings by customer name
            try (PreparedStatement ps = conn.prepareStatement("SELECT b.id,c.name,r.room_number,b.status FROM bookings b JOIN customers c ON b.customer_id=c.id JOIN rooms r ON b.room_id=r.id WHERE c.name LIKE ?")) {
                ps.setString(1, like); ResultSet rs = ps.executeQuery();
                while (rs.next()) model.addRow(new Object[]{"Booking", rs.getString(2) + " — Room " + rs.getString(3), rs.getString(4)});
            }
        } catch (SQLException ex) { ex.printStackTrace(); }

        p.add(createScroll(table), BorderLayout.CENTER);
        JButton cb = createBtn("Close", new Color(55, 65, 81)); cb.addActionListener(e -> dlg.dispose());
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT)); bp.setOpaque(false); bp.add(cb); p.add(bp, BorderLayout.SOUTH);
        dlg.setContentPane(p); dlg.setVisible(true);
    }

    // ═══════════════════════════════════════════════════════════════
    //  CSV EXPORT
    // ═══════════════════════════════════════════════════════════════
    // [MODIFIED] CSV export with theme-safe JFileChooser (fixes dark mode white dialog bug)
    static void exportTableCSV(JTable table, String filename) {
        // Temporarily reset FileChooser UI to system defaults so it renders correctly in dark/light mode
        javax.swing.LookAndFeel savedLF = UIManager.getLookAndFeel();
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new java.io.File(filename + "_" + LocalDate.now() + ".csv"));
        // Restore application theme after creating the file chooser
        try { UIManager.setLookAndFeel(savedLF); } catch (Exception ignored) {}
        applyDialogTheme();
        if (fc.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(fc.getSelectedFile()))) {
                TableModel m = table.getModel();
                // Headers
                StringBuilder hdr = new StringBuilder();
                for (int c = 0; c < m.getColumnCount(); c++) {
                    if (c > 0) hdr.append(",");
                    hdr.append("\"").append(m.getColumnName(c)).append("\"");
                }
                pw.println(hdr);
                // Rows
                for (int r = 0; r < m.getRowCount(); r++) {
                    StringBuilder row = new StringBuilder();
                    for (int c = 0; c < m.getColumnCount(); c++) {
                        if (c > 0) row.append(",");
                        Object val = m.getValueAt(r, c);
                        row.append("\"").append(val != null ? val.toString() : "").append("\"");
                    }
                    pw.println(row);
                }
                showMsg(mainFrame, "Exported to: " + fc.getSelectedFile().getName(), "Export Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) { showMsg(mainFrame, "Export error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        }
    }
}