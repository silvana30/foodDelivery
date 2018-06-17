/*
 * Created by JFormDesigner on Sat Mar 24 01:49:18 GMT+02:00 2018
 */

package view;

import java.beans.*;
import javax.swing.event.*;
import javax.swing.table.*;

import service.CartBLL;
import service.OrderBLL;
import service.ProductBLL;
import service.UserBLL;
import model.Cart;
import model.OrderP;
import model.Product;
import model.User;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Silvana Man
 */
public class View {




    public View() {
        initComponents();
    }

    DefaultTableModel model1 = new DefaultTableModel(new Object[][] {},
            new String[] {"Id", "Name", "Price", "Ingredients"});
    DefaultTableModel model2 = new DefaultTableModel(new Object[][] {},
            new String[] {"Id", "Username", "Password", "Phone","Address","Card number","Loyal"});

    DefaultTableModel model3 = new DefaultTableModel(new Object[][] {},
            new String[] {"Id Cart", "Id product", "cantity"});
    DefaultTableModel model4 = new DefaultTableModel(new Object[][] {},
            new String[] {"Id Order", "Date", "Address","Total Price","PaymentType"});
    private void log_inActionPerformed(ActionEvent e) {
        // TODO add your code here
        User user= UserBLL.findByUsernameAll(usernameF.getText());
        if(user.isAdmin()==true){
            adminW.setVisible(true);
            UserBLL.logare(usernameF.getText());


        }else{
            clientW.setVisible(true);
        UserBLL.logare(usernameF.getText());
        model4update();

            }

        frame.setVisible(false);


    }

    private void frame1WindowStateChanged(WindowEvent e) {
        // TODO add your code here
    }

    private void registerActionPerformed(ActionEvent e) {
        // TODO add your code here

        registerW.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: add custom component creation code here

    }



    private void pozaPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
        BufferedImage myPicture = null;

        try {
            myPicture = ImageIO.read(new File("D://proiecte java//foodDelivery//poza.jpg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        poza = new JLabel(new ImageIcon(myPicture));
    }

    private void registerCActionPerformed(ActionEvent e) {
        // TODO add your code here
        User user=new User(textField3.getText(),textField2.getText(),textField1.getText(),textField4.getText(),textField5.getText());
        UserBLL.insertClient(user);
        model2Update();
        JOptionPane.showMessageDialog(registerW,"You are now registered!");
    }

    private void logOutAdminActionPerformed(ActionEvent e) {
        // TODO add your code here
        UserBLL.delogare();
        adminW.setVisible(false);
        frame.setVisible(true);
    }

    private void logOutActionPerformed(ActionEvent e) {
        UserBLL.delogare();
        model3update();
        clientW.setVisible(false);
        frame.setVisible(true);
    }

    private void myAccountActionPerformed(ActionEvent e) {
        myAccountW.setVisible(true);
    }

    private void button5ActionPerformed(ActionEvent e) {
        myOrders.setVisible(true);
    }

    private void productsTPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here

        model1Update();
    }
    public void model1Update() {
        productsT.setModel(model1);
        produseTableclient.setModel(model1);

        for(int i=model1.getRowCount()-1;i>=0;i--){
            model1.removeRow(i);
        }
        ArrayList<Product> lista=ProductBLL.getProducts();
        Object[] row = new Object[0];
        for (Product p : lista) {
             row = new Object[]{"" + p.getIdProduct() + "", p.getName(), p.getPrice(), p.getIngredients()};
            model1.addRow(row);
            
        }

    }
    public void model2Update(){
        UsersT.setModel(model2);
        for(int i=model2.getRowCount()-1;i>=0;i--){
            model2.removeRow(i);
        }
        ArrayList<User> lista=UserBLL.getClients();
        Object[] row = new Object[0];
        for (User p : lista) {
            row = new Object[]{"" + p.getIdUser() + "", p.getUsername(), p.getPassword(),p.getPhone(), p.getAddress(),p.getCard_nr(),String.valueOf(p.isLoyal())};
            model2.addRow(row);

        }
    }

    public void model3update(){
        cartTable.setModel(model3);
        for(int i=model3.getRowCount()-1;i>=0;i--){
            model3.removeRow(i);
        }
       //ArrayList<Cart> lista=CartBLL.getCarts();
        ArrayList<Cart> lista=CartBLL.getMyCarts(OrderBLL.getMax());

        Object[] row;
        for(Cart c:lista){
            row=new Object[]{""+c.getIdCart()+"",c.getIdProduct(),c.getCantity()};
            model3.addRow(row);
        }

    }

    public void model4update(){
        myOrdersT.setModel(model4);
        for(int i=model4.getRowCount()-1;i>=0;i--){
            model4.removeRow(i);
        }

        ArrayList<OrderP> lista=OrderBLL.getMYorders(UserBLL.isLogged());
        System.out.println(lista.toString());
        Object[] row;
        for(OrderP c:lista){
            row=new Object[]{""+c.getIdOrder()+"",c.getDate(),c.getAddress(),c.getTotalPrice(),c.getPaymentType()};
            model4.addRow(row);
        }

    }

    private void scrollPane1AncestorAdded(AncestorEvent e) {
        // TODO add your code here
    }

    private void scrollPane2PropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void UsersTPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here

        model2Update();

    }

