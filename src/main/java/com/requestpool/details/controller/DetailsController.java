package com.requestpool.details.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.requestpool.details.CustomExceptions.NoDataFoundException;
import com.requestpool.details.model.CountryModel.CountryModelHost;
import com.requestpool.details.model.CountryModel.CountryModelPool;
import com.requestpool.details.model.DetailsModel.DetailsModelHost;
import com.requestpool.details.model.DetailsModel.DetailsModelPool;
import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.model.RouteModel.RouteModelPool;
import com.requestpool.details.model.TravellerModel.TravellerModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelPool;
import com.requestpool.details.service.CountryService.CountryServiceHost;
import com.requestpool.details.service.CountryService.CountryServicePool;
import com.requestpool.details.service.DetailsService.DetailsServiceHost;
import com.requestpool.details.service.DetailsService.DetailsServicePool;
import com.requestpool.details.service.RouteService.RouteServiceHost;
import com.requestpool.details.service.RouteService.RouteServicePool;
import com.requestpool.details.service.TravellerService.TravellerServiceHost;
import com.requestpool.details.service.TravellerService.TravellerServicePool;
import com.requestpool.details.vo.CoordinateVO;
import com.requestpool.details.vo.RequestVO;
import com.requestpool.details.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/details")
@CrossOrigin(origins = "*")
@Slf4j
public class DetailsController {

    @Autowired
    CountryServiceHost countryServiceHost;
    @Autowired
    CountryServicePool countryServicePool;
    @Autowired
    RouteServiceHost routeServiceHost;
    @Autowired
    RouteServicePool routeServicePool;
    @Autowired
    DetailsServiceHost detailsServiceHost;
    @Autowired
    DetailsServicePool detailsServicePool;
    @Autowired
    TravellerServiceHost travellerServiceHost;

    @Autowired
    TravellerServicePool travellerServicePool;

    @GetMapping("/test/app")
    public ResponseEntity<String> testApp(){
        log.info("application endpoint hit successfully");
        String responseBody = "application deployement successfull";
        return new ResponseEntity<>(responseBody,HttpStatus.ACCEPTED);
    }

