package com.example.demo.controller;

import com.example.demo.entity.Consulta;
import com.example.demo.entity.ConsultaRequest;
import com.example.demo.entity.ConsultaResponse;
import com.example.demo.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ConsultaController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping("/consulta")
    public ResponseEntity<?> createProduct(@RequestBody ConsultaRequest consultaRequest) {

        // Envío el request con los parámetros recibidos
        String token = "cXdlcnR5bGFtYXJja19zYUBob3RtYWlsLmNvbXF3ZXJ0eQ==";
        String url = "http://wsruc.com/Ruc2WS_JSON.php?tipo=" + consultaRequest.getTipo() + "&ruc=" + consultaRequest.getRuc() + "&token=" + token;

        ConsultaResponse consultaResponse = restTemplate.getForObject(url, ConsultaResponse.class);

        if (consultaResponse == null || consultaResponse.getRuc() == null) {
            return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
        }

        // Genero una consulta nueva e inserto la información del response
        Consulta consulta = new Consulta();
        consulta.setRuc(consultaResponse.getRuc());
        consulta.setRazon_social(consultaResponse.getRazon_social());
        consulta.setEstado(consultaResponse.getEstado());
        consulta.setDireccion(consultaResponse.getDireccion());
        consulta.setUbigeo(consultaResponse.getUbigeo());
        consulta.setDepartamento(consultaResponse.getDepartamento());
        consulta.setProvincia(consultaResponse.getProvincia());
        consulta.setDistrito(consultaResponse.getDistrito());

        // Guardo la consulta generada en la base de datos
        consulta = consultaRepository.save(consulta);

        // Retorno la consulta creada
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    }

}
