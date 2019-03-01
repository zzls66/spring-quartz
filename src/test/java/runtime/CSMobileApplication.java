/**
 * 
 */
package runtime;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Jetty Server starter.
 * 
 * @author wilson
 *
 */
public class CSMobileApplication {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
      
        long begin = System.currentTimeMillis();
        String projectFolder = System.getProperty("user.dir");
            //projectFolder = projectFolder + "/spring-quartz";

        WebAppContext webapp = new WebAppContext(projectFolder, "/spring-quartz");
        //WebAppContext webapp = new WebAppContext("D:\\wp2\\cnhutong\\cnhutong-cs-mobile", "/cnhutong-cs-mobile");
        webapp.setDefaultsDescriptor(projectFolder + "/src/test/resources/runtime/webdefault.xml");
        // add mock web.xml here to replace the default web.xml under webapp and enable mockMode
        webapp.setDescriptor(projectFolder + "/src/test/resources/runtime/web.local.xml");

        Server server = new Server(8090);
        server.setHandler(webapp);
        server.start();
        
        System.out.println("Jetty Server started, use " + (System.currentTimeMillis() - begin) + " ms");
    }
}
