package com.akash.recruitment.service;

import com.akash.recruitment.config.ModelMapperUtils;
import com.akash.recruitment.dto.ApplicationDTO;
import com.akash.recruitment.repository.FileRepository;
import com.akash.recruitment.dto.FileDTO;
import com.akash.recruitment.entity.FileEntity;
import com.akash.recruitment.excepion.CustomException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    private final ApplicationService applicationService;

    public FileService(FileRepository fileRepository, ApplicationService applicationService) {
        this.fileRepository = fileRepository;
        this.applicationService = applicationService;
    }

    public List<FileDTO> getAll() {

        return ModelMapperUtils.mapAll(fileRepository.findAll(Sort.by(Sort.Direction.DESC, "id")), FileDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        fileRepository.deleteById(id);
        applicationService.deleteByFileId(id);
    }

    public void upload(MultipartFile file) throws CustomException {

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(file.getOriginalFilename());

        List<ApplicationDTO> applications = new ArrayList<>();

        try {
            Workbook wb = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);


            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

                Row row = sheet.getRow(i);

                String email = row.getCell(0).getStringCellValue();
                String fullName = row.getCell(1).getStringCellValue();
                String mobileNo = row.getCell(2).getStringCellValue();

                applications.add(new ApplicationDTO(fileDTO.getId(), email, fullName, mobileNo));
            }
        } catch (Exception e) {
            throw new CustomException("Error occurred while uploading file. Please choose suitable format file.", e);
        }

        fileDTO.setTotalApplication((long) applications.size());

        Long fileId = save(fileDTO);

        applicationService.saveAll(applications, fileId);

    }

    private Long save(FileDTO fileDTO) {
        FileEntity entity = ModelMapperUtils.map(fileDTO, FileEntity.class);
        entity.setInsertedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return fileRepository.save(entity).getId();
    }
}
