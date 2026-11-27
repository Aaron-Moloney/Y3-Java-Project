import java.awt.Color;
import java.rmi.RemoteException;

public class TypeO extends Star {
   TypeO() throws RemoteException {
      this.color = new Color(160, 180, 251);
      this.mass = (double)16.0F;
      this.radius = 6.7;
      this.name = "Type O";
   }

   public String quickFact() {
      return "Fact here";
   }
}
