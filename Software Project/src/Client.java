import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
   static StarListInterface listInterface = null;

   public static void main(String[] args) throws Exception {
      try {
         Registry r = LocateRegistry.getRegistry("localhost", 1234);
         listInterface = (StarListInterface)r.lookup("ListOfStars");
      } catch (RemoteException e) {
         e.printStackTrace();
      }

      new ProjectGUI(listInterface);
   }
}
