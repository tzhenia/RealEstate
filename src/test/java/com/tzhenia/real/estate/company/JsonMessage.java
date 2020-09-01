package com.tzhenia.real.estate.company;

import lombok.Getter;

@Getter
public enum JsonMessage {
    AGENT_SAVE("AgentSave"),
    AGENT_UPDATE("AgentUpdate"),

    DEAL_SAVE("DealSave"),

    REAL_ESTATE_SAVE("RealEstateSave"),
    REAL_ESTATE_UPDATE("RealEstateUpdate");

    private static final String FILE_FORMAT = ".json";
    private final String jsonName;

    JsonMessage(String jsonName) {
        this.jsonName = jsonName + FILE_FORMAT;
    }
}