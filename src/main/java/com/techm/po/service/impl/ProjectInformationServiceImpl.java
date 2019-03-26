package com.techm.po.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techm.po.dao.LeavesDetailsRepository;
import com.techm.po.dao.ProjectInformationRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.LeavesBO;
import com.techm.po.model.bo.ProjectBO;
import com.techm.po.model.bo.ProjectDetailsBO;
import com.techm.po.model.bo.ResourceFxBO;
import com.techm.po.model.dto.LeavesDTO;
import com.techm.po.model.dto.ProjectDTO;
import com.techm.po.service.ProjectInformationService;
import com.techm.po.utils.DateUtils;

@Service
public class ProjectInformationServiceImpl implements ProjectInformationService{
	
	@Autowired
	private ProjectInformationRepository projectInformationRepository;
	
	@Autowired
	private ProjectDetailServiceImpl projectDetailServiceImpl;
	
	@Autowired
	private LeavesDetailsRepository leavesDetailsRepository;
	
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
		List<ProjectBO> projectBOList;
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
				
				accountCategory=accountCategory.isEmpty()? "": ("%"+accountCategory+"%").toLowerCase();
				accountName=accountName.isEmpty()? "": ("%"+accountName+"%").toLowerCase();
				projectName=projectName.isEmpty()? "": ("%"+projectName+"%").toLowerCase();
				projectType=projectType.isEmpty()? "": ("%"+projectType+"%").toLowerCase();	
				
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
			/*
			 * System.out.println("<br>LocalDate startdate1=="+startdate1);
			 * System.out.println("<br>LocalDate enddate1=="+enddate1);
			 * System.out.println("<br>LocalDate accountCategory=="+accountCategory);
			 * System.out.println("<br>LocalDate accountName=="+accountName);
			 * System.out.println("<br>LocalDate projectName=="+projectName);
			 * System.out.println("<br>LocalDate projectType=="+projectType);
			 */
			
			/*String query="select p from ProjectDTO p where p.accountCategory= :accountCategory and p.accountName= :accountName "
					+ "and p.projectName= :projectName and p.projectType= :projectType and p.createdDate between :projectStartDate "
					+ "and :projectEndDate";*/
			String query="select NEW com.techm.po.model.bo.ProjectDetailsBO(p.accountCategory, p.accountName, "
					+ "p.projectName, p.customerName, p.customerSpoc, p.approvalMethod, p.submissionMode, "
					+ "p.projectType, p.billingCurrency, p.poAmount,p.projectStartDate,p.projectEndDate, "
					+ "pc.uom, p.deliverySpoc, p.effortSpoc, p.pid, pc.quote, pc.contractNumber, pc.po, "
					+ "p.createdBy, p.createdDate, p.modifiedBy, p.modifiedDate, p.status) "
					+ "from ProjectDTO p,ProjectContractDTO pc where lower(p.accountCategory) like :accountCategory "
					+ "and lower(p.accountName) like :accountName and lower(p.projectName) like :projectName "
					+ "and lower(p.projectType) like :projectType and p.projectStartDate <= :projectStartDate  and p.projectEndDate >= :projectEndDate "
					+ "and pc.pid=p.pid";
			System.out.println("<br>query=="+query);
			TypedQuery<ProjectDetailsBO> typedQuery = em.createQuery(query , ProjectDetailsBO.class);
			typedQuery.setParameter("accountCategory", accountCategory);
			typedQuery.setParameter("accountName", accountName);
			typedQuery.setParameter("projectName", projectName);
			typedQuery.setParameter("projectType", projectType);
			typedQuery.setParameter("projectStartDate", startdate1);
			typedQuery.setParameter("projectEndDate", enddate1);
			List<ProjectDetailsBO> results = typedQuery.getResultList();
			System.out.println("<br>results in service impl=="+results);
			//List<ProjectDTO> projectDTOList=projectInformationRepository.fetchProjectDetails(accountCategory, accountName, projectName,
					//projectType, startdate1, enddate1);
			//System.out.println("<br>results=="+projectDTOList);
			
