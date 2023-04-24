package com.requestpool.details.dao.RouteDao;

import com.requestpool.details.model.RouteModel.RouteModelHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteDaoHost extends JpaRepository<RouteModelHost,Integer>{

    @Query(value = "SELECT * FROM  base_details_host.table_route WHERE country_host_id=:country_host_id AND coordinates @>:PointA\\:\\:jsonb AND coordinates @>:PointB\\:\\:jsonb", nativeQuery = true)
    List<RouteModelHost> getListOfRoutesMatchesPointAPointB(String PointA, String PointB, Integer country_host_id);

    @Query(value = "SELECT * FROM  base_details_host.table_route WHERE coordinates @>:PointA\\:\\:jsonb AND coordinates @>:PointB\\:\\:jsonb", nativeQuery = true)
    List<RouteModelHost> getListOfRoutesMatchesPointAPointB(String PointA, String PointB);
}
