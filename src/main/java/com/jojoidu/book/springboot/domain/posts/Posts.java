package com.jojoidu.book.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Tip : 어노테이션의 순서 : 주요 어노테이션을 클래스에 가깝게 둔다. 이러면 이후 새로운 언어 전환을 할 경우 필수가 아닌 어노테이션(여기서는 lombok의 어노테이션인 @Getter와 @NoArgsConstructor)을 쉽게 삭제할 수 있따.
// @Getter : 클래스 내 모든 필드의 Getter 메소드를 자동생성
// @NoArgsConstructor : 기본 생성자 자동 추가. public Posts(){}와 같은 효과
//  @Entity : 테이블과 링크될 클래스를 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더코어 네이밍(_)으로 테이블 이름을 매칭한다. ex) SalesManager.java -> sales_manager table
@Getter
@NoArgsConstructor
@Entity
public class Posts {
    /*
    Posts 클래스 : 실제 DB의 테이블과 매칭될 클래스 = Entity 클래스
    JPA를 사용할때 DB데이터를 작업할 경우 실제 쿼리를 날리지 않고, 이 Entity 클래스의 수정을 통해 작업한다.
    */

    @Id // @Id : 해당 테이블의 PK(기본키)필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue : PK생성 규칙을 나타낸다. 스프링부트 2.0에서는 Generation Type.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    private Long id;

    /*
    @column : 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다.
    기본값 외에 변경이 필요한 옵션이 있으면 사용한다.
    문자열의 경우 VARCHAR(255)가 기본 값인데 사이즈를 500으로 늘리고싶거나(ex:title),
    타입을 TEXT로 변경하고 싶거나(ex.content) 등의 경우에 사용한다.
     */

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // @Builder 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
