import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class Star extends UnicastRemoteObject implements StarInterface, Serializable {
   String name = "";
   Color color;
   double mass;
   double radius;
   boolean blackHole;

   Star() throws RemoteException {
   }

   public Color getColor() {
      return this.color;
   }

   public void setMass(double mass) {
      this.mass = mass;
      this.setRadius(this.getRadius());
   }

   public double getMass() {
      return this.mass;
   }

   public void setRadius(double radius) {
      if (!this.blackHole && !(radius <= 4.244645680609458E-5 * this.mass)) {
         this.radius = radius;
      } else {
         this.radius = 4.2446456806094585E-6 * this.mass;
         this.name = "Black Hole";
         this.blackHole = true;
         this.color = new Color(0, 0, 0);
      }
   }

   public double getRadius() {
      return this.radius;
   }

   public boolean isBlackHole() {
      return this.blackHole;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String n) {
      if (this.blackHole) {
         this.name = "Black Hole";
      } else {
         this.name = n;
      }
   }
}
