package com.c201.aebook.api.user.persistence.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("RefreshToken")
public class RefreshRedisTokenEntity {

    @Id
    private String usersId;
    private String token;

    private RefreshRedisTokenEntity(String usersId, String token) {
        this.usersId = usersId;
        this.token = token;
    }

    public static RefreshRedisTokenEntity createToken(String usersId, String token) {
        return new RefreshRedisTokenEntity(usersId, token);
    }

    public void reissue(String token) {
        this.token = token;
    }

}
