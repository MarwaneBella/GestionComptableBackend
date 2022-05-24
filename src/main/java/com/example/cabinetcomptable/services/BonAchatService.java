package com.example.cabinetcomptable.services;

import com.example.cabinetcomptable.entities.BonAchat;
import com.example.cabinetcomptable.entities.Fournisseur;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface BonAchatService {

    String getNextBonANum(Date date);

    String getCurrentBonANum(long id, Date date);

    ResponseEntity<BonAchat> addBonAchat(BonAchat bonAchat);
    ResponseEntity<BonAchat> getBonAchat(long id_ba);
    List<BonAchat> getAllBonAchats();
    ResponseEntity<BonAchat> updateBonAchat(BonAchat BonAchatDtails , long id_ba);
    void deleteBonAchat(long id_ba);

    //
    BonAchat getB(long id);

    List<BonAchat> getAllBonAchatsByFournisseur(Fournisseur fournisseur);


}
