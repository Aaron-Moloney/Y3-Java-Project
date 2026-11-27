import java.awt.Color;
import java.rmi.RemoteException;

public class TypeK extends Star {
   TypeK() throws RemoteException {
      this.color = new Color(254, 222, 189);
      this.mass = 0.45;
      this.radius = 0.7;
      this.name = "Type K";
   }

   public String quickFact() {
      return "Fact here";
   }
}
