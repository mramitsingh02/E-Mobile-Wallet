package com.tester.spring.rest.webservices.repository;

import com.tester.spring.rest.webservices.repository.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
