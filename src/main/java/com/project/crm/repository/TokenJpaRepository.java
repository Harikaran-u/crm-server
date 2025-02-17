package com.project.crm.repository;

import com.project.crm.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<Token, Integer> {
    @Query("""
select t from Token t inner join User u on t.user.id = u.id
where t.user.id = :userId and t.loggedOut = false
""")
    List<Token> findAllAccessTokensByUser(Integer userId);
    Optional<Token> findByAccessToken(String token);

    Optional<Token > findByRefreshToken(String token);
}
