package com.project.mini.repository;

import com.project.mini.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberRepository extends JpaRepository<Member,String> {

    boolean existsByUserid(String userid);

     Member findByUserid(String userid);

    @Query(value="select m.name from Member m where m.userid = :userid")
     String selectName(String userid);

}
