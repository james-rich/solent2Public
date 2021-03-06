/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import solent.ac.uk.ood.examples.cardvalidator.cvv.impl.TrivialCvvAlgorythimStrategy;
import solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCardFactoryAndValidator;

/**
 *
 * @author cgallen
 */
public class CardFactoryDAOImpl implements CardFactoryDAO {
    
    Map<String,CreditCardFactoryAndValidator> cardFactoryMap = new HashMap<String,CreditCardFactoryAndValidator>();
    
    public CardFactoryDAOImpl(){
        
        // you need to use the actual algorythims you created - one for each bank
        String iin = SupportedCardIssuerIdentificationNumbers.VISA_NAT_WEST;
        CreditCardFactoryAndValidator ccfv = new CreditCardFactoryAndValidatorImpl();
        ccfv.setCvvAlgorythim(new VisaNatwestCvvStratergy());
        ccfv.setIssuerIdentificationNumber(iin);
        cardFactoryMap.put(iin, ccfv);
        
        iin=SupportedCardIssuerIdentificationNumbers.VISA_BANK_OF_IRELAND_UK;
        ccfv = new CreditCardFactoryAndValidatorImpl();
        ccfv.setCvvAlgorythim(new VisaBoiCvvStratergy());
        ccfv.setIssuerIdentificationNumber(iin);
        cardFactoryMap.put(iin, ccfv);
        
        iin = SupportedCardIssuerIdentificationNumbers.MASTERCARD_TSB_BANK;
        ccfv = new CreditCardFactoryAndValidatorImpl();
        ccfv.setCvvAlgorythim(new MastercardTsbCvvStratergy());
        ccfv.setIssuerIdentificationNumber(iin);
        cardFactoryMap.put(iin, ccfv);
        
        iin = SupportedCardIssuerIdentificationNumbers.MASTERCARD_LLOYDS_BANK_PLC;
        ccfv = new CreditCardFactoryAndValidatorImpl();
        ccfv.setCvvAlgorythim(new MastercardLloydsCvvStratergy());
        ccfv.setIssuerIdentificationNumber(iin);
        cardFactoryMap.put(iin, ccfv);
        
        iin = SupportedCardIssuerIdentificationNumbers.AMERICAN_EXPRESS_LLOYDS_BANK_PLC;
        ccfv = new CreditCardFactoryAndValidatorImpl();
        ccfv.setCvvAlgorythim(new AmexLloydsCvvStratergy());
        ccfv.setIssuerIdentificationNumber(iin);
        cardFactoryMap.put(iin, ccfv);
        
    }

    @Override
    public CreditCardFactoryAndValidator getCreditCardFactoryAndValidator(String issuerIdentifierNumber) {
        CreditCardFactoryAndValidator ccfv = cardFactoryMap.get(issuerIdentifierNumber);
        if (ccfv==null)
            throw new IllegalArgumentException("cannot create factory for unknown issuerIdentifierNumber:"+issuerIdentifierNumber);
        return ccfv;
    }

    @Override
    public List<String> getSupportedIssuerNames() {
       return new ArrayList<String>( SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.keySet());
    }

    @Override
    public String getIssuerIdentifierNumberForName(String name) {
        return SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.get(name);
    }
    
}
