package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.CompanyEnumException;
import com.example.couponSystem2.myException.enums.CouponEnumException;
import com.example.couponSystem2.repositories.CompanyRepository;
import com.example.couponSystem2.repositories.CouponRepository;
import com.example.couponSystem2.repositories.CustomerRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImplementation extends ClientService implements CompanyService {
    private int companyID;

    public CompanyServiceImplementation(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }


    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        if (!companyRepository.existsCompanyByEmailAndPassword(email, password)) {
            throw new CouponSystemException(CompanyEnumException.COMPANY_NOT_FOUND);
        }
        this.companyID = companyRepository.getCompanyID(email, password);
        return true;
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        if (couponRepository.existsCouponByCompany_IdAndTitle(coupon.getId(), coupon.getTitle()) ){
            throw new CouponSystemException(CouponEnumException.COUPON_TITLE_ALREADY_EXIST);
        }

        Coupon couponFromDB = couponRepository.save(coupon);
        return couponFromDB;
    }

    @Override
    public void updateCoupon(Coupon coupon) {

        if (coupon.getCompany().getId() != companyID) {
            throw new CouponSystemException(CompanyEnumException.CANT_UPDATE_COMPANY_ID);
        }
        couponRepository.updateCoupon(
                coupon.getTitle(),
                coupon.getDescription(),
                coupon.getStartDate(),
                coupon.getEndDate(),
                coupon.getAmount(),
                coupon.getPrice(),
                coupon.getImage(),
                coupon.getId());
    }

    @Override
    public void deleteCoupon(Coupon coupon) {
        if (!couponRepository.existsCouponByCompany_IdAndTitle(coupon.getId(), coupon.getTitle())) {
            throw new CouponSystemException(CouponEnumException.COUPON_NOT_FOUND);
        }
        couponRepository.deleteById(coupon.getId());
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        List<Coupon> couponsList = couponRepository.findAllByCompany_Id(companyID);
        if (couponsList.isEmpty()) {
            System.out.println("Get Company Coupons list is empty");
        }
        return couponsList;
    }

    @Override
    public List<Coupon> getCompanyCouponsByCategory(Category category) {
        List<Coupon> couponsList = couponRepository.findAllByCompany_IdAndCategory(companyID, category);
        if (couponsList.isEmpty()) {
            System.out.println("Get all company's coupons list by category is empty");
        }
        return couponsList;
    }

    @Override
    public List<Coupon> getCompanyCouponsMaxPrice(double maxPrice) {
        List<Coupon> couponsList = couponRepository.getAllCouponsByCustomerByMaxPrice(companyID, maxPrice);
        if (couponsList.isEmpty()) {
            System.out.println("Get all company's coupons by max price is empty");
        }
        return couponsList;
    }

    @Override
    public Company getCompanyDetails() {
        Company company = companyRepository.findById(companyID).get();
        company.setCoupons(getCompanyCoupons());
        return company;
    }
}
