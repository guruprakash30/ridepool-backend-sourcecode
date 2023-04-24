package com.requestpool.details.model.DetailsModel;

import com.requestpool.details.model.RouteModel.RouteModelPool;
import com.requestpool.details.vo.RequestVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_people",
schema = "base_details_pool"
)
public class DetailsModelPool {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
    generator="id_generator_for_pool"
    )
    @SequenceGenerator(name = "id_generator_for_pool",
    sequenceName = "id_sequence",
    schema = "base_details_pool",
    allocationSize = 1
    )
    private Integer details_pool_id;
    private String name;
    private String from;
    private String to;
    private Integer waitTimeInDays;
    private Date date;
    private BigInteger phoneNumber;
    private Integer rideCount;
    @OneToOne
    @JoinColumn(name = "route_pool_id")
    private RouteModelPool routeModelPool;

    public DetailsModelPool(RequestVO requestVO, RouteModelPool routeModelPool){

        name = requestVO.getName();

        from = requestVO.getFrom();

        to = requestVO.getTo();

        waitTimeInDays = requestVO.getWaitTimeInDays();

        date = requestVO.getDate();

        phoneNumber = requestVO.getPhoneNumber();

        rideCount = requestVO.getRideCount();

        this.routeModelPool = routeModelPool;

    }
}
