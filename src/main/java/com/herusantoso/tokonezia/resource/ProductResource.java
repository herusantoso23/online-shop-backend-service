package com.herusantoso.tokonezia.resource;

import com.herusantoso.tokonezia.dto.ResultDTO;
import com.herusantoso.tokonezia.dto.ResultPageDTO;
import com.herusantoso.tokonezia.dto.ProductDTO;
import com.herusantoso.tokonezia.service.ProductService;
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
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ResultDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return productService.create(productDTO);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/product/{id}")
    public ResponseEntity<ResultDTO> updateProduct(@PathVariable String id,
                                                @Valid @RequestBody ProductDTO productDTO) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return productService.update(id, productDTO);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/product/{id}")
    public ResponseEntity<ResultDTO> getDetailProduct(@PathVariable String id) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return productService.getDetail(id);
            }
        };
        return handler.getResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/product")
    public ResponseEntity<ResultPageDTO> getAllProduct(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                                        @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
                                                        @RequestParam(value = "sortBy", required = false) String sortBy,
                                                        @RequestParam(value = "direction", required = false) String direction) {
        Map<String, Object> pageMap = productService.getAll(page, limit, sortBy, direction);
        return RequestHandler.constructListResult(pageMap);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<ResultDTO> delete(@PathVariable String id) {
        RequestHandler handler = new RequestHandler() {
            @Override
            public Object processRequest() {
                return productService.delete(id);
            }
        };
        return handler.getResult();
    }

}
