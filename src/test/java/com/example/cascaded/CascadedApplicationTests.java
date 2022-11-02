package com.example.cascaded;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CascadedApplicationTests {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	ItemRepository itemRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void canCascade(){
		Player player = new Player();
		Item sword = new Item("Sword");
		Item shield = new Item("Shield");
		player.addItem(sword);
		player.addItem(shield);
		// this will cascade and items are saved too
		playerRepository.save(player);

		player.removeItem(shield);
		playerRepository.save(player);
		// annoyingly saving the item is necessary
		// the player now has no knowledge
		// of the item due to - this.items.remove(item);
		// so saving the player can't know to save the item...
		itemRepository.save(shield);
	}

}
