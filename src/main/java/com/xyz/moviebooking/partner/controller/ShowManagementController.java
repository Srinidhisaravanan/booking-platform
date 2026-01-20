package com.xyz.moviebooking.partner.controller;

import com.xyz.moviebooking.movie.dto.ShowResponse;
import com.xyz.moviebooking.partner.dto.ShowManagementRequest;
import com.xyz.moviebooking.partner.service.ShowManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners/{partnerId}/shows")
public class ShowManagementController {

    @Autowired
    private ShowManagementService showManagementService;

    /**
     * B2B API: Theatre creates a new show
     * POST /api/partners/{partnerId}/shows
     */
    @PostMapping
    public ResponseEntity<ShowResponse> createShow(
            @PathVariable Long partnerId,
            @Valid @RequestBody ShowManagementRequest request) {
        ShowResponse response = showManagementService.createShow(partnerId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * B2B API: Theatre updates an existing show
     * PUT /api/partners/{partnerId}/shows/{showId}
     */
    @PutMapping("/{showId}")
    public ResponseEntity<ShowResponse> updateShow(
            @PathVariable Long partnerId,
            @PathVariable Long showId,
            @Valid @RequestBody ShowManagementRequest request) {
        ShowResponse response = showManagementService.updateShow(partnerId, showId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * B2B API: Theatre deletes a show
     * DELETE /api/partners/{partnerId}/shows/{showId}
     */
    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteShow(
            @PathVariable Long partnerId,
            @PathVariable Long showId) {
        showManagementService.deleteShow(partnerId, showId);
        return ResponseEntity.noContent().build();
    }

    /**
     * B2B API: Get all shows managed by this theatre partner
     * GET /api/partners/{partnerId}/shows
     */
    @GetMapping
    public ResponseEntity<List<ShowResponse>> getPartnerShows(@PathVariable Long partnerId) {
        List<ShowResponse> shows = showManagementService.getPartnerShows(partnerId);
        return ResponseEntity.ok(shows);
    }
}