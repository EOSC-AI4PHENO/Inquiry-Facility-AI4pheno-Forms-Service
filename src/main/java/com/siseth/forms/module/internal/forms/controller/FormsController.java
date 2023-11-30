package com.siseth.forms.module.internal.forms.controller;


import com.siseth.forms.module.internal.forms.api.request.FormReqDTO;
import com.siseth.forms.module.internal.forms.api.response.FormResDTO;
import com.siseth.forms.module.internal.forms.api.response.UsersForVerificationDTO;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inquiry-facility/forms")
@Tag(name = "Forms Controller", description = "Endpoints responsible for forms management")
public class FormsController {
    private final FormsService service;

    @GetMapping("/readRegistrationFormDefinition")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get registration form definition", description = "Get registration form definition by id")
    public ResponseEntity<FormResDTO> getForm(@RequestParam String name) {
        return ResponseEntity.ok(service.getFormByType("REGISTRATION", name));
    }

    @PostMapping("/saveForm")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Save form", description = "Save form")
    public ResponseEntity<String> saveForm(@RequestBody FormReqDTO formReqDTO,
                                           @Parameter(hidden = true) @RequestHeader(required = false) String id,
                                           @Parameter(hidden = true) @RequestHeader(required = false) String token,
                                           @Parameter(hidden = true) @RequestHeader(required = false) String name,
                                           @Parameter(hidden = true) @RequestHeader(required = false) String realm) {
        return ResponseEntity.ok(service.saveForm(formReqDTO, id, realm));
    }

    @GetMapping("/getUsersForVerification")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get forms", description = "Get verification forms for all users")
    public ResponseEntity<List<UsersForVerificationDTO>> getUsersForVerification(@Parameter(hidden = true) @RequestHeader(required = false) String id,
                                                                                 @Parameter(hidden = true) @RequestHeader(required = false) String token,
                                                                                 @Parameter(hidden = true) @RequestHeader(required = false) String name,
                                                                                 @Parameter(hidden = true) @RequestHeader(required = false) String realm) {
        return ResponseEntity.ok(service.getUsersForVerification(realm));
    }

    @PutMapping("/acceptForm")
    @Hidden
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Accept form", description = "Accept verification form for the user")
    public ResponseEntity<String> acceptForm(@RequestParam String realm, @RequestParam String userId) {
        return ResponseEntity.ok(service.acceptForm(realm, userId));
    }


    @PutMapping("/rejectForm")
    @Hidden
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Reject form", description = "Reject verification form for the user")
    public ResponseEntity<String> rejectForm(@RequestParam String realm, @RequestParam String userId) {
        return ResponseEntity.ok(service.rejectForm(realm, userId));
    }

    @GetMapping("/status")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get status", description = "Get status forms for all users")
    public ResponseEntity<?> getStatus(@Parameter(hidden = true) @RequestHeader(required = false) String id,
                                                                                 @Parameter(hidden = true) @RequestHeader(required = false) String token,
                                                                                 @Parameter(hidden = true) @RequestHeader(required = false) String name,
                                                                                 @Parameter(hidden = true) @RequestHeader(required = false) String realm) {
        return ResponseEntity.ok(service.getStatus(id, realm));
    }

}
