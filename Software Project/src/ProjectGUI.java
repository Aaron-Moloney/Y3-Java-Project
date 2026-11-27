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

//Decompiler added this, started causing errors
//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

public class ProjectGUI extends JFrame implements ActionListener {
	//Everything we need, buttons fields and panels
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

   //We take in the StarListInterface that the Client recieves
   ProjectGUI(StarListInterface list) throws RemoteException {
	   //Set everything up, originally a lot of this.* but the decompiler decided to add them to everything
	   //Better than the project being gone forever
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
      //Reset the starPanel then, for every star in the list add a specifically formatted panel
      //First panel is 1 tall 2 wide, text on the left, another panel on the right
      //Right panel has all the buttons
      this.starPanel.removeAll();
      this.starGrid.setRows(this.starListInterface.getList().size());
      for(StarInterface star : this.starListInterface.getList()) {
         JPanel newPanel = new JPanel(new GridLayout(1, 2));
         //I did not call this var10003, thanks decompiler, I think I just did getName()
         String var10003 = star.getName();
         newPanel.add(new JLabel(" Name: " + var10003 + " | Mass: " + star.getMass() + " | Radius " + star.getRadius()));
         JPanel newNewPanel = new JPanel(new GridLayout(1, 3));
         JButton viewButton = new JButton("View");
         
         //The putClientProperty method allows us to attach a key and a value to a button, key being the buttons name
         // and value being a reference to the current star in the for loop
         //Later when we push the button and in actionPerformed we can check for the key then
         // recieve the star to do things with
         viewButton.putClientProperty("View", star);
         viewButton.addActionListener(this);
         newNewPanel.add(viewButton);
         
         //Same putClientPropery functionality
         JButton editNameButton = new JButton("Edit Name");
         editNameButton.putClientProperty("Name", star);
         editNameButton.addActionListener(this);
         newNewPanel.add(editNameButton);
         
         //Same putClientPropery functionality
         JButton editRadiusButton = new JButton("Edit Radius");
         editRadiusButton.putClientProperty("Radius", star);
         editRadiusButton.addActionListener(this);
         newNewPanel.add(editRadiusButton);
         
         //Same putClientPropery functionality
         JButton editMassButton = new JButton("Edit Mass");
         editMassButton.putClientProperty("Mass", star);
         editMassButton.addActionListener(this);
         newNewPanel.add(editMassButton);
         
         //Same putClientPropery functionality
         JButton deleteButton = new JButton("Delete");
         deleteButton.putClientProperty("Delete", star);
         deleteButton.addActionListener(this);
         newNewPanel.add(deleteButton);
         newPanel.add(newNewPanel);
         this.starPanel.add(newPanel);
      }
//	  Add everything, reload the panel, setvisible, resize, etc.
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
      //Load using serialisation methods in the list itself
      if (target == this.loadButton) {
         try {
        	 System.out.println("Attempting Load");
            this.starListInterface.loadList();
            this.reloadStarPanel();
         } catch (IOException | ClassNotFoundException e1) {
            ((Exception)e1).printStackTrace();
         }
      }

    //Save using serialisation methods in the list itself
      if (target == this.saveButton) {
         try {
            System.out.println("Attempting save");
            this.starListInterface.saveList();
         } catch (IOException | ClassNotFoundException e1) {
            ((Exception)e1).printStackTrace();
         }
      }
      
//	  Add a new star to the listbased on the input value
      //StarList uses the Factory
      if (target == this.enterButton) {
         try {
        	 System.out.println("Attempting to add a star");
            double input = Double.parseDouble(this.inputField.getText());
            this.starListInterface.addStar(input);
            this.reloadStarPanel();
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         catch (NumberFormatException ex)
         {
        	 ex.printStackTrace();
        	 System.out.println("Enter a number not text.");
         }
      }

      //More decompiler name schemes
      Object var4;
      //Check if the input is coming from a button then...
      if ((var4 = e.getSource()) instanceof JButton) {
    	  //Cast? Decompiler did this, probably not necessary.
         JButton button = (JButton)var4;
         //Check if a certain key exists, then use the star we can get from that to do something
         // based on the button, in this case make a window representing the star
         if (button.getClientProperty("View") != null) {
            StarInterface star = (StarInterface)button.getClientProperty("View");

            try {
               new StarGUI(star);
            } catch (RemoteException e1) {
               e1.printStackTrace();
            }
         }
         
       //Check if a certain key exists, then use the star we can get from that to do something
         // based on the button, in this case changing the radius
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
                  // reloadStarPanel called after fields are updated to refresh the GUI to reflect the changes
                  this.reloadStarPanel();
               } catch (RemoteException e1) {
                  e1.printStackTrace();
               }
            }
         }
         
       //Check if a certain key exists, then use the star we can get from that to do something
         // based on the button, in this case changing the mass
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
                  // reloadStarPanel called after fields are updated to refresh the GUI to reflect the changes
                  this.reloadStarPanel();
               } catch (RemoteException e1) {
                  e1.printStackTrace();
               }
            }
         }
         
       //Check if a certain key exists, then use the star we can get from that to do something
         // based on the button, in this case changing the name
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
                  // reloadStarPanel called after fields are updated to refresh the GUI to reflect the changes
                  this.reloadStarPanel();
               } catch (RemoteException e1) {
                  e1.printStackTrace();
               }
            }
         }
         
       //Check if a certain key exists, then use the star we can get from that to do something
         // based on the button, in this case deleting the star from the list
         if (button.getClientProperty("Delete") != null) {
            System.out.println("Attempting delete.");
            StarInterface star = (StarInterface)button.getClientProperty("Delete");

            try {
               this.starListInterface.removeStar(star.getName(), star.getRadius(), star.getMass());
               // reloadStarPanel called after fields are updated to refresh the GUI to reflect the changes
               this.reloadStarPanel();
            } catch (Exception exc) {
               exc.printStackTrace();
               System.out.println("Some kind of error when deleting");
            }
         }
      }

   }

   //Remakes the list so it accurately shows new values, then refreshes itself
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
