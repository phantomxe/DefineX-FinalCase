package com.patika.garantiservice.repository;
 
import com.patika.garantiservice.entity.Application;
import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
   
    public List<Application> getByUserId(Long userId);
}
