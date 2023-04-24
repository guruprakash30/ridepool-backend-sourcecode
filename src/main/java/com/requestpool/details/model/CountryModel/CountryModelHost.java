package com.requestpool.details.model.CountryModel;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_country",
        schema = "base_details_host"
)
public class CountryModelHost{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="country_id_generator_for_host"
    )
    @SequenceGenerator(name = "country_id_generator_for_host",
            sequenceName = "country_sequence",
            schema = "base_details_host",
            allocationSize = 1
    )
    private Integer country_host_id;
    private String country;
}
