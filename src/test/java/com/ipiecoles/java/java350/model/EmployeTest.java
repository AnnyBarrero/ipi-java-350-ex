package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.exception.EmployeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNow(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbauchePassee(){
        //Given
        //Date d'embauche 10 ans dans le passé
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(10));
        //employe.setDateEmbauche(LocalDate.of(2012, 4, 26)); //Pas bon...
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        // => 10
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(10);
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheFuture(){
        //Given
        //Date d'embauche 2 ans dans le futur
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(2));
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        // => 0
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }
    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNull(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        // => 0
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @ParameterizedTest
    @CsvSource({
            "'M12345',0,1,1.0,1700.0",
            "'M12345',2,1,1.0,1900.0",
            "'T12346',0,1,1.0,1000.0",
            "'T12346',0,2,1.0,2300.0",
            "'T12346',5,1,1.0,1500.0",
            ",0,1,1.0,1000.0",
            "'T12346',0,,1.0,1000.0",
            "'T12346',3,2,1.0,2600.0"
    })
    public void testGetPrimeAnnuelleManagerPerformanceBasePleinTemps(
            String matricule,
            Integer nbAnneesAnciennete,
            Integer performance,
            Double tauxActivite,
            Double prime
    ){
        //Given
        Employe employe = new Employe("Doe", "John", matricule,
                LocalDate.now().minusYears(nbAnneesAnciennete), 2500d, performance, tauxActivite);
        //When
        Double primeObtenue = employe.getPrimeAnnuelle();
        //Then
        Assertions.assertThat(primeObtenue).isEqualTo(prime);
    }

    @Test
    void testAugmenterSalaire() throws EmployeException{
        //Given
        Employe employe = new Employe();
        employe.setSalaire(1000.0);
        //When
        employe.augmenterSalaire(0.15);
        // Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(1150.0);
    }

    @ParameterizedTest
    @CsvSource( {
          "1000.0, -0.0, 1000.0",
          "1500.0, 0.15, 1725.0",
          "1700.0, 0.75, 2975.0",
          "2000.0, 1.50, 5000.0",
    })
    void testAugmenterSalaireCasNominaux(Double ancienSalaire, Double augmentation, Double nouveauSalaire) throws EmployeException {

        //Given
        Employe employe = new Employe();
        employe.setSalaire(ancienSalaire);
        //When
        employe.augmenterSalaire(augmentation);
        // Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(nouveauSalaire);
    }

    @ParameterizedTest
    @CsvSource( {
          "1000.0, 0.25, 1250.0",
          "1500.0, 0.95, 2925.0",
          "1700.0, 1.05, 3485.0",
          "2000.0, 2.50, 7000.0",
    })
    void testAugmenterSalaireCasException(Double ancienSalaire, Double augmentation, Double nouveauSalaire) throws EmployeException {

        //Given
        Employe employe = new Employe();
        employe.setSalaire(ancienSalaire);
        //When
        employe.augmenterSalaire(augmentation);
        // Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(nouveauSalaire);
    }

    @Test
    void testAugmenterSalaireWithoutException() throws EmployeException{



    }
}
