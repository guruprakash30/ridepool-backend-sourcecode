package com.requestpool.details.service.RouteService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.requestpool.details.CustomExceptions.NoDataFoundException;
import com.requestpool.details.dao.RouteDao.RouteDaoHost;
import com.requestpool.details.model.CountryModel.CountryModelHost;
import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelHost;
import com.requestpool.details.service.TravellerService.TravellerServiceHost;
import com.requestpool.details.vo.CoordinateVO;
import com.requestpool.details.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RouteServiceHost {
    @Autowired
    private TravellerServiceHost travellerServiceHost;
    @Autowired
    private RouteDaoHost routeDaoHost;

    public RouteModelHost getOrPutRouteDetails(RequestVO requestVO, CountryModelHost countryModelHost){

        //to find whether it was existing route
        Optional<List<TravellerModelHost>> optional =  travellerServiceHost.getTravellersDetails(requestVO.getFrom(),requestVO.getTo());

        if(!optional.get().isEmpty()){

            System.out.println(requestVO.getName());

            return optional.get().stream().findFirst().get().getRouteModelHost();
        }

        else{

            RouteModelHost routeModelHost = new RouteModelHost();

            routeModelHost.setCoordinates(requestVO.getCoordinates());

            routeModelHost.setCountryModelHost(countryModelHost);

            return routeDaoHost.save(routeModelHost);
        }

    }

    public List<RouteModelHost> getListOfRoutesMatchesPointAPointB(RequestVO requestVO, Integer country_host_id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<CoordinateVO> listA = new ArrayList<>();

        listA.add(requestVO.getPointA());

        List<CoordinateVO> listB = new ArrayList<>();

        listB.add(requestVO.getPointB());

        String PointA = objectMapper.writeValueAsString(listA);

        String PointB = objectMapper.writeValueAsString(listB);

        System.out.println(PointA);

        System.out.println(PointB);

        List<RouteModelHost> routeModelHostList =  routeDaoHost.getListOfRoutesMatchesPointAPointB(PointA, PointB, country_host_id);

        if(!routeModelHostList.isEmpty()){

            routeModelHostList.parallelStream().forEach(route ->{

                if(route.getTravellerModelHosts()==null) route.setTravellerModelHosts(travellerServiceHost.getTravellersByRouteId(route));

            });

            return routeModelHostList;
        }

        else if(requestVO.getTravelType().equals("HOST"))return routeModelHostList;

        else {
            throw new NoDataFoundException("NO HOST DATA FOUND TO DISPLAY BETWEEN THE SELECTED ROUTE");
        }


    }

    public List<RouteModelHost> getRouteDetails(RequestVO requestVO) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<CoordinateVO> listA = new ArrayList<>();

        listA.add(requestVO.getPointA());

        List<CoordinateVO> listB = new ArrayList<>();

        listB.add(requestVO.getPointB());

        String PointA = objectMapper.writeValueAsString(listA);

        String PointB = objectMapper.writeValueAsString(listB);

        System.out.println(PointA);

        System.out.println(PointB);

        List<RouteModelHost> routeModelHostList =  routeDaoHost.getListOfRoutesMatchesPointAPointB(PointA, PointB);

        if(!routeModelHostList.isEmpty()){

            routeModelHostList.parallelStream().forEach(route->{
                if(route.getTravellerModelHosts()==null)route.setTravellerModelHosts(travellerServiceHost.getTravellersByRouteId(route));
            });

            return routeModelHostList;

        }

        else if(requestVO.getTravelType().equals("HOST"))return routeModelHostList;

        else {
            throw new NoDataFoundException("NO HOST DATA FOUND TO DISPLAY BETWEEN THE SELECTED ROUTE");
        }
    }
}
