package com.requestpool.details.model.TravellerModel;

import com.requestpool.details.model.DetailsModel.DetailsModelHost;
import com.requestpool.details.model.RouteModel.RouteModelHost;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "table_traveller",
        schema = "base_details_host"
)
public class TravellerModelHost {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="traveller_id_generator_for_host"
    )
    @SequenceGenerator(name = "traveller_id_generator_for_host",
            sequenceName = "traveller_sequence",
            schema = "base_details_host",
            allocationSize = 1
    )
    private Integer traveller_host_id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "route_host_id")
    private RouteModelHost routeModelHost;
    @OneToOne
    @JoinColumn(name = "details_host_id")
    private DetailsModelHost detailsModelHost;

}
