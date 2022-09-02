package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //상속관계 매핑이라서 상속관계 전략을 지정해야됨 그걸 부모 클래스에 잡아준다
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직==//     //재고를 넣고빼는 로직은 stockQuantity 정보를 사용하는데 이게
    //아이템 entity 가 가지고있기 때문에 핵심 비지니스 로직을 entity 에 직접넣는게 이득

    //재고 수량 증가 로직
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고 수량 감소 로직
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    //


}
