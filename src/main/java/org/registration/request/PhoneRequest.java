package org.registration.request;

public record PhoneRequest(
         String number,
         String citycode,
         String countrycode) {}