    private void updateProdActionPerformed(ActionEvent e) {
        Product prod=new Product(textField8.getText(),Double.parseDouble(textField9.getText()),textField10.getText());
        ProductBLL.update(prod);
        model1Update();
    }

    private void insertProductActionPerformed(ActionEvent e) {
        // TODO add your code here
        Product prod=new Product(textField8.getText(),Double.parseDouble(textField9.getText()),textField10.getText());
        ProductBLL.insert(prod);
        model1Update();
    }

    private void updateUserActionPerformed(ActionEvent e) {
        String username=textField11.getText();
        User user =new User(username,textField12.getText(),textField13.getText(),textField14.getText(),textField15.getText());
        UserBLL.update(user);
        model2Update();


    }

    private void makeLoyalActionPerformed(ActionEvent e) {
        UserBLL.makeLoyal(textField11.getText());
        JOptionPane.showMessageDialog(adminW,"Set successfully!");
        model2Update();
    }

    private void updateClientActionPerformed(ActionEvent e) {
        // TODO add your code here
        int id=UserBLL.isLogged();
        String username=UserBLL.isLoggedUser();
        User user =new User(username,textField18.getText(),textField19.getText(),textField21.getText(),textField22.getText());
        UserBLL.update(user);
        model2Update();
        JOptionPane.showMessageDialog(myAccountW,"Operation completed successfully!");
    }

    private void adminWPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here

