package com.requestpool.details.model.RouteModel;

import com.requestpool.details.model.CountryModel.CountryModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelHost;
import com.requestpool.details.vo.CoordinateVO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "table_route",
        schema = "base_details_host"
)
public class RouteModelHost{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="route_id_generator_for_host"
    )
    @SequenceGenerator(name = "route_id_generator_for_host",
            sequenceName = "route_sequence",
            schema = "base_details_host",
            allocationSize = 1
    )
    private Integer route_host_id;
    @Type(value = io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private List<CoordinateVO> coordinates;
    @OneToMany(mappedBy = "routeModelHost")
    private List<TravellerModelHost> travellerModelHosts;
    @OneToOne
    @JoinColumn(name = "country_host_id")
    private CountryModelHost countryModelHost;
}
