package com.probeaufgabe.servers.services;

import com.probeaufgabe.servers.models.Server;
import com.probeaufgabe.servers.models.ServerResponse;
import com.probeaufgabe.servers.utils.Exceptions.ExceptionFault;
import com.probeaufgabe.servers.utils.Exceptions.Invalid_URI_Exception;
import com.probeaufgabe.servers.utils.Exceptions.GenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServer {

        Logger logger = LoggerFactory.getLogger(ServiceServer.class);

        public List<ServerResponse> checkServersList(List<Server> servers) {

                HttpClient client = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_1_1)
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .connectTimeout(Duration.ofSeconds(20))
                        .build();

                List<ServerResponse> serverResponses = new ArrayList<ServerResponse>();
                int response;
                ServerResponse serverResponse;

                for(Server server:servers){

                        response = checkUrl(server.getUrl(), client);

                        serverResponse = ServerResponse.builder()
                                        .code(response)
                                        .build();

                     serverResponses.add(serverResponse);
                }

                return serverResponses;
        }

        public int checkUrl(String serverUrl, HttpClient client)  {
                HttpResponse<String> response;
                int statusCode = 0;

                try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(serverUrl))
                        .GET()
                        .build();


                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        statusCode = response.statusCode();


                }
                catch (URISyntaxException | IOException | InterruptedException | IllegalArgumentException | NullPointerException exception ){
                        if (exception instanceof URISyntaxException){
                                statusCode = ExceptionFault.INVALID_URI_EXCEPTION.getCode();
                                logger.error(getErrorTypeWithURL(serverUrl),new Invalid_URI_Exception(exception));
                        }else if (exception instanceof InterruptedException ) {
                                statusCode = ExceptionFault.GENERIC_ERROR.getCode();
                                logger.error(getErrorTypeWithURL(serverUrl),new GenericException(exception,"Interrupted Exception Raised"));
                        } else if (exception instanceof  IllegalArgumentException) {
                                statusCode = ExceptionFault.INVALID_URI_EXCEPTION.getCode();
                                logger.error(getErrorTypeWithURL(serverUrl),new Invalid_URI_Exception(exception,"Illegal Argument Exception"));
                        } else if (exception instanceof  NullPointerException) {
                                statusCode = ExceptionFault.INVALID_URI_EXCEPTION.getCode();
                                logger.error(getErrorTypeWithURL(serverUrl),new Invalid_URI_Exception(exception,"NullPointer Exception"));
                        } else {
                                statusCode = ExceptionFault.GENERIC_ERROR.getCode();
                                logger.error(getErrorTypeWithURL(serverUrl),new GenericException(exception,"I/O Exception raised"));
                        }

                }


                return statusCode;
        }

        private String getErrorTypeWithURL (String url){

                final String errorType= "server.error Exception From Server Url: ";

                return errorType+url;
        }



}
