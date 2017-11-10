package vn.needy.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void init();

    String storeImage(MultipartFile image);
    
    void deleteFile(String path);
}
