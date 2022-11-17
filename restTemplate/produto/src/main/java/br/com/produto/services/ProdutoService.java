package br.com.produto.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.produto.dtos.ProdutoResponseDTO;
import br.com.produto.dtos.ProdutoResponseDTO2;
import br.com.produto.entity.Produto;

@Service
public class ProdutoService {

	private static final String GET_PRODUTOS_SERVICE_ENDPOINT_URL = "http://localhost:8080//produto-service/produtosServices";
	private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
	private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees";
	private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
	private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";

	private static RestTemplate restTemplate = new RestTemplate();

	// TRAZER A LISTA DE PRODUTOS
	public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
		// PODE FAZER A TRANSFORMAÇÃO DIRETO O OBJETO EM DTO QUANDO OS ATRIBUTOS SAO IGUAIS DO CONSUMIDOR COM O SERVIÇO PARA MOSTRAR AO USUARIO
		// EXEMPLO : PRODUTO_SERVICE(SERVICO) COM PTODUTO(CONSUMIDOR)

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<List<ProdutoResponseDTO>> produtoResponseDTO = restTemplate.exchange(
				GET_PRODUTOS_SERVICE_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<ProdutoResponseDTO>>() {
				});

		return produtoResponseDTO;
	}

	public List<ProdutoResponseDTO2> findAll02() {
		// PODEMOS PEGAR O OBJETO E EDITAR PARA MOSNTRAR SOMENTE OS ATRIBUTOS QUE DESEJAMOS NO DTO PARA USUARIO
		ResponseEntity<ProdutoResponseDTO[]> response = restTemplate.getForEntity(GET_PRODUTOS_SERVICE_ENDPOINT_URL,
				ProdutoResponseDTO[].class);

		List<ProdutoResponseDTO2> listaProdutoResponseDTO = new ArrayList<>();
		ProdutoResponseDTO[] produtoResponseDTO = response.getBody();
		for (ProdutoResponseDTO produtoResponse : produtoResponseDTO) {
			ProdutoResponseDTO2 pResponseDTO = new ProdutoResponseDTO2();

			pResponseDTO.setId(produtoResponse.getId());
			pResponseDTO.setNome(produtoResponse.getNome());
			pResponseDTO.setDescricao(produtoResponse.getDescricao());

			listaProdutoResponseDTO.add(pResponseDTO);
		}
		return listaProdutoResponseDTO;

	}

}
