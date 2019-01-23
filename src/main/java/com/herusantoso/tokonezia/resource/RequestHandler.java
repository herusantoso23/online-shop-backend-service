package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.ResultPageDTO;
import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.exception.TokoneziaException;
import com.herusantoso.tokonezia.util.Constants;
import com.herusantoso.tokonezia.util.RestUtil;
import com.herusantoso.tokonezia.util.StatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Map;

public abstract class RequestHandler {

    public ResponseEntity<ResultDTO> getResult() {
        ResultDTO result = new ResultDTO();
        try {
            Object obj = processRequest();
            if (obj != null) {
                result.setMessage(StatusCode.OK.name());
                result.setResult(obj);
            }else {
                result.setMessage(StatusCode.OK.name());
                result.setResult(null);
            }
        } catch (TokoneziaException e) {
            result.setMessage(e.getCode().name());
            result.setResult(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }


    public abstract Object processRequest();

    public static ResponseEntity<ResultPageDTO> constructListResult(Map<String, Object> pageMap) {
        ResultPageDTO result = new ResultPageDTO();
        try {
            Collection list = constructPageResult(pageMap, result);
            result.setResult(list);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }

    public static Collection constructPageResult(Map<String, Object> map, ResultPageDTO result) {
        if (map == null) {
            result.setPages("0");
            result.setElements("0");
            result.setMessage(StatusCode.DATA_NOT_FOUND.name());
            return null;
        } else {
            Collection vos = (Collection) map.get(Constants.PageParameter.LIST_DATA);
            result.setPages(String.valueOf(map.get(Constants.PageParameter.TOTAL_PAGES)));
            result.setElements(String.valueOf(map.get(Constants.PageParameter.TOTAL_ELEMENTS)));
            result.setMessage(StatusCode.OK.name());
            return vos;
        }
    }

}
