package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.dto.ResultPageDTO;
import com.herusantoso.tokonezia.dto.PurchaseDTO;
import com.herusantoso.tokonezia.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class PurchaseResource {

    @Autowired
    private PurchaseService purchaseService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/purchase")
    public ResponseEntity<ResultDTO> createPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return purchaseService.create(purchaseDTO);
            }
        };
        return handler.getResult();
    }

}
