package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.entity.*;

public interface InnRepository extends JpaRepository<Inn, Long>{
	
	@Query("SELECT i FROM Inn i WHERE i.address LIKE %:address%"
            + " AND i.price >= :gtePrice"
            + " AND i.price < :ltePrice"
            + " ORDER BY i.updatedAt desc")
	public List<Inn> searchInn(@Param("address") String address,@Param("gtePrice") Double gtePrice,@Param("ltePrice") Double ltePrice);
}
