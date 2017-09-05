package com.sreeni.rest.api;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class App {
    
    public static void main(String[] args) {

        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "com.sreeni.rest.api");
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        Server server = new Server(2222);
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addServlet(sh, "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            server.destroy();
        }

        // ResourceConfig config = new ResourceConfig();
        // config.packages("com.sreeni.rest.api");
        //
        // ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        //
        // Server server = new Server(2222);
        // ServletContextHandler context = new ServletContextHandler(server, "/*");
        // context.addServlet(servlet, "/*");
        //
        // try {
        // server.start();
        // server.join();
        // }catch(Exception ex){
        // ex.printStackTrace();
        // } finally {
        // server.destroy();
        // }

    }

}
