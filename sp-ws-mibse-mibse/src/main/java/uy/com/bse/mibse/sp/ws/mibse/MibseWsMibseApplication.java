package uy.com.bse.mibse.sp.ws.mibse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.Log4J2MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {Log4J2MetricsAutoConfiguration.class})
public class MibseWsMibseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MibseWsMibseApplication.class, args);
	}

}
