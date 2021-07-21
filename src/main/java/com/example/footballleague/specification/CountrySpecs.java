package com.example.footballleague.specification;

import com.example.footballleague.model.Country;
import com.example.footballleague.model.Country_;
import org.springframework.data.jpa.domain.Specification;

public class CountrySpecs {
    public static Specification<Country> byCountryName(String countryName) {
        return (root, query, cb) -> {
            if (countryName == null) {
                return cb.conjunction();
            }
            return cb.like(root.get(Country_.countryName).as(String.class), countryName);
        };
    }
}
