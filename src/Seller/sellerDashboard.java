package Seller;

import static Seller.sellerDashboard.getStock;
import accounts.Login;
import accounts.UserManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.actionLogs;
import config.animation;
import config.databaseConnector;
import config.search;
import config.flatlaftTable;
import config.isAccountExist;
import config.sorter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.mindrot.jbcrypt.BCrypt;
import raven.toast.Notifications;

public final class sellerDashboard extends javax.swing.JFrame {

    int sellerID = UserManager.getLoggedInUserId();

    public sellerDashboard() {
        initComponents();
        Notifications.getInstance().setJFrame(this);
        product_search_bar.setFocusable(false);
        activity_search_bar.setFocusable(false);
        message_search_bar.setFocusable(false);
        orders_search_bar.setFocusable(false);
        archive_search_bar.setFocusable(false);

        getStatus.setFocusable(false);
        getStock.setFocusable(false);
        getPrice.setFocusable(false);
        getCategory.setFocusable(false);
        getDescription.setFocusable(false);
        getName.setFocusable(false);
        shop_username.setFocusable(false);
        shop_password.setFocusable(false);
        shop_name.setFocusable(false);
        shop_location.setFocusable(false);
        shop_fname.setFocusable(false);
        shop_lname.setFocusable(false);
        shop_number.setFocusable(false);
        shop_email.setFocusable(false);
        vieworder_notes.setFocusable(false);

        display_profile_picture();
        dashboard.setSelected(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30)); //jframe rounded border
        flatlaftTable.design(archiveAccountTableContainer, archive_table, archiveAccountTableContainerScroll); // display archive table
        searchBar(archive_search_bar);
        flatlaftTable.design(productsContainer, product_table, jScrollPane5); //seller logs
        searchBar(activity_search_bar);
        flatlaftTable.design(productsContainer1, actionlogs_table, jScrollPane9); //profile
        flatlaftTable.design(productsContainer, product_table, jScrollPane5); // product table\
        searchBar(product_search_bar);
        flatlaftTable.design(messages_container, messages_table, jScrollPane10); // messages table
        flatlaftTable.design(productsContainer2, purchase_table, jScrollPane7); // order table
        searchBar(orders_search_bar);
        flatlaftTable.design(productsContainer3, orders_table, jScrollPane11); // display archive table
        actionLogs.displaySellerLogs(actionlogs_table, sellerID); // display seller logs table
        flatlaftTable.design(productsContainer1, wishlist_table, jScrollPane8); // display wishlist table
        searchBar(wishlist_search_bar);

        displayCurrentDate(); // display current date in dashboard
        displayProducts(); //display Products
        displayRatingSumAndCount(); //display seller rating
        displayArchive(); // display archive table
        displayPurchase(); // display orders table
        messages(); // display messages for seller
        displayWishlistTable(); //display wishist table
        display_wishlist_dashboard(); //display top wishlist in dashboard
        display_best_selling_dashboard(); //display best seeling
        display_orders_dashboard(); //display order dashboard
        displayMessage4Admin(); //display message dashboard

        //display total sales for seller dashboard
        displayTotalSales(sellerID);
        displayTotalProducts(sellerID);
        displayTotalOrders(sellerID);
        displayTotalPendingOrders(sellerID);
        //

        //wishlist table
        searchBar(wishlist_search_bar);
        flatlaftTable.design(wishlist_container, wishlist_table, jScrollPane13); //wishlist table
        //

        //message for admin table
        UXmethods.RoundBorders.setArcStyle(filterContainer3, 10);
        searchBar(message_search_bar);
        flatlaftTable.design(messageContainer, message4admin_table, jScrollPane12); //message table
        UXmethods.RoundBorders.setArcStyle(l1, 20);
        UXmethods.RoundBorders.setArcStyle(l3, 5);
        UXmethods.RoundBorders.setArcStyle(a1, 5);
        UXmethods.RoundBorders.setArcStyle(a2, 5);
        UXmethods.RoundBorders.setArcStyle(a3, 5);
        UXmethods.RoundBorders.setArcStyle(a4, 5);
        UXmethods.RoundBorders.setArcStyle(title, 5);
        UXmethods.RoundBorders.setArcStyle(jPanel19, 10);
        UXmethods.RoundBorders.setArcStyle(help_status, 5);
        UXmethods.RoundBorders.setArcStyle(admin_submit, 10);

        //
        //ROUNDED CORNERS
        // dashboard & buttons
        UXmethods.RoundBorders.setArcStyle(logout, 50);
        UXmethods.RoundBorders.setArcStyle(dashboard, 50);
        UXmethods.RoundBorders.setArcStyle(manage, 50);
        UXmethods.RoundBorders.setArcStyle(orders, 50);
        UXmethods.RoundBorders.setArcStyle(archiveBtn, 50);
        UXmethods.RoundBorders.setArcStyle(admin_support, 50);
        //Containers
        UXmethods.RoundBorders.setArcStyle(CONTAINER, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER2, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER3, 15);
        //

        //archive
        UXmethods.RoundBorders.setArcStyle(c6, 10);
        UXmethods.RoundBorders.setArcStyle(c7, 10);
        UXmethods.RoundBorders.setArcStyle(c8, 10);
        UXmethods.RoundBorders.setArcStyle(c9, 10);
        UXmethods.RoundBorders.setArcStyle(restore, 10);
        UXmethods.RoundBorders.setArcStyle(delete, 10);
        UXmethods.RoundBorders.setArcStyle(jPanel14, 20);
        UXmethods.RoundBorders.setArcStyle(archive_search_bar, 10);
        //

        //profile tab
        UXmethods.RoundBorders.setArcStyle(addContainer4, 20);
        UXmethods.RoundBorders.setArcStyle(messages_container, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer5, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer6, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer7, 20);
        UXmethods.RoundBorders.setArcStyle(deactivate_jpanel, 10);
        UXmethods.RoundBorders.setArcStyle(c1, 10);
        UXmethods.RoundBorders.setArcStyle(c2, 10);
        UXmethods.RoundBorders.setArcStyle(c3, 10);
        UXmethods.RoundBorders.setArcStyle(c4, 10);
        UXmethods.RoundBorders.setArcStyle(c5, 10);
        UXmethods.RoundBorders.setArcStyle(c10, 10);
        UXmethods.RoundBorders.setArcStyle(z6, 10);
        c1.setFocusable(false);
        c2.setFocusable(false);
        c3.setFocusable(false);
        c4.setFocusable(false);
        c5.setFocusable(false);
        c10.setFocusable(false);
        c11.setFocusable(false);
        z6.setFocusable(false);
        descript.setFocusable(false);
        wishlist_search_bar.setFocusable(false);

        UXmethods.RoundBorders.setArcStyle(edit_seller_upload_button, 10);
        UXmethods.RoundBorders.setArcStyle(edit_seller_close_button, 5);
        UXmethods.RoundBorders.setArcStyle(edit_seller_save_button, 5);
        UXmethods.RoundBorders.setArcStyle(activity_search_bar, 10);
        UXmethods.RoundBorders.setArcStyle(filterContainer1, 10);

        UXmethods.RoundBorders.setArcStyle(add1, 10);
        UXmethods.RoundBorders.setArcStyle(add2, 10);
        UXmethods.RoundBorders.setArcStyle(add3, 10);
        UXmethods.RoundBorders.setArcStyle(add4, 10);
        UXmethods.RoundBorders.setArcStyle(add5, 10);
        UXmethods.RoundBorders.setArcStyle(edit_profile, 10);

        //orders table
        UXmethods.RoundBorders.setArcStyle(vieworder_container, 20);
        UXmethods.RoundBorders.setArcStyle(vieworder_container2, 20);
        UXmethods.RoundBorders.setArcStyle(vieworder_container3, 20);
        UXmethods.RoundBorders.setArcStyle(vieworder_container4, 20);
        UXmethods.RoundBorders.setArcStyle(jScrollPane2, 10);
        UXmethods.RoundBorders.setArcStyle(vieworder_background, 10);
        UXmethods.RoundBorders.setArcStyle(decline, 10);
        UXmethods.RoundBorders.setArcStyle(accept_order, 10);
        UXmethods.RoundBorders.setArcStyle(view_order, 10);
        UXmethods.RoundBorders.setArcStyle(filterContainer2, 10);
        UXmethods.RoundBorders.setArcStyle(orders_search_bar, 10);

        //product table
        UXmethods.RoundBorders.setArcStyle(product_search_bar, 10);
        UXmethods.RoundBorders.setArcStyle(filterContainer, 10);

        UXmethods.RoundBorders.setArcStyle(product_table_archive_button, 10);
        UXmethods.RoundBorders.setArcStyle(product_table_edit_button, 10);
        UXmethods.RoundBorders.setArcStyle(product_table_add_button, 10);
        UXmethods.RoundBorders.setArcStyle(product_table_delete_button, 10);

        UXmethods.RoundBorders.setArcStyle(removetbn, 10);
        UXmethods.RoundBorders.setArcStyle(replacebtn, 10);
        UXmethods.RoundBorders.setArcStyle(edit_product_save_button, 10);

        UXmethods.RoundBorders.setArcStyle(jPanel15, 20);
        UXmethods.RoundBorders.setArcStyle(jPanel13, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer1, 20);

        UXmethods.RoundBorders.setArcStyle(jPanel23, 10);

        UXmethods.RoundBorders.setArcStyle(getName, 10);
        UXmethods.RoundBorders.setArcStyle(getCategory, 10);
        UXmethods.RoundBorders.setArcStyle(getPrice, 10);
        UXmethods.RoundBorders.setArcStyle(getStock, 10);
        UXmethods.RoundBorders.setArcStyle(jScrollPane4, 10);
        UXmethods.RoundBorders.setArcStyle(getStatus, 10);

