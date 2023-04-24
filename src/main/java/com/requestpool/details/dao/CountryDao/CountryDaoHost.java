package com.requestpool.details.dao.CountryDao;

import com.requestpool.details.model.CountryModel.CountryModelHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CountryDaoHost extends JpaRepository<CountryModelHost,Integer> {

    public CountryModelHost findByCountry(String country);
}
