package com.requestpool.details.service.TravellerService;

import com.requestpool.details.dao.TravellerDao.TravellerDaoHost;
import com.requestpool.details.model.DetailsModel.DetailsModelHost;
import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravellerServiceHost {

    @Autowired
    TravellerDaoHost travellerDaoHost;
    public Optional<List<TravellerModelHost>> getTravellersDetails(String from, String to){

        Optional<List<TravellerModelHost>> optional = Optional.ofNullable(travellerDaoHost.findByDetailsModelHostFromAndDetailsModelHostTo(from,to));

        return optional;
    }

    public TravellerModelHost putTravellerDetails(RouteModelHost routeModelHost, DetailsModelHost detailsModelHost){

        TravellerModelHost travellerModelHost = new TravellerModelHost();

        travellerModelHost.setName(detailsModelHost.getName());

        travellerModelHost.setDetailsModelHost(detailsModelHost);

        travellerModelHost.setRouteModelHost(routeModelHost);

        return travellerDaoHost.save(travellerModelHost);
    }

    public List<TravellerModelHost> getTravellersByRouteId(RouteModelHost route){
        return travellerDaoHost.findByRouteModelHost(route);
    }
}
