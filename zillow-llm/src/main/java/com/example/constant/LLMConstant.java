package com.example.constant;


import io.github.cdimascio.dotenv.Dotenv;

public class LLMConstant {
    private static final Dotenv dotenv = Dotenv.configure().filename(".env").load();

    public static final String OPENAI_API_KEY = dotenv.get("OPENAI_API_KEY");
    public static final int CHAT_MEMORY = 20;

}
