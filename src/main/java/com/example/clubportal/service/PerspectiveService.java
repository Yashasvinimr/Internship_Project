//package com.example.clubportal.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class PerspectiveService {
//    static String apiKey = "AIzaSyCcA-WBlYHdRkOY-Fze-uLWtP48-nrwFeg";
//    private static final String API_URL = "https://commentanalyzer.googleapis.com/v1alpha1/comments:analyze?key="+apiKey;
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public boolean containsProfanity(String text) {
//        try {
//            System.out.println("Checking for profanity: " + text); // Debug log
//
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            JSONObject requestBody = new JSONObject();
//            requestBody.put("comment", new JSONObject().put("text", text));
//            requestBody.put("languages", new String[]{"en"});
//            requestBody.put("requestedAttributes", new JSONObject().put("TOXICITY", new JSONObject()));
//
//            HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
//            ResponseEntity<String> response = restTemplate.exchange(API_URL + apiKey, HttpMethod.POST, request, String.class);
//
//            System.out.println("Perspective API Response: " + response.getBody()); // Debug log
//
//            JSONObject responseBody = new JSONObject(response.getBody());
//            double toxicityScore = responseBody.getJSONObject("attributeScores")
//                    .getJSONObject("TOXICITY").getJSONObject("summaryScore").getDouble("value");
//
//            System.out.println("Toxicity Score: " + toxicityScore); // Debug log
//
//            return toxicityScore > 0.7; // Adjust threshold if needed
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false; // Fail-safe: Allow comments if API call fails
//        }
//    }
//
//}
