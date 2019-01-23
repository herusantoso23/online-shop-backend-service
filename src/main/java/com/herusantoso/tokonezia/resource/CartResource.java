package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.dto.ResultPageDTO;
import com.herusantoso.tokonezia.dto.CartDTO;
import com.herusantoso.tokonezia.service.CartService;
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
public class CartResource {

    @Autowired
    private CartService cartService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart")
    public ResponseEntity<ResultDTO> addToCart(@Valid @RequestBody CartDTO cartDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return cartService.addToCart(cartDTO);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public ResponseEntity<ResultPageDTO> getAllCart(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                                        @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
                                                        @RequestParam(value = "sortBy", required = false) String sortBy,
                                                        @RequestParam(value = "direction", required = false) String direction) {
        Map<String, Object> pageMap = cartService.getAll(page, limit, sortBy, direction);
        return RequestHandler.constructListResult(pageMap);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<ResultDTO> deleteCart(@PathVariable String id) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return cartService.delete(id);
            }
        };
        return handler.getResult();
    }

}
