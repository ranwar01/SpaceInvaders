public class Camera2D
{
   static double x;
   static double y;

   public static void moveUpBy(int dy)
   {
      y -= dy;
      
   }

   public static void moveDownBy(int dy)
   {
      y += dy;
   }

   public static void moveLeftBy(int dx)
   {
      x -= dx;
   }

   public static void moveRightBy(int dx)
   {
      x += dx;
   }
   public static void setY(int dx)
   {
      y = dx;;
   }
   public static double getCamCurY(){
	   return y;
   }

}
