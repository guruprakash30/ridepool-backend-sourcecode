package com.requestpool.details.dao.TravellerDao;


import com.requestpool.details.model.RouteModel.RouteModelPool;
import com.requestpool.details.model.TravellerModel.TravellerModelPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDaoPool extends JpaRepository<TravellerModelPool,Integer>{
    List<TravellerModelPool> findByDetailsModelPoolFromAndDetailsModelPoolTo(String from, String to);

    List<TravellerModelPool> findByRouteModelPool(RouteModelPool route);
}
