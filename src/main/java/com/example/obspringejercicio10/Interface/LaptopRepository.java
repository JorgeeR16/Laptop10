package com.example.obspringejercicio10.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.obspringejercicio10.Model.Laptop;
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>{
    
}
