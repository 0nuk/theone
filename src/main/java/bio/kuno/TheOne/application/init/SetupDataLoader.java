package bio.kuno.TheOne.application.init;

import bio.kuno.TheOne.adapters.output.repositories.dtos.RoleDatabaseDto;
import bio.kuno.TheOne.adapters.output.repositories.dtos.UserEntityDatabaseDto;
import bio.kuno.TheOne.adapters.output.repositories.jpainterfaces.PrivilegeRepository;
import bio.kuno.TheOne.adapters.output.repositories.jpainterfaces.RoleRepository;
import bio.kuno.TheOne.adapters.output.repositories.jpainterfaces.UserEntityRepository;
import bio.kuno.TheOne.adapters.output.repositories.dtos.PrivilegeDatabaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        PrivilegeDatabaseDto readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        PrivilegeDatabaseDto writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<PrivilegeDatabaseDto> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        RoleDatabaseDto adminRole = roleRepository.findByName("ROLE_ADMIN");
        UserEntityDatabaseDto user = new UserEntityDatabaseDto();
        user.setName("Test");
        user.setLastname("Test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    PrivilegeDatabaseDto createPrivilegeIfNotFound(String name) {

        PrivilegeDatabaseDto privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new PrivilegeDatabaseDto().builder().name(name).build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    RoleDatabaseDto createRoleIfNotFound(
            String name, Collection<PrivilegeDatabaseDto> privileges) {

        RoleDatabaseDto role = roleRepository.findByName(name);
        if (role == null) {
            role = new RoleDatabaseDto().builder().name(name).build();
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
