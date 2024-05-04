package com.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QueryParamsToJSON {

    public static String convertQueryParamsToJson(String queryParams) {
        try {
            // Split the query parameters
            String[] pairs = queryParams.split("&");
            Map<String, Object> jsonMap = new HashMap<>();

            // Iterate over pairs and populate the JSON map
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String key = keyValue[0];
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1]) : "";

                // Special handling for info object
                if (key.equals("orientation") || key.equals("level") || key.equals("style") ||
                        key.equals("type") || key.equals("years")) {
                    if (!jsonMap.containsKey("info")) {
                        jsonMap.put("info", new HashMap<String, String>());
                    }
                    ((Map<String, String>) jsonMap.get("info")).put(key, value);
                } else {
                    jsonMap.put(key, value);
                }
            }

            // Convert map to JSON string
            return new ObjectMapper().writeValueAsString(jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Example usage
        String queryParams = "title=TT&sales=&recommendation=false&weight=0&price=1&city=Cape+Coral" +
                "&rentType=Whole+Rental&houseType=1&orientation=Fully+Furnished&level=11&style=" +
                "&type=111&years=111&imgs=&buytime=&isRented=false&link=&img=&houseType4Search=";

        String json = convertQueryParamsToJson(queryParams);
        System.out.println(json);
    }
}
