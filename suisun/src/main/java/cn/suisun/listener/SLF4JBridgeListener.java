package cn.suisun.listener;

import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class SLF4JBridgeListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		 Logger logger = LogManager.getLogManager().getLogger("");
	        Handler[] handlers = logger.getHandlers();
	        for (Handler handler : handlers) {
	            logger.removeHandler(handler);
	        }
	        SLF4JBridgeHandler.install();
		
	}

    
 

}
