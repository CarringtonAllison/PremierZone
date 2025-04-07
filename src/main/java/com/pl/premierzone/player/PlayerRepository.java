package com.pl.premierzone.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    void deleteByPlayer_name(String player_name);
    Optional<Player> findByPlayer_name(String player_name);
}
