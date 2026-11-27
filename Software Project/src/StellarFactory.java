import java.rmi.RemoteException;

public class StellarFactory {
   public static Star getBody(double mass) throws RemoteException {
      if (mass >= (double)16.0F) {
         return new TypeO();
      } else if (mass >= 2.1) {
         return new TypeB();
      } else if (mass >= 1.4) {
         return new TypeA();
      } else if (mass >= 1.04) {
         return new TypeF();
      } else if (mass >= 0.8) {
         return new TypeG();
      } else if (mass >= 0.45) {
         return new TypeK();
      } else {
         return mass >= 0.08 ? new TypeM() : null;
      }
   }
}
