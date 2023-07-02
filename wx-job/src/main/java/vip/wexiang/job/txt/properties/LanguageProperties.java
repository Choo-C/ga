package vip.wexiang.job.txt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "map")
public class LanguageProperties {
    private Map<String, String> languages = new HashMap<>();
    public Map<String, String> getLanguages() {
        return languages;
    }
    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }
}

