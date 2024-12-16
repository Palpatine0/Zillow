package com.example.service;

import com.example.constant.LLMConstant;
import com.example.llm.DataSummaryAgent;
import com.example.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.json.JSONArray;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

@Service
public class CensusService {

    @Autowired
    private DataSummaryAgent dataSummaryAgent;

    /**
     * Get Annual New Residential Construction Statistics
     */
    public String getNewConstructionStat() {
        String response = HttpClientUtil.getInstance().sendHttpGet(LLMConstant.NEW_RESID_CONST_STAT_URL + LLMConstant.CENSUS_DATA_API_KEY);
        JSONArray jsonResponse = new JSONArray(response);
        List<JSONArray> filteredResults = new ArrayList<>();

        for (int i = 1; i < jsonResponse.length(); i++) {
            JSONArray row = jsonResponse.getJSONArray(i);

            String dataTypeCode = row.getString(0);
            String categoryCode = row.getString(1);
            String errorCode = row.getString(5);

            // Apply filter
            if ("TOTAL".equals(dataTypeCode) && "COMPLETIONS".equals(categoryCode) && "no".equals(errorCode)) {
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
     */
    @Cacheable(cacheNames = "llm", key = "'newConstructionStatValues'")
    public List<Double> getNewConstructionStatValues() {
        String response = HttpClientUtil.getInstance().sendHttpGet(LLMConstant.NEW_RESID_CONST_STAT_URL);
        JSONArray jsonResponse = new JSONArray(response);
        List<JSONArray> filteredResults = new ArrayList<>();

        for (int i = 1; i < jsonResponse.length(); i++) {
            JSONArray row = jsonResponse.getJSONArray(i);

            String dataTypeCode = row.getString(0);
            String categoryCode = row.getString(1);
            String errorCode = row.getString(5);

            // Apply filter
            if ("TOTAL".equals(dataTypeCode) && "COMPLETIONS".equals(categoryCode) && "no".equals(errorCode)) {
                filteredResults.add(row);
            }
        }

        // Sort the filtered results by time
        filteredResults.sort(Comparator.comparing(o -> o.getString(o.length() - 1)));

        // Extract only the numeric values from the filtered results
        List<Double> numericValues = new ArrayList<>();
        for (JSONArray result : filteredResults) {
            try {
                double cellValue = Double.parseDouble(result.getString(4));
                numericValues.add(cellValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return numericValues;
    }

    /**
     * Get Annual New Residential Construction Statistics Summary
     */
    @Cacheable(cacheNames = "llm", key = "'newConstructionStatSummary'")
    public String getNewConstructionStatSummary() {
        // Data introduction explaining the context
        String dataIntro = "The dataset provides monthly completion figures for new residential constructions in 2024. The columns include: " +
            "data_type_code, category_code, time_slot_id (ignore this), seasonally_adj (ignore this), cell_value, error_code (ignore this), and time.\n";

        // Retrieve raw data
        String constructionStat = this.getNewConstructionStat();

        // Prompt tailored to avoid questioning the data and instead focus on explanations
        String prompt = dataIntro + constructionStat + "\n\n" +
            "# Instructions for Summary:\n" +
            "1. Focus on interpreting the data provided. Identify key trends, patterns, or anomalies.\n" +
            "2. Explain what these data points may signify in the context of residential construction completions for 2024.\n" +
            "3. Avoid recommending improvements or suggesting enhancements to the dataset.\n" +
            "4. Keep the explanation clear and concise, emphasizing the meaning of the data points.";

        // Generate the summary using the agent
        return dataSummaryAgent.generateSummary(prompt);
    }


    /**
     * Get Housing Vacancies Statistics
     */
    public String getHousingVacanciesStat() {
        String response = HttpClientUtil.getInstance().sendHttpGet(LLMConstant.HOMEOWNERSHIP_STAT_URL + LLMConstant.CENSUS_DATA_API_KEY);
        JSONArray jsonResponse = new JSONArray(response);
        List<JSONArray> filteredResults = new ArrayList<>();

        for (int i = 1; i < jsonResponse.length(); i++) {
            JSONArray row = jsonResponse.getJSONArray(i);

            String dataTypeCode = row.getString(0);
            String categoryCode = row.getString(1);
            String errorCode = row.getString(5);

            // Apply filter
            if ("HVR".equals(dataTypeCode) && "no".equals(errorCode)) {
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
     * Get Housing Vacancy Rate Summary for the Most Recent Quarter
     */
    @Cacheable(cacheNames = "llm", key = "'housingVacanciesStatSummary'")
    public String getHousingVacanciesStatSummary() {
        // Data introduction explaining the context
        String dataIntro = "The dataset provides Housing Vacancy Rates (HVR) with quarterly values. " +
            "The columns include: data_type_code, category_code, time_slot_id, seasonally_adj, cell_value (vacancy rate), error_code (ignore this), and time.\n";

        // Retrieve raw data
        String housingVacancyStat = this.getHousingVacanciesStat();

        // Prompt tailored to analyze only the most recent quarter
        String prompt = dataIntro + housingVacancyStat + "\n\n" +
            "# Instructions for Summary:\n" +
            "1. Focus only on the most recent quarter's data (latest time values) in one clear paragraph and without using bullet points or section headers..\n" +
            "2. Summarize trends, peaks, and notable points from the most recent quarter in one concise paragraph.\n" +
            "3. Avoid mentioning previous quarters or unnecessary details.\n" +
            "4. Keep the output **under 30 words** for a clean and minimal presentation.";

        // Generate the summary using the agent
        return dataSummaryAgent.generateSummary(prompt);
    }

    /**
     * Get Homeownership Statistics
     */
    public String getHomeownershipStat() {
        String response = HttpClientUtil.getInstance().sendHttpGet(LLMConstant.HOMEOWNERSHIP_STAT_URL + LLMConstant.CENSUS_DATA_API_KEY);
        JSONArray jsonResponse = new JSONArray(response);
        List<JSONArray> filteredResults = new ArrayList<>();

        for (int i = 1; i < jsonResponse.length(); i++) {
            JSONArray row = jsonResponse.getJSONArray(i);

            String dataTypeCode = row.getString(0);
            String categoryCode = row.getString(1);
            String errorCode = row.getString(5);

            // Apply filter
            if ("HOR".equals(dataTypeCode) && "no".equals(errorCode)) {
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
     * Get Homeownership Rate Summary for the Most Recent Quarter
     */
    @Cacheable(cacheNames = "llm", key = "'homeownershipStatSummary'")
    public String getHomeownershipStatSummary() {
        // Data introduction explaining the context
        String dataIntro = "The dataset provides Homeownership Rate (HOR) with quarterly values. Showcasing with percentages of housing units that are owner-occupied. Example: 69.4 indicates 69.4% homeownership for a given quarter.\n " +
            "The columns include: data_type_code, category_code, time_slot_id, seasonally_adj, cell_value (homeownership rate), error_code (ignore this), and time.\n";

        // Retrieve raw data
        String homeownershipStat = this.getHomeownershipStat();

        // Prompt tailored to analyze only the most recent quarter
        String prompt = dataIntro + homeownershipStat + "\n\n" +
            "# Instructions for Summary:\n" +
            "1. Focus only on the most recent quarter's data (latest time values) in one clear paragraph and without using bullet points or section headers..\n" +
            "2. Summarize trends, peaks, and notable points from the most recent quarter in one concise paragraph.\n" +
            "3. Avoid mentioning previous quarters or unnecessary details.\n" +
            "4. Keep the output **under 30 words** for a clean and minimal presentation.";

        // Generate the summary using the agent
        return dataSummaryAgent.generateSummary(prompt);
    }

    /**
     * Get Estimated Rented Units
     */
    public String getEstimatedRentedUnitsStat() {
        String response = HttpClientUtil.getInstance().sendHttpGet(LLMConstant.HOMEOWNERSHIP_STAT_URL + LLMConstant.CENSUS_DATA_API_KEY);
        JSONArray jsonResponse = new JSONArray(response);
        List<JSONArray> filteredResults = new ArrayList<>();

        for (int i = 1; i < jsonResponse.length(); i++) {
            JSONArray row = jsonResponse.getJSONArray(i);

            String dataTypeCode = row.getString(0);
            String categoryCode = row.getString(1);
            String errorCode = row.getString(5);

            // Apply filter
            if ("RENT".equals(dataTypeCode) && "no".equals(errorCode)) {
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
     * Get Estimated Rented Units Summary for the Most Recent Quarter
     */
    @Cacheable(cacheNames = "llm", key = "'estimatedRentedUnitsStatSummary'")
    public String getEstimatedRentedUnitsStatSummary() {
        // Data introduction explaining the context
        String dataIntro = "The dataset provides estimates of rented housing units (RENT) in thousands. Example: 393 indicates there might be 393,000 of estimated rented units for a given quarter.\n " +
            "The columns include: data_type_code, category_code, time_slot_id, seasonally_adj, cell_value (rented housing units), error_code (ignore this), and time.\n";

        // Retrieve raw data
        String estimatedRentedUnitsStat = this.getEstimatedRentedUnitsStat();

        // Prompt tailored to analyze only the most recent quarter
        String prompt = dataIntro + estimatedRentedUnitsStat + "\n\n" +
            "# Instructions for Summary:\n" +
            "1. Focus only on the most recent quarter's data (latest time values) in one clear paragraph and without using bullet points or section headers..\n" +
            "2. Summarize trends, peaks, and notable points from the most recent quarter in one concise paragraph.\n" +
            "3. Avoid mentioning previous quarters or unnecessary details.\n" +
            "4. Keep the output **under 30 words** for a clean and minimal presentation.";

        // Generate the summary using the agent
        return dataSummaryAgent.generateSummary(prompt);
    }
}