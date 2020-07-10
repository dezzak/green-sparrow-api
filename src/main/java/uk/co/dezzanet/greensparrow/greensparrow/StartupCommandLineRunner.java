package uk.co.dezzanet.greensparrow.greensparrow;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import uk.co.dezzanet.greensparrow.greensparrow.seed.ProductSeeder;

@Service
@RequiredArgsConstructor
public class StartupCommandLineRunner implements CommandLineRunner {

    private final ProductSeeder productSeeder;

    @Override
    public void run(String... args) {
        productSeeder.seed();
    }
}
