package com.xyz.moviebooking.partner.dto;

import java.time.LocalDateTime;

public class PartnerResponse {
    private Long id;
    private String companyName;
    private String contactEmail;
    private String contactPhone;
    private String businessRegistrationNumber;
    private String primaryContactName;
    private String businessAddress;
    private String city;
    private String state;
    private String country;
    private String status;
    private LocalDateTime registrationDate;
    private String apiKey;
    private Integer totalTheatres;
    private Integer activeShows;
    
    // Constructors
    public PartnerResponse() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getTotalTheatres() {
        return totalTheatres;
    }

    public void setTotalTheatres(Integer totalTheatres) {
        this.totalTheatres = totalTheatres;
    }

    public Integer getActiveShows() {
        return activeShows;
    }

    public void setActiveShows(Integer activeShows) {
        this.activeShows = activeShows;
    }
}