    @PostMapping("/post")
    public ResponseEntity<List<ResponseVO>> postDetails(@RequestBody RequestVO requestVO) throws JsonProcessingException {

        List<CoordinateVO> coordinateVOList = requestVO.getCoordinates();

        coordinateVOList.add(requestVO.getPointA());

        coordinateVOList.add(requestVO.getPointB());

        if(requestVO.getTravelType().equals("HOST")){

            CountryModelHost countryModelHost = countryServiceHost.getOrPutCountryDetails(requestVO);

            System.out.println(countryModelHost);

            RouteModelHost routeModelHost = routeServiceHost.getOrPutRouteDetails(requestVO,countryModelHost);

            System.out.println(routeModelHost.getTravellerModelHosts());

            DetailsModelHost detailsModelHost = detailsServiceHost.putDetails(requestVO,routeModelHost);

            System.out.println(detailsModelHost);

            TravellerModelHost travellerModelHost = travellerServiceHost.putTravellerDetails(routeModelHost,detailsModelHost);

            System.out.println(travellerModelHost.getRouteModelHost().getRoute_host_id());

            List<ResponseVO> responseVOList = new ArrayList<>();

            // existing routes having current route as subset in host

            List<RouteModelHost> routeModelHostList = routeServiceHost.getListOfRoutesMatchesPointAPointB(requestVO,routeModelHost.getCountryModelHost().getCountry_host_id());

            System.out.println(routeModelHostList.stream().findFirst().get().getTravellerModelHosts());

            if(!routeModelHostList.isEmpty()){

                List<ResponseVO> finalResponseVOList = responseVOList;

                routeModelHostList.parallelStream().forEach(data ->{

                    List<TravellerModelHost> travellersList = data.getTravellerModelHosts();

                    travellersList.parallelStream().forEach(traveller -> {

                        if(traveller.getTraveller_host_id()!=travellerModelHost.getTraveller_host_id()){

                            finalResponseVOList.add(new ResponseVO(traveller));
                        }
                    });

                });
            }

            List<RouteModelPool> routeModelPoolList = routeServicePool.getListOfRoutesMatchesPointAPointB(requestVO,routeModelHost.getCountryModelHost().getCountry_host_id());

            if(!routeModelPoolList.isEmpty()){

                List<ResponseVO> finalResponseVOList = responseVOList;

                routeModelPoolList.parallelStream().forEach(data ->{

                    List<TravellerModelPool> travellersList = data.getTravellerModelPools();

                    travellersList.parallelStream().forEach(traveller -> finalResponseVOList.add(new ResponseVO(traveller)));

                });
            }

            if(responseVOList.isEmpty()){
                throw new NoDataFoundException("NO DATA FOUND TO DISPLAY BETWEEN THE SELECTED ROUTE");
            }

            return new ResponseEntity<>(responseVOList, HttpStatus.CREATED);
        }

        else{

            CountryModelPool countryModelPool = countryServicePool.getOrPutCountryDetails(requestVO);

            System.out.println(countryModelPool);

            RouteModelPool routeModelPool = routeServicePool.getOrPutRouteDetails(requestVO,countryModelPool);

            System.out.println(routeModelPool);

            DetailsModelPool detailsModelPool = detailsServicePool.putDetails(requestVO,routeModelPool);

            System.out.println(detailsModelPool);

            TravellerModelPool travellerModelPool = travellerServicePool.putTravellerDetails(routeModelPool,detailsModelPool);

            System.out.println(travellerModelPool);

            List<ResponseVO> responseVOList = new ArrayList<>();

            // existing routes having current route as subset

            List<RouteModelHost> routeModelHostList = routeServiceHost.getListOfRoutesMatchesPointAPointB(requestVO, routeModelPool.getCountryModelPool().getCountry_pool_id());

            if(!routeModelHostList.isEmpty()){

                List<ResponseVO> finalResponseVOList = responseVOList;

                routeModelHostList.parallelStream().forEach(data ->{

                    List<TravellerModelHost> travellersList = data.getTravellerModelHosts();

                    travellersList.parallelStream().forEach(traveller -> finalResponseVOList.add(new ResponseVO(traveller)));

                });
            }

            return new ResponseEntity<>(responseVOList, HttpStatus.CREATED);
            
        }

    }

    @GetMapping("/get")
    public ResponseEntity<List<ResponseVO>> getDetails(@RequestParam String pointA,@RequestParam String pointB, @RequestParam(name = "selectedRadioButton") String travelType) throws JsonProcessingException{

        RequestVO requestVO = new RequestVO();

        CoordinateVO c1 = new ObjectMapper().readValue(pointA,CoordinateVO.class);

        CoordinateVO c2 = new ObjectMapper().readValue(pointB,CoordinateVO.class);

        requestVO.setPointA(c1);

        requestVO.setPointB(c2);

        requestVO.setTravelType(travelType);

        System.out.println(requestVO.getTravelType());

        List<ResponseVO> responseVOList = new ArrayList<>();

        // existing routes having current route as subset in host

        List<RouteModelHost> routeModelHostList = routeServiceHost.getRouteDetails(requestVO);

        if(!routeModelHostList.isEmpty()){

            List<ResponseVO> finalResponseVOList = responseVOList;

            routeModelHostList.parallelStream().forEach(data ->{

                List<TravellerModelHost> travellersList = data.getTravellerModelHosts();

                travellersList.parallelStream().forEach(traveller -> finalResponseVOList.add(new ResponseVO(traveller)));

            });
        }

        if(requestVO.getTravelType().equals("HOST")){

            List<RouteModelPool> routeModelPoolList = routeServicePool.getRouteDetails(requestVO);

            if(!routeModelPoolList.isEmpty()){

                List<ResponseVO> finalResponseVOList = responseVOList;

                routeModelPoolList.parallelStream().forEach(data ->{

                    List<TravellerModelPool> travellersList = data.getTravellerModelPools();

                    travellersList.parallelStream().forEach(traveller -> finalResponseVOList.add(new ResponseVO(traveller)));

                });
            }

            if(responseVOList.isEmpty())throw new NoDataFoundException("NO DATA FOUND TO DISPLAY BETWEEN THE SELECTED ROUTE");

        }

        return new ResponseEntity<>(responseVOList, HttpStatus.OK);
    }

}
