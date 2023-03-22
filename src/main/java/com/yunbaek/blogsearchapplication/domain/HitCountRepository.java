package com.yunbaek.blogsearchapplication.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HitCountRepository extends JpaRepository<HitCount, Long> {
	Optional<HitCount> findByKeyword(String keyword);

	List<HitCount> findTop10ByOrderByCountDesc();
}
