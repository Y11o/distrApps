package app.distr.hospital.cholecystitis;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@RefreshScope
public class LabDistrAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabDistrAppApplication.class, args);
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		Locale rus = new Locale("ru", "RU");
		localeResolver.setDefaultLocale(rus);
		return localeResolver;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	InfoContributor getInfoContributor() {
		Map<String, Object> details = new HashMap<>();
		details.put("nameApp", "Cholecystitis");
		details.put("developers", "0308.13 yllo&ceciliomanchini");
		details.put("email", "shakhmardanov.ilnur@mail.ru");
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put("info", details);
		return new MapInfoContributor(wrapper);
	}

}
