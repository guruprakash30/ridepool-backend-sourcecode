package com.requestpool.details.model.RouteModel;

import com.requestpool.details.model.CountryModel.CountryModelPool;
import com.requestpool.details.model.TravellerModel.TravellerModelPool;
import com.requestpool.details.vo.CoordinateVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "table_route",
        schema = "base_details_pool"
)
public class RouteModelPool {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="route_id_generator_for_pool"
    )
    @SequenceGenerator(name = "route_id_generator_for_pool",
            sequenceName = "route_sequence",
            schema = "base_details_pool",
            allocationSize = 1
    )
    private Integer route_pool_id;
    @Type(value = io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private List<CoordinateVO> coordinates;
    @OneToMany(mappedBy = "routeModelPool")
    private List<TravellerModelPool> travellerModelPools;
    @OneToOne
    @JoinColumn(name = "country_pool_id")
    private CountryModelPool countryModelPool;
}
