package com.yunbaek.blogsearchapplication.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HiCountRepository extends JpaRepository<HitCount, Long> {
}
