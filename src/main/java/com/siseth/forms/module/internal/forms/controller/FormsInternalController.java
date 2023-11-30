package com.siseth.forms.module.internal.forms.controller;


import com.siseth.forms.module.internal.forms.api.request.FormReqDTO;
import com.siseth.forms.module.internal.forms.api.response.FormResDTO;
import com.siseth.forms.module.internal.forms.api.response.UsersForVerificationDTO;
import com.siseth.forms.module.internal.forms.services.FormsInternalService;
import com.siseth.forms.module.internal.forms.services.FormsService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internal/forms")
@Tag(name = "Forms internal Controller", description = "Endpoints responsible for forms management")
public class FormsInternalController {
    private final FormsInternalService service;

    @GetMapping("/getUserInfo")
    @Operation(summary = "Get user info", description = "Get user info by id")
    public ResponseEntity<UsersForVerificationDTO> getForm(@RequestParam String id, @RequestParam String realm) {
        return ResponseEntity.ok(service.getUserInfo(id, realm));
    }
}
