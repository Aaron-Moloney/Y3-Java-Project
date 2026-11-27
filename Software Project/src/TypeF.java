import java.awt.Color;
import java.rmi.RemoteException;

public class TypeF extends Star {
   TypeF() throws RemoteException {
      this.color = new Color(252, 247, 255);
      this.mass = 1.04;
      this.radius = 1.15;
      this.name = "Type F";
   }

   public String quickFact() {
      return "Fact here";
   }
}
