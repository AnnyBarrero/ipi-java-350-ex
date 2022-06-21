package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/*@SpringBootTest*/
@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

    @Mock
    EmployeRepository employeRepository;

    @InjectMocks
    EmployeService employeService = new EmployeService();
    @BeforeEach
    public void before(){
        employeRepository.deleteAll();
    }

    @Test
    public void testEmbaucheEmploye() throws EmployeException {
        employeService.embaucheEmploye("Doe", "John", Poste.COMMERCIAL,NiveauEtude.MASTER, 1.0);


        Employe employe = employeRepository.findByMatricule("C00001");
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository).save(employeArgumentCaptor.capture());
        Assertions.assertThat(employe.getNom()).isEqualTo("Doe");
        Assertions.assertThat(employe.getPrenom()).isEqualTo("John");
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(Entreprise.PERFORMANCE_BASE);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2129.71);
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(1);
    }

}
