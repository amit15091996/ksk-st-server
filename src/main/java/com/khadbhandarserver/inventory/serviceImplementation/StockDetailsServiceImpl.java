package com.khadbhandarserver.inventory.serviceImplementation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.khadbhandarserver.inventory.dto.StockDetailsDto;
import com.khadbhandarserver.inventory.entity.StockDetails;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.StockDetailsRepository;
import com.khadbhandarserver.inventory.service.StockDetailsService;

@Service
public class StockDetailsServiceImpl implements StockDetailsService {

	@Autowired
	private StockDetailsRepository stockDetailsRepository; 
	
	@Override
	public Map<Object, Object> insertStockDetails(StockDetailsDto stockDetailsDto) {
		Map<Object, Object> stockDetailsMap=new HashMap<>();
		
		StockDetails stockDetails=new StockDetails();
		stockDetails.setStockCategory(stockDetailsDto.getStockCategory());
		stockDetails.setStockName(stockDetailsDto.getStockName());
		stockDetails.setStockPrice(stockDetailsDto.getStockPrice());
		stockDetails.setStockUnit(stockDetailsDto.getStockUnit());
		stockDetails.setStockQuantity(stockDetailsDto.getStockQuantity());
		stockDetails.setTotalStockAmount(stockDetailsDto.getStockPrice()*stockDetails.getStockQuantity());
		
		try {
			StockDetails StockDetailSaved=this.stockDetailsRepository.save(stockDetails);
		if( StockDetailSaved !=null) {
			stockDetailsMap.put(AppConstant.statusCode, AppConstant.ok);
			stockDetailsMap.put(AppConstant.status, AppConstant.success);
			stockDetailsMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
		}
		}
		catch(Exception e) {
			throw new BadRequest(e.getMessage());
		}
	   return stockDetailsMap;
		
	}

	@Override
	public Map<Object, Object> deleteStockDetails(Long stockId) {
		Map<Object, Object> stockDetailsMap=new HashMap<>();
		
		if(this.stockDetailsRepository.findById(stockId).isPresent()) {
		this.stockDetailsRepository.deleteById(stockId);
		stockDetailsMap.put(AppConstant.statusCode, AppConstant.ok);
		stockDetailsMap.put(AppConstant.status, AppConstant.success);
		stockDetailsMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
		}
		else {
			throw new NotFoundException(AppConstant.noRecordFound + stockId);
		}
		return stockDetailsMap;
	}

	@Override
	public Map<Object, Object> updateStockDetails(Long stockId, StockDetailsDto stockDetailsDto) {
		Map<Object, Object> stockDetailsMap=new HashMap<>();
		
		  if(this.stockDetailsRepository.findById(stockId).isPresent()) {
			try {
			this.stockDetailsRepository.updateStockDetals(
					stockDetailsDto.getStockName(),
					stockDetailsDto.getStockCategory(),
					stockDetailsDto.getStockQuantity(),
					stockDetailsDto.getStockUnit(),
					stockDetailsDto.getStockPrice(),
					stockDetailsDto.getStockPrice()*stockDetailsDto.getStockQuantity(),
					stockId
					);
						
		
			stockDetailsMap.put(AppConstant.statusCode, AppConstant.ok);
			stockDetailsMap.put(AppConstant.status, AppConstant.success);
			stockDetailsMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully +stockId);
			}
			catch (Exception e) {throw new BadRequest(e.getMessage());}
			}
			else {throw new NotFoundException(AppConstant.noRecordFound + stockId);}

		return stockDetailsMap;
	}

	@Override
	public Map<Object, Object> getAllStockDetails() {
		  Map<Object, Object> stockDetailsMap=new HashMap<>();
			
		  stockDetailsMap.put(AppConstant.statusCode, AppConstant.ok);
		  stockDetailsMap.put(AppConstant.status, AppConstant.success);
		  stockDetailsMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  stockDetailsMap.put(AppConstant.response, this.stockDetailsRepository.findAll());
			  
			return stockDetailsMap;
	}

	


}
