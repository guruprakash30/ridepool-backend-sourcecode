package com.requestpool.details.service.CountryService;

import com.requestpool.details.dao.CountryDao.CountryDaoPool;
import com.requestpool.details.model.CountryModel.CountryModelPool;
import com.requestpool.details.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CountryServicePool {

    @Autowired
    CountryDaoPool countryDaoPool;
    public CountryModelPool getOrPutCountryDetails(RequestVO requestVO){

        Optional<CountryModelPool> optional = Optional.ofNullable(countryDaoPool.findByCountry(requestVO.getCountry()));

            if(!optional.isPresent()){

                CountryModelPool countryModelPool = new CountryModelPool();

                countryModelPool.setCountry(requestVO.getCountry());

                return countryDaoPool.save(countryModelPool);

            }

            else return optional.get();

        }
    }
