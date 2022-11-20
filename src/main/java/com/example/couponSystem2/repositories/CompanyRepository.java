package com.example.couponSystem2.repositories;

import com.example.couponSystem2.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    //isCompanyExists
    boolean existsCompanyByEmailAndPassword(String email, String password);

    boolean existsCompanyByEmailAndIdIsNot(String email, int id);
    //isCompanyExistsByNameOrEmail
    boolean existsCompanyByEmailOrName(String email, String name);

    boolean existsCompanyByIdAndName(String email, String name);


    //getCompanyID
    @Query(value = "select company_id from companies c where c.company_email = :email and c.company_password = :password", nativeQuery = true)
    int getCompanyID(@Param("email") String email, @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "update companies c set c.company_email = :email, c.company_password = :password where c.company_id = :id", nativeQuery = true)
    void updateCompany(@Param("email") String email, @Param("password") String password,@Param("id") int id);



//    @Query(value = "update companies c set c.company_email = ?1, c.company_password = ?2 where c.company_id = ?3", nativeQuery = true)
//    void updateCompany( String email, String password, int id);


}
