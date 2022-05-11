package com.example.cabinetcomptable.services.impl;

import com.example.cabinetcomptable.entities.BonAchat;
import com.example.cabinetcomptable.entities.BonHonoraire;
import com.example.cabinetcomptable.entities.Facture;
import com.example.cabinetcomptable.repositories.BonAchatRepository;
import com.example.cabinetcomptable.repositories.BonHonoraireRepository;
import com.example.cabinetcomptable.repositories.FactureRepository;
import com.example.cabinetcomptable.services.GenerateFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class GenerateFormatServiceImlp implements GenerateFormatService {

    @Autowired
    private BonAchatRepository bonAchatRepository;

    @Autowired
    BonHonoraireRepository bonHonoraireRepository;

    @Autowired
    FactureRepository factureRepository;

    @Override
    public String formatCodeClient(long id) {
        return String.format("C-"+"%04d" , id);
    }

    @Override
    public String formatCodeFournisseur(long id) {
        return String.format("F-"+"%04d" , id);
    }

    @Override
    public String formatNumeroBonAchat(Date date) {

        LocalDate newDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatYearMonth = DateTimeFormatter.ofPattern("yyMM");
        String yearMonth = newDate.format(formatYearMonth);
        System.out.println(yearMonth);
        BonAchat lastBonAchat = bonAchatRepository.findTopByOrderByIdBaDesc();

        if (lastBonAchat == null) {
            return "BA-"+yearMonth+"-"+ String.format("%04d" ,1 );
        }
        else{

            LocalDate oldDate = LocalDate.parse(lastBonAchat.getDateBa().toString());

             if(newDate.getYear() == oldDate.getYear()){

                String lastIdString  = lastBonAchat.getBonANum();
                int id = Integer.parseInt(lastIdString.substring(lastIdString.lastIndexOf("-") + 1));
                return "BA-"+yearMonth+"-"+ String.format("%04d" , id+1 );
            }
            else{
                return "BA-"+yearMonth+"-"+ String.format("%04d" ,1 );
            }
        }


    }

    @Override
    public String formatNumeroBonHonoraire(Date date) {
        LocalDate newDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatYearMonth = DateTimeFormatter.ofPattern("yyMM");
        String yearMonth = newDate.format(formatYearMonth);
        System.out.println(yearMonth);
        BonHonoraire lastBonHonoraire = bonHonoraireRepository.findTopByOrderByIdBhDesc();

        if (lastBonHonoraire == null) {
            return "BH-"+yearMonth+"-"+ String.format("%04d" ,1 );
        }
        else{

            LocalDate oldDate = LocalDate.parse(lastBonHonoraire.getDateBh().toString());

            if(newDate.getYear() == oldDate.getYear()){

                String lastIdString  = lastBonHonoraire.getBonHNum();
                int id = Integer.parseInt(lastIdString.substring(lastIdString.lastIndexOf("-") + 1));
                return "BH-"+yearMonth+"-"+ String.format("%04d" , id+1 );
            }
            else{
                return "BH-"+yearMonth+"-"+ String.format("%04d" ,1 );
            }
        }
    }

    @Override
    public String formatNumeroFacture(Date date) {
        LocalDate newDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatYearMonth = DateTimeFormatter.ofPattern("yyMM");
        String yearMonth = newDate.format(formatYearMonth);
        System.out.println(yearMonth);
        Facture lastFacture = factureRepository.findTopByOrderByIdFacDesc();

        if (lastFacture == null) {
            return "FA-"+yearMonth+"-"+ String.format("%04d" ,1 );
        }
        else{

            LocalDate oldDate = LocalDate.parse(lastFacture.getDateFac().toString());

            if(newDate.getYear() == oldDate.getYear()){

                String lastIdString  = lastFacture.getFacNum();
                int id = Integer.parseInt(lastIdString.substring(lastIdString.lastIndexOf("-") + 1));
                return "FA-"+yearMonth+"-"+ String.format("%04d" , id+1 );
            }
            else{
                return "FA-"+yearMonth+"-"+ String.format("%04d" ,1 );
            }
        }
    }
}
