package com.akash.recruitment.repository;

import com.akash.recruitment.entity.ApplicationEntity;
import com.akash.recruitment.dto.ApplicationCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    List<ApplicationEntity> findAllByFileIdOrderByIdDesc(Long fileId);

    @Query(nativeQuery = true, value = "" +
            "SELECT \n" +
            "    COUNT(CASE WHEN STATUS = 'Open' then 1 ELSE NULL END) as \"Open\",\n" +
            "    COUNT(CASE WHEN STATUS = 'Completed' then 1 ELSE NULL END) as \"Completed\",\n" +
            "    COUNT(CASE WHEN STATUS = 'Unreachable' then 1 ELSE NULL END) as \"Unreachable\",\n" +
            "    COUNT(CASE WHEN STATUS = 'Rejected' then 1 ELSE NULL END) as \"Rejected\",\n" +
            "    COUNT(CASE WHEN STATUS = 'AlreadyPurchased' then 1 ELSE NULL END) as \"AlreadyPurchased\"\n" +
            "from UPLOADED_APPLICATION_INFO")
    ApplicationCount getCount();

    void deleteAllByFileId(Long fileId);
}
