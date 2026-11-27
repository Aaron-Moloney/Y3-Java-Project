import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProjectGUI extends JFrame implements ActionListener {
   StarListInterface starListInterface;
   JButton loadButton = new JButton("Load Stars");
   JButton saveButton = new JButton("Save Stars");
   JButton enterButton = new JButton("Create");
   JTextField inputField = new JTextField("Input field");
   JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
   GridLayout starGrid = new GridLayout(1, 1);
   JPanel starPanel;
   JPanel inputButtonPanel;
   JPanel inputTextFieldPanel;
   JPanel southPanel;

   ProjectGUI(StarListInterface list) throws RemoteException {
      super("Magellanic Clouds Sandbox");
      this.starPanel = new JPanel(this.starGrid);
      this.inputButtonPanel = new JPanel(new GridLayout(1, 2));
      this.inputTextFieldPanel = new JPanel(new GridLayout(1, 1));
      this.southPanel = new JPanel(new GridLayout(2, 1));
      this.starListInterface = list;
      this.loadButton.addActionListener(this);
      this.buttonPanel.add(this.loadButton);
      this.buttonPanel.add(this.saveButton);
      this.saveButton.addActionListener(this);
      this.enterButton.addActionListener(this);
      this.starPanel.removeAll();
      this.starGrid.setRows(this.starListInterface.getList().size());

      for(StarInterface star : this.starListInterface.getList()) {
         JPanel newPanel = new JPanel(new GridLayout(1, 2));
         String var10003 = star.getName();
         newPanel.add(new JLabel(" Name: " + var10003 + " | Mass: " + star.getMass() + " | Radius " + star.getRadius()));
         JPanel newNewPanel = new JPanel(new GridLayout(1, 3));
         JButton viewButton = new JButton("View");
         viewButton.putClientProperty("View", star);
         viewButton.addActionListener(this);
         newNewPanel.add(viewButton);
         JButton editNameButton = new JButton("Edit Name");
         editNameButton.putClientProperty("Name", star);
         editNameButton.addActionListener(this);
         newNewPanel.add(editNameButton);
         JButton editRadiusButton = new JButton("Edit Radius");
         editRadiusButton.putClientProperty("Radius", star);
         editRadiusButton.addActionListener(this);
         newNewPanel.add(editRadiusButton);
         JButton editMassButton = new JButton("Edit Mass");
         editMassButton.putClientProperty("Mass", star);
         editMassButton.addActionListener(this);
         newNewPanel.add(editMassButton);
         JButton deleteButton = new JButton("Delete");
         deleteButton.putClientProperty("Delete", star);
         deleteButton.addActionListener(this);
         newNewPanel.add(deleteButton);
         newPanel.add(newNewPanel);
         this.starPanel.add(newPanel);
      }

      this.inputButtonPanel.add(this.enterButton);
      this.inputTextFieldPanel.add(this.inputField);
      this.add(this.buttonPanel, "North");
      this.add(this.starPanel, "Center");
      this.southPanel.add(this.inputTextFieldPanel);
      this.southPanel.add(this.inputButtonPanel);
      this.add(this.southPanel, "South");
      this.setDefaultCloseOperation(3);
      this.setSize(1000, 600);
      this.starPanel.revalidate();
      this.starPanel.repaint();
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent e) {
      Object target = e.getSource();
      if (target == this.loadButton) {
         try {
            this.starListInterface.loadList();
            this.reloadStarPanel();
         } catch (IOException | ClassNotFoundException e1) {
            ((Exception)e1).printStackTrace();
         }
      }

      if (target == this.saveButton) {
         try {
            System.out.println("Attempting save");
            this.starListInterface.saveList();
         } catch (IOException | ClassNotFoundException e1) {
            ((Exception)e1).printStackTrace();
         }
      }

      if (target == this.enterButton) {
         try {
            double input = Double.parseDouble(this.inputField.getText());
            this.starListInterface.addStar(input);
            this.reloadStarPanel();
         } catch (IOException e1) {
            e1.printStackTrace();
         }
      }

      Object var4;
      if ((var4 = e.getSource()) instanceof JButton) {
         JButton button = (JButton)var4;
         if (button.getClientProperty("View") != null) {
            StarInterface star = (StarInterface)button.getClientProperty("View");

            try {
               new StarGUI(star);
            } catch (RemoteException e1) {
               e1.printStackTrace();
            }
         }

         if (button.getClientProperty("Radius") != null) {
            double input = (double)0.0F;
            StarInterface star = (StarInterface)button.getClientProperty("Radius");

            try {
               input = Double.parseDouble(this.inputField.getText());
            } catch (Exception exc) {
               exc.printStackTrace();
               System.out.println("Dont try and set a number to a string");
            }

            if (!(input <= (double)0.0F)) {
               try {
                  star.setRadius(input);
                  this.reloadStarPanel();
               } catch (RemoteException e1) {
                  e1.printStackTrace();
               }
            }
         }

         if (button.getClientProperty("Mass") != null) {
            double input = (double)0.0F;
            StarInterface star = (StarInterface)button.getClientProperty("Mass");

            try {
               input = Double.parseDouble(this.inputField.getText());
            } catch (Exception exc) {
               exc.printStackTrace();
               System.out.println("Dont try and set a number to a string");
            }

            if (!(input <= (double)0.0F)) {
               try {
                  star.setMass(input);
                  this.reloadStarPanel();
               } catch (RemoteException e1) {
                  e1.printStackTrace();
               }
            }
         }

         if (button.getClientProperty("Name") != null) {
            String input = "";
            StarInterface star = (StarInterface)button.getClientProperty("Name");

            try {
               input = this.inputField.getText();
            } catch (Exception exc) {
               exc.printStackTrace();
               System.out.println("Some kind of error");
            }

            if (input != null && input.length() != 0) {
               try {
                  star.setName(input);
                  this.reloadStarPanel();
               } catch (RemoteException e1) {
                  e1.printStackTrace();
               }
            }
         }

         if (button.getClientProperty("Delete") != null) {
            System.out.println("Attempting delete.");
            StarInterface star = (StarInterface)button.getClientProperty("Delete");

            try {
               this.starListInterface.removeStar(star.getName());
               this.reloadStarPanel();
            } catch (Exception exc) {
               exc.printStackTrace();
               System.out.println("Some kind of error when deleting");
            }
         }
      }

   }

   void reloadStarPanel() throws RemoteException {
      this.starPanel.removeAll();
      this.starGrid.setRows(this.starListInterface.getList().size());

      for(StarInterface star : this.starListInterface.getList()) {
         JPanel newPanel = new JPanel(new GridLayout(1, 2));
         String var10003 = star.getName();
         newPanel.add(new JLabel(" Name: " + var10003 + " | Mass: " + star.getMass() + " | Radius " + star.getRadius()));
         JPanel newNewPanel = new JPanel(new GridLayout(1, 3));
         JButton viewButton = new JButton("View");
         viewButton.putClientProperty("View", star);
         viewButton.addActionListener(this);
         newNewPanel.add(viewButton);
         JButton editNameButton = new JButton("Edit Name");
         editNameButton.putClientProperty("Name", star);
         editNameButton.addActionListener(this);
         newNewPanel.add(editNameButton);
         JButton editRadiusButton = new JButton("Edit Radius");
         editRadiusButton.putClientProperty("Radius", star);
         editRadiusButton.addActionListener(this);
         newNewPanel.add(editRadiusButton);
         JButton editMassButton = new JButton("Edit Mass");
         editMassButton.putClientProperty("Mass", star);
         editMassButton.addActionListener(this);
         newNewPanel.add(editMassButton);
         JButton deleteButton = new JButton("Delete");
         deleteButton.putClientProperty("Delete", star);
         deleteButton.addActionListener(this);
         newNewPanel.add(deleteButton);
         newPanel.add(newNewPanel);
         this.starPanel.add(newPanel);
      }

      this.starPanel.revalidate();
      this.starPanel.repaint();
   }
}
