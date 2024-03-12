package com.khadbhandarserver.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.ProductCategory;
import com.khadbhandarserver.inventory.entity.StockDetails;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Long>{

	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("UPDATE StockDetails sd  SET sd.stockName=:stockName,sd.stockCategory=:stockCategory,sd.stockQuantity=:stockQuantity,sd.stockUnit=:stockUnit,sd.stockPrice=:stockPrice,sd.totalStockAmount=:totalStockAmount WHERE sd.stockId=:stockId")
	public void updateStockDetals(
			@Param("stockName") String stockName,
			@Param("stockCategory") String stockCategory,
			@Param("stockQuantity") int stockQuantity,
			@Param("stockUnit") String stockUnit,
			@Param("stockPrice") double stockPrice,
			@Param("totalStockAmount") double totalStockAmount,
			@Param("stockId") Long stockId
			);
	
	List<StockDetails> findByStockName(String stockName);
	
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("UPDATE StockDetails sd  SET sd.stockQuantity=:stockQuantity,sd.totalStockAmount=:totalStockAmount WHERE sd.stockId=:stockId")
	public void updateStockDetalsOnSales(
			@Param("stockQuantity") int stockQuantity,
			@Param("totalStockAmount") double totalStockAmount,
			@Param("stockId") Long stockId
			);
	
}
