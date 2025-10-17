package cristinamastellaro.BE_U2_S2_Test.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudConfig(
            @Value("${cloudinary.name}") String cloud_name,
            @Value("${cloudinary.key}") String api_key,
            @Value("${cloudinary.secret}") String api_secret
    ) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloud_name);
        config.put("api_key", api_key);
        config.put("api_secret", api_secret);
        return new Cloudinary(config);
    }
}
