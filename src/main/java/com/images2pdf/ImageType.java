package com.images2pdf;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


import java.io.File;
import java.io.IOException;
enum Orientation{
    PORTRAIT,
    LANDSCAPE
}
public class ImageType {
    private Boolean optimized;
    private final Orientation orientation;
    private BufferedImage image;

    public Orientation getOrientation() {
        return orientation;
    }

    public BufferedImage getImage() {
        return image;
    }



    public ImageType(File file){
        this.optimized = false;
        try{
            this.image = ImageIO.read(file);
            System.out.println("Read image");

        }catch(IOException e){
            System.out.println("Error reading file: "+e);
        }
        if(image.getWidth() > image.getHeight()){
            orientation = Orientation.LANDSCAPE;
        }else{
            orientation = Orientation.PORTRAIT;
        }
    }
    public void optimizeImage() {
        double conversion = 0;
        int scaledWidth;
        int scaledHeight;
        if(     (optimized)||
                (image.getWidth()<3000 && orientation == Orientation.LANDSCAPE)||
                (image.getHeight()<3000 && orientation == Orientation.PORTRAIT)){
            System.out.println("Already optimized");
            optimized = true;
        }else{
            if(orientation == Orientation.LANDSCAPE){
                conversion = image.getWidth()/3000.0;

            }else if(orientation == Orientation.PORTRAIT){
                conversion = image.getHeight()/3000.0;
            }
            scaledWidth = (int) Math.round(image.getWidth()/conversion);
            scaledHeight =(int) Math.round(image.getHeight()/conversion);
            BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, image.getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(this.image, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();
            image = outputImage;

        }

    }
}
