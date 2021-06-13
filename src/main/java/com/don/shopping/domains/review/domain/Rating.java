package com.don.shopping.domains.review.domain;

//Rating 클래스는 별점을 1~5까지만 줄수 있도록하기 위한 Enum 클래스

import lombok.Getter;

@Getter
public enum Rating {

    ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5);

    private  int value;

    //Rating(파라미터)로 받아내서 value값 저장
    Rating(int value){
        this.value = value;
    }
    //valueOf(파라미터)로 받아낸 rating값이 Rating의 값들을
    //for문으로 다꺼내서 받은 rating이랑 값이 같으면
    //Rating의 String값인 ONE,TWO...중의 값이 반환됨
    public static Rating valueOf(int rating){
        for(Rating r : Rating.values()){
            if(r.getValue() == rating)
                return r;
        }
        return null; //1~5값이 아니면 null반환

    }

}
