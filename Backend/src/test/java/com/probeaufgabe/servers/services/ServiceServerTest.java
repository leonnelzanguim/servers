package com.probeaufgabe.servers.services;

import com.probeaufgabe.servers.models.Server;
import com.probeaufgabe.servers.models.ServerResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

//To Not load the global Spring Boot application
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ServiceServerTest {

    Server server;

    ServiceServer serviceServer;


    @BeforeEach
    void setUp() {
        server = Server.builder()
                .url("")
                .build();
        serviceServer = new ServiceServer();
    }

    //test with Parameters (The list of Urls)

    @ParameterizedTest
    @MethodSource("validServerListURLs")
    void validURLsReturnGoodCode( Consumer<Server> setValidURLs){
        setValidURLs.accept(server);
        List<ServerResponse> serverResponses = serviceServer.checkServersList(Collections.singletonList(server));
        Assertions.assertThat(serverResponses.get(0).getCode()).isEqualTo(200);
    }

    @ParameterizedTest
    @MethodSource("inValidServerListURLs")
    void invalidURLsReturnCustomErrorCode( Consumer<Server> setInvalidURLs){
        setInvalidURLs.accept(server);
        List<ServerResponse> serverResponses = serviceServer.checkServersList(Collections.singletonList(server));
        Assertions.assertThat(serverResponses.get(0).getCode()).isEqualTo(404);
    }

    //We define a list of invalid URLs
    private static Stream<Arguments> inValidServerListURLs(){
        List<Consumer<Server>> setInvalidURLs = List.of(
                server -> server.setUrl("google.com"),
                server -> server.setUrl("file:///facebook.com"),
                server -> server.setUrl("https://www. youtube.com")
        );
        return setInvalidURLs.stream().map(Arguments::of);
    }

    //We define a list of valid URLs
    private static Stream<Arguments> validServerListURLs(){
        List<Consumer<Server>> setInvalidURLs = List.of(
                server -> server.setUrl("http://ping.eu"),
                server -> server.setUrl("https://google.com")
        );
        return setInvalidURLs.stream().map(Arguments::of);
    }
}
