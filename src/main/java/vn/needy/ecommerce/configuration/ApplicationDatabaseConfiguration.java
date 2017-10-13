package vn.needy.ecommerce.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationDatabaseConfiguration {
	
	@Autowired
    private Environment enviroment;
     
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }    
 
    // Set for MySQL
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
 
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource)
    {
        return new DataSourceTransactionManager(dataSource);
    }
 
    @Bean
    public DataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(enviroment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(enviroment.getProperty("jdbc.url"));
        dataSource.setUsername(enviroment.getProperty("jdbc.username"));
        dataSource.setPassword(enviroment.getProperty("jdbc.password"));
        dataSource.setConnectionProperties(enviroment.getProperty("jdbc.connectionProperties"));
        
        // Connection pool MySQL
        HikariConfig config = new HikariConfig();
        config.setDataSource(dataSource);
        config.addDataSourceProperty("minimumIdle", "16");
        config.addDataSourceProperty("maximumPoolSize", "32");
    	config.addDataSourceProperty("cachePrepStmts", "true");
    	config.addDataSourceProperty("prepStmtCacheSize", "250");
    	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        
    	HikariDataSource ds = new HikariDataSource(config);
    	
        return ds;
    }
    
    // Set for Redis - using connection pool
    @Bean
	JedisPoolConfig jedisPoolConfig(
			@Value("${redis.pool.maxactive}") int maxActive,
			@Value("${redis.pool.maxidle}") int maxIdle,
			@Value("${redis.pool.minidle}") int minIdle) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxActive);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		return jedisPoolConfig;
	}

	@Bean
	RedisConnectionFactory connectionFactory(JedisPoolConfig poolConfig,
			@Value("${redis.host}") String host,
			@Value("${redis.port}") int port,
			@Value("${redis.timeout}") int timeout) {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(
				poolConfig);
		
		connectionFactory.setHostName(host);
		connectionFactory.setPort(port);
		connectionFactory.setTimeout(timeout);
		connectionFactory.setUsePool(true);
		return connectionFactory;
	}
	
	@Bean
	@Qualifier("StringRedisTemplate")
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
	
	@Bean
	@Qualifier("RedisTemplate")
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnectionFactory);

		// these are required to ensure keys and values are correctly serialized
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
	
    /// Security filter
    @Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		
		// return new CorsFilter(source);
		final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurerAdapter() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "GET", "OPTIONS");
			}
		};
	}
}
