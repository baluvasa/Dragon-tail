package com.techm.po.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techm.po.model.dto.FxRatesDTO;

@Repository
public interface FxRatesRepository extends JpaRepository<FxRatesDTO, String>{

	@Query(value="select * from tbl_fx_rates fx where fx.currency_code=?1 and fx.fx_date=?2", nativeQuery=true)
	public List<FxRatesDTO> fetchByCurrencyCode(String currencyCode, Date fxDate);

	@Query(value="Select * from tbl_fx_rates where status='ACTIVE'", nativeQuery=true)
	List<FxRatesDTO> fetchFxRates();

	@Query(value="select * from tbl_fx_rates fx where fx.currency_code=?1", nativeQuery=true)
	public List<FxRatesDTO> fetchFxRatesByCountryCode(@Param("countryCode")String countryCode);
	
	@Query(value="select * from tbl_fx_rates fx where fx.fx_id=?1", nativeQuery=true)
	public List<FxRatesDTO> fetchFxRates(@Param("fxId")String fxId);

	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_fx_rates SET fx_rate=:fxRate, status='ACTIVE', currency_code=:currencyCode, fx_date=:date WHERE fx_id=:fxId", nativeQuery=true)
	public void updateFxRates(String fxRate, String currencyCode, String fxId, LocalDate date);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_fx_rates SET status='INACTIVE' WHERE fx_id=:fxId", nativeQuery=true)
	public void deleteFxRate(String fxId);
	
	@Modifying
	@Query(value="UPDATE tbl_fx_rates SET status='INACTIVE' WHERE currency_code=:currencyCode AND fx_date=:date", nativeQuery=true)
	public void changeStatusFxRate(String currencyCode, LocalDate date);
	
	@Query(value="select fx_id from tbl_fx_rates where currency_code=:code and fx_date=:date and status='ACTIVE'", nativeQuery=true)
	String fetchFxRateId(String code, LocalDate date);
	
	@Query(value="select * from tbl_fx_rates where currency_code=:currencyCode and fx_date=:date", nativeQuery=true)
	List<FxRatesDTO> fetchFxRatesByCountryCodeAndDate(String currencyCode, LocalDate date);
	
	@Query(value="select * from tbl_fx_rates where fx_date=:date", nativeQuery=true)
	List<FxRatesDTO> fetchFxRatesByDate(LocalDate date);
}
