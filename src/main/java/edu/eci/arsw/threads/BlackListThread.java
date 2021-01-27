/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
/**
 *
 * @author David Coronado
 */
public class BlackListThread extends Thread{
    
    LinkedList<Integer> blackListOcurrences=new LinkedList<>();
    private static final int BLACK_LIST_ALARM_COUNT=5;
    private final int minValue;
    private final int maxValue;
    private final HostBlacklistsDataSourceFacade skds;
    private int ocurrencesCount;
    private int checkedListsCount;
    private final String ipaddress;
    
    
    public BlackListThread(int minValue,int maxValue,HostBlacklistsDataSourceFacade skds,String ipaddress){
        this.minValue=minValue;
        this.maxValue=maxValue;
        this.skds=skds;
        this.ocurrencesCount=0;
        this.checkedListsCount=0;
        this.ipaddress=ipaddress;
        
    }
    
    @Override
    public void run(){
        for (int i=minValue;i<=maxValue && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ipaddress)){                
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }

        }
        
    }

    public int getOcurrencesCount() {
        return ocurrencesCount;
    }

    public int getCheckedListsCount() {
        return checkedListsCount;
    }

    
    
    
}
