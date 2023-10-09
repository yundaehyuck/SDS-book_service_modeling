package com.c201.aebook.api.painting.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c201.aebook.api.painting.persistence.entity.PaintingEntity;
import com.c201.aebook.api.painting.persistence.entity.PaintingType;

@Repository
public interface PaintingRepository extends JpaRepository<PaintingEntity, Long> {
	public Page<PaintingEntity> findByUserIdAndType(Long userId, PaintingType type, Pageable pageable);

	public void delete(PaintingEntity paintingEntity);

	public List<PaintingEntity> findTop12ByTypeOrderByCreatedAtDesc(PaintingType type);
}
