package com.techm.po.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techm.po.model.dto.AccountCategoryDTO;

@Repository
public interface AccountCategoryRepository extends JpaRepository<AccountCategoryDTO, String> {

	@Query("select ac from AccountCategoryDTO ac where upper(ac.accountCategory)=:category "
			 + "and upper(ac.accountName)=:name")
	Optional<AccountCategoryDTO> fetchByNameAndCategory(@Param("category") String category,@Param("name") String name);
	
	
	@Query("select act from AccountCategoryDTO act where lower(act.accountCategory) like :accountCategory or lower(act.accountName) like :accountName and status = 'ACTIVE'")
	List<AccountCategoryDTO> fetchAccountCategoryBySearch(@Param("accountCategory") String accountCategory,@Param("accountName") String accountName);
		
	@Query(value="select * from tbl_account_category acc where acc.account_category_id=?1", nativeQuery=true)
	Optional<AccountCategoryDTO> findAccountCategory(String id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_account_category SET account_name=:accountName , modified_by=:modifiedBy , modified_date=:modifiedDate WHERE account_category_id=:id", nativeQuery=true)
	void updateAccountCategory(String accountName, String id,String modifiedBy,LocalDateTime modifiedDate);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_account_category SET status='INACTIVE' WHERE account_category_id=:accountCategoryId", nativeQuery=true)
	void deletAccountcategory(String accountCategoryId);
	
	@Query(value="Select * from tbl_account_category where status='ACTIVE'", nativeQuery=true)
	List<AccountCategoryDTO> fetchAccountCategoryData();
	
	@Query(value="Select distinct(tac.account_category) from tbl_account_category tac where tac.status='ACTIVE'", nativeQuery=true)
	List<String> fetchAccountCategories();
	
	@Query(value="select tac.account_name from tbl_account_category tac where tac.account_category=:accountCategory and tac.status='ACTIVE'", nativeQuery=true)
	List<String> fetchAccountCategoryNames(String accountCategory);
	
}
