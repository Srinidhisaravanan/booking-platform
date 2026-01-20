package com.xyz.moviebooking.partner.controller;

import com.xyz.moviebooking.partner.dto.PartnerRegistrationRequest;
import com.xyz.moviebooking.partner.dto.PartnerResponse;
import com.xyz.moviebooking.partner.service.PartnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    /**
     * B2B API: Theatre partner registration/onboarding
     * POST /api/partners/register
     */
    @PostMapping("/register")
    public ResponseEntity<PartnerResponse> registerPartner(@Valid @RequestBody PartnerRegistrationRequest request) {
        PartnerResponse response = partnerService.registerPartner(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * B2B API: Get partner profile
     * GET /api/partners/{partnerId}
     */
    @GetMapping("/{partnerId}")
    public ResponseEntity<PartnerResponse> getPartner(@PathVariable Long partnerId) {
        PartnerResponse response = partnerService.getPartner(partnerId);
        return ResponseEntity.ok(response);
    }

    /**
     * B2B API: Update partner profile
     * PUT /api/partners/{partnerId}
     */
    @PutMapping("/{partnerId}")
    public ResponseEntity<PartnerResponse> updatePartner(
            @PathVariable Long partnerId,
            @Valid @RequestBody PartnerRegistrationRequest request) {
        PartnerResponse response = partnerService.updatePartner(partnerId, request);
        return ResponseEntity.ok(response);
    }
}