package com.requestpool.details.dao.DetailsDao;

import com.requestpool.details.model.DetailsModel.DetailsModelPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsDaoPool extends JpaRepository<DetailsModelPool,Integer>{
}
