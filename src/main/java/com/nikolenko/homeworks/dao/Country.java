package com.nikolenko.homeworks.dao;

import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Country{
    private final String code;
    private final String name;
    private final String continent;
    private final String region;
    private final double surfaceArea;
    private final int indepYear;
    private final int population;
    private final double lifeExpectancy;
    private final double gnp;
    private final double gnpOld;
    private final String localName;
    private final String governmentForm;
    private final String headOfState;
    private final int capital;
    private final String cap;
    private final String code2;
}
