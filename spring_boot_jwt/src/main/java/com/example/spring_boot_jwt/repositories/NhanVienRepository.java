package com.example.spring_boot_jwt.repositories;

import com.example.spring_boot_jwt.models.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
	@Query(value = "SELECT * FROM MayBay u WHERE u.GaDen = "DAD", 
			nativeQuery = true)
	Collection<User> findDiemDenDaLat();
}
