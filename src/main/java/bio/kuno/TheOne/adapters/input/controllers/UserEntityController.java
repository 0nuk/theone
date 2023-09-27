package bio.kuno.TheOne.adapters.input.controllers;

import bio.kuno.TheOne.domain.UserEntityDto;
import bio.kuno.TheOne.ports.output.repositories.UserEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Service
@RequestMapping("/user")
public class UserEntityController {
    @Autowired
    UserEntityPort userEntityPort;

    @PostMapping("/save")
    public ResponseEntity<UserEntityDto> saveUser(@RequestBody UserEntityDto request) {
        return ResponseEntity.ok(userEntityPort.save(request));
    }

    @PostMapping("/update")
    public ResponseEntity<UserEntityDto> updateUser(@RequestBody UserEntityDto request) {
        return ResponseEntity.ok(userEntityPort.save(request));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserEntityDto> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userEntityPort.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntityDto>> getAll() {
        return ResponseEntity.ok(userEntityPort.getAll());
    }
}
