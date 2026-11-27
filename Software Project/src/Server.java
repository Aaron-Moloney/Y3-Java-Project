import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
   static StarList list;

   public static void main(String[] args) throws Exception {
      list = new StarList();
      
      //Defaults so you don't load a blank page
//      list.addStar((double)1.0F);
//      list.addStar((double)8.0F);
//      list.addStar((double)5.0F);

      try {
         Registry r = LocateRegistry.createRegistry(1234);
         r.bind("ListOfStars", list);
      } catch (RemoteException e) {
         e.printStackTrace();
      }

   }
}