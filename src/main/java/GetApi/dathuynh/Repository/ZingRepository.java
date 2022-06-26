package GetApi.dathuynh.Repository;

import GetApi.dathuynh.Entity.ZingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZingRepository extends JpaRepository<ZingEntity, Long> {
    ZingEntity findByPass(long pass);
}
