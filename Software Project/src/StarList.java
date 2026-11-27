import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StarList extends UnicastRemoteObject implements StarListInterface, Serializable {
   ArrayList<Star> list = new ArrayList();

   public StarList() throws RemoteException {
   }

   public boolean addStar(double s) {
      try {
         this.list.add(StellarFactory.getBody(s));
         return true;
      } catch (RemoteException e) {
         e.printStackTrace();
         return false;
      }
   }

   public ArrayList<Star> getList() {
      return this.list;
   }

   public void removeStar(int i) {
      this.list.remove(i);
   }

   public void removeStar(String i) {
      System.out.println("here");

      for(int j = 0; j < this.list.size(); ++j) {
         if (((Star)this.list.get(j)).getName().equals(i)) {
            this.list.remove(j);
            break;
         }
      }

   }

   public void saveList() throws IOException, ClassNotFoundException {
      System.out.println("Saving stars.");
      if (this.list.size() != 0) {
         try {
            FileOutputStream fileOut = new FileOutputStream("stars.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.list);
            objectOut.close();
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         }

      }
   }

   public void loadList() throws IOException, ClassNotFoundException {
      System.out.println("Loading stars.");

      try {
         FileInputStream fileIn = new FileInputStream("stars.ser");
         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
         this.list = (ArrayList)objectIn.readObject();

         for(Star star : this.list) {
            System.out.println(star.getName());
         }

         objectIn.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

   }
}
