package com.requestpool.details.service.DetailsService;

import com.requestpool.details.dao.DetailsDao.DetailsDaoHost;
import com.requestpool.details.model.DetailsModel.DetailsModelHost;
import com.requestpool.details.model.RouteModel.RouteModelHost;
import com.requestpool.details.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceHost {
    @Autowired
    DetailsDaoHost detailsDaoHost;

    public DetailsModelHost putDetails(RequestVO requestVO, RouteModelHost routeModelHost){

        DetailsModelHost detailsModelHost = new DetailsModelHost(requestVO,routeModelHost);

        return detailsDaoHost.save(detailsModelHost);
    }
}
