package io.gomobi.quartz.api;

import io.gomobi.quartz.service.impl.PRViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PRViewController {

    private final PRViewService service;

    @GetMapping("/api/v1/pr/view")
    public ResponseEntity<Object> viewPR() {
        return ResponseEntity.ok(service.getPRView());
    }


}