        model1Update();
        model2Update();
    }

    private void button1ActionPerformed(ActionEvent e) {

        ProductBLL.deleteById(Integer.parseInt(textField7.getText()));
        model1Update();
    }

    private void addToCartActionPerformed(ActionEvent e) {
        // TODO add your code here

        int id=OrderBLL.getMax();
        CartBLL myCart=new CartBLL();
        myCart.insert(id,Integer.parseInt(idprod.getText()),Integer.parseInt(cantitate.getText()));
        model3update();

    }

    private void cartTablePropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
        model3update();
    }

    private void orderActionPerformed(ActionEvent e) {
        double totalPrice=0.0;
        double part,part2=0.0;
        int logat=UserBLL.isLogged();
        boolean loial=UserBLL.isLoyal(logat);
        ArrayList<Cart> mycarts=CartBLL.getMyCarts(OrderBLL.getMax());
        for(Cart c:mycarts){
            double price=ProductBLL.getPrice(c.getIdProduct());
            part=price*(double)c.getCantity();
            if(loial){
                part2+=part;
                totalPrice=part2-(0.05*part2);

            }else{
                totalPrice+=part;
            }

        }


        if(payment.getText().equalsIgnoreCase("card")){
            JOptionPane.showMessageDialog(clientW,"Confirm that you want to use your card.");
            OrderBLL.createOrder(totalPrice,payment.getText());
            model4update();
            JOptionPane.showMessageDialog(clientW,"Your order is now completed");

        }else if((payment.getText().equalsIgnoreCase("cash")) || (payment.getText().equalsIgnoreCase("ramburs"))){
            OrderBLL.createOrder(totalPrice,payment.getText());
            model4update();
            JOptionPane.showMessageDialog(clientW,"Your order is now completed");
        }
        else
        JOptionPane.showMessageDialog(clientW,"Make sure you specify payment type: card or cash");

    }

    private void button2ActionPerformed(ActionEvent e) {
        CartBLL.stergeCart(Integer.parseInt(idprod.getText()));
        model3update();
    }

    private void button3ActionPerformed(ActionEvent e) {
        CartBLL.updateCantity(Integer.parseInt(cantitate.getText()),Integer.parseInt(idprod.getText()));
        model3update();
    }

    private void myOrdersTPropertyChange(PropertyChangeEvent e) {
        model4update();
    }

    private void reportActionPerformed(ActionEvent e) {
        OrderBLL.generateReport(textField11.getText());
        JOptionPane.showMessageDialog(adminW,"Report generated! ");
    }








    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Silvana Man
        frame = new JFrame();
        label1 = new JLabel();
        usernameF = new JTextField();
        label2 = new JLabel();
        log_in = new JButton();
        register = new JButton();
         BufferedImage myPicture = null;

                try {
                    myPicture = ImageIO.read(new File("D://proiecte java//foodDelivery//poza.jpg"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


        poza = new JLabel();
        passwordField1 = new JPasswordField();
        registerW = new JFrame();
        label3 = new JLabel();
        label4 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        registerC = new JButton();
        adminW = new JFrame();
        scrollPane1 = new JScrollPane();
        productsT = new JTable();
        scrollPane2 = new JScrollPane();
        UsersT = new JTable();
        textField8 = new JTextField();
        textField9 = new JTextField();
        textField10 = new JTextField();
        updateProd = new JButton();
        textField11 = new JTextField();
        textField12 = new JTextField();
        textField13 = new JTextField();
        textField14 = new JTextField();
        textField15 = new JTextField();
        updateUser = new JButton();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        makeLoyal = new JButton();
        logOutAdmin = new JButton();
        insertProduct = new JButton();
        button1 = new JButton();
        label23 = new JLabel();
        textField7 = new JTextField();
        report = new JButton();
        clientW = new JFrame();
        myAccount = new JButton();
        logOut = new JButton();
        scrollPane3 = new JScrollPane();
        produseTableclient = new JTable();
        idprod = new JTextField();
        cantitate = new JTextField();
        addToCart = new JButton();
        scrollPane4 = new JScrollPane();
        cartTable = new JTable();
        order = new JButton();
        button5 = new JButton();
        payment = new JTextField();
        label24 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        label25 = new JLabel();
        label26 = new JLabel();
        myAccountW = new JFrame();
        textField18 = new JTextField();
        textField19 = new JTextField();
        textField21 = new JTextField();
        textField22 = new JTextField();
        updateClient = new JButton();
        label9 = new JLabel();
        label18 = new JLabel();
        label20 = new JLabel();
        label21 = new JLabel();
        myOrders = new JFrame();
        scrollPane5 = new JScrollPane();
        myOrdersT = new JTable();

        //======== frame ========
        {
            frame.setTitle("Welcome to AllUcanEat!");
            frame.setForeground(new Color(0, 51, 51));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Container frameContentPane = frame.getContentPane();
            frameContentPane.setLayout(null);

            //---- label1 ----
            label1.setText("Username:");
            frameContentPane.add(label1);
            label1.setBounds(90, 30, 170, label1.getPreferredSize().height);

            //---- usernameF ----
            usernameF.setBackground(SystemColor.control);
            usernameF.setForeground(Color.black);
            frameContentPane.add(usernameF);
            usernameF.setBounds(90, 60, 200, usernameF.getPreferredSize().height);

            //---- label2 ----
            label2.setText("Password:");
            frameContentPane.add(label2);
            label2.setBounds(90, 120, 120, label2.getPreferredSize().height);

            //---- log_in ----
            log_in.setText("LOG IN");
            log_in.setForeground(new Color(204, 204, 255));
            log_in.setBackground(Color.blue);
            log_in.addActionListener(e -> {
			log_inActionPerformed(e);
			log_inActionPerformed(e);
		});
            frameContentPane.add(log_in);
            log_in.setBounds(130, 270, 95, 51);

            //---- register ----
            register.setText("Register");
            register.setBackground(new Color(255, 153, 51));
            register.addActionListener(e -> registerActionPerformed(e));
            frameContentPane.add(register);
            register.setBounds(375, 335, 180, register.getPreferredSize().height);

            //---- poza ----
            poza = new JLabel(new ImageIcon(myPicture));
            poza.setText("text");
            frameContentPane.add(poza);
            poza.setBounds(310, 35, 270, 180);
            frameContentPane.add(passwordField1);
            passwordField1.setBounds(90, 165, 195, passwordField1.getPreferredSize().height);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < frameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = frameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = frameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                frameContentPane.setMinimumSize(preferredSize);
                frameContentPane.setPreferredSize(preferredSize);
            }
            frame.pack();
            frame.setLocationRelativeTo(frame.getOwner());
        }

        //======== registerW ========
        {
            registerW.setTitle("Register now!");
            Container registerWContentPane = registerW.getContentPane();
            registerWContentPane.setLayout(null);

            //---- label3 ----
            label3.setText("Username:");
            registerWContentPane.add(label3);
            label3.setBounds(30, 20, 90, label3.getPreferredSize().height);

            //---- label4 ----
            label4.setText("Password:");
            registerWContentPane.add(label4);
            label4.setBounds(30, 60, 80, label4.getPreferredSize().height);
            registerWContentPane.add(textField1);
            textField1.setBounds(185, 100, 130, textField1.getPreferredSize().height);
            registerWContentPane.add(textField2);
            textField2.setBounds(185, 55, 130, textField2.getPreferredSize().height);

            //---- label5 ----
            label5.setText("Phone:");
            registerWContentPane.add(label5);
            label5.setBounds(30, 100, 90, label5.getPreferredSize().height);

            //---- label6 ----
            label6.setText("Address:");
            registerWContentPane.add(label6);
            label6.setBounds(30, 140, 70, label6.getPreferredSize().height);

            //---- label7 ----
            label7.setText("Card number:");
            registerWContentPane.add(label7);
            label7.setBounds(30, 180, 100, 25);
            registerWContentPane.add(textField3);
            textField3.setBounds(185, 15, 130, textField3.getPreferredSize().height);
            registerWContentPane.add(textField4);
            textField4.setBounds(185, 140, 130, textField4.getPreferredSize().height);
            registerWContentPane.add(textField5);
            textField5.setBounds(185, 180, 130, textField5.getPreferredSize().height);

            //---- registerC ----
            registerC.setText("Register");
            registerC.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
            registerC.addActionListener(e -> registerCActionPerformed(e));
            registerWContentPane.add(registerC);
            registerC.setBounds(new Rectangle(new Point(130, 275), registerC.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < registerWContentPane.getComponentCount(); i++) {
                    Rectangle bounds = registerWContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = registerWContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                registerWContentPane.setMinimumSize(preferredSize);
                registerWContentPane.setPreferredSize(preferredSize);
            }
            registerW.pack();
            registerW.setLocationRelativeTo(registerW.getOwner());
        }

        //======== adminW ========
        {
            adminW.setTitle("Welcome, boss! ");
            adminW.addPropertyChangeListener(e -> adminWPropertyChange(e));
            Container adminWContentPane = adminW.getContentPane();
            adminWContentPane.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.addAncestorListener(new AncestorListener() {
                    @Override
                    public void ancestorAdded(AncestorEvent e) {
                        scrollPane1AncestorAdded(e);
                    }
                    @Override
                    public void ancestorMoved(AncestorEvent e) {}
                    @Override
                    public void ancestorRemoved(AncestorEvent e) {}
                });

                //---- productsT ----
                productsT.setCellSelectionEnabled(true);
                productsT.setBackground(new Color(204, 204, 255));
                productsT.setName("Products");
                productsT.addPropertyChangeListener(e -> productsTPropertyChange(e));
                scrollPane1.setViewportView(productsT);
            }
            adminWContentPane.add(scrollPane1);
            scrollPane1.setBounds(25, 15, 480, 225);

            //======== scrollPane2 ========
            {
                scrollPane2.addPropertyChangeListener(e -> scrollPane2PropertyChange(e));

                //---- UsersT ----
                UsersT.setName("UsersT");
                UsersT.setBackground(new Color(204, 204, 255));
                UsersT.addPropertyChangeListener(e -> UsersTPropertyChange(e));
                scrollPane2.setViewportView(UsersT);
            }
            adminWContentPane.add(scrollPane2);
            scrollPane2.setBounds(new Rectangle(new Point(25, 305), scrollPane2.getPreferredSize()));
            adminWContentPane.add(textField8);
            textField8.setBounds(680, 70, 95, textField8.getPreferredSize().height);
            adminWContentPane.add(textField9);
            textField9.setBounds(680, 115, 100, textField9.getPreferredSize().height);
            adminWContentPane.add(textField10);
            textField10.setBounds(680, 160, 95, textField10.getPreferredSize().height);

            //---- updateProd ----
            updateProd.setText("Update");
            updateProd.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            updateProd.addActionListener(e -> updateProdActionPerformed(e));
            adminWContentPane.add(updateProd);
            updateProd.setBounds(new Rectangle(new Point(540, 240), updateProd.getPreferredSize()));
            adminWContentPane.add(textField11);
            textField11.setBounds(705, 330, 120, textField11.getPreferredSize().height);
            adminWContentPane.add(textField12);
            textField12.setBounds(705, 385, 120, textField12.getPreferredSize().height);
            adminWContentPane.add(textField13);
            textField13.setBounds(705, 435, 120, textField13.getPreferredSize().height);
            adminWContentPane.add(textField14);
            textField14.setBounds(705, 485, 120, textField14.getPreferredSize().height);
            adminWContentPane.add(textField15);
            textField15.setBounds(705, 540, 120, textField15.getPreferredSize().height);

            //---- updateUser ----
            updateUser.setText("Update data");
            updateUser.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            updateUser.addActionListener(e -> updateUserActionPerformed(e));
            adminWContentPane.add(updateUser);
            updateUser.setBounds(new Rectangle(new Point(505, 640), updateUser.getPreferredSize()));

            //---- label10 ----
            label10.setText("Name");
            adminWContentPane.add(label10);
            label10.setBounds(new Rectangle(new Point(570, 70), label10.getPreferredSize()));

            //---- label11 ----
            label11.setText("Price");
            adminWContentPane.add(label11);
            label11.setBounds(new Rectangle(new Point(570, 120), label11.getPreferredSize()));

            //---- label12 ----
            label12.setText("Ingredients");
            adminWContentPane.add(label12);
            label12.setBounds(new Rectangle(new Point(570, 165), label12.getPreferredSize()));

            //---- label13 ----
            label13.setText("Username");
            adminWContentPane.add(label13);
            label13.setBounds(new Rectangle(new Point(565, 335), label13.getPreferredSize()));

            //---- label14 ----
            label14.setText("Password");
            adminWContentPane.add(label14);
            label14.setBounds(new Rectangle(new Point(565, 390), label14.getPreferredSize()));

            //---- label15 ----
            label15.setText("Phone");
            adminWContentPane.add(label15);
            label15.setBounds(new Rectangle(new Point(565, 435), label15.getPreferredSize()));

            //---- label16 ----
            label16.setText("Address");
            adminWContentPane.add(label16);
            label16.setBounds(new Rectangle(new Point(565, 485), label16.getPreferredSize()));

            //---- label17 ----
            label17.setText("Card");
            adminWContentPane.add(label17);
            label17.setBounds(new Rectangle(new Point(565, 545), label17.getPreferredSize()));

            //---- makeLoyal ----
            makeLoyal.setText("Make loyal");
            makeLoyal.setName("makeLoyal");
            makeLoyal.setBackground(UIManager.getColor("Button.darcula.color1"));
            makeLoyal.addActionListener(e -> makeLoyalActionPerformed(e));
            adminWContentPane.add(makeLoyal);
            makeLoyal.setBounds(new Rectangle(new Point(665, 640), makeLoyal.getPreferredSize()));

            //---- logOutAdmin ----
            logOutAdmin.setText("LOG OUT");
            logOutAdmin.setBackground(UIManager.getColor("Button.shadow"));
            logOutAdmin.setFont(new Font("Segoe UI", Font.BOLD, 15));
            logOutAdmin.addActionListener(e -> logOutAdminActionPerformed(e));
            adminWContentPane.add(logOutAdmin);
            logOutAdmin.setBounds(new Rectangle(new Point(605, 700), logOutAdmin.getPreferredSize()));

            //---- insertProduct ----
            insertProduct.setText("Insert");
            insertProduct.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            insertProduct.addActionListener(e -> insertProductActionPerformed(e));
            adminWContentPane.add(insertProduct);
            insertProduct.setBounds(645, 240, 90, insertProduct.getPreferredSize().height);

            //---- button1 ----
            button1.setText("Delete");
            button1.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            button1.addActionListener(e -> button1ActionPerformed(e));
            adminWContentPane.add(button1);
            button1.setBounds(new Rectangle(new Point(760, 240), button1.getPreferredSize()));

            //---- label23 ----
            label23.setText("ID");
            adminWContentPane.add(label23);
            label23.setBounds(570, 25, 60, 26);
            adminWContentPane.add(textField7);
            textField7.setBounds(680, 25, 95, textField7.getPreferredSize().height);

            //---- report ----
            report.setText("Report");
            report.addActionListener(e -> reportActionPerformed(e));
            adminWContentPane.add(report);
            report.setBounds(815, 640, 100, report.getPreferredSize().height);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < adminWContentPane.getComponentCount(); i++) {
                    Rectangle bounds = adminWContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = adminWContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                adminWContentPane.setMinimumSize(preferredSize);
                adminWContentPane.setPreferredSize(preferredSize);
            }
            adminW.pack();
            adminW.setLocationRelativeTo(adminW.getOwner());
        }

        //======== clientW ========
        {
            clientW.setTitle("Welcome, user!");
            Container clientWContentPane = clientW.getContentPane();
            clientWContentPane.setLayout(null);

            //---- myAccount ----
            myAccount.setText("My Account");
            myAccount.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            myAccount.addActionListener(e -> myAccountActionPerformed(e));
            clientWContentPane.add(myAccount);
            myAccount.setBounds(new Rectangle(new Point(30, 15), myAccount.getPreferredSize()));

            //---- logOut ----
            logOut.setText("Log out");
            logOut.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            logOut.setFont(new Font("Segoe UI", Font.BOLD, 15));
            logOut.addActionListener(e -> logOutActionPerformed(e));
            clientWContentPane.add(logOut);
            logOut.setBounds(new Rectangle(new Point(230, 15), logOut.getPreferredSize()));

            //======== scrollPane3 ========
            {

                //---- produseTableclient ----
                produseTableclient.setFont(new Font("Calibri", Font.PLAIN, 15));
                scrollPane3.setViewportView(produseTableclient);
            }
            clientWContentPane.add(scrollPane3);
            scrollPane3.setBounds(30, 85, 565, 215);
            clientWContentPane.add(idprod);
            idprod.setBounds(825, 95, 95, idprod.getPreferredSize().height);
            clientWContentPane.add(cantitate);
            cantitate.setBounds(825, 150, 100, cantitate.getPreferredSize().height);

            //---- addToCart ----
            addToCart.setText("add to cart");
            addToCart.setBackground(UIManager.getColor("Button.darcula.selection.color1"));
            addToCart.addActionListener(e -> addToCartActionPerformed(e));
            clientWContentPane.add(addToCart);
            addToCart.setBounds(690, 215, 145, 50);

            //======== scrollPane4 ========
            {

                //---- cartTable ----
                cartTable.setFont(new Font("Calibri", Font.PLAIN, 15));
                cartTable.addPropertyChangeListener(e -> cartTablePropertyChange(e));
                scrollPane4.setViewportView(cartTable);
            }
            clientWContentPane.add(scrollPane4);
            scrollPane4.setBounds(25, 340, 500, 230);

            //---- order ----
            order.setText("Order");
            order.setBackground(UIManager.getColor("Button.light"));
            order.setFont(new Font("Segoe UI", Font.BOLD, 18));
            order.addActionListener(e -> orderActionPerformed(e));
            clientWContentPane.add(order);
            order.setBounds(755, 480, 170, 75);

            //---- button5 ----
            button5.setText("My Orders");
            button5.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            button5.addActionListener(e -> button5ActionPerformed(e));
            clientWContentPane.add(button5);
            button5.setBounds(new Rectangle(new Point(430, 15), button5.getPreferredSize()));
            clientWContentPane.add(payment);
            payment.setBounds(575, 510, 110, payment.getPreferredSize().height);

            //---- label24 ----
            label24.setText("Payment type:");
            clientWContentPane.add(label24);
            label24.setBounds(new Rectangle(new Point(575, 460), label24.getPreferredSize()));

            //---- button2 ----
            button2.setText("Remove product");
            button2.setBackground(UIManager.getColor("Button.darcula.selection.color2"));
            button2.addActionListener(e -> button2ActionPerformed(e));
            clientWContentPane.add(button2);
            button2.setBounds(new Rectangle(new Point(690, 285), button2.getPreferredSize()));

            //---- button3 ----
            button3.setText("Update");
            button3.setBackground(UIManager.getColor("Button.darcula.selectedButtonForeground"));
            button3.addActionListener(e -> button3ActionPerformed(e));
            clientWContentPane.add(button3);
            button3.setBounds(690, 355, 150, button3.getPreferredSize().height);

            //---- label25 ----
            label25.setText("ID product or cart");
            clientWContentPane.add(label25);
            label25.setBounds(new Rectangle(new Point(660, 100), label25.getPreferredSize()));

            //---- label26 ----
            label26.setText("Cantity");
            clientWContentPane.add(label26);
            label26.setBounds(660, 150, 60, label26.getPreferredSize().height);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < clientWContentPane.getComponentCount(); i++) {
                    Rectangle bounds = clientWContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = clientWContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                clientWContentPane.setMinimumSize(preferredSize);
                clientWContentPane.setPreferredSize(preferredSize);
            }
            clientW.pack();
            clientW.setLocationRelativeTo(clientW.getOwner());
        }

        //======== myAccountW ========
        {
            myAccountW.setTitle("My Account");
            Container myAccountWContentPane = myAccountW.getContentPane();
            myAccountWContentPane.setLayout(null);
            myAccountWContentPane.add(textField18);
            textField18.setBounds(145, 25, 140, textField18.getPreferredSize().height);
            myAccountWContentPane.add(textField19);
            textField19.setBounds(145, 75, 140, textField19.getPreferredSize().height);
            myAccountWContentPane.add(textField21);
            textField21.setBounds(145, 125, 140, textField21.getPreferredSize().height);
            myAccountWContentPane.add(textField22);
            textField22.setBounds(145, 180, 140, textField22.getPreferredSize().height);

            //---- updateClient ----
            updateClient.setText("update");
            updateClient.setBackground(new Color(255, 204, 204));
            updateClient.addActionListener(e -> updateClientActionPerformed(e));
            myAccountWContentPane.add(updateClient);
            updateClient.setBounds(130, 255, 105, 51);

            //---- label9 ----
            label9.setText("New password:");
            myAccountWContentPane.add(label9);
            label9.setBounds(30, 30, 140, label9.getPreferredSize().height);

            //---- label18 ----
            label18.setText("Phone:");
            myAccountWContentPane.add(label18);
            label18.setBounds(30, 80, 60, label18.getPreferredSize().height);

            //---- label20 ----
            label20.setText("Address:");
            myAccountWContentPane.add(label20);
            label20.setBounds(new Rectangle(new Point(30, 130), label20.getPreferredSize()));

            //---- label21 ----
            label21.setText("Card number:");
            myAccountWContentPane.add(label21);
            label21.setBounds(new Rectangle(new Point(30, 185), label21.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < myAccountWContentPane.getComponentCount(); i++) {
                    Rectangle bounds = myAccountWContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = myAccountWContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                myAccountWContentPane.setMinimumSize(preferredSize);
                myAccountWContentPane.setPreferredSize(preferredSize);
            }
            myAccountW.pack();
            myAccountW.setLocationRelativeTo(myAccountW.getOwner());
        }

        //======== myOrders ========
        {
            myOrders.setTitle("My orders");
            Container myOrdersContentPane = myOrders.getContentPane();
            myOrdersContentPane.setLayout(null);

            //======== scrollPane5 ========
            {

                //---- myOrdersT ----
                myOrdersT.addPropertyChangeListener(e -> myOrdersTPropertyChange(e));
                scrollPane5.setViewportView(myOrdersT);
            }
            myOrdersContentPane.add(scrollPane5);
            scrollPane5.setBounds(35, 20, 680, 400);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < myOrdersContentPane.getComponentCount(); i++) {
                    Rectangle bounds = myOrdersContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = myOrdersContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                myOrdersContentPane.setMinimumSize(preferredSize);
                myOrdersContentPane.setPreferredSize(preferredSize);
            }
            myOrders.pack();
            myOrders.setLocationRelativeTo(myOrders.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Silvana Man
    public JFrame frame;
    private JLabel label1;
    private JTextField usernameF;
    private JLabel label2;
    private JButton log_in;
    private JButton register;
    public JLabel poza;
    private JPasswordField passwordField1;
    public JFrame registerW;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton registerC;
    public JFrame adminW;
    private JScrollPane scrollPane1;
    private JTable productsT;
    private JScrollPane scrollPane2;
    private JTable UsersT;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JButton updateProd;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JButton updateUser;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JButton makeLoyal;
    private JButton logOutAdmin;
    private JButton insertProduct;
    private JButton button1;
    private JLabel label23;
    private JTextField textField7;
    private JButton report;
    private JFrame clientW;
    private JButton myAccount;
    private JButton logOut;
    private JScrollPane scrollPane3;
    private JTable produseTableclient;
    private JTextField idprod;
    private JTextField cantitate;
    private JButton addToCart;
    private JScrollPane scrollPane4;
    private JTable cartTable;
    private JButton order;
    private JButton button5;
    private JTextField payment;
    private JLabel label24;
    private JButton button2;
    private JButton button3;
    private JLabel label25;
    private JLabel label26;
    private JFrame myAccountW;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField21;
    private JTextField textField22;
    private JButton updateClient;
    private JLabel label9;
    private JLabel label18;
    private JLabel label20;
    private JLabel label21;
    private JFrame myOrders;
    private JScrollPane scrollPane5;
    private JTable myOrdersT;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
