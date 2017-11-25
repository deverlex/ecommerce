package vn.needy.ecommerce.common.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

	@Value("${resource_dir.images}")
	private String dirResource;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public String storeImage(MultipartFile image) {
		// At this time, we will post to local folder 17/27/Tuesday/12
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/ww/E/HH");
		File file = new File(dirResource + "/" + sdf.format(Calendar.getInstance().getTime()));
		if (!file.exists()) {
			file.mkdirs();
		}
		
		try {
			File saveFile = new File(file.getAbsolutePath() + "/" + image.getOriginalFilename());
			image.transferTo(saveFile);
			return "rsv0001.needy.vn" + saveFile.getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteFile(String path) {
		// TODO Auto-generated method stub
		
	}

}
