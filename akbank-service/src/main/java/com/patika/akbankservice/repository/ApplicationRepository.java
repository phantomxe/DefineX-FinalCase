package com.patika.akbankservice.repository;

import com.patika.akbankservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
   
    public List<Application> getByUserId(Long userId);
}
