import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//StarListInterface for use in the client
public interface StarListInterface extends Remote {
   boolean addStar(double var1) throws RemoteException;

   ArrayList<Star> getList() throws RemoteException;

   void removeStar(int var1) throws RemoteException;

   void removeStar(String var1, double var2, double var3) throws RemoteException;

   void saveList() throws RemoteException, IOException, ClassNotFoundException;

   void loadList() throws RemoteException, IOException, ClassNotFoundException;
}
