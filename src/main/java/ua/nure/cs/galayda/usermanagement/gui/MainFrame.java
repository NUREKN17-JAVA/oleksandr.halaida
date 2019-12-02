package ua.nure.cs.galayda.usermanagement.gui;

import ua.nure.cs.galayda.usermanagement.db.DAO;
import ua.nure.cs.galayda.usermanagement.db.DAOFactory;
import ua.nure.cs.galayda.usermanagement.entity.User;
import ua.nure.cs.galayda.usermanagement.gui.util.Messages;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 4990500669153305864L;

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private JPanel contentPanel;
    private BrowsePanel browsePanel;
    private AddPanel addPanel;
    private DAO<User> dao;
    private EditPanel editPanel;

    public MainFrame() {
        super();
        dao = DAOFactory.getInstance().getUserDao();
        initialize();
    }

    public DAO<User> getUserDao() {
        return dao;
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(Messages.getString("MainFrame.user_management"));
        this.setContentPane(getContentPanel());
    }

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(
                    new BorderLayout());//главный контент пэнел, где будут панели add delete details
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private BrowsePanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        browsePanel.initTable();
        return browsePanel;
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }

    private AddPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }
        return addPanel;
    }

    public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }

    private EditPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }

    public void showEditPanel(User user) {
        getEditPanel().setUser(user);
        showPanel(getEditPanel());

    }
}