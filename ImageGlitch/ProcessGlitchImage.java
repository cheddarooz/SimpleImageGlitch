    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    import javafx.scene.layout.HBox;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.image.PixelReader;
    import javafx.scene.image.PixelWriter;
    import javafx.scene.image.WritableImage;
    
    import javafx.scene.paint.Color;
    public class ProcessGlitchImage extends Application
    {
        public static void main(String[] arg)
        {            
            boolean glitchComplete = false; 
            boolean glitchSaved = false;
            
            launch(arg);
        }
        @Override
        public void start(Stage stage)
        {
            
            Image image = new Image("oregonWater.jpg");
            glitch(image);
            ImageView imageView = new ImageView(image);
            HBox hbox = new HBox(imageView);
            
            Scene scene = new Scene(hbox, image.getWidth(), image.getHeight());
            
            stage.setScene(scene);
            stage.setTitle("Image Viewer");
            stage.setResizable(true);
            
            //write a condidition that checks whether or not to show the image
            stage.show();
        }
        // make this method a graphics draw method to actually draw the glitches
        public void glitch(Image visual)
        {
            boolean previousPixelGlitched = false;
            PixelReader pixelReader = visual.getPixelReader();
            WritableImage writableImage = new WritableImage((int)visual.getWidth(), (int)visual.getHeight()); 
            
            int imageWidth = (int)visual.getWidth(); 
            int imageHeight = (int)visual.getHeight();
            //used a 2D array instead
            //int[] imageHeightArr = new int[imageHeight];
            //int[] imageWidthArr = new int[imageWidth];
            int[][] imagePixelSet = new int[imageWidth][imageHeight];
            
            for(int x = 0; x < imageWidth; x++)
            {
                for(int y = 0; y < imageHeight; y++)
                {
                    imagePixelSet[x][y] = y;
                    //if you want to load the color of the pixel in the array...
                    //int pixel = pixelReader.getArgb(x,y);
                    //imagePixelSet[x][y] = pixel;
                }
            }
            // R G B A
            Color randomColor = new Color((double)(Math.random()* 1)+ 0,(double)(Math.random()* 1)+ 0,(double)(Math.random()* 1)+ 0, 1); 
            //25% chance to glitch this pixel, a second 80% if the previous pixel was glitched
            for(int x = 0; x < imageWidth; x++)
            {
                for(int y = 0; y < imageHeight; y++)
                {
                    if(((int)(Math.random() * 100) + 0) < 25 || (previousPixelGlitched == true && (((int)(Math.random()* 100) +0) < 80)))
                    {
                        previousPixelGlitched = true; 
                        //get this current pixels color 
                        Color pixelColor = pixelReader.getColor(x,y);
                        double mixPercentage = Math.round(.5 + Math.random() * 50) / 100.0; 
                        //interpolate (new data points from random color, percentage, and pixel color) equation: colorA * p + colorB * (1.0 - p) p = mixPercentage
                        double lerpColorR = pixelColor.getRed()* mixPercentage + (randomColor.getRed() * (1.0 - mixPercentage)); 
                        double lerpColorG = pixelColor.getGreen()*mixPercentage + (randomColor.getGreen() * (1.0 - mixPercentage));
                        double lerpColorB = pixelColor.getBlue()*mixPercentage + (randomColor.getBlue() * (1.0 - mixPercentage));
                        
                        Color interpolatedColor = new Color(lerpColorR,lerpColorG,lerpColorB);
                        
                    } 
                    else 
                    {
                        previousPixelGlitched = false; 
                        // assign new color to random color
                        randomColor = new Color((double)(Math.random()* 1)+ 0,(double)(Math.random()* 1)+ 0,(double)(Math.random()* 1)+ 0, 1);
                    }
                }
         }
      }
    public void finalResult()
    {
         
            
    }
}
