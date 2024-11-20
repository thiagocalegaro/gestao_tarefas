package br.csi.gestao_servicos.controller;

import br.csi.gestao_servicos.model.Funcionario.Funcionario;
import br.csi.gestao_servicos.model.Tarefa.Tarefa;
import br.csi.gestao_servicos.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//testando
@RestController
@RequestMapping("/tarefa")
@Tag(name = "tarefas", description = "Path relacionado a operações de tarefas")
public class TarefaController {
    private TarefaService tarefaService;
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    @GetMapping("/listar")
    @Operation(summary = "Listar tarefas", description = "Retorna uma lista de tarefas")
    public void listar(){
        this.tarefaService.listar();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna o tarefa correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Tarefa.class))),
            @ApiResponse(responseCode = "404", description = "tarefa não encontrado", content = @Content)
    })
    public Tarefa buscarId(@Parameter(description = "ID do tarefa a ser buscado") @PathVariable Long id){
        return this.tarefaService.getServicoByID(id);
    }
    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Buscar tarefa por UUID", description = "Retorna o tarefa correspondente ao UUID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Tarefa.class))),
            @ApiResponse(responseCode = "404", description = "tarefa não encontrado", content = @Content)
    })
    public Tarefa buscarUuid(@Parameter(description = "UUID do tarefa a ser buscado") @PathVariable String uuid){
        return this.tarefaService.getServicoByUUID(uuid);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tarefa por ID", description = "Exclui o tarefa correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa excluído com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "tarefa não encontrado", content = @Content)
    })
    public void excluirId(@Parameter(description = "ID do tarefa a ser excluído") @PathVariable Long id){
        this.tarefaService.excluirByID(id);
    }
    @DeleteMapping("/uuid/{uuid}")
    @Operation(summary = "Excluir tarefa por UUID", description = "Exclui o tarefa correspondente ao UUID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa excluído com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "tarefa não encontrado", content = @Content)
    })
    public void excluirUuid(@Parameter(description = "UUID do tarefa a ser excluído") @PathVariable String uuid){
        this.tarefaService.excluirByUUID(uuid);
    }
    @PostMapping()
    @Operation(summary = "Criar nova tarefa", description = "Cria um novo tarefa e adiciona à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tarefa criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Tarefa.class))),
            @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos", content = @Content)
    })
    public void salvar(@RequestBody @Valid Tarefa tarefa){
        this.tarefaService.salvar(tarefa);
    }
    @PutMapping
    @Operation(summary = "Atualizar tarefa", description = "Atualiza um tarefa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa atualizado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Tarefa.class))),
            @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "tarefa não encontrado", content = @Content)
    })
    public ResponseEntity updateId(@RequestBody Tarefa tarefa){
        this.tarefaService.atualizarByID(tarefa);
        return ResponseEntity.ok(tarefa);
    }
    @PutMapping("/uuid")
    @Operation(summary = "Atualizar tarefa por UUID", description = "Atualiza um tarefa pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa atualizado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Tarefa.class))),
            @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "tarefa não encontrado", content = @Content)
    })
    public ResponseEntity updateUuid(@RequestBody Tarefa tarefa){
        this.tarefaService.atualizarByUUID(tarefa);
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping("/vincular")
    @Operation(summary = "Vincular tarefa ao funcionário", description = "Vincula uma tarefa a um funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa vinculada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa ou funcionário não encontrado")
    })
    public void vincularTarefaAoFuncionario(@RequestBody Funcionario funcionario, @RequestParam Long tarefa) {
        this.tarefaService.vincularTarefaAoFuncionario(tarefa, funcionario);
    }
    public void lista(){
        this.tarefaService.listar();
    }


}

