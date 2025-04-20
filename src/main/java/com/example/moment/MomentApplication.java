package com.example.moment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.moment.domain.Item;
import com.example.moment.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MomentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner test(ItemRepository itemRepository) {
        return args -> {
            itemRepository.save(new Item("열쇠"));
            System.out.println("아이템 저장 완료!");
        };
    }

}
