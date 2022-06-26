package GetApi.dathuynh.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ZingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cookies_id", nullable = false, updatable = false)
    private long cookies_id;

    @Column(name = "zmp3_rqid", nullable = false)
    private String zmp3_rqid;

    @Column(name = "zmp3_sid", nullable = false)
    private String zmp3_sid;

    @Column(name = "zpsid", nullable = false)
    private String zpsid;

    @Column(name = "pass", nullable = false)
    private long pass;
}
