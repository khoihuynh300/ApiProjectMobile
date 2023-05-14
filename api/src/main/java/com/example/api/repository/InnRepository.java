package com.example.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.entity.*;

public interface InnRepository extends JpaRepository<Inn, Long>{
	
	@Query("SELECT i FROM Inn i WHERE i.address LIKE %:address%"
            + " AND i.price >= :gtePrice"
            + " AND i.price < :ltePrice"
            + " AND i.size <= :size"
            + " ORDER BY i.updatedAt desc")
	public List<Inn> searchInn(@Param("address") String address,@Param("gtePrice") Double gtePrice,@Param("ltePrice") Double ltePrice, @Param("size") int size);

	Page<Inn> findByAddressContainingAndIsDeleted(String address,Boolean isDeleted, Pageable pageable);
	Page<Inn> findByAddressContainingAndIsDeletedAndIsConfirmed(String address,Boolean isDeleted, Boolean isConfirmed, Pageable pageable);
	Page<Inn> findByIsDeleted(Boolean isDeleted, Pageable pageable);
	Page<Inn> findByIsDeletedAndIsConfirmed(Boolean isDeleted, Boolean isConfirmed, Pageable pageable);
	
	List<Inn> findByProposedById(Users users); 
}
