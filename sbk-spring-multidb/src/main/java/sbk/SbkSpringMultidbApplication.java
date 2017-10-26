package sbk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SbkSpringMultidbApplication {

	public static void main(String[] args) {
    SpringApplication app = new SpringApplication(SbkSpringMultidbApplication.class);
    app.setAdditionalProfiles("secret");
    app.run(args);
  }
}
