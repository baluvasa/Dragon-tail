package com.techm.po.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.techm.po.model.dto.HolidaysDTO;

@Repository
public interface HolidayRepository extends JpaRepository<HolidaysDTO, Long>{

	
	@Query("SELECT h FROM HolidaysDTO h where lower(h.accountCategory) like :accountCategory or lower(h.accountName) like :accountName or "
			+ "lower(h.projectName) like :projectName or lower(h.year) like :year")
	List<HolidaysDTO> fetchHolidaysDetails(@Param("accountCategory") String accountCategory,@Param("accountName") String accountName,@Param("projectName") String projectName,@Param("year") String year);

		
	@Query("SELECT h FROM HolidaysDTO h where h.accountCategory like %?1% and "
			+ "h.accountName like %?2% and h.projectName like %?3% and h.year like %?4%")
	Optional<HolidaysDTO> fetchHolidaysDetailsExists(String accountCategory, String accountName, String projectName, String year);
	
	 @Query("SELECT h FROM HolidaysDTO h where h.holidayId=:id") 
	 Optional<HolidaysDTO> findHolidaysDetails(@Param("id") Integer id);
	  	  
	 @Query(nativeQuery= true, value ="SELECT id FROM public.holidays where account_category=:accountCategory and account_name=:accountName and project_name=:projectName and year=:year")
	 public Long idData(String accountCategory, String accountName, String projectName, String year);
}