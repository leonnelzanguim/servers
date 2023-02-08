package com.probeaufgabe.servers.contollers;

import com.probeaufgabe.servers.models.Server;
import com.probeaufgabe.servers.models.ServerResponse;
import com.probeaufgabe.servers.services.ServiceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerServer {

    @Autowired
    ServiceServer serviceServer;

    @RequestMapping(value = "/server", method = RequestMethod.POST)
    public List<ServerResponse> prüfen(@RequestBody List<Server> server_url) throws URISyntaxException, IOException, InterruptedException {
        return serviceServer.prüfenServer(server_url);
    }
}
