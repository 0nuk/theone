package bio.kuno.TheOne;

import bio.kuno.TheOne.config.StorageProperties;
import bio.kuno.TheOne.ports.output.imagestorage.ImageStoragePort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//(exclude = { SecurityAutoConfiguration.class })
@EnableConfigurationProperties(StorageProperties.class)
public class TheOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheOneApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ImageStoragePort imageStoragePort) {
		return (args) -> {
			imageStoragePort.deleteAll();
			imageStoragePort.init();
		};
	}

}
