package br.csi.gestao_servicos.controller;

import br.csi.gestao_servicos.model.Funcionario.Funcionario;
import br.csi.gestao_servicos.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
@Tag(name = "Funcionários", description = "Path relacionado a opereções de funcionários")
public class FuncionarioController {
    private FuncionarioService funcionarioService;
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @GetMapping("/listar")
    @Operation(summary = "Listar funcionários", description = "Retorna uma lista de funcionários")
    public List<Funcionario> listar(){
        return this.funcionarioService.listar();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Funcionário por ID", description = "Retorna o funcionário correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })
    public Funcionario buscarId(@Parameter(description = "ID do funcionário a ser buscado") @PathVariable Long id){
        return this.funcionarioService.getFuncionarioByID(id);
    }
    @PostMapping("/print-json")
    @Operation(summary = "Printar JSON", description = "Printa o JSON fornecido")
    public void printJson(@RequestBody String json){

        System.out.println(json);
    }
    @PostMapping()
    @Operation(summary = "Criar novo funcionário", description = "Cria um novo funcionário e adiciona à lista")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Funcionario.class))),
                @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos",
                content = @Content)
        })

    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Funcionario funcionario, UriComponentsBuilder uriBuilder){
        this.funcionarioService.salvar(funcionario);
        URI uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }
    @PutMapping
    @Operation(summary = "Atualizar funcionário", description = "Atualiza um funcionário pelo ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos", content = @Content),
                @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
        })
    public ResponseEntity updateId(@RequestBody Funcionario funcionario){
        this.funcionarioService.atualizarByID(funcionario);
        return ResponseEntity.ok(funcionario);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar funcionário", description = "Deleta um funcionário pelo ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Funcionário deletado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
        })
    public void delete(@PathVariable Long id){
        this.funcionarioService.excluirByID(id);
    }
    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Buscar funcionário por UUID", description = "Retorna o funcionário correspondente ao UUID fornecido")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
        })
    public Funcionario buscarUuid(@PathVariable String uuid){
        return this.funcionarioService.getFuncionarioByUUID(uuid);
    }
    @PutMapping("/uuid")
    @Operation(summary = "Atualizar funcionário por UUID", description = "Atualiza um funcionário pelo UUID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos", content = @Content),
                @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
        })
    public ResponseEntity updateUuid(@RequestBody Funcionario funcionario){
        this.funcionarioService.atualizarByUUID(funcionario);
        return ResponseEntity.ok(funcionario);
    }
    @DeleteMapping("/uuid/{uuid}")
    @Operation(summary = "Deletar funcionário por UUID", description = "Deleta um funcionário pelo UUID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Funcionário deletado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
        })
    public void deleteUuid(@PathVariable String uuid) {
        this.funcionarioService.excluirByUUID(uuid);
    }


}
