package com.requestpool.details.dao.TravellerDao;

import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.model.TravellerModel.TravellerModelHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDaoHost extends JpaRepository<TravellerModelHost,Integer>{

    List<TravellerModelHost> findByDetailsModelHostFromAndDetailsModelHostTo(String from, String to);

    List<TravellerModelHost> findByRouteModelHost(RouteModelHost route);
}
