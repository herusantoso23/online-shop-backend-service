package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.dto.ResultPageDTO;
import com.herusantoso.tokonezia.dto.ShopDTO;
import com.herusantoso.tokonezia.service.ShopService;
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
public class ShopResource {

    @Autowired
    private ShopService shopService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shop")
    public ResponseEntity<ResultDTO> createShop(@Valid @RequestBody ShopDTO shopDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return shopService.create(shopDTO);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/shop/{id}")
    public ResponseEntity<ResultDTO> updateShop(@PathVariable String id,
                                                @Valid @RequestBody ShopDTO shopDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return shopService.update(id, shopDTO);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/shop/{id}")
    public ResponseEntity<ResultDTO> getDetailShop(@PathVariable String id) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return shopService.getDetail(id);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/shop")
    public ResponseEntity<ResultPageDTO> getAllShop(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                                        @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
                                                        @RequestParam(value = "sortBy", required = false) String sortBy,
                                                        @RequestParam(value = "direction", required = false) String direction) {
        Map<String, Object> pageMap = shopService.getAll(page, limit, sortBy, direction);
        return RequestHandler.constructListResult(pageMap);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/shop/{id}")
    public ResponseEntity<ResultDTO> deleteShop(@PathVariable String id) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return shopService.delete(id);
            }
        };
        return handler.getResult();
    }

}
