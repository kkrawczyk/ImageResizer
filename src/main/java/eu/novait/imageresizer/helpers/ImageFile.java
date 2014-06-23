/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.imageresizer.helpers;

import java.io.File;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Krzysztof
 */
public class ImageFile {

    private String filename;

    public ImageFile(String filename) {
        this.filename = filename;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean exists() {
        return new File(this.filename).exists();
    }

    @Override
    public String toString() {
        return new File(this.filename).getName();
    }

    public String getOutputFilename(String outputdir) {
        String newname = "";
        if (!outputdir.endsWith("/") && !outputdir.endsWith("\"")) {
            outputdir += "/";
        }
        File f = new File(this.filename);
        String basename = FilenameUtils.getName(filename);
        String ext = FilenameUtils.getExtension(basename);
        String onlyname = FilenameUtils.getBaseName(filename);
        
        newname = outputdir+basename;
        File outputfile = new File(newname);
        while(outputfile.exists()){
            int idx = 1;
            newname = outputdir+onlyname+"_"+Integer.toString(idx)+"."+ext;
            outputfile = new File(newname);
            idx++;
        }
        return newname;
    }
}
