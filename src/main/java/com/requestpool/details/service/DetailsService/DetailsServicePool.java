package com.requestpool.details.service.DetailsService;

import com.requestpool.details.dao.DetailsDao.DetailsDaoPool;
import com.requestpool.details.model.DetailsModel.DetailsModelPool;
import com.requestpool.details.model.RouteModel.RouteModelPool;
import com.requestpool.details.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailsServicePool {
    @Autowired
    DetailsDaoPool detailsDaoPool;

    public DetailsModelPool putDetails(RequestVO requestVO, RouteModelPool routeModelPool){

        DetailsModelPool detailsModelPool = new DetailsModelPool(requestVO,routeModelPool);

        return detailsDaoPool.save(detailsModelPool);
    }
}
