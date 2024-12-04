package br.ejb; 
import jakarta.ejb.ActivationConfigProperty; 
import jakarta.ejb.MessageDriven; 
import jakarta.jms.Message; 
import jakarta.jms.MessageListener; 

    @MessageDriven(activationConfig={ 
        @ActivationConfigProperty( 
            propertyName="destinationLookup", 
            propertyValue="java/Fila"), 
        @ActivationConfigProperty( 
            propertyName="destinationType", 
            propertyValue="jakarta.jms.Queue") 
    })

    public class EJBConsumidor implements MessageListener {   
        @Override 
        public void onMessage(Message msg) { 
            System.out.println("Mensagem recebida"); 
        } 
    }

