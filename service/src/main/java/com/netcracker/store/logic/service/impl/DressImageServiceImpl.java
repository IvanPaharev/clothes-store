package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.DressImageService;
import com.netcracker.store.persistence.dao.DressImageDao;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.DressImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.INTERFACES
)
@PropertySource("classpath:service.properties")
public class DressImageServiceImpl extends BaseServiceImpl<DressImage, Integer> implements DressImageService {
    private final DressImageDao dressImageDao;
    private byte[][] otherImagesBytes = new byte[100][];
    private byte[] mainImageBytes;

    @Value("${mainImagesPath}")
    private String mainImagesPath;

    @Value("${otherImagesPath}")
    private String otherImagesPath;

    public DressImageServiceImpl(DressImageDao dressImageDao) {
        super(dressImageDao);
        this.dressImageDao = dressImageDao;
    }

    @Override
    public void uploadMainImage(MultipartFile file) {
        try {
            mainImageBytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadOtherImage(MultipartFile file, int id) {
        try {
            if (id < otherImagesBytes.length) {
                otherImagesBytes[id] = file.getBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeTemporalImages(Dress dress) {
        String fileName;
        if (mainImageBytes != null) {
            try {
                fileName = dress.getId() + ".jpg";
                Path path = Paths.get(mainImagesPath + fileName);
                Files.write(path, mainImageBytes);
                mainImageBytes = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (otherImagesBytes.length > 0) {
            try {
                for (int i = 0; i < otherImagesBytes.length; i++) {
                    if (otherImagesBytes[i] != null) {
                        fileName = dress.getId() + "_" + i + ".jpg";
                        Path path = Paths.get(otherImagesPath + fileName);
                        Files.write(path, otherImagesBytes[i]);
                        add(new DressImage(fileName, dress));
                    }
                    otherImagesBytes[i] = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
