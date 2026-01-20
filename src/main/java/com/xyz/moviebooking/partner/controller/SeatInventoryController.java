package com.xyz.moviebooking.partner.controller;

import com.xyz.moviebooking.partner.dto.SeatInventoryRequest;
import com.xyz.moviebooking.partner.dto.SeatInventoryResponse;
import com.xyz.moviebooking.partner.service.SeatInventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners/{partnerId}/inventory")
public class SeatInventoryController {

    @Autowired
    private SeatInventoryService seatInventoryService;

    /**
     * B2B API: Theatre updates seat inventory for a show
     * PUT /api/partners/{partnerId}/inventory/shows/{showId}
     */
    @PutMapping("/shows/{showId}")
    public ResponseEntity<SeatInventoryResponse> updateSeatInventory(
            @PathVariable Long partnerId,
            @PathVariable Long showId,
            @Valid @RequestBody SeatInventoryRequest request) {
        SeatInventoryResponse response = seatInventoryService.updateSeatInventory(partnerId, showId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * B2B API: Get current seat inventory for a show
     * GET /api/partners/{partnerId}/inventory/shows/{showId}
     */
    @GetMapping("/shows/{showId}")
    public ResponseEntity<SeatInventoryResponse> getSeatInventory(
            @PathVariable Long partnerId,
            @PathVariable Long showId) {
        SeatInventoryResponse response = seatInventoryService.getSeatInventory(partnerId, showId);
        return ResponseEntity.ok(response);
    }

    /**
     * B2B API: Theatre can block/unblock specific seats
     * PATCH /api/partners/{partnerId}/inventory/shows/{showId}/seats/{seatId}/block
     */
    @PatchMapping("/shows/{showId}/seats/{seatId}/block")
    public ResponseEntity<Void> blockSeat(
            @PathVariable Long partnerId,
            @PathVariable Long showId,
            @PathVariable Long seatId,
            @RequestParam boolean blocked) {
        seatInventoryService.blockSeat(partnerId, showId, seatId, blocked);
        return ResponseEntity.ok().build();
    }
}