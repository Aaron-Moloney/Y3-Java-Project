import java.awt.Color;
import java.rmi.RemoteException;

public class TypeA extends Star {
   TypeA() throws RemoteException {
      this.color = new Color(218, 224, 253);
      this.mass = 1.4;
      this.radius = 1.4;
      this.name = "Type A";
   }

   public String quickFact() {
      return "Fact here";
   }
}
