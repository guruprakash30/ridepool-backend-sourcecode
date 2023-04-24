package com.requestpool.details.dao.DetailsDao;

import com.requestpool.details.model.DetailsModel.DetailsModelHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsDaoHost extends JpaRepository<DetailsModelHost,Integer>{
}
