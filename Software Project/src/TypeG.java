import java.awt.Color;
import java.rmi.RemoteException;

public class TypeG extends Star {
   TypeG() throws RemoteException {
      this.color = new Color(254, 239, 229);
      this.mass = 0.8;
      this.radius = 0.96;
      this.name = "Type G";
   }

   public String quickFact() {
      return "Fact here";
   }
}
