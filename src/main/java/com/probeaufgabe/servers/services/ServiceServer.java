package com.probeaufgabe.servers.services;

import com.probeaufgabe.servers.models.Server;
import com.probeaufgabe.servers.models.ServerResponse;
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
        public List<ServerResponse> prüfenServer(List<Server> servers) {

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

        private int checkUrl(String serverUrl, HttpClient client) throws URISyntaxException, IOException, InterruptedException {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(serverUrl))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                return response.statusCode();
        }

}
