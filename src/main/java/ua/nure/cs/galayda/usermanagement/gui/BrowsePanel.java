package ua.nure.cs.galayda.usermanagement.gui;


import ua.nure.cs.galayda.usermanagement.db.DatabaseException;
import ua.nure.cs.galayda.usermanagement.entity.User;
import ua.nure.cs.galayda.usermanagement.gui.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowsePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = -4548910292462347062L;

    private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
    private static final String USER_TABLE_COMPONENT_NAME = "userTable";
    private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
    private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
    private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
    private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
    private static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit";
    private static final String DELETE_COMMAND = "delete";
    private static final String DETAILS_COMMAND = "details";

    private MainFrame parent;

    private JPanel buttonPanel;

    private JButton addButton;

    private JButton detailsButton;

    private JButton deleteButton;

    private JButton editButton;

    private JScrollPane tablePanel;

    private JTable userTable;

    public BrowsePanel(MainFrame mainFrame) {
        parent = mainFrame;
        initialize();
    }

    private void initialize() {
        this.setName(BROWSE_PANEL_COMPONENT_NAME);
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(), BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    private JScrollPane getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JButton getAddButton() {
        if (addButton == null) {
            addButton = new JButton();
            addButton.setText(Messages.getString("BrowsePanel.add"));
            addButton.setName(ADD_BUTTON_COMPONENT_NAME);
            addButton.setActionCommand(ADD_COMMAND);
            addButton.addActionListener(this);
        }
        return addButton;
    }

    private JPanel getButtonsPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getAddButton(), null);
            buttonPanel.add(getEditButton(), null);
            buttonPanel.add(getDeleteButton(), null);
            buttonPanel.add(getDetailsButton(), null);
        }
        return buttonPanel;
    }

    private JButton getEditButton() {
        if (editButton == null) {
            editButton = new JButton();
            editButton.setText(Messages.getString("BrowsePanel.edit"));
            editButton.setName(EDIT_BUTTON_COMPONENT_NAME);
            editButton.setActionCommand(EDIT_COMMAND);
            editButton.addActionListener(this);
        }
        return editButton;
    }

    private JButton getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new JButton();
            deleteButton.setText(Messages.getString("BrowsePanel.delete"));
            deleteButton.setName(DELETE_BUTTON_COMPONENT_NAME);
            deleteButton.setActionCommand(DELETE_COMMAND);
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    private JButton getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = new JButton();
            detailsButton.setText(Messages.getString("BrowsePanel.details"));
            detailsButton.setName(DETAILS_BUTTON_COMPONENT_NAME);
            detailsButton.setActionCommand(DETAILS_COMMAND);
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    private JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName(USER_TABLE_COMPONENT_NAME);
        }
        return userTable;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("add".equalsIgnoreCase(actionCommand)) {
            this.setVisible(false);
            parent.showAddPanel();
        } else if ("edit".equalsIgnoreCase(actionCommand)) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
            }
            User user = ((UserTableModel) userTable.getModel())
                    .getUser(selectedRow);
            this.setVisible(false);
            parent.showEditPanel(user);
        } else if ("delete".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
            }
            try {
                parent.getUserDao()
                        .delete(((UserTableModel) userTable.getModel())
                                .getUser(selectedRow));
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            initTable();
        }
    }

    public void initTable() {
        UserTableModel model;
        try {
            model = new UserTableModel(
                    (ArrayList<User>) parent.getUserDao().findAll());
        } catch (DatabaseException e) {
            model = new UserTableModel(new ArrayList<User>());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        getUserTable().setModel(model);
    }
}
