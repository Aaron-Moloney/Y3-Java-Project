import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

//StarInterface for use in the Client
public interface StarInterface extends Remote {
   Color getColor() throws RemoteException;
   String quickFact() throws RemoteException;
   void setMass(double var1) throws RemoteException;
   double getMass() throws RemoteException;
   void setRadius(double var1) throws RemoteException;
   double getRadius() throws RemoteException;
   boolean isBlackHole() throws RemoteException;
   String getName() throws RemoteException;
   void setName(String var1) throws RemoteException;
}
