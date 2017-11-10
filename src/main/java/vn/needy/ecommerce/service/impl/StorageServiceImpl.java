package vn.needy.ecommerce.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.needy.ecommerce.service.StorageService;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeImage(MultipartFile file) {
		// TODO Auto-generated method stub
//		FileOutputStream fos = null;
//		System.out.println("MultipartFile? " + file.getOriginalFilename());
		File convFile = new File("/home/lion/" + file.getOriginalFilename());
		try {
			file.transferTo(convFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    try {
//			convFile.createNewFile();
//			fos = new FileOutputStream(convFile); 
//		    fos.write(file.getBytes());
//		    fos.flush();
//		    fos.close();
//		    Writer output = new BufferedWriter(new FileWriter(convFile));
//		    output.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	}

	@Override
	public void deleteFile(String path) {
		// TODO Auto-generated method stub
		
	}

}
