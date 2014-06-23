/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.imageresizer.models;

import eu.novait.imageresizer.helpers.ImageFile;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Krzysztof
 */
public class ImageListModel extends AbstractListModel {

    private ArrayList<ImageFile> collection;
    
    public ImageListModel(){
        this.collection = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return this.collection.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.collection.get(index);
    }

    public void addElement(String filename) {
        ImageFile ifile = new ImageFile(filename);
        this.collection.add(ifile);
        this.fireContentsChanged(this, 0, this.collection.size());
    }

    public void removeElement(int index) {
        Object o = this.collection.get(index);
        if (o != null) {
            this.collection.remove(o);
        }
    }

    public void removeElement(Object o) {
        this.collection.remove(o);
        this.fireContentsChanged(this, 0, this.collection.size());
    }

    public ImageFile[] getAll() {
        Object[] obs = this.collection.toArray();
        ImageFile[] ret = new ImageFile[obs.length];
        this.collection.toArray(ret);
        return ret;
    }
}
