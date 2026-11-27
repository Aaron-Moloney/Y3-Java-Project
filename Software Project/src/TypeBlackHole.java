import java.awt.Color;
import java.rmi.RemoteException;

public class TypeBlackHole extends Star {
   TypeBlackHole() throws RemoteException {
      this.color = new Color(0, 0, 0);
      this.blackHole = true;
      this.name = "Black Hole";
   }

   public String quickFact() {
      return "Nothing can escape from here.";
   }
}
