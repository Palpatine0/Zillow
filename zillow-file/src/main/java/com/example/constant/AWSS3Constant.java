package com.example.constant;


import com.amazonaws.regions.Regions;
import io.github.cdimascio.dotenv.Dotenv;

public class AWSS3Constant {
    private static final Dotenv dotenv = Dotenv.configure().filename(".env").load();
    public static final Regions REGION = Regions.US_EAST_1;
    public static final String ACCESS_KEY = dotenv.get("AWS_S3_ACCESS_KEY");
    public static final String SECRET_KEY = dotenv.get("AWS_S3_SECRET_KEY");
    public static final String BUCKET_NAME = "percival-s3-zillow";
}
