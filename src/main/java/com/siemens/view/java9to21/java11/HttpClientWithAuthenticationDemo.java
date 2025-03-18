package com.siemens.view.java9to21.java11;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientWithAuthenticationDemo {
    public static void main(String[] args) {
        // step 1: create http client
        try (HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .authenticator(authenticator)
                .build()) {

            // step 2: http request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://httpbin.org/basic-auth/admin/admin"))
                    .build();

            // HttpRequest httpRequest = HttpRequest.newBuilder()
            //        .GET()
            //        .uri(URI.create("https://httpbin.org/basic-auth/user/pass"))
            //        .header("Authorization", getBasicAuthenticationHeader("user", "pass"))
            //        .build();

            // step 3: http response
            HttpResponse<String> httpResponse;
            HttpHeaders headers;

            try {
                httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                headers = httpResponse.headers();
                headers.map().forEach((key, value) -> System.out.println(key + " : " + value));

                System.out.println(httpResponse.body());
            } catch (Exception ignored) {

            }
        }
    }

//    private static String getBasicAuthenticationHeader(String username, String password) {
//        String valueToEncode = username + ":" + password;
//        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
//    }

    static Authenticator authenticator = new Authenticator() {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("admin", "admin".toCharArray());
        }
    };
}
