package com.example.giftgrower;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface JpaRepository<T, ID>
        extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {

        }




        /* ✿✿✿✿ JpaRepository 설명 읽어보아라. ✿✿✿✿
        JpaRepository 인터페이스를 보면 CRUD 뿐만 아니라 PagingSorting을 지원합니다.
        JpaRepository에서 지원하는 기본적인 메소드는 다음과 같습니다.
        save(): 레코드 저장
        findOne(): PK로 레코드 한 건 찾기
        findAll(): 전체 레코드 불러오기
        count(): 레코드 갯수
        delete(): 레코드 삭제
        */