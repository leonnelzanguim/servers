package com.probeaufgabe.servers.contollers;

import com.probeaufgabe.servers.models.Server;
import com.probeaufgabe.servers.models.ServerResponse;
import com.probeaufgabe.servers.services.ServiceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerServer {

    @Autowired
    ServiceServer serviceServer;


    @RequestMapping(value = "/server", method = RequestMethod.POST)
    public List<ServerResponse> serversCheck(@RequestBody List<Server> server_url) {
        return serviceServer.checkServersList(server_url);
    }
}
