package br.com.desafioviceri.controller;

import br.com.desafioviceri.model.User;
import br.com.desafioviceri.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*") // aceitar informaçao de qualquer lugar
@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    UserService userService;

    //SALVA UM NOVO USUARIO
    @ApiOperation(value = "Registrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário cadastrado!"),
            @ApiResponse(code = 400, message = "Erro! Verifique os parâmetros fornecidos.")
    })
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User newUser) {
        Optional<Object> registeredObject = userService.registerUser(newUser);

        if (registeredObject.isPresent()) {
            return ResponseEntity.status(201).body(registeredObject.get());
        } else {
            return ResponseEntity.status(400).build();
        }

    }

    //VERIFICANDO UM USUARIO NO BANCO
    @ApiOperation(value = "Veriificando um usuário no banco")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cadastro encontrado!"),
            @ApiResponse(code = 400, message = "Não foi encontrado nenhum cadastro com os parâmetros fornecidos.")
    })
    @PutMapping("/login")
    public ResponseEntity<Object> getCredentials(@Valid @RequestBody User user) {
        Optional<?> existentUser = userService.getCredentials(user);

        if (existentUser.isPresent()) {
            return ResponseEntity.status(201).body(existentUser.get());
        } else {
            return ResponseEntity.status(400).build();
        }

    }

    //BUSCA POR NOME
    @ApiOperation(value = "Buscando um cadastro pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Filtro de cadastro por nome"),
            @ApiResponse(code = 404, message = "Não foi encontrado nenhum cadastro com o nome digitado")
    })
    @GetMapping("/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List< User> findUserByName(@PathVariable ("name")String name){
        log.info("Listando usuários com o nome :[{}]", name);
        return userService.findByName(name);
    }

    //ATUALIZANDO CADASTRO
    @ApiOperation(value = "Atualizando um cadastro")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "cadastro atualizado com sucesso"),
            @ApiResponse(code = 404, message = "cadastro nao encontrado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUserById(@PathVariable(value = "id") Long id, @RequestBody User user) {
        log.info("Atualizando o cadastro : [{}]", id, user);
        return userService.updateUserById(user, id);
    }

    //DELETA CADASTRO
    @ApiOperation(value = "Excluindo um cadastro")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cadastro excluido com sucesso"),
            @ApiResponse(code = 404, message = "Nao foi possivel excluir o cadastro")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long id) {
        log.info("Excluindo cadastro do id :[{}]", id);
        return userService.deleteById(id);
    }

}
