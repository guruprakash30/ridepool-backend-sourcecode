package com.requestpool.details.model.DetailsModel;

import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.vo.RequestVO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_people",
        schema = "base_details_host"
)
public class DetailsModelHost {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="id_generator_for_host"
    )
    @SequenceGenerator(name = "id_generator_for_host",
            sequenceName = "id_sequence",
            schema = "base_details_host",
            allocationSize = 1
    )
    private Integer details_host_id;
    private String name;
    private String from;
    private String to;
    private Integer waitTimeInDays;
    private Date date;
    private BigInteger phoneNumber;
    private Integer rideCount;
    @OneToOne
    @JoinColumn(name = "route_host_id")
    private RouteModelHost routeModelHost;

    public DetailsModelHost(RequestVO requestVO, RouteModelHost routeModelHost){

        name = requestVO.getName();

        from = requestVO.getFrom();

        to = requestVO.getTo();

        waitTimeInDays = requestVO.getWaitTimeInDays();

        date = requestVO.getDate();

        phoneNumber = requestVO.getPhoneNumber();

        rideCount = requestVO.getRideCount();

        this.routeModelHost = routeModelHost;

    }
}
