package com.xyz.moviebooking.partner.service;

import com.xyz.moviebooking.partner.dto.PartnerRegistrationRequest;
import com.xyz.moviebooking.partner.dto.PartnerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PartnerService {

    /**
     * B2B Service: Register new theatre partner
     * Handles KYC validation, contract setup, API key generation
     */
    public PartnerResponse registerPartner(PartnerRegistrationRequest request) {
        // 1. Validate business registration (external KYC service integration)
        validateBusinessRegistration(request.getBusinessRegistrationNumber());
        
        // 2. Create partner entity (simplified - would use repository)
        PartnerResponse response = new PartnerResponse();
        response.setId(generatePartnerId());
        response.setCompanyName(request.getCompanyName());
        response.setContactEmail(request.getContactEmail());
        response.setContactPhone(request.getContactPhone());
        response.setBusinessRegistrationNumber(request.getBusinessRegistrationNumber());
        response.setPrimaryContactName(request.getPrimaryContactName());
        response.setBusinessAddress(request.getBusinessAddress());
        response.setCity(request.getCity());
        response.setState(request.getState());
        response.setCountry(request.getCountry());
        response.setStatus("UNDER_REVIEW"); // KYC pending
        response.setRegistrationDate(LocalDateTime.now());
        response.setApiKey(generateApiKey());
        response.setTotalTheatres(0);
        response.setActiveShows(0);
        
        // 3. Send onboarding email and documentation
        sendOnboardingEmail(response);
        
        return response;
    }

    @Transactional(readOnly = true)
    public PartnerResponse getPartner(Long partnerId) {
        // Fetch partner details (would use repository)
        return createSamplePartner(partnerId);
    }

    public PartnerResponse updatePartner(Long partnerId, PartnerRegistrationRequest request) {
        // Update partner profile (would use repository)
        PartnerResponse updated = getPartner(partnerId);
        updated.setCompanyName(request.getCompanyName());
        updated.setContactEmail(request.getContactEmail());
        // ... other fields
        return updated;
    }

    private void validateBusinessRegistration(String registrationNumber) {
        // Integration with government business registry API
        // Validate GST, PAN, Business License
        if (registrationNumber == null || registrationNumber.isEmpty()) {
            throw new RuntimeException("Invalid business registration number");
        }
    }

    private Long generatePartnerId() {
        return System.currentTimeMillis(); // Simplified
    }

    private String generateApiKey() {
        return "pk_" + UUID.randomUUID().toString().replace("-", "");
    }

    private void sendOnboardingEmail(PartnerResponse partner) {
        // Email service integration
        System.out.println("Sending onboarding email to: " + partner.getContactEmail());
    }

    private PartnerResponse createSamplePartner(Long partnerId) {
        PartnerResponse sample = new PartnerResponse();
        sample.setId(partnerId);
        sample.setCompanyName("PVR Cinemas Ltd");
        sample.setContactEmail("partnerships@pvr.co.in");
        sample.setStatus("ACTIVE");
        sample.setTotalTheatres(5);
        sample.setActiveShows(25);
        return sample;
    }
}