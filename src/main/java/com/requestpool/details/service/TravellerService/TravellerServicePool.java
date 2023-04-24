package com.requestpool.details.service.TravellerService;


import com.requestpool.details.dao.TravellerDao.TravellerDaoPool;
import com.requestpool.details.model.DetailsModel.DetailsModelPool;
import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.model.RouteModel.RouteModelPool;
import com.requestpool.details.model.TravellerModel.TravellerModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravellerServicePool {
    @Autowired
    TravellerDaoPool travellerDaoPool;
    public Optional<List<TravellerModelPool>> getTravellersDetails(String from, String to){

        Optional<List<TravellerModelPool>> optional = Optional.ofNullable(travellerDaoPool.findByDetailsModelPoolFromAndDetailsModelPoolTo(from,to));

        return optional;
    }

    public TravellerModelPool putTravellerDetails(RouteModelPool routeModelPool, DetailsModelPool detailsModelPool){

        TravellerModelPool travellerModelPool = new TravellerModelPool();

        travellerModelPool.setName(detailsModelPool.getName());

        travellerModelPool.setDetailsModelPool(detailsModelPool);

        travellerModelPool.setRouteModelPool(routeModelPool);

        return travellerDaoPool.save(travellerModelPool);
    }

    public List<TravellerModelPool> getTravellersByRouteId(RouteModelPool route){
        return travellerDaoPool.findByRouteModelPool(route);
    }
}
