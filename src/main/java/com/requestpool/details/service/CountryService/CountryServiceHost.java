package com.requestpool.details.service.CountryService;

import com.requestpool.details.dao.CountryDao.CountryDaoHost;
import com.requestpool.details.model.CountryModel.CountryModelHost;
import com.requestpool.details.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceHost {
    @Autowired
    CountryDaoHost countryDaoHost;

    public CountryModelHost getOrPutCountryDetails(RequestVO requestVO){

        Optional<CountryModelHost> optional = Optional.ofNullable(countryDaoHost.findByCountry(requestVO.getCountry()));

        if(!optional.isPresent()){

            CountryModelHost countryModelHost = new CountryModelHost();

            countryModelHost.setCountry(requestVO.getCountry());

            return countryDaoHost.save(countryModelHost);

        }

        else return optional.get();
    }
}