			//ProjectBO projectBOObj=new ProjectBO();
			projectDetailServiceImpl = new ProjectDetailServiceImpl();
			projectBOList = projectDetailServiceImpl.projectDetailsToBo(results);
			if(results.size()>0) {
				
				response.put("projectDetails", projectBOList);
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
	public Map<String, Object> getPOSummaryDetails(String customerName, String quote, String pId, String contract, String yyyyMM) {
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		List<ResourceFxBO> resourceFxBO=null;
		resourceFxBO=new ArrayList<ResourceFxBO>();
		String fxRateValue=null;
		String startdate11=null,enddate11=null;
		LocalDate startdate1=null,enddate1=null; 
		List<LeavesDTO> leavesBOList;
		leavesBOList=new ArrayList<LeavesDTO>();
		Map<String,Integer> leaveHolidaysMap=new HashMap<String,Integer>();
		try {
			Map<String,String> mmMap=new HashMap<String,String>();
			mmMap.put("Jan", "january");	mmMap.put("Feb", "february");
			mmMap.put("Mar", "march");		mmMap.put("Apr", "april");
			mmMap.put("May", "may");		mmMap.put("Jun", "June");
			mmMap.put("Jul", "july");		mmMap.put("Aug", "august");
			mmMap.put("Sep", "september");	mmMap.put("Oct", "october");
			mmMap.put("Nov", "november");	mmMap.put("Dec", "december");
			String month=mmMap.get(yyyyMM.split("-")[1]);
			String year=yyyyMM.split("-")[0];
			startdate11="01-"+yyyyMM.split("-")[1]+"-"+yyyyMM.split("-")[0];
			enddate11="31-"+yyyyMM.split("-")[1]+"-"+yyyyMM.split("-")[0];						
			
			  
		  System.out.println("<br>startdate11=="+startdate11);
		  System.out.println("<br>enddate11=="+enddate11);
		  
		  startdate1=DateUtils.parseDate(startdate11);
		  
		  enddate1=DateUtils.parseDate(enddate11);
		  System.out.println("<br>LocalDate startdate1=="+startdate1);
		  System.out.println("<br>LocalDate enddate1=="+enddate1); 
			
			try {
				String associate=null;int count=0,monthHolidays=0;
				//resourceFxBO=projectInformationRepository.getPOSummaryDetails(accountCategory, accountName, projectName, yyyyMMM, customerName, projectStartDate, projectEndDate, currency, pId, startdate1, enddate1);
				System.out.println("<br>results=="+startdate1+" "+enddate1+" "+quote+" "+contract+" "+pId+" "+month+" "+year);
				String query="select NEW com.techm.po.model.bo.ResourceFxBO(rm.pId, r.associateId,r.associateName,"
						+ "rm.associateStartDate,rm.associateEndDate ,r.band, pc.uom,"
						+ "rm.ratePerHour * cast((select fr.fxRate from FxRatesDTO fr where fr.fxDate = "
						+ "(select fx.fxDate from FxRatesDTO fx where fx.fxDate "
						+ "between :projectStart and :projectEnd and fx.status = 'ACTIVE') and fr.status='ACTIVE') as float), "
						+ "EXTRACT(DAY FROM rm.associateEndDate-now()) as releasedate ) "
						+ "from ResourceDTO r,ResourceMapDTO rm ,ProjectDTO p,ProjectContractDTO pc "
						+ "where r.associateId=rm.associateId and rm.pId=p.pid and p.pid=pc.pid and "
						+ "p.pid= :pId and pc.quote= :quote and pc.contractNumber= :contract";
				TypedQuery<ResourceFxBO> typedQuery = em.createQuery(query , ResourceFxBO.class);
				typedQuery.setParameter("projectStart", startdate1);
				typedQuery.setParameter("projectEnd", enddate1);
				typedQuery.setParameter("quote", quote);
				typedQuery.setParameter("contract", contract);
				typedQuery.setParameter("pId", pId);				
				List<ResourceFxBO> results = typedQuery.getResultList();
				
				String q="select l.associateId as associateId,count(l),h."+month+" from LeavesDTO as l,HolidaysDTO h "
						+ "where h.year= :year and l.leaveDate between :startdate1 and :enddate1 and l.status='ACTIVE' group by (l.associateId,h."+month+")";
				TypedQuery<Object[]> typeQuery = em.createQuery(q,Object[].class);
				typeQuery.setParameter("startdate1", startdate1);
				typeQuery.setParameter("enddate1", enddate1);
				typeQuery.setParameter("year", year);
				List<Object[]> results12=typeQuery.getResultList();
				for (Object[] result : results12) {
				    associate = (String) result[0];
				    count = ((Number) result[1]).intValue();
				    monthHolidays = ((Number) result[2]).intValue();
				    leaveHolidaysMap.put((String) result[0],((Number) result[1]).intValue());				    
				}
				
				//List<ResourceFxBO> results=projectInformationRepository.getPOSummaryDetails(customerName, projectStart, 
						//projectEnd, quote, pId, contract);
				//leavesBOList=leavesDetailsRepository.getAssociateLeaveDetails(startdate1, enddate1);
				/*String query1="select l.associateId as associate, count(l) as count from LeavesDTO as l where l.leaveDate between :startdate1 and :enddate1 group by (l.associateId)";
				TypedQuery<Object[]> typedQuery1 = (TypedQuery<Object[]>) em.createQuery(query1);
				typedQuery1.setParameter("startdate1", startdate1);
				typedQuery1.setParameter("enddate1", enddate1);
				List<Object[]> leavesBOList = typedQuery1.getResultList();*/
				
				/*List<Object[]> results1 = em.createQuery("select l.associateId as associate, count(l) as count from LeavesDTO as l where l.leaveDate between :startdate1 and :enddate1 group by (l.associateId)").getResultList();
				for (Object[] result : results1) {
				    String associate = (String) result[0];
				    int count = ((Number) result[1]).intValue();
				}*/
				System.out.println("monthHolidays=="+monthHolidays);
				System.out.println("totalLeaves=="+leaveHolidaysMap);
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
