package com.requestpool.details.model.TravellerModel;


import com.requestpool.details.model.DetailsModel.DetailsModelPool;
import com.requestpool.details.model.RouteModel.RouteModelPool;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "table_traveller",
        schema = "base_details_pool"
)
public class TravellerModelPool {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="id_generator_for_traveller"
    )
    @SequenceGenerator(name = "id_generator_for_traveller",
            sequenceName = "traveller_sequence",
            schema = "base_details_pool",
            allocationSize = 1
    )
    private Integer traveller_pool_id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "route_pool_id")
    private RouteModelPool routeModelPool;
    @OneToOne
    @JoinColumn(name = "details_pool_id")
    private DetailsModelPool detailsModelPool;
}
