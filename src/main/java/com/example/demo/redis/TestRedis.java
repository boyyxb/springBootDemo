package com.example.demo.redis;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableCaching
public class TestRedis extends CachingConfigurerSupport {
	
	  
	/**
	 * 生成Key
	 */
	@Bean
	public KeyGenerator keyGenerator(){
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object obj, Method method, Object... param) {
				// TODO Auto-generated method stub
				StringBuilder bl = new StringBuilder();
				bl.append(obj.getClass().getName());
				bl.append(method.getName());
				for(Object o : param){
					bl.append(o.toString());
				}
				return bl.toString();
			}
		};
	}
	
	/**
	 * 管理缓存
	 * @param redisTemplate
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate){
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		return rcm;
	}
	
	/**
	 * redisTemplat 配置
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
		
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

//		
//		StringRedisTemplate conn = new StringRedisTemplate();
//		Jackson2JsonRedisSerializer jack = new Jackson2JsonRedisSerializer(Object.class);
//		ObjectMapper mapp = new ObjectMapper();
//		mapp.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
//		mapp.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jack.setObjectMapper(mapp);
//		conn.setValueSerializer(jack);
//		conn.afterPropertiesSet();
        return template;
	}
	

}
