package com.c201.aebook.api.user.persistence.repository;

import com.c201.aebook.api.user.persistence.entity.RefreshRedisTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshRedisRepository extends CrudRepository<RefreshRedisTokenEntity, String> {
}
