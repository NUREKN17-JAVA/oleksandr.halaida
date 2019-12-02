package ua.nure.cs.galayda.usermanagement.gui;

import ua.nure.cs.galayda.usermanagement.db.DatabaseException;
import ua.nure.cs.galayda.usermanagement.entity.User;
import ua.nure.cs.galayda.usermanagement.gui.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class AddPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = -1447962623166083695L;

    private static final String ADD_PANEL_COMMAND_NAME = "addPanel";
    private static final String CANCEL_BUTTON_COMMAND_NAME = "cancelButton";
    private static final String OK_BUTTON_COMMAND_NAME = "okButton";
    private static final String FIRST_NAME_FIELD = "firstNameField";
    private static final String LAST_NAME_FIELD = "lastNameField";
    private static final String DATE_OF_BIRTH_FIELD = "dateOfBirthField";

    protected MainFrame parent;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private JButton cancelButton;
    private JButton okButton;
    private JTextField firstNameField;
    private JTextField dateOfBirthField;
    private JTextField lastNameField;
    private Color bgColor;

    public AddPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        this.setName(ADD_PANEL_COMMAND_NAME);
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }
        return buttonPanel;
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setText(Messages.getString("AddPanel.cancel"));
            cancelButton.setName(CANCEL_BUTTON_COMMAND_NAME);
            cancelButton.setActionCommand("cancel");
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText(Messages.getString("AddPanel.ok"));
            okButton.setName(OK_BUTTON_COMMAND_NAME);
            okButton.setActionCommand("ok");
            okButton.addActionListener(this);
        }
        return okButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabeledField(fieldPanel,
                    Messages.getString("AddPanel.first_name"),
                    getFirstNameField());
            addLabeledField(fieldPanel,
                    Messages.getString("AddPanel.last_name"),
                    getLastNameField());
            addLabeledField(fieldPanel,
                    Messages.getString("AddPanel.date_of_birth"),
                    getDateOfBirthField());
        }
        return fieldPanel;
    }

    protected JTextField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName(DATE_OF_BIRTH_FIELD);
        }
        return dateOfBirthField;
    }

    protected JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName(LAST_NAME_FIELD);
        }
        return lastNameField;
    }

    private void addLabeledField(JPanel panel, String labelText,
            JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    protected JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName(FIRST_NAME_FIELD);
        }
        return firstNameField;
    }

    protected void doAction(ActionEvent e) throws ParseException {
        if ("ok".equalsIgnoreCase(e.getActionCommand())) {
            User user = new User();
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            DateFormat format = DateFormat.getDateInstance();
            try {
                Date date = format.parse(getDateOfBirthField().getText());
                user.setDateOfBirth(date);
            } catch (ParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                return;
            }
            try {
                parent.getUserDao().create(user);
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            doAction(e);
        } catch (ParseException e1) {
            return;
        }
        clearFields();
        this.setVisible(false);
        parent.showBrowsePanel();

    }

    private void clearFields() {
        getFirstNameField().setText("");
        getFirstNameField().setBackground(bgColor);

        getLastNameField().setText("");
        getLastNameField().setBackground(bgColor);

        getDateOfBirthField().setText("");
        getDateOfBirthField().setBackground(bgColor);
    }
}
