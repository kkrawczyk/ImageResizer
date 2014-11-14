/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.imageresizer.helpers;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 *
 * @author Krzysztof
 */
public class JAIDetector {

    public static boolean jaiAvailable() {
        boolean mediaLib = false;
        Class mediaLibImage = null;
        try {
            mediaLibImage = Class.forName("com.sun.medialib.mlib.Image");
        } catch (ClassNotFoundException e) {
        }
        mediaLib = (mediaLibImage != null);

        // npw check if we either wanted to disable explicitly and if we installed the native libs
        if (mediaLib) {

            try {
                // explicit disable
                mediaLib
                        = !Boolean.getBoolean("com.sun.media.jai.disableMediaLib");

                //native libs installed
                if (mediaLib) {
                    final Class mImage = mediaLibImage;
                    mediaLib = AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
                        public Boolean run() {
                            try {
                                final Class params[] = {};
                                Method method = mImage.getDeclaredMethod("isAvailable", params);
                                final Object paramsObj[] = {};
                                final Object o = mImage.newInstance();
                                return (Boolean) method.invoke(o, paramsObj);
                            } catch (Throwable e) {
                                return false;
                            }
                        }
                    });
                }
            } catch (Throwable e) {
                mediaLib = false;
            }
        }
        return mediaLib;
    }
}
