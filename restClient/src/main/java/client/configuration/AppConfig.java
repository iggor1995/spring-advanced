package client.configuration;

        import java.util.ArrayList;
        import java.util.List;

        import client.viewResolver.PdfViewResolver;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.MediaType;
        import org.springframework.web.accept.ContentNegotiationManager;
        import org.springframework.web.servlet.ViewResolver;
        import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
        import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
        import org.springframework.web.servlet.view.InternalResourceViewResolver;
        import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(
                MediaType.TEXT_HTML);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(jspViewResolver());
        resolvers.add(pdfViewResolver());
        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}