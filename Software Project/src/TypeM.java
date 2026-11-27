import java.awt.Color;
import java.rmi.RemoteException;

public class TypeM extends Star {
   TypeM() throws RemoteException {
      this.color = new Color(253, 188, 121);
      this.mass = 0.08;
      this.radius = 0.6;
      this.name = "Type M";
   }

   public String quickFact() {
      return "Fact here";
   }
}