        UXmethods.RoundBorders.setArcStyle(add_category, 10);
        UXmethods.RoundBorders.setArcStyle(addPrice, 10);
        UXmethods.RoundBorders.setArcStyle(addCategory, 10);
        UXmethods.RoundBorders.setArcStyle(jScrollPane8, 10);
        UXmethods.RoundBorders.setArcStyle(addStock, 10);
        UXmethods.RoundBorders.setArcStyle(add_product_save_button, 10);
        UXmethods.RoundBorders.setArcStyle(addReplace, 10);
        UXmethods.RoundBorders.setArcStyle(addRemove, 10);
        UXmethods.RoundBorders.setArcStyle(jPanel24, 10);
        //
    }

    private void displayCurrentDate() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);

        todaysDate.setText(formattedDate);
    }

    private void searchBar(JTextField search) {
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        search.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new javax.swing.ImageIcon(getClass().getResource("/image/search_icon.png")));
        search.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                //+ "background:#FFFFFF;"
                + "margin:5,20,5,20");
    }

    private void displayRatingSumAndCount() {
        int sum_star;
        int seller_count;

        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT SUM(total_star) as total_star, COUNT(*) as total_rating FROM tbl_rating4seller WHERE seller_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, sellerID);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        sum_star = rs.getInt("total_star");
                        seller_count = rs.getInt("total_rating");
                        float rating = (float) sum_star / seller_count;
                        if (sum_star < 1) {
                            seller_rating.setText("0 (0)");
                        } else {
                            seller_rating.setText(String.format("%.1f (%d)", rating, seller_count));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void displayTotalOrders(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT COUNT(*) as TotalOrders FROM tbl_orders WHERE order_status = 'Accepted' AND seller_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, sellerID);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int totalorders = rs.getInt("TotalOrders");
                        totalOrders.setText(String.format("%d", totalorders));
                    } else {
                        totalOrders.setText("0");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void display_profile_picture() {
        try {
            databaseConnector dbc = new databaseConnector();
            ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id =" + sellerID);
            if (rs.next()) {
                int height = 50;
                int width = 50;
                String getImageFromDatabase = rs.getString("profile_picture");
                GetImage.displayImage(profile, getImageFromDatabase, height, width);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void displayTotalPendingOrders(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT COUNT(*) as TotalPending FROM tbl_orders WHERE order_status = 'Pending' AND seller_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, sellerID);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int pending_orders = rs.getInt("TotalPending");
                        pendingOrders.setText(String.format("%d", pending_orders));
                    } else {
                        pendingOrders.setText("0");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void displayTotalProducts(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT COUNT(*) as TotalProducts FROM tbl_products WHERE product_status = 'Available' AND seller_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, sellerID);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int total_products = rs.getInt("TotalProducts");
                        seller_total_products.setText(String.format("%d", total_products));
                    } else {
                        seller_total_products.setText("0");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void displayTotalSales(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT SUM(total_price) as TotalSales FROM tbl_orders WHERE order_status = 'Accepted' AND seller_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, sellerID);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int sales = rs.getInt("TotalSales");
                        totalSales.setText(String.format("₱ %d", sales));
                    } else {
                        totalSales.setText("₱ 0");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void displayOrdersTable() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT "
                    + "p.product_name AS `Product Name`, "
                    + "o.total_quantity AS `Total Quantity`, "
                    + "o.total_price AS `Total Price`, "
                    + "o.payment_method AS `Payment Method` "
                    + "FROM tbl_orders o "
                    + "JOIN tbl_products p ON o.product_id = p.product_id WHERE o.order_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, transaction_id);

                try (ResultSet rs = pst.executeQuery()) {
                    orders_table.setModel(DbUtils.resultSetToTableModel(rs));
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    orders_table.setDefaultRenderer(Object.class, centerRenderer);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    //orders table
    public void displayPurchase() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT "
                    + "`order_id` as `Order ID`, "
                    + "`buyer_id` as `Buyer ID`, "
                    + "`seller_id` as `Seller ID`, "
                    + "`product_id` as `Product ID`, "
                    + "`total_quantity` as `Quantity`, "
                    + "`total_price` as `Total Amount`, "
                    + "`payment_method` as `Payment Method`, "
                    + "`date_purchase` as `Date Purchase`, "
                    + "`order_status` as `Status` "
                    + "FROM tbl_orders WHERE seller_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, sellerID);
                try (ResultSet rs = pst.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        orders_is_empty.setText("ORDERS TABLE IS EMPTY!");
                        purchase_table.setModel(new DefaultTableModel());
                    } else {
                        orders_is_empty.setText("");
                        purchase_table.setModel(DbUtils.resultSetToTableModel(rs));
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        purchase_table.setDefaultRenderer(Object.class, centerRenderer);
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void messages() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT * FROM tbl_messages4seller WHERE seller_id = ?")) {
                pstmt.setInt(1, sellerID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    messages_table.setModel(DbUtils.resultSetToTableModel(rs));

                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    messages_table.setDefaultRenderer(Object.class, centerRenderer);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayProducts() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(""
                    + "SELECT `product_id` as `Product ID`,"
                    + "`product_name` as `Product Name`,"
                    + "`product_price` as `Price`,"
                    + "`product_stock` as `Stock(s)`,"
                    + "`product_category` as `Category`,"
                    + "`total_sold` as `Sold`,"
                    + "`date_created` as `Date Created`,"
                    + "`product_status` as `Status`"
                    + " FROM tbl_products "
                    + "WHERE product_status IN ('Available', 'Not Available', 'Sold out')"
                    + "AND seller_id = ?")) {
                pstmt.setInt(1, sellerID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        product_is_empty.setText("PRODUCT TABLE IS EMPTY!");
                        product_table.setModel(new DefaultTableModel());
                    } else {
                        product_is_empty.setText("");
                        product_table.setModel(DbUtils.resultSetToTableModel(rs));
                        product_table.getColumnModel().getColumn(7).setCellRenderer(new StatusCellRenderer());
                        //TableColumn column;
                        //column = product_table.getColumnModel().getColumn(0);
                        //column.setPreferredWidth(20);
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        product_table.setDefaultRenderer(Object.class, centerRenderer);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    class StatusCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = (String) value;

            switch (status) {
                case "Available":
                    cellComponent.setForeground(new Color(0, 102, 0));
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Not Available":
                    cellComponent.setForeground(new Color(204, 204, 204));
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Sold out":
                    cellComponent.setForeground(Color.RED);
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Pending":
                    cellComponent.setForeground(new Color(255, 153, 0));
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Archived":
                    cellComponent.setForeground(new Color(102, 102, 102));
                    break;
                default:
                    cellComponent.setForeground(table.getForeground());
            }
            ((JLabel) cellComponent).setHorizontalAlignment(SwingConstants.CENTER);

            return cellComponent;
        }
    }

    public void displayArchive() {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT"
                    + "`product_id` as `Product ID`,"
                    + "`product_name` as `Product Name`,"
                    + "`product_price` as `Price`,"
                    + "`product_stock` as `Stock(s)`,"
                    + "`product_category` as `Category`,"
                    + "`total_sold` as `Sold`,"
                    + "`date_created` as `Date Created`,"
                    + "`product_status` as `Status`"
                    + "FROM tbl_products WHERE product_status IN ('Archive') AND seller_id = ?");
            pstmt.setInt(1, sellerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    archive_is_empty.setText("ARCHIVE TABLE IS EMPTY!");
                    archive_table.setModel(new DefaultTableModel());
                } else {
                    archive_is_empty.setText("");
                    archive_table.setModel(DbUtils.resultSetToTableModel(rs));
                    archive_table.getColumnModel().getColumn(7).setCellRenderer(new StatusCellRenderer());
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    archive_table.setDefaultRenderer(Object.class, centerRenderer);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void displayMessage4Admin() {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT "
                    + "`message_id` AS `Message ID`, "
                    + "`message_category` AS `Category`, "
                    + "`message_title` AS `Title`, "
                    + "`message_description` AS `Description`, "
                    + "`date_sent` AS `Date Sent`, "
                    + "`message_status` AS `Status` "
                    + "FROM tbl_message4admin WHERE seller_id = ?"
            );
            pstmt.setInt(1, sellerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    message_is_empty.setText("MESSAGE IS EMPTY!");
                    message4admin_table.setModel(new DefaultTableModel());
                } else {
                    message_is_empty.setText("");
                    message4admin_table.setModel(DbUtils.resultSetToTableModel(rs));
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

                    TableColumn column;
                    column = message4admin_table.getColumnModel().getColumn(3);
                    column.setPreferredWidth(500);

                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    message4admin_table.setDefaultRenderer(Object.class, centerRenderer);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void displayProfileInfo() {
        try {
            databaseConnector dbc = new databaseConnector();
            ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id =" + sellerID);
            if (rs.next()) {
                String fname;
                String lname;
                fname = rs.getString("first_name");
                lname = rs.getString("last_name");
                fname = Character.toUpperCase(fname.charAt(0)) + fname.substring(1);
                lname = Character.toUpperCase(lname.charAt(0)) + lname.substring(1);
                seller_full_name.setText(fname + " " + lname);
                username.setText("@" + rs.getString("username"));
                seller_address.setText("" + rs.getString("address"));
                seller_phone.setText("" + rs.getString("phone_number"));
                seller_email.setText("" + rs.getString("email"));
                seller_store.setText(rs.getString("shop_name"));
                int height = 120;
                int width = 120;
                String getImageFromDatabase = rs.getString("profile_picture");
                GetImage.displayImage(display_photo, getImageFromDatabase, height, width);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void displayWishlistTable() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT a_buyer.first_name AS `Buyer Name`, "
                    + "p.product_name AS `Product Name`, "
                    + "w.total_favorites AS `Favorite(s)`, "
                    + "w.date_added AS `Date Added` "
                    + "FROM tbl_wishlist w "
                    + "JOIN tbl_accounts a_buyer ON a_buyer.account_id = w.buyer_id "
                    + "JOIN tbl_products p ON p.product_id = w.product_id "
                    + "WHERE w.seller_id = ?")) {
                pstmt.setInt(1, sellerID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        wishlist_is_empty.setText("WISHLIST TABLE IS EMPTY!");
                        wishlist_table.setModel(new DefaultTableModel());
                    } else {
                        wishlist_is_empty.setText("");
                        wishlist_table.setModel(DbUtils.resultSetToTableModel(rs));
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        wishlist_table.setDefaultRenderer(Object.class, centerRenderer);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void display_wishlist_dashboard() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT p.product_name AS `Product Name`, "
                    + "w.total_favorites AS `Favorite(s)`, "
                    + "p.product_image AS `image` "
                    + "FROM tbl_wishlist w "
                    + "JOIN tbl_products p ON p.product_id = w.product_id "
                    + "WHERE w.seller_id = ? "
                    + "ORDER BY w.total_favorites DESC "
                    + "LIMIT 2")) {
                pstmt.setInt(1, sellerID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        top1_name.setText(rs.getString("Product Name"));
                        String total_favorites = rs.getString("Favorite(s)");
                        top1_favorites.setText(total_favorites + " Favorite(s)");
                        int height = 80;
                        int width = 80;
                        String getImageFromDatabase = rs.getString("image");
                        GetImage.displayImage(top1_photo, getImageFromDatabase, height, width);
                    } else {
                        top1_name.setText("");
                        top1_favorites.setText("");
                    }
                    if (rs.next()) {
                        top2_name.setText(rs.getString("Product Name"));
                        String total_favorites = rs.getString("Favorite(s)");
                        top2_favorites.setText(total_favorites + " Favorite(s)");
                        int height = 80;
                        int width = 80;
                        String getImageFromDatabase = rs.getString("image");
                        GetImage.displayImage(top2_photo, getImageFromDatabase, height, width);
                    } else {
                        top2_name.setText("");
                        top2_favorites.setText("");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void display_best_selling_dashboard() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT product_name, "
                    + "product_price, "
                    + "total_sold, "
                    + "product_image "
                    + "FROM tbl_products "
                    + "WHERE seller_id = ? "
                    + "AND total_sold > 1 "
                    + "ORDER BY total_sold DESC "
                    + "LIMIT 2")) {
                pstmt.setInt(1, sellerID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        top1_product_name.setText(rs.getString("product_name"));
                        top1_product_price.setText("₱  " + rs.getString("product_price"));
                        String total_sold = rs.getString("total_sold");
                        top1_product_sold.setText(total_sold + " sold");
                        int height = 80;
                        int width = 80;
                        String getImageFromDatabase = rs.getString("product_image");
                        GetImage.displayImage(top1_product_photo, getImageFromDatabase, height, width);
                    } else {
                        top1_product_name.setText("");
                        top1_product_price.setText("");
                        top1_product_sold.setText("");
                        top1_product_photo.setIcon(null);
                    }
                    if (rs.next()) {
                        top2_product_name.setText(rs.getString("product_name"));
                        top2_product_price.setText("₱  " + rs.getString("product_price"));
                        String total_sold = rs.getString("total_sold");
                        top2_product_sold.setText(total_sold + " sold");
                        int height = 80;
                        int width = 80;
                        String getImageFromDatabase = rs.getString("product_image");
                        GetImage.displayImage(top2_product_photo, getImageFromDatabase, height, width);
                    } else {
                        top2_product_name.setText("");
                        top2_product_price.setText("");
                        top2_product_sold.setText("");
                        top2_product_photo.setIcon(null);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void display_orders_dashboard() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT p.product_name AS `product_name`, "
                    + "p.product_price AS `price`, "
                    + "o.total_quantity AS `quantity`, "
                    + "o.total_price AS `total_price`, "
                    + "p.product_image AS `product_image` "
                    + "FROM tbl_orders o "
                    + "JOIN tbl_products p ON p.product_id = o.product_id "
                    + "WHERE o.seller_id = ? AND o.order_status = 'Pending' "
                    + "ORDER BY o.order_status = 'Pending' DESC "
                    + "LIMIT 2")) {
                pstmt.setInt(1, sellerID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Reset total amount text
                    boolean foundPendingOrder = false;

                    // Display top 1 order
                    if (rs.next()) {
                        total_amount.setText("Total amount");
                        top1_order_name.setText(rs.getString("product_name"));
                        String price = rs.getString("price");
                        String quantity = rs.getString("quantity");
                        top1_order_price.setText("₱ " + price + " " + "x" + " " + quantity);
                        top1_order_total.setText(rs.getString("total_price"));
                        int height = 80;
                        int width = 80;
                        String getImageFromDatabase = rs.getString("product_image");
                        GetImage.displayImage(top1_order_photo, getImageFromDatabase, height, width);
                        foundPendingOrder = true;
                    } else {
                        total_amount.setText("");
                        top1_order_name.setText("");
                        top1_order_price.setText("");
                        top1_order_total.setText("");
                        top1_order_photo.setIcon(null);
                    }
                    // Display top 2 order
                    if (rs.next()) {
                        total_amount.setText("Total amount");
                        top2_order_name.setText(rs.getString("product_name"));
                        String price = rs.getString("price");
                        String quantity = rs.getString("quantity");
                        top2_order_price.setText("₱ " + price + " " + "x" + " " + quantity);
                        top2_order_total.setText(rs.getString("total_price"));
                        int height = 80;
                        int width = 80;
                        String getImageFromDatabase = rs.getString("product_image");
                        GetImage.displayImage(top2_order_photo, getImageFromDatabase, height, width);
                        foundPendingOrder = true;
                    } else {
                        total_amount.setText("");
                        top2_order_name.setText("");
                        top2_order_price.setText("");
                        top2_order_total.setText("");
                        top2_order_photo.setIcon(null);
                    }

                    // If no pending order found, reset total amount text
                    if (!foundPendingOrder) {
                        total_amount.setText("");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        dashboardContainer = new javax.swing.JPanel();
        logout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        profile = new javax.swing.JLabel();
        dashboard = new javax.swing.JToggleButton();
        manage = new javax.swing.JToggleButton();
        orders = new javax.swing.JToggleButton();
        archiveBtn = new javax.swing.JToggleButton();
        admin_support = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        helloSeller = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        totalSales = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        totalLoss = new javax.swing.JLabel();
        CONTAINER = new javax.swing.JPanel();
        CONTAINER1 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        top1_favorites = new javax.swing.JLabel();
        top1_name = new javax.swing.JLabel();
        top1_photo = new javax.swing.JLabel();
        top2_favorites = new javax.swing.JLabel();
        top2_name = new javax.swing.JLabel();
        top2_photo = new javax.swing.JLabel();
        CONTAINER2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        top2_order_total = new javax.swing.JLabel();
        top1_order_name = new javax.swing.JLabel();
        top2_order_name = new javax.swing.JLabel();
        top1_order_price = new javax.swing.JLabel();
        top2_order_price = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        total_amount = new javax.swing.JLabel();
        top1_order_photo = new javax.swing.JLabel();
        top2_order_photo = new javax.swing.JLabel();
        top1_order_total = new javax.swing.JLabel();
        CONTAINER3 = new javax.swing.JPanel();
        CONTAINER5 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        top1_product_photo = new javax.swing.JLabel();
        top1_product_name = new javax.swing.JLabel();
        top2_product_price = new javax.swing.JLabel();
        top2_product_photo = new javax.swing.JLabel();
        top2_product_name = new javax.swing.JLabel();
        top1_product_price = new javax.swing.JLabel();
        top2_product_sold = new javax.swing.JLabel();
        top1_product_sold = new javax.swing.JLabel();
        pendingOrders = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalOrders = new javax.swing.JLabel();
        seller_total_products = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        todaysDate = new javax.swing.JLabel();
        overviewTotalSales = new javax.swing.JLabel();
        overviewTotalLoss1 = new javax.swing.JLabel();
        overviewTotalLoss2 = new javax.swing.JLabel();
        overviewTotalLoss3 = new javax.swing.JLabel();
        overviewTotalLoss4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        productsContainer = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        product_is_empty = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        product_search_bar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        filterContainer = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        filter_product_table = new javax.swing.JComboBox<>();
        product_table_add_button = new javax.swing.JButton();
        product_table_edit_button = new javax.swing.JButton();
        product_table_archive_button = new javax.swing.JButton();
        product_table_delete_button = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        archiveAccountTableContainer = new javax.swing.JPanel();
        archive_is_empty = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        archiveAccountTableContainerScroll = new javax.swing.JScrollPane();
        archive_table = new javax.swing.JTable();
        archive_search_bar = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        c6 = new javax.swing.JPanel();
        manage18 = new javax.swing.JLabel();
        productID = new javax.swing.JLabel();
        c7 = new javax.swing.JPanel();
        manage24 = new javax.swing.JLabel();
        productQuantity = new javax.swing.JLabel();
        c8 = new javax.swing.JPanel();
        manage25 = new javax.swing.JLabel();
        productStatus = new javax.swing.JLabel();
        c9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descript = new javax.swing.JEditorPane();
        productPhoto = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        productPrice = new javax.swing.JLabel();
        restore = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        s = new javax.swing.JPanel();
        productsContainer2 = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        orders_is_empty = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        purchase_table = new javax.swing.JTable();
        orders_search_bar = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        filterContainer2 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        view_order = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        l1 = new javax.swing.JPanel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        a4 = new javax.swing.JToggleButton();
        a1 = new javax.swing.JToggleButton();
        a2 = new javax.swing.JToggleButton();
        jLabel97 = new javax.swing.JLabel();
        a3 = new javax.swing.JToggleButton();
        help_status = new javax.swing.JButton();
        jSeparator18 = new javax.swing.JSeparator();
        jPanel19 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        l3 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        title = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        explain = new javax.swing.JTextArea();
        header = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        admin_submit = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        display_photo = new javax.swing.JLabel();
        seller_full_name = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        seller_rating = new javax.swing.JLabel();
        seperator = new javax.swing.JPanel();
        edit_profile = new javax.swing.JButton();
        addContainer4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        seller_address = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        username = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        seller_phone = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        seller_email = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        seller_store = new javax.swing.JLabel();
        bacgkround = new javax.swing.JLabel();
        productsContainer1 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        actionlogs_table = new javax.swing.JTable();
        activity_search_bar = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        filterContainer1 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        add2 = new javax.swing.JButton();
        messages_container = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        messages_table = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        add1 = new javax.swing.JButton();
        add3 = new javax.swing.JButton();
        add4 = new javax.swing.JButton();
        add5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        addContainer1 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel24 = new javax.swing.JPanel();
        addPhoto = new javax.swing.JLabel();
        addReplace = new javax.swing.JButton();
        addRemove = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        errorImage = new javax.swing.JLabel();
        addContainer = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        add_category = new javax.swing.JComboBox<>();
        addCategory = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        add_product_save_button = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        addName = new javax.swing.JTextField();
        addPrice = new javax.swing.JTextField();
        addStock = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        addDescription = new javax.swing.JEditorPane();
        nameError = new javax.swing.JLabel();
        stockError = new javax.swing.JLabel();
        priceError = new javax.swing.JLabel();
        desError = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        getStatus = new javax.swing.JComboBox<>();
        getCategory = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        edit_product_save_button = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        getName = new javax.swing.JTextField();
        getPrice = new javax.swing.JTextField();
        getStock = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        getDescription = new javax.swing.JEditorPane();
        editNameError = new javax.swing.JLabel();
        editPriceError = new javax.swing.JLabel();
        editStockError = new javax.swing.JLabel();
        desError2 = new javax.swing.JLabel();
        desError3 = new javax.swing.JLabel();
        desError5 = new javax.swing.JLabel();
        desError6 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel23 = new javax.swing.JPanel();
        getPhoto = new javax.swing.JLabel();
        replacebtn = new javax.swing.JButton();
        removetbn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        desError7 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        shop_photo = new javax.swing.JLabel();
        seperator1 = new javax.swing.JPanel();
        addContainer5 = new javax.swing.JPanel();
        seller_address1 = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        seller_phone1 = new javax.swing.JLabel();
        seller_email1 = new javax.swing.JLabel();
        seller_store1 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        shop_location = new javax.swing.JTextField();
        shop_name = new javax.swing.JTextField();
        deactivate_jpanel = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        deactivate = new javax.swing.JButton();
        edit_seller_close_button = new javax.swing.JButton();
        edit_seller_save_button = new javax.swing.JButton();
        shop_username = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        change_password = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        c4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        c11 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        c5 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        shop_password = new javax.swing.JPasswordField();
        z6 = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        bacgkround1 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        addContainer6 = new javax.swing.JPanel();
        seller_address2 = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        seller_phone2 = new javax.swing.JLabel();
        seller_email2 = new javax.swing.JLabel();
        seller_store2 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        edit_seller_upload_button = new javax.swing.JButton();
        addContainer7 = new javax.swing.JPanel();
        seller_address3 = new javax.swing.JLabel();
        username3 = new javax.swing.JLabel();
        seller_phone3 = new javax.swing.JLabel();
        seller_email3 = new javax.swing.JLabel();
        seller_store3 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        shop_email = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        shop_number = new javax.swing.JTextField();
        shop_fname = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        c1 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        c3 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        c2 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        shop_lname = new javax.swing.JTextField();
        c10 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        vieworder_container3 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        vieworder_shipping_name = new javax.swing.JLabel();
        vieworder_shipping_number = new javax.swing.JLabel();
        vieworder_shipping_location = new javax.swing.JLabel();
        vieworder_container2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        vieworder_contact_email = new javax.swing.JLabel();
        vieworder_contact_name = new javax.swing.JLabel();
        vieworder_contact_number = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        vieworder_container4 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        vieworder_notes = new javax.swing.JTextArea();
        vieworder_container = new javax.swing.JPanel();
        vieworder_background = new javax.swing.JPanel();
        vieworder_photo = new javax.swing.JLabel();
        vieworder_date = new javax.swing.JLabel();
        vieworder_total = new javax.swing.JLabel();
        vieworder_orderID = new javax.swing.JLabel();
        vieworder_category = new javax.swing.JLabel();
        vieworder_product_name = new javax.swing.JLabel();
        vieworder_status = new javax.swing.JLabel();
        status_background = new javax.swing.JButton();
        productsContainer3 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        orders_table = new javax.swing.JTable();
        jLabel89 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        decline = new javax.swing.JButton();
        accept_order = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        messageContainer = new javax.swing.JPanel();
        message_is_empty = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jScrollPane12 = new javax.swing.JScrollPane();
        message4admin_table = new javax.swing.JTable();
        message_search_bar = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        filterContainer3 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        filter_product_table1 = new javax.swing.JComboBox<>();
        jLabel108 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        wishlist_container = new javax.swing.JPanel();
        jSeparator19 = new javax.swing.JSeparator();
        wishlist_is_empty = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        wishlist_table = new javax.swing.JTable();
        wishlist_search_bar = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        filterContainer4 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        filter_product_table2 = new javax.swing.JComboBox<>();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel5.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardContainer.setBackground(new java.awt.Color(241, 241, 241));
        dashboardContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setBackground(new java.awt.Color(255, 102, 102));
        logout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout_icons.png"))); // NOI18N
        logout.setBorderPainted(false);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        dashboardContainer.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 50, 50));
        dashboardContainer.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 50, 20));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/2.png"))); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        dashboardContainer.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 50));

        dashboard.setBackground(new java.awt.Color(204, 204, 204));
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/dashboard_icon.png"))); // NOI18N
        dashboard.setBorderPainted(false);
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        dashboardContainer.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 50, 50));

        manage.setBackground(new java.awt.Color(204, 204, 204));
        manage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/manage_icon.png"))); // NOI18N
        manage.setBorderPainted(false);
        manage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageMouseClicked(evt);
            }
        });
        manage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageActionPerformed(evt);
            }
        });
        dashboardContainer.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 50, 50));

        orders.setBackground(new java.awt.Color(204, 204, 204));
        orders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/orders_icon.png"))); // NOI18N
        orders.setBorderPainted(false);
        orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersMouseClicked(evt);
            }
        });
        orders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersActionPerformed(evt);
            }
        });
        dashboardContainer.add(orders, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 50, 50));

        archiveBtn.setBackground(new java.awt.Color(204, 204, 204));
        archiveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/archive_icon.png"))); // NOI18N
        archiveBtn.setBorderPainted(false);
        archiveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archiveBtnMouseClicked(evt);
            }
        });
        archiveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(archiveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 50, 50));

        admin_support.setBackground(new java.awt.Color(204, 204, 204));
        admin_support.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/admin_icon.png"))); // NOI18N
        admin_support.setBorderPainted(false);
        admin_support.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_supportMouseClicked(evt);
            }
        });
        admin_support.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_supportActionPerformed(evt);
            }
        });
        dashboardContainer.add(admin_support, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 50, 50));

        jPanel5.add(dashboardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 600, 20));

        tabs.setBackground(new java.awt.Color(153, 153, 153));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Great job today! You've done exceptionally well.");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 186, 580, -1));

        helloSeller.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 40)); // NOI18N
        helloSeller.setText("Hi, Seller!");
        jPanel6.add(helloSeller, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/try.png"))); // NOI18N
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 1120, 260));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Overview");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 158, 226));
        jLabel20.setText("SHOPTIFY");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 30));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Welcome to shoptify dashboard");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, -1));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 70));

        totalSales.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalSales.setForeground(new java.awt.Color(255, 255, 255));
        totalSales.setText("₱  0");
        jPanel6.add(totalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Sales");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, 20));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Loss");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, 20));

        totalLoss.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalLoss.setForeground(new java.awt.Color(255, 255, 255));
        totalLoss.setText("₱  0");
        jPanel6.add(totalLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, -1));

        CONTAINER.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CONTAINER1.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        CONTAINER.add(CONTAINER1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 420, 250));

        jLabel114.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel114.setText("Customer Favorites");
        CONTAINER.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 200, 50));

        jButton4.setBackground(new java.awt.Color(0, 158, 226));
        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("View all");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        CONTAINER.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 130, 30));

        top1_favorites.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top1_favorites.setForeground(new java.awt.Color(0, 158, 226));
        top1_favorites.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER.add(top1_favorites, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 90, 20));

        top1_name.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CONTAINER.add(top1_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 160, -1));
        CONTAINER.add(top1_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 80));

        top2_favorites.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top2_favorites.setForeground(new java.awt.Color(0, 158, 226));
        top2_favorites.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER.add(top2_favorites, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 90, 20));

        top2_name.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CONTAINER.add(top2_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 160, -1));
        CONTAINER.add(top2_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, 80));

        jPanel6.add(CONTAINER, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 420, 370, 240));

        CONTAINER2.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel16.setText("Latest orders");
        CONTAINER2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jButton1.setBackground(new java.awt.Color(0, 158, 226));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("View all");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        CONTAINER2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 130, 30));

        top2_order_total.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top2_order_total.setForeground(new java.awt.Color(0, 158, 226));
        top2_order_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER2.add(top2_order_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 90, 20));

        top1_order_name.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CONTAINER2.add(top1_order_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 160, -1));

        top2_order_name.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CONTAINER2.add(top2_order_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 160, 20));

        top1_order_price.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top1_order_price.setForeground(new java.awt.Color(153, 153, 153));
        CONTAINER2.add(top1_order_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 120, 20));

        top2_order_price.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top2_order_price.setForeground(new java.awt.Color(153, 153, 153));
        CONTAINER2.add(top2_order_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 120, 20));

        jLabel120.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(153, 153, 153));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER2.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 90, 20));

        total_amount.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        total_amount.setForeground(new java.awt.Color(153, 153, 153));
        total_amount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER2.add(total_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 90, 20));
        CONTAINER2.add(top1_order_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 80));
        CONTAINER2.add(top2_order_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, 80));

        top1_order_total.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top1_order_total.setForeground(new java.awt.Color(0, 158, 226));
        top1_order_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER2.add(top1_order_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 90, 20));

        jPanel6.add(CONTAINER2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 360, 240));

        CONTAINER3.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CONTAINER5.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        CONTAINER3.add(CONTAINER5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 420, 250));

        jLabel57.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel57.setText("Best selling");
        CONTAINER3.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jButton2.setBackground(new java.awt.Color(0, 158, 226));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("View all");
        jButton2.setBorderPainted(false);
        CONTAINER3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 130, 30));
        CONTAINER3.add(top1_product_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 80));

        top1_product_name.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CONTAINER3.add(top1_product_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 160, -1));

        top2_product_price.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top2_product_price.setForeground(new java.awt.Color(153, 153, 153));
        CONTAINER3.add(top2_product_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 120, 20));
        CONTAINER3.add(top2_product_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, 80));

        top2_product_name.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CONTAINER3.add(top2_product_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 160, -1));

        top1_product_price.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top1_product_price.setForeground(new java.awt.Color(153, 153, 153));
        CONTAINER3.add(top1_product_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 120, 20));

        top2_product_sold.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top2_product_sold.setForeground(new java.awt.Color(0, 158, 226));
        top2_product_sold.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER3.add(top2_product_sold, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 90, 20));

        top1_product_sold.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        top1_product_sold.setForeground(new java.awt.Color(0, 158, 226));
        top1_product_sold.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CONTAINER3.add(top1_product_sold, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 90, 20));

        jPanel6.add(CONTAINER3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 360, 240));

        pendingOrders.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        pendingOrders.setForeground(new java.awt.Color(255, 255, 255));
        pendingOrders.setText("0");
        jPanel6.add(pendingOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pending Orders");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 370, -1, 20));

        totalOrders.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalOrders.setForeground(new java.awt.Color(255, 255, 255));
        totalOrders.setText("0");
        jPanel6.add(totalOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 330, -1, -1));

        seller_total_products.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        seller_total_products.setForeground(new java.awt.Color(255, 255, 255));
        seller_total_products.setText("0");
        jPanel6.add(seller_total_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Products");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, -1, 20));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Total Orders");
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 370, -1, 20));

        todaysDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        todaysDate.setForeground(new java.awt.Color(102, 102, 102));
        todaysDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        todaysDate.setText("Date");
        todaysDate.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(todaysDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 70, -1));

        overviewTotalSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalsales_1.png"))); // NOI18N
        jPanel6.add(overviewTotalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 280, 80));

        overviewTotalLoss1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalLoss.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 280, 80));

        overviewTotalLoss2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pendingOrders.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 320, 170, 80));

        overviewTotalLoss3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalProducts.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 170, 80));

        overviewTotalLoss4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalOrders.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, 170, 80));

        tabs.addTab("tab1", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productsContainer.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        productsContainer.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1150, 10));

        product_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        product_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsContainer.add(product_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 290, 1150, 60));

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        product_table.setAutoCreateRowSorter(true);
        product_table.setBackground(new java.awt.Color(241, 241, 241));
        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        product_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        product_table.setShowHorizontalLines(true);
        product_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(product_table);

        productsContainer.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1140, 550));

        product_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        product_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        product_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_search_barMouseClicked(evt);
            }
        });
        product_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                product_search_barKeyReleased(evt);
            }
        });
        productsContainer.add(product_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 40));

        jLabel17.setBackground(new java.awt.Color(241, 241, 241));
        jLabel17.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Product Table");
        productsContainer.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setBackground(new java.awt.Color(241, 241, 241));
        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Filter by:");
        filterContainer.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Categories", "Electronics", "Grocery", "Pet Supplies", "Fashion" }));
        filter_product_table.setSelectedIndex(0);
        filter_product_table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filter_product_table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_product_tableActionPerformed(evt);
            }
        });
        filterContainer.add(filter_product_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer.add(filterContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 180, 40));

        product_table_add_button.setBackground(new java.awt.Color(0, 158, 226));
        product_table_add_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_add_button.setForeground(new java.awt.Color(255, 255, 255));
        product_table_add_button.setText("Add product  +");
        product_table_add_button.setBorderPainted(false);
        product_table_add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_add_buttonActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_add_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 50, 130, 40));

        product_table_edit_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_edit_button.setForeground(new java.awt.Color(51, 51, 51));
        product_table_edit_button.setText("Edit");
        product_table_edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_edit_buttonActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_edit_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 130, 40));

        product_table_archive_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_archive_button.setForeground(new java.awt.Color(51, 51, 51));
        product_table_archive_button.setText("Add to archive");
        product_table_archive_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_archive_buttonActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_archive_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 130, 40));

        product_table_delete_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_delete_button.setText("Delete");
        product_table_delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_delete_buttonActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_delete_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 130, 40));

        jLabel31.setBackground(new java.awt.Color(241, 241, 241));
        jLabel31.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 204, 204));
        jLabel31.setText("Manage Product  >");
        productsContainer.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel26.setBackground(new java.awt.Color(241, 241, 241));
        jLabel26.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Product Table");
        productsContainer.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, 30));

        jPanel8.add(productsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1140, 670));

        tabs.addTab("tab2", jPanel8);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archiveAccountTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        archiveAccountTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archive_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        archive_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        archive_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        archiveAccountTableContainer.add(archive_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 820, 60));
        archiveAccountTableContainer.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 840, 20));

        archiveAccountTableContainerScroll.setBackground(new java.awt.Color(0, 0, 0));
        archiveAccountTableContainerScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        archive_table.setBackground(new java.awt.Color(241, 241, 241));
        archive_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        archive_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        archive_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archive_tableMouseClicked(evt);
            }
        });
        archiveAccountTableContainerScroll.setViewportView(archive_table);

        archiveAccountTableContainer.add(archiveAccountTableContainerScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 820, 550));

        archive_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        archive_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        archive_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archive_search_barMouseClicked(evt);
            }
        });
        archive_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                archive_search_barKeyReleased(evt);
            }
        });
        archiveAccountTableContainer.add(archive_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 40));

        jLabel41.setBackground(new java.awt.Color(241, 241, 241));
        jLabel41.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("Manage Product  >  Product Table  >");
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        archiveAccountTableContainer.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel42.setBackground(new java.awt.Color(241, 241, 241));
        jLabel42.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Archive Products Table");
        archiveAccountTableContainer.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 30));

        jPanel2.add(archiveAccountTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 820, 670));

        jPanel14.setBackground(new java.awt.Color(241, 241, 241));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        c6.setBackground(new java.awt.Color(255, 255, 255));
        c6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage18.setForeground(new java.awt.Color(102, 102, 102));
        manage18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-tag-woman-horizontal-24.png"))); // NOI18N
        c6.add(manage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productID.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        productID.setForeground(new java.awt.Color(102, 102, 102));
        c6.add(productID, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 30));

        jPanel14.add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        c7.setBackground(new java.awt.Color(255, 255, 255));
        c7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage24.setForeground(new java.awt.Color(102, 102, 102));
        manage24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-quantity-24.png"))); // NOI18N
        c7.add(manage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productQuantity.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        productQuantity.setForeground(new java.awt.Color(102, 102, 102));
        c7.add(productQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 30));

        jPanel14.add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        c8.setBackground(new java.awt.Color(255, 255, 255));
        c8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage25.setForeground(new java.awt.Color(102, 102, 102));
        manage25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-status-24.png"))); // NOI18N
        c8.add(manage25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productStatus.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        productStatus.setForeground(new java.awt.Color(102, 102, 102));
        c8.add(productStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 30));

        jPanel14.add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        c9.setBackground(new java.awt.Color(255, 255, 255));
        c9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        descript.setBorder(null);
        descript.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(descript);

        c9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 290));

        jPanel14.add(c9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 270, 310));

        productPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 70x70.png"))); // NOI18N
        jPanel14.add(productPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        productName.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        productName.setForeground(new java.awt.Color(51, 51, 51));
        productName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productName.setText("PRODUCT NAME");
        jPanel14.add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 190, 40));
        jPanel14.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 300, 20));

        productPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        productPrice.setForeground(new java.awt.Color(102, 102, 102));
        productPrice.setText("Status");
        jPanel14.add(productPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, 20));

        restore.setBackground(new java.awt.Color(122, 183, 147));
        restore.setForeground(new java.awt.Color(255, 255, 255));
        restore.setText("Restore");
        restore.setBorder(null);
        restore.setBorderPainted(false);
        restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreActionPerformed(evt);
            }
        });
        jPanel14.add(restore, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 130, 40));

        delete.setBackground(new java.awt.Color(255, 102, 102));
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.setBorder(null);
        delete.setBorderPainted(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel14.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 620, 130, 40));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Description");
        jPanel14.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 310, 670));

        tabs.addTab("tab3", jPanel2);

        s.setBackground(new java.awt.Color(255, 255, 255));
        s.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productsContainer2.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        productsContainer2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1140, 30));

        orders_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        orders_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        orders_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsContainer2.add(orders_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 290, 1150, 60));

        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        purchase_table.setAutoCreateRowSorter(true);
        purchase_table.setBackground(new java.awt.Color(241, 241, 241));
        purchase_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        purchase_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        purchase_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchase_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(purchase_table);

        productsContainer2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 540));

        orders_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        orders_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        orders_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orders_search_barMouseClicked(evt);
            }
        });
        orders_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                orders_search_barKeyReleased(evt);
            }
        });
        productsContainer2.add(orders_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel79.setBackground(new java.awt.Color(241, 241, 241));
        jLabel79.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jLabel79.setText("Product Table");
        productsContainer2.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer2.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel80.setBackground(new java.awt.Color(241, 241, 241));
        jLabel80.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
        jLabel80.setText("Filter by:");
        filterContainer2.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jComboBox5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Status", "Pending", "Accepted", "Declined", "Cancelled" }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        filterContainer2.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer2.add(filterContainer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        view_order.setBackground(new java.awt.Color(0, 158, 226));
        view_order.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        view_order.setForeground(new java.awt.Color(255, 255, 255));
        view_order.setText("View order");
        view_order.setBorderPainted(false);
        view_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_orderActionPerformed(evt);
            }
        });
        productsContainer2.add(view_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 160, 40));

        s.add(productsContainer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1150, 620));

        jLabel81.setBackground(new java.awt.Color(241, 241, 241));
        jLabel81.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(204, 204, 204));
        jLabel81.setText("Manage Orders  >");
        s.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel82.setBackground(new java.awt.Color(241, 241, 241));
        jLabel82.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
        jLabel82.setText("Orders Table");
        s.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 30));

        tabs.addTab("tab4", s);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel96.setBackground(new java.awt.Color(241, 241, 241));
        jLabel96.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(204, 204, 204));
        jLabel96.setText("Contact Admin  >");
        jPanel4.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel98.setBackground(new java.awt.Color(241, 241, 241));
        jLabel98.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(51, 51, 51));
        jLabel98.setText("Help Center");
        jPanel4.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 30));

        l1.setBackground(new java.awt.Color(241, 241, 241));
        l1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        l1.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));
        l1.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 320, 20));

        a4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        a4.setText("Report an issue");
        a4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a4ActionPerformed(evt);
            }
        });
        l1.add(a4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 280, 50));

        a1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        a1.setText("Deactivate account");
        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });
        l1.add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 280, 50));

        a2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        a2.setText("Delete product");
        a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a2ActionPerformed(evt);
            }
        });
        l1.add(a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 280, 50));

        jLabel97.setBackground(new java.awt.Color(241, 241, 241));
        jLabel97.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(51, 51, 51));
        jLabel97.setText("Category");
        l1.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        a3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        a3.setText("Request a feature");
        a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a3ActionPerformed(evt);
            }
        });
        l1.add(a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 280, 50));

        help_status.setBackground(new java.awt.Color(0, 158, 226));
        help_status.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        help_status.setForeground(new java.awt.Color(255, 255, 255));
        help_status.setText("View  help status");
        help_status.setBorderPainted(false);
        help_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                help_statusActionPerformed(evt);
            }
        });
        l1.add(help_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 290, 50));
        l1.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 280, 20));

        jPanel4.add(l1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 430));

        jPanel19.setBackground(new java.awt.Color(241, 241, 241));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel107.setBackground(new java.awt.Color(241, 241, 241));
        jLabel107.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(51, 51, 51));
        jLabel107.setText("Description");
        jPanel19.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 210, 20));

        message.setColumns(20);
        message.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        message.setRows(5);
        l3.setViewportView(message);

        jPanel19.add(l3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 710, 290));
        jPanel19.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 700, 40));

        jLabel109.setBackground(new java.awt.Color(241, 241, 241));
        jLabel109.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(51, 51, 51));
        jLabel109.setText("Title");
        jPanel19.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 600, 20));

        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        explain.setBackground(new java.awt.Color(241, 241, 241));
        explain.setColumns(20);
        explain.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        explain.setForeground(new java.awt.Color(153, 153, 153));
        explain.setRows(5);
        explain.setText("\n");
        jScrollPane6.setViewportView(explain);

        jPanel19.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 700, 50));

        header.setBackground(new java.awt.Color(241, 241, 241));
        header.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        header.setForeground(new java.awt.Color(51, 51, 51));
        header.setText("Category selected");
        jPanel19.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 700, 40));
        jPanel19.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 770, 20));

        admin_submit.setBackground(new java.awt.Color(0, 158, 226));
        admin_submit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        admin_submit.setForeground(new java.awt.Color(255, 255, 255));
        admin_submit.setText("Submit");
        admin_submit.setBorderPainted(false);
        admin_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_submitActionPerformed(evt);
            }
        });
        jPanel19.add(admin_submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 160, 40));

        jPanel4.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 780, 610));

        tabs.addTab("tab5", jPanel4);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel54.setBackground(new java.awt.Color(241, 241, 241));
        jLabel54.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(204, 204, 204));
        jLabel54.setText("My Profile  >");
        jPanel11.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel55.setBackground(new java.awt.Color(241, 241, 241));
        jLabel55.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("Your activities");
        jPanel11.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, 30));

        jLabel56.setBackground(new java.awt.Color(241, 241, 241));
        jLabel56.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setText("View Profile");
        jPanel11.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, 30));

        display_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        display_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default_seller_profile.png"))); // NOI18N
        jPanel11.add(display_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 150));

        seller_full_name.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        seller_full_name.setForeground(new java.awt.Color(51, 51, 51));
        seller_full_name.setText("Archie Albarico");
        jPanel11.add(seller_full_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 162, 140, 50));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/star_rating.png"))); // NOI18N
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 70, 40));

        seller_rating.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_rating.setForeground(new java.awt.Color(153, 153, 153));
        seller_rating.setText("5.0 (12)");
        jPanel11.add(seller_rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 190, 70, 40));

        seperator.setBackground(new java.awt.Color(241, 241, 241));
        seperator.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit_profile.setBackground(new java.awt.Color(0, 158, 226));
        edit_profile.setForeground(new java.awt.Color(255, 255, 255));
        edit_profile.setText("Edit profile");
        edit_profile.setBorderPainted(false);
        edit_profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_profileActionPerformed(evt);
            }
        });
        seperator.add(edit_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 110, 40));

        jPanel11.add(seperator, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 490, 60));

        addContainer4.setBackground(new java.awt.Color(241, 241, 241));
        addContainer4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/location.png"))); // NOI18N
        addContainer4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        seller_address.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        addContainer4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 490, 20));

        username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/phone.png"))); // NOI18N
        addContainer4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 50, 30));

        seller_phone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/email.png"))); // NOI18N
        addContainer4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 50, 30));

        seller_email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/store.png"))); // NOI18N
        addContainer4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 50, 30));

        seller_store.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_store, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jPanel11.add(addContainer4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 490, 180));

        bacgkround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/background.png"))); // NOI18N
        jPanel11.add(bacgkround, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        productsContainer1.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        productsContainer1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 20));

        jScrollPane9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        actionlogs_table.setAutoCreateRowSorter(true);
        actionlogs_table.setBackground(new java.awt.Color(241, 241, 241));
        actionlogs_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        actionlogs_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        actionlogs_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                actionlogs_tableKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(actionlogs_table);

        productsContainer1.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 160));

        activity_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        activity_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        activity_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activity_search_barMouseClicked(evt);
            }
        });
        activity_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                activity_search_barKeyReleased(evt);
            }
        });
        productsContainer1.add(activity_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel33.setBackground(new java.awt.Color(241, 241, 241));
        jLabel33.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Product Table");
        productsContainer1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer1.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setBackground(new java.awt.Color(241, 241, 241));
        jLabel34.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Filter by:");
        filterContainer1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Add", "Delete", "Edit", "Accept", "Decline", "Login", "Log out", " " }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        filterContainer1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer1.add(filterContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        add2.setBackground(new java.awt.Color(0, 158, 226));
        add2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add2.setForeground(new java.awt.Color(255, 255, 255));
        add2.setText("Print");
        add2.setBorderPainted(false);
        add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2ActionPerformed(evt);
            }
        });
        productsContainer1.add(add2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 130, 40));

        jPanel11.add(productsContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 1150, 230));

        messages_container.setBackground(new java.awt.Color(241, 241, 241));
        messages_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        messages_table.setAutoCreateRowSorter(true);
        messages_table.setBackground(new java.awt.Color(241, 241, 241));
        messages_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        messages_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messages_tableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(messages_table);

        messages_container.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 640, 260));

        jLabel40.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Messages");
        messages_container.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, -1));

        add1.setBackground(new java.awt.Color(0, 158, 226));
        add1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add1.setForeground(new java.awt.Color(255, 255, 255));
        add1.setText("Mark as read");
        add1.setBorderPainted(false);
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1ActionPerformed(evt);
            }
        });
        messages_container.add(add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 120, 40));

        add3.setBackground(new java.awt.Color(0, 158, 226));
        add3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add3.setForeground(new java.awt.Color(255, 255, 255));
        add3.setText("Reply");
        add3.setBorderPainted(false);
        add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add3ActionPerformed(evt);
            }
        });
        messages_container.add(add3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 120, 40));

        add4.setBackground(new java.awt.Color(0, 158, 226));
        add4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add4.setForeground(new java.awt.Color(255, 255, 255));
        add4.setText("Delete");
        add4.setBorderPainted(false);
        add4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add4ActionPerformed(evt);
            }
        });
        messages_container.add(add4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 90, 40));

        add5.setBackground(new java.awt.Color(0, 158, 226));
        add5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add5.setForeground(new java.awt.Color(255, 255, 255));
        add5.setText("Archive");
        add5.setBorderPainted(false);
        add5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add5ActionPerformed(evt);
            }
        });
        messages_container.add(add5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 100, 40));

        jPanel11.add(messages_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 640, 340));

        tabs.addTab("tab6", jPanel11);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setBackground(new java.awt.Color(241, 241, 241));
        jLabel46.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Add Product");
        jPanel9.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, 30));

        addContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        addContainer1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, 30));

        jPanel24.setBackground(new java.awt.Color(204, 204, 204));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel24.add(addPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 270, 175));

        addContainer1.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 390, 195));

        addReplace.setBackground(new java.awt.Color(0, 158, 226));
        addReplace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addReplace.setForeground(new java.awt.Color(255, 255, 255));
        addReplace.setText("Select image");
        addReplace.setBorderPainted(false);
        addReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReplaceActionPerformed(evt);
            }
        });
        addContainer1.add(addReplace, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 190, 40));

        addRemove.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addRemove.setForeground(new java.awt.Color(51, 51, 51));
        addRemove.setText("Remove");
        addRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRemoveActionPerformed(evt);
            }
        });
        addContainer1.add(addRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 190, 40));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Product Image");
        addContainer1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel47.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Add Product");
        addContainer1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel48.setBackground(new java.awt.Color(241, 241, 241));
        jLabel48.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(153, 153, 153));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Set the product thumbnail image. Only *.png, *.jpeg, *.jpg files are accepted.");
        addContainer1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 390, 30));

        errorImage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        errorImage.setForeground(new java.awt.Color(255, 51, 51));
        errorImage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addContainer1.add(errorImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 260, -1));

        jPanel9.add(addContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 410, 450));

        addContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Stock(s)");
        addContainer.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        add_category.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add_category.setForeground(new java.awt.Color(51, 51, 51));
        add_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        addContainer.add(add_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 320, 50));

        addCategory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addCategory.setForeground(new java.awt.Color(51, 51, 51));
        addCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Fashion", "Grocery", "Pet Supplies" }));
        addContainer.add(addCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 320, 50));

        jLabel38.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Category");
        addContainer.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        add_product_save_button.setBackground(new java.awt.Color(0, 158, 226));
        add_product_save_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add_product_save_button.setForeground(new java.awt.Color(255, 255, 255));
        add_product_save_button.setText("Save");
        add_product_save_button.setBorderPainted(false);
        add_product_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_product_save_buttonActionPerformed(evt);
            }
        });
        addContainer.add(add_product_save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 270, 40));

        jLabel49.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Price");
        addContainer.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel50.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Status");
        addContainer.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel51.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Description");
        addContainer.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel52.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Product Name");
        addContainer.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel53.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("General infromation");
        addContainer.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        addContainer.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 720, 30));

        addName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName.setForeground(new java.awt.Color(51, 51, 51));
        addName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addNameFocusGained(evt);
            }
        });
        addContainer.add(addName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 660, 50));
        addName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "e.g., PlayStation 5");

        addPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addPrice.setForeground(new java.awt.Color(51, 51, 51));
        addPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addPriceFocusGained(evt);
            }
        });
        addContainer.add(addPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, 50));
        addPrice.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "₱");

        addStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addStock.setForeground(new java.awt.Color(51, 51, 51));
        addStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addStockFocusGained(evt);
            }
        });
        addContainer.add(addStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 320, 50));
        addStock.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0");

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));

        addDescription.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addDescription.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane8.setViewportView(addDescription);

        addContainer.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 660, 120));

        nameError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        nameError.setForeground(new java.awt.Color(255, 51, 51));
        nameError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addContainer.add(nameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 540, -1));

        stockError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        stockError.setForeground(new java.awt.Color(255, 51, 51));
        stockError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addContainer.add(stockError, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 250, -1));

        priceError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        priceError.setForeground(new java.awt.Color(255, 51, 51));
        priceError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addContainer.add(priceError, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 250, -1));

        desError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError.setForeground(new java.awt.Color(255, 51, 51));
        desError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addContainer.add(desError, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 540, -1));

        jPanel9.add(addContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 720, 620));

        jLabel99.setBackground(new java.awt.Color(241, 241, 241));
        jLabel99.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(204, 204, 204));
        jLabel99.setText("Manage Product  >  Product Table  >");
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        tabs.addTab("tab7", jPanel9);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Stock(s)");
        jPanel13.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        getStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getStatus.setForeground(new java.awt.Color(51, 51, 51));
        getStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        getStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getStatusMouseClicked(evt);
            }
        });
        jPanel13.add(getStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 320, 50));

        getCategory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getCategory.setForeground(new java.awt.Color(51, 51, 51));
        getCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Fashion", "Grocery", "Pet Supplies" }));
        getCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getCategoryMouseClicked(evt);
            }
        });
        jPanel13.add(getCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 320, 50));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Category");
        jPanel13.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        edit_product_save_button.setBackground(new java.awt.Color(0, 158, 226));
        edit_product_save_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_product_save_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_product_save_button.setText("Save");
        edit_product_save_button.setBorderPainted(false);
        edit_product_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_product_save_buttonActionPerformed(evt);
            }
        });
        jPanel13.add(edit_product_save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 270, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Price");
        jPanel13.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Status");
        jPanel13.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Description");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Product Name");
        jPanel13.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("General infromation");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        jPanel13.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 720, 30));

        getName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getName.setForeground(new java.awt.Color(51, 51, 51));
        getName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getNameMouseClicked(evt);
            }
        });
        jPanel13.add(getName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 660, 50));

        getPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getPriceMouseClicked(evt);
            }
        });
        jPanel13.add(getPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, 50));

        getStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getStockMouseClicked(evt);
            }
        });
        jPanel13.add(getStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 320, 50));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        getDescription.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getDescription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getDescriptionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(getDescription);

        jPanel13.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 660, 120));

        editNameError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editNameError.setForeground(new java.awt.Color(255, 51, 51));
        editNameError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel13.add(editNameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 250, -1));

        editPriceError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editPriceError.setForeground(new java.awt.Color(255, 51, 51));
        editPriceError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel13.add(editPriceError, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 250, -1));

        editStockError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editStockError.setForeground(new java.awt.Color(255, 51, 51));
        editStockError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel13.add(editStockError, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 250, -1));

        desError2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError2.setForeground(new java.awt.Color(255, 51, 51));
        desError2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel13.add(desError2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 260, -1));

        desError3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError3.setForeground(new java.awt.Color(255, 51, 51));
        desError3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel13.add(desError3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 560, -1));

        desError5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError5.setForeground(new java.awt.Color(255, 51, 51));
        desError5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel13.add(desError5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 550, -1));

        desError6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError6.setForeground(new java.awt.Color(255, 51, 51));
        desError6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel13.add(desError6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 260, -1));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 720, 620));

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel15.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, 30));

        jPanel23.setBackground(new java.awt.Color(204, 204, 204));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel23.add(getPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 270, 175));

        jPanel15.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 390, 195));

        replacebtn.setBackground(new java.awt.Color(0, 158, 226));
        replacebtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        replacebtn.setForeground(new java.awt.Color(255, 255, 255));
        replacebtn.setText("Replace");
        replacebtn.setBorderPainted(false);
        replacebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replacebtnActionPerformed(evt);
            }
        });
        jPanel15.add(replacebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 190, 40));

        removetbn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        removetbn.setForeground(new java.awt.Color(51, 51, 51));
        removetbn.setText("Remove");
        removetbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removetbnActionPerformed(evt);
            }
        });
        jPanel15.add(removetbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 190, 40));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Product Image");
        jPanel15.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel43.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Edit Product");
        jPanel15.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel44.setBackground(new java.awt.Color(241, 241, 241));
        jLabel44.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(153, 153, 153));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Set the product thumbnail image. Only *.png, *.jpeg, *.jpg files are accepted.");
        jPanel15.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 390, 30));

        desError7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError7.setForeground(new java.awt.Color(255, 51, 51));
        desError7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel15.add(desError7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 260, -1));

        jPanel3.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 410, 450));

        jLabel101.setBackground(new java.awt.Color(241, 241, 241));
        jLabel101.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(204, 204, 204));
        jLabel101.setText("Manage Product  >  Product Table  >");
        jLabel101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel101MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel103.setBackground(new java.awt.Color(241, 241, 241));
        jLabel103.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(51, 51, 51));
        jLabel103.setText("Edit Product");
        jPanel3.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, 30));

        tabs.addTab("tab8", jPanel3);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        shop_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shop_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default_seller_profile.png"))); // NOI18N
        jPanel28.add(shop_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, 170));

        seperator1.setBackground(new java.awt.Color(241, 241, 241));
        seperator1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel28.add(seperator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 490, 60));

        addContainer5.setBackground(new java.awt.Color(241, 241, 241));
        addContainer5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seller_address1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_address1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        username1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        seller_phone1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_phone1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        seller_email1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        seller_store1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_store1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel65.setBackground(new java.awt.Color(241, 241, 241));
        jLabel65.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Shop details");
        addContainer5.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));
        addContainer5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 640, 10));

        jLabel58.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setText("Shop name");
        addContainer5.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel59.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("Location");
        addContainer5.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        shop_location.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_location.setForeground(new java.awt.Color(51, 51, 51));
        shop_location.setBorder(null);
        shop_location.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_locationMouseClicked(evt);
            }
        });
        addContainer5.add(shop_location, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 520, 20));

        shop_name.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_name.setForeground(new java.awt.Color(51, 51, 51));
        shop_name.setBorder(null);
        shop_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_nameMouseClicked(evt);
            }
        });
        addContainer5.add(shop_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 510, 20));

        deactivate_jpanel.setBackground(new java.awt.Color(255, 255, 255));
        deactivate_jpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(153, 153, 153));
        jLabel60.setText("Deactivating your account will remove all your access. This action cannot be undone.");
        deactivate_jpanel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 40));

        jLabel72.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("Deactivate your account?");
        deactivate_jpanel.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        deactivate.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        deactivate.setForeground(new java.awt.Color(255, 51, 51));
        deactivate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete_button.png"))); // NOI18N
        deactivate.setBorder(null);
        deactivate.setBorderPainted(false);
        deactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivateActionPerformed(evt);
            }
        });
        deactivate_jpanel.add(deactivate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, 40));

        edit_seller_close_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_seller_close_button.setForeground(new java.awt.Color(51, 51, 51));
        edit_seller_close_button.setText("Close");
        edit_seller_close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_seller_close_buttonActionPerformed(evt);
            }
        });
        deactivate_jpanel.add(edit_seller_close_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 260, 40));

        edit_seller_save_button.setBackground(new java.awt.Color(0, 158, 226));
        edit_seller_save_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_seller_save_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_seller_save_button.setText("Save Changes");
        edit_seller_save_button.setBorderPainted(false);
        edit_seller_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_seller_save_buttonActionPerformed(evt);
            }
        });
        deactivate_jpanel.add(edit_seller_save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 260, 40));

        addContainer5.add(deactivate_jpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 580, 170));

        shop_username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_username.setForeground(new java.awt.Color(51, 51, 51));
        shop_username.setBorder(null);
        shop_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_usernameMouseClicked(evt);
            }
        });
        addContainer5.add(shop_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 520, 20));

        jLabel70.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("Username");
        addContainer5.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        change_password.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        change_password.setForeground(new java.awt.Color(0, 158, 226));
        change_password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        change_password.setText("Change password");
        change_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                change_passwordMouseClicked(evt);
            }
        });
        addContainer5.add(change_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shop_icon.png"))); // NOI18N
        addContainer5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 50, -1));
        addContainer5.add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 580, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/location_icon.png"))); // NOI18N
        addContainer5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 50, -1));
        addContainer5.add(c11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 580, 40));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/username_icon.png"))); // NOI18N
        addContainer5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 50, 40));
        addContainer5.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 580, 40));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/password_icon.png"))); // NOI18N
        addContainer5.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 50, -1));

        shop_password.setText("jPasswordField1");
        shop_password.setBorder(null);
        addContainer5.add(shop_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 520, 20));
        addContainer5.add(z6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 580, 40));

        jLabel106.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(51, 51, 51));
        jLabel106.setText("Password");
        addContainer5.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jPanel28.add(addContainer5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 640, 620));

        bacgkround1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/background.png"))); // NOI18N
        jPanel28.add(bacgkround1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 180));

        jLabel63.setBackground(new java.awt.Color(241, 241, 241));
        jLabel63.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setText("Edit Seller Profile");
        jPanel28.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, 30));

        addContainer6.setBackground(new java.awt.Color(241, 241, 241));
        addContainer6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seller_address2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_address2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        username2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        seller_phone2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_phone2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        seller_email2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        seller_store2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_store2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel61.setBackground(new java.awt.Color(241, 241, 241));
        jLabel61.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Your Photo");
        addContainer6.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        jLabel64.setBackground(new java.awt.Color(241, 241, 241));
        jLabel64.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(153, 153, 153));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("This will displayed to everyone");
        addContainer6.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 190, 30));

        jLabel62.setBackground(new java.awt.Color(241, 241, 241));
        jLabel62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(153, 153, 153));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("File extension: .JPEG, .PNG and JPEG only");
        addContainer6.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 280, 30));

        edit_seller_upload_button.setBackground(new java.awt.Color(0, 158, 226));
        edit_seller_upload_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_seller_upload_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_seller_upload_button.setText("Change photo");
        edit_seller_upload_button.setBorderPainted(false);
        edit_seller_upload_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_seller_upload_buttonActionPerformed(evt);
            }
        });
        addContainer6.add(edit_seller_upload_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, 40));

        jPanel28.add(addContainer6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 490, 170));

        addContainer7.setBackground(new java.awt.Color(241, 241, 241));
        addContainer7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seller_address3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_address3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        username3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(username3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        seller_phone3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_phone3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        seller_email3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        seller_store3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_store3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel66.setBackground(new java.awt.Color(241, 241, 241));
        jLabel66.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Personal information");
        addContainer7.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));
        addContainer7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 490, 10));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("Last name");
        addContainer7.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jLabel68.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
        jLabel68.setText("Email address");
        addContainer7.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        shop_email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_email.setForeground(new java.awt.Color(51, 51, 51));
        shop_email.setBorder(null);
        shop_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_emailMouseClicked(evt);
            }
        });
        addContainer7.add(shop_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 380, 20));

        jLabel69.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(51, 51, 51));
        jLabel69.setText("Full name");
        addContainer7.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        shop_number.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_number.setForeground(new java.awt.Color(51, 51, 51));
        shop_number.setBorder(null);
        shop_number.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_numberMouseClicked(evt);
            }
        });
        addContainer7.add(shop_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 380, 20));

        shop_fname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_fname.setForeground(new java.awt.Color(51, 51, 51));
        shop_fname.setBorder(null);
        shop_fname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_fnameMouseClicked(evt);
            }
        });
        addContainer7.add(shop_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 150, 20));

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile_icon.png"))); // NOI18N
        addContainer7.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 50, 40));
        addContainer7.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 210, 40));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/number_icon.png"))); // NOI18N
        addContainer7.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 50, 40));
        addContainer7.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 440, 40));

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/email_icon.png"))); // NOI18N
        addContainer7.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 50, -1));
        addContainer7.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 440, 40));

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile_icon.png"))); // NOI18N
        addContainer7.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 50, 40));

        shop_lname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_lname.setForeground(new java.awt.Color(51, 51, 51));
        shop_lname.setBorder(null);
        shop_lname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_lnameMouseClicked(evt);
            }
        });
        addContainer7.add(shop_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 150, 20));
        addContainer7.add(c10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 210, 40));

        jLabel75.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("First name");
        addContainer7.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jPanel28.add(addContainer7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 490, 280));

        jLabel112.setBackground(new java.awt.Color(241, 241, 241));
        jLabel112.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(204, 204, 204));
        jLabel112.setText("My Profile  >  View Profile  >");
        jLabel112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel112MouseClicked(evt);
            }
        });
        jPanel28.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        tabs.addTab("tab9", jPanel28);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setBackground(new java.awt.Color(241, 241, 241));
        jLabel84.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setText("View Order");
        jPanel16.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, 30));

        jLabel104.setBackground(new java.awt.Color(241, 241, 241));
        jLabel104.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(204, 204, 204));
        jLabel104.setText("Manage Orders  >  Orders Table  >");
        jLabel104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel104MouseClicked(evt);
            }
        });
        jPanel16.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        vieworder_container3.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_container3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setBackground(new java.awt.Color(241, 241, 241));
        jLabel86.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 51, 51));
        jLabel86.setText("Shipping address");
        vieworder_container3.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/location.png"))); // NOI18N
        vieworder_container3.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 50, 30));

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/order_summary_profile.png"))); // NOI18N
        vieworder_container3.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 50, 30));

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/phone.png"))); // NOI18N
        vieworder_container3.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        vieworder_shipping_name.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vieworder_shipping_name.setForeground(new java.awt.Color(153, 153, 153));
        vieworder_shipping_name.setText("Archie Albarico");
        vieworder_container3.add(vieworder_shipping_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 110, -1));

        vieworder_shipping_number.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vieworder_shipping_number.setForeground(new java.awt.Color(153, 153, 153));
        vieworder_shipping_number.setText("Archie Albarico");
        vieworder_container3.add(vieworder_shipping_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 110, -1));

        vieworder_shipping_location.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vieworder_shipping_location.setForeground(new java.awt.Color(153, 153, 153));
        vieworder_shipping_location.setText("Archie Albarico");
        vieworder_container3.add(vieworder_shipping_location, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 110, -1));

        jPanel16.add(vieworder_container3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 350, 150));

        vieworder_container2.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_container2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/order_summary_profile.png"))); // NOI18N
        vieworder_container2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 50, 30));

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/email.png"))); // NOI18N
        vieworder_container2.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 50, 30));

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/phone.png"))); // NOI18N
        vieworder_container2.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        vieworder_contact_email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vieworder_contact_email.setForeground(new java.awt.Color(153, 153, 153));
        vieworder_contact_email.setText("Archie Albarico");
        vieworder_container2.add(vieworder_contact_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 110, -1));

        vieworder_contact_name.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vieworder_contact_name.setForeground(new java.awt.Color(153, 153, 153));
        vieworder_contact_name.setText("Archie Albarico");
        vieworder_container2.add(vieworder_contact_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 110, -1));

        vieworder_contact_number.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vieworder_contact_number.setForeground(new java.awt.Color(153, 153, 153));
        vieworder_contact_number.setText("Archie Albarico");
        vieworder_container2.add(vieworder_contact_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 110, -1));

        jLabel95.setBackground(new java.awt.Color(241, 241, 241));
        jLabel95.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(51, 51, 51));
        jLabel95.setText("Contact information");
        vieworder_container2.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel16.add(vieworder_container2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 350, 150));

        vieworder_container4.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_container4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel87.setBackground(new java.awt.Color(241, 241, 241));
        jLabel87.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(51, 51, 51));
        jLabel87.setText("Notes");
        vieworder_container4.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        vieworder_notes.setColumns(20);
        vieworder_notes.setRows(5);
        jScrollPane2.setViewportView(vieworder_notes);

        vieworder_container4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 310, 210));

        jPanel16.add(vieworder_container4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 380, 350, 270));

        vieworder_container.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        vieworder_background.setBackground(new java.awt.Color(255, 255, 204));
        vieworder_background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        vieworder_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vieworder_background.add(vieworder_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 100));

        vieworder_container.add(vieworder_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 140));

        vieworder_date.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_date.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vieworder_date.setForeground(new java.awt.Color(51, 51, 51));
        vieworder_date.setText("May 1, 2023");
        vieworder_container.add(vieworder_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        vieworder_total.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_total.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        vieworder_total.setForeground(new java.awt.Color(51, 51, 51));
        vieworder_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        vieworder_total.setText("3 x ₱ 2,300");
        vieworder_container.add(vieworder_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, -1, 30));

        vieworder_orderID.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_orderID.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        vieworder_orderID.setForeground(new java.awt.Color(51, 51, 51));
        vieworder_orderID.setText("#1001111");
        vieworder_container.add(vieworder_orderID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        vieworder_category.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_category.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        vieworder_category.setForeground(new java.awt.Color(153, 153, 153));
        vieworder_category.setText("Category");
        vieworder_container.add(vieworder_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, 20));

        vieworder_product_name.setBackground(new java.awt.Color(241, 241, 241));
        vieworder_product_name.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        vieworder_product_name.setForeground(new java.awt.Color(51, 51, 51));
        vieworder_product_name.setText("Macbook Air");
        vieworder_container.add(vieworder_product_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, 30));

        vieworder_status.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        vieworder_status.setForeground(new java.awt.Color(204, 0, 0));
        vieworder_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vieworder_status.setText("Pending");
        vieworder_container.add(vieworder_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 110, 20));

        status_background.setBackground(new java.awt.Color(255, 204, 204));
        status_background.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        status_background.setBorderPainted(false);
        vieworder_container.add(status_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 110, 40));

        jPanel16.add(vieworder_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 780, 220));

        productsContainer3.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        orders_table.setAutoCreateRowSorter(true);
        orders_table.setBackground(new java.awt.Color(241, 241, 241));
        orders_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        orders_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        orders_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orders_tableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(orders_table);

        productsContainer3.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 780, 300));

        jLabel89.setBackground(new java.awt.Color(241, 241, 241));
        jLabel89.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 51));
        jLabel89.setText("Product Table");
        productsContainer3.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        jButton3.setBackground(new java.awt.Color(0, 158, 226));
        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("View order");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        productsContainer3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 160, 40));

        jLabel88.setBackground(new java.awt.Color(241, 241, 241));
        jLabel88.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 51, 51));
        jLabel88.setText("Order summary");
        productsContainer3.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        decline.setBackground(new java.awt.Color(255, 102, 102));
        decline.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        decline.setForeground(new java.awt.Color(255, 255, 255));
        decline.setText("Decline");
        decline.setBorder(null);
        decline.setBorderPainted(false);
        decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineActionPerformed(evt);
            }
        });
        productsContainer3.add(decline, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 130, 40));

        accept_order.setBackground(new java.awt.Color(122, 183, 147));
        accept_order.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        accept_order.setForeground(new java.awt.Color(255, 255, 255));
        accept_order.setText("Accept");
        accept_order.setBorder(null);
        accept_order.setBorderPainted(false);
        accept_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accept_orderActionPerformed(evt);
            }
        });
        productsContainer3.add(accept_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 130, 40));

        jPanel16.add(productsContainer3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 780, 370));

        tabs.addTab("tab10", jPanel16);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        messageContainer.setBackground(new java.awt.Color(241, 241, 241));
        messageContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        message_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        message_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        message_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageContainer.add(message_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 290, 1150, 60));
        messageContainer.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 10));

        jScrollPane12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        message4admin_table.setAutoCreateRowSorter(true);
        message4admin_table.setBackground(new java.awt.Color(241, 241, 241));
        message4admin_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        message4admin_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        message4admin_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                message4admin_tableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(message4admin_table);

        messageContainer.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 540));

        message_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        message_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        message_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                message_search_barMouseClicked(evt);
            }
        });
        message_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                message_search_barKeyReleased(evt);
            }
        });
        messageContainer.add(message_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel36.setBackground(new java.awt.Color(241, 241, 241));
        jLabel36.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Product Table");
        messageContainer.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer3.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel74.setBackground(new java.awt.Color(241, 241, 241));
        jLabel74.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Filter by:");
        filterContainer3.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table1.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Status", "Deactivate account", "Delete product", "Report bug", "Request feature" }));
        filter_product_table1.setSelectedIndex(0);
        filter_product_table1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filter_product_table1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_product_table1ActionPerformed(evt);
            }
        });
        filterContainer3.add(filter_product_table1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 160, 30));

        messageContainer.add(filterContainer3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 240, 40));

        jPanel20.add(messageContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1150, 620));

        jLabel108.setBackground(new java.awt.Color(241, 241, 241));
        jLabel108.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(204, 204, 204));
        jLabel108.setText("Contact Admin  >  Help Center  >");
        jLabel108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel108MouseClicked(evt);
            }
        });
        jPanel20.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel110.setBackground(new java.awt.Color(241, 241, 241));
        jLabel110.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(51, 51, 51));
        jLabel110.setText("View Report Status Table");
        jPanel20.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, 30));

        tabs.addTab("tab11", jPanel20);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        wishlist_container.setBackground(new java.awt.Color(241, 241, 241));
        wishlist_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        wishlist_container.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1140, 30));

        wishlist_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        wishlist_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        wishlist_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wishlist_container.add(wishlist_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 290, 1150, 60));

        jScrollPane13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        wishlist_table.setAutoCreateRowSorter(true);
        wishlist_table.setBackground(new java.awt.Color(241, 241, 241));
        wishlist_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        wishlist_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        wishlist_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wishlist_tableMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(wishlist_table);

        wishlist_container.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 540));

        wishlist_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        wishlist_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        wishlist_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wishlist_search_barMouseClicked(evt);
            }
        });
        wishlist_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                wishlist_search_barKeyReleased(evt);
            }
        });
        wishlist_container.add(wishlist_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel141.setBackground(new java.awt.Color(241, 241, 241));
        jLabel141.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(51, 51, 51));
        jLabel141.setText("Product Table");
        wishlist_container.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer4.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setBackground(new java.awt.Color(241, 241, 241));
        jLabel45.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Filter by:");
        filterContainer4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table2.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Categories", "Electronics", "Grocery", "Pet Supplies", "Fashion" }));
        filter_product_table2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filter_product_table2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_product_table2ActionPerformed(evt);
            }
        });
        filterContainer4.add(filter_product_table2, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        wishlist_container.add(filterContainer4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        jPanel17.add(wishlist_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1150, 620));

        jLabel142.setBackground(new java.awt.Color(241, 241, 241));
        jLabel142.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(204, 204, 204));
        jLabel142.setText("Wishlist  >");
        jPanel17.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel143.setBackground(new java.awt.Color(241, 241, 241));
        jLabel143.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(51, 51, 51));
        jLabel143.setText("Wishlist table");
        jPanel17.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 30));

        tabs.addTab("tab12", jPanel17);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -20, 1190, 740));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1180, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        Login out = new Login();
        out.setVisible(true);
        this.dispose();

        String action = "Logged out";
        String details = "Seller " + sellerID + " Successfully logged out!";
        actionLogs.recordSellerLogs(sellerID, action, details);
    }//GEN-LAST:event_logoutActionPerformed
    String fileName;
    String imagePath;
    private void product_table_add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_add_buttonActionPerformed
        tabs.setSelectedIndex(6);
    }//GEN-LAST:event_product_table_add_buttonActionPerformed

    File selectedFile;
    String getImage;

    private void edit_profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_profileActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id =" + sellerID);
            if (rs.next()) {
                shop_fname.setText("" + rs.getString("first_name"));
                shop_lname.setText("" + rs.getString("last_name"));
                shop_email.setText("" + rs.getString("email"));
                shop_number.setText("" + rs.getString("phone_number"));
                shop_username.setText("" + rs.getString("username"));
                shop_password.setText("Password");
                shop_location.setText("" + rs.getString("address"));
                shop_name.setText("" + rs.getString("shop_name"));
                int height = 120;
                int width = 120;
                getImage = rs.getString("profile_picture");
                GetImage.displayImage(shop_photo, getImage, height, width);
                tabs.setSelectedIndex(8);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_edit_profileActionPerformed

    private void product_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_product_search_barKeyReleased
        search.searchResult(product_table, product_search_bar);
    }//GEN-LAST:event_product_search_barKeyReleased

    private void product_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_search_barMouseClicked
        product_search_bar.setFocusable(true);
        product_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_product_search_barMouseClicked

    private void archive_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archive_tableMouseClicked
        int rowIndex = archive_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = archive_table.getModel();

            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    productID.setText("" + rs.getString("product_id"));
                    productPrice.setText("₱  " + rs.getString("product_price"));
                    productName.setText("" + rs.getString("product_name"));
                    productQuantity.setText("" + rs.getString("product_stock"));
                    productStatus.setText("" + rs.getString("product_status"));
                    descript.setText("" + rs.getString("product_description"));
                    int height = 70;
                    int width = 70;
                    String getImageFromDatabase = rs.getString("product_image");
                    GetImage.displayImage(productPhoto, getImageFromDatabase, height, width);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_archive_tableMouseClicked

    private void restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            int pid = Integer.parseInt(productID.getText());
            String sql = "UPDATE tbl_products SET product_status='Available' WHERE product_id = ?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, pid);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    displayProducts();
                    displayArchive();
                    JOptionPane.showMessageDialog(null, "Product has been retored Successfully!");
                    String action = "Restore";
                    String details = "Seller " + sellerID + " Successfully restore product " + pid + "!";
                    actionLogs.recordSellerLogs(sellerID, action, details);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to restore Product!");
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_restoreActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int rowIndex = archive_table.getSelectedRow(); // Get the selected row index
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a product first");
        } else {
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                int pid = (int) archive_table.getValueAt(rowIndex, 0);
                int sold1 = (int) archive_table.getValueAt(rowIndex, 5);
                if (sold1 > 0) {
                    JOptionPane.showMessageDialog(null, "You can't delete this product. Please contact the administrator!");
                    return;
                }
                try {
                    databaseConnector dbc = new databaseConnector();
                    dbc.deleteProduct(pid);
                    displayProducts();
                    displayArchive();
                    JOptionPane.showMessageDialog(null, "Product deleted successfully!");

                    // logs
                    String details = "User " + sellerID + " successfully deleted the product " + pid + "!";
                    String action = "Delete product";
                    actionLogs.recordSellerLogs(sellerID, action, details);
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void product_table_archive_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_archive_buttonActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();

            String sql = "UPDATE tbl_products SET product_status = 'Archive' WHERE product_id = ?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, p_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Product added to archive Successfully!");
                    displayProducts();
                    displayArchive();
                    String action = "Archive";
                    String details = "Seller " + sellerID + " Successfully put product " + p_id + " to archive!";
                    actionLogs.recordSellerLogs(sellerID, action, details);
                    displayTotalProducts(sellerID);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add the product to archive!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_product_table_archive_buttonActionPerformed

    private static int p_id;
    private static int sold = 0;
    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
        int rowIndex = product_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
            return;
        }
        TableModel model = product_table.getModel();

        try {
            databaseConnector dbc = new databaseConnector();

            ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id = " + model.getValueAt(rowIndex, 0));

            if (rs.next()) {
                p_id = rs.getInt("product_id");
                getName.setText("" + rs.getString("product_name"));
                getPrice.setText("" + rs.getString("product_price"));
                getStock.setText("" + rs.getString("product_stock"));
                getCategory.setSelectedItem(rs.getString("product_category"));
                getStatus.setSelectedItem(rs.getString("product_status"));
                getDescription.setText("" + rs.getString("product_description"));
                String getImageFromDatabase = rs.getString("product_image");
                int height = 160;
                int width = 160;
                GetImage.displayImage(getPhoto, getImageFromDatabase, height, width);
                sold = rs.getInt("total_sold");
            } else {
                JOptionPane.showMessageDialog(null, "Product details not found!");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void accept_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accept_orderActionPerformed
        int rowIndex = purchase_table.getSelectedRow();
        newStock = product_stock - total_quantity;
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a product first");
            return;
        }

        try {
            databaseConnector dbc = new databaseConnector();
            String orderStatusQuery = "SELECT order_status, total_quantity, product_id FROM tbl_orders WHERE order_id = ?";
            try (PreparedStatement statusStmt = dbc.getConnection().prepareStatement(orderStatusQuery)) {
                statusStmt.setInt(1, transaction_id);
                ResultSet rs = statusStmt.executeQuery();

                if (rs.next()) {
                    String orderStatus = rs.getString("order_status");
                    int totalQuantity = rs.getInt("total_quantity");
                    int productId = rs.getInt("product_id");

                    if (!"Pending".equals(orderStatus)) {
                        JOptionPane.showMessageDialog(null, "You cannot make changes in this order anymore.");
                        return;
                    }
                    String fetchProductQuery = "SELECT product_stock FROM tbl_products WHERE product_id = ?";
                    PreparedStatement fetchProductStmt = dbc.getConnection().prepareStatement(fetchProductQuery);
                    fetchProductStmt.setInt(1, productId);
                    ResultSet fetchRs = fetchProductStmt.executeQuery();

                    if (fetchRs.next()) {
                        int currentStock = fetchRs.getInt("product_stock");
                        if (currentStock < totalQuantity) {
                            JOptionPane.showMessageDialog(null, "Not enough stock to accept this order.");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Product not found!");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Order not found!");
                    return;
                }
            }

            // Proceed to accept the order
            String updateOrderQuery = "UPDATE tbl_orders SET order_status='Accepted' WHERE order_id=?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(updateOrderQuery)) {
                pst.setInt(1, transaction_id);
                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    updateStock(newStock, product_id);
                    if (newStock < 1) {
                        updateStatus("Sold out");
                    }
                    updateProductSold(total_quantity, product_id); // Add quantity sold
                    JOptionPane.showMessageDialog(null, "Order has been accepted successfully!");
                    String action = "Accept Order";
                    String details = "Seller " + sellerID + " successfully accepted order " + transaction_id + "!";
                    actionLogs.recordSellerLogs(sellerID, action, details);
                    displayPurchase();
                    displayProducts();
                    display_orders_dashboard();
                    newStock = 0;
                    // Clear
                    transaction_id = 0;
                    buyer_id = 0;
                    product_id = 0;
                    clear_order_table();
                    displayTotalSales(sellerID);
                    displayTotalPendingOrders(sellerID);
                    displayTotalOrders(sellerID);
                    tabs.setSelectedIndex(3);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_accept_orderActionPerformed

    private void updateProductSold(int quantitySold, int productId) {
        databaseConnector dbc = new databaseConnector();
        String sql;
        sql = "UPDATE tbl_products SET total_sold = total_sold + ? WHERE product_id = ?";
        try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
            pst.setInt(1, quantitySold);
            pst.setInt(2, productId);
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating product sold data: " + e.getMessage());
        }
    }

    private static int product_stock;
    private static int total_quantity;
    private static int newStock = 0;

    public static void getStock() {
        try {

            databaseConnector dbc = new databaseConnector();

            String query = "SELECT product_stock FROM tbl_products WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product_stock = rs.getInt("product_stock");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void updateStock(int newStock, int productId) {
        try {

            databaseConnector dbc = new databaseConnector();

            String updateQuery = "UPDATE tbl_products SET product_stock = ? WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setInt(1, newStock);
            pst.setInt(2, product_id);
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void updateStatus(String status) {
        try {
            databaseConnector dbc = new databaseConnector();

            String updateQuery = "UPDATE tbl_products SET product_status = ? WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, status);
            pst.setInt(2, product_id);
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    private void clear_order_table() {
        vieworder_shipping_name.setText("");
        vieworder_shipping_location.setText("");
        vieworder_shipping_number.setText("");
        vieworder_contact_name.setText("");
        vieworder_contact_email.setText("");
        vieworder_contact_email.setText("");
        vieworder_category.setText("");
        vieworder_product_name.setText("");
        vieworder_total.setText("");
        vieworder_orderID.setText("");
        vieworder_date.setText("");
        vieworder_status.setText("");
        vieworder_photo.setIcon(null);
    }


    private void declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineActionPerformed
        int rowIndex = purchase_table.getSelectedRow();

        try {
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "Please select a product first");
                return;
            }
            databaseConnector dbc = new databaseConnector();

            String orderStatusQuery = "SELECT order_status FROM tbl_orders WHERE order_id = ?";
            try (PreparedStatement statusStmt = dbc.getConnection().prepareStatement(orderStatusQuery)) {
                statusStmt.setInt(1, transaction_id);
                ResultSet rs = statusStmt.executeQuery();

                if (rs.next()) {
                    String orderStatus = rs.getString("order_status");

                    if (!"Pending".equals(orderStatus)) {
                        JOptionPane.showMessageDialog(null, "You cannot make changes in this order anymore.");
                        return;
                    }
                }

                String sql = "UPDATE tbl_orders SET `order_status`='Declined' WHERE `order_id`=?";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setInt(1, transaction_id);
                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Order has been declined Successfully!");
                        String action = "Declined Order";
                        String details = "Seller " + sellerID + " declined order " + transaction_id + "!";
                        actionLogs.recordSellerLogs(sellerID, action, details);
                        displayPurchase();
                        displayProducts();
                        display_orders_dashboard();
                        newStock = 0;
                        // Clear
                        transaction_id = 0;
                        buyer_id = 0;
                        product_id = 0;
                        clear_order_table();
                        displayTotalSales(sellerID);
                        displayTotalPendingOrders(sellerID);
                        displayTotalOrders(sellerID);
                        tabs.setSelectedIndex(3);
                    }
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_declineActionPerformed

    private static int buyer_id;
    private static int product_id;
    private static int transaction_id;
    private static String order_status;

    public static void displayProductPhoto(JLabel photo) {
        try {

            databaseConnector dbc = new databaseConnector();

            String query = "SELECT product_image FROM tbl_products WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String getImageFromDatabase = rs.getString("product_image");
                int height = 120;
                int width = 110;
                GetImage.displayImage(photo, getImageFromDatabase, height, width);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void displaySalesInfo(JLabel fullname, JLabel profile) {
        try {

            databaseConnector dbc = new databaseConnector();

            String query = "SELECT first_name, last_name, profile_picture FROM tbl_accounts WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, buyer_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String getImageFromDatabase = rs.getString("profile_picture");

                firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);

                fullname.setText(firstName + " " + lastName);
                int height = 40;
                int width = 40;
                GetImage.displayImage(profile, getImageFromDatabase, height, width);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        displayProfileInfo();
        manage.setSelected(false);
        orders.setSelected(false);
        archiveBtn.setSelected(false);
        dashboard.setSelected(false);
        admin_support.setSelected(false);
        actionLogs.displaySellerLogs(actionlogs_table, sellerID); // display seller logs table
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_profileMouseClicked

    private void product_table_edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_edit_buttonActionPerformed
        try {
            int rowIndex = product_table.getSelectedRow();
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "Please Select an Item!");
            } else {
                TableModel model = product_table.getModel();
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id = " + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    int height = 270;
                    int width = 175;
                    String getImageFromDatabase = rs.getString("product_image");
                    GetImage.displayImage(getPhoto, getImageFromDatabase, height, width);
                    getName.setText(rs.getString("product_name"));
                    getPrice.setText(rs.getString("product_price"));
                    getStock.setText(rs.getString("product_stock"));
                    getDescription.setText(rs.getString("product_description"));
                    getStatus.setSelectedItem(rs.getString("product_status"));

                    tabs.setSelectedIndex(7);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_product_table_edit_buttonActionPerformed

    private void getPhoto(JLabel photo) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                Image resizedImage = originalImage.getScaledInstance(270, 175, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                photo.setIcon(icon);

                String imageName = selectedFile.getName();
                imagePath = "src/ProductsImages/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                selectedFile = destination;

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        tabs.setSelectedIndex(0);
        manage.setSelected(false);
        orders.setSelected(false);
        archiveBtn.setSelected(false);
        admin_support.setSelected(false);
    }//GEN-LAST:event_dashboardActionPerformed

    private void manageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageActionPerformed
        displayProducts();
        tabs.setSelectedIndex(1);
        dashboard.setSelected(false);
        orders.setSelected(false);
        archiveBtn.setSelected(false);
        admin_support.setSelected(false);
    }//GEN-LAST:event_manageActionPerformed

    private void ordersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersActionPerformed
        displayPurchase();
        tabs.setSelectedIndex(3);
        dashboard.setSelected(false);
        manage.setSelected(false);
        archiveBtn.setSelected(false);
        admin_support.setSelected(false);
    }//GEN-LAST:event_ordersActionPerformed

    private void archiveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveBtnActionPerformed
        displayArchive();
        tabs.setSelectedIndex(2);
        dashboard.setSelected(false);
        manage.setSelected(false);
        orders.setSelected(false);
        admin_support.setSelected(false);
    }//GEN-LAST:event_archiveBtnActionPerformed

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        dashboard.setSelected(true);
    }//GEN-LAST:event_dashboardMouseClicked

    private void manageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageMouseClicked
        manage.setSelected(true);
    }//GEN-LAST:event_manageMouseClicked

    private void ordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseClicked
        orders.setSelected(true);
    }//GEN-LAST:event_ordersMouseClicked

    private void archiveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiveBtnMouseClicked
        archiveBtn.setSelected(true);
    }//GEN-LAST:event_archiveBtnMouseClicked

    private void emptyValues() {
        getName.setText("");
        getPrice.setText("");
        getStock.setText("");
        getStatus.setSelectedIndex(0);
        getDescription.setText("");
        getPhoto.setIcon(null);
    }

    private void edit_product_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_product_save_buttonActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String productName = getName.getText();
            String productPrice = getPrice.getText();
            String productStocks = getStock.getText();
            String productDescription = getDescription.getText();
            String productStatus = (String) getStatus.getSelectedItem();
            String productCategory = (String) getCategory.getSelectedItem();

            // Validate input fields
            if (productName.isEmpty() || productPrice.isEmpty() || productStocks.isEmpty() || productDescription.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Please fill in all fields!");
                return;
            }
            if (productName.length() > 21) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Product name must not exceed 21 characters!");
                editNameError.setText("* Product name must not exceed 21 characters.");
                getName.setText("");
                return;
            }

            if (productDescription.length() > 999) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Desccription exceed 900 characters!");
                desError3.setText("* Desccription must not exceed 900 characters!");
                return;
            }

            if (selectedFile == null || !selectedFile.exists()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Please select an image!");
                desError7.setText("* Product image cannot be empty.");
                return;
            }

            // Additional validation for numeric fields
            int price;
            int stocks;
            try {
                price = Integer.parseInt(productPrice);
            } catch (NumberFormatException ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Price must be numeric!");
                return;
            }

            try {
                stocks = Integer.parseInt(productStocks);
            } catch (NumberFormatException ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Stocks must be numeric!");
                return;
            }

            if (price <= 0) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Price must be greater than zero!");

                editPriceError.setText("* Price must be greater than zero.");
                getPrice.setText("");
                return;
            }
            if (stocks <= 0) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Stocks must be greater than zero!");
                editStockError.setText("* Stocks must be greater than zero.");
                getStock.setText("");
                return;
            }

            if (selectedFile != null) {

                sql = "UPDATE tbl_products SET "
                        + "product_name=?, "
                        + "product_price=?, "
                        + "product_stock=?, "
                        + "product_description=?, "
                        + "product_image=?, "
                        + "product_status=?, "
                        + "product_category=? "
                        + "WHERE product_id=?";
            } else {
                sql = "UPDATE tbl_products SET "
                        + "product_name=?, "
                        + "product_price=?, "
                        + "product_stock=?, "
                        + "product_description=?, "
                        + "product_status=?, "
                        + "product_category=? "
                        + "WHERE product_id=?";
            }

            String checkQuery = "SELECT COUNT(*) FROM tbl_products WHERE product_name = ?";
            PreparedStatement checkStmt = dbc.getConnection().prepareStatement(checkQuery);
            checkStmt.setString(1, productName);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Product already exist!");
                getName.setText("");
                return;
            }

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setString(1, productName);
                pst.setInt(2, price);
                pst.setInt(3, stocks);
                pst.setString(4, productDescription);

                if (selectedFile != null) {
                    pst.setString(5, imagePath);
                    pst.setString(6, productStatus);
                    pst.setString(7, productCategory);
                    pst.setInt(8, p_id);
                } else {
                    pst.setString(5, productStatus);
                    pst.setString(6, productCategory);
                    pst.setInt(7, p_id);
                }

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                    if (selectedFile == null) {
                        String action = "Edit Product";
                        String details = "Seller " + sellerID + " successfully edited product " + p_id + "!";
                        actionLogs.recordSellerLogs(sellerID, action, details);
                    }
                    displayProducts();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update data!");
                }
            }

            emptyValues();
            p_id = 0;
            displayTotalProducts(sellerID);
            displayProducts();
            removeErrorsMessage();
            tabs.setSelectedIndex(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_edit_product_save_buttonActionPerformed

    private void replacebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replacebtnActionPerformed
        getPhoto(getPhoto);
    }//GEN-LAST:event_replacebtnActionPerformed

    private void removetbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removetbnActionPerformed
        getPhoto.setIcon(null);
        selectedFile = null;
        imagePath = null;
    }//GEN-LAST:event_removetbnActionPerformed

    private void addReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReplaceActionPerformed
        getPhoto(addPhoto);
    }//GEN-LAST:event_addReplaceActionPerformed

    private void addRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRemoveActionPerformed
        addPhoto.setIcon(null);
        selectedFile = null;
        imagePath = null;
    }//GEN-LAST:event_addRemoveActionPerformed

    private void defaultValues() {
        addName.setText("PlayStation 5");
        addName.setForeground(Color.decode("#999999"));
        addPrice.setText("₱");
        addPrice.setForeground(Color.decode("#999999"));
        addStock.setText("0");
        addStock.setForeground(Color.decode("#999999"));
        add_category.setSelectedIndex(0);
        add_category.setForeground(Color.decode("#999999"));
        addDescription.setText("");
        addDescription.setForeground(Color.decode("#999999"));
        addPhoto.setIcon(null);
    }

    private void removeErrorsMessage() {
        //add
        priceError.setText("");
        nameError.setText("");
        stockError.setText("");
        desError.setText("");
        errorImage.setText("");

        //edit
        desError2.setText("");
        desError3.setText("");
        desError5.setText("");
        desError6.setText("");
        desError7.setText("");
    }

    private void add_product_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_product_save_buttonActionPerformed
        String valname = addName.getText();
        String valprice = addPrice.getText();
        String valstocks = addStock.getText();
        String valdes = addDescription.getText();
        String valstats = (String) add_category.getSelectedItem();
        String valcategory = (String) addCategory.getSelectedItem();

        if ((valdes.isEmpty() || valname.isEmpty() || valprice.equals("₱") || valprice.isEmpty() || valstocks.isEmpty())
                || (addName.getForeground().equals(Color.decode("#999999")))) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Please fill in all fields!");
            return;
        }

        if (selectedFile == null || !selectedFile.exists()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Please select an image!");
            errorImage.setText("* Product image cannot be empty.");
            return;
        }

        if (valname.length() > 21) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Product name must not exceed 21 characters!");
            nameError.setText("* Product name must not exceed 21 characters.");
            addName.setText("");
            return;
        }

        if (valdes.length() > 999) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Desccription exceed 900 characters!");
            desError.setText("* Desccription must not exceed 900 characters!");
            return;
        }

        int price;
        int stocks;

        try {
            price = Integer.parseInt(valprice);
        } catch (NumberFormatException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Price must be numeric!");
            return;
        }

        try {
            stocks = Integer.parseInt(valstocks);
        } catch (NumberFormatException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Stocks must be numeric!");
            return;
        }

        if (price <= 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Price must be greater than zero!");
            priceError.setText("* Price must be greater than zero.");
            addPrice.setText("");
            return;
        }
        if (stocks <= 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Stocks must be greater than zero!");
            stockError.setText("* Stocks must be greater than zero.");
            addStock.setText("");
            return;
        }

        try {
            databaseConnector dbc = new databaseConnector();

            String checkQuery = "SELECT COUNT(*) FROM tbl_products WHERE product_name = ?";
            PreparedStatement checkStmt = dbc.getConnection().prepareStatement(checkQuery);
            checkStmt.setString(1, valname);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Product already exist!");
                return;
            }

            String insertQuery = "INSERT INTO tbl_products"
                    + "(`seller_id`,"
                    + "`product_name`,"
                    + "`product_price`,"
                    + "`product_stock`,"
                    + "`product_description`,"
                    + "`product_status`,"
                    + "`product_image`,"
                    + "`product_category`,"
                    + "`date_created`)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
            try (PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setInt(1, sellerID);
                insertStmt.setString(2, valname);
                insertStmt.setInt(3, price);
                insertStmt.setInt(4, stocks);
                insertStmt.setString(5, valdes);
                insertStmt.setString(6, valstats);
                insertStmt.setString(7, imagePath);
                insertStmt.setString(8, valcategory);

                insertStmt.executeUpdate();
            }
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Product added successfully!");

            String action = "Add Product";
            String details = "Seller " + sellerID + " Successfully added a new product!";
            actionLogs.recordSellerLogs(sellerID, action, details);
            defaultValues();
            removeErrorsMessage();
            displayTotalProducts(sellerID);
            displayProducts();
            tabs.setSelectedIndex(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error adding product!" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_add_product_save_buttonActionPerformed

    private void activity_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activity_search_barMouseClicked
        activity_search_bar.setFocusable(true);
        activity_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_activity_search_barMouseClicked

    private void activity_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_activity_search_barKeyReleased
        search.searchResult(actionlogs_table, activity_search_bar);
    }//GEN-LAST:event_activity_search_barKeyReleased

    private void add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add1ActionPerformed

    private void messages_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messages_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_messages_tableMouseClicked

    private void add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add2ActionPerformed

    private void add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add3ActionPerformed

    private void add4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add4ActionPerformed

    private void add5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add5ActionPerformed

    private void edit_seller_upload_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_seller_upload_buttonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                Image resizedImage = originalImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                shop_photo.setIcon(icon);

                String imageName = selectedFile.getName();
                imagePath = "src/sampleProfiles/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Update the selectedFile to point to the new location
                selectedFile = destination;

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_edit_seller_upload_buttonActionPerformed

    private void shop_locationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_locationMouseClicked
        shop_location.setFocusable(true);
        shop_location.requestFocusInWindow();
    }//GEN-LAST:event_shop_locationMouseClicked

    private void shop_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_nameMouseClicked
        shop_name.setFocusable(true);
        shop_name.requestFocusInWindow();
    }//GEN-LAST:event_shop_nameMouseClicked

    private void shop_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_emailMouseClicked
        shop_email.setFocusable(true);
        shop_email.requestFocusInWindow();
    }//GEN-LAST:event_shop_emailMouseClicked

    private void shop_numberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_numberMouseClicked
        shop_number.setFocusable(true);
        shop_number.requestFocusInWindow();
    }//GEN-LAST:event_shop_numberMouseClicked

    private void shop_fnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_fnameMouseClicked
        shop_fname.setFocusable(true);
        shop_fname.requestFocusInWindow();
    }//GEN-LAST:event_shop_fnameMouseClicked

    private void shop_usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_usernameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_shop_usernameMouseClicked

    private void deactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivateActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();

            // Check if the seller has any products
            String checkProductsSql = "SELECT COUNT(*) FROM tbl_products WHERE seller_id = ?";
            try (PreparedStatement checkPst = dbc.getConnection().prepareStatement(checkProductsSql)) {
                checkPst.setInt(1, sellerID);

                try (ResultSet rs = checkPst.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        // No products found, deactivate the account
                        String deactivateSql = "UPDATE tbl_accounts SET status = ? WHERE account_id = ?";
                        try (PreparedStatement pst = dbc.getConnection().prepareStatement(deactivateSql)) {
                            pst.setString(1, "Deactivated");
                            pst.setInt(2, sellerID);

                            int rowsUpdated = pst.executeUpdate();

                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(null, "Account Deactivated Successfully!");
                                // logs
                                String details = "User " + sellerID + " successfully deactivated their account!";
                                String action = "Change Status";
                                actionLogs.recordAdminLogs(sellerID, action, details);
                                Login out = new Login();
                                out.setVisible(true);
                                this.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to deactivate account!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Account deactivation failed. Contact the administrator.");
                    }
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_deactivateActionPerformed

    private void edit_seller_close_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_seller_close_buttonActionPerformed
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_edit_seller_close_buttonActionPerformed

    private void edit_seller_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_seller_save_buttonActionPerformed
        String val_fame = shop_fname.getText();
        String val_lame = shop_lname.getText();
        String val_number = shop_number.getText();
        String val_shop = shop_name.getText();
        String val_location = shop_location.getText();
        String val_email = shop_email.getText();

        // Check if any field is empty and if selectedFile is not empty
        if ((val_location.isEmpty() || val_fame.isEmpty() || val_lame.isEmpty() || val_number.isEmpty() || val_shop.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (val_number.length() < 11 || val_number.length() > 12 || !val_number.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isAccountExist.checkEmail(val_email)) {
            JOptionPane.showMessageDialog(null, "Email already registered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql;
        PreparedStatement pst;

        if (selectedFile != null && selectedFile.exists()) {
            fileName = selectedFile.getName();
            imagePath = "src/sampleProfiles/" + fileName;
        } else {
            imagePath = getImage;
        }

        try {
            databaseConnector dbc = new databaseConnector();

            sql = "UPDATE tbl_accounts SET first_name=?, last_name=?, address=?, phone_number=?, email=?, shop_name=?, profile_picture=? WHERE account_id=?";
            pst = dbc.getConnection().prepareStatement(sql);
            pst.setString(1, val_fame);
            pst.setString(2, val_lame);
            pst.setString(3, val_location);
            pst.setString(4, val_number);
            pst.setString(5, val_email);
            pst.setString(6, val_shop);
            pst.setString(7, imagePath);
            pst.setInt(8, sellerID);

            int rowsUpdated = pst.executeUpdate();
            pst.close();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
                //logs
                String details = "User " + sellerID + " Successfully change profile info!";
                String action = "Change profile";

                actionLogs.recordAdminLogs(sellerID, action, details);
                actionLogs.displaySellerLogs(actionlogs_table, sellerID);
                displayProfileInfo();
                tabs.setSelectedIndex(5);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update Account!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error adding product!" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_edit_seller_save_buttonActionPerformed

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private void purchase_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchase_tableMouseClicked

    }//GEN-LAST:event_purchase_tableMouseClicked

    private void orders_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orders_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_orders_tableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void view_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_orderActionPerformed
        int rowIndex = purchase_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select an item!");
            return;
        }

        TableModel model = purchase_table.getModel();
        transaction_id = (int) model.getValueAt(rowIndex, 0);
        buyer_id = (int) model.getValueAt(rowIndex, 1);
        product_id = (int) model.getValueAt(rowIndex, 3);

        try {
            databaseConnector dbc = new databaseConnector();
            String orderQuery = "SELECT * FROM tbl_orders WHERE order_id = " + transaction_id;
            String accountQuery = "SELECT first_name, last_name, address, email, phone_number FROM tbl_accounts WHERE account_id = " + buyer_id;
            String productQuery = "SELECT product_name, product_price, product_category FROM tbl_products WHERE product_id = " + product_id;

            try (ResultSet rsOrder = dbc.getData(orderQuery); ResultSet rsAccount = dbc.getData(accountQuery); ResultSet rsProduct = dbc.getData(productQuery)) {
                if (rsOrder.next() && rsAccount.next() && rsProduct.next()) {
                    // Buyer profile
                    String phoneNumber = rsAccount.getString("phone_number");
                    vieworder_shipping_number.setText(phoneNumber);
                    vieworder_contact_number.setText(phoneNumber);

                    String firstName = rsAccount.getString("first_name");
                    String lastName = rsAccount.getString("last_name");
                    firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                    lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);

                    String fullName = capitalize(firstName) + " " + capitalize(lastName);

                    vieworder_contact_name.setText(fullName);
                    vieworder_shipping_name.setText(fullName);
                    vieworder_contact_email.setText(rsAccount.getString("email"));
                    vieworder_shipping_location.setText(rsAccount.getString("address"));

                    // Product information
                    vieworder_product_name.setText(rsProduct.getString("product_name"));
                    int price = rsProduct.getInt("product_price");
                    int quantity = rsOrder.getInt("total_quantity");
                    vieworder_total.setText(quantity + " x " + price);
                    vieworder_category.setText(rsProduct.getString("product_category"));

                    // Order information
                    total_quantity = rsOrder.getInt("total_quantity");
                    vieworder_notes.setText(rsOrder.getString("notes"));
                    vieworder_date.setText(rsOrder.getString("date_purchase"));
                    String status = rsOrder.getString("order_status");
                    vieworder_status.setText(status);

                    switch (status) {
                        case "Pending":
                            status_background.setBackground(new Color(255, 255, 204));
                            vieworder_status.setForeground(new Color(255, 153, 0));
                            break;
                        case "Accepted":
                            status_background.setBackground(new Color(204, 255, 204));
                            vieworder_status.setForeground(new Color(0, 153, 0));
                            break;
                        default:
                            status_background.setBackground(new Color(255, 204, 204));
                            vieworder_status.setForeground(new Color(204, 0, 0));
                            break;
                    }

                    vieworder_orderID.setText("#" + rsOrder.getString("order_id"));
                    buyer_id = rsOrder.getInt("buyer_id");
                    transaction_id = rsOrder.getInt("order_id");
                    product_id = rsOrder.getInt("product_id");
                    getStock();
                    displayOrdersTable();
                    tabs.setSelectedIndex(9);
                } else {
                    JOptionPane.showMessageDialog(null, "Order details not found!");
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
        }
    }//GEN-LAST:event_view_orderActionPerformed

    private void product_table_delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_delete_buttonActionPerformed
        int rowIndex = product_table.getSelectedRow(); // Get the selected row index
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a product first");
        } else {
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                int pid = (int) product_table.getValueAt(rowIndex, 0);
                int sold1 = (int) product_table.getValueAt(rowIndex, 5);
                if (sold1 > 0) {
                    JOptionPane.showMessageDialog(null, "You can't delete this product. Please contact the administrator!");
                    return;
                }
                try {
                    databaseConnector dbc = new databaseConnector();
                    dbc.deleteProduct(pid);
                    displayProducts();
                    displayArchive();
                    display_best_selling_dashboard(); //display best selling
                    JOptionPane.showMessageDialog(null, "Product deleted successfully!");

                    // logs
                    String details = "User " + sellerID + " successfully deleted the product " + pid + "!";
                    String action = "Delete product";
                    actionLogs.recordSellerLogs(sellerID, action, details);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_product_table_delete_buttonActionPerformed

    private void addNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addNameFocusGained
        if (addName.getText().equals("PlayStation 5")) {
            addName.setText("");
            addName.setForeground(Color.decode("#333333"));
        }
    }//GEN-LAST:event_addNameFocusGained

    private void addPriceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addPriceFocusGained
        if (addPrice.getText().equals("₱")) {
            addPrice.setText("");
            addPrice.setForeground(Color.decode("#333333"));
        }
    }//GEN-LAST:event_addPriceFocusGained

    private void addStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addStockFocusGained
        if (addStock.getText().equals("0")) {
            addStock.setText("");
            addStock.setForeground(Color.decode("#333333"));
        }
    }//GEN-LAST:event_addStockFocusGained

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
        defaultValues();
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel99MouseClicked

    private void jLabel101MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseClicked
        emptyValues();
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel101MouseClicked

    private void orders_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orders_search_barKeyReleased
        search.searchResult(purchase_table, orders_search_bar);
    }//GEN-LAST:event_orders_search_barKeyReleased

    private void actionlogs_tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_actionlogs_tableKeyReleased
        search.searchResult(actionlogs_table, activity_search_bar);
    }//GEN-LAST:event_actionlogs_tableKeyReleased

    private void archive_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archive_search_barMouseClicked
        archive_search_bar.setFocusable(true);
        archive_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_archive_search_barMouseClicked

    private void archive_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_archive_search_barKeyReleased
        search.searchResult(archive_table, archive_search_bar);
    }//GEN-LAST:event_archive_search_barKeyReleased

    private void shop_lnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_lnameMouseClicked
        shop_lname.setFocusable(true);
        shop_fname.requestFocusInWindow();
    }//GEN-LAST:event_shop_lnameMouseClicked

    String newPassword;
    private void change_passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_change_passwordMouseClicked
        String oldPassword = JOptionPane.showInputDialog(null, "Enter your old password:");

        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your old password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isExist = isAccountExist.checkPassword(oldPassword, sellerID);

        if (!isExist) {
            JOptionPane.showMessageDialog(null, "Your old password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        newPassword = JOptionPane.showInputDialog(null, "Enter your new password:");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a new password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newPassword.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            // Update the password in the database
            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String updateQuery = "UPDATE tbl_accounts SET password = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            int rowsAffected;
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery)) {
                pst.setString(1, hashedNewPassword);
                pst.setInt(2, sellerID);
                rowsAffected = pst.executeUpdate();
            }

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully.");
                //logs
                String details = "User " + sellerID + " Successfully changed the password!";
                String action = "Change Password";
                actionLogs.recordSellerLogs(sellerID, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
        }
    }//GEN-LAST:event_change_passwordMouseClicked

    private void admin_supportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_supportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_supportMouseClicked

    private void admin_supportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_supportActionPerformed
        displayMessage4Admin();
        tabs.setSelectedIndex(4);
        dashboard.setSelected(false);
        manage.setSelected(false);
        orders.setSelected(false);
        archiveBtn.setSelected(false);
    }//GEN-LAST:event_admin_supportActionPerformed

    private void message4admin_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_message4admin_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_message4admin_tableMouseClicked

    private void message_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_message_search_barMouseClicked
        message_search_bar.setFocusable(true);
        message_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_message_search_barMouseClicked

    private void message_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_message_search_barKeyReleased
        search.searchResult(message4admin_table, message_search_bar);
    }//GEN-LAST:event_message_search_barKeyReleased

    private void jLabel108MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseClicked
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel108MouseClicked

    private void jLabel104MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel104MouseClicked
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_jLabel104MouseClicked

    private void orders_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orders_search_barMouseClicked
        orders_search_bar.setFocusable(true);
        orders_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_orders_search_barMouseClicked

    private void getNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getNameMouseClicked
        getName.setFocusable(true);
        getName.requestFocusInWindow();
    }//GEN-LAST:event_getNameMouseClicked

    private void getPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getPriceMouseClicked
        getPrice.setFocusable(true);
        getPrice.requestFocusInWindow();
    }//GEN-LAST:event_getPriceMouseClicked

    private void getStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getStockMouseClicked
        getStock.setFocusable(true);
        getStock.requestFocusInWindow();
    }//GEN-LAST:event_getStockMouseClicked

    private void getStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getStatusMouseClicked
        getStatus.setFocusable(true);
        getStatus.requestFocusInWindow();
    }//GEN-LAST:event_getStatusMouseClicked

    private void getCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getCategoryMouseClicked
        getCategory.setFocusable(true);
        getCategory.requestFocusInWindow();
    }//GEN-LAST:event_getCategoryMouseClicked

    private void getDescriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getDescriptionMouseClicked
        getDescription.setFocusable(true);
        getDescription.requestFocusInWindow();
    }//GEN-LAST:event_getDescriptionMouseClicked

    private void jLabel112MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel112MouseClicked
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel112MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tabs.setSelectedIndex(11);
        dashboard.setSelected(false);
        manage.setSelected(false);
        orders.setSelected(false);
        archiveBtn.setSelected(false);
        admin_support.setSelected(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void wishlist_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishlist_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_wishlist_tableMouseClicked

    private void wishlist_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishlist_search_barMouseClicked
        wishlist_search_bar.setFocusable(true);
        wishlist_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_wishlist_search_barMouseClicked

    private void wishlist_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wishlist_search_barKeyReleased
        search.searchResult(wishlist_table, wishlist_search_bar);
    }//GEN-LAST:event_wishlist_search_barKeyReleased

    private void help_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_help_statusActionPerformed
        displayMessage4Admin();
        tabs.setSelectedIndex(10);
    }//GEN-LAST:event_help_statusActionPerformed

    private void a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a3ActionPerformed
        a1.setSelected(false);
        a2.setSelected(false);
        a4.setSelected(false);
        header.setText("");
        explain.setText("");
        header.setText("Request feature");
        explain.setText("Please briefly describe the feature you are requesting and provide a detailed explanation.\n"
                + "Include the use case and how this feature would benefit you or improve the system.");
    }//GEN-LAST:event_a3ActionPerformed

    private void a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a2ActionPerformed
        a1.setSelected(false);
        a4.setSelected(false);
        a3.setSelected(false);
        header.setText("");
        explain.setText("");
        header.setText("Delete a product");
        explain.setText("Please briefly describe the product you want to delete and provide a detailed explanation of the reason for deletion.\n"
                + "Include any relevant product details or issues.");
    }//GEN-LAST:event_a2ActionPerformed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        a4.setSelected(false);
        a2.setSelected(false);
        a3.setSelected(false);
        header.setText("");
        explain.setText("");
        header.setText("Delete a product");
        explain.setText("Please briefly explain why you want to deactivate your account and provide a detailed explanation.\n"
                + "Include any feedback on your experience and suggestions for improvement.");
    }//GEN-LAST:event_a1ActionPerformed

    private void a4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a4ActionPerformed
        a1.setSelected(false);
        a2.setSelected(false);
        a3.setSelected(false);
        header.setText("");
        explain.setText("");
        header.setText("Report a issue");
        explain.setText("Please briefly explain the issue and provide a detailed description.\n"
                + "Include steps to reproduce the bug, expected result, and actual result.");
    }//GEN-LAST:event_a4ActionPerformed

    private void admin_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_submitActionPerformed
        String var_title = title.getText();
        String var_message = message.getText();
        String var_category = header.getText();

        if (var_title.isEmpty() || var_message.isEmpty() || var_category.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Please fill in all fields!");
            return;
        }
        if (var_message.length() > 1999) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Message must not exceed 21 characters!");
            return;
        }
        databaseConnector dbc = new databaseConnector();
        try {
            PreparedStatement pst;
            String sql = "INSERT INTO `tbl_message4admin` (`seller_id`, `message_category`, `message_title`, `message_description`, `date_sent`) VALUES (?, ?, ?, ?, NOW())";
            pst = dbc.getConnection().prepareStatement(sql);
            pst.setInt(1, sellerID);
            pst.setString(2, var_category);
            pst.setString(3, var_title);
            pst.setString(4, var_message);
            pst.executeUpdate();
            pst.close();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Message sent successfully");
            displayMessage4Admin();
            title.setText("");
            message.setText("");
            header.setText("");
            explain.setText("");
            tabs.setSelectedIndex(10);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while sending the message.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_admin_submitActionPerformed

    private void filter_product_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_product_tableActionPerformed
        sorter.searchResult(product_table, filter_product_table);
    }//GEN-LAST:event_filter_product_tableActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        sorter.searchResult(purchase_table, jComboBox5);
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        sorter.searchResult(actionlogs_table, jComboBox4);
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void filter_product_table1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_product_table1ActionPerformed
        sorter.searchResult(message4admin_table, filter_product_table1);
    }//GEN-LAST:event_filter_product_table1ActionPerformed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel41MouseClicked

    private void filter_product_table2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_product_table2ActionPerformed
        sorter.searchResult(wishlist_table, filter_product_table2);
    }//GEN-LAST:event_filter_product_table2ActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> {
                new sellerDashboard().setVisible(true);
            });

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(sellerDashboard.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CONTAINER;
    private javax.swing.JPanel CONTAINER1;
    private javax.swing.JPanel CONTAINER2;
    private javax.swing.JPanel CONTAINER3;
    private javax.swing.JPanel CONTAINER5;
    private javax.swing.JToggleButton a1;
    private javax.swing.JToggleButton a2;
    private javax.swing.JToggleButton a3;
    private javax.swing.JToggleButton a4;
    private javax.swing.JButton accept_order;
    private javax.swing.JTable actionlogs_table;
    private javax.swing.JTextField activity_search_bar;
    private javax.swing.JButton add1;
    private javax.swing.JButton add2;
    private javax.swing.JButton add3;
    private javax.swing.JButton add4;
    private javax.swing.JButton add5;
    private javax.swing.JComboBox<String> addCategory;
    private javax.swing.JPanel addContainer;
    private javax.swing.JPanel addContainer1;
    private javax.swing.JPanel addContainer4;
    private javax.swing.JPanel addContainer5;
    private javax.swing.JPanel addContainer6;
    private javax.swing.JPanel addContainer7;
    private javax.swing.JEditorPane addDescription;
    private javax.swing.JTextField addName;
    private javax.swing.JLabel addPhoto;
    private javax.swing.JTextField addPrice;
    private javax.swing.JButton addRemove;
    private javax.swing.JButton addReplace;
    private javax.swing.JTextField addStock;
    private javax.swing.JComboBox<String> add_category;
    private javax.swing.JButton add_product_save_button;
    private javax.swing.JButton admin_submit;
    private javax.swing.JToggleButton admin_support;
    private javax.swing.JPanel archiveAccountTableContainer;
    private javax.swing.JScrollPane archiveAccountTableContainerScroll;
    private javax.swing.JToggleButton archiveBtn;
    private javax.swing.JLabel archive_is_empty;
    private javax.swing.JTextField archive_search_bar;
    private javax.swing.JTable archive_table;
    private javax.swing.JLabel bacgkround;
    private javax.swing.JLabel bacgkround1;
    private javax.swing.JTextField c1;
    private javax.swing.JTextField c10;
    private javax.swing.JTextField c11;
    private javax.swing.JTextField c2;
    private javax.swing.JTextField c3;
    private javax.swing.JTextField c4;
    private javax.swing.JTextField c5;
    private javax.swing.JPanel c6;
    private javax.swing.JPanel c7;
    private javax.swing.JPanel c8;
    private javax.swing.JPanel c9;
    private javax.swing.JLabel change_password;
    private javax.swing.JToggleButton dashboard;
    private javax.swing.JPanel dashboardContainer;
    private javax.swing.JButton deactivate;
    private javax.swing.JPanel deactivate_jpanel;
    private javax.swing.JButton decline;
    private javax.swing.JButton delete;
    private javax.swing.JLabel desError;
    private javax.swing.JLabel desError2;
    private javax.swing.JLabel desError3;
    private javax.swing.JLabel desError5;
    private javax.swing.JLabel desError6;
    private javax.swing.JLabel desError7;
    private javax.swing.JEditorPane descript;
    private javax.swing.JLabel display_photo;
    private javax.swing.JLabel editNameError;
    private javax.swing.JLabel editPriceError;
    private javax.swing.JLabel editStockError;
    private javax.swing.JButton edit_product_save_button;
    private javax.swing.JButton edit_profile;
    private javax.swing.JButton edit_seller_close_button;
    private javax.swing.JButton edit_seller_save_button;
    private javax.swing.JButton edit_seller_upload_button;
    private javax.swing.JLabel errorImage;
    private javax.swing.JTextArea explain;
    private javax.swing.JPanel filterContainer;
    private javax.swing.JPanel filterContainer1;
    private javax.swing.JPanel filterContainer2;
    private javax.swing.JPanel filterContainer3;
    private javax.swing.JPanel filterContainer4;
    private javax.swing.JComboBox<String> filter_product_table;
    private javax.swing.JComboBox<String> filter_product_table1;
    private javax.swing.JComboBox<String> filter_product_table2;
    private javax.swing.JComboBox<String> getCategory;
    private javax.swing.JEditorPane getDescription;
    private javax.swing.JTextField getName;
    private javax.swing.JLabel getPhoto;
    private javax.swing.JTextField getPrice;
    private javax.swing.JComboBox<String> getStatus;
    private javax.swing.JTextField getStock;
    private javax.swing.JLabel header;
    private javax.swing.JLabel helloSeller;
    private javax.swing.JButton help_status;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel l1;
    private javax.swing.JScrollPane l3;
    private javax.swing.JButton logout;
    private javax.swing.JToggleButton manage;
    private javax.swing.JLabel manage18;
    private javax.swing.JLabel manage24;
    private javax.swing.JLabel manage25;
    private javax.swing.JTextArea message;
    private javax.swing.JTable message4admin_table;
    private javax.swing.JPanel messageContainer;
    private javax.swing.JLabel message_is_empty;
    private javax.swing.JTextField message_search_bar;
    private javax.swing.JPanel messages_container;
    private javax.swing.JTable messages_table;
    private javax.swing.JLabel nameError;
    private javax.swing.JToggleButton orders;
    private javax.swing.JLabel orders_is_empty;
    private javax.swing.JTextField orders_search_bar;
    private javax.swing.JTable orders_table;
    private javax.swing.JLabel overviewTotalLoss1;
    private javax.swing.JLabel overviewTotalLoss2;
    private javax.swing.JLabel overviewTotalLoss3;
    private javax.swing.JLabel overviewTotalLoss4;
    private javax.swing.JLabel overviewTotalSales;
    private javax.swing.JLabel pendingOrders;
    private javax.swing.JLabel priceError;
    private javax.swing.JLabel productID;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPhoto;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productQuantity;
    private javax.swing.JLabel productStatus;
    private javax.swing.JLabel product_is_empty;
    private javax.swing.JTextField product_search_bar;
    private javax.swing.JTable product_table;
    private javax.swing.JButton product_table_add_button;
    private javax.swing.JButton product_table_archive_button;
    private javax.swing.JButton product_table_delete_button;
    private javax.swing.JButton product_table_edit_button;
    private javax.swing.JPanel productsContainer;
    private javax.swing.JPanel productsContainer1;
    private javax.swing.JPanel productsContainer2;
    private javax.swing.JPanel productsContainer3;
    private javax.swing.JLabel profile;
    private javax.swing.JTable purchase_table;
    private javax.swing.JButton removetbn;
    private javax.swing.JButton replacebtn;
    private javax.swing.JButton restore;
    private javax.swing.JPanel s;
    private javax.swing.JLabel seller_address;
    private javax.swing.JLabel seller_address1;
    private javax.swing.JLabel seller_address2;
    private javax.swing.JLabel seller_address3;
    private javax.swing.JLabel seller_email;
    private javax.swing.JLabel seller_email1;
    private javax.swing.JLabel seller_email2;
    private javax.swing.JLabel seller_email3;
    private javax.swing.JLabel seller_full_name;
    private javax.swing.JLabel seller_phone;
    private javax.swing.JLabel seller_phone1;
    private javax.swing.JLabel seller_phone2;
    private javax.swing.JLabel seller_phone3;
    private javax.swing.JLabel seller_rating;
    private javax.swing.JLabel seller_store;
    private javax.swing.JLabel seller_store1;
    private javax.swing.JLabel seller_store2;
    private javax.swing.JLabel seller_store3;
    private javax.swing.JLabel seller_total_products;
    private javax.swing.JPanel seperator;
    private javax.swing.JPanel seperator1;
    private javax.swing.JTextField shop_email;
    private javax.swing.JTextField shop_fname;
    private javax.swing.JTextField shop_lname;
    private javax.swing.JTextField shop_location;
    private javax.swing.JTextField shop_name;
    private javax.swing.JTextField shop_number;
    private javax.swing.JPasswordField shop_password;
    private javax.swing.JLabel shop_photo;
    private javax.swing.JTextField shop_username;
    private javax.swing.JButton status_background;
    private javax.swing.JLabel stockError;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField title;
    private javax.swing.JLabel todaysDate;
    private javax.swing.JLabel top1_favorites;
    private javax.swing.JLabel top1_name;
    private javax.swing.JLabel top1_order_name;
    private javax.swing.JLabel top1_order_photo;
    private javax.swing.JLabel top1_order_price;
    private javax.swing.JLabel top1_order_total;
    private javax.swing.JLabel top1_photo;
    private javax.swing.JLabel top1_product_name;
    private javax.swing.JLabel top1_product_photo;
    private javax.swing.JLabel top1_product_price;
    private javax.swing.JLabel top1_product_sold;
    private javax.swing.JLabel top2_favorites;
    private javax.swing.JLabel top2_name;
    private javax.swing.JLabel top2_order_name;
    private javax.swing.JLabel top2_order_photo;
    private javax.swing.JLabel top2_order_price;
    private javax.swing.JLabel top2_order_total;
    private javax.swing.JLabel top2_photo;
    private javax.swing.JLabel top2_product_name;
    private javax.swing.JLabel top2_product_photo;
    private javax.swing.JLabel top2_product_price;
    private javax.swing.JLabel top2_product_sold;
    private javax.swing.JLabel totalLoss;
    private javax.swing.JLabel totalOrders;
    private javax.swing.JLabel totalSales;
    private javax.swing.JLabel total_amount;
    private javax.swing.JLabel username;
    private javax.swing.JLabel username1;
    private javax.swing.JLabel username2;
    private javax.swing.JLabel username3;
    private javax.swing.JButton view_order;
    private javax.swing.JPanel vieworder_background;
    private javax.swing.JLabel vieworder_category;
    private javax.swing.JLabel vieworder_contact_email;
    private javax.swing.JLabel vieworder_contact_name;
    private javax.swing.JLabel vieworder_contact_number;
    private javax.swing.JPanel vieworder_container;
    private javax.swing.JPanel vieworder_container2;
    private javax.swing.JPanel vieworder_container3;
    private javax.swing.JPanel vieworder_container4;
    private javax.swing.JLabel vieworder_date;
    private javax.swing.JTextArea vieworder_notes;
    private javax.swing.JLabel vieworder_orderID;
    private javax.swing.JLabel vieworder_photo;
    private javax.swing.JLabel vieworder_product_name;
    private javax.swing.JLabel vieworder_shipping_location;
    private javax.swing.JLabel vieworder_shipping_name;
    private javax.swing.JLabel vieworder_shipping_number;
    private javax.swing.JLabel vieworder_status;
    private javax.swing.JLabel vieworder_total;
    private javax.swing.JPanel wishlist_container;
    private javax.swing.JLabel wishlist_is_empty;
    private javax.swing.JTextField wishlist_search_bar;
    private javax.swing.JTable wishlist_table;
    private javax.swing.JTextField z6;
    // End of variables declaration//GEN-END:variables
}
