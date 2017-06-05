package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.DressImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
public interface DressImageService extends BaseService<DressImage, Integer> {
    void uploadMainImage(MultipartFile file);
    void uploadOtherImage(MultipartFile file, int id);
    void writeTemporalImages(Dress dress);
}
