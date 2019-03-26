package com.techm.po.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techm.po.model.bo.LeavesBO;
import com.techm.po.model.dto.LeavesDTO;
import com.techm.po.model.dto.ProjectDTO;
import com.techm.po.model.dto.ProjectInformationDTO;

@Repository
public interface ProjectInformationRepository extends JpaRepository<ProjectDTO, String>{

	@Query(value="select * from tbl_po_approvals po where po.associate_id=?1 and po.month_year=?2", nativeQuery=true)
	Optional<ProjectInformationDTO> fetchPoApprovalsByMonthYear(String associateId, String monthYear);
	
	@Query(value="select distinct account_category from tbl_project", nativeQuery=true)
	List<String> fetchAccountCategories();

	@Query(value="select * from tbl_project po where po.account_category=?1", nativeQuery=true)
	List<ProjectDTO> fetchprojectInfo(String accountCategory);
	
	/*@Query(value="select rm.p_id, r.associate_id,r.associate_name,rm.associate_start_date,rm.associate_end_date , "
			+ "EXTRACT(DAY FROM rm.associate_end_date-now()) as releasedate, r.band, p.unit_of_mesurment, p.pid, "
			+ "(select fx_rate  from tbl_fx_rates where  currency_code= ?8 and fx_date = (select max(fx.fx_date)  "
			+ "from tbl_fx_rates fx where fx_date between ?9 and ?10 and status = 'ACTIVE' and currency_code= ?8)) "
			+ "from tbl_resources r,tbl_resource_map rm ,tbl_project p "
			+ "where rm.associate_id=r.associate_id and rm.p_id=p.pid", nativeQuery=true)
	List<ResourceFxBO> getPOSummaryDetails(String accountCategory, String accountName, String projectName,
			String yyyyMMM, String customerName, String projectStartDate, String projectEndDate, String currency, String pId, LocalDate startdate1, LocalDate enddate1);
	*/
	
	/*@Query(value="select NEW com.techm.po.model.bo.ResourceFxBO(" + 
			"rm.pId, r.associateId,r.associateName,rm.associateStartDate,rm.associateEndDate ,r.band, p.uof," + 
			"(select fr.fxRate from FxRatesDTO fr where fr.fxDate = " + 
			"(select fx.fxDate from FxRatesDTO fx where fx.fxDate between :projectStart and :projectEnd and fx.status = 'ACTIVE' order by fx_date desc))," + 
			"EXTRACT(DAY FROM rm.associateEndDate-now()) as releasedate ) " + 
			"from ResourceDTO r,ResourceMapDTO rm ,ProjectDTO p " + 
			"where r.associateId=rm.associateId and rm.pId=p.pid and p.pid='PID585858'", nativeQuery=true)
	List<ResourceFxBO> getPOSummaryDetails(String customerName, LocalDate projectStart, 
			LocalDate projectEnd, String quote, String pId, String contract);*/
	
	/*@Query(value="select * from tbl_project p where lower(p.account_category) like :accountCategory and lower(p.account_name) like :accountName " + 
			"and lower(p.project_name) like :projectName and lower(p.project_type) like :projectType and p.created_date between :startdate1 " + 
			"and :enddate1", nativeQuery=true)
	List<ProjectDTO> fetchProjectDetails(String accountCategory, String accountName, String projectName,
			String projectType, LocalDate startdate1, LocalDate enddate1);*/
	
	@Query(value="select * from tbl_leaves", nativeQuery=true)
	List<LeavesDTO> getAssociateLeaveDetails(LocalDate startdate1, LocalDate enddate1);
	
	//@Query("select september from public.tbl_holidays where project_name='balu po' and year='yyyy'")
	//int getAssociateMonthHolidays(String id,String mm,int yyyy);
	
}
