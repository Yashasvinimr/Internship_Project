//package com.example.clubportal.service;
//import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;
//import com.google.cloud.language.v1.*;
//import com.google.cloud.language.v1.Document.Type;
//import org.springframework.stereotype.Service;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class ContentModerationService {
//
//    public boolean isContentAppropriate(String text) throws IOException {
//        try (LanguageServiceClient language = LanguageServiceClient.create()) {
//            Document doc = Document.newBuilder()
//                    .setContent(text)
//                    .setType(Type.PLAIN_TEXT)
//                    .build();
//
//            AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(doc);
//            List<Entity> entities = response.getEntitiesList(); // Fix applied
//
//            for (Entity entity : entities) {
//                if (entity.getSentiment().getMagnitude() > 0.5 && entity.getSentiment().getScore() < -0.3) {
//                    // If sentiment score is negative, content might be inappropriate
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//}
