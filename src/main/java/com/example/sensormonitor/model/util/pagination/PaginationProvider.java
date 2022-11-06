package com.example.sensormonitor.model.util.pagination;

/**
 * Provides parameters of pagination
 */
public class PaginationProvider {

    private PaginationProvider() {
    }

    /**
     * Consider the number of the position in the list of results that
     * a page starts from depend on provided page and limit of items on the page.
     * @param page - the current page number
     * @param limit - the number of items on the single page
     * @return - the number of the position in the list of all results that the page starts from
     */
    public static int getStartPosition(int page, int limit) {
        return (page - 1) * limit;
    }

    /**
     * Consider pages number according to provided limit and number of items
     * @param limit - the number of items on the single page
     * @param itemsNumber - the number of all items
     * @return - the number of pages
     */
    public static long getPagesNumber(int limit, long itemsNumber) {
        long pagesNumber;

        if(itemsNumber%limit == 0) {
            pagesNumber = itemsNumber / limit;
        } else {
            pagesNumber = (itemsNumber / limit) + 1;
        }

        return pagesNumber;
    }
}