/**
 * 版权所有 2013 efuture Company, Inc. 保留所有权
 */
package cn.suisun.resource.test;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.servlet.GrizzlyWebContainerFactory;

/**
 * 测试容器
 * 
 * @author wanghj
 * @data   2013-05-27
 *
 */
public class Main {

    private static int getPort(int defaultPort) {
       String port = System.getProperty("jersey.test.port");
        if (null != port) {
            try {
                return Integer.parseInt(port);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;        
    } 
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {
        final Map<String, String> initParams = new HashMap<String, String>();
        
        //必须对应相应的包
        initParams.put("com.sun.jersey.config.property.packages", 
                "com.efuture.storage.service");   

        System.out.println("Starting grizzly...");
        return GrizzlyWebContainerFactory.create(BASE_URI, initParams);
    }
    
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
                BASE_URI, BASE_URI));
        System.in.read();
        httpServer.stop();
    }    
}
