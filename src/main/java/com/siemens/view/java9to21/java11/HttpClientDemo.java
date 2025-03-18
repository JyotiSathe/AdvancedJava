package com.siemens.view.java9to21.java11;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientDemo {

    public static void main(String[] args) {
        // step 1: create http client
        try (HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .build()) {

            // step 2: http request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                    .build();

            // step 3: http response
            HttpResponse<String> httpResponse;
            HttpHeaders headers;
            JSONArray jsonArray;

            try {
                httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                headers = httpResponse.headers();
                headers.map().forEach((key, value) -> System.out.println(key + " : " + value));

                // step 4: parse response
                jsonArray = new JSONArray(httpResponse.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println("name: " + jsonObject.getString("name"));
                    System.out.println("street: " + jsonObject.getJSONObject("address").getString("street"));
                }
            } catch (Exception ignored) {

            }
        }
    }
}
