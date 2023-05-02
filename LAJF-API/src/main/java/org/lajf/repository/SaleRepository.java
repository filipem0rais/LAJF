//
// Auteur : Filipe Dias Morais
// Projet : LAJF-API
// Date   : 02.05.2023
// 


package org.lajf.repository;

import org.lajf.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
