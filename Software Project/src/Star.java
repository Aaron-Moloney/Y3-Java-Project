import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Base class for star types, extends remoteObject to allow the client to use it
//Some logic in the radius setter is to check if you make a black hole
public abstract class Star extends UnicastRemoteObject implements StarInterface{
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
	   //4.24465... was orignially a double division operation to get the Schwarzschild Radius (2953.0 / 695700000.0),
	   //Decompilation has caused it to be this instead
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
    	  //Black Holes are immutable, though they actually dissolve over time
         this.name = "Black Hole";
      } else {
         this.name = n;
      }
   }
}
