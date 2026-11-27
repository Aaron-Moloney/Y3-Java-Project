import java.awt.Color;
import java.rmi.RemoteException;

public class TypeB extends Star {
   TypeB() throws RemoteException {
      this.color = new Color(174, 192, 252);
      this.mass = 2.1;
      this.radius = 1.8;
      this.name = "Type B";
   }

   public String quickFact() {
      return "Fact here";
   }
}
