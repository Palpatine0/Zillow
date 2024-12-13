package com.example.service;
import com.example.constant.LLMConstant;
import com.example.util.HttpClientUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.json.JSONArray;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

@Service
public class CensusService {

    /**
     * Get Annual New Residential Construction Statistics
     * @return
     */
    public String getConstructionStat() {
        String response = HttpClientUtil.getInstance().sendHttpGet(LLMConstant.NEW_RESID_CONST_STAT_URL);
        JSONArray jsonResponse = new JSONArray(response);
        List<JSONArray> filteredResults = new ArrayList<>();

        for (int i = 1; i < jsonResponse.length(); i++) {
            JSONArray row = jsonResponse.getJSONArray(i);

            String dataTypeCode = row.getString(0);
            String categoryCode = row.getString(1);

            // Apply filter
            if ("TOTAL".equals(dataTypeCode) && "COMPLETIONS".equals(categoryCode)) {
                filteredResults.add(row);
            }
        }
        filteredResults.sort(Comparator.comparing(o -> o.getString(o.length() - 1)));
        StringBuilder resultBuilder = new StringBuilder();
        for (JSONArray result : filteredResults) {
            resultBuilder.append(result.toString()).append("\n");
        }

        return resultBuilder.toString();
    }

    /**
     * Get Annual New Residential Construction Statistics Values
     * @return
     */
    @Cacheable(cacheNames = "llm", key = "'constructionStatValues'")
    public List<Double> getConstructionStatValues() {
        String response = HttpClientUtil.getInstance().sendHttpGet(LLMConstant.NEW_RESID_CONST_STAT_URL);
        JSONArray jsonResponse = new JSONArray(response);
        List<JSONArray> filteredResults = new ArrayList<>();

        for (int i = 1; i < jsonResponse.length(); i++) {
            JSONArray row = jsonResponse.getJSONArray(i);

            String dataTypeCode = row.getString(0);
            String categoryCode = row.getString(1);

            // Apply filter
            if ("TOTAL".equals(dataTypeCode) && "COMPLETIONS".equals(categoryCode)) {
                filteredResults.add(row);
            }
        }

        // Sort the filtered results by time
        filteredResults.sort(Comparator.comparing(o -> o.getString(o.length() - 1)));

        // Extract only the numeric values from the filtered results
        List<Double> numericValues = new ArrayList<>();
        for (JSONArray result : filteredResults) {
            try {
                double cellValue = Double.parseDouble(result.getString(4)); // Assuming `cell_value` is at index 4
                numericValues.add(cellValue);
            } catch (NumberFormatException e) {
                // Handle cases where cell_value is not a valid number
                e.printStackTrace();
            }
        }

        return numericValues;
    }

}