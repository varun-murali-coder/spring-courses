package webserver;

import java.io.IOException;
import java.io.OutputStream;

import com.reflections.ServerConfiguration;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
	
public void startServer() throws IOException {
	HttpServer server=HttpServer.create(ServerConfiguration.getInstance().getServerAddress(), 0);
	server.createContext("/greeting").setHandler(exchange->{
		String responseMessage=ServerConfiguration.getInstance().getGreetingMessage();
		exchange.sendResponseHeaders(200, responseMessage.length());
		OutputStream responseBody=exchange.getResponseBody();
		responseBody.write(responseMessage.getBytes());
		responseBody.flush();
		responseBody.close();
	});
	System.out.println("Starting server on port:"+ServerConfiguration.getInstance().getServerAddress().getPort());
	server.start();
}
}
