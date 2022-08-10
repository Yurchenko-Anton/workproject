package com.example.LoadSource.SourceBase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceBaseRepository extends JpaRepository<SourceBase,Long> {
 SourceBase findFirstByOrderByStartTimestampDesc();
}
