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

public class GetPlugins {
    private static final Logger logger = Logger.getLogger(GetPlugins.class);
    
    public static final String FACTPP_ID="uk.ac.manchester.cs.owl.factplusplus";
    
    public static void main(String[] args) {
        try {
            logger.info("finding plugins...");
            PluginRegistry registry = PluginManager.getInstance().getPluginRegistry();
            download(registry, FACTPP_ID);
        }
        catch (Throwable t) {
            logger.error("Exception caught", t);
            System.exit(-1);
        }
    }
    
    public static void download(PluginRegistry registry, String id) throws IOException {
        for (PluginInfo info : registry.getAvailableDownloads()) {
            if (id == null || info.getId().equals(id)) {
                logger.info("trying to download " + info.getId());
                try {
                    URL downloadUrl = info.getDownloadURL();
                    File output = new File("build" + File.separator + info.getId() + ".jar");
                    InputStream in = new BufferedInputStream(downloadUrl.openStream());
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(output));
                    int c;
                    while ((c = in.read()) != -1) {
                        out.write(c);
                    }
                    out.flush();
                    in.close();
                    out.close();
                    logger.info("Downloaded " + info.getId() + ".jar from");
                    logger.info("   " + info.getDownloadURL());
                }
                catch (Throwable t) {
                    logger.warn("Exception caught during download " + t);
                }
            }
        }
    }
}
