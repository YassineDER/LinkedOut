package org.ichat.backend.repository;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;
import org.ichat.backend.model.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepoTest {
    @Autowired
    private RoleRepository roleRepository;

    // role to test
    private Roles roleInDatabase;

    @BeforeEach
    public void setUp() {
        Roles R = new Roles();
        R.setName("ROLE_USER");
        R.setPermissions("READ,WRITE");
        roleInDatabase = roleRepository.save(R);
    }

    // CRUD
    @Test
    public void testCreateRole() {
        assertThat(roleInDatabase).isNotNull();
        assertThat(roleInDatabase.getRole_id()).isGreaterThan(0);
    }

    @Test
    public void testReadRole() {
        Roles R = new Roles();
        R.setRole_id(23);
        R.setName("ROLE_USER");
        R.setPermissions("READ,WRITE");

        Roles fetched = roleRepository.findById(roleInDatabase.getRole_id()).get();
        boolean notThere = roleRepository.findById(23).isPresent();

        assertThat(fetched).isNotNull();
        assertThat(fetched.getRole_id()).isEqualTo(roleInDatabase.getRole_id());
        assertThat(notThere).isFalse();
    }

    @Test
    public void testReadAllRoles() {
        assertThat(roleRepository.findAll()).isNotEmpty();
        assertThat(roleRepository.findAll()).contains(roleInDatabase);
        assertThat(roleRepository.findAll()).size().isEqualTo(1);
    }

    @Test
    public void testUpdateRole() {
        roleInDatabase.setName("ROLE_ADMIN");
        roleRepository.save(roleInDatabase);

        Roles updated = roleRepository.findById(roleInDatabase.getRole_id()).get();
        assertThat(updated.getName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    public void testDeleteRole() {
        roleRepository.delete(roleInDatabase);
        assertThat(roleRepository.findById(roleInDatabase.getRole_id())
                .isPresent()).isFalse();
    }

    @Test
    public void testDeleteAllRoles() {
        Roles R = new Roles();
        R.setName("ROLE_USER1");
        R.setPermissions("READ,WRITE");
        roleRepository.save(R);
        roleRepository.deleteAll();
        assertThat(roleRepository.findAll()).isEmpty();
    }

    @Test
    public void testFindByName() {
        Roles fetched = roleRepository.findByName(roleInDatabase.getName()).get();
        assertThat(fetched).isNotNull();
        assertThat(fetched.getRole_id()).isEqualTo(roleInDatabase.getRole_id());
    }
}
