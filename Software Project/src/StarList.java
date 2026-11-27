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

   //Uses the factory to add a new Star of the type returned by the factory
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

   //Remove based on position
   public void removeStar(int i) {
      this.list.remove(i);
   }

   //Remove based on name, radius and mass
   //Iterate until found
   public void removeStar(String i, double var2, double var3) {
//      System.out.println("here");

      for(int j = 0; j < this.list.size(); ++j) {
         if (((Star)this.list.get(j)).getName().equals(i) &&
        		 this.list.get(j).getRadius() == var2 &&
		 this.list.get(j).getMass() == var3) {
            this.list.remove(j);
            break;
         }
      }

   }
   
   //Could do with a create if not exist function for serialisation

   //Serialise the list, do nothing if its empty, otherwise just write it
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

   //Deserialise the list, make sure to case the object to what you expect
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
