package com.requestpool.details.vo;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateVO implements Serializable {

    private double lat;
    private double lng;

}
