package com.tzhenia.real.estate.company;

import java.util.Optional;

public enum ServerRoute {
    AGENT_API("agent/"),
    AGENT_ID_API("agent/{param}"),

    DEAL_API("deal/"),
    DEAL_ID_API("deal/{param}"),
    DEAL_BY_AGENT_API("deal/agent/{param}"),
    DEAL_BY_TOP_SELLERS_API("deal/agent/top/{param}"),

    REAL_ESTATE_API("realEstate/"),
    REAL_ESTATE_ID_API("realEstate/{param}"),
    REAL_ESTATE_SEARCH_BY_STATUS_API("realEstate/status/{param}");

    public static final String BASE_URL = Optional.ofNullable(System.getenv("SERVER_URL")).orElse("http://localhost:8080");
    private final String path;

    ServerRoute(String path) {
        this.path = path;
    }

    public String getRoute() {
        return String.join("/", BASE_URL, "api", path);
    }
}