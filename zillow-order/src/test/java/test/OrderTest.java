package test;

import com.example.OrderApplication;
import com.example.dao.OrderDao;
import com.example.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = OrderApplication.class)
@RunWith(SpringRunner.class)
public class OrderTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderDao orderDao;

    @Test
    public void getOrdersByUserId() {
        breaksStart();
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is("6569a6ab668974488c1f6568"));

        List<Order> orderList = mongoTemplate.find(query,Order.class);
        for (Order order : orderList) {
            System.out.println(order);
        }
        breaksEnd();
    }

    @Test
    public void getOrders() {
        breaksStart();
        List<Order> orderList = mongoTemplate.findAll(Order.class);
        for (Order order : orderList) {
            System.out.println(order);
        }
        breaksEnd();
    }

    public void breaksStart(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------CHECK--------------------------------------------------");
    }
    public void breaksEnd(){
        System.out.println("---------------------------------------------CHECK--------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
