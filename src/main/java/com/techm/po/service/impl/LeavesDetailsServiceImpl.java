package com.techm.po.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.techm.po.dao.LeavesDetailsRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.DefaultEnums;
import com.techm.po.model.bo.LeavesBO;
import com.techm.po.model.dto.LeavesDTO;
import com.techm.po.service.LeavesDetailsService;
import com.techm.po.utils.DateUtils;


@Service
public class LeavesDetailsServiceImpl implements LeavesDetailsService{
	
	@Autowired
	private LeavesDetailsRepository leavesDetailsRepository;
	
	
	@Override
	@Transactional
	public Map<String, Object> addLeavesDetails(LeavesBO LeavesBo){
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		Optional<LeavesDTO> leavesData;
		LeavesDTO dto = new  LeavesDTO();

		leavesData=leavesDetailsRepository.findByIdDate(LeavesBo.getAssociateId(),DateUtils.parseDate(LeavesBo.getLeaveDate()));
		if(leavesData.isPresent()) {
			response.put("message", "Leave Details already exist for "+ LeavesBo.getAssociateId() +" on "+LeavesBo.getLeaveDate()+".");
			response.put("status", HttpStatus.CONFLICT.value());
			return response;
		}
		else {
			try {
				dto.setStatus(DefaultEnums.ACTIVE.name());
				dto.setCreatedDate(LocalDate.now());
				dto.setAssociateId(LeavesBo.getAssociateId());
				dto.setAssociateName(LeavesBo.getAssociateName());
				dto.setRemarks(LeavesBo.getRemarks());
				dto.setLeaveDate(DateUtils.parseDate(LeavesBo.getLeaveDate()));
				dto.setCreatedBy(LeavesBo.getCreatedBy());
				leavesDetailsRepository.save(dto);
				response.put("message", "Leave details created successfully");
				response.put("status", HttpStatus.CREATED.value());
				return response;
			}
			catch (Exception e) {
				throw new InvalidServiceException("Exception occured while adding Leave.");
			}
		}		
	}
	public List<LeavesBO> mapDtoToBo(List<LeavesDTO> leavesData) throws ParseException {

		List<LeavesBO> leavesBoList = new ArrayList<LeavesBO>();
		for (LeavesDTO dto : leavesData) {
			LeavesBO leavesBO=new LeavesBO(dto.getLeaveId(), dto.getAssociateId(), dto.getAssociateName(), DateUtils.reverseDateParsing(dto.getLeaveDate().toString()), dto.getRemarks());
			leavesBoList.add(leavesBO);
		}
		return leavesBoList;
	}
	@Override
	@Transactional
	public Map<String, Object> fetchLeavesDetails(String id, String name, String monthYear){
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();		
		List<LeavesDTO> leavesData;

		List<LeavesBO> leavesBoList = new ArrayList<LeavesBO>();
		leavesData=new ArrayList<LeavesDTO>();
		String startdate;
		String enddate;

		if(id.isEmpty() && name.isEmpty() && monthYear.isEmpty())
		{
			id="% %";
			name="% %";
			startdate="% %";
			enddate="% %";
			
	try {
				
				//leavesData=leavesDetailsRepository.findAll();				
				leavesData=leavesDetailsRepository.findAllleavesinfo();
				if(leavesData.size()!=0 && !leavesData.isEmpty()) {
					leavesBoList = mapDtoToBo(leavesData);
					response.put("message", "Leaves Details Fetched Successfully");
					response.put("status", HttpStatus.OK.value());
					response.put("leavesDetails", leavesBoList);
				}else {
					response.put("message", "No Data Found");
					response.put("status", HttpStatus.NO_CONTENT.value());
				}
				
			}catch(Exception e) {
				throw new InvalidServiceException("Exception Occured While fetching Leaves Details");
			}
		}
		else
		{	
			id = id.isEmpty() ? "": ("%"+id+"%").toLowerCase();
			name = name.isEmpty() ? "": ("%"+name+"%").toLowerCase();
			if(monthYear.isEmpty()) {
				
				try {
					
					//leavesData=leavesDetailsRepository.findAll();				
					leavesData=leavesDetailsRepository.fetchLeavesDetailsdateempty(id, name);
					if(leavesData.size()!=0 && !leavesData.isEmpty()) {

						leavesBoList = mapDtoToBo(leavesData);
						response.put("message", "Leaves Details Fetched Successfully");
						response.put("status", HttpStatus.OK.value());
						response.put("leavesDetails", leavesBoList);
					}else {
						response.put("message", "No Data Found");
						response.put("status", HttpStatus.NO_CONTENT.value());
					}
					
				}catch(Exception e) {
					throw new InvalidServiceException("Exception Occured While fetching Leaves Details");
				}
			}else {
				

				String startdate11="01-"+monthYear.split("-")[0]+"-"+monthYear.split("-")[1];
				String enddate11="31-"+monthYear.split("-")[0]+"-"+monthYear.split("-")[1];
				LocalDate startdate1=DateUtils.parseDate(startdate11);
				LocalDate enddate1=DateUtils.parseDate(enddate11);
				
				try {
					
					//leavesData=leavesDetailsRepository.findAll();				
					leavesData=leavesDetailsRepository.fetchLeavesDetails(id, name,startdate1 ,enddate1);
					if(leavesData.size()!=0 && !leavesData.isEmpty()) {

						leavesBoList = mapDtoToBo(leavesData);
						response.put("message", "Leaves Details Fetched Successfully");
						response.put("status", HttpStatus.OK.value());
						response.put("leavesDetails", leavesBoList);
					}else {
						response.put("message", "No Data Found");
						response.put("status", HttpStatus.NO_CONTENT.value());
					}
					
				}catch(Exception e) {
					throw new InvalidServiceException("Exception Occured While fetching Leaves Details");
				}
			}
			
			
			

		}
		
		
	
//		}else {
//			try {
//				/*if(monthYear!=null) {
//					String[] mmyy=monthYear.split("-");
//					if(mmyy.length==2) {
//						mm=Integer.parseInt(mmyy[0]);
//						yy=Integer.parseInt(mmyy[1]);
//					}else {
//						throw new InvalidServiceException("You Entered Invalid Month and Year");
//					}
//				}*/
////				String startDate=null,endDate=null;
////				StringBuffer sb= new StringBuffer();
////				sb.append(monthYear);
////				sb.append("-01 00:00:00");
////				startDate=sb.toString();
////				
////				StringBuffer sb1= new StringBuffer();
////				sb1.append(monthYear);
////				sb1.append("-31 23:59:59");
////				endDate=sb1.toString();
//				
//				leavesData=leavesDetailsRepository.fetchLeavesDetails(id,name,startdate,enddate);
//				if(leavesData.size()!=0 && !leavesData.isEmpty()) {
//					response.put("message", "Leaves Details Fetched Successfully");
//					response.put("status", HttpStatus.OK.value());
//					response.put("leavesDetails", leavesData);
//				}else {
//					response.put("message", "No Data Found");
//					response.put("status", HttpStatus.NO_CONTENT.value());
//					response.put("leavesDetails", leavesData);
//				}
//				
//			}catch(Exception e) {
//				throw new InvalidServiceException("Exception Occured While fetching Leaves Details");
//			}
//		}
		
		return response;
	}
	
