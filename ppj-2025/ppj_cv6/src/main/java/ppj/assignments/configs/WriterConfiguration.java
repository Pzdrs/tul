package ppj.assignments.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ppj.assignments.writer.DevelWriter;
import ppj.assignments.writer.ProdWriter;
import ppj.assignments.writer.Writer;

@Configuration
public class WriterConfiguration {

    @Bean
    @Profile("prod")
    public Writer devWriter() {
        return new ProdWriter();
    }

    @Bean
    @Profile("dev")
    public Writer prodWriter() {
        return new DevelWriter();
    }
}
