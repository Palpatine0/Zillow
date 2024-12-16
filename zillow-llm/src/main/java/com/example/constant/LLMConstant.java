package com.example.constant;


import io.github.cdimascio.dotenv.Dotenv;

public class LLMConstant {
    private static final Dotenv dotenv = Dotenv.configure().filename(".env").load();

    // Open AI
    public static final String OPENAI_API_KEY = dotenv.get("OPENAI_API_KEY");
    public static final int MAX_TOKEN = 36000;

    // Tavily
    public static final String TAVILI_API_KEY = dotenv.get("TAVILI_API_KEY");

    // Census
    public static final String CENSUS_DATA_API_KEY = dotenv.get("CENSUS_DATA_API_KEY");
    public static final String NEW_RESID_CONST_STAT_URL = "https://api.census.gov/data/timeseries/eits/resconst?get=data_type_code,category_code,time_slot_id,seasonally_adj,cell_value,error_data&time=2024&key=";
    public static final String HOMEOWNERSHIP_STAT_URL = "https://api.census.gov/data/timeseries/eits/hv.html?get=data_type_code,category_code,time_slot_id,seasonally_adj,cell_value,error_data&time=2024&key=";

}
