package com.requestpool.details.dao.CountryDao;


import com.requestpool.details.model.CountryModel.CountryModelPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDaoPool extends JpaRepository<CountryModelPool,Integer>{
    public CountryModelPool findByCountry(String country);
}
