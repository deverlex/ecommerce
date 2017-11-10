package vn.needy.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void init();

    void storeImage(MultipartFile file);
    
    void deleteFile(String path);
}
