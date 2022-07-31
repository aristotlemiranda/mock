package sg.mmaris.batch.dummy;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class DummyApplication implements ApplicationRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DummyApplication.class, args);
		int exitCode = SpringApplication.exit(run);
		System.exit(exitCode);
	}


	@Override
	public void run(ApplicationArguments args) {
		Optional.ofNullable(args.getOptionValues("jobName").get(0)).filter(s -> s.equals("SOD")
				|| s.equals("OPS") || s.equals("CUT")).ifPresent(s -> {
			System.out.println("Running JOB " + s);
			try {
				String interval = args.getOptionValues("interval").get(0);
				Thread.sleep(Integer.valueOf(interval));
				if(s.equals("SOD")) System.exit(0);
				System.exit(-32);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
