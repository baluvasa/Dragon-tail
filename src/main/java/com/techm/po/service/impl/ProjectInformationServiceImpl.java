package com.techm.po.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techm.po.dao.ProjectInformationRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.ProjectBO;
import com.techm.po.model.bo.ResourceFxBO;
import com.techm.po.model.dto.ProjectDTO;
import com.techm.po.service.ProjectInformationService;
import com.techm.po.utils.DateUtils;

@Service
public class ProjectInformationServiceImpl implements ProjectInformationService{
	
	@Autowired
	private ProjectInformationRepository projectInformationRepository;
	
	@Autowired
	private ProjectDetailServiceImpl projectDetailServiceImpl;
	
	@PersistenceContext
    public EntityManager em;

	@Override
	public Map<String, Object> fetchAccountCategory() {
		Map<String, Object> response;
		List<String> accountCategoriesList;
		response = new HashMap<String, Object>();
		try {
			accountCategoriesList = projectInformationRepository.fetchAccountCategories();
			if(accountCategoriesList.size() > 0) {
				response.put("accountCategoryList", accountCategoriesList);
				response.put("status", HttpStatus.OK.value());
			}
			
			else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}			
		}
		catch (Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Account Category details.");
		}
		return response;
	}



	@Override
	public Map<String, Object> fetchprojectInfo(String accountCategory) {
		Map<String, Object> response;
		List<ProjectDTO> projectInfoList;
		List<ProjectBO> projectBOList;
		response = new HashMap<String, Object>();
		try {
			projectInfoList = projectInformationRepository.fetchprojectInfo(accountCategory);
			if(projectInfoList.size() > 0) {
				projectDetailServiceImpl = new ProjectDetailServiceImpl();
				projectBOList = projectDetailServiceImpl.mapDtoToBo(projectInfoList);
				response.put("projectInfoList", projectBOList);
				response.put("status", HttpStatus.OK.value());
			}
			
			else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}			
		}
		catch (Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Project Infornation details.");
		}
		return response;
	}

		@Override
	public Map<String, Object> fetchProjectDetails(String accountCategory, String accountName, String projectName,
			String projectType, String yyyyMM) {
		
		System.out.println("<br>in fetchProjectDetails method==");
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		LocalDate startdate1=null;
		LocalDate enddate1=null;
		String startdate11=null;
		String enddate11=null;
		try {
						
			if(accountCategory.isEmpty() && accountName.isEmpty() && projectName.isEmpty() && projectType.isEmpty()) {
				
				accountCategory="%%";
				accountName="%%";
				projectName="%%";
				projectType="%%";				
				
			}else {
				
				accountCategory=accountCategory.isEmpty()? "%%": ("%"+accountCategory+"%").toLowerCase();
				accountName=accountName.isEmpty()? "%%": ("%"+accountName+"%").toLowerCase();
				projectName=projectName.isEmpty()? "%%": ("%"+projectName+"%").toLowerCase();
				projectType=projectType.isEmpty()? "%%": ("%"+projectType+"%").toLowerCase();					
				
			}
			
			if(yyyyMM.isEmpty()) {
				
				startdate11="01-Jan-1900";
				enddate11="31-Dec-2050";
			}
			else{
				
				startdate11="01-"+yyyyMM.split("-")[1]+"-"+yyyyMM.split("-")[0];
				enddate11="31-"+yyyyMM.split("-")[1]+"-"+yyyyMM.split("-")[0];
				System.out.println("<br>startdate11=="+startdate11);
				System.out.println("<br>enddate11=="+enddate11);				
			}
			
			startdate1=DateUtils.parseDate(startdate11);
			enddate1=DateUtils.parseDate(enddate11);
			System.out.println("<br>LocalDate startdate1=="+startdate1);
			System.out.println("<br>LocalDate enddate1=="+enddate1);
			
			/*String query="select p from ProjectDTO p where p.accountCategory= :accountCategory and p.accountName= :accountName "
					+ "and p.projectName= :projectName and p.projectType= :projectType and p.createdDate between :projectStartDate "
					+ "and :projectEndDate";
			System.out.println("<br>query=="+query);
			TypedQuery<ProjectDTO> typedQuery = em.createQuery(query , ProjectDTO.class);
			typedQuery.setParameter("accountCategory", accountCategory);
			typedQuery.setParameter("accountName", accountName);
			typedQuery.setParameter("projectName", projectName);
			typedQuery.setParameter("projectType", projectType);
			typedQuery.setParameter("projectStartDate", startdate1);
			typedQuery.setParameter("projectEndDate", enddate1);
			List<ProjectDTO> results = typedQuery.getResultList();*/
			List<ProjectDTO> projectDTOList=projectInformationRepository.fetchProjectDetails(accountCategory, accountName, projectName,
					projectType, startdate1, enddate1);
			System.out.println("<br>results=="+projectDTOList);
			
			if(projectDTOList.size()>0) {
				response.put("projectDetails", projectDTOList);
				response.put("status", HttpStatus.OK.value());
			}				
			else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}
			
		}catch(Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Project Infornation details.");
		}
		return response;
	}

	@Override
	public Map<String, Object> getPOSummaryDetails(String customerName, String projectStartDate, 
			String projectEndDate, String quote, String pId, String contract) {
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		List<ResourceFxBO> resourceFxBO=null;
		resourceFxBO=new ArrayList<ResourceFxBO>();
		String fxRateValue=null;
		
		int totalLeaves=0,totalHolidays=0;
		
		try {
									
			String startdate11=projectStartDate.split("-")[0]+"-"+projectStartDate.split("-")[1]+"-"+projectStartDate.split("-")[2];
			String enddate11=projectEndDate.split("-")[0]+"-"+projectEndDate.split("-")[1]+"-"+projectEndDate.split("-")[2];
			System.out.println("<br>startdate11=="+startdate11);
			System.out.println("<br>enddate11=="+enddate11);
			
			LocalDate startdate1=DateUtils.parseDate(startdate11);
			LocalDate enddate1=DateUtils.parseDate(enddate11);
			System.out.println("<br>LocalDate startdate1=="+startdate1);
			System.out.println("<br>LocalDate enddate1=="+enddate1);
			
			LocalDate projectStart=DateUtils.parseDate(projectStartDate);
			LocalDate projectEnd=DateUtils.parseDate(projectEndDate);
			System.out.println("<br>LocalDate projectStart=="+projectStart);
			System.out.println("<br>LocalDate projectEnd=="+projectEnd);
			
			try {

				//resourceFxBO=projectInformationRepository.getPOSummaryDetails(accountCategory, accountName, projectName, yyyyMMM, customerName, projectStartDate, projectEndDate, currency, pId, startdate1, enddate1);
				//System.out.println("<br>results=="+);
				String query="select NEW com.techm.po.model.bo.ResourceFxBO(rm.pId, r.associateId,r.associateName,"
						+ "rm.associateStartDate,rm.associateEndDate ,r.band, p.uof,"
						+ "rm.ratePerHour * cast((select fr.fxRate from FxRatesDTO fr where fr.fxDate = "
						+ "(select fx.fxDate from FxRatesDTO fx where fx.fxDate "
						+ "between :projectStart and :projectEnd and fx.status = 'ACTIVE') and fr.status='ACTIVE') as float), "
						+ "EXTRACT(DAY FROM rm.associateEndDate-now()) as releasedate ) "
						+ "from ResourceDTO r,ResourceMapDTO rm ,ProjectDTO p "
						+ "where r.associateId=rm.associateId and rm.pId=p.pid and "
						+ "p.pid= :pId and p.quote= :quote and p.contract= :contract";
				TypedQuery<ResourceFxBO> typedQuery = em.createQuery(query , ResourceFxBO.class);
				typedQuery.setParameter("projectStart", projectStart);
				typedQuery.setParameter("projectEnd", projectEnd);
				typedQuery.setParameter("quote", quote);
				typedQuery.setParameter("contract", contract);
				typedQuery.setParameter("pId", pId);				
				List<ResourceFxBO> results = typedQuery.getResultList();
				
				//List<ResourceFxBO> results=projectInformationRepository.getPOSummaryDetails(customerName, projectStart, 
						//projectEnd, quote, pId, contract);
				System.out.println("<br>results=="+results);
				if(results.size()>0) {
					response.put("projectInfoList", results);
					response.put("status", HttpStatus.OK.value());
				}				
				else {
					response.put("message", "No data found.");
					response.put("status", HttpStatus.NO_CONTENT.value());
				}
			}catch(Exception e) {
				e.printStackTrace();
				throw new InvalidServiceException("Exception occured while Associate Fx Rates details.");
			}
			
			//totalLeaves=projectInformationRepository.getAssociateLeaveDetails(startdate11, enddate11);
			//System.out.println("totalLeaves=="+totalLeaves);			
			
			//totalHolidays=projectInformationRepository.getAssociateMonthHolidays(projectName, mm, yyyy);			
		}catch(Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Project Infornation details.");
		}
		
		return response;
	}
	
