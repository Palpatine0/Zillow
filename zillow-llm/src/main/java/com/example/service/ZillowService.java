package com.example.service;

import com.example.dao.ItemDao;
import com.example.dao.CommentDao;
import com.example.dao.PreferenceDao;
import com.example.entity.Item;
import com.example.entity.Comment;
import com.example.entity.Preference;
import com.example.llm.UserPreferenceAgent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZillowService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PreferenceDao preferenceDao;

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @Autowired
    private UserPreferenceAgent userPreferenceAgent;

    /**
     * Searches items based on a natural language query.
     *
     * @param query the natural language query
     * @return a list of items matching the criteria
     */
    public List<Item> searchItems(String query) {
        Map<String, Object> criteria = parseQueryToCriteria(query, "item");
        return itemDao.findItemsByCriteria(criteria);
    }

    /**
     * Searches comments based on a natural language query.
     *
     * @param query the natural language query
     * @return a list of comments matching the criteria
     */
    public List<Comment> searchComments(String query) {
        Map<String, Object> criteria = parseQueryToCriteria(query, "comment");
        return commentDao.findCommentsByCriteria(criteria);
    }

    /**
     * Parses a natural language query into MongoDB criteria.
     *
     * @param query      the natural language query
     * @param entityType the type of entity ("item" or "comment")
     * @return a map representing the criteria
     */
    private Map<String, Object> parseQueryToCriteria(String query, String entityType) {
        // System.out.println("--------------------------------------------------------");
        // System.out.println("Query to temp LLM:\n" + query);
        // System.out.println("--------------------------------------------------------");
        String prompt = buildPrompt(query, entityType);


        // System.out.println("--------------------------------------------------------");
        // System.out.println("Prompt sent to LLM:\n" + prompt);
        // System.out.println("--------------------------------------------------------");

        // Generate the criteria using the LLM
        String jsonCriteria = chatLanguageModel.generate(prompt);
        // System.out.println("--------------------------------------------------------");
        // System.out.println("LLM response:\n" + jsonCriteria);
        // System.out.println("--------------------------------------------------------");

        // Convert JSON string to Map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonCriteria, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * Builds the LLM prompt based on the entity type.
     *
     * @param query      the natural language query
     * @param entityType the type of entity ("item" or "comment")
     * @return the constructed prompt
     */
    private String buildPrompt(String query, String entityType) {
        String validFields;
        String examples;
        String constraints = "";

        switch (entityType.toLowerCase()) {
            case "item":
                validFields = "id, title, description, sales, recommendation, weight, price, city, rentType, " +
                    "info (area, orientation, baths, level, style, type, beds, years), imgs, buytime, isRented";
                constraints = "- When the user mentions \"type\", map it to \"info.type\".\n" +
                    "- The possible values for \"info.type\" are: \"Single Family Residence\", \"Condominium\".\n" +
                    "- The possible values for \"rentType\" are: \"Whole Rental\", \"Room Rental\", \"Shared Rental\".\n" +
                    "For nested fields in 'info', represent them as 'info.fieldName'.\n";
                examples = "Query: \"Find single family residences in Dallas\"\n" +
                    "Output: { \"city\": \"Dallas\", \"info.type\": \"Single Family Residence\" }\n" +
                    "Query: \"Show me condominiums for shared rental in Dallas under $500,000\"\n" +
                    "Output: { \"city\": \"Dallas\", \"price\": { \"$lt\": 500000 }, \"info.type\": \"Condominium\", \"rentType\": \"Shared Rental\" }\n" +
                    "Query: \"Dallas properties\"\n" +
                    "Output: { \"city\": \"Dallas\" }";
                break;

            case "comment":
                validFields = "username, comment, itemId";
                examples = "Query: \"Find all comments for item ID 12345\"\n" +
                    "Output: { \"itemId\": \"12345\" }\n" +
                    "Query: \"Show me comments from user 'john_doe'\"\n" +
                    "Output: { \"username\": \"john_doe\" }\n" +
                    "Query: \"What did people say about item ID 67890?\"\n" +
                    "Output: { \"itemId\": \"67890\" }";
                break;

            default:
                throw new IllegalArgumentException("Unsupported entity type: " + entityType);
        }

        return String.format(
            "As a virtual assistant, convert the following natural language query into a JSON object representing search criteria for MongoDB.\n" +
                "Only use the fields from the %s class: %s.\n" +
                "%s" +
                "Do not include any other fields.\n" +
                "Ensure the criteria match the query exactly.\n" +
                "Here are some examples:\n" +
                "%s\n" +
                "Query: \"%s\"\n" +
                "Output only the JSON object.",
            entityType, validFields, constraints, examples, query
        );
    }


    public void managePreferences(String userId, String query) {
        try {
            // Retrieve existing preference
            Preference existingPreference = preferenceDao.findPreferenceByUserId(userId);
            String existingPreferenceDesc = null;
            if (existingPreference != null) {
                existingPreferenceDesc = existingPreference.getDescription();
            }

            // Build JSON payload manually
            HashMap<String, String> jsonPayloadMap = new HashMap<>();
            jsonPayloadMap.put("existingPreference", existingPreferenceDesc);
            jsonPayloadMap.put("query", query);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(jsonPayloadMap);

            // Call the agent
            String updatedPreference = userPreferenceAgent.analyzePreferences(jsonPayload);

            // Handle preference updates
            if (existingPreference == null && updatedPreference != null) {
                Preference newPreference = new Preference();
                newPreference.setUserId(userId);
                newPreference.setDescription(updatedPreference);
                preferenceDao.savePreference(newPreference);
            } else if (updatedPreference != null && !updatedPreference.equals(existingPreferenceDesc)) {
                preferenceDao.updatePreferenceByUserId(userId, updatedPreference);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}