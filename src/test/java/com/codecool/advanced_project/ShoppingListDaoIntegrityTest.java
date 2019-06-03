package com.codecool.advanced_project;

import com.codecool.advanced_project.entity.ShoppingListEntity;
import com.codecool.advanced_project.repository.ShoppingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShoppingListDaoIntegrityTest {

    private String shopListTable = "shopping_list";

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @BeforeEach
    void clear() {
        //sequence do not start from 1, just items are deleted
        shoppingListRepository.deleteAll();
    }

    @ParameterizedTest
    @ValueSource(longs = {1,33,456,912,Long.MAX_VALUE})
    void testSaveNewShoppingListWritesCorrectGroupId(Long groupId) {
        ShoppingListEntity shoppingList = ShoppingListEntity.builder()
                                                            .groupId(groupId)
                                                            .build();

        shoppingListRepository.save(shoppingList);
        assertNotNull(shoppingListRepository.getOne(groupId));
    }
}
