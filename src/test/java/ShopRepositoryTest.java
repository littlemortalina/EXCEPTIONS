import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShopRepositoryTest {
    ShopRepository repository = new ShopRepository();
    Product product1 = new Product(10, "book", 1500);
    Product product2 = new Product(11, "disk", 200);
    Product product3 = new Product(12, "notebook", 90);
    Product product4 = new Product(11, "pensil", 100);


    @Test
    public void ShouldRemovePositive() { // удаление существующей карточки товара
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.remove(11);
        Product[] expected = {product1, product3};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveWithException() { // удаление несуществующей карточки
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.remove(15);
        });

    }

    @Test
    public void ShouldUseExtensionAddDuplicate() { // добавление дубликата
        repository.add(product1);
        repository.add(product4);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product4);
        });
    }

    @Test
    public void ShouldAddProduct() { // добавление товара
        repository.add(product1);
        repository.add(product2);

        Product[] expected = {product1, product2};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }
}
