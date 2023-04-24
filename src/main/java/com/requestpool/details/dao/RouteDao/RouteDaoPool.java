package com.requestpool.details.dao.RouteDao;

import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.model.RouteModel.RouteModelPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteDaoPool extends JpaRepository<RouteModelPool,Integer>{

    @Query(value = "SELECT * FROM  base_details_pool.table_route WHERE country_pool_id=:country_pool_id AND coordinates @>:PointA\\:\\:jsonb AND coordinates @>:PointB\\:\\:jsonb", nativeQuery = true)
    List<RouteModelPool> getListOfRoutesMatchesPointAPointB(String PointA, String PointB, Integer country_pool_id);

    @Query(value = "SELECT * FROM  base_details_pool.table_route WHERE coordinates @>:PointA\\:\\:jsonb AND coordinates @>:PointB\\:\\:jsonb", nativeQuery = true)
    List<RouteModelPool> getListOfRoutesMatchesPointAPointB(String PointA, String PointB);
}
