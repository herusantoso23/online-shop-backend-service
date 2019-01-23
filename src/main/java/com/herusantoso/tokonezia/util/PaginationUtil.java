package com.herusantoso.tokonezia.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {

    public static Sort.Direction getSortBy(String sortBy) {
        if (sortBy.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        } else {
            return Sort.Direction.DESC;
        }
    }

    public static Map<String, Object> constructMapReturn(Collection voList, long totalElements, int totalPages) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(Constants.PageParameter.LIST_DATA, voList);
        map.put(Constants.PageParameter.TOTAL_ELEMENTS, totalElements);
        map.put(Constants.PageParameter.TOTAL_PAGES, totalPages);

        return map;
    }
}
