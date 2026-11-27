import java.awt.Color;
import java.rmi.RemoteException;

public class TypeA extends Star {
	//Set Params based on typical values and colour that the star shines at
   TypeA() throws RemoteException {
      this.color = new Color(218, 224, 253);
      this.mass = 1.4;
      this.radius = 1.4;
      this.name = "Type A";
   }

   //Incomplete method, no facts
   public String quickFact() {
      return "Fact here";
   }
}
