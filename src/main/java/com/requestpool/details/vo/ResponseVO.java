package com.requestpool.details.vo;

import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelPool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {
    private String name;
    private String from;
    private String to;
    private String travelType;
    private Date date;
    private BigInteger phoneNumber;
    private Integer rideCount;

    public ResponseVO(TravellerModelHost travellerModelHost){
        name = travellerModelHost.getName();
        from = travellerModelHost.getDetailsModelHost().getFrom();
        to = travellerModelHost.getDetailsModelHost().getTo();
        travelType="HOST";
        date=travellerModelHost.getDetailsModelHost().getDate();
        phoneNumber=travellerModelHost.getDetailsModelHost().getPhoneNumber();
        rideCount=travellerModelHost.getDetailsModelHost().getRideCount();
    }

    public ResponseVO(TravellerModelPool travellerModelPool){
        name = travellerModelPool.getName();
        from = travellerModelPool.getDetailsModelPool().getFrom();
        to = travellerModelPool.getDetailsModelPool().getTo();
        travelType="POOL";
        date=travellerModelPool.getDetailsModelPool().getDate();
        phoneNumber=travellerModelPool.getDetailsModelPool().getPhoneNumber();
        rideCount=travellerModelPool.getDetailsModelPool().getRideCount();
    }
}
