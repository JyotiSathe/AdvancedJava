package com.siemens.view.java9to21.java11;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.dto.UserDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HttpClientAsyncDemoV1 {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .build();

        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture =
                httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        httpResponseCompletableFuture
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    if (responseBody != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            List<UserDTO> userDTOS = mapper.readValue(responseBody, new TypeReference<>() {
                            });
                            if (userDTOS != null && !userDTOS.isEmpty()) {
                                for (UserDTO user : userDTOS) {
                                    System.out.println(user.getName() + " " + user.getUsername());
                                }
                            }
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
                .exceptionally(ex -> {
                    System.out.println(ex.getMessage());
                    return null;
                })
                .join();
    }
}
