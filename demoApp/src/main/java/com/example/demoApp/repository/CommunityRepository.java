package com.example.demoApp.repository;

import com.example.demoApp.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    //Any query goes here
}
