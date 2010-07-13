package org.protege.install.binary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.log4j.Logger;
import org.protege.editor.core.update.PluginInfo;
import org.protege.editor.core.update.PluginManager;
import org.protege.editor.core.update.PluginRegistry;

public class DownloadAllPlugins {
    private static final Logger logger = Logger.getLogger(GetPlugins.class);
    
    public static final String FACTPP_ID="uk.ac.manchester.cs.owl.factplusplus";
    
    public static void main(String[] args) {
        try {
            logger.info("finding plugins...");
            PluginRegistry registry = PluginManager.getInstance().getPluginRegistry();
            GetPlugins.download(registry, null);
        }
        catch (Throwable t) {
            logger.error("Exception caught", t);
            System.exit(-1);
        }
    }

}
