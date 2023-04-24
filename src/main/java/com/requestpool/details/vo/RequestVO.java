package com.requestpool.details.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestVO {
    private String name;
    private String from;
    private String to;
    @JsonProperty("selectedRadioButton")
    private String travelType;
    @JsonProperty("noOfDays")
    private Integer waitTimeInDays;
    private Date date;
    @JsonProperty("contact")
    private BigInteger phoneNumber;
    private Integer rideCount;
    private String country;
    private List<CoordinateVO> coordinates;
    private CoordinateVO pointA;
    private CoordinateVO pointB;
}
