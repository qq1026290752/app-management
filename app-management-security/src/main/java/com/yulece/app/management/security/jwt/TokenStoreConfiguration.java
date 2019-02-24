package com.yulece.app.management.security.jwt;

import com.yulece.app.management.security.properties.SSOProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenStoreConfiguration {



    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "yichao.secuirty.oauth2",name = "storeType",havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
    @Configuration
    @ConditionalOnProperty(prefix = "yichao.secuirty.oauth2",name = "storeType",havingValue = "jwt",matchIfMissing = true)
    public static class JwtToKenConfiguration {

        @Autowired
        private SSOProperties zuulProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }


        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            //加密秘钥
            converter.setSigningKey(zuulProperties.getOauth().getoAuth2SigningKey());
            return converter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer() {
            AppTokenEnhancer appTokenEnhancer = new AppTokenEnhancer();
            return appTokenEnhancer;
        }
    }
}
