package com.koushik.firstproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /public/upload/** URLs to files under /public/upload/ in the file system
        registry.addResourceHandler("/public/upload/**")
            .addResourceLocations("file:public/upload/")  // use 'file:' prefix for external folders
            .setCachePeriod(3600)                        // optional: cache static resources for 3600 seconds
            .resourceChain(true)                         // enable resource chain for versioning/caching
            .addResolver(new VersionResourceResolver()
                .addContentVersionStrategy("/**"));      // use content-hash versioning
    }
}
