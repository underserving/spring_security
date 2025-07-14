package com.security.securityApplicaton.repos;

//import com.security.securityApplicaton.models.UserPrincipal;
import com.security.securityApplicaton.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    public Users findByUserName(String name);
}
