import java.awt.Color;
import java.rmi.RemoteException;
import javax.swing.JFrame;

public class StarGUI extends JFrame {
   int baseSize = 200;
   Color color;

   StarGUI(StarInterface star) throws RemoteException {
      super(star.getName());
      this.baseSize = (int)(star.getRadius() * (double)this.baseSize);
      if (this.baseSize < 100) {
         this.baseSize = 100;
      }

      if (this.baseSize > 1000) {
         this.baseSize = 1000;
      }

      this.color = star.getColor();
      this.getContentPane().setBackground(this.color);
      this.setSize(this.baseSize * 3, this.baseSize);
      this.setVisible(true);
   }
}
