package com.nikolenko.homeworks.homework_25;

import lombok.*;

//@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
//@Builder
public class City {
    private  long id;
    private  String name;
    private  String countryCode;
    private  String district;
    private  int population;
}