//	@Override
//	public Map<String, Object> addPoApprovalDetails(PoApprovalsDTO approvalsDto) {
//		Optional<PoApprovalsDTO> approvalData;
//		Map<String, Object> response;
//		response = new HashMap<>();
//		
//		approvalData = poApprovalRepository.fetchPoApprovalsByMonthYear(approvalsDto.getAssociateId(), approvalsDto.getMonthYear());
//		if (approvalData.isPresent()) {
//			throw new InvalidServiceException("PO is already generated for the given month and year.");
//		} else {
//				try {
//					approvalsDto.setStatus(DefaultEnums.ACTIVE.name());
//					approvalsDto.setCreatedDate(LocalDateTime.now());
//					poApprovalRepository.save(approvalsDto);
//					response.put("message", "PO Approval details added successfully");
//					response.put("status", HttpStatus.CREATED.value());
//				} catch (Exception e) {
//					
//					throw new InvalidServiceException("Exception occured while adding PO Approval details.");
//				}
//			} 
//		return response;
//	}
//
//
//	@Override
//	public Map<String, Object> fetchProjectByCategory(String accountCategory, String accountName) {
//		Map<String, Object> response;
//		Optional<ProjectDTO> projectData;
//		ProjectBO projectBo;
//		List<ResourceDTO> resourcesDtoList;
//		
//		response = new HashMap<String, Object>();
//		try {
//			projectData = projectRepository.fetchProjectDetails(accountCategory, accountName);
//			resourcesDtoList = resourcesRepository.fetchResourceDetailsbyID(projectData.get().getPid());
//			if(projectData.isPresent()) {
//				projectBo = mapDtoToBo(projectData.get());
//				response.put("projectDetails", projectBo);
//			}
//			if(resourcesDtoList.size() != 0 && !resourcesDtoList.isEmpty()) {
//				response.put("resourcesDetails", resourcesDtoList);
//			}
//			else {
//				response.put("message", "No data found.");
//				response.put("status", HttpStatus.NO_CONTENT.value());
//			}			
//		}
//		catch (Exception e) {
//			throw new InvalidServiceException("Exception occured while fetching project and associate details.");
//		}
//		return response;
//	}
//
//	
//	private ProjectBO mapDtoToBo(ProjectDTO dto) throws ParseException {
//		ProjectBO bo;
//		bo = new ProjectBO();
//		bo.setAccountCategory(dto.getAccountCategory());
//		bo.setAccountName(dto.getAccountName());
//		bo.setProjectName(dto.getProjectName());
//		String startDate = DateUtils.reverseDateParsing(dto.getProjectStartDate().toString());
//		String endDate = DateUtils.reverseDateParsing(dto.getProjectEndDate().toString());
//		bo.setProjectStartDate(startDate);
//		bo.setProjectEndDate(endDate);
//		bo.setCustomerName(dto.getCustomerName());
//		bo.setProjectBillingCurrency(dto.getBillingCurrency());
//		return bo;	
//	}
}
