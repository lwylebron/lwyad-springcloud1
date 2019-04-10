package lwy.ad.dao;

import lwy.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser,Long> {

    //根据用户名查找用户
    AdUser findByUsername(String username);

}
