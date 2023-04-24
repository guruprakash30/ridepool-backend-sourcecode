package com.requestpool.details.model.CountryModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "table_country",
        schema = "base_details_pool"
)
public class CountryModelPool {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="country_id_generator_for_pool"
    )
    @SequenceGenerator(name = "country_id_generator_for_pool",
            sequenceName = "country_sequence",
            schema = "base_details_pool",
            allocationSize = 1
    )
    private Integer country_pool_id;
    private String country;
}
