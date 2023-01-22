package com.akash.recruitment.service;

import com.akash.recruitment.config.ModelMapperUtils;
import com.akash.recruitment.dto.ApplicationCountDTO;
import com.akash.recruitment.dto.ApplicationDTO;
import com.akash.recruitment.dto.FileStatus;
import com.akash.recruitment.entity.ApplicationEntity;
import com.akash.recruitment.dto.ApplicationCount;
import com.akash.recruitment.repository.ApplicationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationDTO findById(Long id) {

        return ModelMapperUtils.map(applicationRepository.findById(id), ApplicationDTO.class);
    }

    public List<ApplicationDTO> getAll() {

        return ModelMapperUtils.mapAll(applicationRepository.findAll(Sort.by(Sort.Direction.DESC, "id")), ApplicationDTO.class);
    }

    public List<ApplicationDTO> getAllByFileId(Long fileId) {

        return ModelMapperUtils.mapAll(applicationRepository.findAllByFileIdOrderByIdDesc(fileId), ApplicationDTO.class);
    }

    public void saveAll(List<ApplicationDTO> applications, Long fileId) {

        List<ApplicationEntity> entities = ModelMapperUtils.mapAll(applications, ApplicationEntity.class);

        entities.forEach(e -> {
            e.setFileId(fileId);
            e.setStatus(FileStatus.Open.name());
            e.setInsertedAt(LocalDateTime.now());
            e.setUpdatedAt(LocalDateTime.now());
        });

        applicationRepository.saveAll(entities);
    }

    public void update(ApplicationDTO fileDTO) {

        ApplicationEntity entity = ModelMapperUtils.map(fileDTO, ApplicationEntity.class);
        entity.setUpdatedAt(LocalDateTime.now());

        applicationRepository.save(entity);
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }

    public ApplicationCountDTO getCount() {

        ApplicationCount count = applicationRepository.getCount();

        ApplicationCountDTO dto = new ApplicationCountDTO();
        dto.setOpen(count.getOpen());
        dto.setCompleted(count.getCompleted());
        dto.setRejected(count.getRejected());
        dto.setUnreachable(count.getUnreachable());
        dto.setAlreadyPurchased(count.getAlreadyPurchased());

        return dto;
    }

    public void deleteByFileId(Long fileId) {
        applicationRepository.deleteAllByFileId(fileId);
    }
}
