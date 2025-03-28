import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class RegistrationForm {
    public static class RegistrationFormUI {
    private final JFrame frame;
    private final JTextField nameField;
    private final JTextField mobileField;
    private final JRadioButton male;
    private final JRadioButton female;
    private final JComboBox<String> day;
    private final JComboBox<String> month;
    private final JComboBox<String> year;
    private final JTextArea addressField;
    private final JCheckBox terms;
    private final JButton submit;
    private final JButton reset;
    private final JTextArea displayArea;
    
    public RegistrationFormUI() {
        frame = new JFrame("Registration Form"); 
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("Name"));
        nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Mobile"));
        mobileField = new JTextField();
        formPanel.add(mobileField);
        
        formPanel.add(new JLabel("Gender"));
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);
        formPanel.add(genderPanel);
        
        formPanel.add(new JLabel("DOB"));
        String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] years = {"2000", "2001", "2002", "2003", "2004"};
        day = new JComboBox<>(days);
        month = new JComboBox<>(months);
        year = new JComboBox<>(years);
        JPanel dobPanel = new JPanel();
        dobPanel.add(day);
        dobPanel.add(month);
        dobPanel.add(year);
        formPanel.add(dobPanel);
        
        formPanel.add(new JLabel("Address"));
        addressField = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(addressField));
        
        terms = new JCheckBox("Accept Terms And Conditions");
        formPanel.add(terms);
        
        submit = new JButton("Submit");
        reset = new JButton("Reset");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submit);
        buttonPanel.add(reset);
        formPanel.add(buttonPanel);
        
        frame.add(formPanel);
        
        displayArea = new JTextArea();
        frame.add(new JScrollPane(displayArea));
        
        submit.addActionListener(e -> submitForm());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void submitForm() {
        String name = nameField.getText();
        String mobile = mobileField.getText();
        String gender = male.isSelected() ? "Male" : "Female";
        String dob = day.getSelectedItem() + "-" + month.getSelectedItem() + "-" + year.getSelectedItem();
        String address = addressField.getText();
        
        if (!terms.isSelected()) {
            JOptionPane.showMessageDialog(frame, "You must accept the terms.");
            return;
        }
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "password");
             PreparedStatement pst = con.prepareStatement("INSERT INTO users (name, mobile, gender, dob, address) VALUES (?, ?, ?, ?, ?)");) {
            
            pst.setString(1, name);
            pst.setString(2, mobile);
            pst.setString(3, gender);
            pst.setString(4, dob);
            pst.setString(5, address);
            pst.executeUpdate();
            
            displayData(con);
            
        } catch (SQLException ex) {
        }
    }
    
    private void displayData(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        displayArea.setText("ID\tName\tMobile\tGender\tDOB\tAddress\n");
        while (rs.next()) {
            displayArea.append(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("mobile") + "\t" +
                    rs.getString("gender") + "\t" + rs.getString("dob") + "\t" + rs.getString("address") + "\n");
        }
    }

    /**
         * @param args
         * @throws Exception
         */
        public static void main(String[] args) throws Exception {
            System.out.println("Welcome to the Registration Form");
    
            @SuppressWarnings("unused")
            Statement sqlSt; // Create a statement object to execute SQL queries
            @SuppressWarnings("unused")
                String useSQL = "use registration"; // SQL command to select the database
    // SQL command to select the database
            @SuppressWarnings("unused")
                String output = "output";
            // Execute the SQL command to select the database
            // Output variable to store the result of the SQL query
    // SQL command to select the database
    }
    
}


@Override
public String toString() {
    return "RegistrationForm []";
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(RegistrationFormUI::new);
}

}
