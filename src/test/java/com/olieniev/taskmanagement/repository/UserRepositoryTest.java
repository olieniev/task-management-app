package com.olieniev.taskmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("""
        Find user by username returns expected user
            """)
    @Sql(scripts = "classpath:database/user/insert-two-users.sql")
    @Sql(scripts = "classpath:database/user/delete-users.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void check_findByUsername_returnsExpectedUser() {
        Long actual = userRepository.findByUsername("testusername2").get().getId();
        Long expected = 300L;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("""
        User exists by email returns true when user exists
            """)
    @Sql(scripts = "classpath:database/user/insert-two-users.sql")
    @Sql(scripts = "classpath:database/user/delete-users.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void check_existsByEmail_returnTrueWhenExists() {
        assertTrue(userRepository.existsByEmail("test2@email.com"));
    }

    @Test
    @DisplayName("""
        User exists by username returns true when user exists
            """)
    @Sql(scripts = "classpath:database/user/insert-two-users.sql")
    @Sql(scripts = "classpath:database/user/delete-users.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void check_existsByUsername_returnTrueWhenExists() {
        assertTrue(userRepository.existsByUsername("testusername1"));
    }
}
