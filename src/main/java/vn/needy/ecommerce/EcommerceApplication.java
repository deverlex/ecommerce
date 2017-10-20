package vn.needy.ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;

@SpringBootApplication
public class EcommerceApplication extends SpringBootServletInitializer {

	@Value("${needy.firebase.path}")
	private String firebaseFilePath;
	
	@Value("${needy.firebase.database.url}")
	private String firbaseDbUrl;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EcommerceApplication.class);
    }
	
	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+7:00"));
	    initializeFirebaseAuthConfig();
	}
	
	void initializeFirebaseAuthConfig() {
		URL fileUrl = EcommerceApplication.class.getClassLoader().getResource(firebaseFilePath);
		try {
			File file = new File(fileUrl.toURI());
			FileInputStream serviceAccount = new FileInputStream(file);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
					  .setDatabaseUrl(firbaseDbUrl)
					  .build();
			FirebaseApp.initializeApp(options);
			serviceAccount.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
}
