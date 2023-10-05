package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// 테이블명이 클래스명과 다를 경우
// @Table(name = "USER")
public class Member {

    @Id
    private Long id;
    
    // 필드명과 컬럼명이 다를 경우
    // @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
