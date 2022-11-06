package com.example.sensormonitor.model.dto;

public class PageDtoFactory {
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_LIMIT = 4;

    private PageDtoFactory() {
    }

    public static PageDto createPageDto(int page, int limit) {
        int currentPage = page != 0 ? page : DEFAULT_PAGE_NUMBER;
        int currentLimit = limit != 0 ? limit : DEFAULT_LIMIT;

        return new PageDto(currentLimit, currentPage);
    }
}