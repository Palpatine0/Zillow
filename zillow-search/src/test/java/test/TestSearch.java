package test;

import com.example.SearchApplication;
import com.example.dao.SearchDao;
import com.example.entity.Item;
import com.example.entity.Item4ES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
public class TestSearch {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SearchDao searchDao;

    @Value("${zillow.banner.nginx.prefix}")
    private String nginxPrefix;

    @Test
    public void ESInitTest() {
        List<Item> itemList = mongoTemplate.findAll(Item.class);
        ArrayList<Item4ES> arrayList = new ArrayList<>();
        for (Item item : itemList) {
            Item4ES item4ES = new Item4ES();
            item4ES.setId(item.getId());
            item4ES.setTitle(item.getTitle());
            item4ES.setCity(item.getCity());
            item4ES.setAptType(item.getHouseType());
            item4ES.setImgs(nginxPrefix + item.getImgs());
            item4ES.setPrice(String.valueOf(item.getPrice()));
            item4ES.setRentType(item.getRentType());
            arrayList.add(item4ES);
        }
        searchDao.batchInsertToES(arrayList);
    }
}
