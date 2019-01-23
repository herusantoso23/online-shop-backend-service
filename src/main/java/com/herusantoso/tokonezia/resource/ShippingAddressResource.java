package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.dto.ResultPageDTO;
import com.herusantoso.tokonezia.dto.ShippingAddressDTO;
import com.herusantoso.tokonezia.service.ShippingAddressService;
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
public class ShippingAddressResource {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shipping-address")
    public ResponseEntity<ResultDTO> createShippingAddress(@Valid @RequestBody ShippingAddressDTO shippingAddressDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                shippingAddressService.create(shippingAddressDTO);
                return Boolean.TRUE;
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/shipping-address/{id}")
    public ResponseEntity<ResultDTO> updateShippingAddress(@PathVariable String id,
                                                @Valid @RequestBody ShippingAddressDTO shippingAddressDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                shippingAddressService.update(id, shippingAddressDTO);
                return Boolean.TRUE;
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/shipping-address/{id}")
    public ResponseEntity<ResultDTO> getDetailShippingAddress(@PathVariable String id) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return shippingAddressService.getDetail(id);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/shipping-address")
    public ResponseEntity<ResultPageDTO> getAllShippingAddress(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                                        @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
                                                        @RequestParam(value = "sortBy", required = false) String sortBy,
                                                        @RequestParam(value = "direction", required = false) String direction) {
        Map<String, Object> pageMap = shippingAddressService.getAll(page, limit, sortBy, direction);
        return RequestHandler.constructListResult(pageMap);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/shipping-address/{id}")
    public ResponseEntity<ResultDTO> deleteShippingAddress(@PathVariable String id) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return shippingAddressService.delete(id);
            }
        };
        return handler.getResult();
    }

}
