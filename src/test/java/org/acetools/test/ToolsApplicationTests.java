package org.acetools.test;

import org.acetools.repository.ElementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ToolsApplicationTests {

    @Autowired
    private ElementRepository elementRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(elementRepository).isNotNull();
    }

}
