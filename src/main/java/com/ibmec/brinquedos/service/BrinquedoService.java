package com.ibmec.brinquedos.service;

import com.ibmec.brinquedos.exception.BrinquedoNaoEncontradoException;
import com.ibmec.brinquedos.model.Brinquedo;
import com.ibmec.brinquedos.repository.BrinquedoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BrinquedoService {

    private final BrinquedoRepository repository;

    public BrinquedoService(BrinquedoRepository repository) {
        this.repository = repository;
    }

    public List<Brinquedo> listarTodos() {
        return repository.findAll();
    }

    public List<Brinquedo> listarPorTipo(String tipo) {
        return repository.findByTipoIgnoreCase(tipo);
    }

    public List<Brinquedo> listarPorIdadeMaxima(Integer idade) {
        return repository.findByClassificacaoLessThanEqual(idade);
    }

    public Brinquedo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BrinquedoNaoEncontradoException(id));
    }

    public Brinquedo criar(Brinquedo brinquedo) {
        brinquedo.setId(null);
        return repository.save(brinquedo);
    }

    public List<Brinquedo> criarVarios(List<Brinquedo> lista) {
        lista.forEach(b -> b.setId(null));
        return repository.saveAll(lista);
    }

    public Brinquedo atualizar(Long id, Brinquedo dados) {
        Brinquedo existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setTipo(dados.getTipo());
        existente.setClassificacao(dados.getClassificacao());
        existente.setTamanho(dados.getTamanho());
        existente.setPreco(dados.getPreco());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new BrinquedoNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }
}