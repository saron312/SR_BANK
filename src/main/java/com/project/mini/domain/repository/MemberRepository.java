package com.project.mini.domain.repository;

import com.project.mini.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByUserId(String userId);
    boolean existsByUserId(String userId);

    List<Member> findAllByUserIdNotIn(Collection<String> userId);


}
