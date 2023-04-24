package com.requestpool.details.service.RouteService;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.requestpool.details.dao.RouteDao.RouteDaoPool;
import com.requestpool.details.model.CountryModel.CountryModelPool;
import com.requestpool.details.model.RouteModel.RouteModelPool;
import com.requestpool.details.model.TravellerModel.TravellerModelPool;
import com.requestpool.details.service.TravellerService.TravellerServicePool;
import com.requestpool.details.vo.CoordinateVO;
import com.requestpool.details.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServicePool {

    @Autowired
    private TravellerServicePool travellerServicePool;
    @Autowired
    private RouteDaoPool routeDaoPool;

    public RouteModelPool getOrPutRouteDetails(RequestVO requestVO, CountryModelPool countryModelPool){

        //to find whether it was existing route
        Optional<List<TravellerModelPool>> optional =  travellerServicePool.getTravellersDetails(requestVO.getFrom(),requestVO.getTo());

        if(!optional.get().isEmpty()){

            return optional.get().stream().findFirst().get().getRouteModelPool();
        }

        else{

            RouteModelPool routeModelPool = new RouteModelPool();

            routeModelPool.setCoordinates(requestVO.getCoordinates());

            routeModelPool.setCountryModelPool(countryModelPool);

            return routeDaoPool.save(routeModelPool);
        }

    }

    public List<RouteModelPool> getRouteDetails(RequestVO requestVO) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<CoordinateVO> listA = new ArrayList<>();

        listA.add(requestVO.getPointA());

        List<CoordinateVO> listB = new ArrayList<>();

        listB.add(requestVO.getPointB());

        String PointA = objectMapper.writeValueAsString(listA);

        String PointB = objectMapper.writeValueAsString(listB);

        List<RouteModelPool> routeModelPoolList = routeDaoPool.getListOfRoutesMatchesPointAPointB(PointA, PointB);

        if(!routeModelPoolList.isEmpty()){

            routeModelPoolList.parallelStream().forEach(route ->{
                if(route.getTravellerModelPools()==null)route.setTravellerModelPools(travellerServicePool.getTravellersByRouteId(route));
            });
        }

        return routeModelPoolList;
    }


    public List<RouteModelPool> getListOfRoutesMatchesPointAPointB(RequestVO requestVO, Integer country_pool_id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<CoordinateVO> listA = new ArrayList<>();

        listA.add(requestVO.getPointA());

        List<CoordinateVO> listB = new ArrayList<>();

        listB.add(requestVO.getPointB());

        String PointA = objectMapper.writeValueAsString(listA);

        String PointB = objectMapper.writeValueAsString(listB);

        System.out.println(PointA);

        System.out.println(PointB);

        List<RouteModelPool> routeModelPoolList = routeDaoPool.getListOfRoutesMatchesPointAPointB(PointA, PointB, country_pool_id);

        if(!routeModelPoolList.isEmpty()){

            routeModelPoolList.parallelStream().forEach( route ->{

                if(route.getTravellerModelPools()==null)route.setTravellerModelPools(travellerServicePool.getTravellersByRouteId(route));
            });
        }

        return routeModelPoolList;
    }
}
