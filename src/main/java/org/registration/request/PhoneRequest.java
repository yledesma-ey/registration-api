package org.registration.request;

public record PhoneRequest(
         String number,
         String cityCode,
         String countryCode) {}
