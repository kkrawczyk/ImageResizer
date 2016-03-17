/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.imageresizer.helpers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Krzysztof
 */
public class ImageConverter {

    private File file;
    private File outputfile;
    private int width;
    private int height;

    public ImageConverter(String filename, String outputfile, int width, int height) {
        this.file = new File(filename);
        this.outputfile = new File(outputfile);
        this.width = width;
        this.height = height;
    }

    public void process() throws IOException {
        BufferedImage inImage = ImageIO.read(this.file);
        double ratio = (double) inImage.getWidth() / (double) inImage.getHeight();
        int w = this.width;
        int h = this.height;
        if (inImage.getWidth() >= inImage.getHeight()) {
            w = this.width;
            h = (int) Math.round(w / ratio);
        } else {
            h = this.height;
            w = (int) Math.round(ratio * h);
        }
        int type = inImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : inImage.getType();
        BufferedImage outImage = new BufferedImage(w, h, type);
        Graphics2D g = outImage.createGraphics();
        g.drawImage(inImage, 0, 0, w, h, null);
        g.dispose();
        String ext = FilenameUtils.getExtension(this.file.getAbsolutePath());
        String t = "jpg";
        switch (ext) {
            case "png":
                t = "png";
                break;
        }
        ImageIO.write(outImage, t, this.outputfile);
    }
}