	@Override
	@Transactional	public Map<String, Object> updateLeavesDetails(LeavesBO LeavesBo){        
        Map<String, Object> response;
        response=new HashMap<String, Object>();
        Optional<LeavesDTO> leavesDetails;
        LeavesDTO leavesDetailsObj=null;
               leavesDetails=leavesDetailsRepository.findByIdLeaveDate(LeavesBo.getLeaveId());
               if(leavesDetails.isPresent()) {                     
                     try {
                            leavesDetailsObj=leavesDetails.get();                
                            System.out.println(leavesDetailsObj);
                            leavesDetailsObj.setLeaveDate(DateUtils.parseDate(LeavesBo.getLeaveDate()));
                            leavesDetailsObj.setRemarks(LeavesBo.getRemarks());
                            leavesDetailsObj.setModifiedDate(LocalDate.now());
                            leavesDetailsObj.setModifiedBy(LeavesBo.getModifiedBy());
                            leavesDetailsRepository.save(leavesDetailsObj);
                            response.put("message","Leaves Details Updated Successfully");
                            response.put("status",HttpStatus.OK.value());
                     }catch(Exception e) {
                            throw new InvalidServiceException("Exception occured while updating Leaves Details");
                     }                    
                     
               }else {
                     response.put("message","No Data Found");
                     response.put("status",HttpStatus.NO_CONTENT.value());                           
               }
               
 
        
        return response;
 }
	
	
	@Transactional
	@Override
	public Map<String, Object> deleteLeavesDetails(Integer id){
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		Optional<LeavesDTO> leavesDetails;
		LeavesDTO leavesDetailsObj=null;
		leavesDetails=leavesDetailsRepository.findByleaveId(id);
		if(leavesDetails.isPresent()) {
			try {
				
				leavesDetailsObj=leavesDetails.get();
				leavesDetailsObj.setModifiedDate(LocalDate.now());
				leavesDetailsObj.setStatus(DefaultEnums.INACTIVE.name());
				leavesDetailsRepository.save(leavesDetailsObj);			
				response.put("message","Leaves Details Deleted Successfully");
				response.put("Status",HttpStatus.OK.value());
			}
			catch(Exception e) {
				throw new InvalidServiceException("Exception occured while deleting Leaves Details");
			}
		}else {
			response.put("message","No details found for entered Associate Id");
			response.put("Status",HttpStatus.NO_CONTENT.value());
		}
		return response;

	}
	 
	 	
}
