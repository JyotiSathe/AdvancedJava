package com.siemens.view.java9to21.java11;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HttpClientAsyncDemo {

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
                .thenApply(HttpClientAsyncDemo::parseJsonArray)
                .thenAccept(jsonArray -> {
                    if (jsonArray != null) {
                        Stream<Object> jsonStream = IntStream
                                .range(0, jsonArray.length())
                                .mapToObj(jsonArray::get);
                        jsonStream.forEach(obj -> {
                            final JSONObject jsonObject = (JSONObject) obj;
                            if (!jsonObject.isNull("name")) {
                                System.out.println(jsonObject.get("name") + "," + jsonObject.get("username"));
                            }
                        });
                    }
                })
                .exceptionally(ex -> {
                    System.out.println(ex.getMessage());
                    return null;
                })
                .join();
    }

    public static JSONArray parseJsonArray(String responseBody) {
        try {
            return new JSONArray(responseBody);
        } catch (JSONException e) {
            return null;
        }
    }
}
