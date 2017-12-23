

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer
{
   Image background;

   double x;
   double y;
   double z;

   int w;
   int h;
   int count;


   public ImageLayer(String filename, double x, double y, double z, int w,int h, int count)
   {
      background = Toolkit.getDefaultToolkit().getImage(filename);

      this.x = x;
      this.y = y;
      this.z = z;
      this.h = h;  // background height
      this.w = w;
      this.count  = count;

   }


   public void draw(Graphics g)
   {
		for (int i = 0; i< 1000; i++){
			g.drawImage(background,(int) (0-Camera2D.x/z),(int)(0-Camera2D.y)-i*h,null);
   }


}
}
