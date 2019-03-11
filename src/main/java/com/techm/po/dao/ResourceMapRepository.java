package com.techm.po.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techm.po.model.dto.ResourceMapDTO;

public interface ResourceMapRepository extends JpaRepository<ResourceMapDTO, Integer>{
@Query(value="UPDATE tbl_resource_map SET associate_start_date=:startdate, associate_end_date=:enddate,location=:location,linked=:linked,p_id=:pid,rate_per_hour=:ratePerHour WHERE resource_map_id=:resourceMapId", nativeQuery=true)
void updateProjectinfo(Integer resourceMapId, String location, LocalDate startdate, LocalDate enddate, String linked,
		String pid, int ratePerHour);


}
