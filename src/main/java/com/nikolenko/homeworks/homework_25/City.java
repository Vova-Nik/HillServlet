package com.nikolenko.homeworks.homework_25;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class City {
    private final long id;
    private final String name;
    private final String countryCode;
    private final String district;
    private final int population;
}


